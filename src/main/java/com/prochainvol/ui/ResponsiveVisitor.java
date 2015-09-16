package com.prochainvol.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import com.prochainvol.Constants;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.api.TravelType;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.request.filter.Filter;
import com.prochainvol.api.request.filter.FlightRecommendationPredicate;
import com.prochainvol.api.request.filter.MaxStopOnMovePredicate;
import com.prochainvol.api.request.filter.MaxStopOnReturnPredicate;
import com.prochainvol.api.request.filter.MaxStopPredicate;
import com.prochainvol.api.request.filter.PricePredicate;
import com.prochainvol.api.request.filter.RelativePricePredicate;
import com.prochainvol.api.response.FlightRecommendation;
import com.prochainvol.api.response.IFlight;
import com.prochainvol.api.response.Path;
import com.prochainvol.api.response.ReportUnit;
import com.prochainvol.api.response.RequestResult;
import com.prochainvol.api.response.RequestResultUnit;
import com.prochainvol.sql.SqlAirport;
import com.prochainvol.sql.airport.TravelplaceReaderReport;

public class ResponsiveVisitor extends AbstractResponsiveVisitor implements IAffichableVisitor {

	private static final Logger logger = Logger.getLogger(ResponsiveVisitor.class.getName());

	public ResponsiveVisitor(ProchainvolConfig config) {
		super(config);
	}

	public String[] getIds(String[] iatas) throws ProchainvolException {
		int length = iatas.length;
		String[] depIds = new String[length];
		for (int i = 0; i < length; i++) {
			SqlAirport departureAirport = config.getAirports().getAirport(iatas[i]);
			depIds[i] = departureAirport.getFullNameWithIata();
		}
		return depIds;
	}

	@Override
	public void visit(Filter filter) {
		for (FlightRecommendationPredicate<?> pred : filter.getPredicates()) {
			switch (pred.getFlightFilter()) {
			case RELATIVE_MAX_PRICE:
				RelativePricePredicate relativePricePredicate = (RelativePricePredicate) pred;
				generateLine("Fourchette de prix avant filtre", String.format("[%.2f .. %.2f]",
						relativePricePredicate.getMinPrice(), relativePricePredicate.getMaxPrice()));
				generateLine("Rapport max/min avant filtre", String.format("%.2f",
						relativePricePredicate.getMaxPrice() / relativePricePredicate.getMinPrice()));
				generateLine("Ecart max - min avant filtre", String.format("%.2f",
						relativePricePredicate.getMaxPrice() - relativePricePredicate.getMinPrice()));
				generateLine("Fourchette de prix après filtre", String.format("[%.2f .. %.2f]",
						relativePricePredicate.getMinPrice(), relativePricePredicate.getRelativeMaxPrice()));
				generateLine("Rapport max/min après filtre", String.format("%.2f",
						relativePricePredicate.getRelativeMaxPrice() / relativePricePredicate.getMinPrice()));

				generateLine("taux", String.format("%.2f", relativePricePredicate.getValue()));
				break;
			case MAX_PRICE:
				generateLine("max", String.format("%.2f", pred.getValue()));
				break;
			case MAX_STOP:
			case MAX_STOP_ON_MOVE:
			case MAX_STOP_ON_RETURN:
				generateLine("max", String.format("%d", pred.getValue()));
			}
		}
	}

