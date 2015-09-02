package com.prochainvol.api.provider.odigeo;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.prochainvol.api.provider.AbstractAirlineService;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.response.RequestResult;
import com.prochainvol.json.JsonUtilities;

 
public class OdigeoCallable implements Callable<RequestResult> {
 
	private static final Logger logger = Logger
			.getLogger(OdigeoCallable.class.getName());

 
	final private RequestParams params;
	final private AbstractAirlineService service;
	final private boolean isAsync;

	public OdigeoCallable(RequestParams params, AbstractAirlineService service, boolean isAsync) {
		this.params = params;
		this.service = service;
		this.isAsync = isAsync;
	}
 
	@Override
	public RequestResult call() throws Exception {
		RequestResult response = service.service(params);
		logger.trace("response = "+JsonUtilities.getGsonPretty().toJson(response));
		return response;
	}
}