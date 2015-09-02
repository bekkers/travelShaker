package com.prochainvol.api.response;

import java.util.Date;

import org.apache.log4j.Logger;

import com.prochainvol.api.provider.PROVIDER;
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.ui.IAffichable;
import com.prochainvol.ui.IAffichableVisitor;

/**
 * -Source (Edreams,opodo,)
- vol (obj et JSON avec going path, return path)
- url
- prix
- outboundiata
- inboundiata
- outbounddate
- inbounddate
- dureetotale
- outboundstop
- inboundstop
- duration (int en minutes)
- durationclear (exemple 2h33)
- un path composé de elementaryflight
- - elementaryflight : flightnumber,outbounddate,inbounddate,operatingcarrier, outboundiata,inboundiata,operatingcarrieriata)

 * @author yves
 *
 */

public class FlightRecommendation extends ProchainvolObject implements IAffichable  {
	
	private static final Logger logger = Logger
			.getLogger(FlightRecommendation.class.getName());

	private PROVIDER flightSource;
	private String subSource= "";
	private String errorMess;

	private IFlight flight;
	private final Float price;
	private String url;
	private Date outboundDate;

	private Date inboundDate;


	private String outboundIata;
	
	
	private String inboundIata;
	public FlightRecommendation(PROVIDER flightSource, IFlight flight,
			Float price, String url) {
		super();
		this.flightSource = flightSource;
		this.flight = flight;
		this.price = price;
		this.url = url;
	}
	@Override
	public void accept(IAffichableVisitor visitor) {
        visitor.visit(this);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightRecommendation other = (FlightRecommendation) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (inboundDate == null) {
			if (other.inboundDate != null)
				return false;
		} else if (!inboundDate.equals(other.inboundDate))
			return false;
		if (outboundDate == null) {
			if (other.outboundDate != null)
				return false;
		} else if (!outboundDate.equals(other.outboundDate))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	public int getDurationClear() {
		// TODO calculer la durée en clair à partir de la durée
		return 0;
	}
	public String getErrorMess() {
		return errorMess;
	}
	public IFlight getFlight() {
		return flight;
	}
	public PROVIDER getFlightSource() {
		return flightSource;
	}

	
	public Date getInboundDate() {
		return inboundDate;
	}
	public String getInboundIata() {
		return inboundIata;
	}
	public int getInboundStop() {
		return flight.getGoingStops();
	}
	public int getNbStopsAller() {
		return  flight.getGoingPath().size()-1;
	}

	public int getNbStopsRetour() {
		if (flight.isReturnFlight()) {
			return flight.getReturnPath().size()-1;
		} else {
			return 0;
		}
	}
	public Date getOutboundDate() {
		return outboundDate;
	}

	public String getOutboundIata() {
		return outboundIata;
	}


	public int getOutboundStop() {
		return flight.getReturnStops();
	}


	public Float getPrice() {
		return price;
	}


	public String getSubSource() {
		return subSource;
	}


	public String getUrl() {
		return url;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result
				+ ((inboundDate == null) ? 0 : inboundDate.hashCode());
		result = prime * result
				+ ((outboundDate == null) ? 0 : outboundDate.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}


	public boolean isReturnFlight () {
		return flight.isReturnFlight();
	}


	public void setErrorMess(String errorMess) {
		this.errorMess = errorMess;
	}


	public void setFlight(IFlight flight) {
		this.flight = flight;
	}



	public void setFlightSource(PROVIDER flightSource) {
		this.flightSource = flightSource;
	}


	public void setInboundDate(Date inboundDate) {
		this.inboundDate = inboundDate;
	}


	public void setInboundIata(String inboundIata) {
		this.inboundIata = inboundIata;
	}
	
	public void setOutboundDate(Date outboundDate) {
		this.outboundDate = outboundDate;
	}


	public void setOutboundIata(String outboundIata) {
		this.outboundIata = outboundIata;
	}
	
	public void setSubSource(String subSource) {
		this.subSource = subSource;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
