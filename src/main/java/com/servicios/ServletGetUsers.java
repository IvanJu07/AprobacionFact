package com.servicios;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ClassCloudSql;

/**
 * Servlet implementation class getUsers
 */
@WebServlet("/ServletGetUsers")
public class ServletGetUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = "SELECT * FROM usuarios ORDER BY ordertable;";
		 ClassCloudSql get = new ClassCloudSql();
		 PrintWriter out = response.getWriter();
		 try
		 {  
			String jsonString = get.select(query);
			response.setContentType("text/plain"); 
			response.addHeader("Access-Control-Allow-Origin", "*");
	        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
	        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	        response.addHeader("Access-Control-Max-Age", "1728000");
		    out.println(jsonString);  
		  }catch(Exception e)
		  {  
			  out.println("erro");  
		      e.printStackTrace();  
		  } 
	}

}
