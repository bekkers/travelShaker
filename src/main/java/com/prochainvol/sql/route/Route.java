package com.prochainvol.sql.route;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prochainvol.json.ProchainvolObject;

/**
 * As of January 2012, the OpenFlights/Airline Route Mapper Route Database
 * contains 59036 routes between 3209 airports on 531 airlines spanning the
 * globe, as shown in the map above. The data is ISO 8859-1 (Latin-1) encoded.
 * The special value \N is used for "NULL" to indicate that no value is
 * available, and is understood automatically by MySQL if imported. Notes:
 * Routes are directional: if an airline operates services from A to B and from
 * B to A, both A-B and B-A are listed separately. Routes where one carrier
 * operates both its own and codeshare flights are listed only once.
 * 
 * @author bekkers
 * 
 */
@SuppressWarnings("serial")
@Table(name = "routes")
@Entity
public class Route extends ProchainvolObject implements Serializable {

	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}


	public static void main(String[] args) {
	}

	@Id
	@GeneratedValue
	@Column(name = "idairline")
	private int id;


	/**
	 * Source airport 3-letter (IATA) or 4-letter (ICAO) code of the source
	 * airport.
	 */
	@Column(name = "outbound")
	private String departureAirport;

	/**
	 * Source airport ID Unique OpenFlights identifier for source airport (see
	 * Airport)
	 */
	@Column(name = "outboundid")
	private int departureAirportId;

	/**
	 * Destination airport 3-letter (IATA) or 4-letter (ICAO) code of the
	 * destination airport.
	 */
	@Column(name = "inbound")
	private String arrivalAirport;

	/**
	 * Destination airport ID Unique OpenFlights identifier for destination
	 * airport (see Airport)
	 */
	@Column(name = "inboundid")
	private int arrivalAirportId;

	/**
	 * Codeshare "Y" if this flight is a codeshare (that is, not operated by
	 * Airline, but another carrier), empty otherwise.
	 */
	@Column
	private String codeshare;

	/**
	 * Stops Number of stops on this flight ("0" for direct)
	 */
	@Column
	private int stops;

	/**
	 * Equipment 3-letter codes for plane type(s) generally used on this flight,
	 * separated by spaces
	 */
	@Column
	private String equipment;

	@Column
	private String active;

	@Column
	private String airline;

	public String getAirline() {
		return airline;
	}


	public void setAirline(String airline) {
		this.airline = airline;
	}


	public Route() {
		super();
	}


	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public int getArrivalAirportId() {
		return arrivalAirportId;
	}

	public String getCodeshare() {
		return codeshare;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public int getDepartureAirportId() {
		return departureAirportId;
	}

	public String getEquipment() {
		return equipment;
	}

	public int getId() {
		return id;
	}

	public int getStops() {
		return stops;
	}


	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public void setArrivalAirportId(int arrivalAirportId) {
		this.arrivalAirportId = arrivalAirportId;
	}

	public void setCodeshare(String codeshare) {
		this.codeshare = codeshare;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public void setDepartureAirportId(int departureAirportId) {
		this.departureAirportId = departureAirportId;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

}
