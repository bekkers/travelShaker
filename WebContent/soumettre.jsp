<%@ page import="com.prochainvol.ProchainvolConfig"%>
<%@ page import="com.prochainvol.ui.UiUtilities"%>
<%@ page import="com.prochainvol.ui.ProchainvolHeader"%>
<%@ page import="com.prochainvol.Constants"%>
<%@ page import="com.prochainvol.httpServlet.Request"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.prochainvol.ui.IAffichable"%>
<%@ page import="com.prochainvol.dom.DomUtilities"%>
<%@ page import="com.prochainvol.ui.ResponsiveVisitor"%>


<%!private static final Logger logger = Logger
			.getLogger("com.prochainvol.soumettre_jsp");%>
<%
	ProchainvolConfig prochainvolConfig = (ProchainvolConfig) session
			.getAttribute(Constants.SESS_PROCHAINVOL_CONFIG);
	if (prochainvolConfig == null) {
		RequestDispatcher rd = request
				.getRequestDispatcher("LoginServlet");
		rd.forward(request, response);
	}

	ProchainvolHeader prochainvolHeader = new ProchainvolHeader(
			"Voir une travel place", true);
	
	ResponsiveVisitor visitor = new ResponsiveVisitor(prochainvolConfig);
	prochainvolHeader.accept(visitor); // entête

	response.setContentType("text/html; charset=UTF-8");
	response.setCharacterEncoding("UTF-8");
	out.write(DomUtilities.headers);
	out.print(visitor.getDocumentAsString());
%>
<div class="container_12">
	<div class="grid_12">
	<form action="GetTravelPlace">
		<input type="hidden" name="which" value="iata" />Iata <input
			type="text" name="iata" /><input type="submit" value="soumettre" />
	</form>
	</div>
	</div>
