package com.prochainvol.csv.airport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.prochainvol.Constants;
import com.prochainvol.ReaderUtilities;
import com.prochainvol.sql.AbstractSqlReader;
import com.prochainvol.sql.SqlAirport;
import com.prochainvol.sql.airport.Airports;

import au.com.bytecode.opencsv.CSVReader;

public class OpenflightCsvAirportReader extends AbstractSqlReader<Airports> {

	private static final Logger logger = Logger.getLogger(OpenflightCsvAirportReader.class
			.getName());

	private static final String csvFile = Constants.PROCHAINVOL_PROPS.getProperty("openflightsAirportCsvLocalFile");


	private static Airports airports;

	public static Airports getAirports() {
		if (airports==null) {
			airports = new OpenflightCsvAirportReader().load();
			airports.checkAirports();
		}
		return airports;
	}

	public static SqlAirport getAirport(String iata) {
		Airports airports = getAirports();
		return airports.getAirport(iata);
	}

	@Override
	public Airports load() {
		logger.info("Reading from local csv file : "+csvFile);
		BufferedReader br = null;
		CSVReader reader = null;

	    List<SqlAirport> all = new ArrayList<SqlAirport>();
		try {
			InputStream in = this.getClass().getResourceAsStream(csvFile);
			br = new BufferedReader(new InputStreamReader(in, "UTF8"));
		    reader = new CSVReader(br);
			String[] nextLine;

			int nbCsvLines = 0;
			while ((nextLine = reader.readNext()) != null) {

				nbCsvLines++;
				
				String name = ReaderUtilities.stringClean(nextLine[1]);
				String city = ReaderUtilities.stringClean(nextLine[2]);
				String country = ReaderUtilities.stringClean(nextLine[3]);
				String iata = ReaderUtilities.stringClean(nextLine[4]);
				String icao = ReaderUtilities.stringClean(nextLine[5]);
				icao = icao == null || icao.equals("N") ? "" : icao;
				Float latitude =ReaderUtilities.readFloat(nextLine[6]);
				Float longitude = ReaderUtilities.readFloat(nextLine[7]);
				Float altitude = ReaderUtilities.readFloat(nextLine[8]);

				SqlAirport airport = new SqlAirport(name, city, country, iata, icao);
				airport.setLatitude(latitude);
				airport.setLongitude(longitude);
				airport.setAltitude(altitude);



				airport.setOpenflightsid(ReaderUtilities.readInt(nextLine[0]));
				airport.setTimezone(ReaderUtilities.readFloat(nextLine[9]));
				airport.setDst(ReaderUtilities.stringClean(nextLine[10]));

				all.add(airport);
			}


			reader.close();

		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return new Airports(all);
	}
}
