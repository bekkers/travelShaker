package com.prochainvol.api.provider.odigeo;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.ProchainvolException;

public interface InterfaceOdigeoExecutor {
	public SearchStatusResponse execService(Search searchList)
			throws ProchainvolException;
}
