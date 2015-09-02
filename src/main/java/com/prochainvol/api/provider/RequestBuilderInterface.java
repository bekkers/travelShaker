package com.prochainvol.api.provider;

import java.util.Map;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.prochainvol.ProchainvolException;
import com.prochainvol.TestConstants;
import com.prochainvol.api.request.RequestParams;

public interface RequestBuilderInterface<T> {
	
	public static final RequestParams ONE_MONTH_TOGO_TEST_VALUES = TestConstants.oneMonthToGoRequestParamFromJson;

	public T buildRequest() throws ProchainvolException;
	public Map<String, Search> buildRequest(RequestParams params) throws ProchainvolException;
}
