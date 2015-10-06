<%@ page import="com.prochainvol.ProchainvolConfig"%>
<%@ page import="com.prochainvol.json.ProchainvolObject"%>
<%@ page import="com.prochainvol.ui.UiUtilities"%>
<%@ page import="com.prochainvol.ui.ProchainvolHeader"%>
<%@ page import="com.prochainvol.Constants"%>
<%@ page import="com.prochainvol.httpServlet.Request"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.prochainvol.ui.IAffichable"%>
<%@ page import="com.prochainvol.ui.ResponsiveVisitor"%>
<%@ page import="com.prochainvol.ui.UiUtilities"%>
<%@ page import="com.prochainvol.dom.DomUtilities"%>

<%!private static final Logger logger = Logger
			.getLogger("com.prochainvol.configuration_jsp");%>
<%
	ProchainvolConfig prochainvolConfig = (ProchainvolConfig) session
	.getAttribute(Constants.SESS_PROCHAINVOL_CONFIG);
	if (prochainvolConfig == null) {
		RequestDispatcher rd = request
		.getRequestDispatcher("LoginServlet");
		rd.forward(request, response);
	}

	ProchainvolHeader prochainvolHeader = new ProchainvolHeader(
	"Etat de la configuration", true);
	boolean withReportUnit = false;
	ProchainvolObject[] affichables = { prochainvolConfig };
	String typeParamName = "output";
	String type = request.getParameter(typeParamName);
	if (type == null || type.equals("json")) {
		String json = UiUtilities.getJsonContent(withReportUnit,
		affichables);

		response.setContentType("application/json; charset=UTF-8");
		out.write(json);

	} else if (type.equals("html")) {
		ResponsiveVisitor visitor = new ResponsiveVisitor(
		prochainvolConfig);
		String html = UiUtilities.getHtmlContent(visitor,
		prochainvolHeader, affichables);

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		out.write(DomUtilities.headers);
		out.write(html);

	} else {
		String msg = String.format(
		"valeur de parametre '%s' ignorée = %", typeParamName,
		type);
		logger.error(msg);
		throw new ServletException(msg);
	}
%>
