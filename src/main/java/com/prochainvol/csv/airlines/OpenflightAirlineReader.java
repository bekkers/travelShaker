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

public class OpenflightAirlineReader extends AbstractSqlReader<AirlineCompanies> {

	private static final Logger logger = Logger
			.getLogger(OpenflightAirlineReader.class.getName());

	private static final String csvFile = "/csv/openflightsAirlines.csv";
	
	private static AirlineCompanies airlineCompanies;

	public static AirlineCompanies getAirlineCompanies() {
		if (airlineCompanies==null) {
			airlineCompanies = new OpenflightAirlineReader().load();
			airlineCompanies.checkAirlineCompanies();
		}
		return airlineCompanies;
	}

	public static AirlineCompany getAirport(String iata) {
		getAirlineCompanies();
		return airlineCompanies.getAirCompany(iata);
	}
	
	public OpenflightAirlineReader() {
		super();
	}

	@Override
	public AirlineCompanies load() {
		logger.info("Reading from local csv file : "+csvFile);
		List<AirlineCompany> all = new ArrayList<AirlineCompany>();
		BufferedReader br = null;

		try {
			InputStream in = this.getClass().getResourceAsStream(csvFile);

			br = new BufferedReader(new InputStreamReader(in, "UTF8"));
			CSVReader reader = new CSVReader(br);
			String[] nextLine;

			int total = 0;
			while ((nextLine = reader.readNext()) != null) {

				AirlineCompany airline = new AirlineCompany();
				int openfligthId = ReaderUtilities.readInt(nextLine[0]);
				airline.setOpenflightId(openfligthId);
				String name = ReaderUtilities.stringClean(nextLine[1]);
				if (openfligthId==4896) {
					name = "Thomas Cook Airlines Belgium"; // http://fr.wikipedia.org/wiki/Thomas_Cook_Airlines_Belgium
				}
				airline.setName(name);
				String alias = ReaderUtilities.stringClean(nextLine[2]);
				if (alias!=null && alias.equals("N"))  {
					alias = null;
				}
				airline.setAlias(alias);

				String IATA = ReaderUtilities.stringClean(nextLine[3]);
				if (openfligthId==4374) { // Sky Express http://aviation-safety.net/database/operator/airline.php?var=8317
					IATA="GQ";
				}
				IATA = IATA!=null && IATA.equals("N") ? "" : IATA;
				airline.setIata(IATA);
				if (openfligthId==4896) {
					IATA = "HQ";  // http://www.americas-fr.com/voyages/informations/thomas-cook-airlines.html
				}

				String ICAO = ReaderUtilities.stringClean(nextLine[4]);
				ICAO = ICAO!=null && ICAO.equals("N") ? "" : ICAO;
				airline.setIcao(ICAO);

				String callSign = ReaderUtilities.stringClean(nextLine[5]);
				airline.setCallsign(callSign);

				if (openfligthId==68) {   // http://en.wikipedia.org/wiki/Air_Tindi
					ICAO = "TID";
					callSign ="DISCOVERY";
				}

				airline.setCountry(ReaderUtilities.stringClean(nextLine[6]));
				airline.setActive(ReaderUtilities.stringClean(nextLine[7]));
				all.add(airline);

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
