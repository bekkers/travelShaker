package com.prochainvol.sql.route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.prochainvol.IDataReader;
import com.prochainvol.ReaderUtilities;

public class RouteReaderSql implements IDataReader<Route> {

	private static Map<String, Route> all = new HashMap<String, Route>();

	public static Map<String, Route> getAll() {
		return all;
	}

	public static void main(String[] args) {
		IDataReader<Route> routeReader = new RouteReaderSql();
		int size = routeReader.load().size();
		System.out.println("Table length : "+size);
	}

	public Map<String, Route> load() {
		all = new HashMap<String, Route>();
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
		int i = 0;
		for (Route route : routes) {
			String routeId = ReaderUtilities.getRouteId(route);
			if (all.get(routeId)!=null) {
				i++;
				if (i<10) {
					System.out.println(route+" ; "+all.get(routeId));
				}
			} else {
				all.put(routeId, route);
			}
		}

		// Commit the transaction, which will cause the entity to
		// be stored in the database
		em.getTransaction().commit();

		// It is always good practice to close the EntityManager so that
		// resources are conserved.
		em.close();
		return all;

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
