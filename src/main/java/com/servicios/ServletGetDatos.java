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
 * Servlet implementation class ServletGetDatos
 */
@WebServlet("/ServletGetDatos")
public class ServletGetDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = "select proyecto,Proveedor, NoDocumento, count(proyecto) as items, format(sum(Importe),2) as importe,"
				+ " correoPM from servicios group by proyecto,Proveedor, NoDocumento, correoPM;";
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
		    out.println(convertToUTF8(jsonString));  
		  }catch(Exception e)
		  {  
			  out.println("erro");  
		      e.printStackTrace();  
		  } 
	}
	public static String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
}
