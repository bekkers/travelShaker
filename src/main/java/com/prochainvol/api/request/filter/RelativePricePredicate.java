package com.prochainvol.api.request.filter;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.prochainvol.api.response.FlightRecommendation;

public class RelativePricePredicate extends FlightRecommendationPredicate<Float> {
	
	private static final Logger logger = Logger
			.getLogger(RelativePricePredicate.class.getName());

	private Float minPrice;
	private Float maxPrice;

	private Float relativeMaxPrice;
	public RelativePricePredicate(float taux) {
		super(FLIGHT_FILTER.RELATIVE_MAX_PRICE, taux);
	}

	public float getMaxPrice() {
		return maxPrice;
	}


	public float getMinPrice() {
		return minPrice;
	}

	public float getRelativeMaxPrice() {
		return this.relativeMaxPrice;
	}

	public float getTaux() {
		return value;
	}

	@Override
	public String getValueAsString() {
		return String.format("maxPrice = %.2f, minPrice= %.2f, relativeMaxPrice= %.2f", maxPrice, minPrice, relativeMaxPrice);
	}

	public void setMaxRelativePrice(List<FlightRecommendation> recommendations) {
		maxPrice = recommendations.stream()
				.map(FlightRecommendation::getPrice)
				.collect(Collectors.maxBy(new PriceComp())).get();
		minPrice = recommendations.stream()
				.map(FlightRecommendation::getPrice)
				.collect(Collectors.minBy(new PriceComp())).get();
		this.relativeMaxPrice = minPrice + value * (maxPrice - minPrice);
		logger.trace(String.format("maxPrice = %.2f, minPrice= %.2f, relativeMaxPrice= %.2f", maxPrice, minPrice, relativeMaxPrice));
	}

	@Override
	public boolean test(FlightRecommendation r) {
		return r.getPrice()<relativeMaxPrice;
	}

}
