package com.prochainvol.csv.airlines;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.prochainvol.ReaderUtilities;
import com.prochainvol.sql.AbstractSqlReader;
import com.prochainvol.sql.airlines.AirlineCompanies;
import com.prochainvol.sql.airlines.AirlineCompany;

import au.com.bytecode.opencsv.CSVReader;

public class CensusGovAirlineReader extends AbstractSqlReader<AirlineCompanies> {

	private static final Logger logger = Logger
			.getLogger(CensusGovAirlineReader.class.getName());

	private static final String csvFile = "/csv/airCarrierNameCensusGov.csv";

	private static AirlineCompanies airlineCompanies;

	public static AirlineCompanies getAirlineCompanies() {
		if (airlineCompanies==null) {
			airlineCompanies = new CensusGovAirlineReader().load();
			airlineCompanies.checkAirlineCompanies();
		}
		return airlineCompanies;
	}

	public static AirlineCompany getAirport(String iata) {
		getAirlineCompanies();
		return airlineCompanies.getAirCompany(iata);
	}
	
	public CensusGovAirlineReader() {
		super();
	}

	@Override
	public AirlineCompanies load() {
		List<AirlineCompany>all = new ArrayList<AirlineCompany>();
		BufferedReader br = null;

		try {
			InputStream in = this.getClass().getResourceAsStream(csvFile);

			br = new BufferedReader(new InputStreamReader(in, "UTF8"));
			CSVReader reader = new CSVReader(br,',','"',4);
			String[] nextLine;


			int total = 0;
			while ((nextLine = reader.readNext()) != null) {
				AirlineCompany airline = new AirlineCompany();
				String name = ReaderUtilities.stringClean(nextLine[0]);
				airline.setName(name);

				String IATA = ReaderUtilities.stringClean(nextLine[1]);
				airline.setIata(IATA);

				String ICAO = null;
				if (nextLine.length > 2) {
					ICAO = ReaderUtilities.stringClean(nextLine[2]);
					airline.setIcao(ICAO);
				}
				airline.setActive("Y");
				all.add(airline);
				total++;

			}

			reader.close();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return new AirlineCompanies(all);
	}


}
