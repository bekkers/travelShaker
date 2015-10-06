package com.prochainvol.ui;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

import com.prochainvol.ProchainvolException;
import com.prochainvol.ProchainvolRuntimeException;
import com.prochainvol.json.JsonUtilities;
import com.prochainvol.json.ProchainvolObject;

public class UiUtilities {

	private static final Logger logger = Logger.getLogger(UiUtilities.class
			.getName());

	public static String getHtmlContent(ResponsiveVisitor visitor,
			ProchainvolHeader prochainvolHeader,
			ProchainvolObject... prochainvolObjects) {

		prochainvolHeader.accept(visitor); // entête

		Arrays.stream(prochainvolObjects).forEach(o -> {
			IAffichable a = (IAffichable) o;
			try {
				a.accept(visitor);
			} catch (ProchainvolException ex) {
				String msg = "Erreur interne fatale";
				logger.fatal(msg, ex);
				throw new ProchainvolRuntimeException(ex);
			}
		});

		String result = null;
		try {
			result = visitor.getDocumentAsString();
		} catch (TransformerException | ProchainvolException e) {
			String msg = "Impossible de générer le contenu html";
			logger.fatal(msg, e);
			throw new ProchainvolRuntimeException(msg, e);
		}
		return result;

	}

	public static String getJsonContent(boolean noRRU,
			ProchainvolObject... prochainvolObjects) throws ServletException {
		String result = null;
//		System.out.println("noRRU = " + noRRU);
		if (noRRU) {
			result = Arrays
					.stream(prochainvolObjects)
					.map(o -> {
						String name = o.getClass().getName();
						String attrName = name.substring(
								name.lastIndexOf(".") + 1, name.length());
						return String.format(
								"\"%s\":\n%s",
								attrName,
								JsonUtilities.getGsonPrettyWithoutRRU().toJson(
										o));
					}).collect(Collectors.joining(",\n", "{\n", "}\n"));
		} else {
			result = Arrays
					.stream(prochainvolObjects)
					.map(o -> {
						String name = o.getClass().getName();
						String attrName = name.substring(
								name.lastIndexOf(".") + 1, name.length());
						return String.format("\"%s\":\n%s", attrName,
								o.toString());
					}).collect(Collectors.joining(",\n", "{\n", "}\n"));
		}
		return result;
	}
}
