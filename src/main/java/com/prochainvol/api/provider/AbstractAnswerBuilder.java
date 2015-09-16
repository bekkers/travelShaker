package com.prochainvol.api.provider;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.request.filter.Filter;
import com.prochainvol.api.request.filter.FlightRecommendationPredicate;
import com.prochainvol.api.request.filter.MaxStopOnMovePredicate;
import com.prochainvol.api.request.filter.MaxStopOnReturnPredicate;
import com.prochainvol.api.request.filter.MaxStopPredicate;
import com.prochainvol.api.request.filter.PriceComp;
import com.prochainvol.api.request.filter.PricePredicate;
import com.prochainvol.api.request.filter.RelativePricePredicate;
import com.prochainvol.api.response.FlightRecommendation;
import com.prochainvol.api.response.ReportUnit;
import com.prochainvol.api.response.RequestResultUnit;

public abstract class AbstractAnswerBuilder<I, O> {

	private static final Logger logger = Logger
			.getLogger(AbstractAnswerBuilder.class.getName());

	protected final PROVIDER provider;

	protected final ProchainvolConfig config;

	public AbstractAnswerBuilder(ProchainvolConfig config, PROVIDER provider) {
		this.config = config;
		this.provider = provider;
	}

	public abstract List<RequestResultUnit> buildProchainvolAnswer(
			List<O> airSearchResponse, RequestParams requestParam, Date startDate) throws ProchainvolException;

	public abstract RequestResultUnit buildProchainvolAnswer(
			O airSearchResponse, RequestParams requestParam, Date startDate) throws ProchainvolException;

	public ProchainvolConfig getConfig() {
		return config;
	}

	public PROVIDER getProvider() {
		return provider;
	}

	protected RequestResultUnit filterRecommendations(
			RequestParams requestParams, ReportUnit rapportRequete,
			List<FlightRecommendation> recommendations)
			throws ProchainvolException {
		logger.trace("nb recommendations = "+recommendations.size());

		rapportRequete.setNbRecommendationsRecues(recommendations.size());
		if (recommendations.size() > 0) {
			Float maxPrice = recommendations.stream()
					.map(FlightRecommendation::getPrice)
					.collect(Collectors.maxBy(new PriceComp())).get();
			rapportRequete.setMaxPrice(maxPrice);
			Float minPrice = recommendations.stream()
					.map(FlightRecommendation::getPrice)
					.collect(Collectors.minBy(new PriceComp())).get();
			rapportRequete.setMinPrice(minPrice);


		}
		// calcul du nombre des recommandations avec stops
		List<FlightRecommendation> directFlightsOnMove = recommendations
				.stream().filter(new MaxStopOnMovePredicate(0))
				.collect(Collectors.toList());
		int size = 0;
		if (directFlightsOnMove != null) {
			size = directFlightsOnMove.size();
			logger.trace("directFlightsOnMove.size() = " + size);
		}
		rapportRequete.setNbDirectAller(size);

		List<FlightRecommendation> directFlightsOnReturn = recommendations
				.stream().filter(new MaxStopOnReturnPredicate(0))
				.collect(Collectors.toList());
		if (directFlightsOnReturn != null) {
			size = directFlightsOnReturn.size();
			logger.trace("directFlightsOnReturn.size() = " + size);
		}
		rapportRequete.setNbDirectRetour(size);

		List<FlightRecommendation> directFlightsOnMoveAndReturn = recommendations
				.stream().filter(new MaxStopPredicate(0))
				.collect(Collectors.toList());
		if (directFlightsOnMoveAndReturn != null) {
			size = directFlightsOnMoveAndReturn.size();
			logger.trace("directFlightsOnMoveAndReturn.size() = " + size);
		}
		rapportRequete.setNbDirectAllerRetour(size);
		logger.trace("rapportRequete = "+rapportRequete);
		
		Filter filter = config.getCurrentFlightFilters();
//		System.out.println(filter);

		// filtrage des recommendations
		List<FlightRecommendationPredicate<?>> predicateList = filter.getPredicates();
		logger.trace("recommendations.size() = " + recommendations.size()
				+ " predicateList.size() = " + (predicateList== null ? "null" :predicateList.size()+""));
		List<FlightRecommendation> filteredRecommendations = recommendations;
		for (FlightRecommendationPredicate<?> flightFilter : predicateList) {
			logger.trace("flightFilter = "
					+ String.format(flightFilter.getFlightFilter().toString(),
							flightFilter.getValueAsString()));

			switch (flightFilter.getFlightFilter()) {
			case RELATIVE_MAX_PRICE:
				RelativePricePredicate relativePriceFilter = (RelativePricePredicate) flightFilter;
				relativePriceFilter.setMaxRelativePrice(recommendations);
				filteredRecommendations = recommendations.stream()
						.filter(relativePriceFilter)
						.collect(Collectors.toList());
				logger.trace(relativePriceFilter + ", nbRecommendations = "
						+ filteredRecommendations.size());
				break;
			case MAX_PRICE:
				PricePredicate priceFilter = (PricePredicate) flightFilter;
				filteredRecommendations = recommendations.stream()
						.filter(priceFilter).collect(Collectors.toList());
				break;
			case MAX_STOP:
			case MAX_STOP_ON_MOVE:
			case MAX_STOP_ON_RETURN:
				@SuppressWarnings("unchecked")
				FlightRecommendationPredicate<Integer> integerFilter = (FlightRecommendationPredicate<Integer>) flightFilter;
				filteredRecommendations = recommendations.stream()
						.filter(integerFilter).collect(Collectors.toList());
				break;
			}
			logger.trace("new nbRecommendations = "
					+ filteredRecommendations.size());
			recommendations = filteredRecommendations;
		}

		logger.info(getProvider().name() + " : nb recommendations reçues = "
				+ recommendations.size() + ", nb gardées = "
				+ filteredRecommendations.size());

		return new RequestResultUnit(filteredRecommendations, rapportRequete);
	}

}
