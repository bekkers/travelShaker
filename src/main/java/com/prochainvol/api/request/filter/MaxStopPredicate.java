package com.prochainvol.api.request.filter;

import com.prochainvol.api.response.FlightRecommendation;

public class MaxStopPredicate extends FlightRecommendationPredicate<Integer>{


	public MaxStopPredicate(int max) {
		super(FLIGHT_FILTER.MAX_STOP, max);
	}

	@Override
	public String getValueAsString() {
		return value.toString();
	}

	@Override
	public boolean test(FlightRecommendation r) {
		if (value <0) {
			return true;
		} else {
			return r.getNbStopsAller()<=value && r.getNbStopsRetour()<=value;
		}
	}
}
