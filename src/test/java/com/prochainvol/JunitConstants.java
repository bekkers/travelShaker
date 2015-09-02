package com.prochainvol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Logger;

import com.prochainvol.api.TravelType;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.request.filter.Filter;
import com.prochainvol.api.request.filter.MaxStopPredicate;
import com.prochainvol.api.request.filter.RelativePricePredicate;
import com.prochainvol.api.response.FlightRecommendation;
import com.prochainvol.api.response.Path;
import com.prochainvol.api.response.RequestResultUnit;
import com.prochainvol.json.JsonUtilities;
import com.prochainvol.sql.airport.DebugAirportReader;
import com.prochainvol.sql.airport.SqlAirport;

public class JunitConstants {

	public static void checkResults(final RequestParams requestParam,
			RequestResultUnit requestResult, String expectedAirportName,
			ProchainvolConfig config) throws ProchainvolException {
		List<FlightRecommendation> recommendations = requestResult
				.getRecommendations();

		FlightRecommendation flightRecommendation = recommendations.get(0);

		List<Path> goingPath = flightRecommendation.getFlight().getGoingPath();
		assertEquals(1, goingPath.size());
		Path goingFlight = goingPath.get(0);
		assertEquals(TestConstants.marseilleProvenceIata, goingFlight.getRoute()
				.getDepartureAirportIata());

		// System.out.println(requestParam.getDepartureDate() + " : " +
		// goingFlight.getOutboundDate());
		// System.out.println(requestParam);

		assertEquals(Constants.DATE_PICKER_DATE_FORMAT.format(requestParam
				.getDepartureDate()),
				Constants.DATE_PICKER_DATE_FORMAT.format(goingFlight
						.getDateAtterrisage()));

		List<Path> returnPath = flightRecommendation.getFlight()
				.getReturnPath();
		assertEquals(1, returnPath.size());
		Path returnFlight = returnPath.get(0);
		SqlAirport airportFrom = config.getTravelplace(returnFlight.getRoute()
				.getDepartureAirportIata());
		assertEquals(expectedAirportName, airportFrom.getName());

		// System.out.println(requestParam.getReturnDate() + " : " +
		// returnFlight.getOutboundDate());

		assertEquals(Constants.DATE_PICKER_DATE_FORMAT.format(requestParam
				.getReturnDate()),
				Constants.DATE_PICKER_DATE_FORMAT.format(returnFlight
						.getDateDecolage()));

	}
	
	public static Filter createFilter(int maxStop) {
		Filter filter = new Filter();
		filter.addPredicate(new MaxStopPredicate(maxStop));
		return filter;
	}
	public static Filter createFilter(int maxStop, Float priceRatio) {
		Filter filter = new Filter();
		filter.addPredicate(new MaxStopPredicate(maxStop));
		filter.addPredicate(new RelativePricePredicate(priceRatio));
		return filter;
	}
	public static void reportError(Throwable e) {
		e.printStackTrace();
		fail(String.format("Exception = %s, message = %s", e.getClass()
				.getName(), e.getMessage()));
	}
	protected static final Logger logger = Logger
			.getLogger(JunitConstants.class.getName());
	public static final String datePickerFormatedDate_25_1_2015 = "01/25/2015";

	public static final String aDatePickerAMTime = "07:30 AM";
	public static final String aDatePickerPMTime = "02:15 PM";
	public static final RequestParams testRequestParam;
	public static final RequestParams oneMonthToGoRequestParamFromConstructor;

	public static final RequestParams oneMonthToGoRequestParamFromJsonOneWay;


	static {
		try {
			testRequestParam = new RequestParams("MRS", "LON",
					JsonUtilities.getGSON_DATE_FORMAT()
							.parse("15 Feb 2015 04:42:58 CET"),
					JsonUtilities.getGSON_DATE_FORMAT()
							.parse("28 Feb 2015 04:42:58 CET"), 0,
					TravelType.RETURN, 1, 0, 0);

			oneMonthToGoRequestParamFromConstructor = new RequestParams(
					TestConstants.marseilleProvenceIata, TestConstants.londonAllIata,
					TestConstants.oneMonthToGoDepartureDate, TestConstants.returnDate, Constants.DEFAULT_MAX_STOPS);
			oneMonthToGoRequestParamFromConstructor
					.setDepartureTime(aDatePickerAMTime);
			oneMonthToGoRequestParamFromConstructor
					.setReturnTime(aDatePickerPMTime);
			String json = TestConstants.getOneMonthToGoRequestParamsAsJson();
			oneMonthToGoRequestParamFromJsonOneWay = JsonUtilities.getGson()
					.fromJson(json, RequestParams.class);
			oneMonthToGoRequestParamFromJsonOneWay
					.setTravelType(TravelType.ONE_WAY);

			
		} catch (Exception e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new Error(e);
		}
	}
	
	public static RequestParams getDebugRequestParams() throws ProchainvolException {
		// utilisé en mode debug pour des appels à la main
		String fileName = Constants.PROCHAINVOL_PROPS.getProperty("debugRequestParamsFilename");
		return JsonUtilities.readFromInputStream(RequestParams.class, fileName );
	}


	private JunitConstants() {
	}

}
