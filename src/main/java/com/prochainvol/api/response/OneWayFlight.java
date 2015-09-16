package com.prochainvol.api.response;

import java.util.List;

import com.prochainvol.json.ProchainvolObject;

public class OneWayFlight extends ProchainvolObject implements IFlight {

	private static final String UNSUPORTED_ERROR_MESSAGE = "OneWayFlight class unsupportedOperation : %s";

	protected final List<Path> goingPath;

	protected final int goingDuration;
	public OneWayFlight(List<Path> goingPath2, int goingDuration) {
		super();
		this.goingPath = goingPath2;
		this.goingDuration = goingDuration;
	}

	public void addGoingSegment(Path elementaryFlight) {
		goingPath.add(elementaryFlight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OneWayFlight other = (OneWayFlight) obj;
		if (goingPath == null) {
			if (other.goingPath != null)
				return false;
		} else if (!goingPath.equals(other.goingPath))
			return false;
		return true;
	}

	public int getGoingDuration() {
		return goingDuration;
	}
	
	public List<Path> getGoingPath() {
		return goingPath;
	}

	public int getGoingStops() {
		return this.goingPath.size();
	}

	public int getReturnDuration() {
		throw new UnsupportedOperationException(String.format(UNSUPORTED_ERROR_MESSAGE, "getReturnDuration"));
	}

	public List<Path> getReturnPath() {
		throw new UnsupportedOperationException(String.format(UNSUPORTED_ERROR_MESSAGE, "getReturnPath"));
	}

	public int getReturnStops() {
		throw new UnsupportedOperationException(String.format(UNSUPORTED_ERROR_MESSAGE, "getReturnStops"));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((goingPath == null) ? 0 : goingPath.hashCode());
		return result;
	}

	public boolean isReturnFlight() {
		return false;
	}

	public void setReturnDuration(int returnDuration) {
		throw new UnsupportedOperationException(String.format(UNSUPORTED_ERROR_MESSAGE, "setReturnDuration"));
	}

	@Override
	public void setReturnPath(List<Path> returnPath) {
		throw new UnsupportedOperationException(String.format(UNSUPORTED_ERROR_MESSAGE, "setReturnPath"));
	}

	@Override
	public String toShortString() {
		StringBuffer buff = new StringBuffer();
		List<Path> pathList = this.getGoingPath();
		buff.append("[");
		int i=0;
		for (Path path : pathList) {
			if (i!=0) {
				buff.append(", ");
			}
			i++;
			buff.append(path.toShortString());
		}
		buff.append("]");
		return buff.toString();
	}


}