	@Override
	public void visit(FlightRecommendation pvFlightRecommendation) {
		String price = Float.toString(pvFlightRecommendation.getPrice()) + " €";
		String[] gridFormat = { "grid_3", "grid_5", "grid_4" };

		String subSource = pvFlightRecommendation.getSubSource();
		String affichableSubSource = subSource.length() == 0 ? "" : subSource + " : ";

		IFlight flight = pvFlightRecommendation.getFlight();
		List<Path> goingPaths = flight.getGoingPath();
		if (goingPaths.size() == 0) {
			generateLine("Error no going path ...");
		} else {
			if (!flight.isReturnFlight()) {
				// one way flights
				for (Path goingFlight : goingPaths) {
					String value2 = String.format("%s %s%s", goingFlight.getCarrierName(),
							goingFlight.getCarrierIataCode(), goingFlight.getFlightNumber());
					String value3 = Constants.PROCHAINVOL_SHORT_DATE_TIME_FORMAT.format(goingFlight.getDateDecolage());
					generateLine(affichableSubSource + price, value2, value3, gridFormat);
				}
			} else {
				// return flights
				for (Path goingFlight : goingPaths) {
					// afficher l'aller
					String value2 = String.format("A: %s %s%s", goingFlight.getCarrierName(),
							goingFlight.getCarrierIataCode(), goingFlight.getFlightNumber());
					String value3 = Constants.PROCHAINVOL_SHORT_DATE_TIME_FORMAT.format(goingFlight.getDateDecolage());
					generateLine(affichableSubSource + price, value2, value3, gridFormat);
				}

				// le retour
				for (Path returnFlight : flight.getReturnPath()) {

					// afficher le retour
					String value2 = String.format("R: %s %s%s", returnFlight.getCarrierName(),
							returnFlight.getCarrierIataCode(), returnFlight.getFlightNumber());
					String value3 = Constants.PROCHAINVOL_SHORT_DATE_TIME_FORMAT.format(returnFlight.getDateDecolage());
					generateLine("-", value2, value3, gridFormat);
					generateLine("", "", "", gridFormat);
				}
			}
		}
	}

	public void visit(List<RequestParams> params) throws ProchainvolException {
		for (RequestParams requestParams : params) {
			visit(requestParams);
		}

	}

	@Override
	public void visit(ProchainvolConfig prochainvolConfig) {

		generateLine("Request Readers", Arrays.toString(prochainvolConfig.getCurrentProviders()));
		generateLine("Executor type", prochainvolConfig.getExecutorType().name());

		generateLine("Nombre d'aéroports", Integer.toString(ProchainvolConfig.getAirports().getAll().size()));
		generateLine("Nombre d'airline companies", Integer.toString(prochainvolConfig.getAirlineCompanies().getAll().size()));

		Element link4 = doc.createElement("a");
		link4.setAttribute("href", "soumettre.jsp");
		link4.appendChild(doc.createTextNode("Choisir un iata"));
		generateLine(doc.createTextNode("Ouvrir la page de dialog"), link4);

		/*
		 * <form action="GetTravelPlace"><input type="hidden" name="which"
		 * value="iata"/>Iata <input type="text" name="iata"/><input
		 * type="submit" value="soumettre"/></form>
		 */

	}

	public void visit(RelativePricePredicate r) {
		generateLine("Fourchette de prix avant filtre",
				String.format("[%.2f .. %.2f]", r.getMinPrice(), r.getMaxPrice()));
		generateLine("Rapport max/min avant filtre", String.format("%.2f", r.getMaxPrice() / r.getMinPrice()));
		generateLine("Ecart max - min avant filtre", String.format("%.2f", r.getMaxPrice() - r.getMinPrice()));
		generateLine("Fourchette de prix après filtre",
				String.format("[%.2f .. %.2f]", r.getMinPrice(), r.getRelativeMaxPrice()));
		generateLine("Rapport max/min après filtre", String.format("%.2f", r.getRelativeMaxPrice() / r.getMinPrice()));
	}

	@Override
	public void visit(ReportUnit rapport) {

		generateH4Line("Response information before filters ");

		generateLine("Durée de la requête HTTP", Long.toString(rapport.getDuréeHttp()) + " ms");
		generateLine("Durée de l'analyse", Long.toString(rapport.getDuréeAnalyse()) + " ms");

		generateLine("Nb total de recommendations", Integer.toString(rapport.getNbRecommendationsRecues()));
		generateLine("Nb recommendations vol direct à l'aller", Integer.toString(rapport.getNbDirectAller()));
		generateLine("Nb recommendations vol direct au retour", Integer.toString(rapport.getNbDirectRetour()));
		generateLine("Nb recommendations vols directs aller et retour",
				Integer.toString(rapport.getNbDirectAllerRetour()));

		generateH4Line("Filter report");

	}

