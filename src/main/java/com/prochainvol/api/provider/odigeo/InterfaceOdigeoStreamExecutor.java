package com.prochainvol.api.provider.odigeo;

import java.util.List;
import java.util.Map;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.ProchainvolException;

public interface InterfaceOdigeoStreamExecutor {
	public List<SearchStatusResponse> execService(Map<String, Search> searchList)
			throws ProchainvolException;
}
