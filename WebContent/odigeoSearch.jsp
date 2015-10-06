<%@ page import="javax.xml.datatype.DatatypeConfigurationException"%>
<%@ page import="javax.xml.datatype.DatatypeFactory"%>
<%@ page import="javax.xml.datatype.XMLGregorianCalendar"%>

<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.odigeo.metasearch.metasearch.ws.v2.CabinClass"%>
<%@ page import="com.odigeo.metasearch.metasearch.ws.v2.Carrier"%>
<%@ page
	import="com.odigeo.metasearch.metasearch.ws.v2.ItinerariesLegend"%>
<%@ page
	import="com.odigeo.metasearch.metasearch.ws.v2.ItineraryLocationRequest"%>
<%@ page
	import="com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearchRequest"%>
<%@ page
	import="com.odigeo.metasearch.metasearch.ws.v2.ItinerarySegmentRequest"%>
<%@ page import="com.odigeo.metasearch.metasearch.ws.v2.Preferences"%>
<%@ page
	import="com.odigeo.metasearch.metasearch.ws.v2.PreferencesRequest"%>
<%@ page import="com.odigeo.metasearch.metasearch.ws.v2.Search"%>
<%@ page import="com.odigeo.metasearch.metasearch.ws.v2.SearchRequest"%>
<%@ page
	import="com.odigeo.metasearch.metasearch.ws.v2.SearchResultsPage"%>
<%@ page
	import="com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse"%>
<%@ page import="com.prochainvol.Constants"%>
<%@ page import="com.prochainvol.TestConstants"%>
<%@ page import="com.prochainvol.sql.airlines.AirlineCompany"%>
<%@ page import="com.prochainvol.json.JsonUtilities"%>
<%@ page
	import="com.prochainvol.api.provider.odigeo.OdigeoRequestBuilder"%>
<%@ page import="com.prochainvol.api.request.RequestParams"%>
<%@ page import="com.prochainvol.api.EXECUTOR_TYPE"%>
<%@ page
	import="com.prochainvol.api.provider.odigeo.AbstractOdigeoExecutor"%>
<%@ page
	import="com.prochainvol.api.provider.odigeo.OdigeoExecutorDirectSync"%>
<%@ page
	import="com.prochainvol.api.provider.odigeo.OdigeoExecutorBamSync"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>

<%!private static final Logger logger = Logger.getLogger("com.prochainvol.odigeoSearch_jsp");

	private static final Gson gson = JsonUtilities.getGsonPretty();%>
<%
	OdigeoRequestBuilder odigeoRequestBuilder = new OdigeoRequestBuilder();
	String paramsAsString = request.getParameter("params");

	Map<String, Search> searches = null;
	RequestParams params = null;
	if (paramsAsString == null) {
		logger.info("odigeoSearch.jps has been called without params, a default one is provided");
		params= TestConstants.oneMonthToGoRequestParamFromJson;
	} else {
		params = gson.fromJson(paramsAsString, RequestParams.class);
	}
	logger.trace("params = " + params);
	searches = odigeoRequestBuilder.buildRequest(params);
	logger.trace("search = " + gson.toJson(searches));

	AbstractOdigeoExecutor odigeoExecService = Constants.IS_WINDOWS ? new OdigeoExecutorBamSync()
			: new OdigeoExecutorDirectSync();

	// demande de service
	List<SearchStatusResponse> searchStatusResponse = odigeoExecService.execService(searches);

	response.setContentType("application/json");
	// Get the printwriter object from response to write the required json object to the output stream      
	// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
	out.print(gson.toJson(searchStatusResponse));
	out.flush();
%>

