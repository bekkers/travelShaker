package com.prochainvol.sql.airport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.ProchainvolObject;

public class Airports extends ProchainvolObject {

	private final Map<String, List<SqlAirport>> all = new HashMap<String, List<SqlAirport>>();

	public Airports() {
		super();
	}

	public Airports(List<SqlAirport> airports) {
		super();
		for (SqlAirport airport : airports) {
			addAirport(airport);
		}
	}

	public void clearAll() {
		all.clear();
	}

	public void addAirport(SqlAirport airport) {
		String iata = airport.getIata();
		List<SqlAirport> previous = all.get(iata);
		if (previous != null) {
			previous.add(airport);
		} else {
			List<SqlAirport> newList = new ArrayList<SqlAirport>();
			newList.add(airport);
			all.put(iata, newList);
		}
	}

	public Map<String, List<SqlAirport>> getAll() throws ProchainvolException {
		return all;
	}

}
