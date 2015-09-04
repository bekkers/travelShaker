package com.prochainvol.sql.airlines;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.sql.AbstractSqlReader;

public class SqlAirlineReader extends AbstractSqlReader<AirlineCompanies> {

	private static final Logger logger = Logger
			.getLogger(SqlAirlineReader.class.getName());



	@Override
	public AirlineCompanies load() {
		final String msg = "Reading airport persistent entities from Prochainvol DB via JPA EntityManager ";
		logger.info(msg);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("airlines");
		EntityManager em = null;
		List<AirlineCompany> sqlAirlineCompanies = null;
		try {
			em = emf.createEntityManager();
			// Begin a new local transaction so that we can persist a new entity
			em.getTransaction().begin();
	
			// read the existing entries
			Query q = em.createQuery("select a from AirlineCompany a");

			sqlAirlineCompanies = (List<AirlineCompany>) q.getResultList();
		} catch (PersistenceException | IllegalStateException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw e;
		} finally {
			if (em!=null)
				em.close();

		}
		
		int nbLines = sqlAirlineCompanies.size();
		logger.info("Nblines  = "+nbLines);

		return new AirlineCompanies(sqlAirlineCompanies);

	}

}
