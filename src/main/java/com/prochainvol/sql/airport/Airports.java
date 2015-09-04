package com.prochainvol.sql.airport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.ProchainvolObject;

public class Airports extends ProchainvolObject {

	private static final Logger logger = Logger
			.getLogger(Airports.class.getName());

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
	
	public List<String> checkAirports() {
		List<String> result = new ArrayList<String>();
		for (Entry<String, List<SqlAirport>> entry :all.entrySet()) {
			if (entry.getValue().size()!=1) {
				logger.error("airport problem with iata "+entry.getKey()+" : "+entry.getValue().size());
				result.add(entry.getKey());
			}
		}
		return result;
	}
	
	public SqlAirport getAirport(String iata) {
		List<SqlAirport> airport = all.get(iata);
		if (airport == null) {
			return null;
		}
		return airport.get(0);
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
