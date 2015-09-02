package com.prochainvol.httpServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

import com.prochainvol.Constants;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.ReaderUtilities;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.dom.DomUtilities;
import com.prochainvol.ui.EditResponsiveVisitor;
import com.prochainvol.ui.ProchainvolHeader;

/**
 * Servlet implementation class EditConfiguration
 */
@WebServlet("/RequestUI")
public class RequestUI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(RequestUI.class
			.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

			ProchainvolConfig prochainvolConfig = (ProchainvolConfig) request
					.getSession().getAttribute(
							Constants.SESS_PROCHAINVOL_CONFIG);
			if (prochainvolConfig == null) {
				RequestDispatcher rd = request
						.getRequestDispatcher("LoginServlet");
				rd.forward(request, response);
			}
			EditResponsiveVisitor visitor = new EditResponsiveVisitor(
					prochainvolConfig);
			ProchainvolHeader prochainvolHeader = new ProchainvolHeader(
					"Try a Request", prochainvolConfig, true);
			prochainvolHeader.accept(visitor); // entête
			Date date1 = null;
			Date date2 = null;
			RequestParams requestParams = (RequestParams) request
					.getAttribute("params");

			String html = null;
			try {
				if (requestParams == null) {
					requestParams = new RequestParams(null, null, date1, date2, prochainvolConfig.getMaxStop());
				}
				String errorMessage = (String) request.getAttribute("errorMess");
				if (errorMessage == null) {
					requestParams.accept(visitor);
				} else {
					visitor.visit(requestParams, errorMessage);
				}
				html = visitor.getDocumentAsString();
			} catch (ProchainvolException | TransformerException e) {
				logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
				throw new ServletException(e);
			}
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out.write(DomUtilities.headers);
			out.print(html);

			InputStream in = this.getClass().getResourceAsStream(
					Constants.PROCHAINVOL_PROPS.getProperty("requestJs"));
			String jsScript = null;
			try {
				jsScript = ReaderUtilities.fromStream(in);
			} catch (IOException e) {
				logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
				throw new ServletException(e);
			}

			out.print("<script>");
			out.print(jsScript);
			out.print("</script>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
