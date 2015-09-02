package com.prochainvol.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.prochainvol.sql.airport.MysqlDataSource;

public class SqlUtilities {
	
	public static void affiche(ResultSet resultat) throws SQLException {
		affiche(resultat, 0);
	}

	public static void affiche(ResultSet resultat, int max) throws SQLException {		
		int i;
		ResultSetMetaData rs = resultat.getMetaData();
		int nbcol = rs.getColumnCount();
	
		for (i = 1; i <= nbcol; i++) {
	
			if (i > 1)
				System.out.print("\t");
			System.out.print(rs.getColumnLabel(i));
		}
		System.out.println(""); // ligne suivante
		int nbLigne = 0;
		while (resultat.next()) {
			nbLigne++;
			for (i = 1; i <= nbcol; i++) {
				if (i > 1)
					System.out.print("\t");
				System.out.print(resultat.getString(i));
			}
			System.out.println(""); // ligne suivante
			if (max>0 && nbLigne>=max) {
				break;
			}
		}
	}
	public static void afficherTables(Connection con) throws SQLException {
		afficherTables(con, 0);
	}
		
	public static void afficherTables(Connection con, int max) throws SQLException {
		ResultSet rs;
		Statement stmt = null;		
		try {
		// afficher les tables
		stmt = con.createStatement();
		
		System.out.println("\n*********** les tables ***************");
		String[] types = { "table" };
		DatabaseMetaData meta = con.getMetaData();
		rs = meta.getTables(null, null, "%", types);
		SqlUtilities.affiche(rs);

		rs = meta.getTables(null, null, "%", types);
		while (rs.next()) {
			String tableName = rs.getString(3);
			System.out.println("\n*********** "+tableName+" ***************");
			ResultSet resultat = stmt.executeQuery("select * from "+tableName);
			SqlUtilities.affiche(resultat, max);
		}
		} finally {
			if (stmt != null) stmt.close();
		}
	}
	
	/* replace multiple whitespaces between words with single blank */
	public static String itrim(String source) {
		return source.replaceAll("bs{2,}b", " ");
	}

	
	public static String lrtrim(String source){
		return ltrim(rtrim(source));
	}

	/* remove leading whitespace */
	public static String ltrim(String source) {
		return source.replaceAll("^s+", "");
	}

	public static void main(String[] args) throws SQLException {
		MysqlDataSource mysqlDataSoure = new MysqlDataSource();
		afficherTables(mysqlDataSoure.getConnection(), 10);
	}

	/* remove trailing whitespace */
	public static String rtrim(String source) {
		return source.replaceAll("s+$", "");
	}

	/* remove all superfluous whitespaces in source string */
	public static String trim(String source) {
		return itrim(ltrim(rtrim(source)));
	}

}
