package com.prochainvol.httpServlet;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.prochainvol.dom.DomUtilities;
import com.prochainvol.ui.EditResponsiveVisitor;
import com.prochainvol.ui.ProchainvolHeader;

/**
 * Servlet implementation class EditConfiguration
 */
@WebServlet("/EditConfiguration")
public class EditConfiguration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(EditConfiguration.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProchainvolConfig prochainvolConfig = (ProchainvolConfig) request.getSession().getAttribute(Constants.SESS_PROCHAINVOL_CONFIG);
		if (prochainvolConfig==null) {
			RequestDispatcher rd = request
					.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
		}

		try {
			EditResponsiveVisitor visitor = new EditResponsiveVisitor(prochainvolConfig);
			ProchainvolHeader prochainvolHeader = new ProchainvolHeader(
					"Edit Configuration", prochainvolConfig, true);
			prochainvolHeader.accept(visitor); // entête
			prochainvolConfig.accept(visitor);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out.write(DomUtilities.headers);
			out.print(visitor.getDocumentAsString());
		} catch (TransformerException | ProchainvolException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new ServletException(e);
		}
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
