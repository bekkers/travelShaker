package com.prochainvol.sql.airport;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.JsonUtilities;

public class DebugAirportReader extends AbstractAirportReader{

	private static final Logger logger = Logger.getLogger(DebugAirportReader.class
			.getName());
	
	private static final String airportList = "/json/debugAirports.json";


	public DebugAirportReader() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public Airports load() throws ProchainvolException {
		final String msg = "Building a debug airport table with entities : {Marseille, london, orly, charlesDeGaulle}";
		logger.info(msg);
		rapport.start();
		
		return JsonUtilities.readFromInputStream(Airports.class, airportList);
	}

}
