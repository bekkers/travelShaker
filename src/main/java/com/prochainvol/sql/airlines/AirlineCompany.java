package com.prochainvol.sql.airlines;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
/**
 * As of January 2012, the OpenFlights Airlines Database contains 5888 airlines.
 * Each entry contains the following information:
 Airline ID 	Unique OpenFlights identifier for this airline.
 Name 	Name of the airline.
 Alias 	Alias of the airline. For example, All Nippon Airways is commonly known as "ANA".
 IATA 	2-letter IATA code, if available.
 ICAO 	3-letter ICAO code, if available.
 Callsign 	Airline callsign.
 Country 	Country or territory where airline is incorporated.
 Active 	"Y" if the airline is or has until recently been operational, "N" if it is defunct. This field is not reliable: in particular, major airlines that stopped flying long ago, but have not had their IATA code reassigned (eg. Ansett/AN), will incorrectly show as "Y".

 The data is ISO 8859-1 (Latin-1) encoded. The special value \N is used for "NULL" to indicate that no value is available, and is understood automatically by MySQL if imported.

 Notes: Airlines with null codes/callsigns/countries generally represent user-added airlines. Since the data is intended primarily for current flights, defunct IATA codes are generally not included. For example, "Sabena" is not listed with a SN IATA code, since "SN" is presently used by its successor Brussels Airlines.
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.prochainvol.json.ProchainvolObject;

@SuppressWarnings("serial")
@Table(name = "airlines")
@Entity
@Cacheable
public class AirlineCompany extends ProchainvolObject implements Serializable {

	private static final Logger logger = Logger.getLogger(AirlineCompany.class
			.getName());




	/*
	 * Airline 
	 * ID Unique OpenFlights identifier for this airline. 
	 * Name Name of the airline. 
	 * Alias Alias of the airline. For example, All Nippon Airways is commonly known as "ANA". 
	 * IATA 2-letter IATA code, if available. 
	 * ICAO 3-letter ICAO code, if available. 
	 * Callsign Airline callsign. 
	 * Country Country or territory where airline is incorporated. 
	 * Active 
	 *   "Y" if the airline is or has until recently been operational, 
	 *   "N" if it is defunct.
	 *   This field is not reliable: in particular, major airlines that stopped
	 *   flying long ago, but have not had their IATA code reassigned (eg. Ansett/AN), will incorrectly show as "Y".
	 */
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "OPENFLIGHTID")
	private int openflightId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ALIAS")
	private String alias;

	@Column(name = "IATA")
	private String iata;

	@Column(name = "ICAO")
	private String icao;

	@Column(name = "CALLSIGN")
	private String callsign;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "ACTIVE")
	private String active;



	public String getActive() {
		return active;
	}

	public String getAlias() {
		return alias;
	}

	public String getCallsign() {
		return callsign;
	}


	public String getCountry() {
		return country;
	}

	public String getIata() {
		return iata;
	}

	public String getIcao() {
		return icao;
	}

	public int getId() {
		return id;
	}

	public String getKey() {
		return String.format("%s:%s", iata, icao);
	}

	public String getName() {
		return name;
	}

	public int getOpenflightId() {
		return openflightId;
	}

	public void nullifyEmptyAttribute() {
		if (iata != null && (iata.length() == 0 || iata.equals("\\N"))) {
			iata = null;
		}
		if (icao != null && (icao.length() == 0 || icao.equals("\\N"))) {
			icao = null;
		}
		if (callsign != null && (callsign.length() == 0 || callsign.equals("\\N"))) {
			callsign = null;
		}
		if (country != null && (country.length() == 0 || country.equals("\\N"))) {
			country = null;
		}
		if (alias != null && (alias.length() == 0 || alias.equals("\\N"))) {
			alias = null;
		}
		if (active != null && (active.length() == 0 || active.equals("\\N"))) {
			active = null;
		}
		if (active!=null) {
			active = active.toUpperCase();
		}

	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOpenflightId(int openflightId) {
		this.openflightId = openflightId;
	}


}
