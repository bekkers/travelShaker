package com.prochainvol.csv.airport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolException;
import com.prochainvol.ReaderUtilities;
import com.prochainvol.sql.RailwayStation;
import com.prochainvol.sql.RailwayStations;

import au.com.bytecode.opencsv.CSVReader;

/**
 * from https://www.google.com/fusiontables/DataSource?docid=1DY7TLusym-
 * ZAk5odXdklOjY49Uq_btHhUU9Aqc_9#rows:id=1
 */

public class RailwayStationCsvReader {

	private static final Logger logger = Logger
			.getLogger(RailwayStationCsvReader.class.getName());

	private static final String RAILWAY_STATION_CSV_LOCAL_FILE = "/csv/railway.csv";
	public static final String source[] = {
			"https://www.google.com/fusiontables/DataSource?docid=1DY7TLusym-ZAk5odXdklOjY49Uq_btHhUU9Aqc_9#rows:id=1",
			"http://en.wikipedia.org/wiki/List_of_IATA-indexed_railway_stations" };

	public static final String regExpr = "^([\\-]?[0-9]*),([0-9]*)$";

	public static String getString(InputStreamReader is) throws IOException {
		int ch;
		StringBuilder sb = new StringBuilder();
		while ((ch = is.read()) != -1)
			sb.append((char) ch);
		return sb.toString();
	}

	public RailwayStations load() throws ProchainvolException {
		Map<String, RailwayStation> all = new HashMap<String, RailwayStation>();
		final String msg = "Reading from local file : "
				+ RAILWAY_STATION_CSV_LOCAL_FILE;
		logger.info(msg);
		InputStream in = this.getClass().getResourceAsStream(
				RAILWAY_STATION_CSV_LOCAL_FILE);
		CSVReader reader = null;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(in, "UTF8"));
			reader = new CSVReader(br);
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {

				/*
				 * protected String iata; protected String icao; protected
				 * String city; protected String city_fr; protected Country
				 * country; protected String countryName_en; protected String
				 * countryName_fr; protected String name; protected String
				 * name_fr; protected String longName; protected String
				 * longName_fr; protected String administrativeDivision;
				 */

				String iata = ReaderUtilities.stringClean(nextLine[0]);
				String longName = ReaderUtilities.stringClean(nextLine[1]);
				String longNameFr = ReaderUtilities.stringClean(nextLine[2]);
				String name = ReaderUtilities.stringClean(nextLine[3]);
				String nameFr = ReaderUtilities.stringClean(nextLine[4]);
				String city = ReaderUtilities.stringClean(nextLine[5]);
				String cityFr = ReaderUtilities.stringClean(nextLine[6]);
				String administrativeDivision = ReaderUtilities
						.stringClean(nextLine[7]);
				String countryNameEn = ReaderUtilities.stringClean(nextLine[8]);
				String urlWikipedia = ReaderUtilities.stringClean(nextLine[9]);
				String urlGeo = ReaderUtilities.stringClean(nextLine[10]);
				String lattitude = ReaderUtilities.stringClean(nextLine[11]);
				String longitude = ReaderUtilities.stringClean(nextLine[12]);
				boolean isAirportStation = false;
				if (nextLine.length > 13) {
					String isA = ReaderUtilities.stringClean(nextLine[13]);
					if (isA.equals("A")) {
						isAirportStation = true;
						iata = iata+"-A";
					}
				}
				longName = String.format("%s (%s)", longName, iata);
				longNameFr = String.format("%s (%s)", longNameFr, iata);
				if (lattitude!=null) {
					lattitude = lattitude.replaceAll(regExpr, "$1.$2");
				}
				if (longitude!=null) {
					longitude = longitude.replaceAll(regExpr, "$1.$2");
				}
				Float lat = null;
				Float lon = null;
				try {
					if (lattitude != null && lattitude.length() > 0) {
						lat = Float.parseFloat(lattitude);
					}
					if (longitude != null && longitude.length() > 0) {
						lon = Float.parseFloat(longitude);
					}
				} catch (NumberFormatException e) {
					logger.error(e);
					throw new ProchainvolException(e);
				}
				RailwayStation railwayStation = new RailwayStation(iata,
						longName, longNameFr, name, nameFr, city, cityFr,
						administrativeDivision, countryNameEn, urlWikipedia,
						urlGeo, lat, lon, isAirportStation);
				// System.out.println(railwayStation);
				all.put(iata, railwayStation);
			}
		} catch (IOException e) {
			logger.error(e);
			throw new ProchainvolException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				logger.error(e);
				throw new ProchainvolException(e);
			}
		}

		return new RailwayStations(all);
	}

}
