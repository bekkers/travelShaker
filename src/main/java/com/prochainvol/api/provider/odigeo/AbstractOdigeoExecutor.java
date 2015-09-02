package com.prochainvol.api.provider.odigeo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.ProchainvolException;

public abstract class AbstractOdigeoExecutor implements InterfaceOdigeoStreamExecutor, InterfaceOdigeoExecutor {
	public List<SearchStatusResponse> execService(Map<String, Search> searchList)
			throws ProchainvolException {
		List<SearchStatusResponse> result = new ArrayList<SearchStatusResponse>();
		for (Search search : searchList.values()) {
			result.add(execService(search));
		}
		return result;
	}

	public abstract SearchStatusResponse execService(Search search)
			throws ProchainvolException;
}
