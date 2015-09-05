package com.prochainvol.sql.airport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.Test;

import com.prochainvol.JunitConstants;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.json.JsonUtilities;

public class SqlTest {

	static final int idAgen = 128;
	static final String iataAgen = "AGF";
	static final String iataMarseille = "MRS";
	static final String iataOrly = "ORY";
	static final String iataCDG = "CDG";
	static final String iataLOndonAll = "LON";
	static final String cityMarseille = "Marseille";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Test
	public void testGetAirport() {
		try {
			//ProchainvolConfig config = new ProchainvolConfig();
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

	@Test
	public void testDebugAirports() {
		try {
			TobeAddedAirportReader debugAirportReader = new TobeAddedAirportReader();
			Airports airports = debugAirportReader.load();
			List<String> listBugs = airports.checkAirports();
			assertEquals(1, listBugs.size());
			System.out.println("airports =" + airports);


		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}


	@Test
	public void testAirlines() {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("airlines");
			EntityManager em = emf.createEntityManager();
			// Begin a new local transaction so that we can persist a new entity
			em.getTransaction().begin();

			// read by id : Agen la Garenne "AGF"
			SqlAirport agen = em.find(SqlAirport.class, idAgen);
			assertNotNull(agen);
			// System.out.println("Agen la Garenne = " + agen);
			assertEquals(iataAgen, agen.getIata());

			// read by iata : marseilleProvence
			Query q1 = em.createQuery("select a from SqlAirport a WHERE a.iata='" + iataMarseille + "'");
			List<SqlAirport> sqlAirports1 = (List<SqlAirport>) q1.getResultList();
			assertEquals(1, sqlAirports1.size());
			SqlAirport marseille = sqlAirports1.get(0);
			// System.out.println("Marseille Provence=" + marseille);
			assertEquals(cityMarseille, marseille.getCity());

			// read by iata : Orly
			Query q2 = em.createQuery("select a from SqlAirport a WHERE a.iata='" + iataOrly + "'");
			List<SqlAirport> sqlAirports2 = (List<SqlAirport>) q2.getResultList();
			assertEquals(1, sqlAirports2.size());
			SqlAirport orly = sqlAirports2.get(0);
			// System.out.println("orly=" + orly);

			// read by iata : cdg
			Query q3 = em.createQuery("select a from SqlAirport a WHERE a.iata='" + iataCDG + "'");
			List<SqlAirport> sqlAirports3 = (List<SqlAirport>) q3.getResultList();
			assertEquals(1, sqlAirports3.size());
			SqlAirport cdg = sqlAirports3.get(0);
			// System.out.println("cdg=" + cdg);

			// read by iata : london all
			Query q4 = em.createQuery("select a from SqlAirport a WHERE a.iata='" + iataLOndonAll + "'");
			List<SqlAirport> sqlAirports4 = (List<SqlAirport>) q4.getResultList();
			assertEquals(1, sqlAirports4.size());
			SqlAirport london = sqlAirports4.get(0);
			// System.out.println("london=" + london);

			// read by iata : tulza
			Query q5 = em.createQuery("select a from SqlAirport a WHERE a.iata='YMU'");
			List<SqlAirport> sqlAirports5 = (List<SqlAirport>) q5.getResultList();
			assertEquals(1, sqlAirports5.size());
			SqlAirport Mansons = sqlAirports5.get(0);
			// System.out.println("Mansons Landing=" + Mansons);

			SqlAirport[] airports = { agen, marseille, orly, cdg, london, Mansons };
			System.out.println("airports=" + JsonUtilities.getGsonPretty().toJson(airports));

			em.getTransaction().commit();
			// It is always good practice to close the EntityManager so that
			// resources are conserved.
			em.close();
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

	@Test
	public void testAirport() {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("airlines");
			EntityManager em = emf.createEntityManager();
			// Begin a new local transaction so that we can persist a new entity
			em.getTransaction().begin();

			// read by id : Agen la Garenne "AGF"
			SqlAirport agen = em.find(SqlAirport.class, idAgen);
			assertNotNull(agen);
			// System.out.println("Agen la Garenne = " + agen);
			assertEquals(iataAgen, agen.getIata());

			// read by iata : marseilleProvence
			Query q1 = em.createQuery("select a from SqlAirport a WHERE a.iata='" + iataMarseille + "'");
			List<SqlAirport> sqlAirports1 = (List<SqlAirport>) q1.getResultList();
			assertEquals(1, sqlAirports1.size());
			SqlAirport marseille = sqlAirports1.get(0);
			// System.out.println("Marseille Provence=" + marseille);
			assertEquals(cityMarseille, marseille.getCity());

			em.getTransaction().commit();
			// It is always good practice to close the EntityManager so that
			// resources are conserved.
			em.close();
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

}