	@Override
	public void visit(RequestParams requestParams) throws ProchainvolException {

		generateH4Line("Your request");

		String[] iatas = requestParams.getDepartureAirportIata();
		generateLine("Depart airport", Arrays.toString(getIds(iatas)));
		generateLine("Depart Date", Constants.PROCHAINVOL_SHORT_DATE_FORMAT.format(requestParams.getDepartureDate()));
		generateLine("Depart Time", requestParams.getDepartureTime());

		iatas = requestParams.getArrivalAirportIata();
		generateLine("Arrival airport", Arrays.toString(getIds(iatas)));

		generateLine("stops", Integer.toString(requestParams.getStops()));
		generateLine("adults", Integer.toString(requestParams.getAdults()));
		generateLine("children", Integer.toString(requestParams.getChildren()));
		generateLine("infants", Integer.toString(requestParams.getInfants()));
		TravelType travelType = requestParams.getTravelType();
		generateLine("Travel Type", travelType.toString());
		if (travelType != TravelType.ONE_WAY) {
			generateLine("Return Date", Constants.PROCHAINVOL_SHORT_DATE_FORMAT.format(requestParams.getReturnDate()));
			generateLine("Return Time", requestParams.getReturnTime());
		}
		generateLine("week", Integer.toString(requestParams.getWeek()));
	}

	@Override
	public void visit(RequestResult requestResult) {
		List<RequestResultUnit> requestResultUnits = requestResult.getRequestResultUnits();
		logger.trace(String.format("nb requestResultUnits = %d", requestResultUnits.size()));

		if (requestResultUnits.size() == 0) {
			generateH4Line("No response");
		} else {
			List<ReportUnit> rapports = requestResultUnits.stream().map(r -> r.getReportUnit())
					.collect(Collectors.toList());
			if (rapports.size() == 0) {
				generateH4Line("No repport");
			} else {
				// TODO bug non implemented "Not implemented, table size = 3"
				generateH4Line("Response information before filters");

				generateTr(strongText("Libellé"), rapports.stream().map(r -> strongText(r.getProvider().toString()))
						.collect(Collectors.toList()));
				generateTr("Durée de la requête HTTP", rapports.stream()
						.map(r -> Long.toString(r.getDuréeHttp()) + " ms").collect(Collectors.toList()));
				generateTr("Durée de l'analyse", rapports.stream().map(r -> Long.toString(r.getDuréeAnalyse()) + " ms")
						.collect(Collectors.toList()));

				generateTr("Nb recommendations vol direct à l'aller", rapports.stream()
						.map(r -> Integer.toString(r.getNbDirectAller())).collect(Collectors.toList()));
				// if (rapports.get(0).getParams().getStops() > 0) {
				// generateTr(
				// "Nb recommendations vol direct au retour",
				// rapports.stream()
				// .map(r -> Integer.toString(r
				// .getNbDirectRetour()))
				// .collect(Collectors.toList()));
				// generateTr(
				// "Nb recommendations vols directs aller et retour",
				// rapports.stream()
				// .map(r -> Integer.toString(r
				// .getNbDirectAllerRetour()))
				// .collect(Collectors.toList()));
				// }

				generateTr("Fourchette des prix avant filtrage",
						rapports.stream().map(r -> String.format("[%.2f .. %.2f]", r.getMinPrice(), r.getMaxPrice()))
								.collect(Collectors.toList()));

				generateTr("Taille du flux de recommendations avant filtrage", rapports.stream()
						.map(r -> Integer.toString(r.getNbRecommendationsRecues())).collect(Collectors.toList()));

				// Rapport sur les filtrages

				if (requestResultUnits.size() > 0 && config.getCurrentFlightFilters().getPredicates().size() > 0) {
					ProchainvolConfig config = requestResult.getProchainvolConfig();
					generateH4Line("Application des filtres dans l'ordre " + config.getCurrentFlightFilters()
							.getPredicates().stream()
							.map(r -> r.getFlightFilter().name()
									+ ((r instanceof RelativePricePredicate || r instanceof PricePredicate)
											? "(" + r.getValue().toString() + ")"
											: ((r instanceof MaxStopPredicate || r instanceof MaxStopOnMovePredicate
													|| r instanceof MaxStopOnReturnPredicate)
															? "(" + r.getValue().toString() + ")" : "")))
							.collect(Collectors.joining(" : ", "[", "]")));

					generateTr(strongText("libellés"), rapports.stream().map(r -> strongText(r.getProvider().name()))
							.collect(Collectors.toList()));
					List<FlightRecommendationPredicate<?>> flightPredicates = requestResult.getProchainvolConfig()
							.getCurrentFlightFilters().getPredicates();
					for (int i = 0; i < flightPredicates.size(); i++) {
						FlightRecommendationPredicate<?> filter = flightPredicates.get(i);
						generateLine(filter.getFlightFilter().name());
					}
				}
				// affichage des résultats filtrés

				requestResultUnits.forEach(r -> {
					List<FlightRecommendation> recommendations = r.getRecommendations();
					generateH4Line(r.getReportUnit().getProvider().name() + " "+r.getRoute().toShortString());

					String errorMess = r.getReportUnit().getErrorMess();
					if (errorMess != null && errorMess.length() > 0) {
						generateLine(errorMess);
						try {
							visit(r.getReportUnit().getParams());
						} catch (ProchainvolException e) {
							String msg = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
							generateLine("impossible de montrer les paramêtres de la requête : " + msg);
						}
					}
					recommendations.forEach(rec -> visit(rec));
				});
			}
		}
	}

