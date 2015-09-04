package com.prochainvol;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import com.prochainvol.api.EXECUTOR_TYPE;
import com.prochainvol.api.TravelType;
import com.prochainvol.api.provider.PROVIDER;

public class Constants {

	private static final Logger logger = Logger.getLogger(Constants.class
			.getName());


    public static final String PROCHAINVOL_PROPS_FILE_NAME = "/prochainvol.properties";
    public static final Properties PROCHAINVOL_PROPS = ProchainvolUtilities
			.loadProperties(PROCHAINVOL_PROPS_FILE_NAME);

    public static final String LINUX_IP_ADDRESS= PROCHAINVOL_PROPS.getProperty(
			"linuxIpAdress");

    public static Float DEFAULT_MAX_PRICE_RATIO;
	public static int DEFAULT_ADULTS;
	public static int DEFAULT_MAX_INACTIVE_INTERVAL;
	public static int DEFAULT_TRAVESHACKER_MAX_INACTIVE_INTERVAL;
	public static int DEFAULT_CHILDREN;
	public static int DEFAULT_INFANTS;
	public static int DEFAULT_MAX_STOPS;
	public static int ANY_VALUE_FOR_MAX_STOP = -1;

	public static TravelType DEFAULT_TRAVEL_TYPE = TravelType.RETURN;
	public static final String ANY_TIME = PROCHAINVOL_PROPS.getProperty("anyTime", "ANY");
	public static final String DEFAULT_TIME = ANY_TIME;
	public static final int DEFAULT_WEEK = 0;

	public static final SimpleDateFormat DATE_PICKER_DATE_FORMAT = new SimpleDateFormat(
			PROCHAINVOL_PROPS.getProperty(
					"datePickerDateFormat", "MM/dd/yyyy"), new Locale("en", "US"));

	static final String shortDateTimeFormat = PROCHAINVOL_PROPS.getProperty(
			"prochainvolShortDateTimeFormat", "dd MMMM hh:mm aaa");
	public static final SimpleDateFormat PROCHAINVOL_SHORT_DATE_TIME_FORMAT = new SimpleDateFormat(
			shortDateTimeFormat, Locale.FRANCE);
	static final String shortDateFormat = PROCHAINVOL_PROPS.getProperty(
			"prochainvolShortDateTimeFormat", "dd MMM yyyy");
	public static final SimpleDateFormat PROCHAINVOL_SHORT_DATE_FORMAT = new SimpleDateFormat(
			shortDateFormat, Locale.FRANCE);

	public static final String iataRegExpr = "^\\(([A-Z0-9]{3})\\).*$";
	public static final String multipleIataRegExpr = "^([A-Z0-9]{3})(\\;[A-Z0-9]{3}))*$";
	
	public static final boolean IS_WINDOWS = OSValidator.isWindows();
	public static final EXECUTOR_TYPE DEFAULT_EXECUTOR_TYPE = Constants.IS_WINDOWS ? EXECUTOR_TYPE.BAM
			: EXECUTOR_TYPE.DIRECT;
	
	// session
	public static final String SESS_PROCHAINVOL_CONFIG = "prochainvolConfig";
	public static final String SESS_SESSION_INFO = "sessionInfo";
	public static final String CTX_SESSION_COUNT = "sessionCount";
	public static final String CTX_SESSIONS_INFO = "sessionsInfo";

	// odigeo
	
    public static final String ODIGEO_PROPS_FILE_NAME = "/odigeo.properties";
    public static final Properties ODIGEO_PROPS = ProchainvolUtilities
			.loadProperties(ODIGEO_PROPS_FILE_NAME);
    
    public static final QName ODIGEO_SERVICE_NAME = new QName("http://metasearch.odigeo.com/metasearch/ws/v2/", "ItinerarySearch");
    public static final String ODIGEO_WSDL_URL = "http://metasearch.odigeo.com/metasearch/ws/v2/ItinerarySearch?wsdl";

	public static final String ODIGEO_PARTID = "38481";

	
	// Etat d'un utilisateur

	public static PROVIDER[] DEFAULT_PROVIDERS = { PROVIDER.ODIGEO };

	public static PROVIDER[] getDEFAULT_PROVIDERS() {
		return DEFAULT_PROVIDERS;
	}

	public static void setDEFAULT_PROVIDERS(PROVIDER[] dEFAULT_PROVIDERS) {
		DEFAULT_PROVIDERS = dEFAULT_PROVIDERS;
	}
	
	static {
		String maxStopAsString = PROCHAINVOL_PROPS
				.getProperty("defaultMaxStops");
		System.out.println("maxStopAsString = '"+maxStopAsString+"'");
					
		if (maxStopAsString == null || maxStopAsString.equals("-1")) {
			DEFAULT_MAX_STOPS = -1;
		} else if (maxStopAsString.matches("^[0..9]+$")){
			DEFAULT_MAX_STOPS = Integer.parseInt(maxStopAsString);
		} else {
			String mess = String.format(
					"Incorrect defaultMaxStops in prochainvol.properties file : %s",
					maxStopAsString);
			logger.fatal(mess);
			throw new ProchainvolRuntimeException(mess);
		}

	}
}
