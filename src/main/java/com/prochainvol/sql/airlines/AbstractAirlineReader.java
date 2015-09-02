package com.prochainvol.sql.airlines;

import java.util.Map;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.ProchainvolObject;

public abstract class AbstractAirlineReader extends ProchainvolObject {

	protected final AIRLINE_READER airlineCompanyReader;
	protected final StringBuffer rapport;

	public AbstractAirlineReader(AIRLINE_READER airportReader) {
		super();
		this.airlineCompanyReader = airportReader;
		rapport  = new StringBuffer(
				"\nLoading airlines from " + airlineCompanyReader.name() +
				"\n=====================================\n");
	}

	public AIRLINE_READER getAirlineCompanyReader() {
		return airlineCompanyReader;
	}

	public String getRapport() {
		return rapport.toString();
	}

	public abstract Map<String, AirlineCompany> load() throws ProchainvolException ;

}