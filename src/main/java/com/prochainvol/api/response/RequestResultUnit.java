package com.prochainvol.api.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.ui.IAffichable;
import com.prochainvol.ui.IAffichableVisitor;

public class RequestResultUnit extends ProchainvolObject implements IAffichable {

	protected List<FlightRecommendation> recommendations;
	protected ReportUnit reportUnit;
	Route route;
	
	
	public String toShortString() {
		StringBuffer buff = new StringBuffer();
		if (route!=null) {
			buff.append(route.toShortString());
		}
		int i =0;
		for (FlightRecommendation flightRecommendation : recommendations) {
			i++;
			buff.append("\n\t").append(i+". ").append(flightRecommendation.toShortString());
			IFlight flight = flightRecommendation.getFlight();
			buff.append(" ").append(flight.toShortString());
		}
		return buff.toString();
	}
	
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public RequestResultUnit() {
		super();
		this.recommendations = new ArrayList<FlightRecommendation>();
	}
	
	public RequestResultUnit(List<FlightRecommendation> recommendations,
			ReportUnit requestReportUnit) {
		super();
		this.recommendations = recommendations;
		this.reportUnit = requestReportUnit;
	}

	public RequestResultUnit(ReportUnit requestReportUnit) {
		super();
		this.recommendations = new ArrayList<FlightRecommendation>();
		this.reportUnit = requestReportUnit;
	}

	@Override
	public void accept(IAffichableVisitor visitor) {
        visitor.visit(this);
	}

	public List<FlightRecommendation> getDirectMoveFlight() {
		 return recommendations.stream().filter(r -> r.getInboundStop() == 0).collect(Collectors.toList());

	}

	public List<FlightRecommendation> getNonDirectMoveFlight() {
		 return recommendations.stream().filter(r -> r.getInboundStop() > 0).collect(Collectors.toList());

	}

	public List<FlightRecommendation> getRecommendations() {
		return recommendations;
	}
	
	public ReportUnit getReportUnit() {
		return reportUnit;
	}

	public void setRecommendations(List<FlightRecommendation> recommendations) {
		this.recommendations = recommendations;
	}


}
