package com.hackathon.samurai.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This File is Created for Amazon Hackathon
 * Date : Sep 20, 2015
 */

/**
 * @author Vijayakumar Anbu
 * @project : Hackathon
 * @Date :Sep 20, 2015
 * @java Version : Java 8
 */
public class UserServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException{
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet Get</h1>");
		out.println("</body>");
		out.println("</html>");	
		
	}
	
}