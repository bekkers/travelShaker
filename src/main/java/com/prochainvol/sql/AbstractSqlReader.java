package com.prochainvol.sql;

import com.prochainvol.ProchainvolException;
import com.prochainvol.json.ProchainvolObject;

public abstract class AbstractSqlReader<T> extends ProchainvolObject {

	public AbstractSqlReader() {
		super();
	}

	public abstract T load() throws ProchainvolException ;

}