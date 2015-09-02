package com.prochainvol.api.provider.odigeo;

import org.apache.log4j.Logger;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.ProchainvolException;

public class OdigeoExecutorBamSync extends AbstractOdigeoExecutor {

	private static final Logger logger = Logger
			.getLogger(OdigeoExecutorBamSync.class.getName());
	
	private final BamOperator operator = new BamOperator();


	@Override
	public SearchStatusResponse execService(Search search)
			throws ProchainvolException {
		return operator.execService(search);
	}



}
