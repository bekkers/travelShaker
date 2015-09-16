package com.prochainvol.sql.airport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.sql.SqlAirport;

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
		logger.info("airports : list size = "+airports.size()+", map size = "+all.size());
	}

	public void clearAll() {
		all.clear();
	}
	
	public List<String> checkAirports() {
		List<String> result = new ArrayList<String>();
		int i = 0;
		for (Entry<String, List<SqlAirport>> entry :all.entrySet()) {
			if (entry.getValue().size()!=1) {
				logger.error("airport problem with iata "+entry.getKey()+" : "+entry.getValue().size());
				result.add(entry.getKey());
				i++;
			}
		}
		logger.info("number of airport entries with size not equals 1 = "+i);
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

	public Map<String, List<SqlAirport>> getAll() {
		return all;
	}

}
