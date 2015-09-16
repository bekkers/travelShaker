package com.prochainvol.ui;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.json.ProchainvolObject;


public class ProchainvolHeader extends ProchainvolObject implements IResponsive, IAffichable {
	

	private final String title;

	private final String image = "images/logo2.png";

	private boolean isHomeButton = false;

	public ProchainvolHeader(String title) {
		super();

		this.title = title;
	}
	public ProchainvolHeader(String title, boolean isHomeButton) {
		this(title);
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
