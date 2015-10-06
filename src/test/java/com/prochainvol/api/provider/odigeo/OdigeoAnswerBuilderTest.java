package com.prochainvol.api.provider.odigeo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.Constants;
import com.prochainvol.JunitConstants;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.api.EXECUTOR_TYPE;
import com.prochainvol.api.provider.AbstractAnswerBuilder;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.response.FlightRecommendation;
import com.prochainvol.api.response.IFlight;
import com.prochainvol.api.response.OneWayFlight;
import com.prochainvol.api.response.Path;
import com.prochainvol.api.response.ReportUnit;
import com.prochainvol.api.response.RequestResult;
import com.prochainvol.api.response.RequestResultUnit;
import com.prochainvol.api.response.Route;
import com.prochainvol.json.JsonUtilities;

public class OdigeoAnswerBuilderTest {
	
	private static List<String> londonAirPorts = Arrays.asList( "LCY", "STN", "LGW");
	private static List<String> parisAirPorts = Arrays.asList( "ORY", "BVA");


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

//	@Test
//	public void testBuildProchainvolAnswer() {
//		try {
//			AbstractAnswerBuilder<Search, SearchStatusResponse> builder = new OdigeoAnswerBuilder(
//					new ProchainvolConfig());
//			// TODO testBuildProchainvolAnswer avec new ProchainvolConfig()
//			} catch (Exception e) {
//			JunitConstants.reportError(e);
//		}
//
//	}


	@Test
	public void testRequest_SQL_DebugOdigeo_Paris_DUB_AllerSimple() {
		try {
			ProchainvolConfig debugConfig = new ProchainvolConfig(EXECUTOR_TYPE.DEBUG);
//			debugConfig.setCurrentFlightFilters(JunitConstants.createFilter(6));

			OdigeoService odigeoService = new OdigeoService(debugConfig);			
			RequestResult requestResult = odigeoService.service(Constants.getDebugRequestParams());
			List<RequestResultUnit> units = requestResult.getRequestResultUnits();
			System.out.println("nb units = "+units.size());
			assertTrue(units.size()>0);
			RequestResultUnit requestResultUnit = units.get(0);
			System.out.println(requestResultUnit.toShortString());

			List<FlightRecommendation> recommendations = requestResultUnit
					.getRecommendations();
			assertTrue(recommendations.size()>0);
//			System.out.println("************\n"+requestResult);
			FlightRecommendation rec0 = recommendations.get(0);
			String url0 = rec0.getUrl();
			System.out.println("url0 = "+url0);
			assertTrue(url0.contains("utm_campaign=38481-"));
			assertEquals(15, recommendations.size());
			ReportUnit rapport = requestResultUnit.getReportUnit();
			assertEquals(15, rapport.getNbRecommendationsRecues());

			checkRecommendations_Paris_DUB_AllerSimple(requestResultUnit, debugConfig);

		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

	@Test
	public void testRequest_SQL_DebugAsyncOdigeo() {
		try {
			ProchainvolConfig debugConfig = new ProchainvolConfig(EXECUTOR_TYPE.DEBUG);
			debugConfig.setCurrentFlightFilters(JunitConstants.createFilter(6));

			
			List<RequestParams> params = new ArrayList<RequestParams>();
			List<RequestResultUnit> results = new ArrayList<RequestResultUnit>();

			long startTime = System.nanoTime();
			RequestParams requestParams = JsonUtilities.readFromInputStream(RequestParams.class, "/json/thomas1.json");
			params.add(requestParams);

			OdigeoService odigeoService1 = new OdigeoService(debugConfig, "/json/resultThomas1.json");			
			RequestResult requestResult = odigeoService1.service(Constants.getDebugRequestParams());
			RequestResultUnit requestResultUnit = requestResult
					.getRequestResultUnits().get(0);
			RequestResult requestResult1 = odigeoService1.service(requestParams);
			RequestResultUnit requestResultUnit1 = requestResult1
					.getRequestResultUnits().get(0);
			results.add(requestResultUnit1);
			System.out.println(requestResultUnit1);
			
			RequestParams requestParams2 = JsonUtilities.readFromInputStream(RequestParams.class, "/json/thomas2.json");
			params.add(requestParams2);
			
			OdigeoService odigeoService2 = new OdigeoService(debugConfig, "/json/resultThomas2.json");			
			RequestResult requestResult2 = odigeoService2.service(requestParams2);
			RequestResultUnit requestResultUnit2 = requestResult2.getRequestResultUnits().get(0);
			System.out.println(requestResultUnit2.toShortString());
			results.add(requestResultUnit2);
			
			
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

	public static void checkRecommendations_Paris_DUB_AllerSimple(RequestResultUnit requestResult,
			ProchainvolConfig config) throws ProchainvolException {
		// on prend come exemple de résultat le fichier "/json/odigeoResultReturn.json"
		// Il s'agit de la réponse à une demande de vol Marseille/Londres (MSR/LON°
		List<FlightRecommendation> recommendations = requestResult.getRecommendations();
		assertTrue(recommendations.size() > 0);
		RequestParams requestParam = requestResult.getReportUnit().getParams();

		for (FlightRecommendation recommendation : recommendations) {

			IFlight flight = recommendation.getFlight();

			Path path0 = flight.getGoingPath().get(0);
			Route route0 = path0.getRoute();
			assertNotNull(route0);

			List<Path> goingPath = recommendation.getFlight().getGoingPath();
			assertEquals(1, goingPath.size());
			Path goingFlight = goingPath.get(0);
			String arrivalAirportIata = goingFlight.getRoute()
					.getArrivalAirportIata();
			assertTrue(arrivalAirportIata.equals("DUB"));
			
			String departureAirportIata = goingFlight.getRoute().getDepartureAirportIata();
			assertTrue(parisAirPorts.contains(departureAirportIata));



			assertTrue(recommendation.getFlight() instanceof OneWayFlight);

		}
	}

	@Test
	public void testThomas1() {
		try {
			SearchStatusResponse searchStatusResponse = JsonUtilities.readFromInputStream(SearchStatusResponse.class, "/json/thomas1response.json");
			RequestParams requestParams = JsonUtilities.readFromInputStream(RequestParams.class, "/json/thomas1.json");
			ProchainvolConfig debugConfig = new ProchainvolConfig(EXECUTOR_TYPE.DEBUG);
			debugConfig.setCurrentFlightFilters(JunitConstants.createFilter(6));

			OdigeoService odigeoService = new OdigeoService(debugConfig);
			RequestResultUnit requestResultUnit = odigeoService.testService(requestParams, searchStatusResponse);
			System.out.println(requestResultUnit);

		} catch (Exception e) {
			JunitConstants.reportError(e);
		}
	}


}
