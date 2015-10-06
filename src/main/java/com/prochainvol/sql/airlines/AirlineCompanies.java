package com.prochainvol.sql.airlines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.ProchainvolObject;

public class AirlineCompanies extends ProchainvolObject {

	private static final Logger logger = Logger.getLogger(AirlineCompanies.class.getName());

	private final Map<String, List<AirlineCompany>> all = new HashMap<String, List<AirlineCompany>>();

	public AirlineCompanies() {
		super();
	}

	public AirlineCompanies(List<AirlineCompany> aircompanies) {
		super();
		for (AirlineCompany airCompany : aircompanies) {
			addAirCompany(airCompany);
		}
		logger.info("aircompanies : list size = "+aircompanies.size()+", map size = "+all.size());
	}

	public void clearAll() {
		all.clear();
	}

	public List<String> checkAirlineCompanies() {
		List<String> result = new ArrayList<String>();
		int i = 0;
		for (Entry<String, List<AirlineCompany>> entry : all.entrySet()) {
			if (entry.getValue().size() != 1) {
				logger.error("airline company problem with iata " + entry.getKey() + " : " + entry.getValue().size());
				result.add(entry.getKey());
				i++;
			}
		}
		logger.info("number of airline company entries with size not equals 1 = "+i);
		return result;
	}

	public AirlineCompany getAirCompany(String iata) {
		List<AirlineCompany> airport = all.get(iata);
		if (airport == null) {
			return null;
		}
		return airport.get(0);
	}

	public void addAirCompany(AirlineCompany airCompany) {
		if (airCompany.getActive().equals("Y")) { // On jete toutes les compagnies non active
			String iata = airCompany.getIata();
			List<AirlineCompany> previous = all.get(iata);
			if (previous != null) {
				previous.add(airCompany);
			} else {
				List<AirlineCompany> newList = new ArrayList<AirlineCompany>();
				newList.add(airCompany);
				all.put(iata, newList);
			}
		}
	}

	public Map<String, List<AirlineCompany>> getAll() {
		return all;
	}

}
