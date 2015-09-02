package com.prochainvol.httpServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.prochainvol.Constants;
import com.prochainvol.ProchainvolConfig;

/**
 * Servlet implementation class Disconnect
 */
@WebServlet("/Disconnect")
public class Disconnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = Logger.getLogger(Disconnect.class
			.getName());


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProchainvolConfig prochainvolConfig = (ProchainvolConfig) session
				.getAttribute(Constants.SESS_PROCHAINVOL_CONFIG);
		RequestDispatcher rd = request
				.getRequestDispatcher("LoginServlet");
		if (prochainvolConfig == null) {
			rd.forward(request, response);
		}
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.SESS_SESSION_INFO);
		logger.info(String.format("Fin de session par l'utilisateur %s", sessionInfo));
		session.invalidate();
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
