package com.prochainvol.ui;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.prochainvol.Constants;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.api.EXECUTOR_TYPE;
import com.prochainvol.api.TravelType;
import com.prochainvol.api.provider.PROVIDER;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.request.filter.Filter;

public class EditResponsiveVisitor extends AbstractResponsiveVisitor implements
		IEditableVisitor {
	
	private static final Logger logger = Logger.getLogger(EditResponsiveVisitor.class
			.getName());


	public EditResponsiveVisitor(ProchainvolConfig config) {
		super(config);
	}

	public void createRequestUI(RequestParams requestparams, Element grid_12)
			throws ProchainvolException {
		Text libelleDepartureAirportIata = doc
				.createTextNode("Departure Airport");
		Text libelleArrivalAirportIata = doc.createTextNode("Arrival Airport");
		Text libelleTravelType = doc.createTextNode("Travel Type");
		Text libelleDepartDate = doc.createTextNode("Depart Date");
		Text libelleReturnDate = doc.createTextNode("Return Date");
		Text libelleStops = doc.createTextNode("Stops");
		Text libelleAdults = doc.createTextNode("Adults");
		Text libelleChildren = doc.createTextNode("Children");
		Text libelleInfants = doc.createTextNode("Infants");
		Text libelleWeek = doc.createTextNode("Week");
		Text libelleSynchronous = doc.createTextNode(" Synchronous");
		Text libelleAsynchronous = doc.createTextNode(" Asynchronous");
		Text libelleMode = doc.createTextNode("Mode");

		Element tripTypeDiv = createRootDiv();
		Element tripTypeSelect = doc.createElement("select");
		tripTypeSelect.setAttribute("size", "1");
		tripTypeSelect.setAttribute("name", "tripType");
		tripTypeSelect.setAttribute("id", "tripType");
		tripTypeDiv.appendChild(tripTypeSelect);
		for (TravelType travelType : TravelType.values()) {
			Element option = doc.createElement("option");
			option.setAttribute("value", travelType.name());
			if (travelType == TravelType.RETURN) {
				option.setAttribute("selected", "selected");
			}
			Text libelle = doc.createTextNode(travelType.toString());
			option.appendChild(libelle);
			tripTypeSelect.appendChild(option);
		}
		
		/*
		 * <input type="radio" name="sex" value="male" checked>Male
<br>
<input type="radio" name="sex" value="female">Female

		 */
		Element modeDiv  = createRootDiv();
		Element modeSynchronous = doc.createElement("input");
		modeSynchronous.setAttribute("type", "radio");
		modeSynchronous.setAttribute("name", "mode");
		modeSynchronous.setAttribute("value", "synchronous");
		modeSynchronous.setAttribute("checked", "checked");
		modeDiv.appendChild(modeSynchronous);
		modeDiv.appendChild(libelleSynchronous);

		Element modeAsynchronous = doc.createElement("input");
		modeAsynchronous.setAttribute("type", "radio");
		modeAsynchronous.setAttribute("name", "mode");
		modeAsynchronous.setAttribute("value", "asynchronous");
		modeDiv.appendChild(modeAsynchronous);
		modeDiv.appendChild(libelleAsynchronous);


		Element stopsDiv = createStopUI(requestparams.getStops());
		
		Element adultsDiv = createRootDiv();
		Element adultsSelect = doc.createElement("select");
		adultsSelect.setAttribute("name", "adults");
		adultsDiv.appendChild(adultsSelect);
		for (int i = 0; i<6; i++) {
			Element option = doc.createElement("option");
			option.setAttribute("value", Integer.toString(i));
			if (i==1) {
				option.setAttribute("selected", "selected");
			}
			Text libelle = doc.createTextNode(Integer.toString(i));
			option.appendChild(libelle);
			adultsSelect.appendChild(option);
		}
		
		Element childrenDiv = createRootDiv();
		Element childrenSelect = doc.createElement("select");
		childrenSelect.setAttribute("name", "children");
		childrenDiv.appendChild(childrenSelect);
		for (int i = 0; i<6; i++) {
			Element option = doc.createElement("option");
			option.setAttribute("value", Integer.toString(i));
			if (i==requestparams.getChildren()) {
				option.setAttribute("selected", "selected");
			}
			Text libelle = doc.createTextNode(Integer.toString(i));
			option.appendChild(libelle);
			childrenSelect.appendChild(option);
		}
		
		Element infantsDiv = createRootDiv();
		Element infantsSelect = doc.createElement("select");
		infantsSelect.setAttribute("name", "infants");
		infantsDiv.appendChild(infantsSelect);
		for (int i = 0; i<3; i++) {
			Element option = doc.createElement("option");
			option.setAttribute("value", Integer.toString(i));
			if (i==requestparams.getInfants()) {
				option.setAttribute("selected", "selected");
			}
			Text libelle = doc.createTextNode(Integer.toString(i));
			option.appendChild(libelle);
			infantsSelect.appendChild(option);
		}
		
		// TODO autoriser le nom d'un aéroport ou d'une ville ... en plus des iata
		Element departureIataDiv = createRootDiv();
		Element departureIataInput = doc.createElement("input");
		departureIataInput.setAttribute("type", "text");
		departureIataInput.setAttribute("name", "departAirport");
		departureIataInput.setAttribute("list", "airports");
		departureIataInput.setAttribute("style", "width: 100%");
		departureIataDiv.appendChild(departureIataInput);
		String departureIata = Arrays.toString(requestparams.getDepartureAirportIata());
		if (departureIata!=null) {
			departureIataInput.setAttribute("value", departureIata);
		}
		
		Element weekDiv = createRootDiv();
		Element weekSelect = doc.createElement("select");
		int week = requestparams.getWeek();
		weekDiv.appendChild(doc.createTextNode("+ ")); 
		
		weekSelect.setAttribute("size", "1");
		weekSelect.setAttribute("name", "week");
		weekSelect.setAttribute("id", "week");
		weekDiv.appendChild(weekSelect);
		for (int i=0; i<11; i++) {
			Element option = doc.createElement("option");
			option.setAttribute("value", ""+i);
			if (i == week) {
				option.setAttribute("selected", "selected");
			}
			Text libelle = doc.createTextNode(""+i);
			option.appendChild(libelle);
			weekSelect.appendChild(option);
		}
		weekDiv.appendChild(doc.createTextNode(" week")); 

		
		Element arrivalIataDiv = createRootDiv();
		Element arrivalIataInput = doc.createElement("input");
		arrivalIataInput.setAttribute("type", "text");
		arrivalIataInput.setAttribute("name", "arrivalAirport");
		arrivalIataInput.setAttribute("list", "airports");
		arrivalIataInput.setAttribute("style", "width: 100%");
		arrivalIataDiv.appendChild(arrivalIataInput);
		String arrivalIata = Arrays.toString(requestparams.getArrivalAirportIata());
		if (arrivalIata!=null) {
			arrivalIataInput.setAttribute("value", arrivalIata);
		}

		// TODO limiter le champs departDatepicker aux jours après le jour courrant
		Element departDateDiv = createRootDiv();
		Element departureDateInput = doc.createElement("input");
		departureDateInput.setAttribute("type", "text");
		departureDateInput.setAttribute("name", "departDate");
		departureDateInput.setAttribute("id", "departDatepicker");
		departureDateInput.setAttribute("placeholder", "Get a date");
		departureDateInput.setAttribute("style", "width: 100%");
		departDateDiv.appendChild(departureDateInput);
		Date departureDate = requestparams.getDepartureDate();
		if (departureDate!=null) {
			departureDateInput.setAttribute("value", Constants.DATE_PICKER_DATE_FORMAT.format(departureDate));
		}
		// TODO validation no depart Airpot iata or bad depart Airport iata
		// TODO validation no return Airpot iata or bad return Airport iata
		// TODO validation bad iata
		// TODO validation max price or bad max price
		// TODO validation know departure date
		// TODO validation no depart date or bad depart date
		// TODO validation no return date or bad return date, if return ticket
		
		// TODO limiter le champs returnDatepicker aux jours après le jour de départ
		Element returnDateDiv = createRootDiv();
		Element returnDateInput = doc.createElement("input");
		returnDateInput.setAttribute("type", "text");
		returnDateInput.setAttribute("name", "returnDate");
		returnDateInput.setAttribute("placeholder", "Get a date");
		returnDateInput.setAttribute("id", "returnDatepicker");
		returnDateInput.setAttribute("style", "width: 100%");
		returnDateDiv.appendChild(returnDateInput);
		Date returnDate = requestparams.getReturnDate();
		if (returnDate!=null) {
			returnDateInput.setAttribute("value", Constants.DATE_PICKER_DATE_FORMAT.format(returnDate));
		}
		
		
		Node[][] table = { 
				{ libelleMode, modeDiv },
				{ libelleDepartureAirportIata, departureIataDiv },
				{ libelleArrivalAirportIata, arrivalIataDiv },
				{ libelleTravelType, tripTypeDiv },
				{ libelleWeek, weekDiv },
				{ libelleDepartDate, departDateDiv },
				{ libelleReturnDate, returnDateDiv },
				{ libelleStops, stopsDiv },
				{ libelleAdults, adultsDiv },
				{ libelleChildren, childrenDiv},
				{ libelleInfants, infantsDiv }};

		
		Element outputSelect = doc.createElement("select");
		outputSelect.setAttribute("name", "output");
		outputSelect.setAttribute("size", "1");
		
		Element optionHtml = doc.createElement("option");
		optionHtml.setAttribute("selected", "selected");
		optionHtml.setAttribute("value", "html");
		Text libelleHtml = doc.createTextNode("html");
		optionHtml.appendChild(libelleHtml);
		outputSelect.appendChild(optionHtml);
		
		Element optionJson = doc.createElement("option");
		optionJson.setAttribute("value", "json");
		outputSelect.appendChild(optionJson);
		Text libelleJson = doc.createTextNode("json");
		optionJson.appendChild(libelleJson);
		

		Element htmlTable = putIntoHtmlForm(table, "Request", outputSelect);
		grid_12.appendChild(htmlTable);
		((Element) weekDiv.getParentNode().getParentNode()).setAttribute("id", "weekDiv");
		XPath xPath = XPathFactory.newInstance().newXPath();
		final String xpathExpr = "table/tr[td[text()='Return Date']]";
		try {
			NodeList trList = (NodeList) xPath.evaluate(xpathExpr, htmlTable, XPathConstants.NODESET);
			Element tr = (Element) trList.item(0);
			tr.setAttribute("id", "returnDateLine");
		} catch (XPathExpressionException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new ProchainvolException(e);
		}
		
		Element datalist = doc.createElement("datalist");
		datalist.setAttribute("id", "airport");
		root.appendChild(datalist);
	}

	public Element putIntoHtmlForm(Node[][] table, String action) {
		return putIntoHtmlForm(table, action, null);
	}

	public Element putIntoHtmlForm(Node[][] table, String action, Node extra) {
		Element form = doc.createElement("form");
		form.setAttribute("id", "requestForm");
		form.setAttribute("action", action);
		form.setAttribute("method", "get");

//		Element hiddenParamInput = doc.createElement("input");
//		hiddenParamInput.setAttribute("type", "hidden");
//		hiddenParamInput.setAttribute("name", "fromNavigator");
//		hiddenParamInput.setAttribute("style", "width: 100%");
//		hiddenParamInput.setAttribute("value", "true");
//		form.appendChild(hiddenParamInput);
		
		form.appendChild(generateHtmlTable(table));

		Element submit = doc.createElement("input");
		submit.setAttribute("type", "submit");
		submit.setAttribute("name", "action");
		submit.setAttribute("value", "Send");
		form.appendChild(submit);
		if (extra != null) {
			form.appendChild(extra);
		}
		return form;
	}

	@Override
	public void visit(Filter filter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visit(ProchainvolConfig prochainvolConfig) {
		Element grid_12 = createContainerAndGrid_12();


		// list of providers
		Text libelleRequestReader = doc.createTextNode("Request Reader");

		Element requestReaderTable = doc.createElement("table");
		List<PROVIDER> providers = Arrays.asList(PROVIDER
				.values());
		List<PROVIDER> validRequestReaders = Arrays
				.asList(prochainvolConfig.getCurrentProviders());
		providers.forEach(r -> {
			Element tr = doc.createElement("tr");
			Element td = doc.createElement("td");
			Element input = doc.createElement("input");
			input.setAttribute("type", "checkbox");
			input.setAttribute("name", "requestReaders");
			input.setAttribute("value", r.name());
			if (validRequestReaders.contains(r)) {
				input.setAttribute("checked", "checked");
			}
			td.appendChild(input);
			Text libelle = doc.createTextNode(" " + r.toString() + "\n");
			td.appendChild(libelle);
			tr.appendChild(td);
			requestReaderTable.appendChild(tr);
		});


		// Max stops
		Text libelleMaxStops = doc.createTextNode("Max Stops");

		Element divMaxStops = createStopUI(prochainvolConfig.getMaxStop());
		
		
		Node[][] table = { 
				{ libelleRequestReader, requestReaderTable },
				{ libelleMaxStops, divMaxStops } };

		grid_12.appendChild(putIntoHtmlForm(table, "Recharger"));

	}

	
	@Override
	public void visit(RequestParams requestparams) throws ProchainvolException {

		Element grid_12 = createContainerAndGrid_12();

		createRequestUI(requestparams, grid_12);
		
	}

	public void visit(RequestParams requestParams, String errorMessage) throws ProchainvolException {
		Element grid_12 = createContainerAndGrid_12();
		Element message = doc.createElement("div");
		message.setTextContent(errorMessage);
		message.setAttribute("style","color:red;");
		grid_12.appendChild(message);
		createRequestUI(requestParams, grid_12);
	}

	private Element createContainerAndGrid_12() {
		Element container = doc.createElement("div");
		container.setAttribute("class", "container_12");
		root.appendChild(container);

		Element grid = doc.createElement("div");
		grid.setAttribute("class", "grid_12");
		container.appendChild(grid);

		return grid;
	}

	private Element createRootDiv() {
		Element rootDiv = doc.createElement("div");
		rootDiv.setAttribute("style", "width: 100%; padding: 5px 1em;");
		return rootDiv;
	}

	private Element createStopUI(int nbStop) {
		logger.trace("nbStop = "+nbStop);
		Element stopsDiv = createRootDiv();
		Element stopsSelect = doc.createElement("select");
		stopsSelect.setAttribute("name", "stops");
		stopsDiv.appendChild(stopsSelect);
		for (int i = 0; i<4; i++) {
			Element option = doc.createElement("option");
			option.setAttribute("value", Integer.toString(i));
			if (i==nbStop) {
				option.setAttribute("selected", "selected");
			}
			Text libelle = doc.createTextNode(Integer.toString(i));
			option.appendChild(libelle);
			stopsSelect.appendChild(option);
		}
		Element optionAny = doc.createElement("option");
		optionAny.setAttribute("value", "Any");
		if (nbStop==Constants.ANY_VALUE_FOR_MAX_STOP) {
			optionAny.setAttribute("selected", "selected");
		}
		Text libelleAny = doc.createTextNode("Any");
		optionAny.appendChild(libelleAny);
		stopsSelect.appendChild(optionAny);
		return stopsDiv;
	}


}
