package com.prochainvol.csv;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.prochainvol.JunitConstants;
import com.prochainvol.csv.airport.AirodysseyCsvAirportReader;
import com.prochainvol.csv.airport.DbpediaCsvAirportReader;
import com.prochainvol.csv.airport.OpenflightCsvAirportReader;
import com.prochainvol.sql.airport.Airports;

public class CsvAirportTest {


		@BeforeClass
		public static void setUpBeforeClass() throws Exception {

		}

		@Test
		public void AirodysseyCsvAirports() {
			try {
				Airports airodyssey = AirodysseyCsvAirportReader.getAirports();
				int size = airodyssey.getAll().size();
				assertEquals(1123 , size);
				System.out.println("Airodyssey size = "+size);
			} catch (Exception e) {
				JunitConstants.reportError(e);
			}

		}

		@Test
		public void OpenflightCsvAirports() {
			try {
				Airports openflight = OpenflightCsvAirportReader.getAirports();
				int size = openflight.getAll().size();
				assertEquals(5879 , size);
				System.out.println("Openflight size = "+size);
			} catch (Exception e) {
				JunitConstants.reportError(e);
			}

		}

		@Test
		public void DbpediaCsvAirports() {
			try {
				Airports dbpedia = DbpediaCsvAirportReader.getAirports();
				int size = dbpedia.getAll().size();
				assertEquals(1279 , size);
				System.out.println("dbpedia size = "+size);
			} catch (Exception e) {
				JunitConstants.reportError(e);
			}

		}


	}
