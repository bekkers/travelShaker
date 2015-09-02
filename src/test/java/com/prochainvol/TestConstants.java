package com.prochainvol;

import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;

import com.prochainvol.api.request.RequestParams;
import com.prochainvol.json.JsonUtilities;
import com.prochainvol.sql.airport.DebugAirportReader;

public class TestConstants {

	public static final RequestParams oneMonthToGoRequestParamFromJson;

	public static final int DEFAULT_WEEK = 0;
	public static final Date TO_DAY = new Date();
	public static final Date returnDate = ProchainvolUtilities.addMonth(
			ProchainvolUtilities.addDays(TO_DAY, 14), 1);
	public static final int oneMonthToGoMonth;

	public static final int oneMonthToGoDayOfMonth;
	public static final String TO_DAY_GSON_FORMATED = JsonUtilities
			.getGSON_DATE_FORMAT().format(TO_DAY);
	public static final String formatedReturnDate = JsonUtilities
			.getGSON_DATE_FORMAT().format(returnDate);
	public static final Date oneMonthToGoDepartureDate = ProchainvolUtilities
			.addMonth(TO_DAY, 1);
	public static final String jsonFormatedDepartDate = JsonUtilities
			.getGSON_DATE_FORMAT().format(oneMonthToGoDepartureDate);
	
	public static final String londonAllIata = "LON";
	public static final String marseilleProvenceIata = "MRS";

	
	static {
		oneMonthToGoRequestParamFromJson = JsonUtilities.getGson()
				.fromJson(getOneMonthToGoRequestParamsAsJson(),
						RequestParams.class);
		GregorianCalendar gregorianCalendarForTest = new DateTime(
				oneMonthToGoRequestParamFromJson
						.getDepartureDate()).toGregorianCalendar();
		oneMonthToGoMonth = gregorianCalendarForTest
				.get(GregorianCalendar.MONTH);
		oneMonthToGoDayOfMonth = gregorianCalendarForTest
				.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	public static String getOneMonthToGoRequestParamsAsJson() {
		StringBuffer buf = new StringBuffer("{\"stops\":");
		buf.append(Constants.DEFAULT_MAX_STOPS + ",\"week\":0,\"departureAirportIata\":[\"");
		buf.append(marseilleProvenceIata).append(
				"\"],\"arrivalAirportIata\":[\"");
		buf.append(londonAllIata).append(
				"\"],\"departureDate\":\"");
		buf.append(jsonFormatedDepartDate).append("\",\"returnDate\":\"");
		buf.append(formatedReturnDate).append("\",\"departureTime\":\"");
		buf.append(Constants.DEFAULT_TIME).append("\",\"returnTime\":\"");
		buf.append(Constants.DEFAULT_TIME)
				.append("\",\"travelType\":\"RETURN\",\"adults\":1,\"children\":0,\"infants\":0,\"withReportUnit\":false}");
		System.out.println("getOneMonthToGoRequestParamsAsJson() "+buf.toString());
		return buf.toString();
	}

}