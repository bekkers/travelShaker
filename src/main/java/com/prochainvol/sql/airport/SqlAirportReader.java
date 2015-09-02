package com.prochainvol.sql.airport;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolUtilities;

public class SqlAirportReader extends AbstractAirportReader {

	private static final Logger logger = Logger
			.getLogger(SqlAirportReader.class.getName());

	public SqlAirportReader() {
		super();
	}

	@SuppressWarnings("unchecked")
	public Airports load() {
		final String msg = "Reading airport persistent entities from Prochainvol DB via JPA EntityManager ";
		logger.info(msg);
		rapport = new TravelplaceReaderReport();
		rapport.start();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("airlines");
		EntityManager em = null;
		List<SqlAirport> sqlAirports = null;
		try {
			em = emf.createEntityManager();
			// Begin a new local transaction so that we can persist a new entity
			em.getTransaction().begin();
	
			// read the existing entries
			Query q = em.createQuery("select a from SqlAirport a");

			sqlAirports = (List<SqlAirport>) q.getResultList();
		} catch (PersistenceException | IllegalStateException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw e;
		} finally {
			if (em!=null)
				em.close();

		}

//		List<Airport> airportList = sqlAirports.stream().map(x -> x.asAirport()).collect(Collectors.toList());

		
		int nbLines = sqlAirports.size();
		rapport.setSizeEntry(nbLines);			
		logger.info("Nblines  = "+nbLines);

		return new Airports(sqlAirports);

	}

	public SqlAirport read(int id) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("airlines");
		EntityManager em = emf.createEntityManager();
		SqlAirport airport = em.find(SqlAirport.class, id);
		em.close();
		emf.close();
		return airport;
	}


}
