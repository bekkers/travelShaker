package com.prochainvol.httpServlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.prochainvol.Constants;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.api.EXECUTOR_TYPE;
import com.prochainvol.api.provider.PROVIDER;

/**
 * Servlet implementation class Recharger
 */
@WebServlet("/Recharger")
public class Recharger extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// get parameter departureAirport
		String readerParamName = "airportReader";
		String readerName = request.getParameter(readerParamName);
		if (readerName == null) {
			String msg = String
					.format("Impossible de démarrer/redémarrer Prochainvol, pas de parametre '%s'",
							readerParamName);
			logger.error(msg);
			throw new ServletException(msg);
		}


		String requestReadersLibelle = "requestReaders";
		String[] requestReaderNames = request
				.getParameterValues(requestReadersLibelle);
		if (requestReaderNames.length == 0) {
			String msg = String
					.format("Impossible de démarrer/redémarrer Prochainvol, pas de parametre '%s'",
							requestReadersLibelle);
			logger.error(msg);
			throw new ServletException(msg);
		}
		List<PROVIDER> requestReaders = Arrays.asList(requestReaderNames)
				.stream().map(r -> PROVIDER.valueOf(r))
				.collect(Collectors.toList());

		String executorParamName = "executorType";
		EXECUTOR_TYPE executorType = EXECUTOR_TYPE.DIRECT;
		String executorTypeAsString = request.getParameter(executorParamName);

		if (executorTypeAsString != null) {
			try {
				executorType = EXECUTOR_TYPE.valueOf(executorTypeAsString
						.toUpperCase());
			} catch (IllegalArgumentException e) {
				String mess = String.format(
						"Incorrect executor type parameter : ",
						executorTypeAsString);
				logger.error(mess);
				throw new ServletException(mess);
			}

		}

		String stopParamName = "stops";
		int nbStops = 0;
		String stopsAsString = request.getParameter(stopParamName);
		if (stopsAsString != null) {
			if (stopsAsString.equals("Any")) {
				nbStops = Constants.ANY_VALUE_FOR_MAX_STOP;
			} else {
				nbStops = Integer.parseInt(stopsAsString);
			}
		}

		// TODO mettre le paramatre saveReload dans l'interface utilisateur
		synchronized (this.getServletContext()) {
			try {
				HttpSession session = request.getSession();
				ProchainvolConfig oldProchainvolConfig = (ProchainvolConfig) session.getAttribute(
						Constants.SESS_PROCHAINVOL_CONFIG);
				ProchainvolConfig prochainvolConfig = new ProchainvolConfig(executorType, requestReaders.toArray(new PROVIDER[requestReaders
								.size()]));
				prochainvolConfig.setUser(oldProchainvolConfig.getUser());
				logger.info(String.format("recharger prochainvolConfig = %s",
						prochainvolConfig));
				session.setAttribute(
						Constants.SESS_PROCHAINVOL_CONFIG, prochainvolConfig);
			} catch (Throwable e) {
				String msg = "Impossible de démarrer Prochainvol avec "
						+ readerName;
				logger.error(msg);
				throw new ServletException(msg, e);
			}
		}
		response.sendRedirect("index.jsp");
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
