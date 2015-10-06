package com.prochainvol.httpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.prochainvol.dom.DomUtilities;

@WebServlet("/LoginForm")
public class LoginForm extends HttpServlet {

	private static final Logger logger = Logger.getLogger(LoginForm.class.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMess = request.getParameter("errorMess");
		PrintWriter out = response.getWriter();
		out.write("<html><head>");
		out.write(DomUtilities.headers);
		out.write("</head>\n");

		out.write(
				"<body><form action=\"LoginServlet\" onClick=\"$('body').addClass('loading')\" method=\"post\">\n<table>");
		out.write("<tr><td colspan=\"2\"><img src=\"images/logo2.png\"/></td></tr>");
		if (errorMess != null) {
			out.write("<tr><td colspan=\"2\"><span style=\"color=\"red\">");
			out.write(errorMess);
			out.write("</span></td></tr>\n");
		}
		out.write("<tr><td>Name</td><td><input type=\"text\" name=\"username\"/></td></tr>\n");
		out.write("<tr><td>Password</td><td><input type=\"password\" name=\"userpass\"/></td></tr>\n");
		out.write("<tr><td colspan=\"2\"><input type=\"submit\" value=\"login\"/></td></tr>\n");
		out.write("</table></form>\n");
		out.write("<div class=\"modal\"><!-- Place at bottom of page --></div>\n");
		out.write("<script>\n");
		out.write("$(document).on({");
		out.write("ajaxStop: function() { $('body').removeClass('loading'); }  ");
		out.write("});\n");
		out.write("</script>\n");
		out.write("</body></html>\n");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}