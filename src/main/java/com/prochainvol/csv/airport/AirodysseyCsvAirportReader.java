package com.prochainvol.csv.airport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.prochainvol.Constants;
import com.prochainvol.sql.AbstractSqlReader;
import com.prochainvol.sql.SqlAirport;
import com.prochainvol.sql.airport.Airports;

import au.com.bytecode.opencsv.CSVReader;

// source http://airodyssey.net/reference/airports/
public class AirodysseyCsvAirportReader extends AbstractSqlReader<Airports> {

	private static final Logger logger = Logger.getLogger(AirodysseyCsvAirportReader.class
			.getName());

	private static final String csvFile = Constants.PROCHAINVOL_PROPS.getProperty("airodysseyAirportCsvLocalFile");
	
	private static Airports airports;

	public static Airports getAirports() {
		if (airports==null) {
			airports = new AirodysseyCsvAirportReader().load();
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
		CSVReader reader = null;

	    List<SqlAirport> all = new ArrayList<SqlAirport>();
		try {
			InputStream in = this.getClass().getResourceAsStream(csvFile);
			br = new BufferedReader(new InputStreamReader(in, "UTF8"));
		    reader = new CSVReader(br);
		    String [] nextLine;
		    
			int i = 0;
		    while ((nextLine = reader.readNext()) != null) {
				i++;

				String iata = nextLine[0];
				String icao = nextLine[1];
				if (icao.equals("****")) {
					icao = null;
				}
				
				String city = nextLine[2];
				String country = nextLine[3];
				
				String name = null;
				if (nextLine.length > 4) {
					name = nextLine[4];
				} else {
					name = "\\N";
				}
				SqlAirport airport = new SqlAirport(name, city, country, iata, icao);
				all.add(airport);

			}
		    
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
