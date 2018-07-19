package com.btcetrade.bitcoin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentServlet extends HttpServlet{
	
	protected void doGET(HttpServletRequest request,HttpServletResponse response){
		doPOST(request, response);
	}
	protected void doPOST(HttpServletRequest request,HttpServletResponse response){
		try{
			PrintWriter out=response.getWriter();
			out.println("<html>"
					+ "<head>"
					+ "<title>BTC Etrade"
					+ "</title>"
					+ "<body>ssssss"
					+ "</body>"
					+ "</html>"
					+ "</head>");
			out.close();
	
		}catch(Exception e){
			e.printStackTrace();
		}
			}

}
