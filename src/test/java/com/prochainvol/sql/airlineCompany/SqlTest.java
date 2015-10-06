package com.prochainvol.sql.airlineCompany;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.prochainvol.JunitConstants;
import com.prochainvol.sql.airlines.AirlineCompanies;
import com.prochainvol.sql.airlines.AirlineCompany;
import com.prochainvol.sql.airlines.SqlAirlineReader;

public class SqlTest {


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Test
	public void testDebugAirlines() {
		try {
			DebugAirlineCompanyReader debugAirportReader = new DebugAirlineCompanyReader();
			AirlineCompanies airlines = debugAirportReader.load();
			List<String> listBugs = airlines.checkAirlineCompanies();
			assertEquals(0, listBugs.size());
			assertEquals("Ryanair", airlines.getAirCompany("FR").getName());


		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}


	@Test
	public void testAirlinessFromConfig() {
		try {

		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

	@Test
	public void testSGBDAirlineCompany() {
		try {
			AirlineCompanies AirlineCompanies = new SqlAirlineReader().load();
			System.out.println("nb airline companies = "+AirlineCompanies.getAll().size());
			List<String> listBugs = AirlineCompanies.checkAirlineCompanies();
			System.out.println("nb bugs =" + listBugs.size());
			Map<String, List<AirlineCompany>> all = AirlineCompanies.getAll();
			for (String iata : listBugs) {
				System.out.println("\n*** "+iata+" ***");
				for (AirlineCompany company : all.get(iata)) {
					System.out.println(company);					
				}
			}
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

	@Test
	public void testOpenflightCsvAirlineCompany() {
		try {
			AirlineCompanies AirlineCompanies = new SqlAirlineReader().load();
			System.out.println("nb airline companies = "+AirlineCompanies.getAll().size());
			List<String> listBugs = AirlineCompanies.checkAirlineCompanies();
			System.out.println("nb bugs =" + listBugs.size());
			Map<String, List<AirlineCompany>> all = AirlineCompanies.getAll();
			for (String iata : listBugs) {
				System.out.println("\n*** "+iata+" ***");
				for (AirlineCompany company : all.get(iata)) {
					System.out.println(company);					
				}
			}
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

}
