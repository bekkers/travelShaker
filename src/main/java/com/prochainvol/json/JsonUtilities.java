package com.prochainvol.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prochainvol.Constants;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.ProchainvolUtilities;

public class JsonUtilities {

	private static String GSON_DATE_FORMAT_AS_STRING;

	private static final String RESPONSE_MESS = "{\"reponse\":\"%s\"}";

	private static final String RESPONSE_CONTENT = "{\"reponse\":\"%s\",\n\"content\":%s}";

	private static SimpleDateFormat GSON_DATE_FORMAT;

	private static Gson gson;

	private static Gson gsonPretty;

	private static Gson gsonPrettyWithoutRRU;

	private static final Logger logger = Logger.getLogger(JsonUtilities.class
			.getName());

	public static String createResponse(String content) {
		return String.format(RESPONSE_CONTENT, "ok", content);
	}

	public static String createResponse(String msg, String content) {
		return String.format(RESPONSE_CONTENT, msg, content);
	}

	public static String createResponseErr(String msg) {
		return String.format(RESPONSE_MESS, msg);
	}

	/*
*/
	public static String createResponseOk() {
		return String.format(RESPONSE_MESS, "ok");
	}

	public static Gson getGson() {
		if (gson == null) {
			gson = new GsonBuilder()
					.registerTypeAdapter(XMLGregorianCalendar.class,
							new XGCalConverter.Serializer())
					.registerTypeAdapter(XMLGregorianCalendar.class,
							new XGCalConverter.Deserializer())
					.setDateFormat(getGSON_DATE_FORMAT_AS_STRING())
					.setExclusionStrategies(new ProchainvolExclusionStrategy())
					.registerTypeAdapter(ProchainvolConfig.class,
							new ProchainvolConfigSerializer()).create();
		}

		return gson;
	}

	public static SimpleDateFormat getGSON_DATE_FORMAT() {
		if (GSON_DATE_FORMAT == null) {
			GSON_DATE_FORMAT = new SimpleDateFormat(
					getGSON_DATE_FORMAT_AS_STRING(), Locale.ENGLISH);
		}
		return GSON_DATE_FORMAT;
	}

	public static String getGSON_DATE_FORMAT_AS_STRING() {
		if (GSON_DATE_FORMAT_AS_STRING == null) {
			GSON_DATE_FORMAT_AS_STRING = Constants.PROCHAINVOL_PROPS
					.getProperty("gsonDateFormat", "dd MMM yyyy HH:mm:ss zzz");
		}
		return GSON_DATE_FORMAT_AS_STRING;
	}

	public static Gson getGsonPretty() {
		if (gsonPretty == null) {
			gsonPretty = new GsonBuilder()
					.setPrettyPrinting()
					.registerTypeAdapter(XMLGregorianCalendar.class,
							new XGCalConverter.Serializer())
					.registerTypeAdapter(XMLGregorianCalendar.class,
							new XGCalConverter.Deserializer())
					.setDateFormat(getGSON_DATE_FORMAT_AS_STRING())
					.setExclusionStrategies(new ProchainvolExclusionStrategy())
					.registerTypeAdapter(ProchainvolConfig.class,
							new ProchainvolConfigSerializer()).create();
		}
		return gsonPretty;
	}

	public static Gson getGsonPrettyWithoutRRU() {
		if (gsonPrettyWithoutRRU == null) {
			gsonPrettyWithoutRRU = new GsonBuilder()
					.setPrettyPrinting()
					.setDateFormat(getGSON_DATE_FORMAT_AS_STRING())
					.setExclusionStrategies(new ProchainvolExclusionStrategy())
					.setExclusionStrategies(new ReportUnitExclusionStrategy())
					.registerTypeAdapter(ProchainvolConfig.class,
							new ProchainvolConfigSerializer()).create();
		}
		return gsonPrettyWithoutRRU;
	}

	public static String inputStreamToString(String fileName)
			throws ProchainvolException, URISyntaxException, IOException {
		URL url = JsonUtilities.class.getResource(fileName);
		if (url == null) {
			String msg = String.format(
					"Impossible de trouver la ressource %s", fileName);
			logger.fatal(msg);
			throw new ProchainvolException(msg);
		}
		URI uri = url.toURI();
		logger.debug("Reading json from = " + uri.getPath());

		String string = Files
				.lines(Paths.get(uri), Charset.defaultCharset()).parallel() // for
																			// parallel
																			// processing
				// .filter(line -> line.length() > 2) // to filter some
				// lines
				// // by a predicate
				.map(String::trim) // to change line
				.collect(Collectors.joining());
		logger.trace("json = " + string);
		return string;
	}

	public static <T> T readFromFile(Class<T> c,
			File file) throws ProchainvolException {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new ProchainvolException(e);
		}
		return (T) getGson().fromJson(br, c);
	}

	public static <T> T readFromFile(Class<T> c,
			String fileName) throws ProchainvolException {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new ProchainvolException(e);
		}
		return (T) getGson().fromJson(br, c);
	}

	public static <T> T readFromInputStream(Class<T> c, String fileName)
			throws ProchainvolException {
		logger.debug("fileName = " + fileName);
		try {
			String string = inputStreamToString(fileName);
			return (T) getGson().fromJson(string, c);
		} catch (IOException | URISyntaxException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new ProchainvolException(e);
		} // to join lines

	}

	public static <T extends ProchainvolObject> T readFromString(Class<T> c,
			String json) throws ProchainvolException {
		BufferedReader br;
		br = new BufferedReader(new StringReader(json));
		return (T) getGson().fromJson(br, c);
	}
	public static void writeToFile(ProchainvolObject object, File file)
			throws ProchainvolException {

		try {
			logger.info("writing json to = " + file);
			// write converted json data to a file named "CountryGSON.json"
			FileWriter writer = new FileWriter(file);
			writer.write(object.toString());
			writer.close();

		} catch (IOException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new ProchainvolException(e);
		}
	}
	public static void writeToFile(ProchainvolObject object, String fileName)
			throws ProchainvolException {

		try {
			logger.info("writing json to = " + fileName);
			// write converted json data to a file named "CountryGSON.json"
			FileWriter writer = new FileWriter(fileName);
			writer.write(object.toString());
			writer.close();

		} catch (IOException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new ProchainvolException(e);
		}
	}
	public static void writeToFile(String json, String fileName) throws ProchainvolException {
		try {
			logger.info("writing json to = " + fileName);
			// write converted json data to a file named "CountryGSON.json"
			FileWriter writer = new FileWriter(fileName);
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new ProchainvolException(e);
		}
	}

}
