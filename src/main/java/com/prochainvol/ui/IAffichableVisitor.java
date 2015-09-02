package com.prochainvol.ui;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.request.filter.Filter;
import com.prochainvol.api.response.FlightRecommendation;
import com.prochainvol.api.response.ReportUnit;
import com.prochainvol.api.response.RequestResult;
import com.prochainvol.api.response.RequestResultUnit;
import com.prochainvol.sql.airport.TravelplaceReaderReport;

public interface IAffichableVisitor extends IResponsiveVisitor {
    void visit(Filter filter);
	void visit(FlightRecommendation pvFlightRecommendation);
	void visit(ProchainvolConfig prochainvolConfig);
	void visit(ProchainvolHeader prochainvolHeader);
	void visit(ReportUnit rapportRequeteOpodo);
	void visit(RequestParams requestParams) throws ProchainvolException;
	void visit(RequestResult prochainvolResult);
	void visit(RequestResultUnit requestResult);
	void visit(TravelplaceReaderReport rapportAirportReader);
}

