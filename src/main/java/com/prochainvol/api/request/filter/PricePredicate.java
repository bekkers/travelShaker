package com.prochainvol.api.request.filter;

import com.prochainvol.api.response.FlightRecommendation;

public class PricePredicate extends FlightRecommendationPredicate<Float> {
	
	public PricePredicate(float max) {
		super(FLIGHT_FILTER.MAX_PRICE, max);
	}

	@Override
	public String getValueAsString() {
		return value.toString();
	}

	@Override
	public boolean test(FlightRecommendation r) {
		return r.getPrice()<value;
	}

}
