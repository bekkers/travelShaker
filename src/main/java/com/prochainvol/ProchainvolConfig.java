package com.prochainvol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.sql.airlines.AirlineCompany;
import com.prochainvol.sql.airlines.SqlAirlineReader;
import com.prochainvol.sql.airport.AbstractAirportReader;
import com.prochainvol.sql.airport.Airports;
import com.prochainvol.sql.airport.SqlAirport;
import com.prochainvol.sql.airport.SqlAirportReader;
import com.prochainvol.ui.IAffichable;
import com.prochainvol.ui.IAffichableVisitor;
import com.prochainvol.ui.IEditable;
import com.prochainvol.ui.IEditableVisitor;

public class ProchainvolConfig extends ProchainvolObject implements
		IAffichable, IEditable {

	private static final Logger logger = Logger
			.getLogger(ProchainvolConfig.class.getName());

	private static final String version = Constants.PROCHAINVOL_PROPS.getProperty(
			"version");

	private String user;

	private final EXECUTOR_TYPE executorType;

	private Airports airports;
	private PROVIDER[] currentProviders = { PROVIDER.ODIGEO };
	private Filter currentFlightFilters = new Filter();
	private int maxStop;

	private boolean isAsync;
	
	public ProchainvolConfig()
			throws ProchainvolException {
		this(Constants.DEFAULT_EXECUTOR_TYPE);
	}

	public ProchainvolConfig(EXECUTOR_TYPE executorType)
			throws ProchainvolException {
		this(executorType, Constants.DEFAULT_PROVIDERS);
	}


	
	public ProchainvolConfig(EXECUTOR_TYPE executorType2, 
			PROVIDER[] provider)
			throws ProchainvolException {
		this.executorType = executorType2;
		this.maxStop = Constants.DEFAULT_MAX_STOPS;
		this.currentProviders = provider;

		logger.debug(String
				.format("Creating Prochainvol configuration, requestReaders = %s",
						Arrays.toString(getCurrentProviders())));
		init();
	}
	
	@Override
	public void accept(IAffichableVisitor visitor) {
		visitor.visit(this);
	}

	public void accept(IEditableVisitor visitor) {
		visitor.visit(this);
	}

	public Airports getAirports() {
		return airports;
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

	// public Map<String, ? extends Airport> getAirportListByIata() {
	// return airportListByIata;
	// }


	public String getUser() {
		return user;
	}


	public String getVersion() {
		return version;
	}

	public void init() throws ProchainvolException {

		AbstractAirportReader airportReader = new SqlAirportReader();
		airports = airportReader.load();
//		SqlAirlineReader sqlAirlineReader = new SqlAirlineReader();
//		Map<String, AirlineCompany> sqlAirlineCompanies = sqlAirlineReader
//				.load();


	}

	public boolean isAsync() {
		return isAsync;
	}
	public RequestResult request(
			RequestParams params) throws ProchainvolException {
		List<RequestResultUnit> results = new ArrayList<RequestResultUnit>();
		for (PROVIDER provider : currentProviders) {
			AbstractAirlineService service = createService(provider);
			RequestResult result = service.service(params);
			results.addAll(result.getRequestResultUnits());
		}
		return new RequestResult(results, this);
	}

	public void setAirports(Airports airports) {
		this.airports = airports;
	}



	public void setAsync(boolean isAsync) {
		this.isAsync = isAsync;
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

	private AbstractAirlineService createService(PROVIDER provider)
			throws ProchainvolException {
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
