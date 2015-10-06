package com.prochainvol.sql.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.csv.airport.AirodysseyCsvAirportReader;
import com.prochainvol.csv.airport.DbpediaCsvAirportReader;
import com.prochainvol.csv.airport.OpenflightCsvAirportReader;
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.sql.SqlAirport;
import com.prochainvol.sql.airport.SqlAirportReader;

public class Routes extends ProchainvolObject {

	private static final Logger logger = Logger.getLogger(Routes.class.getName());

	// les routes sont regroupées par clés "depIata:arrIata:compIata"
	private final Map<String, List<Route>> all = new HashMap<String, List<Route>>();

	public Routes() {
		super();
	}

	public Routes(List<Route> airports) {
		super();
		for (Route airport : airports) {
			addRoute(airport);
		}
	}

	public void clearAll() {
		all.clear();
	}

	public List<String> checkRoutes() {
		List<String> result = new ArrayList<String>();
		for (Entry<String, List<Route>> entry : all.entrySet()) {
			if (entry.getValue().size() != 1) {
				logger.error("airport problem with iata " + entry.getKey() + " : " + entry.getValue().size());
				result.add(entry.getKey());
			} else {
				Route route = entry.getValue().get(0);
				String depIata = route.getDepartureAirport();
				checkExists(depIata);
				String arrIata = route.getArrivalAirport();
				checkExists(arrIata);
				String compIata = route.getAirline();
				if (ProchainvolConfig.getAirports().getAirport(compIata) == null) {
//					logger.error("unknow company iata : " + compIata);
				}
			}
		}
		return result;
	}

	public void checkExists(String depIata) {
		// TODO mettre les inconnus en liste
		SqlAirport sqlAirport = ProchainvolConfig.getAirports().getAirport(depIata);
		if (sqlAirport == null) {
			logger.error("unknow departure airport iata : " + depIata);
			sqlAirport = OpenflightCsvAirportReader.getAirport(depIata);
			if (sqlAirport !=null) {
				SqlAirportReader.write(sqlAirport);
				logger.info("airport added from Openflights = "+sqlAirport );
			} else {
				sqlAirport = DbpediaCsvAirportReader.getAirport(depIata);
				if (sqlAirport !=null) {
					logger.info("airport added from Dbpedia = "+sqlAirport );
					SqlAirportReader.write(sqlAirport);
					logger.info("airport added from Dbpedia = "+sqlAirport );
				} else {
					sqlAirport = AirodysseyCsvAirportReader.getAirport(depIata);
					if (sqlAirport !=null) {
						SqlAirportReader.write(sqlAirport);
						logger.info("airport added from Airodyssey = "+sqlAirport );
					} else {
						logger.error("comfirmed");
					}
				}
			}
		}
	}

	public Route getRoute(String iata) {
		List<Route> airport = all.get(iata);
		if (airport == null) {
			return null;
		}
		return airport.get(0);
	}

	public void addRoute(Route route) {
		String key = route.getDepartureAirport() + ":" + route.getArrivalAirport() + ":" + route.getAirline();
		List<Route> previous = all.get(key);
		if (previous != null) {
			previous.add(route);
		} else {
			List<Route> newList = new ArrayList<Route>();
			newList.add(route);
			all.put(key, newList);
		}
	}

	public Map<String, List<Route>> getAll() throws ProchainvolException {
		return all;
	}

}
