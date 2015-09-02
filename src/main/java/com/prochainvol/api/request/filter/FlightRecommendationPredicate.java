package com.prochainvol.api.request.filter;

import java.util.function.Predicate;

import com.prochainvol.api.response.FlightRecommendation;
import com.prochainvol.json.ProchainvolObject;

public abstract class FlightRecommendationPredicate<T> extends ProchainvolObject implements Predicate<FlightRecommendation> {

	protected final T value;

	protected final FLIGHT_FILTER flightFilter;
	public FlightRecommendationPredicate(FLIGHT_FILTER flightFilter, T value) {
		this.value = value;
		this.flightFilter = flightFilter;
	}

	public FLIGHT_FILTER getFlightFilter() {
		return flightFilter;
	}


	public T getValue() {
		return value;
	}
	
	public abstract String getValueAsString();

	@Override
	public abstract boolean test(FlightRecommendation r);
	
}
