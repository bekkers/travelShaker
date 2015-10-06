package com.prochainvol.sql;

import org.apache.log4j.Logger;

public class RailwayStation extends SqlAirport {
	

	private static final Logger logger = Logger.getLogger(RailwayStation.class.getName());
	
	
	private static String commaCleaner = "(\\(((?!\\)).)*?),";


	private String administrativeDivision;


	private String urlWikipedia;


	private boolean isAirportStation;

	public RailwayStation(String iata) {
		super(null, null, null, iata);
	}

	public RailwayStation(String name, String city, String country,
			String iata, String location) {
		super(name, city, country, iata);
	}

	

	public RailwayStation(String iata, String longName, String longNameFr,
			String name, String nameFr, String city, String cityFr,
			String administrativeDivision,
			String countryNameEn, String urlWikipedia, String urlGeo,
			Float latitude, Float longitude, boolean isAirportStation) {
		super(name, city, countryNameEn, iata);
		this.titrefr = nameFr;
		this.titrefull = longName;
		this.titrefullfr = longNameFr;
		this.country = countryNameEn;
		this.cityfr = cityFr;
		this.setAdministrativeDivision(administrativeDivision);
		this.setUrlWikipedia(urlWikipedia);
		this.latitude = latitude;
		this.longitude = longitude;
		this.setAirportStation(isAirportStation);
	}


	public String getAdministrativeDivision() {
		return administrativeDivision;
	}

	public void setAdministrativeDivision(String administrativeDivision) {
		this.administrativeDivision = administrativeDivision;
	}

	public String getUrlWikipedia() {
		return urlWikipedia;
	}

	public void setUrlWikipedia(String urlWikipedia) {
		this.urlWikipedia = urlWikipedia;
	}

	public boolean isAirportStation() {
		return isAirportStation;
	}

	public void setAirportStation(boolean isAirportStation) {
		this.isAirportStation = isAirportStation;
	}


	
}
