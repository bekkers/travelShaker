package com.prochainvol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

public class ProchainvolUtilities {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final Logger logger = Logger.getLogger(ProchainvolUtilities.class.getName());

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	public static Date addMonth(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months); // minus number would decrement the
											// months
		return cal.getTime();
	}

	public static String getMyIPAddress() {

		// Let's ask AWS
		try {
			URL url = new URL("http://checkip.amazonaws.com/");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			return br.readLine();
		} catch (Exception e) {
			return Constants.LINUX_IP_ADDRESS;
		}

	}

	public static String getStackTraceAsString(Throwable e) {
		StringBuffer stack = new StringBuffer();
		for (StackTraceElement elem : e.getStackTrace()) {
			stack.append(elem).append("\n");
		}
		final String msg = e.getClass().getName() + " : " + e.getMessage() + "\n" + stack.toString();
		return msg;
	}

	public static Properties loadProperties(String source) {
		Properties result = new Properties();

		InputStream input = null;

		try {
			// load a properties file
			input = Constants.class.getResourceAsStream(source);
			result.load(input);

		} catch (IOException ex) {
			String mess = "Impossible de charger les properties : " + source;
			logger.fatal(mess, ex);
			throw new ProchainvolRuntimeException(mess, ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	// HTTP GET request
	public static String sendGet(String url) throws ClientProtocolException, IOException {

		StringBuffer result = new StringBuffer();
		BufferedReader rd =  null;
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);

			// add request header
			request.addHeader("User-Agent", USER_AGENT);

			HttpResponse response = client.execute(request);

			StatusLine statusLine = response.getStatusLine();
			logger.info("Sending 'GET' request to URL : " + url);

			int statusCode = statusLine.getStatusCode();
			logger.info("Response status : " + statusCode + ", Reason = " + statusLine.getReasonPhrase());

			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

		} finally {
			if (rd!=null) {
				rd.close();
			}
		}
		return result.toString();

	}

	/*
	 * Converts XMLGregorianCalendar to java.util.Date in Java
	 */
	public static Date toDate(XMLGregorianCalendar calendar) throws ProchainvolException {
		if (calendar == null) {
			String mess = "xmlGregorianCalendar is null";
			logger.fatal(mess);
			throw new ProchainvolException(mess);
		}
		return calendar.toGregorianCalendar().getTime();
	}

	/*
	 * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws ProchainvolException {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		} catch (DatatypeConfigurationException ex) {
			logger.fatal(ex);
			throw new ProchainvolException(ex);
		}
		return xmlCalendar;
	}

	public static void writeReport(StringBuffer rapport, String fileName) throws ProchainvolException {
		if (OSValidator.isWindows()) {
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
			try {
				FileWriter writer = new FileWriter(fileName);
				writer.write(rapport.toString());
				writer.close();
			} catch (IOException e) {
				logger.warn(e);
				throw new ProchainvolException(e);
			}
		}
	}

}
