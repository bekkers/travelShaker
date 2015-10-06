package com.prochainvol.api.provider.odigeo;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearchRequest;
import com.odigeo.metasearch.metasearch.ws.v2.ItinerarySegmentRequest;
import com.odigeo.metasearch.metasearch.ws.v2.PreferencesRequest;
import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchRequest;
import com.prochainvol.JunitConstants;
import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.TestConstants;
import com.prochainvol.api.provider.RequestBuilderInterface;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.response.Route;
import com.prochainvol.json.JsonUtilities;

public class OdigeoRequestBuilderTest {

	private static RequestParams ONE_MONTH_TOGO_TEST_VALUES;
	final Gson gson = JsonUtilities.getGsonPretty();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ONE_MONTH_TOGO_TEST_VALUES = TestConstants.oneMonthToGoRequestParamFromJson;
		GregorianCalendar gregorianCalendarForTest = new DateTime(
				ONE_MONTH_TOGO_TEST_VALUES.getDepartureDate())
				.toGregorianCalendar();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildRequestRequestParams() {
		try {
			RequestBuilderInterface<Search> odigeoRequestBuilder = new OdigeoRequestBuilder();
			RequestParams params = TestConstants.oneMonthToGoRequestParamFromJson;
			Route route = params.getRouteAsList().get(0);
			Map<String, Search> searchList = odigeoRequestBuilder.buildRequest(params);
			Search search = searchList.get(route.getKey());
			String json = gson.toJson(search);
//			System.out.println(json);
			Search search1 = gson.fromJson(json,
					Search.class);
			assertEquals(search.getSearchRequest().getItinerarySearchRequest()
					.getSegmentRequests().get(0).getDate(), search1
					.getSearchRequest().getItinerarySearchRequest()
					.getSegmentRequests().get(0).getDate());
			checkPreferences(search);

		} catch (Exception e) {
			JunitConstants.reportError(e);
		}
	}

	@Test
	public void testBuildRequest() {
		try {
			OdigeoRequestBuilder odigeoRequestBuilder = new OdigeoRequestBuilder();
			Search search = odigeoRequestBuilder.buildRequest();
			checkPreferences(search);

		} catch (Exception e) {
			JunitConstants.reportError(e);
		}
	}
	
	@Test
	public void testSerializeDeserializeSearch() {
		try {
			OdigeoRequestBuilder odigeoRequestBuilder = new OdigeoRequestBuilder();
			Search search = odigeoRequestBuilder.buildRequest();
			checkPreferences(search);
			String json = gson.toJson(search);
			Search search1 = gson.fromJson(json, Search.class);
			String json1 = gson.toJson(search1);
			System.out.println(json);
			assertEquals(json, json1);
			XMLGregorianCalendar date = search.getSearchRequest().getItinerarySearchRequest().getSegmentRequests().get(0).getDate();
			XMLGregorianCalendar date1 = search1.getSearchRequest().getItinerarySearchRequest().getSegmentRequests().get(0).getDate();
			System.out.println(date);
			System.out.println(date1);
			assertEquals(date, date1);

		} catch (Exception e) {
			JunitConstants.reportError(e);
		}
	}

	@Test
	public void testThomas1() {
		try {
			RequestParams requestParams = JsonUtilities.readFromInputStream(RequestParams.class, "/json/thomas1.json");
			OdigeoRequestBuilder odigeoRequestBuilder = new OdigeoRequestBuilder();
			Map<String, Search> search = odigeoRequestBuilder.buildRequest(requestParams);
			String builtString = JsonUtilities.getGson().toJson(search);
			
			String savedString = JsonUtilities.inputStreamToString("/json/thomas1Search.json");
			assertEquals(savedString.replaceAll("46.31.194.10", ProchainvolUtilities.getMyIPAddress()), builtString);

		} catch (Exception e) {
			JunitConstants.reportError(e);
		}
	}

	public void checkPreferences(Search search) {
		PreferencesRequest preferences = search.getPreferences();
		assertEquals(OdigeoRequestBuilder.domainCode,
				preferences.getDomainCode());
		assertEquals(OdigeoRequestBuilder.locale, preferences.getLocale());
		assertEquals(ProchainvolUtilities.getMyIPAddress(),
				preferences.getRealUserIP());
		assertEquals(OdigeoRequestBuilder.userAgent, preferences.getUserAgent());

		assertEquals(OdigeoRequestBuilder.metasearchEngineCode,
				search.getMetasearchEngineCode());

		SearchRequest searchRequest = search.getSearchRequest();
		assertEquals(OdigeoRequestBuilder.maxSize, searchRequest.getMaxSize());

		ItinerarySearchRequest itinerarySearchRequest = searchRequest
				.getItinerarySearchRequest();
		assertEquals(OdigeoRequestBuilder.cabinClassTourist,
				itinerarySearchRequest.getCabinClass());

		ItinerarySegmentRequest itinerarySegmentRequest = itinerarySearchRequest
				.getSegmentRequests().get(0);
		assertEquals(ONE_MONTH_TOGO_TEST_VALUES.getDepartureAirportIata()[0],
				itinerarySegmentRequest.getDeparture().getIataCode());
		assertEquals(ONE_MONTH_TOGO_TEST_VALUES.getArrivalAirportIata()[0],
				itinerarySegmentRequest.getDestination().getIataCode());
		assertEquals(TestConstants.oneMonthToGoMonth + 1, itinerarySegmentRequest.getDate()
				.getMonth());
		assertEquals(TestConstants.oneMonthToGoDayOfMonth, itinerarySegmentRequest.getDate()
				.getDay());
	}

}
