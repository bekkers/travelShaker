<%@ page import="com.prochainvol.Constants"%>
<%@ page import="com.prochainvol.ProchainvolConfig"%>
<%@ page import="com.prochainvol.dom.DomUtilities"%>
<%@ page import="com.prochainvol.ui.ResponsiveVisitor"%>
<%@ page import="com.prochainvol.ui.ProchainvolHeader"%>
<%@ page import="org.apache.log4j.Logger"%>
<%!private static final Logger logger = Logger.getLogger("com.prochainvol.index_jsp");

	String titre(ProchainvolConfig prochainvolConfig) {
		return Constants.API_NAME + " API admin v" + Constants.API_VERSION;
	}%>
<%
	logger.debug("on est entre dans index.jsp");

	ProchainvolConfig prochainvolConfig = (ProchainvolConfig) session
			.getAttribute(Constants.SESS_PROCHAINVOL_CONFIG);
	if (prochainvolConfig == null) {
		logger.debug("dispach vers login.jsp");
		RequestDispatcher rd = request
				.getRequestDispatcher("LoginForm");
		rd.forward(request, response);
	}
	ResponsiveVisitor visitor = new ResponsiveVisitor(prochainvolConfig);

	ProchainvolHeader prochainvolHeader = new ProchainvolHeader(
			titre(prochainvolConfig), false);

	prochainvolHeader.accept(visitor); // entête
	response.setContentType("text/html; charset=UTF-8");
	response.setCharacterEncoding("UTF-8");
	out.write(DomUtilities.headers);
	out.print(visitor.getDocumentAsString());
%>
<div class="container_12">
	<div class="grid_12">
		<div class="container_12">
			<div class="grid_6">
				<div class="center">
				<h4>Tests</h4>
				<a href="RequestUI">Request (Browse)</a><br />
				<a href="Bam">Bam (json)</a><br />
				<a href="GetTravelTypes">GetTravelTypes (json)</a><br />
				</div>
			</div>
			<div class="grid_6">
				<div class="center">
					<h4>Admin</h4>
					<form action="configuration.jsp" method="post">
						<input type="submit" value="See configuration" /> <select
							name="output" size="1">
							<option selected="selected">html</option>
							<option>json</option>
						</select>
					</form>
					<form action="EditConfiguration" method="post">
						<input type="submit" value="Edit configuration" />
					</form>
				<a href="informations.html" target="_blank">Documentation Travelshacker</a> 
				</div>
			</div>
		</div>
	</div>
</div>
