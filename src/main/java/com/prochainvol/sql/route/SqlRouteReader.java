package com.prochainvol.sql.route;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.prochainvol.sql.AbstractSqlReader;

public class SqlRouteReader extends AbstractSqlReader<Routes> {


	public Routes load() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("airlines");
		EntityManager em = emf.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// read the existing entries
		Query q = em.createQuery("select a from Route a");

		// do we have entries?
		@SuppressWarnings("unchecked")
		List<Route> routes = q.getResultList();

		// Commit the transaction, which will cause the entity to
		// be stored in the database
		em.getTransaction().commit();

		// It is always good practice to close the EntityManager so that
		// resources are conserved.
		em.close();
		return new Routes(routes);

	}

	public Route read(int id) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("airlines");
		EntityManager em = emf.createEntityManager();
		Route route = em.find(Route.class, id);
		em.close();
		emf.close();
		return route;
	}

}
