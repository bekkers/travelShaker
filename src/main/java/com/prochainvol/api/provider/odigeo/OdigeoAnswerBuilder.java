package com.prochainvol.api.provider.odigeo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.odigeo.metasearch.metasearch.ws.v2.Carrier;
import com.odigeo.metasearch.metasearch.ws.v2.CollectionEstimatedFees;
import com.odigeo.metasearch.metasearch.ws.v2.FareItinerary;
import com.odigeo.metasearch.metasearch.ws.v2.FareItineraryPrice;
import com.odigeo.metasearch.metasearch.ws.v2.ItinerariesLegend;
import com.odigeo.metasearch.metasearch.ws.v2.Location;
import com.odigeo.metasearch.metasearch.ws.v2.MessageResponse;
import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchResultsPage;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.odigeo.metasearch.metasearch.ws.v2.Section;
import com.odigeo.metasearch.metasearch.ws.v2.SectionResult;
import com.odigeo.metasearch.metasearch.ws.v2.Segment;
import com.odigeo.metasearch.metasearch.ws.v2.SegmentResult;
import com.prochainvol.Constants;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.api.TravelType;
import com.prochainvol.api.provider.AbstractAnswerBuilder;
import com.prochainvol.api.provider.PROVIDER;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.response.FlightRecommendation;
import com.prochainvol.api.response.IFlight;
import com.prochainvol.api.response.OneWayFlight;
import com.prochainvol.api.response.Path;
import com.prochainvol.api.response.ReportUnit;
import com.prochainvol.api.response.RequestResultUnit;
import com.prochainvol.api.response.ReturnFlight;
import com.prochainvol.api.response.Route;
import com.prochainvol.json.JsonUtilities;
import com.prochainvol.sql.airlines.AirlineCompany;

