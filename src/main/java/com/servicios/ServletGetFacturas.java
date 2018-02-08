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
 * Servlet implementation class ServletGetFacturas
 */
@WebServlet("/ServletGetFacturas")
public class ServletGetFacturas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetFacturas() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String mes = request.getParameter("mes");
		 String query = " SELECT proyecto,"
		 			  + "NumeroEmpleado, "
		 			  + "TarifaPorHora,"
		 			  + "HorasTrabajadas,"
		 			  + "Importe,"
		 			  + "NombreEmpleado,"
		 			  + "correoPM,"
		 			  + "correoFuncionario,"
		 			  + "NoDocumento,"
		 			  + "Proveedor,"
		 			  + "Subproyecto,"
		 			  + "ClavePresupuestal,"
		 			  + "PeriodoServicio, "
		 			  + "tarifamensual "
		 			  + " FROM servicios " + 
				 		" WHERE PeriodoServicio = '"+mes+"'" + 
				 		" AND Estatus = 'APROBADO PARA FACTURARCION';";
		 ClassCloudSql get = new ClassCloudSql();
		 System.out.println(query);
		 PrintWriter out = response.getWriter();
		 try
		 {  
			String jsonString = get.selectServicios(query);
			
			response.setContentType("text/html;charset=UTF-8");
		    request.setCharacterEncoding("UTF-8");
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
