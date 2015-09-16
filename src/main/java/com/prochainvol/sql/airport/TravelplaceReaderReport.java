package com.prochainvol.sql.airport;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.ProchainvolObject;
import com.prochainvol.sql.SqlAirport;
import com.prochainvol.ui.IAffichable;
import com.prochainvol.ui.IAffichableVisitor;

public class TravelplaceReaderReport extends ProchainvolObject implements IAffichable {

	private static final Logger logger = Logger.getLogger(TravelplaceReaderReport.class
			.getName());

	private int sizeEntry;
	private int sizeResult;
	private Long startTime;
	private Long duree;
	private Date when;
	private Map<String, List<SqlAirport>> IataDoublons;
	private Map<String, List<SqlAirport>> FullNameDoublons;
	

	@Override
	public void accept(IAffichableVisitor visitor) {
        visitor.visit(this);
	}
	
	public long getDuree() throws ProchainvolException { // durée en ms
		if (duree == null) {
			String msg = "stop() not completed, please complete";
			logger.error(msg);
			throw new ProchainvolException(msg);
		}
		return duree;
	}

	public Map<String, List<SqlAirport>> getFullNameDoublons() {
		return FullNameDoublons;
	}

	public Map<String, List<SqlAirport>> getIataDoublons() {
		return IataDoublons;
	}

	public int getSizeEntry() {
		return sizeEntry;
	}

	public int getSizeResult() {
		return sizeResult;
	}

	public long getStartTime() {
		return startTime;
	}

	public Date getWhen() {
		return this.when;
	}


	public void setFullNameDoublons(Map<String, List<SqlAirport>> fullNameDoublons) {
		FullNameDoublons = fullNameDoublons;
	}

	public void setIataDoublons(Map<String, List<SqlAirport>> doublonsByIata) {
		IataDoublons = doublonsByIata;
	}

	public void setSizeEntry(int nbLine) {
		this.sizeEntry = nbLine;
	}

	public void setSizeResult(int size) {
		this.sizeResult = size;
	}


	public void start() {
		this.startTime = System.nanoTime();
		this.when = new Date();
	}

	public void stop() {
		long endTime = System.nanoTime();
		if (startTime == null) {
			String msg = "start() not completed, please complete";
			logger.error(msg);
			throw new Error(msg);
		}
		duree =  (endTime - startTime) /1000000; 

	}


}
