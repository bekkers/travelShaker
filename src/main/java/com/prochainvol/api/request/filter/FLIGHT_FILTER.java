package com.prochainvol.api.request.filter;

import com.prochainvol.json.JsonUtilities;

public enum FLIGHT_FILTER {
	MAX_STOP("nb stops on move and nb stops on return <= %s"), 
	MAX_STOP_ON_MOVE("nb stops on move <= %s"), 
	MAX_STOP_ON_RETURN("nb stops on return <= %s"), 
	MAX_PRICE("price < %s"),
	RELATIVE_MAX_PRICE("price < %s =(#minPrice + #p * (#maxPrice - #minPrice))");
	
	private final String title;

	private FLIGHT_FILTER(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String toJson() {
		return JsonUtilities.getGson().toJson(this);
	}

	public String toString() {
		return title;
	}
}
