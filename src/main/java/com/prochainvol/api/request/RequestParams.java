package com.prochainvol.api.request;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.prochainvol.Constants;
import com.prochainvol.ProchainvolException;
import com.prochainvol.api.TravelType;
import com.prochainvol.api.response.Route;
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.ui.IAffichable;
import com.prochainvol.ui.IAffichableVisitor;
import com.prochainvol.ui.IEditable;
import com.prochainvol.ui.IEditableVisitor;

public class RequestParams extends ProchainvolObject implements IAffichable, IEditable  {


	private static final Logger logger = Logger.getLogger(RequestParams.class
			.getName());

	private boolean isSynchronous = true;
	private int stops = -1;
	private int week = 0;

	private String[] departureAirportIata;	
	private String[] arrivalAirportIata;
	private Date departureDate;
	private Date returnDate;

	private String departureTime = Constants.DEFAULT_TIME;
	private String returnTime = Constants.DEFAULT_TIME;
	private TravelType travelType;
	private int adults;
	private int children;
	private int infants;
	private boolean withReportUnit = false;
	public RequestParams(String departureAirportIata,
			String arrivalAirportIata, Date departDate, Date returnDate,
			Integer stops)
			throws ProchainvolException {
		this(departureAirportIata, arrivalAirportIata, stops);
		this.departureDate = departDate;
		this.returnDate = returnDate;
	}
	public RequestParams(String departureAirportIata,
			String arrivalAirportIata, Date departDate, Date returnDate,
			Integer stops, TravelType travelType, int adults,
			int children, int infants) throws ProchainvolException {
		this(departureAirportIata, arrivalAirportIata, departDate, returnDate, stops);
		this.travelType = travelType;
		this.adults = adults;
		this.children = children;
		this.infants = infants;
	}
	public RequestParams(String departureAirportIata, String arrivalAirportIata,
			Integer stops)
		throws ProchainvolException {
		
		super();
		this.departureAirportIata = getIatas(departureAirportIata);
		this.arrivalAirportIata = getIatas(arrivalAirportIata);
		this.stops = stops;
		this.travelType = Constants.DEFAULT_TRAVEL_TYPE;
		this.adults = Constants.DEFAULT_ADULTS;
		this.children = Constants.DEFAULT_CHILDREN;
		this.infants = Constants.DEFAULT_INFANTS;
	}
	public RequestParams(String departureAirportIata,
			String arrivalAirportIata, String departDate, String returnDate, Integer stops)
			throws ProchainvolException {
		this(departureAirportIata, arrivalAirportIata, stops);
		try {
			this.departureDate = Constants.DATE_PICKER_DATE_FORMAT
					.parse(departDate);
		} catch (ParseException e) {
			String msg = "Impossible de parser cette date = " + departDate;
			logger.fatal(msg);
			throw new ProchainvolException(msg, e);
		}
		try {
			this.returnDate = Constants.DATE_PICKER_DATE_FORMAT
					.parse(returnDate);
		} catch (ParseException e) {
			String msg = "Impossible de parser cette date = " + returnDate;
			logger.fatal(msg);
			throw new ProchainvolException(msg, e);
		}
	}
	@Override
	public void accept(IAffichableVisitor visitor) throws ProchainvolException {
		visitor.visit(this);
	}

	@Override
	public void accept(IEditableVisitor visitor) throws ProchainvolException {
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
		RequestParams other = (RequestParams) obj;
		if (adults != other.adults)
			return false;
		if (arrivalAirportIata == null) {
			if (other.arrivalAirportIata != null)
				return false;
		} else if (!arrivalAirportIata.equals(other.arrivalAirportIata))
			return false;
		if (children != other.children)
			return false;
		if (departureAirportIata == null) {
			if (other.departureAirportIata != null)
				return false;
		} else if (!departureAirportIata.equals(other.departureAirportIata))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (infants != other.infants)
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (returnTime == null) {
			if (other.returnTime != null)
				return false;
		} else if (!returnTime.equals(other.returnTime))
			return false;
		if (stops != other.stops)
			return false;
		if (travelType != other.travelType)
			return false;
		return true;
	}

	public int getAdults() {
		return adults;
	}

	public String[] getArrivalAirportIata() {
		return this.arrivalAirportIata;
	}

	public int getChildren() {
		return children;
	}


	public String[] getDepartureAirportIata() {
		return this.departureAirportIata;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public int getInfants() {
		return infants;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public List<Route> getRouteAsList() {
		List<Route> result = new ArrayList<Route>();
		int dmax = departureAirportIata.length;
		int amax = arrivalAirportIata.length;
		for (int i = 0; i<dmax; i++) {
			for (int j = 0; j<amax; j++) {
				result.add(new Route(departureAirportIata[i], arrivalAirportIata[j]));
			}
		}
		return result;
	}

	public int getStops() {
		return stops;
	}

	public TravelType getTravelType() {
		return travelType;
	}

	public int getWeek() {
		return week;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adults;
		result = prime
				* result
				+ ((arrivalAirportIata == null) ? 0 : arrivalAirportIata
						.hashCode());
		result = prime * result + children;
		result = prime
				* result
				+ ((departureAirportIata == null) ? 0 : departureAirportIata
						.hashCode());
		result = prime * result
				+ ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result
				+ ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + infants;
		result = prime * result
				+ ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result
				+ ((returnTime == null) ? 0 : returnTime.hashCode());
		result = prime * result + stops;
		result = prime * result
				+ ((travelType == null) ? 0 : travelType.hashCode());
		return result;
	}

	public boolean isSynchronous() {
		return isSynchronous;
	}

	public boolean isWithReportUnit() {
		return withReportUnit;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public void setArrivalAirportIata(String[] arrivalAirportIata) {
		this.arrivalAirportIata = arrivalAirportIata;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public void setDepartureAirportIata(String[] departureAirportIata) {
		this.departureAirportIata = departureAirportIata;
	}

	public void setDepartureDate(Date departDate) {
		this.departureDate = departDate;
	}

	public void setDepartureTime(String departTime) {
		this.departureTime = departTime;
	}

	public void setInfants(int infants) {
		this.infants = infants;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

	public void setSynchronous(boolean isSynchronous) {
		this.isSynchronous = isSynchronous;
	}

	public void setTravelType(TravelType travelType) {
		this.travelType = travelType;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public void setWithReportUnit(boolean withReportUnit) {
		this.withReportUnit = withReportUnit;
	}

	private String[] getIatas(String iataAsString) throws ProchainvolException {
		String[] result = new String[1];
		if (iataAsString.matches("^[A-Z0-9]{3}$")) {
			result[0] = iataAsString;			
		} else if (iataAsString.matches(Constants.iataRegExpr))  {
			result[0] = iataAsString.replaceFirst(Constants.iataRegExpr, "$1");
		} else if (iataAsString.matches(Constants.multipleIataRegExpr)) {
			result = iataAsString.split(";");
		} else {
			throw new ProchainvolException("Incorrect list of airport arrivals : "+(iataAsString==null ? "null" : iataAsString));
		}
		return result;
	}

}