public class OdigeoAnswerBuilder extends
		AbstractAnswerBuilder<Search, SearchStatusResponse> {

	// TODO for asynchronous call see bindings file
	// http://www.coderanch.com/t/472048/Web-Services/java/Synchronous-Asynchronous

	private static final Logger logger = Logger
			.getLogger(OdigeoAnswerBuilder.class.getName());

	public final static Class<SearchStatusResponse> responseClass = SearchStatusResponse.class;
	public final static Class<Search> requestClass = Search.class;

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			PROVIDER.ODIGEO.getProperties().getProperty("dateFormat",
					"EEE MMM d HH:mm:ss z yyyy"), Locale.US);

	private static String regExpr = "^.*utm_campaign=([a-zA-Z0-9]{3}\\-[a-zA-Z0-9]{3}).*$";
	private Map<Integer, Location> locationMap = new HashMap<Integer, Location>();
	private Map<String, AirlineCompany> airlineCompanyMap = new HashMap<String, AirlineCompany>();
	private Map<Integer, Segment> segmentMap = new HashMap<Integer, Segment>();

	private Map<Integer, Section> sectionMap = new HashMap<Integer, Section>();

	private Gson gson = JsonUtilities.getGsonPretty();

	public OdigeoAnswerBuilder(ProchainvolConfig config)
			throws ProchainvolException {
		super(config, PROVIDER.ODIGEO);
	}

	@Override
	public List<RequestResultUnit> buildProchainvolAnswer(
			List<SearchStatusResponse> airSearchResponses,
			RequestParams requestParams, Date startDate)
			throws ProchainvolException {
		
		List<RequestResultUnit> results = new ArrayList<RequestResultUnit>();
		for (SearchStatusResponse reponse :airSearchResponses) {
			long startTime = System.nanoTime();
			RequestResultUnit rapportRequete = buildProchainvolAnswer(
					reponse, requestParams, new Date());
			logger.trace("rapportRequete = "+rapportRequete);
			Gson gson = JsonUtilities.getGsonPretty();
			long mediumTime = System.nanoTime();
			List<FlightRecommendation> flightRecommendationMap = new ArrayList<FlightRecommendation>();
			RequestResultUnit searchStatusResponse = filterRecommendations(
					requestParams,
					rapportRequete.getReportUnit(),
					flightRecommendationMap);
			long endTime = System.nanoTime();
			ReportUnit requestReportUnit = searchStatusResponse.getReportUnit();
			requestReportUnit.setDuréeHttp((mediumTime - startTime) / 1000000); // durées
																				// en
																				// ms
			requestReportUnit.setDuréeAnalyse((endTime - mediumTime) / 1000000);
			results.add(searchStatusResponse);
			System.out.println("------------------------>\n"
					+ gson.toJson(searchStatusResponse));
		}
		return results;
	}

	@Override
	public RequestResultUnit buildProchainvolAnswer(
			SearchStatusResponse response, RequestParams requestParams,
			Date startDate) throws ProchainvolException {

		List<FlightRecommendation> flightRecommendationMap = new ArrayList<FlightRecommendation>();
		logger.trace("SearchStatusResponse = " + gson.toJson(response));

		// Preferences for the current response, including language and currency
		// as mentioned above.
		// Preferences preferences = response.getPreferences();

		// Summary of the locations, collection methods, airlines, sections and
		// segments involved in this set of itineraries.
		ItinerariesLegend itinerariesLegend = response.getLegend();

		if (itinerariesLegend != null) {

			// Carriers involved in the set of itineraries described by the
			// searchResultsPages
			List<Carrier> carriers = itinerariesLegend.getCarriers();
			logger.trace("carriers.length = " + carriers.size());
			for (Carrier carrier : carriers) {
				carrier.getCode();
				// TODO chantier
				AirlineCompany airlineCompany = new AirlineCompany();
				airlineCompany.setName(carrier.getName());
				String iata = carrier.getCode();
				airlineCompany.setIata(iata);
				List<AirlineCompany> carrierList = ProchainvolConfig.getAirlinecompanies().getAll().get(iata);
				if (carrierList == null) {
					logger.error("Unknown carrier : " + airlineCompany);
				} else if (carrierList.size()>1) {
					logger.error("More than one carrier known for iata " + iata);
				} else {

					airlineCompany = carrierList.get(0);
					// System.out.println("airlineCompany : " + airlineCompany);
				}
				airlineCompanyMap.put(iata, airlineCompany);
			}

			List<Location> places = itinerariesLegend.getLocations();
			for (Location location : places) {
				locationMap.put(location.getGeoNodeId(), location);
			}
			logger.trace("locationMap.length = " + locationMap.size());

			// Each CollectionEstimatedFees contains an identifier and a list
			// represents the estimated fee for each collection method.
			List<CollectionEstimatedFees> collectionFees = itinerariesLegend
					.getCollectionEstimatedFees();
			logger.trace("collectionFees.length = " + collectionFees.size());

			// Each SectionResult object contains an identifier and a Section.
			// Section identifiers in Segments can be resolved to Sections
			// by looking up the id property in this list.
			List<SectionResult> sectionResults = itinerariesLegend
					.getSectionResults();
			for (SectionResult sectionResult : sectionResults) {
				Section section = sectionResult.getSection();
				sectionMap.put(sectionResult.getId(), section);
			}
			logger.trace("sectionMap.length = " + sectionMap.size());

			// Each SegmentResult object contains an identifier and a Segment.
			// Segment identifiers in FareItinerary objects can be resolved to
			// Segments
			// by matching the identifier with a SegmentResult found in this
			// list.
			List<SegmentResult> segmentResults = itinerariesLegend
					.getSegmentResults();
			for (SegmentResult segmentResult : segmentResults) {
				int id = segmentResult.getId();
				Segment segment = segmentResult.getSegment();
				segmentMap.put(id, segment);
			}
			logger.trace("segmentMap.length = " + segmentMap.size());

			List<SearchResultsPage> itineraryResultsPages = response
					.getItineraryResultsPages();
			logger.trace("itineraryResultsPages.length = "
					+ itineraryResultsPages.size());
			// a sorted list of itineraries, as a result of a search operation
			for (SearchResultsPage searchResultsPage : itineraryResultsPages) {

				// The brand of the search, like ‘ED for eDream, ‘OP’ for Opodo,
				// GO
				// for GoVoyage and ‘TL’ for TravelLink.
				String brand = searchResultsPage.getBrand();

				// List of error and warning messages thrown during the search
				// for
				// the specific website.
				List<MessageResponse> localMessageReponse = searchResultsPage
						.getMessages();

				List<String> messages = new ArrayList<String>();
				String messageForBrand = null;
				for (MessageResponse MessageResponse : localMessageReponse) {
					messages.add(String.format("%s : %s",
							MessageResponse.getCode(),
							MessageResponse.getDescription()));
				}
				if (messages.size() > 0) {
					messageForBrand = Arrays.toString(messages.toArray());
					logger.trace(String.format("%s : %s", brand,
							messageForBrand));
				}

				// URL to the brand site (eDreams, Opodo, Govoyage or
				// TravelLink)
				// that repeats the search whose results are shown.
				// Parameters fareItineraryKey and segmentKeys can be added to
				// this
				// base URL
				String clickOutUrl = searchResultsPage.getClickoutURL();

				// Itineraries that fulfills the search requirements indicated
				// by
				// the user.
				List<FareItinerary> fareItineraries = searchResultsPage
						.getItineraryResults();
				logger.trace("fareItineraries.length = "
						+ fareItineraries.size());

				// a FareItinerary object represents a combination of flights/
				// train
				// routes
				for (FareItinerary fareItinerary : fareItineraries) {

					// Additional itinerary parameters to concatenate to base
					// URL
					String fareClickOutUrl = fareItinerary
							.getClickoutURLParams();

					// Additional itinerary parameters
					// To add the additional parameters, the client has to
					// concatenate the clickoutURLParams to clickoutURL:
					String localClickOutUrl = clickOutUrl + fareClickOutUrl;
					// fareItineraryKey can be added to the base URL
					String fareItineraryKey = fareItinerary.getKey();
					// logger.trace("fareItineraryKey = " + fareItineraryKey);

					// Detailed price information for any of the options in this
					// itinerary
					FareItineraryPrice price = fareItinerary.getPrice();
					// Total price used to order the itinerary results
					BigDecimal totalPrice = price.getTotalPrice();

					// List of segments for the first part of the route.
					// In a one way itinerary, they will be the only present
					// segments
					List<Integer> firstSegmentsIds = fareItinerary
							.getFirstSegmentsIds();

					if (requestParams.getTravelType() == TravelType.ONE_WAY) {
						for (Integer segmentId : firstSegmentsIds) {
							Segment segment = segmentMap.get(segmentId);

							FlightRecommendation flightRecommendation = createFlightRecommendation(
									brand, localClickOutUrl, fareItineraryKey,
									totalPrice, segment);

							flightRecommendationMap.add(flightRecommendation);
						}

					} else {
						for (Integer segmentId : firstSegmentsIds) {
							Segment segmentAller = segmentMap.get(segmentId);

							// List of segments for the second part of the
							// route.
							List<Integer> secondSegmentsIds = fareItinerary
									.getSecondSegmentsIds();
							for (Integer segmentRetourId : secondSegmentsIds) {
								// brasser les allers avec les retours
								Segment segmentRetour = segmentMap
										.get(segmentRetourId);

								FlightRecommendation flightRecommendation = createFlightRecommendation(
										brand, localClickOutUrl,
										fareItineraryKey, totalPrice,
										segmentAller, segmentRetour);

								flightRecommendationMap
										.add(flightRecommendation);
							}
						}

					}

					// List of segments for the third part of the route.
					List<Integer> thirdSegmentsIds = fareItinerary
							.getThirdSegmentsIds();

				}

			}
			logger.trace("flightRecommendationMap.length = "
					+ flightRecommendationMap.size());
		} else {
			List<MessageResponse> messageResponse = response.getMessages();
			if (messageResponse == null || messageResponse.size() == 0) {
				return new RequestResultUnit();
			} else {
				String messages = messageResponse.stream()
						.map(p -> p.getCode() + ":" + p.getDescription())
						.collect(Collectors.joining(", "));
				String mess = String.format(
						"Liste des erreurs retournées par l'API Odigeo : %s",
						messages);
				logger.error(mess);
				throw new ProchainvolException(mess);
			}
		}

		ReportUnit rapportRequete = new ReportUnit(provider,
				provider.getUrl(), startDate, requestParams);
		return filterRecommendations(requestParams, rapportRequete,
				flightRecommendationMap);
	}

	// public static void main(String[] argv) {
	// String s =
	// "arrivalLocation=RAK&utm_campaign=BOD-RAK&utm_content=metasearch-10&";
	// System.out.println(s.replaceFirst(regExpr, "$1"));
	// }

	public String[] comment(String label, String value) {
		String[] line = new String[2];
		line[0] = label;
		line[1] = value;
		return line;
	}

	public FlightRecommendation createFlightRecommendation(String brand,
			String localClickOutUrl, String fareItineraryKey,
			BigDecimal totalPrice, Segment segment) throws ProchainvolException {

		// Select itinerary
		// To select an itinerary the client has to add additional
		// parameters to the URL
		String additionalSegmentParameters = String.format(
				"&fareItineraryKey=%s&segmentKey0=%s", fareItineraryKey,
				segment.getKey());
		String url = localClickOutUrl + additionalSegmentParameters;

		long goingDuration = segment.getDuration();

		List<Path> goingPath = createPath(segment);
		IFlight flight = new OneWayFlight(goingPath, (int) goingDuration);
		String deepLink = makeDeepLink(url, ODIGEO_PROVIDER.valueOf(brand));
		FlightRecommendation flightRecommendation = new FlightRecommendation(
				PROVIDER.ODIGEO, flight, totalPrice.floatValue(), deepLink);

		flightRecommendation.setSubSource(brand);

		// logger.trace("flightRecommendation = " + flightRecommendation);
		return flightRecommendation;
	}

	public FlightRecommendation createFlightRecommendation(String brand,
			String localClickOutUrl, String fareItineraryKey,
			BigDecimal totalPrice, Segment aller, Segment retour)
			throws ProchainvolException {

		String additionalSegmentParameters = String.format(
				"&fareItineraryKey=%s&segmentKey0=%s&segmentKey1=%s",
				fareItineraryKey, aller.getKey(), retour.getKey());
		String url = localClickOutUrl + additionalSegmentParameters;
		List<Path> goingPath = createPath(aller);
		long goingDuration = aller.getDuration();

		// Select the two itineraries (aller + retour)
		// To select an itinerary the client has to add additional
		// parameters to the URL
		List<Path> returnPath = createPath(retour);
		long returnDuration = retour.getDuration();

		IFlight flight = new ReturnFlight(goingPath, (int) goingDuration,
				returnPath, (int) returnDuration);
		String deepLink = makeDeepLink(url, ODIGEO_PROVIDER.valueOf(brand));
		FlightRecommendation flightRecommendation = new FlightRecommendation(
				PROVIDER.ODIGEO, flight, totalPrice.floatValue(), deepLink);
		flightRecommendation.setSubSource(brand);

		// logger.trace("flightRecommendation = " + flightRecommendation);
		return flightRecommendation;
	}

	public Path createPath(Section section) throws ProchainvolException {
		short flightNumber = Short.parseShort(section.getId().trim());
		Location departureLocation = locationMap
				.get(section.getFromGeoNodeId());
		String departureAirport = departureLocation.getIataCode();
		Location arrivalLocation = locationMap.get(section.getToGeoNodeId());
		String arrivalAirport = arrivalLocation.getIataCode();
		Route route = new Route(departureAirport, arrivalAirport);
		Date outboundDate = ProchainvolUtilities.toDate(section
				.getDepartureDate());
		Date inboundDate = ProchainvolUtilities
				.toDate(section.getArrivalDate());
		AirlineCompany carrier = airlineCompanyMap
				.get(section.getCarrierCode());
		String operatingCarrierName = carrier.getName();
		String operatingCarrierIataCode = carrier.getIata();
		Path path = new Path(flightNumber, route, outboundDate, inboundDate,
				operatingCarrierName, operatingCarrierIataCode, config);
		return path;
	}

	public List<Path> createPath(Segment segment) throws ProchainvolException {
		List<Integer> sectionIds = segment.getSectionIds();
		List<Path> goingPath = new ArrayList<Path>();
		for (Integer id : sectionIds) {
			Section section = sectionMap.get(id);
			Path path = createPath(section);
			goingPath.add(path);
		}
		return goingPath;
	}

	public String makeDeepLink(String localClickOutUrl, ODIGEO_PROVIDER provider) {
		String route = localClickOutUrl.replaceFirst(regExpr, "$1");
		logger.trace("route = " + route);
		localClickOutUrl = localClickOutUrl.replaceFirst("utm_campaign=",
				"utm_campaign=" + Constants.ODIGEO_PARTID + "-").replaceFirst(
				"utm_content=meta\\-fr", "utm_content=metasearch-10");
		String format = provider.getFormat().replaceFirst("=XXX", "=" + route);
		logger.trace("format = " + format.replace("&", "&\n"));

		String result = format + localClickOutUrl;
		logger.trace("deeplink = " + result.replace("&", "&\n"));
		return result;
	}

}
