package com.prochainvol.httpServlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.prochainvol.Constants;

public class ProchainvolSessionListener implements HttpSessionListener {

	private static final Logger logger = Logger
			.getLogger(ProchainvolSessionListener.class.getName());

	private static int totalActiveSessions;

	public static int getTotalActiveSession() {
		return totalActiveSessions;
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		totalActiveSessions++;
		HttpSession session = event.getSession();
		ServletContext context = session.getServletContext();
		Integer sessionCount = (Integer) context
				.getAttribute(Constants.CTX_SESSION_COUNT);
		if (sessionCount == null) {
			sessionCount = new Integer(0);
			context.setAttribute(Constants.CTX_SESSIONS_INFO,
					new HashMap<String, SessionInfo>());
		}
		context.setAttribute(Constants.CTX_SESSION_COUNT, ++sessionCount);



		logger.info(String.format("start session%d, nbActives = %d",
				sessionCount, totalActiveSessions));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ServletContext context = session.getServletContext();

		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute(Constants.SESS_SESSION_INFO);
		if (sessionInfo != null) {
			sessionInfo.setDuration();
			Map<String, SessionInfo> sessionsInfo = (Map<String, SessionInfo>) context
					.getAttribute(Constants.CTX_SESSIONS_INFO);
			;
			sessionsInfo.put(session.getId(), sessionInfo);
			context.setAttribute(Constants.CTX_SESSIONS_INFO, sessionsInfo);

			Integer sessionCount = (Integer) context
					.getAttribute(Constants.CTX_SESSION_COUNT);
			logger.info(String.format("end session%d, nbActives = %d",
					sessionCount, totalActiveSessions));
			totalActiveSessions--;
		}
	}
}