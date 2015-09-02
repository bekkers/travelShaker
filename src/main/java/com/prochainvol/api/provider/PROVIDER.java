package com.prochainvol.api.provider;

import java.util.Properties;

import com.prochainvol.ProchainvolUtilities;

public enum PROVIDER {
	ODIGEO();

	private final String url;
	private final Properties props;

	private PROVIDER() {
		props = ProchainvolUtilities.loadProperties("/" + toString()
				+ ".properties");
		this.url = props.getProperty("url").intern();


	}



	public Properties getProperties() {
		return props;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

}
