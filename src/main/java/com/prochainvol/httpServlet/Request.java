package com.prochainvol.httpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.prochainvol.Constants;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.api.TravelType;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.request.filter.Filter;
import com.prochainvol.api.response.RequestResult;
import com.prochainvol.dom.DomUtilities;
import com.prochainvol.json.JsonUtilities;
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.ui.ProchainvolHeader;
import com.prochainvol.ui.ResponsiveVisitor;
import com.prochainvol.ui.UiUtilities;

@WebServlet("/Request")
@SuppressWarnings("serial")
public class Request extends HttpServlet {

	private static final Logger logger = Logger.getLogger(Request.class
			.getName());

	public static void checkParameter(String parameter, String paramName)
			throws ServletException {
		if (parameter == null || parameter.length() == 0) {
			String msg = String.format("Pas de paramètre '%s'", paramName);
			logger.error(msg);
			throw new ServletException(msg);
		}
	}

	public static String getOutputStatus(HttpServletRequest request) {
		String output = request.getParameter("output");
		if (output == null || !output.equals("json")) {
			// html is the default value
			output = "html";
		}
		return output;
	}

	private synchronized ProchainvolConfig getProchainvolConfig(
			HttpServletRequest request, HttpServletResponse httpResponse,
			String output) throws IOException, ServletException {
		HttpSession session = request.getSession();
		ProchainvolConfig prochainvolConfig = (ProchainvolConfig) session
				.getAttribute(Constants.SESS_PROCHAINVOL_CONFIG);
		if (prochainvolConfig == null) {
			
			prochainvolConfig = (ProchainvolConfig) session.getServletContext().getAttribute(Constants.SESS_PROCHAINVOL_CONFIG);

			if (prochainvolConfig == null) {
				try {
					prochainvolConfig = new ProchainvolConfig();
				} catch (ProchainvolException e) {
					String msg = "Impossible d'initialiser prochainvolConfig : "
							+ e.getMessage();
					logger.fatal(msg);
					jsonResponse(httpResponse, "{}", msg);
				}
			}
		}
		return prochainvolConfig;
	}

