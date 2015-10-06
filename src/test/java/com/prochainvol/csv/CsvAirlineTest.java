package com.prochainvol.csv;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.prochainvol.JunitConstants;
import com.prochainvol.csv.airlines.CensusGovAirlineReader;
import com.prochainvol.csv.airlines.OpenflightAirlineReader;
import com.prochainvol.csv.airport.DbpediaCsvAirportReader;
import com.prochainvol.sql.airlines.AirlineCompanies;
import com.prochainvol.sql.airport.Airports;

public class CsvAirlineTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Test
	public void OpenflightCsvAirlineCompanies() {
		try {
			AirlineCompanies companies = OpenflightAirlineReader.getAirlineCompanies();
			int size = companies.getAll().size();
			assertEquals(939, size);
			System.out.println("Openflight Companies size = " + size);
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

	@Test
	public void DbpediaCsvAirlineCompanies() {
		try {
			AirlineCompanies companies = CensusGovAirlineReader.getAirlineCompanies();
			int size = companies.getAll().size();
			 assertEquals(797 , size);
			System.out.println("CensusGov size = " + size);
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

}
