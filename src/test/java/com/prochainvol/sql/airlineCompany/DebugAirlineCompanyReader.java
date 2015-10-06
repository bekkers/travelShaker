package com.prochainvol.sql.airlineCompany;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.JsonUtilities;
import com.prochainvol.sql.AbstractSqlReader;
import com.prochainvol.sql.airlines.AirlineCompanies;

public class DebugAirlineCompanyReader extends AbstractSqlReader<AirlineCompanies>{

	private static final Logger logger = Logger.getLogger(DebugAirlineCompanyReader.class
			.getName());
	
	// TODO 
	private static final String airlineList = "/json/debugAirlineCompanyReader.json";


	public DebugAirlineCompanyReader() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public AirlineCompanies load() throws ProchainvolException {
		final String msg = "Building a debug airlines table with entities : {Ryanair, Transavia France}";
		logger.info(msg);
		
		return JsonUtilities.readFromInputStream(AirlineCompanies.class, airlineList);
	}

}
