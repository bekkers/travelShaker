package com.prochainvol.api.response;

import java.util.ArrayList;
import java.util.List;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.ui.IAffichable;
import com.prochainvol.ui.IAffichableVisitor;

public class RequestResult extends ProchainvolObject implements IAffichable {
	
	private final List<RequestResultUnit> requestResultUnits;

	private long duréeHttp;

	private long duréeAnalyse;

	private final ProchainvolConfig prochainvolConfig;

	public RequestResult(List<RequestResultUnit> results, ProchainvolConfig prochainvolConfig) {
		super();
		this.requestResultUnits = results;
		this.prochainvolConfig = prochainvolConfig;
	}
	@Override
	public void accept(IAffichableVisitor visitor) {
        visitor.visit(this);
	}
	public long getDuréeAnalyse() {
		return duréeAnalyse;
	}

	public long getDuréeHttp() {
		return duréeHttp;
	}
	public List<RequestParams> getParams() {
		List<RequestParams> result = new ArrayList<RequestParams>();
		for (RequestResultUnit requestResultUnit : requestResultUnits) {
			result.add(requestResultUnit.reportUnit.getParams());
		}
		return result;
	}

	public ProchainvolConfig getProchainvolConfig() {
		return prochainvolConfig;
	}

	public List<RequestResultUnit> getRequestResultUnits() {
		return requestResultUnits;
	}

	public void setDuréeAnalyse(long duréeAnalyse) {
		this.duréeAnalyse = duréeAnalyse;
	}

	public void setDuréeHttp(long duréeHttp) {
		this.duréeHttp = duréeHttp;
	}

}
