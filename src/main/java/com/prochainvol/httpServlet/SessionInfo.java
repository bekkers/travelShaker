package com.prochainvol.httpServlet;

import java.util.Date;

import com.prochainvol.json.ProchainvolObject;

public class SessionInfo extends ProchainvolObject {

	private final Date date;
	private String duration;
	private String userName;
	private String remoteHost;
	private String remoteAddr;

	public SessionInfo() {
		super();
		this.date = new Date();
	}

	public Date getDate() {
		return date;
	}

	public String getDuration() {
		return duration;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public String getUserName() {
		return userName;
	}

	public void setDuration() {
		long millisDiff = new Date().getTime() - date.getTime();
		long timeDifSeconds = millisDiff / 1000;
		long timeDifMinutes = millisDiff / (60 * 1000);
		long timeDifHours = millisDiff / (60 * 60 * 1000);
		long timeDifDays = millisDiff / (24 * 60 * 60 * 1000);
		this.duration = String.format("%3d days %02d:%02d:%02d", timeDifDays,
				timeDifHours, timeDifMinutes, timeDifSeconds);
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;		
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
