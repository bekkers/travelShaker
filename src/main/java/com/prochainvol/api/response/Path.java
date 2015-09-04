package com.prochainvol.api.response;

import java.io.Serializable;
import java.util.Date;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.sql.airlines.AirlineCompany;

@SuppressWarnings("serial")
public class Path extends ProchainvolObject implements Serializable {

	protected final short FlightNumber;

	protected Route Route;

	protected final Date dateAtterrisage;  // date d'atterrissage

	protected final Date dateDecolage; // date de décolage

	protected final String CarrierName;

	protected final String CarrierIataCode;

	public Path(short flightNumber2, Route route2, Date outboundDate,
			Date inboundDate, String operatingCarrierIataCode, ProchainvolConfig config) {
		this(flightNumber2, route2, outboundDate, inboundDate, null, operatingCarrierIataCode, config);
	}

	public Path(short flightNumber, Route route, Date outboundDate, Date inboundDate, 
			String operatingCarrierName, String operatingCarrierIataCode, ProchainvolConfig config) {
		this.FlightNumber = flightNumber;
		this.Route = route;
		this.dateAtterrisage = inboundDate;
		this.dateDecolage = outboundDate;
		this.CarrierIataCode = operatingCarrierIataCode;
		if (operatingCarrierName!= null) {
			this.CarrierName = operatingCarrierName;
		} else {
			AirlineCompany airlinecompanie = ProchainvolConfig.getAirlinecompanies().getAirCompany(operatingCarrierIataCode);
			this.CarrierName = airlinecompanie.getIata();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Path other = (Path) obj;
		if (CarrierIataCode == null) {
			if (other.CarrierIataCode != null)
				return false;
		} else if (!CarrierIataCode.equals(other.CarrierIataCode))
			return false;
		if (FlightNumber != other.FlightNumber)
			return false;
		if (dateDecolage == null) {
			if (other.dateDecolage != null)
				return false;
		} else if (!dateDecolage.equals(other.dateDecolage))
			return false;
		return true;
	}

	public String getCarrierIataCode() {
		return CarrierIataCode;
	}
	public String getCarrierName() {
		return CarrierName;
	}
	public Date getDateAtterrisage() {
		return dateAtterrisage;
	}
	public Date getDateDecolage() {
		return dateDecolage;
	}
	public short getFlightNumber() {
		return FlightNumber;
	}
	public Route getRoute() {
		return Route;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((CarrierIataCode == null) ? 0 : CarrierIataCode.hashCode());
		result = prime * result + FlightNumber;
		result = prime * result
				+ ((dateDecolage == null) ? 0 : dateDecolage.hashCode());
		return result;
	}

	public void setRoute(Route route) {
		Route = route;
	}

	public void setRoute(String departureAirportItata, String arrivalAirportItata) {
		this.Route = new Route(departureAirportItata, arrivalAirportItata);
	}




}