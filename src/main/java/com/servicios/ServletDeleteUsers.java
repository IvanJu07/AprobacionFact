package com.servicios;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ClassCloudSql;

/**
 * Servlet implementation class ServletDeleteUsers
 */
@WebServlet("/ServletDeleteUsers")
public class ServletDeleteUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String query = " DELETE FROM usuarios"
					 + " WHERE email = '"+email+"';";
		ClassCloudSql consulta = new ClassCloudSql();
		System.out.println(query);
		try 
		{
			consulta.insert(query);
			response.setContentType("text/plain"); 
			response.addHeader("Access-Control-Allow-Origin", "*");
	        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD");
	        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	        response.addHeader("Access-Control-Max-Age", "1728000");
		    PrintWriter out = response.getWriter();
		    	out.println(true); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
