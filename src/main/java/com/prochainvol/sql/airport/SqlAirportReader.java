package com.prochainvol.sql.airport;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.sql.AbstractSqlReader;
import com.prochainvol.sql.SqlAirport;

public class SqlAirportReader extends AbstractSqlReader<Airports> {

	private static final Logger logger = Logger
			.getLogger(SqlAirportReader.class.getName());

	@Override
	@SuppressWarnings("unchecked")
	public Airports load() {
		final String msg = "Reading airport persistent entities from Prochainvol DB via JPA EntityManager ";
		logger.info(msg);
		EntityManagerFactory emf = null;
		EntityManager em = null;
		List<SqlAirport> sqlAirports = null;
		try {
			emf = Persistence
					.createEntityManagerFactory("airlines");
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
			if (emf!=null)
				emf.close();

		}

//		List<Airport> airportList = sqlAirports.stream().map(x -> x.asAirport()).collect(Collectors.toList());

		
		int nbLines = sqlAirports.size();
		logger.info("Nblines  = "+nbLines);

		return new Airports(sqlAirports);

	}

	public static SqlAirport  read(int id) {
		return AbstractSqlReader.read(SqlAirport.class, id);
	}
	
	public static SqlAirport readByIata(String iata) {
		List<SqlAirport> list = AbstractSqlReader.query("select a from SqlAirport a WHERE a.iata='" + iata + "'");
		if (list.size()>1) {
			logger.warn("oops, plusieurs airports avec le même iata : "+iata);
		}
		return list.get(0);
	}


	public static void write(SqlAirport airport) {
		AbstractSqlReader.write(airport);
		ProchainvolConfig.getAirports().addAirport(airport);
	}

}
