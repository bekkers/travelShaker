package com.prochainvol.httpServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.api.TravelType;
import com.prochainvol.json.JsonUtilities;

/**
 * Servlet implementation class GetTravelTypes
 */
@WebServlet("/GetTravelTypes")
public class GetTravelTypes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = Logger.getLogger(GetTravelTypes.class
			.getName());



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		StringBuffer json = new StringBuffer("{");
		int i=0;
		final int MAX = TravelType.values().length;
		for (TravelType travelType : TravelType.values()) {
			i++;
			json.append("\n  \"").append(travelType.name()).append("\":\"").append(travelType.toString()).append("\"");
			if (i<MAX) {
				json.append(",");
			}
		}
		json.append("\n}");
		try {
			response.getWriter().write(JsonUtilities.createResponse(json.toString()));
		} catch (IOException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
