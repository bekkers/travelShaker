package com.prochainvol.httpServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.prochainvol.TestConstants;
import com.prochainvol.api.TravelType;

/**
 * Servlet implementation class GetReturnDate
 */
@WebServlet("/GetTravelDates")
public class GetTravelDates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = Logger.getLogger(GetTravelDates.class.getName());
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// get parameter tripType
		String paramName = "tripType";
		String tripType = request.getParameter(paramName);
		Request.checkParameter(tripType, paramName);
		TravelType travelType = TravelType.valueOf(tripType);
		
		// get parameter week
		paramName = "week";
		String weekAsString = request.getParameter(paramName).trim();
		logger.trace("weekAsString = "+weekAsString);
		int week = TestConstants.DEFAULT_WEEK;
		if (weekAsString!=null) {
				week = Integer.parseInt(weekAsString);
		}
		
		
		TravelDates travelDates = TravelDates.getTravelDates(travelType, week);
		logger.trace("travelDates = "+travelDates);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(travelDates.datePickerFormat());
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
