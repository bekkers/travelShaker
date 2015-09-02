package com.prochainvol.sql.airport;
/**
 * Daylight Savings Time (DST)

When active, Daylight Savings Time (DST), or "summer time", adds one to the normal timezone, so eg. New York, normally UTC-5, becomes UTC-4 while DST is active. OpenFlights currently understands the following types of DST:

    European: Starts on the last Sunday of March, ends on the last Sunday of October. Used in all European countries (except Iceland), as well as Greenland, Lebanon, Russia and Tunisia. Jordan and Syria are almost the same, starting and ending on Friday instead of Sunday. European DST is also used to (crudely) approximate Iranian DST, although they actually use an entirely different calendar.
    US/Canada: Starts on the second Sunday of March, ends on the first Sunday of November. Used in the United States (except Arizona, Hawaii and island territories) and Canada (with convoluted exceptions).
    South American: Starts on the third Sunday of October, ends on the third Sunday of March. Used, with some variance in the exact dates, in Argentina, Chile, Mexico, Paraguay, Uruguay as well as the African states of Namibia and Mauritius.
    Australia: Starts on the first Sunday of October, ends on the first Sunday of April. Not used in Queensland and the Northern Territory.
    New Zealand: Starts on the last Sunday of September, ends on the first Sunday of April.
    None: DST not observed.
    Unknown: DST status not known. The same as "None".

The rules for DST change constantly and not all airports are up to date or marked correctly. Please contact the OpenFlights team if you find any errors.
Examples

A flight in April departs Singapore (SIN) at 20:00 and arrives in Chennai (MAA) at 21:30. Singapore is UTC+8, Chennai is UTC+5.5. Flight duration is thus (21:30-20:00) - (05:30-08:00) = 1:30 - (-2:30) = 4:00.

A flight in June departs Newark (EWR) at 23:00 and arrives in Singapore (SIN) at 07:40 + 2 days. Singapore is UTC+8, New York is UTC-4 (DST). Flight duration is thus (07:40+48:00)-23:00 - (-04:00-08:00) = -32:40 - -(12:00) = 20:40.
 * @author yves
 *
 */
public enum DST {
	E ("Europe"), A ("US/Canada"), S ("South America"), O ("Australia"), Z ("New Zealand"), N ("None"), U ("Unknown");
	private final String timeZone;

	private DST(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZone() {
		return timeZone;
	}
	
}
