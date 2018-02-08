package com.connection;
import com.google.apphosting.api.ApiProxy;
import java.sql.*;
import java.util.Map;
import javax.servlet.ServletException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ClassCloudSql{
	Connection conn = null;
	String outPut = "";
	
	public Connection CloudSqlFacturacion() throws ServletException 
	{
		ApiProxy.Environment env = ApiProxy.getCurrentEnvironment();
		Map<String,Object> attr = env.getAttributes();
		String hostname = (String) attr.get("com.google.appengine.runtime.default_version_hostname");
		String url = hostname.contains("localhost:")? System.getProperty("cloudsql-local-facturacion") : System.getProperty("cloudsql-facturacion");
		System.out.println("connecting to: " + url);
		try 
		{
			conn =  DriverManager.getConnection(url);
		}catch (SQLException e) 
		{
			throw new ServletException("No se puede conectar a Cloud Sql", e);
		}
		return conn;
	}
	public Connection CloudSqlServicios() throws ServletException 
	{
		ApiProxy.Environment env = ApiProxy.getCurrentEnvironment();
		Map<String,Object> attr = env.getAttributes();
		String hostname = (String) attr.get("com.google.appengine.runtime.default_version_hostname");
		String url = hostname.contains("localhost:")? System.getProperty("cloudsql-local-servicios") : System.getProperty("cloudsql-servicios");
		System.out.println("connecting to: " + url);
		try 
		{
			conn =  DriverManager.getConnection(url);
		}catch (SQLException e) 
		{
			throw new ServletException("No se puede conectar a Cloud Sql", e);
		}
		return conn;
	}
	
	/*
	public Connection CloudSqlFacturacion() throws SQLException, ServletException 
	{
		 Connection connection = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		    String servidor = "jdbc:mysql://35.184.195.186:3306/AprobacionFacturas";
	        String usuario = "root";
	        String pass = "123";
	        connection = DriverManager.getConnection(servidor, usuario, pass);
        } catch (ClassNotFoundException ex) {
        		throw new ServletException("No se puede conectar a Cloud Sql", ex);
        }
		return connection;
	}
	public Connection CloudSqlServicios() throws SQLException, ServletException 
	{
		 Connection connection = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
	        String servidor = "jdbc:mysql://35.226.67.205/AprobacionDeServicios";
	        String usuario = "root";
	        String pass = "123";
	        connection = DriverManager.getConnection(servidor, usuario, pass);
        } catch (ClassNotFoundException ex) {
        		throw new ServletException("No se puede conectar a Cloud Sql", ex);
        }
		return connection;
	}
	*/
	
	
	@SuppressWarnings({ "unchecked"})
	public String selectServicios(String query) throws ServletException, SQLException 
	{
	
	    JSONArray json = new JSONArray();
	    Connection conec = CloudSqlServicios();
	    Statement statement = conec.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
	    try  
	    {
	       
	        ResultSetMetaData metadata = resultSet.getMetaData();
	        int numColumns = metadata.getColumnCount();
	        while (resultSet.next()) 
	        {
	        	JSONObject obj = new JSONObject();    
	            for (int i = 1; i <= numColumns; ++i)  
	            {
	                String column_name = metadata.getColumnName(i);
	                obj.put(column_name, resultSet.getObject(column_name));
	            } 
	            json.add(obj);
	        }
	        System.out.println("Query successful");
	      }finally {
	    	    try { resultSet.close(); } catch (Exception e) { /* ignored */ }
	    	    try { statement.close(); } catch (Exception e) { /* ignored */ }
	    	    try { conec.close(); } catch (Exception e) { /* ignored */ }
	    	}
	    return json.toJSONString();
	} 
	@SuppressWarnings({ "unchecked"})
	public String select(String query) throws ServletException, SQLException 
	{
	
	    JSONArray json = new JSONArray();
	    Connection conec = CloudSqlFacturacion();
	    Statement statement = conec.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
	    try  
	    {
	       
	        ResultSetMetaData metadata = resultSet.getMetaData();
	        int numColumns = metadata.getColumnCount();
	        while (resultSet.next()) 
	        {
	        	JSONObject obj = new JSONObject();    
	            for (int i = 1; i <= numColumns; ++i)  
	            {
	                String column_name = metadata.getColumnName(i);
	                obj.put(column_name, resultSet.getObject(column_name));
	            } 
	            json.add(obj);
	        }
	        System.out.println("Query successful");
	      }finally {
	    	    try { resultSet.close(); } catch (Exception e) { /* ignored */ }
	    	    try { statement.close(); } catch (Exception e) { /* ignored */ }
	    	    try { conec.close(); } catch (Exception e) { /* ignored */ }
	    	}
	    return json.toJSONString();
	} 
	public String selectcsv(String query) throws ServletException, SQLException 
	{
		Connection conec = CloudSqlFacturacion();
		Statement statement = conec.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		String chido = "";
	    try 
	    {
	        
	        ResultSetMetaData metadata = resultSet.getMetaData();
	        int numColumns = metadata.getColumnCount();
	        while (resultSet.next()) 
	        {
	        	chido += resultSet.getObject(1).toString().trim().replaceAll("\n", "")+"\n";
	        	
	        }
	        System.out.println("Query successful");
	      }finally {
	    	    try { resultSet.close(); } catch (Exception e) { /* ignored */ }
	    	    try { statement.close(); } catch (Exception e) { /* ignored */ }
	    	    try { conec.close(); } catch (Exception e) { /* ignored */ }
	    	}
	    System.out.println(chido);
	    return chido;
	} 
	public String insert(String query) throws ServletException, SQLException 
	{
		Connection conec = CloudSqlFacturacion();
		Statement statement = conec.createStatement();
		
		try 
		{
			statement.executeUpdate(query); 
			System.out.println("Query successful");
			outPut = "Exito";
	     }catch (SQLException e) {
	    	  	throw new ServletException("Erro al insertar: "+e);
		}finally {
	    	    
	    	    try { statement.close(); } catch (Exception e) { /* ignored */ }
	    	    try { conec.close(); } catch (Exception e) { /* ignored */ }
	    	}
		return outPut;
	}
	@SuppressWarnings("unchecked")
	public String store_procedure(String query) throws ServletException, SQLException 
	{
		Connection conec = CloudSqlFacturacion();
		Statement statement = conec.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		JSONArray json = new JSONArray();
		    try 
		    {
		        
		        ResultSetMetaData metadata = resultSet.getMetaData();
		        int numColumns = metadata.getColumnCount();
		        while (resultSet.next()) 
		        {
		        	JSONObject obj = new JSONObject();    
		            for (int i = 1; i <= numColumns; ++i)  
		            {
		                String column_name = metadata.getColumnName(i);
		                obj.put(column_name, resultSet.getObject(column_name));
		            } 
		            json.add(obj);
		        }
		        System.out.println("Query successful");
		      }finally {
		    	    try { resultSet.close(); } catch (Exception e) { /* ignored */ }
		    	    try { statement.close(); } catch (Exception e) { /* ignored */ }
		    	    try { conec.close(); } catch (Exception e) { /* ignored */ }
		    	}
		    return json.toJSONString();
	}
}
