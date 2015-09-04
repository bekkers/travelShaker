package com.prochainvol.sql;

import com.prochainvol.Constants;
import com.prochainvol.sql.airport.DataSource;


public class MysqlDataSource extends DataSource {
	
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//	   static final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";  

	   //  Database credentials
	   static final String SERVER_NAME = Constants.LINUX_IP_ADDRESS;
	   static final String DB_PORT = "3306";
	   
	   static final String DB_NAME = "prochainvoldev";
	   static final String USER = "prochainvol";
	   static final String PASS = "sidolou1";

	public MysqlDataSource() {
		super(DB_NAME, USER, PASS, SERVER_NAME, DB_PORT);
		this.driver = JDBC_DRIVER;
	}

	@Override
	protected String buildUrl(String serverName, String port, String databaseName) {
		StringBuffer result = new StringBuffer("jdbc:mysql://")
			.append(serverName).append(":").append(port).append("/")
			.append(databaseName);
		return result.toString();
	}
	
}
