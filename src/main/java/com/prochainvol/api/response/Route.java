package com.prochainvol.api.response;

import java.util.Date;

import com.prochainvol.json.ProchainvolObject;

public class Route extends ProchainvolObject {

	private String departureAirportIata;

	private String arrivalAirportIata;

	private Date lastUse;
	private int counter;

	public Route() {
		super();
	}

	public Route(String departureAirport, String arrivalAirport) {
		this.departureAirportIata = departureAirport;
		this.arrivalAirportIata = arrivalAirport;
		lastUse = new Date();
	}

	public String toShortString() {
		StringBuffer buff = new StringBuffer();
		buff.append(this.getDepartureAirportIata()).append(":").append(this.getArrivalAirportIata());
		return buff.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (arrivalAirportIata == null) {
			if (other.arrivalAirportIata != null)
				return false;
		} else if (!arrivalAirportIata.equals(other.arrivalAirportIata))
			return false;
		if (departureAirportIata == null) {
			if (other.departureAirportIata != null)
				return false;
		} else if (!departureAirportIata.equals(other.departureAirportIata))
			return false;
		return true;
	}

	public String getArrivalAirportIata() {
		return arrivalAirportIata;
	}

	public int getCounter() {
		return counter;
	}

	public String getDepartureAirportIata() {
		return departureAirportIata;
	}

	public String getKey() {
		return String.format("%s:%s", departureAirportIata, arrivalAirportIata);
	}

	public Date getLastUse() {
		return lastUse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalAirportIata == null) ? 0 : arrivalAirportIata.hashCode());
		result = prime * result + ((departureAirportIata == null) ? 0 : departureAirportIata.hashCode());
		return result;
	}

	public void setArrivalAirportIata(String arrivalAirport) {
		this.arrivalAirportIata = arrivalAirport;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setDepartureAirportIata(String departureAirport) {
		this.departureAirportIata = departureAirport;
	}

	public void setLastUse(Date lastUse) {
		this.lastUse = lastUse;
	}
}
