package com.prochainvol.api.provider.odigeo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import org.apache.log4j.Logger;

import com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearch;
import com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearchService;
import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchResponse;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.Constants;
import com.prochainvol.ProchainvolException;
import com.prochainvol.json.JsonUtilities;

public class OdigeoExecutorDirectAsync implements InterfaceOdigeoStreamExecutor {

	private static final Logger logger = Logger
			.getLogger(OdigeoExecutorDirectAsync.class.getName());

	private final List<SearchStatusResponse> result = new ArrayList<SearchStatusResponse>();
	private final List<Exception> exceptions = new ArrayList<Exception>();
	private int counter;

	@Override
	public List<SearchStatusResponse> execService(Map<String, Search> searchList)
			throws ProchainvolException {
		counter = searchList.size();
		try {
			ItinerarySearch itinerarySearch = new ItinerarySearch(new URL(
					Constants.ODIGEO_WSDL_URL),
					Constants.ODIGEO_SERVICE_NAME);
			ItinerarySearchService service = itinerarySearch
					.getItinerarySearchServicePort();
			for (Search search : searchList.values()) {
				AsyncHandler<SearchResponse> asyncHandler = new AsyncHandler<SearchResponse> () {
					
					@Override
					public void handleResponse(Response<SearchResponse> res) {
						putResponse(res);
						
					}

				};
				service.searchAsync(
						search.getPreferences(), search.getSearchRequest(),
						search.getMetasearchEngineCode(), asyncHandler);
			}
		} catch (MalformedURLException e) {
			logger.error(e);
			throw new ProchainvolException(e);
		}

		while (true) {
			if (isFinished()) {
				break;
			}
		}
		if (exceptions.size()>0) {
			String mess = "Execution interrompue";
			logger.error(mess, exceptions.get(0));
			throw new ProchainvolException(mess);
		}
		logger.debug("execService response is : "
				+ JsonUtilities.getGsonPretty().toJson(result));
		return result;
	}

	private synchronized boolean isFinished() {
		return counter == 0;
	}

		
	private synchronized void putResponse(Response<SearchResponse> res) {
		SearchResponse response;
		try {
			response = res.get();
			result.add(response.getSearchStatus());
		} catch (Exception e) {
			logger.error(e);
			exceptions.add(e);
		} finally {
			counter= counter-1;
		}
	}
}
