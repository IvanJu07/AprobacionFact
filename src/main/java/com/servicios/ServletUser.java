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
 * Servlet implementation class ServletUser
 */
@WebServlet("/ServletUser")
public class ServletUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	String jsonResult;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//INPUT DEL FORM
		String email = request.getParameter("inputEmail");
		//VERIFICA SI EXISTE EL USUARIO
		String query = "SELECT * FROM usuarios WHERE email = '"+email+"' ";
		ClassCloudSql consulta = new ClassCloudSql();
		try 
		{
			jsonResult = consulta.select(query);
			response.setContentType("text/plain"); 
			response.addHeader("Access-Control-Allow-Origin", "*");
	        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
	        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	        response.addHeader("Access-Control-Max-Age", "1728000");
		    PrintWriter out = response.getWriter();
		    if(jsonResult.equals("[]")){
		    		//SI NO EXISTE REGRESA UN FALSE Y LA VARIABLE DE SESION ES NULL
		    		out.println(false); 
		    }
		    else
		    		out.println(jsonResult);   
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
