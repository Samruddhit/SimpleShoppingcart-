package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class OrderItemsServlet extends HttpServlet {
	PrintWriter out;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemno;
		String itemname;
		int price;
		try
		{
		out = response.getWriter();
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		if (name != null)
		{
		out.println("Welcome "+name+"<br>");
		out.println("<a href=\"/shopping_cart-simple/LogoutServlet\">Logout</a>");
		out.println("<br>");
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","tiger");
		st = con.prepareStatement("select * from Item");
		rs = st.executeQuery();
		out.println("<form method=\"post\" action=\"PlaceOrderServlet\">");
		out.println("<table border>");
		out.println("<tr>");
		out.println("<th>Select Item</th><th>Item Name</th><th>Price</th><th>Quantity</th>");
		out.println("</tr>");
		while (rs.next())
		{
			itemno=rs.getString("itemno");
			itemname=rs.getString("itemname");
			price = rs.getInt("price");
			out.println("<tr>");
			out.println("<td><input type=checkbox name=itemno value="+itemno+"></input></td>");
			out.print("<td>"+itemname+"</td>");
			out.print("<td>"+price+"</td>");
			out.println("<td><input type=text name=qty size=5></input></td>");
			out.println("<tr>");
			
		}
		out.println("</table>");
		out.println("<input type=submit value=\"Place Order\"/>");
		out.println("</form>");
		st.close();
		con.close();
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
