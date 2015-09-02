package com.prochainvol;

import java.util.Map;

public interface IDataReader<T> {

	public abstract Map<String, T> load();

}