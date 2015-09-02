package com.prochainvol.httpServlet;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolDate;
import com.prochainvol.api.TravelType;
import com.prochainvol.json.ProchainvolObject;

public class TravelDates extends ProchainvolObject {

	private static final Logger logger = Logger.getLogger(TravelDates.class
			.getName());

	public final static String format = "{\"departureDate\":\"%s\",\"returnDate\":\"%s\"}";

	public static TravelDates getTravelDates(TravelType travelType) {
		return getTravelDates(travelType, 0);
	}

	public static TravelDates getTravelDates(TravelType travelType, int week) {
		ProchainvolDate departureDate = null;
		ProchainvolDate returnDate = null;
		switch (travelType) {
		case LONG_WEEK: // Semaine lundi/dimanche
			departureDate = new ProchainvolDate()
					.getNextDayOfWeek(Calendar.MONDAY, week);
			returnDate = departureDate.getNextDayOfWeek(Calendar.SUNDAY, 0);
			break;
		case LONG_WEEKEND: // Weekend vendredi/lundi
			departureDate = new ProchainvolDate()
					.getNextDayOfWeek(Calendar.FRIDAY, week);
			returnDate = departureDate.getNextDayOfWeek(Calendar.MONDAY, 0);
			break;
		case MEDIUM_WEEK: // Semaine lundi/vendredi
			departureDate = new ProchainvolDate()
					.getNextDayOfWeek(Calendar.MONDAY, week);
			returnDate = departureDate.getNextDayOfWeek(Calendar.FRIDAY, 0);
			break;
		case SHORT_WEEK: // Semaine lundi/jeudi
			departureDate = new ProchainvolDate()
					.getNextDayOfWeek(Calendar.MONDAY, week);
			returnDate = departureDate.getNextDayOfWeek(Calendar.THURSDAY, 0);
			break;
		case SHORT_WEEKEND: // Weekend vendredi/dimanche
			departureDate = new ProchainvolDate()
					.getNextDayOfWeek(Calendar.FRIDAY, week);
			returnDate = departureDate.getNextDayOfWeek(Calendar.SUNDAY, 0);
			break;
		default:
			break;
		}
		return new TravelDates(departureDate, returnDate);

	}

	private TravelType travelType;

	private ProchainvolDate departureDate;
	private ProchainvolDate returnDate;

	public TravelDates() {
		super();
	}

	public TravelDates(ProchainvolDate departureDate, ProchainvolDate returnDate) {
		super();
		this.departureDate = departureDate;
		this.returnDate = returnDate;
	}

	public String datePickerFormat() {
		String mess = String.format(format, this.departureDate.datePickerFormat(),
				this.returnDate.datePickerFormat());
		logger.info(mess);
		return mess;
	}

	public ProchainvolDate getDepartureDate() {
		return departureDate;
	}

	public ProchainvolDate getReturnDate() {
		return returnDate;
	}

	public TravelType getTravelType() {
		return travelType;
	}

}
