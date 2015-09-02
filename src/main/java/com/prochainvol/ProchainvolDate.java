package com.prochainvol;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringJoiner;

import org.apache.log4j.Logger;

import com.prochainvol.json.ProchainvolObject;

// TODO amelioration : consider change this call for Java 8 date time api
// http://www.mscharhag.com/2014/02/java-8-datetime-api.html
public class ProchainvolDate extends ProchainvolObject {
	private static final Logger logger = Logger.getLogger(ProchainvolDate.class.getName());

//	public static List<ProchainvolDate> getNextDaysOfWeek(
//			List<ProchainvolDate> dates, int dayOfWeek)
//			throws ProchainvolException {
//		List<ProchainvolDate> result = new ArrayList<ProchainvolDate>();
//		for (ProchainvolDate date : dates) {
//			// create a Calendar for the the current date
//			Calendar cal = date.getCalendar();
//			do {
//				// get the day of the week for the current day
//				int day = cal.get(Calendar.DAY_OF_WEEK);
//				// check if it is a Saturday or Sunday
//				if (day == dayOfWeek) {
//					ProchainvolDate prochainvolDate = new ProchainvolDate(
//							cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
//							cal.get(Calendar.DAY_OF_MONTH));
//					result.add(prochainvolDate);
//					break;
//				}
//				// advance to the next day
//				cal.add(Calendar.DAY_OF_YEAR, 1);
//			} while (true);
//		}
//		return result;
//	}

	private int year;

	private int month;
	private int dayOfMonth;
	public ProchainvolDate() {
		super();
		Calendar cal = new GregorianCalendar();
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONTH);
		this.dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
	}

	public ProchainvolDate(int week) {
		super();
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.WEEK_OF_YEAR, week);
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONTH);
		this.dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
	}

	public ProchainvolDate(int year, int month, int dayOfMonth) {
		super();
		this.year = year;
		this.month = month-1;
		this.dayOfMonth = dayOfMonth;
	}

	public String datePickerFormat() {
		return Constants.DATE_PICKER_DATE_FORMAT.format(this.getDate());
	}

	public GregorianCalendar getCalendar() {
		return new GregorianCalendar(year, month, dayOfMonth);
	}

	public Date getDate() {
		Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
		return date;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public int getMonth() {
		return month+1;
	}

	public ProchainvolDate getNextDayOfWeek(int dayOfWeek, int week) {
		return this.getNextDaysOfWeek(dayOfWeek, week+1).get(week);
	}

	public List<ProchainvolDate> getNextDaysOfWeek(int dayOfWeek, int size) {
		logger.trace("dayOfWeek = "+dayOfWeek+" size = "+size);
		List<ProchainvolDate> result = new ArrayList<ProchainvolDate>();
		// create a Calendar for the current date
		Calendar cal = this.getCalendar();
		do {
			// get the day of the week for the current day
			int day = cal.get(Calendar.DAY_OF_WEEK);
			// check if it is a Saturday or Sunday
			if (day == dayOfWeek) {
				ProchainvolDate prochainvolDate = new ProchainvolDate(
						cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1,
						cal.get(Calendar.DAY_OF_MONTH));
				result.add(prochainvolDate);
			}
			// advance to the next day
			cal.add(Calendar.DAY_OF_YEAR, 1);
		} while (result.size() < size);
		// stop when we reach the list is filled
		StringJoiner sj = new StringJoiner(":", "[", "]");
		for (ProchainvolDate date : result) {
			sj.add(date.toString());
		}
		logger.trace("result = "+sj.toString());
		return result;
	}
	
	public int getYear() {
		return year;
	}

}
