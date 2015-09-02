package com.prochainvol.api;

public enum TravelType {
	RETURN("Aller-retour"), 
	ONE_WAY("Aller simple"),
	LONG_WEEKEND("Weekend vendredi/lundi"),
	SHORT_WEEKEND("Weekend vendredi/dimanche"),
	LONG_WEEK("Semaine lundi/dimanche"),
	MEDIUM_WEEK("Semaine lundi/vendredi"),
	SHORT_WEEK("Semaine lundi/jeudi");
;
	private String typeName;

	private TravelType(String typeName){
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return typeName;
	}
	
	
}
