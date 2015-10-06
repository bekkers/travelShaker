<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.prochainvol.Constants"%>
<%@ page import="com.prochainvol.ProchainvolConfig"%>
<%@ page import="com.prochainvol.ReaderUtilities"%>
<%@ page import="com.prochainvol.dom.DomUtilities"%>
<%@ page import="com.prochainvol.ui.ResponsiveVisitor"%>
<%@ page import="com.prochainvol.ui.ProchainvolHeader"%>
    <% 
	ProchainvolConfig prochainvolConfig = (ProchainvolConfig) session
			.getAttribute(Constants.SESS_PROCHAINVOL_CONFIG);
	if (prochainvolConfig == null) {
		RequestDispatcher rd = request
				.getRequestDispatcher("LoginServlet");
		rd.forward(request, response);
	}
	ResponsiveVisitor visitor = new ResponsiveVisitor(prochainvolConfig);

	ProchainvolHeader prochainvolHeader = new ProchainvolHeader(
			Constants.API_TITLE, true);
	
	prochainvolHeader.accept(visitor); // entête
	response.setContentType("text/html; charset=UTF-8");
	response.setCharacterEncoding("UTF-8");
	out.write(DomUtilities.headers);
	out.print(visitor.getDocumentAsString());
	%>
<div class="container_12">
	<div class="grid_12">
<iframe src="informations.html" width="100%" 
     height="1000" style="border:2px solid orange">
</iframe> </div></div>