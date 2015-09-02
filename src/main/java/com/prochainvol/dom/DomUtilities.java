package com.prochainvol.dom;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DomUtilities {

	/*
	 * <meta name="viewport"
	 * content="width=device-width, initial-scale=1, maximum-scale=1" />
	 */
	public static final String headers = "\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
			+ "\n<meta name=\"viewport\" "
			+ "content=\"width=device-width, initial-scale=1, maximum-scale=1\" />"
			+ "\n<link href=\"css/fluid_grid.css\" rel=\"stylesheet\" type=\"text/css\" />"
			+ "\n<link href=\"css/prochainvol.css\" rel=\"stylesheet\" type=\"text/css\" />"
			+ "\n<link href=\"//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css\" rel=\"stylesheet\" >"
			+ "\n<script src=\"//code.jquery.com/jquery-1.10.2.min.js\"></script>"
			+ "\n<script src=\"//code.jquery.com/ui/1.11.2/jquery-ui.js\"></script>"
			+ "\n<script src=\"http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js\"></script>"
			+ "\n<script src=\"js/prochainvol.js\"></script>";

	public static String domToString(Node doc) throws TransformerException {
		return domToString(doc, false);
	}

	public static String domToString(Node doc, boolean indent)
			throws TransformerException {
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		if (indent == true) {
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "3");
		}
		transformer.transform(domSource, result);
		return writer.toString();
	}


	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	public static Document readXml(InputSource is) throws SAXException,
			IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setValidating(false);
		dbf.setIgnoringComments(false);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setNamespaceAware(true);
		// dbf.setCoalescing(true);
		// dbf.setExpandEntityReferences(true);

		DocumentBuilder db = null;
		db = dbf.newDocumentBuilder();
		db.setEntityResolver(new NullResolver());

		// db.setErrorHandler( new MyErrorHandler());

		return db.parse(is);
	}


	public static Document stringToDom(String xmlRecords) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db = dbf.newDocumentBuilder();

		db.setEntityResolver(new EntityResolver() {
			@Override
			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				return new InputSource(new StringReader("dtdUrl"));
			}
		});
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xmlRecords));

		Document doc = db.parse(is);
		// NodeList nodes = doc.getElementsByTagName("employee");
		//
		// for (int i = 0; i < nodes.getLength(); i++) {
		// Element element = (Element) nodes.item(i);
		//
		// NodeList name = element.getElementsByTagName("name");
		// Element line = (Element) name.item(0);
		// System.out.println("Name: " + getCharacterDataFromElement(line));
		//
		// NodeList title = element.getElementsByTagName("title");
		// line = (Element) title.item(0);
		// System.out.println("Title: " + getCharacterDataFromElement(line));
		// }

		return doc;
	}

	String dtdUrl = "http://www.w3.org/TR/html4/strict.dtd";

}
