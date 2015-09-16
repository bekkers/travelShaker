package com.prochainvol.httpServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.json.JsonUtilities;
import com.prochainvol.sql.SqlAirport;

@WebServlet("/GetTravelPlace")
public class GetTravelPlace extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(GetTravelPlace.class
			.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTravelPlace() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String output = Request.getOutputStatus(request);

		ProchainvolConfig prochainvolConfig = Request.getProchainvolConfig(
				request, response, output);
		String whichParameter = request.getParameter("which");
		String json = null;
		try {
			if (whichParameter == null) {
				throw new ProchainvolException("manque le parametre 'which'");
			}

			// which = {AIRPORT, RAILWAY_STATION, FERRY_TERMINAL, UNKNOWN, ALL,
			// iata}
			if (whichParameter.equals("iata")) {
				String iata = request.getParameter("iata");
				if (iata == null) {
					throw new ProchainvolException("manque le parametre 'iata'");
				}

				SqlAirport travelplace = prochainvolConfig
						.getAirports().getAirport(iata);
				if (travelplace == null) {
					json = JsonUtilities.createResponseErr("Aita inconnu = " + iata);
				} else {
					json = JsonUtilities.createResponse(travelplace.toString());
				}

			} 
		} catch (IllegalArgumentException | ProchainvolException e) {
			json = JsonUtilities.createResponseErr(e.getMessage());
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(json);
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
