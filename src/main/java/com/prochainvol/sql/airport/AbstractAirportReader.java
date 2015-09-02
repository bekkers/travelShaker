package com.prochainvol.sql.airport;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.ProchainvolObject;

public abstract class AbstractAirportReader extends ProchainvolObject {

	protected TravelplaceReaderReport rapport;

	public AbstractAirportReader() {
		super();
		rapport = new TravelplaceReaderReport();
	}



	public TravelplaceReaderReport getRapport() {
		return rapport;
	}

	public abstract Airports load() throws ProchainvolException ;

	public void setRapport(TravelplaceReaderReport rapport) {
		this.rapport = rapport;
	}
}