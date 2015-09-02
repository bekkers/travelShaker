package com.prochainvol.api.response;

import java.util.List;

public class ReturnFlight extends OneWayFlight {

	private List<Path> returnPath;



	private int returnDuration;



	public ReturnFlight(List<Path> goingPath, int goingDuration,
			List<Path> returnPath, int returnDuration) {
		super(goingPath, goingDuration);
		this.returnPath = returnPath;
		this.returnDuration = returnDuration;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReturnFlight other = (ReturnFlight) obj;
		if (returnPath == null) {
			if (other.returnPath != null)
				return false;
		} else if (!returnPath.equals(other.returnPath))
			return false;
		return true;
	}
	@Override
	public int getReturnDuration() {
		return returnDuration;
	}

	@Override
	public List<Path> getReturnPath() {
		return returnPath;
	}

	
	@Override
	public int getReturnStops() {
		return this.returnPath.size();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((returnPath == null) ? 0 : returnPath.hashCode());
		return result;
	}

	@Override
	public boolean isReturnFlight() {
		return true;
	}

	
	@Override
	public void setReturnDuration(int duration) {
		this.returnDuration = duration;
	}

	@Override
	public void setReturnPath(List<Path> returnPath) {
		this.returnPath = returnPath;
	}

}
