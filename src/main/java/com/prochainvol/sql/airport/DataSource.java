package com.prochainvol.sql.airport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.prochainvol.sql.SqlUtilities;

/**
 * @author  bekkers
 */
public abstract class DataSource {
	
	// connexion � la base
	private Connection con = null;
	
	/**
	 * @uml.property  name="driver"
	 */
	protected String driver;
	/**
	 * @uml.property  name="url"
	 */
	protected String url;
	/**
	 * @uml.property  name="user"
	 */
	protected String user;
	/**
	 * @uml.property  name="password"
	 */
	protected String password;

	public DataSource(String databaseName, String user, String password,
			String serverName, String port) {
		super();
		this.url = buildUrl(serverName,port,databaseName);
		this.user = user;
		this.password = password;
	}
		public void closeConnection() {
			if (con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
		public Connection getConnection() {
			if (con!=null) return con;
		try {
			// String driver = System.getProperty("jdbc.drivers");
			Class.forName(driver);
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url,user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

			Throwable t = e.getCause();
			while (t != null) {
				System.out.println(t.getMessage());
				t = t.getCause();
			}
		}
		return con;
	}
		
		public String getUrlModel() {
			return buildUrl("<server>","<port>","<nomDeBase>");
		}

		public void showTables(Connection con) throws SQLException {
			SqlUtilities.afficherTables(con, 1);
		}

		protected abstract String buildUrl(String serverName, String port, String databaseName);
}