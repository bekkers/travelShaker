package com.prochainvol.api.request.filter;

import com.prochainvol.api.response.FlightRecommendation;

public class MaxStopOnReturnPredicate extends FlightRecommendationPredicate<Integer>{

	public MaxStopOnReturnPredicate(int max) {
		super(FLIGHT_FILTER.MAX_STOP_ON_RETURN, max);
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
		return r.getNbStopsRetour()<=value;
		}
	}
}