	public static void jsonResponse(HttpServletResponse httpResponse,
			String result, String mess) throws IOException {
		httpResponse.setContentType("application/json; charset=UTF-8");
		httpResponse.setCharacterEncoding("UTF-8");
		PrintWriter out = httpResponse.getWriter();
		out.print(JsonUtilities.createResponse(mess, result));
	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO validation des valeurs de parametres en javascript

		String output = Request.getOutputStatus(request);

		ProchainvolConfig prochainvolConfig = getProchainvolConfig(
				request, response, output);
		RequestParams params = null;
		try {
			String flitersAsString = request.getParameter("filter");
			if (flitersAsString != null) {
				Filter filter = JsonUtilities.readFromString(Filter.class,
						flitersAsString);
				if (filter != null) {
					prochainvolConfig.setCurrentFlightFilters(filter);
				}
			}
			// get parameter action
			String actionName = "action";
			String action = request.getParameter(actionName);
			if (action == null) {
				action = "Send";
			} else if (action.equals("Acceuil")) {
				response.sendRedirect("index.jsp");
				return;
			}
			if (!action.equals("Send")) {
				String msg = "Valeur inconnue pour le paramêtre 'action' : "
						+ action;
				logger.fatal(msg);
				throw new ProchainvolException(msg);
			}

			// get parameter mode
			String paramName = "mode";
			String modeAsString = request.getParameter(paramName);
			boolean isSynchronous = true;
			if (modeAsString != null && modeAsString.equals("asynchronous")) {
				isSynchronous = false;
			}

			String departureIataAsString = request.getParameter("departAirport");;
			checkParameter(departureIataAsString, "departAirport");

			String arrivalIataAsString = request.getParameter("arrivalAirport");
			checkParameter(arrivalIataAsString, "arrivalAirport");

			// get parameter week
			paramName = "week";
			String weekAsString = request.getParameter(paramName).trim();
			logger.trace("week = " + weekAsString);
			int week = Constants.DEFAULT_WEEK;
			if (weekAsString != null && weekAsString.matches("^\\d+$")) {
				week = Integer.parseInt(weekAsString);
			}

			// get parameter tripType
			paramName = "tripType";
			String tripType = request.getParameter(paramName);
			checkParameter(tripType, paramName);
			TravelType travelType = TravelType.valueOf(tripType);
			Date departDate = null;
			Date returnDate = null;
			switch (travelType) {
			case RETURN: // Aller Retour
				paramName = "returnDate";
				String returnDateAsString = request.getParameter(paramName);
				checkParameter(returnDateAsString, paramName);
				returnDate = Constants.DATE_PICKER_DATE_FORMAT
						.parse(returnDateAsString);
			case ONE_WAY: // Aller simple
				// get parameter departDate
				paramName = "departDate";
				String departDateAsString = request.getParameter(paramName);
				checkParameter(departDateAsString, paramName);
				departDate = Constants.DATE_PICKER_DATE_FORMAT
						.parse(departDateAsString);
				break;
			default:
				logger.trace("travelType = " + travelType + ", week = " + week);

				// get parameters week and returnDate
				returnDate = TravelDates.getTravelDates(travelType, week)
						.getReturnDate().getDate();
				departDate = TravelDates.getTravelDates(travelType, week)
						.getDepartureDate().getDate();
				break;
			}

			params = new RequestParams(departureIataAsString, arrivalIataAsString, departDate,
					returnDate, prochainvolConfig.getMaxStop());
			params.setWeek(week);

			// set parameter noReportUnit (default to false)
			paramName = "withReportUnit";
			String reportUnitAsString = request.getParameter(paramName);
			boolean withReportUnit = false;
			if (reportUnitAsString != null && reportUnitAsString.equals("true")) {
				withReportUnit = true;
			}
			params.setWithReportUnit(withReportUnit);

			// set requestType
			params.setTravelType(travelType);

			paramName = "stops";
			String stopsAsString = request.getParameter(paramName);
			checkParameter(stopsAsString, paramName);
			if (stopsAsString.equals("Any")) {
				params.setStops(Constants.ANY_VALUE_FOR_MAX_STOP);
			} else {
				params.setStops(Integer.parseInt(stopsAsString));
			}

			// get parameter adults
			paramName = "adults";
			String adultsAsString = request.getParameter(paramName);
			if (adultsAsString == null) {
				params.setAdults(Constants.DEFAULT_ADULTS);
			} else {
				params.setAdults(Integer.parseInt(adultsAsString));
			}

			// get parameter children
			paramName = "children";
			String childrenAsString = request.getParameter(paramName);
			if (childrenAsString == null) {
				params.setChildren(Constants.DEFAULT_CHILDREN);
			} else {
				params.setChildren(Integer.parseInt(childrenAsString));
			}

			// get parameter infants
			paramName = "infants";
			String infantsAsString = request.getParameter(paramName);
			if (infantsAsString == null) {
				params.setInfants(Constants.DEFAULT_INFANTS);
			} else {
				params.setInfants(Integer.parseInt(infantsAsString));
			}

			RequestResult requestResults = null;

			if (!isSynchronous) {
				params.setSynchronous(false);
			}

			logger.trace("params = " + params);
			requestResults = prochainvolConfig.request(params);
			ProchainvolHeader prochainvolHeader = new ProchainvolHeader(
					"Rapport de Requête", prochainvolConfig, true);
			ProchainvolObject[] affichables = { params, requestResults };
			String typeParamName = "output";
			String type = request.getParameter(typeParamName);
			if (type == null || type.equals("json")) {
				String json = UiUtilities.getJsonContent(withReportUnit,
						affichables);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json; charset=UTF-8");
				out.write(json);
			} else if (type.equals("html")) {
				ResponsiveVisitor visitor = new ResponsiveVisitor(
						prochainvolConfig);
				String html = UiUtilities.getHtmlContent(visitor,
						prochainvolHeader, affichables);
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();

				out.write(DomUtilities.headers);
				out.write(html);
			} else {
				String msg = String.format(
						"valeur de parametre '%s' ignorée = %", typeParamName,
						type);
				logger.error(msg);
			}
		} catch (Exception e) {
			if (output.equals("json")) {
				try {
					String result = JsonUtilities.createResponseErr(e
							.getClass().getName() + " : " + e.getMessage());
					response.setContentType("application/json; charset=UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.print(result);
				} catch (java.io.CharConversionException e1) {
					throw new ServletException(e1);
				}
			} else {
				request.setAttribute("params", params);
				request.setAttribute("errorMess", e.getMessage());
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/RequestUI");
				dispatcher.forward(request, response);
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
