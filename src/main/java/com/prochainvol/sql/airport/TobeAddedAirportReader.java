package com.prochainvol.sql.airport;

import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.json.JsonUtilities;
import com.prochainvol.sql.AbstractSqlReader;
import com.prochainvol.sql.SqlAirport;

public class TobeAddedAirportReader extends AbstractSqlReader<Airports>{

	private static final Logger logger = Logger.getLogger(TobeAddedAirportReader.class
			.getName());
	
	private static final String airportList = "/json/toBeAddedAirport.json";

	private static Airports airports;

	public static Airports getAirports() throws ProchainvolException {
		if (airports==null) {
			airports = new TobeAddedAirportReader().load();
			airports.checkAirports();
		}
		return airports;
	}
	
	public static void main(String[] args) throws ProchainvolException {
		new ProchainvolConfig();
		getAirports();
		airports.checkAirports();
		for (Entry<String, List<SqlAirport>> entry : airports.getAll().entrySet()) {
			SqlAirport airport = entry.getValue().get(0);
			SqlAirport previous = SqlAirportReader.readByIata(airport.getIata());
			if (previous == null) {
				SqlAirportReader.write(airport);
				System.out.println(airport);
			} else {
				System.out.println("allready there, id = "+previous.getId());
			}
		}
	}

	public static SqlAirport getAirport(String iata) throws ProchainvolException {
		Airports airports = getAirports();
		return airports.getAirport(iata);
	}

	public TobeAddedAirportReader() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public Airports load() throws ProchainvolException {
		final String msg = "Building a toBeAdded airport table with file "+airportList;
		logger.info(msg);
		
		return JsonUtilities.readFromInputStream(Airports.class, airportList);
	}

}
