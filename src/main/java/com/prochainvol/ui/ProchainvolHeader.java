package com.prochainvol.ui;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.json.ProchainvolObject;


public class ProchainvolHeader extends ProchainvolObject implements IResponsive, IAffichable {
	

	private final String title;

	private final String image = "images/logo2.png";

	private boolean isHomeButton = false;

	private final ProchainvolConfig config;
	public ProchainvolHeader(String title, ProchainvolConfig config) {
		super();

		this.title = title;
		this.config = config;
	}
	public ProchainvolHeader(String title, ProchainvolConfig config, boolean isHomeButton) {
		this(title, config);
		this.isHomeButton = isHomeButton;
	}

	@Override
	public void accept(IAffichableVisitor visitor) {
        visitor.visit(this);
	}


	
	@Override
	public void accept(IResponsiveVisitor visitor) {
        visitor.visit(this);
	}

	public ProchainvolConfig getConfig() {
		return config;
	}

	public String getImage() {
		return image;
	}

	public String getTitle() {
		return title;
	}

	public boolean isHomeButton() {
		return isHomeButton;
	}

	public void setHomeButton(boolean acceuil) {
		this.isHomeButton = acceuil;
	}


}
