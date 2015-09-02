package com.prochainvol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.prochainvol.sql.route.Route;


public class ReaderUtilities {

	public static String fromStream(InputStream in) throws IOException
	{
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
	    BufferedReader reader = new BufferedReader(isr);
	    StringBuilder out = new StringBuilder();
	    String newLine = System.getProperty("line.separator");
	    String line;
	    while ((line = reader.readLine()) != null) {
	        out.append(line);
	        out.append(newLine);
	    }
	    reader.close();
	    isr.close();
	    return out.toString();
	}


	static public String getRouteId(Route route) {
		return route.getDepartureAirport() + ":"
				+ route.getArrivalAirport() + ":"
				+ route.getAirline();
	}

	public static float readFloat(String s) {
		return Float.parseFloat(s);
	}

	public static int readInt(String s) {
		if (s.equals("\\N") || s.equals("N"))
			return -1;
		else
			return Integer.parseInt(s);
	}

	public static String stringClean(String s) {
		String result = s.replaceAll("^\"(.*)\"$", "$1").trim();
		return result.length()==0 ? null : result;
	}
	
	public static String stringFromResourceFile(Object o, String fileName)
			throws UnsupportedEncodingException, IOException {
		InputStream in = o.getClass().getResourceAsStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		StringBuilder sb=new StringBuilder();
		String read = br.readLine();

		while(read != null) {
		    //System.out.println(read);
		    sb.append(read);
		    read =br.readLine();

		}
		String s = sb.toString();
		br.close();
		in.close();
		return s;
	}



}
