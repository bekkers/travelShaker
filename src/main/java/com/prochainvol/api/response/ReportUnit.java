package com.prochainvol.api.response;

import java.util.Date;

import com.prochainvol.api.provider.PROVIDER;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.ui.IAffichable;
import com.prochainvol.ui.IAffichableVisitor;

public class ReportUnit extends ProchainvolObject implements IAffichable {
	
	private final PROVIDER provider;
	private final String url;


	private final RequestParams params;
	private final Date date;
	private String errorMess;
	private long duréeHttp;  // durée en ms
	private long duréeAnalyse; // durée en ms
	private String responseFileName;
	private int nbRecommendationsRecues;
	private int nbDirectAller = 0;
	private int nbDirectRetour = 0;
	private int nbDirectAllerRetour = 0;
	private float minPrice;
	private float maxPrice;
	public ReportUnit(PROVIDER provider, String url, Date date, RequestParams params) {
		super();
		this.date = date;
		this.provider = provider;
		this.params = params;
		this.url = url;
	}



	@Override
	public void accept(IAffichableVisitor visitor) {
        visitor.visit(this);
	}





	public Date getDate() {
		return date;
	}


	public long getDuréeAnalyse() {
		return duréeAnalyse;
	}

	public long getDuréeHttp() {
		return duréeHttp;
	}

	public String getErrorMess() {
		return errorMess;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public float getMinPrice() {
		return minPrice;
	}

	public int getNbDirectAller() {
		return nbDirectAller;
	}

	public int getNbDirectAllerRetour() {
		return nbDirectAllerRetour;
	}

	public int getNbDirectRetour() {
		return nbDirectRetour;
	}

	public int getNbRecommendationsRecues() {
		return nbRecommendationsRecues;
	}

	public RequestParams getParams() {
		return params;
	}

	public PROVIDER getProvider() {
		return provider;
	}

	public String getResponseFileName() {
		return responseFileName;
	}

	public String getUrl() {
		return url;
	}



	public void setDuréeAnalyse(long duréeAnalyse) {
		this.duréeAnalyse = duréeAnalyse;
	}

	public void setDuréeHttp(long duréeHttp) {
		this.duréeHttp = duréeHttp;
	}


	public void setErrorMess(String errorMess) {
		this.errorMess = errorMess;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}


	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public void setNbDirectAller(int nbDirectAller) {
		this.nbDirectAller = nbDirectAller;
	}

	public void setNbDirectAllerRetour(int nbDirectAllerRetour) {
		this.nbDirectAllerRetour = nbDirectAllerRetour;
	}

	public void setNbDirectRetour(int nbDirectRetour) {
		this.nbDirectRetour = nbDirectRetour;
	}

	public void setNbDirectReturn(int size) {
		nbDirectRetour = size;
	}


	public void setNbRecommendationsRecues(int nbRecommendationsRecues) {
		this.nbRecommendationsRecues = nbRecommendationsRecues;
	}



	public void setResponseFileName(String responseFileName) {
		this.responseFileName = responseFileName;
	}





}
