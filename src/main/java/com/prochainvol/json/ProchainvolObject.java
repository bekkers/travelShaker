package com.prochainvol.json;



public abstract class ProchainvolObject {
	
	public String toJson() {
		return JsonUtilities.getGson().toJson(this);
	}

	@Override
	public String toString() {
		return JsonUtilities.getGsonPretty().toJson(this);
	}
}
