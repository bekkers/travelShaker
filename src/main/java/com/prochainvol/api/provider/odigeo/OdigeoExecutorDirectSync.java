package com.prochainvol.api.provider.odigeo;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearch;
import com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearchService;
import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.Constants;
import com.prochainvol.ProchainvolException;
import com.prochainvol.json.JsonUtilities;

public class OdigeoExecutorDirectSync extends AbstractOdigeoExecutor {

	private static final Logger logger = Logger
			.getLogger(OdigeoExecutorDirectSync.class.getName());

	@Override
	public SearchStatusResponse execService(Search search)
			throws ProchainvolException {
		SearchStatusResponse searchStatusResponse = null;
		try {
				ItinerarySearch itinerarySearch = new ItinerarySearch(new URL(
						Constants.ODIGEO_WSDL_URL),
						Constants.ODIGEO_SERVICE_NAME);
				ItinerarySearchService service = itinerarySearch
						.getItinerarySearchServicePort();
				searchStatusResponse = service.search(
						search.getPreferences(), search.getSearchRequest(),
						search.getMetasearchEngineCode());
		} catch (MalformedURLException e) {
			logger.error(e);
			throw new ProchainvolException(e);
		}

		logger.debug("execService response is : "
				+ JsonUtilities.getGsonPretty().toJson(searchStatusResponse));
		return searchStatusResponse;
	}

}
