package com.prochainvol.api.provider.odigeo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.odigeo.metasearch.metasearch.ws.v2.CabinClass;
import com.odigeo.metasearch.metasearch.ws.v2.ItineraryLocationRequest;
import com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearchRequest;
import com.odigeo.metasearch.metasearch.ws.v2.ItinerarySegmentRequest;
import com.odigeo.metasearch.metasearch.ws.v2.PreferencesRequest;
import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchRequest;
import com.prochainvol.ProchainvolException;
import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.api.TravelType;
import com.prochainvol.api.provider.RequestBuilderInterface;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.response.Route;
import com.prochainvol.httpServlet.TravelDates;
import com.prochainvol.json.JsonUtilities;

public class OdigeoRequestBuilder implements RequestBuilderInterface<Search> {

	private static final Logger logger = Logger
			.getLogger(OdigeoRequestBuilder.class.getName());

	public static final String metasearchEngineCode = "5b21cce3fe877c13cdb60c25b4885";
	public static final String locale = "fr_FR";
	// (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)
	// Chrome/27.0.1453.116 Safari/537.36
	public static final String userAgent = "Mozilla/5.0";
	public static final String domainCode = ".fr";
	public static final int maxSize = 5;

	public static final CabinClass cabinClassTourist = CabinClass.TOURIST;

	@Override
	public Search buildRequest() throws ProchainvolException {
		Search search = createSearchObject();
		ItinerarySearchRequest itinerarySearchRequest = search
				.getSearchRequest().getItinerarySearchRequest();

		itinerarySearchRequest.setDirectFlightsOnly(false);
		itinerarySearchRequest.setNumAdults(1);
		itinerarySearchRequest.setNumChildren(0);
		itinerarySearchRequest.setNumInfants(0);

		ItinerarySegmentRequest itinerarySegmentRequest = new ItinerarySegmentRequest();
		itinerarySearchRequest.getSegmentRequests()
				.add(itinerarySegmentRequest);

		ItineraryLocationRequest departure = new ItineraryLocationRequest();
		ItineraryLocationRequest destination = new ItineraryLocationRequest();
		departure.setIataCode(ONE_MONTH_TOGO_TEST_VALUES
				.getDepartureAirportIata()[0]);
		destination.setIataCode(ONE_MONTH_TOGO_TEST_VALUES
				.getArrivalAirportIata()[0]);

		XMLGregorianCalendar gregorianCalendar;
		try {
			gregorianCalendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(
							new DateTime(ONE_MONTH_TOGO_TEST_VALUES
									.getDepartureDate()).toGregorianCalendar());
		} catch (DatatypeConfigurationException e) {
			throw new ProchainvolException(e);
		}
		itinerarySegmentRequest.setDate(gregorianCalendar);
		itinerarySegmentRequest.setDeparture(departure);
		itinerarySegmentRequest.setDestination(destination);

		return search;
	}

	@Override
	public Map<String, Search> buildRequest(RequestParams params)
			throws ProchainvolException {
		Search search = createSearchObject();
		ItinerarySearchRequest itinerarySearchRequest = search
				.getSearchRequest().getItinerarySearchRequest();
		itinerarySearchRequest.setDirectFlightsOnly(params.getStops() == 0);
		itinerarySearchRequest.setNumAdults(params.getAdults());
		itinerarySearchRequest.setNumChildren(params.getChildren());
		itinerarySearchRequest.setNumInfants(params.getInfants());
		int nbStops = params.getStops();
		itinerarySearchRequest.setDirectFlightsOnly(nbStops==0);

		/*
		 * List<ItinerarySegmentRequest> segmentRequests. List of segments to be
		 * searched. The possible lengths of this list are: - One. For a one-way
		 * itinerary. - Two. If second segment destination and arrival places
		 * match destination and arrival for the first one respectively, it is
		 * what is commonly known as a round-trip itinerary. If locations are
		 * different, then it can be a completely independent segment, and in
		 * this case, it is named multi-segment itinerary. Three. For
		 * multi-segment itineraries. Segments must be in correct date order.
		 */
		return setSegments(params, search);
	}

