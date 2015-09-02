package com.prochainvol.httpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.json.JsonUtilities;
import com.prochainvol.sql.airport.Airports;
import com.prochainvol.sql.airport.SqlAirport;

/**
 * fournit la liste des aéroports avec leurs Iata et leur fullname.
 * 
 * Cette liste est utilisée pour fournir l'assistance de choix des aéroports
 * dans les formulaires Cette liste est calculée une seule et unique fois coté
 * serveur dans la méthode init(). La propriété
 * <load-on-startup>1</load-on-startup> dans le fichier web.xml garantie que ce
 * calcul est effectué au chargement de l'application.
 * 
 * Cette liste est mise en cache chez le client par la méthode js
 * 'loadOptionList()'
 */
@WebServlet("/GetAirports")
public class GetAirports extends HttpServlet {
	private static class AirportInfo {
		private String iata;

		private String name;

		@SuppressWarnings("unused")
		public AirportInfo() {
			super();
		}

		public AirportInfo(String iata, String name) {
			super();
			this.iata = iata;
			this.name = name;
		}

		@SuppressWarnings("unused")
		public String getIata() {
			return iata;
		}

		@SuppressWarnings("unused")
		public String getName() {
			return name;
		}

		@SuppressWarnings("unused")
		public void setIata(String iata) {
			this.iata = iata;
		}
		@SuppressWarnings("unused")
		public void setName(String name) {
			this.name = name;
		}
	}

	private static final long serialVersionUID = 1L;

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private List<AirportInfo> airports = new ArrayList<AirportInfo>();

	public synchronized void initAirportInfo()
			throws ServletException {
		// TODO bug pourquoi le paramatrre session ?
			if (airports == null) {
				// initialisation de la table des airportInfo pour l'assistant
				// de saisie dans l'interface
				try {
					for (SqlAirport airport : Airports.getInstance().getAll()
							.values()) {
						airports.add(new AirportInfo(airport.getIata(), airport
								.getFullName()));
					}
				} catch (ProchainvolException e) {
					logger.fatal("impossible d'initialiser les aéroports : " +e.getMessage());
					throw new ServletException(e);
				}
			}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String output = Request.getOutputStatus(request);

			ProchainvolConfig prochainvolConfig = Request.getProchainvolConfig(request,
					response, output);
			initAirportInfo(request.getSession());
			String result = JsonUtilities.getGson().toJson(airports);
			response.setContentType("application/json; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		} catch (java.io.CharConversionException e) {
			throw new ServletException(e);
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
