package com.prochainvol.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.api.request.filter.FlightRecommendationPredicate;
import com.prochainvol.api.response.Route;

public class ProchainvolExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> clazz) {
        if (clazz.equals(ProchainvolConfig.class)
        	)
            return true;
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {

        return (f.getDeclaringClass() == FlightRecommendationPredicate.class && (
        		f.getName().equals("pred")
        ))  || (f.getDeclaringClass() == Route.class && (
        		f.getName().equals("lastUse")
        		|| f.getName().equals("counter")
        ));
    }

}
