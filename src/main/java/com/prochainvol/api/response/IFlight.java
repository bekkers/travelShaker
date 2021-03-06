package com.prochainvol.api.response;

import java.util.List;

public interface IFlight {

	public abstract int getGoingDuration();
	public abstract List<Path> getGoingPath(); 

	public abstract int getGoingStops();

	public abstract int getReturnDuration();
	public abstract List<Path> getReturnPath();
	public abstract int getReturnStops(); 
	public abstract boolean isReturnFlight();
	public abstract void setReturnPath(List<Path> returnPath);
	public abstract String toJson();
	public abstract String toShortString();

}