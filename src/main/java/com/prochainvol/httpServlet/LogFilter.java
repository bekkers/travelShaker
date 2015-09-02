package com.prochainvol.httpServlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
 
public class LogFilter implements Filter {
 
	private static final Logger logger = Logger
			.getLogger(LogFilter.class.getName());

    public void destroy() {
        //add code to release any resource
    }
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest request = (HttpServletRequest) req;
         
        //Get the IP address of client machine.
        String ipAddress = request.getRemoteAddr();
         
        //Log the IP address and current timestamp.
        logger.info("IP "+ipAddress + ", Time "
                            + new Date().toString());
         
        chain.doFilter(req, res);
    }
    public void init(FilterConfig config) throws ServletException {
         
        //Get init parameter
//        String testParam = config.getInitParameter("test-param");
         
        //Print the init parameter
//        System.out.println("Test Param: " + testParam);
    }
}