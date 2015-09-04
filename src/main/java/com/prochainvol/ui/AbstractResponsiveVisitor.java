package com.prochainvol.ui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.ProchainvolRuntimeException;
import com.prochainvol.dom.DomUtilities;

public class AbstractResponsiveVisitor implements IResponsiveVisitor {

	private static final Logger logger = Logger
			.getLogger(AbstractResponsiveVisitor.class.getName());

	protected Document doc;
	protected Element root;
	protected final ProchainvolConfig config;

	public AbstractResponsiveVisitor(ProchainvolConfig config2)  {
		super();
		DocumentBuilder builder = null;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			String msg = "serious DOM configuration error";
			logger.fatal(msg);
			throw new ProchainvolRuntimeException(msg, e);
		}
		doc = builder.newDocument(); // création du document
		root = doc.createElement("div");
		doc.appendChild(root);
		config = config2;
	}

	public Document getDocument() {
		return doc;
	}

	public String getDocumentAsString() throws TransformerException,
			ProchainvolException {
		if (doc == null) {
			String msg = "Impossible de sérialiser, document null";
			logger.fatal(msg);
			throw new ProchainvolException(msg);
		}
		return DomUtilities.domToString(doc);
	}

	public String getDocumentAsString(boolean indent)
			throws TransformerException {
		return DomUtilities.domToString(doc, indent);
	}

	public Element strongText(String text) {
		if (text == null)
			text = "_";

		Element strong = doc.createElement("strong");
		strong.appendChild(doc.createTextNode(text));
		return strong;
	}

	@Override
	public void visit(ProchainvolHeader prochainvolHeader) {
		Element headersContainer12Root = doc.createElement("div");
		headersContainer12Root.setAttribute("class", "container_12");
		root.appendChild(headersContainer12Root);

		Element headersGrid12 = doc.createElement("div");
		headersGrid12.setAttribute("class", "grid_12");
		headersContainer12Root.appendChild(headersGrid12);

		Element headersContainer12 = doc.createElement("div");
		headersContainer12.setAttribute("class", "container_12");
		headersGrid12.appendChild(headersContainer12);

		Element headersGrid4 = doc.createElement("div");
		headersGrid4.setAttribute("class", "grid_4");
		headersContainer12.appendChild(headersGrid4);

		Element image = doc.createElement("img");
		image.setAttribute("src", prochainvolHeader.getImage());
		headersGrid4.appendChild(image);

		Element heardersGrid6 = doc.createElement("div");
		heardersGrid6.setAttribute("class", "grid_6");
		headersContainer12.appendChild(heardersGrid6);

		Element title = doc.createElement("h1");
		title.appendChild(doc.createTextNode(prochainvolHeader.getTitle()));
		heardersGrid6.appendChild(title);

		Element heardersGrid2 = doc.createElement("div");
		heardersGrid2.setAttribute("class", "grid_2");
		headersContainer12.appendChild(heardersGrid2);

		Element divName = doc.createElement("div");
		divName.appendChild(doc.createTextNode("User : " + config.getUser()
				+ " "));
		Element disconnect = doc.createElement("a");
		disconnect.setAttribute("href", "Disconnect");
		disconnect.setTextContent("Se déconnecter");
		divName.appendChild(disconnect);

		heardersGrid2.appendChild(divName);

		if (prochainvolHeader.isHomeButton()) {
			Element divForm = doc.createElement("div");
			Element form = doc.createElement("form");
			divForm.appendChild(form);
			form.setAttribute("action", "index.jsp");
			Element inputSubmit = doc.createElement("input");
			inputSubmit.setAttribute("type", "submit");
			inputSubmit.setAttribute("value", "Acceuil");
			form.appendChild(inputSubmit);
			heardersGrid2.appendChild(divForm);

		}
		// génération de la ligne de configuration
		ProchainvolConfig prochainvolConfig = prochainvolHeader.getConfig();
		generateLine(
				"Providers : "
						+ Arrays.toString(prochainvolConfig
								.getCurrentProviders()),
				String.format("Default Stops : %s, Executor type : %s", prochainvolConfig.getDefaultStop(),
				prochainvolConfig.getExecutorType().name()));

		// génération du trait de séparation horizontal
		Element sepContainer = doc.createElement("div");
		sepContainer.setAttribute("class", "container_12");
		root.appendChild(sepContainer);

		Element sepGrid12 = doc.createElement("div");
		sepGrid12.setAttribute("class", "grid_12");
		sepContainer.appendChild(sepGrid12);

		Element sepDiv = doc.createElement("div");
		sepDiv.setAttribute("class", "top-separator");
		sepDiv.appendChild(doc.createTextNode("   "));
		sepGrid12.appendChild(sepDiv);
	}


	void generateAnchor(String value, String url) {
		if (url == null) {
			generateLine(value, "_");
		} else {
			Element a1 = doc.createElement("a");
			a1.setAttribute("href", url);
			a1.appendChild(doc.createTextNode(url));
			Text text = doc.createTextNode(value);
			generateLine(text, a1);
		}
	}

	void generateDeepLine(Element form, Node node1, Node node2) {
		String[] gridFormat = { "grid_6", "grid_6" };
		generateDeepLine(form, node1, node2, gridFormat);
	}

	void generateDeepLine(Node rootNode, Node node) {

		Element container = doc.createElement("div");
		container.setAttribute("class", "container_12");
		rootNode.appendChild(container);

		Element grid = doc.createElement("div");
		grid.setAttribute("class", "grid_12");
		container.appendChild(grid);

		grid.appendChild(node);
	}

	void generateH4Line(String value) {
		if (value == null || value.length() == 0)
			value = "_";
		Element h4 = doc.createElement("h4");
		h4.appendChild(doc.createTextNode(value));
		generateLine(h4);
	}

	Element generateHtmlTable(List<List<Node>> values) {
		Element table = doc.createElement("table");
		values.forEach(l -> {
			Element tr = doc.createElement("tr");
			table.appendChild(tr);
			l.forEach(t -> {
				Element td = doc.createElement("td");
				td.appendChild(t);
				tr.appendChild(td);
			});
		});
		return table;
	}

	Element generateHtmlTable(Node... nodes) {
		List<List<Node>> lines = Arrays.stream(nodes)
				.map(r -> Arrays.asList(r)).collect(Collectors.toList());
		return generateHtmlTable(lines);
	}

	Element generateHtmlTable(Node[][] values) {
		List<List<Node>> lines = Arrays.stream(values)
				.map(r -> Arrays.asList(r)).collect(Collectors.toList());
		return generateHtmlTable(lines);
	}

	Element generateHtmlTable(String... nodes) {
		List<List<Node>> lines = Arrays.stream(nodes)
				.map(s -> Arrays.asList((Node) doc.createTextNode(s)))
				.collect(Collectors.toList());
		return generateHtmlTable(lines);
	}

	Element generateHtmlTable(String[][] table) {
		List<List<Node>> lines = Arrays
				.stream(table)
				.map(l -> Arrays.asList(l).stream()
						.map(s -> (Node) doc.createTextNode(s))
						.collect(Collectors.toList()))
				.collect(Collectors.toList());
		return generateHtmlTable(lines);
	}

	void generateLine(Node node) {
		generateDeepLine(this.root, node);
	}

	void generateLine(Node node1, Node node2) {
		String[] gridFormat = { "grid_6", "grid_6" };
		generateLine(node1, node2, gridFormat);
	}

	void generateLine(Node node1, Node node2, Node node3) {
		String[] gridFormat = { "grid_4", "grid_4", "grid_4" };
		generateLine(node1, node2, node3, gridFormat);
	}

	void generateLine(Node node1, Node node2, Node node3, String[] gridFormat) {
		Element mainContainer = doc.createElement("div");
		mainContainer.setAttribute("class", "container_12");
		this.root.appendChild(mainContainer);

		Element mainGrid = doc.createElement("div");
		mainGrid.setAttribute("class", "grid_12");
		mainContainer.appendChild(mainGrid);

		Element container = doc.createElement("div");
		container.setAttribute("class", "container_12");
		mainGrid.appendChild(container);

		Element grid1 = doc.createElement("div");
		grid1.setAttribute("class", gridFormat[0]);
		grid1.appendChild(node1);
		container.appendChild(grid1);

		Element grid2 = doc.createElement("div");
		grid2.setAttribute("class", gridFormat[1]);
		grid2.appendChild(node2);
		container.appendChild(grid2);

		Element grid3 = doc.createElement("div");
		grid3.setAttribute("class", gridFormat[2]);
		grid3.appendChild(node3);
		container.appendChild(grid3);
	}

	void generateLine(Node node1, Node node2, String[] gridFormat) {
		generateDeepLine(this.root, node1, node2, gridFormat);
	}

	void generateLine(String value) {
		if (value == null || value.length() == 0)
			value = "_";
		Text text = doc.createTextNode(value);
		generateLine(text);
	}

	void generateLine(String value1, String value2) {
		if (value1 == null || value1.length() == 0)
			value1 = "_";
		if (value2 == null || value2.length() == 0)
			value2 = "_";
		Text text1 = doc.createTextNode(value1);
		Text text2 = doc.createTextNode(value2);
		generateLine(text1, text2);
	}

	void generateLine(String value1, String value2, String value3) {
		if (value1 == null || value1.length() == 0)
			value1 = "_";
		if (value2 == null || value2.length() == 0)
			value2 = "_";
		if (value3 == null || value3.length() == 0)
			value3 = "_";
		Text text1 = doc.createTextNode(value1);
		Text text2 = doc.createTextNode(value2);
		Text text3 = doc.createTextNode(value3);
		generateLine(text1, text2, text3);
	}

	void generateLine(String value1, String value2, String value3,
			String[] gridFormat) {
		if (value1 == null || value1.length() == 0)
			value1 = "_";
		if (value2 == null || value2.length() == 0)
			value2 = "_";
		if (value3 == null || value3.length() == 0)
			value3 = "_";
		Text text1 = doc.createTextNode(value1);
		Text text2 = doc.createTextNode(value2);
		Text text3 = doc.createTextNode(value3);
		generateLine(text1, text2, text3, gridFormat);
	}

	void generateLine(String value1, String value2, String[] gridFormat) {
		if (value1 == null || value1.length() == 0)
			value1 = "_";
		if (value2 == null || value2.length() == 0)
			value2 = "_";
		Text text1 = doc.createTextNode(value1);
		Text text2 = doc.createTextNode(value2);
		generateLine(text1, text2, gridFormat);
	}

	void generateTr(Node string, List<Node> collect) {
		switch (collect.size()) {
		case 1:
			generateLine(string, collect.get(0));
			break;
		case 2:
			generateLine(string, collect.get(0), collect.get(1));
			break;
		default:
			generateLine("Not implemented, table size = " + collect.size());
		}

	}

	void generateTr(String string, List<String> collect) {
		switch (collect.size()) {
		case 1:
			generateLine(string, collect.get(0));
			break;
		case 2:
			generateLine(string, collect.get(0), collect.get(1));
			break;
		default:
			generateLine("Not implemented, table size = " + collect.size());
		}

	}

	void generateTxt(String title, String txt) {
		if (txt == null || title == null || txt.length() == 0
				|| title.length() == 0)
			return;
		generateH4Line(title);
		Element p = doc.createElement("p");
		p.appendChild(doc.createTextNode(txt));
		generateLine(p);
	}

	private void generateDeepLine(Node rootNode, Node node1, Node node2,
			String[] gridFormat) {
		Element mainContainer = doc.createElement("div");
		mainContainer.setAttribute("class", "container_12");
		rootNode.appendChild(mainContainer);

		Element mainGrid = doc.createElement("div");
		mainGrid.setAttribute("class", "grid_12");
		mainContainer.appendChild(mainGrid);

		Element container = doc.createElement("div");
		container.setAttribute("class", "container_12");
		mainGrid.appendChild(container);

		Element grid1 = doc.createElement("div");
		grid1.setAttribute("class", gridFormat[0]);
		grid1.appendChild(node1);
		container.appendChild(grid1);

		Element grid2 = doc.createElement("div");
		grid2.setAttribute("class", gridFormat[1]);
		grid2.appendChild(node2);
		container.appendChild(grid2);
	}

}