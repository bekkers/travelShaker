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
import com.prochainvol.ProchainvolException;
import com.prochainvol.ProchainvolUtilities;
import com.prochainvol.User;
import com.prochainvol.Users;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(LoginServlet.class
			.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String userName = request.getParameter("username");
		String password = request.getParameter("userpass");

		if (userName == null || password == null) {
			final String errorMess = "Sorry username and password required";
			forwardError(request, response, password, errorMess);
		} else {
			User user = null;
			try {
				user = Users.getInstance().getUser(userName);
			} catch (ProchainvolException e) {
				logger.error(e);
				throw new ServletException(e);
			}
			if (user == null) {
				final String errorMess = "Sorry unknown username : " + userName;
				forwardError(request, response, password, errorMess);
			} else if (password.equals(user.getPassword())) {
				initConfig(request, userName);
				RequestDispatcher rd = request
						.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} else {
				final String errorMess = "Sorry incorrect password for this user";
				forwardError(request, response, password, errorMess);
			}
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
			ProchainvolConfig config =  new ProchainvolConfig();
			config.setUser(userName);
			config.init();
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