	public Map<String, Search> setSegments(RequestParams params, Search search)
			throws ProchainvolException {
		int dmax = params.getDepartureAirportIata().length;
		int amax = params.getArrivalAirportIata().length;
		Map<String, Search> result = new HashMap<String, Search>();
		
			for (Route route : params.getRouteAsList()) {
				Search newSearch = JsonUtilities.getGson().fromJson(JsonUtilities.getGson().toJson(search), Search.class);
				result.put(route.getKey(), newSearch);
				setSegments(route, params, newSearch);
		}
		return result;
	}


	public void setSegments(Route route, RequestParams params, Search search)
			throws ProchainvolException {

		List<ItinerarySegmentRequest> segmentRequests = search
				.getSearchRequest().getItinerarySearchRequest()
				.getSegmentRequests();

		TravelType travelType = params.getTravelType();
		switch (travelType) {
		case ONE_WAY:
			String departureIata = route.getDepartureAirportIata();
			String destinationIata = route.getArrivalAirportIata();
			Date departureDate = params.getDepartureDate();

			segmentRequests.add(getItinerarySegment(departureIata,
					destinationIata, departureDate));
			break;
		case RETURN:
			// aller
			departureIata = route.getDepartureAirportIata();
			destinationIata = route.getArrivalAirportIata();
			departureDate = params.getDepartureDate();

			segmentRequests.add(getItinerarySegment(departureIata,
					destinationIata, departureDate));

			// retour
			destinationIata = route.getDepartureAirportIata();
			departureDate = params.getReturnDate();

			segmentRequests.add(getItinerarySegment(
					route.getArrivalAirportIata(),
					route.getDepartureAirportIata(), params.getReturnDate()));

			break;
		default:
			// get parameters week and returnDate
			TravelDates travelDates = TravelDates.getTravelDates(travelType, 0);
			// aller
			departureIata = route.getDepartureAirportIata();
			destinationIata = route.getArrivalAirportIata();

			Date departDate = travelDates.getDepartureDate().getDate();
			segmentRequests.add(getItinerarySegment(departureIata,
					destinationIata, departDate));

			// retour
			destinationIata = route.getDepartureAirportIata();
			departureDate = params.getReturnDate();

			Date returnDate = travelDates.getReturnDate().getDate();
			segmentRequests.add(getItinerarySegment(
					route.getArrivalAirportIata(),
					route.getDepartureAirportIata(), returnDate));

			break;
		}
	}

	private Search createSearchObject() {
		Search search = new Search();
		search.setMetasearchEngineCode(metasearchEngineCode);

		PreferencesRequest preferences = new PreferencesRequest();
		search.setPreferences(preferences);
		preferences.setLocale(locale);
		preferences.setDomainCode(domainCode);
		preferences.setRealUserIP(ProchainvolUtilities.getMyIPAddress());
		preferences.setUserAgent(userAgent);

		SearchRequest searchRequest = new SearchRequest();
		search.setSearchRequest(searchRequest);
		ItinerarySearchRequest itinerarySearchRequest = new ItinerarySearchRequest();
		// TODO attention chez odigeo tout le monde voyage en touriste ...
		itinerarySearchRequest.setCabinClass(cabinClassTourist);
		itinerarySearchRequest.setDirectFlightsOnly(false);
		searchRequest.setItinerarySearchRequest(itinerarySearchRequest);
		searchRequest.setMaxSize(maxSize);
		return search;
	}

	private ItinerarySegmentRequest getItinerarySegment(String departureIata,
			String destinationIata, Date departureDate)
			throws ProchainvolException {
		ItinerarySegmentRequest itinerarySegmentRequest = new ItinerarySegmentRequest();

		ItineraryLocationRequest departure = new ItineraryLocationRequest();
		ItineraryLocationRequest destination = new ItineraryLocationRequest();
		departure.setIataCode(departureIata);
		destination.setIataCode(destinationIata);
		itinerarySegmentRequest.setDeparture(departure);
		itinerarySegmentRequest.setDestination(destination);

		XMLGregorianCalendar gregorianCalendar;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(departureDate);
			gregorianCalendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendarDate(cal.get(Calendar.YEAR),
							cal.get(Calendar.MONTH) + 1,
							cal.get(Calendar.DAY_OF_MONTH),
							DatatypeConstants.FIELD_UNDEFINED);

		} catch (DatatypeConfigurationException e) {
			throw new ProchainvolException(e);
		}
		itinerarySegmentRequest.setDate(gregorianCalendar);
		logger.trace(JsonUtilities.getGsonPretty().toJson(
				itinerarySegmentRequest));
		return itinerarySegmentRequest;
	}

}