	@Override
	public void visit(RequestResultUnit requestResult) {
		// RequestReport rapport = requestResult.getRapportRequete();
		// visit(rapport);
		// String errorMes = rapport.getErrorMess();
		// if (errorMes != null && errorMes.length()>0) {
		// generateLine(errorMes);
		// }
		List<FlightRecommendation> recommendations = requestResult.getRecommendations();
		for (FlightRecommendation recommendation : recommendations) {// recommendations
			visit(recommendation);
		}
	}

	@Override
	public void visit(TravelplaceReaderReport rapportAirportReader) {
		generateH4Line("Rapport de chargement des aéroports");

		generateLine("Quand", rapportAirportReader.getWhen().toString());
		try {
			generateLine("Temps de chargement", Long.toString(rapportAirportReader.getDuree()) + " ms");
		} catch (ProchainvolException e) {
			generateLine("Temps de chargement", e.getMessage());
		}
		generateLine("Nb Airports, source", Integer.toString(rapportAirportReader.getSizeEntry()));
		int size = rapportAirportReader.getSizeResult();
		generateLine("Nb Airports, retained", Integer.toString(size));

		Map<String, List<SqlAirport>> airports = rapportAirportReader.getIataDoublons();
		if (airports != null && airports.size() > 0) {
			generateH4Line("Iata doublons");
			for (Entry<String, List<SqlAirport>> entry : airports.entrySet()) {
				StringBuffer buf = new StringBuffer("Full Names = ");
				int i = 0;
				for (SqlAirport airport : entry.getValue()) {
					if (i != 0) {
						buf.append(", ");
					}
					i++;
					buf.append(airport.getFullName());
				}
				String[] gridFormat = { "grid_4", "grid_8" };
				generateLine(entry.getKey(), buf.toString(), gridFormat);
			}
		}

		airports = rapportAirportReader.getFullNameDoublons();
		if (airports != null && airports.size() > 0) {
			generateH4Line("Full name doublons");
			for (Entry<String, List<SqlAirport>> entry : airports.entrySet()) {
				StringBuffer buf = new StringBuffer();
				int i = 0;
				for (SqlAirport airport : entry.getValue()) {
					if (i != 0) {
						buf.append(", ");
					}
					i++;
					buf.append("{Iata = ").append(airport.getIata()).append(", lat = ").append(airport.getLatitude())
							.append(", lon = ").append(airport.getLongitude()).append("}");
				}
				String[] gridFormat = { "grid_5", "grid_7" };
				generateLine(entry.getKey(), buf.toString(), gridFormat);
			}
		}
	}

	public void visitCountryMap(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String value = entry.getValue();
			if (value.equals("_")) {
				generateLine(doc.createTextNode("_"), doc.createTextNode(entry.getKey()));
			} else {
				Element elemImg = doc.createElement("img");
				elemImg.setAttribute("src", value);
				generateLine(elemImg, doc.createTextNode(entry.getKey()));
			}
		}
	}

}
