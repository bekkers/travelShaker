package com.prochainvol.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.prochainvol.ProchainvolConfig;

public class ProchainvolConfigSerializer implements
		JsonSerializer<ProchainvolConfig> {
    @Override
	public JsonElement serialize(ProchainvolConfig src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonObject tree = (JsonObject) new Gson().toJsonTree(src);
		// TODO voir pourquoi il y a cette adjonction
//		tree.addProperty("rapport", src.getRapportAirportReader().toJson());
		return tree;
	}
}
