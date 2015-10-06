package com.prochainvol.sql;

import java.util.Map;

import com.prochainvol.json.ProchainvolObject;

public class RailwayStations extends ProchainvolObject{
	
	private final Map<String, RailwayStation> railwayStationMap;
	
	public RailwayStations(Map<String, RailwayStation> railwaySationMap) {
		super();
		this.railwayStationMap = railwaySationMap;
	}

	public Map<String, RailwayStation> getRailwayStationMap() {
		return railwayStationMap;
	}


}
