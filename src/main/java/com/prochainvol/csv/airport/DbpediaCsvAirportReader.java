package com.prochainvol.csv.airport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.prochainvol.Constants;
import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.sql.AbstractSqlReader;
import com.prochainvol.sql.SqlAirport;
import com.prochainvol.sql.airport.Airports;

import au.com.bytecode.opencsv.CSVReader;

public class DbpediaCsvAirportReader extends AbstractSqlReader<Airports> {

	private static final Logger logger = Logger.getLogger(DbpediaCsvAirportReader.class
			.getName());


	private static final String csvFile = Constants.PROCHAINVOL_PROPS.getProperty("dbpediaAirportCsvLocalFile");

	private static Airports airports;

	public static Airports getAirports() {
		if (airports==null) {
			airports = new DbpediaCsvAirportReader().load();
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
		final String msg = "Reading from local csv file : "+csvFile;
		logger.info(msg);
		BufferedReader br = null;
	    List<SqlAirport> all = new ArrayList<SqlAirport>();
		String[] nextLine = null;;
		int lineNumber = 0;
		String coordinates =  null;
		CSVReader reader = null;
		try {
			InputStream in = this.getClass().getResourceAsStream(csvFile);
			br = new BufferedReader(new InputStreamReader(in));
	
			reader = new CSVReader(br);


			reader.readNext(); // read the headers line
			while ((nextLine = reader.readNext()) != null) {
				lineNumber++;

				String name = nextLine[0];
				String icao = nextLine[1];
				String iata = nextLine[2];
//				System.out.println(String.format("name = %s, iata = %s", name,iata));
				coordinates =  nextLine[3];
				String city = nextLine[4];
				String country = nextLine[5];
//				String ressourceName = nextLine[6];
				String[] latLon = coordinates.split(" ");
				
				// TODO récupérer l'altitude sur dbpedia
				SqlAirport airport = new SqlAirport(name, city, country, iata, icao); 
				airport.setLatitude(Float.parseFloat(latLon[0]));
				airport.setLongitude(Float.parseFloat(latLon[1]));
				all.add(airport);

			}

		} catch (NumberFormatException e) {
			String mess = String.format("convertion float impossible at line %d, string = %s, line = %s", lineNumber, coordinates, Arrays.toString(nextLine));
			logger.fatal(mess, e);
		} catch (IOException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return new Airports(all);
	}


}
