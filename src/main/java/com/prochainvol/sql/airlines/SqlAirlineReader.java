package com.prochainvol.sql.airlines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

public class SqlAirlineReader extends AbstractAirlineReader {

	private static final Logger logger = Logger
			.getLogger(SqlAirlineReader.class.getName());

	private static final int[] excludedIds = {2988,4264,146,5533,3384,1828,4560,18702,3001,5584,5651};

	private static boolean isExcluded(int id) {
		boolean excluded = false;
		for (int excludedId :excludedIds) {
			if (id==excludedId) {
				excluded = true;
				break;
			}
		}
		return excluded;
	}

	public SqlAirlineReader() {
		super(AIRLINE_READER.SQL);
	}

	@Override
	public Map<String, AirlineCompany> load() {
		Map<String, List<AirlineCompany>> all = new HashMap<String, List<AirlineCompany>>();
		Map<String, List<AirlineCompany>> allName = new HashMap<String, List<AirlineCompany>>();
		Map<String, List<AirlineCompany>> allByICAO = new HashMap<String, List<AirlineCompany>>();
		Map<String, List<AirlineCompany>> allByIATA = new HashMap<String, List<AirlineCompany>>();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("airlines");
		EntityManager em = emf.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// read the existing entries
		Query q = em.createQuery("select a from AirlineCompany a");

		// do we have entries?
		@SuppressWarnings("unchecked")
		List<AirlineCompany> airlines = q.getResultList();
		int activeNull = 0;
		int activeYes = 0;
		int activeNo = 0;

		int total = 0;
		for (AirlineCompany airline : airlines) {
			airline.nullifyEmptyAttribute();
			String active = airline.getActive();
			int openfligthId = airline.getOpenflightId();
			if (active != null && !isExcluded(openfligthId)) {
				total++;
				String name = airline.getName();
				if (active.equals("Y")) {
					activeYes++;
				} else if (active.equals("N")) {
					activeNo++;
				} else {
					System.out.println("active ?? : " + airline);
				}
				String key = airline.getKey();
				
				if (openfligthId==4896) {
					airline.setIata("HQ");  // http://www.americas-fr.com/voyages/informations/thomas-cook-airlines.html
					airline.setName("Thomas Cook Airlines Belgium"); 
				}

				
				List<AirlineCompany> previousList = all.get(key);
				if (previousList == null) {
					previousList = new ArrayList<AirlineCompany>();
					all.put(key, previousList);
				}
				previousList.add(airline);

				if (name == null) {
					System.out.println("name ?? : " + airline);
				} else {
					List<AirlineCompany> previousNameList = allName.get(name);
					if (previousNameList == null) {
						previousNameList = new ArrayList<AirlineCompany>();
						allName.put(name, previousNameList);
					}
					previousNameList.add(airline);
				}

				String ICAO = airline.getIcao();
				List<AirlineCompany> previousByIcao = allByICAO.get(ICAO);
				if (previousByIcao == null) {
					previousByIcao = new ArrayList<AirlineCompany>();
					allByICAO.put(ICAO, previousByIcao);
				}
				previousByIcao.add(airline);

				String IATA = airline.getIata();
				List<AirlineCompany> previousByIata = allByIATA.get(IATA);
				if (previousByIata == null) {
					previousByIata = new ArrayList<AirlineCompany>();
					allByIATA.put(IATA, previousByIata);
				}
				previousByIata.add(airline);
}
		}
		em.getTransaction().commit();
		rapport.append(String.format("\n\nnb d'entrées : %s", airlines.size()));
		rapport.append(String.format("\n\nnb Total conservé : %s", total));
		rapport.append(String.format("\n\nnb key : %s", all.size()));

		rapport.append(String.format("\nactive : %s", activeYes));
		rapport.append(String.format("\nnon active : %s", activeNo));
		rapport.append(String.format("\nnull active : %s", activeNull));

		rapport.append(String.format("\nnb names : %d", allName.size()));

		long nbDuplicatedKey = all.values().stream()
				.filter(x -> x.size() > 1).count();
		rapport.append(String.format("\nnb Duplicated key : %d", nbDuplicatedKey));
		
		long nbDuplicatedNames = allName.values().stream()
				.filter(x -> x.size() > 1).count();
		rapport.append(String.format("\nnb Duplicated Names : %d", nbDuplicatedNames));

		long nbDuplicatedIcao = allByICAO.values().stream()
				.filter(x -> x.size() > 1).count();
		rapport.append(String.format("\nnb Duplicated ICAO : %d", nbDuplicatedIcao));

		long nbDuplicatedIata = allByIATA.values().stream()
				.filter(x -> x.size() > 1).count();
		rapport.append(String.format("\nnb Duplicated IATA : %d", nbDuplicatedIata));

		String duplicatedKey = all.values().stream().filter(x -> x.size() > 1)
				.map(x -> {
					String iata = x.get(0).getIata();
					String diff = "";
					try {
						diff = x.get(0).diff(x.get(1));
						diff = diff==null ? "" : diff;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return String.format("%s/%d : %s", iata, x.size(), diff);
				}).sorted().collect(Collectors.joining(",\n", "[\n", "\n]"));
		rapport.append("\n\nDuplicated iata : ").append(duplicatedKey);

		String duplicatedNames = allName.values().stream()
				.filter(x -> x.size() > 1).map(x -> {
					String name = x.get(0).getName();
					String diff = "";
					try {
						diff = x.get(0).diff(x.get(1));
						diff = diff==null ? "" : diff;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return String.format("%s/%d : \n%s", name, x.size(), diff);
				}).sorted().collect(Collectors.joining(",\n", "[\n", "\n]"));
		rapport.append("\n\nDuplicated names : ").append(duplicatedNames);

		String duplicatedIcao = allByICAO.values().stream().filter(x -> x.size() > 1)
				.map(x -> {
					String icao = x.get(0).getIcao();
					String diff = "";
					try {
						diff = x.get(0).diff(x.get(1));
						diff = diff==null ? "" : diff;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return String.format("%s/%d : %s", icao, x.size(), diff);
				}).sorted().collect(Collectors.joining(",\n", "[\n", "\n]"));
		rapport.append("\n\nDuplicated icao : ").append(duplicatedIcao);

		String duplicatedIata = allByIATA.values().stream().filter(x -> x.size() > 1)
				.map(x -> {
					String iata = x.get(0).getIata();
					String diff = "";
					try {
						diff = x.get(0).diff(x.get(1));
						diff = diff==null ? "" : diff;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return String.format("%s/%d : %s", iata, x.size(), diff);
				}).sorted().collect(Collectors.joining(",\n", "[\n", "\n]"));
		rapport.append("\n\nDuplicated iata : ").append(duplicatedIata);

		// It is always good practice to close the EntityManager so that
		// resources are conserved.
		em.close();

		Map<String, AirlineCompany> result = all.values().stream().map(x -> x.get(0))
				.collect(Collectors.toMap(x -> x.getKey(), x -> x));
		return result;
	}


	public AirlineCompany read(int id) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("airlines");
		EntityManager em = emf.createEntityManager();
		AirlineCompany airline = em.find(AirlineCompany.class, id);
		em.close();
		emf.close();
		return airline;
	}

	public void store(List<AirlineCompany> airlines) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("airlines");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		// Query query = em.createNativeQuery("DELETE FROM airlines");
		// query.executeUpdate();

		for (AirlineCompany airline : airlines) {
			em.persist(airline);
		}
		logger.trace("end persist");
		em.getTransaction().commit();
		logger.trace("end commit");
		em.close();
		logger.trace("end em.close()");
		emf.close();
		logger.trace("end emf.close()");
	}

}
