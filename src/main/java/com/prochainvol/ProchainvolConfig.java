package com.prochainvol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.prochainvol.api.EXECUTOR_TYPE;
import com.prochainvol.api.provider.AbstractAirlineService;
import com.prochainvol.api.provider.PROVIDER;
import com.prochainvol.api.provider.odigeo.OdigeoService;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.request.filter.Filter;
import com.prochainvol.api.request.filter.MaxStopPredicate;
import com.prochainvol.api.response.RequestResult;
import com.prochainvol.api.response.RequestResultUnit;
import com.prochainvol.csv.airlines.OpenflightAirlineReader;
import com.prochainvol.csv.airport.OpenflightCsvAirportReader;
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.sql.SqlAirport;
import com.prochainvol.sql.airlines.AirlineCompanies;
import com.prochainvol.sql.airlines.SqlAirlineReader;
import com.prochainvol.sql.airport.Airports;
import com.prochainvol.sql.airport.SqlAirportReader;
import com.prochainvol.sql.route.Routes;
import com.prochainvol.sql.route.SqlRouteReader;
import com.prochainvol.ui.IAffichable;
import com.prochainvol.ui.IAffichableVisitor;
import com.prochainvol.ui.IEditable;
import com.prochainvol.ui.IEditableVisitor;

public class ProchainvolConfig extends ProchainvolObject implements IAffichable, IEditable {

	private static final Logger logger = Logger.getLogger(ProchainvolConfig.class.getName());

	private final static boolean IS_SQL = sqlIsConnected();

	static private final Airports airports;

	static private final AirlineCompanies airlineCompanies;

	static private final Routes routes;

	static {
		if (IS_SQL) {
			airports = new SqlAirportReader().load();
			List<String> errs = airports.checkAirports();
			logger.info("nb of airports errors = " + errs.size());

			airlineCompanies = new SqlAirlineReader().load();
			errs = airlineCompanies.checkAirlineCompanies();
			logger.info("nb of airlines errors = " + errs.size());

			routes = new SqlRouteReader().load();
			errs = routes.checkRoutes();
			logger.info("nb of airroutes errors = " + errs.size());

		} else {
			airports = new OpenflightCsvAirportReader().load();
			List<String> errs = airports.checkAirports();
			logger.info("nb of airports errors = " + errs.size());
			
			airlineCompanies = new OpenflightAirlineReader().load();
			routes = new Routes();
}
	}

	public static AirlineCompanies getAirlinecompanies() {
		return airlineCompanies;
	}

	public static Airports getAirports() {
		return airports;
	}

	public static Routes getRoutes() {
		return routes;
	}

	private static boolean sqlIsConnected() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		List<SqlAirport> sqlAirports = null;
		boolean result = true;
		try {
			emf = Persistence.createEntityManagerFactory("airlines");
			em = emf.createEntityManager();
		} catch (Exception e) {
			result = false;
		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();

		}
		logger.info("IsSql = " + result);
		return result;
	}

	private String user;
	private boolean async = false;

	private final EXECUTOR_TYPE executorType;
	private PROVIDER[] currentProviders = { PROVIDER.ODIGEO };
	private Filter currentFlightFilters = new Filter();

	private int maxStop;

	public ProchainvolConfig() throws ProchainvolException {
		this(Constants.DEFAULT_EXECUTOR_TYPE);
	}

	public ProchainvolConfig(EXECUTOR_TYPE executorType) throws ProchainvolException {
		this(executorType, Constants.DEFAULT_PROVIDERS);
	}

	public ProchainvolConfig(EXECUTOR_TYPE executorType2, PROVIDER[] provider) throws ProchainvolException {
		this.executorType = executorType2;
		this.currentProviders = provider;
		this.maxStop = Constants.DEFAULT_MAX_STOPS;

		logger.debug(String.format("Creating Prochainvol configuration, requestReaders = %s",
				Arrays.toString(getCurrentProviders())));
	}

	@Override
	public void accept(IAffichableVisitor visitor) {
		visitor.visit(this);
	}

	public void accept(IEditableVisitor visitor) {
		visitor.visit(this);
	}

	public AirlineCompanies getAirlineCompanies() {
		return airlineCompanies;
	}

	public Filter getCurrentFlightFilters() {
		return currentFlightFilters;
	}

	public PROVIDER[] getCurrentProviders() {
		return this.currentProviders;
	}

	public String getDefaultStop() {
		return maxStop < 0 ? "ANY" : Integer.toString(maxStop);
	}

	public EXECUTOR_TYPE getExecutorType() {
		return executorType;
	}

	public int getMaxStop() {
		return maxStop;
	}

	public String getUser() {
		return user;
	}

	public boolean isAsync() {
		return async;
	}

	public RequestResult request(RequestParams params) throws ProchainvolException {
		List<RequestResultUnit> results = new ArrayList<RequestResultUnit>();
		for (PROVIDER provider : currentProviders) {
			AbstractAirlineService service = createService(provider);
			RequestResult result = service.service(params);
			results.addAll(result.getRequestResultUnits());
		}
		return new RequestResult(results, this);
	}

	public void setAsync(boolean async) {
		this.async = async;
	}

	public void setCurrentFlightFilters(Filter flightFlilters) {
		this.currentFlightFilters = flightFlilters;
	}

	public void setCurrentProviders(PROVIDER[] requestReaders) {
		this.currentProviders = requestReaders;
	}

	public void setMaxStop(int maxStop) {
		this.maxStop = maxStop;
	}

	public void setUser(String user) {
		this.user = user;
	}

	private AbstractAirlineService createService(PROVIDER provider) throws ProchainvolException {
		AbstractAirlineService service = null;
		switch (provider) {
		case ODIGEO:
			service = new OdigeoService(this);
		}
		return service;
	}

	private void initFiterPredicates(int i) {
		if (i >= 0) {
			currentFlightFilters.addPredicate(new MaxStopPredicate(i));
		}
	}

}
