package com.prochainvol.httpServlet;

import java.io.IOException;
import java.net.URLEncoder;

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
import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.json.JsonUtilities;

@WebServlet("/StartTravelShacker")
public class StartTravelShacker extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(StartTravelShacker.class
			.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		initConfig(request, "travelshacker");
		response.setContentType("application/json; charset=UTF-8");
		try {
			response.getWriter().write(JsonUtilities.createResponseOk());
		} catch (IOException e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			throw new ServletException(e);
		}
	}

	public void forwardError(HttpServletRequest request,
			HttpServletResponse response, String password,
			final String errorMess) throws ServletException, IOException {
		logger.info(errorMess);
		RequestDispatcher rd = request
				.getRequestDispatcher("login.jsp?errorMess="
						+ URLEncoder.encode(errorMess, "UTF-8"));
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void initConfig(HttpServletRequest request, String userName)
			throws ServletException {
		HttpSession session = request.getSession();
		// time in seconds
		if (userName.equals("travelshacker")) {
			session.setMaxInactiveInterval(Constants.DEFAULT_MAX_INACTIVE_INTERVAL);
		} else {
			session.setMaxInactiveInterval(Constants.DEFAULT_TRAVESHACKER_MAX_INACTIVE_INTERVAL);			
		}
		try {
			String remoteHost = request.getRemoteHost();
			String remoteAddr = request.getRemoteAddr();
			logger.info(String.format("Starting session for %s on %s, IP is %s", userName, remoteHost, remoteAddr));
			ProchainvolConfig config = ProchainvolConfig.createDefaultProchainvolConfig();
			config.setUser(userName);
			session.setAttribute(Constants.SESS_PROCHAINVOL_CONFIG, config);
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setUserName(userName);
			sessionInfo.setRemoteHost(remoteHost);
			sessionInfo.setRemoteAddr(remoteAddr);
			session.setAttribute(Constants.SESS_SESSION_INFO, sessionInfo);
			logger.info(String.format("Starting session %s", sessionInfo));
		} catch (Throwable e) {
			logger.fatal(ProchainvolUtilities.getStackTraceAsString(e));
			session.invalidate();
			throw new ServletException(e);
		} 
	}
}