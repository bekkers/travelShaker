package com.prochainvol.api.provider;

import com.prochainvol.ProchainvolException;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.response.RequestResult;

public abstract class AbstractAirlineService {
	
	public abstract RequestResult service(RequestParams params) throws ProchainvolException;
}
