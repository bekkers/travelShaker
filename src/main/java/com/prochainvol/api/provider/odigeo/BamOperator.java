package com.prochainvol.api.provider.odigeo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.Constants;
import com.prochainvol.ProchainvolException;
import com.prochainvol.json.JsonUtilities;

public class BamOperator implements InterfaceOdigeoExecutor {
	private static final Logger logger = Logger
			.getLogger(BamOperator.class.getName());

	private static final String TOMCAT = Constants.PROCHAINVOL_PROPS.getProperty("tomcatSurProchainvol");
	private static final String API_NAME = Constants.PROCHAINVOL_PROPS.getProperty("apiName");
	private static final String VERSI0N = Constants.PROCHAINVOL_PROPS.getProperty("previousVersion");
	private static final String url = TOMCAT+"/"+API_NAME+VERSI0N+"/odigeoSearch.jsp";
	// HTTP POST request
	public SearchStatusResponse execService(Search search) throws ProchainvolException {
		StringBuffer response = new StringBuffer();
		try {
			logger.trace("\ngoing to send 'POST' request to URL : " + url);
			URLConnection conn = new URL(url).openConnection();
			String searchParam = JsonUtilities.getGson().toJson(search);
			logger.trace(String.format("Post parameters : search=%s",
					searchParam));

			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(
					conn.getOutputStream());

			String urlParameters = String.format("search=%s",
					URLEncoder.encode(searchParam, "UTF-8"));
			writer.write(urlParameters);
			writer.flush();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			writer.close();
			reader.close();

		} catch (IOException e) {
			logger.error(e);
			throw new ProchainvolException(e);
		} catch (Exception e) {
			logger.error(e);
			throw new ProchainvolException(e);
		}

		String json = response.toString();
		System.out.println("task output = " + json);
		SearchStatusResponse result = JsonUtilities.getGson()
				.fromJson(json, SearchStatusResponse.class);
		return result;

	}

}
