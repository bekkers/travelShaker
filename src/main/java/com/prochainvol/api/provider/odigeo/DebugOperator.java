package com.prochainvol.api.provider.odigeo;

import org.apache.log4j.Logger;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.ProchainvolException;
import com.prochainvol.api.provider.PROVIDER;
import com.prochainvol.json.JsonUtilities;

public class DebugOperator implements InterfaceOdigeoExecutor {
	private static final Logger logger = Logger
			.getLogger(DebugOperator.class.getName());

	private final String[] fileName;
	private int courant = 0;
	
	public DebugOperator() {
		this.fileName = new String[1];
		fileName[0] = PROVIDER.ODIGEO.getProperties().getProperty("debugResultFilename");
		logger.debug("debugResultFilename = "+ fileName[0]);
	}
	
	public boolean next() {
		courant++;
		if (courant >= fileName.length) {
			return false;
		} else {
			return true;
		}
	}
	
	public DebugOperator(String[] fileName) {
		this.fileName = fileName;
		logger.debug("debugResultFilename = "+ fileName[0]+", size = "+fileName.length);
	}
	
	public SearchStatusResponse execService(Search search) throws ProchainvolException {
		return JsonUtilities.readFromInputStream(SearchStatusResponse.class, fileName[0]);

	}

}
