package com.prochainvol.api.provider.odigeo;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.ProchainvolException;

public class OdigeoExecutorDebugSync extends AbstractOdigeoExecutor {

	private DebugOperator operator;
	public OdigeoExecutorDebugSync() {
		this.operator = new DebugOperator();
	}

	public OdigeoExecutorDebugSync(String[] fileName) {
		this.operator = new DebugOperator(fileName);
	}

	@Override
	public SearchStatusResponse execService(Search search) throws ProchainvolException {
		return operator.execService(search);
	}


}
