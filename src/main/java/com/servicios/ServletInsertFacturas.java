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
 * Servlet implementation class ServletInsertFacturas
 */
@WebServlet("/ServletInsertFacturas")
public class ServletInsertFacturas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsertFacturas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parametros = request.getParameter("parametros");
		String longitud = request.getParameter("long");
		String aux = request.getParameter("contador");
		
		String query = " INSERT INTO servicios "
					 + " SELECT "+parametros;
		ClassCloudSql consulta = new ClassCloudSql();
		//System.out.println(query);
		//System.out.println(longitud+" : "+aux);
		try 
		{
			consulta.insert(query);
			response.setContentType("text/plain"); 
			response.addHeader("Access-Control-Allow-Origin", "*");
	        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD");
	        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	        response.addHeader("Access-Control-Max-Age", "1728000");
		    PrintWriter out = response.getWriter();
		    if(longitud.equals(aux))
		    		out.println(true); 
		    else
		    		out.println(false); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
       
	}
	public static String convertFromUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

}
