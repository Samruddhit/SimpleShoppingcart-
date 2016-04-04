package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PlaceOrderServlet extends HttpServlet {
	PrintWriter out;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
		out =response.getWriter();
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		if (name != null)
		{
		out.println("Welcome "+name+"<br>");
		out.println("<a href=\"/shopping_cart-simple/LogoutServlet\">Logout</a>");
		out.println("<br>");
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","tiger");
		String[] selected = request.getParameterValues("itemno");
		String[] qty = request.getParameterValues("qty");
		out.println("Items Ordered<br>");
		out.println("<form>");
		out.println("<table border>");
		out.println("<tr><th>Item Name</th><th>Price</th><th>Quantity</th></tr>");
		for(int i=0;i<selected.length;i++)
		{
			st = con.prepareStatement("select * from Item where itemno='"+selected[i]+"'");
			rs = st.executeQuery();
			while(rs.next())
			{
				String itemname = rs.getString("itemname");
				int price = rs.getInt("price");
				out.println("<tr><td>"+itemname+"</td>"+"<td>"+price+"</td>"+"<td>"+qty[i]+"</td></tr>");
			}
		}
		
		out.println("</table>");
		out.println("</form>");
		out.println("Order Placed Successfully");
		}
		else
		{
			out.println("Invalid Session<br>");
			out.println("<a href=\"login.jsp\">Click here </a> to login");
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request,response);
	}

}
