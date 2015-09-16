package com.prochainvol.sql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.ProchainvolObject;

public abstract class AbstractSqlReader<T1> extends ProchainvolObject {

	public AbstractSqlReader() {
		super();
	}

	public abstract T1 load() throws ProchainvolException;

	public static <T> T read(Class<T> entityClass, int id) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		T airport = null;
		try {
			emf = Persistence.createEntityManagerFactory("airlines");
			em = emf.createEntityManager();
			airport = em.find(entityClass, id);
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();

		}
		return airport;
	}

	public static <T> List<T> query(String query) {
		List<T> listAirports = null;
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("airlines");
			em = emf.createEntityManager();
			Query q2 = em.createQuery(query);
			listAirports = (List<T>) q2.getResultList();
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();

		}
		return listAirports;
	}

	public static <T> void write(T airport) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("airlines");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(airport);
			em.flush();

			em.getTransaction().commit();
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();

		}
	}

}