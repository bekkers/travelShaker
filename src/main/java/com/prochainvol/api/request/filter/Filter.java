package com.prochainvol.api.request.filter;

import java.util.ArrayList;
import java.util.List;

import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.ui.IAffichable;
import com.prochainvol.ui.IAffichableVisitor;
import com.prochainvol.ui.IEditable;
import com.prochainvol.ui.IEditableVisitor;

public class Filter extends ProchainvolObject implements
IAffichable, IEditable {
	
	private final List<FlightRecommendationPredicate<?>> predicates ;
	
	public Filter() {
		this.predicates = new ArrayList<FlightRecommendationPredicate<?>>(); 
	}

	public Filter(List<FlightRecommendationPredicate<?>> predicates) {
		super();
		this.predicates = predicates;
	}

	@Override
	public void accept(IAffichableVisitor visitor) {
		visitor.visit(this);
	}

	public void accept(IEditableVisitor visitor) {
		visitor.visit(this);
	}

	public void addPredicate(FlightRecommendationPredicate<?> filter) {
		predicates.add(filter);
	}

	public void clearPredicates() {
		predicates.clear();
	}

	public List<FlightRecommendationPredicate<?>> getPredicates() {
		return predicates;
	}


}
