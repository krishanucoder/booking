package com.stays.bookingserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseDAO extends BaseDAO {

	private static final Logger logger = LogManager.getLogger(DatabaseDAO.class);
	
	@Value("${db.name}")
	private String dbName;

	public int checkTableName(String tableName) {
		
		if (logger.isInfoEnabled()) {
			logger.info("checkTableName -- START");
		}
		
		int result = 0;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = dbConnection.Connect();
			String query = "SELECT table_name FROM information_schema.tables where table_schema=='"+dbName+"'";
			//String query = "select tname from tab";
			pst = connection.prepareStatement(query);
			System.out.println("checkTableName ---- >>>"+pst);
			rs = pst.executeQuery();
			while (rs.next()) {
				
				if(tableName.equalsIgnoreCase(rs.getString(1))){
					
					result = 1;
					break;
					
				} else {
					
					result = 0;
				}
			}
			
		} catch (Exception e) {
			
			result = 0;
			
		} finally {

			 try{
		           
		           if(rs != null)
		            if(!rs.isClosed())
		            	rs.close();
		           
		          }catch(Exception e){
		                 e.printStackTrace();
		          }
			 
			 try{
		           
		           if(pst != null)
		            if(!pst.isClosed())
		            	pst.close();
		           
		          }catch(Exception e){
		                 e.printStackTrace();
		          }
			 
			try { // Closing Connection Object
				if (connection != null) {

					if (!connection.isClosed())
						connection.close();

					if (logger.isDebugEnabled()) {
						logger.debug("Connection Closed for checkTableName");
					}
				}
			} catch (Exception e) {

				if (logger.isDebugEnabled()) {
					logger.debug("Connection not closed for checkTableName"
							+ e.getMessage());
				}

			}

		}
		
		if (logger.isInfoEnabled()) {
			logger.info("checkTableName -- END");
		}
		
		return result;
	}
	
	public String fetchQuery(String query) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchQuery -- START");
		}
		
		String result = null;
		Connection connection = null;
		PreparedStatement pst = null;

		try {
			result = "<table width='90%' align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=1 style=\"border-collapse: collapse\" bordercolor=\"#111111\" >\r\n\t";
			result = result + "<tr bgcolor='#666beb' style='color:white;'>\r\n\t\t";
			connection = dbConnection.Connect();
			pst = connection.prepareStatement(query);
			System.out.println("fetchQuery ---- >>>"+pst);
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			//result = result + "<tr>";
			for (int i = 1; i <= numberOfColumns; i++) {
				
				String Name = rsMetaData.getColumnName(i);
				result = result + "<th width='10%' align='center'>";
				result = result + Name;
				result = result + "</th>";
			}
			result = result + "</tr>";
			while (rs.next()) {
				
				result = result + "<tr>";
				for (int i = 1; i <= numberOfColumns; i++) {
			
					String Type = rsMetaData.getColumnTypeName(i);
					if (Type.equals("numeric")) {
						
						result = result + "<td width='10%' align='center'>";
						result = result + rs.getInt(i);
						result = result + "</td>";
						
					} else {
						
						result = result + "<td width='10%' align='center'>";
						result = result + rs.getString(i);
						result = result + "</td>";
					}
				}
				result = result + "</tr>";
			}
			
			result = result + "</table>\r\n";
			
		} catch (Exception e) {
			
			result = e.getMessage();
			
		} finally {

			try{
				if(pst!=null)
				if(!pst.isClosed())
					pst.close();

				}catch(Exception e){
				e.printStackTrace();
				}

			try { // Closing Connection Object
				if (connection != null) {

					if (!connection.isClosed())
						connection.close();

					if (logger.isDebugEnabled()) {
						logger.debug("Connection Closed for insertQuery");
					}
				}
			} catch (Exception e) {

				if (logger.isDebugEnabled()) {
					logger.debug("Connection not closed for insertQuery"
							+ e.getMessage());
				}
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchQuery -- END");
		}
		
		return result;
	}
	
	public String insertQuery(String query) {
			
		if (logger.isInfoEnabled()) {
			logger.info("insertQuery -- START");
		}
		
		String result = null;
		int resultOfQuery = 0;
		Connection connection = null;
		PreparedStatement pst = null;

		try {
			connection = dbConnection.Connect();
			pst = connection.prepareStatement(query);
			System.out.println("insertQuery ---- >>>"+pst);
			resultOfQuery = pst.executeUpdate();
			if (resultOfQuery > 0)
				result = "Insert Successfully Done";
			else
				result = "Insert Can't Be Done";
			connection.commit();
			
		} catch (Exception e) {
			
			result = e.getMessage();
			
		} finally {

			try{
				if(pst!=null)
				if(!pst.isClosed())
					pst.close();

				}catch(Exception e){
				e.printStackTrace();
				}

			try { // Closing Connection Object
				if (connection != null) {

					if (!connection.isClosed())
						connection.close();

					if (logger.isDebugEnabled()) {
						logger.debug("Connection Closed for insertQuery");
					}
				}
			} catch (Exception e) {

				if (logger.isDebugEnabled()) {
					logger.debug("Connection not closed for insertQuery"
							+ e.getMessage());
				}
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("insertQuery -- END");
		}
		
		return result;
	}
	
	public String updateQuery(String query) {
		
		if (logger.isInfoEnabled()) {
			logger.info("updateQuery -- START");
		}
		
		String result = null;
		int resultOfQuery = 0;
		Connection connection = null;
		PreparedStatement pst = null;

		try {
			connection = dbConnection.Connect();
			pst = connection.prepareStatement(query);
			System.out.println("updateQuery ---- >>>"+pst);
			resultOfQuery = pst.executeUpdate();
			if (resultOfQuery > 0)
				result = "Update Successfully Done";
			else
				result = "Update Can't Be Done";
			connection.commit();
			
		} catch (Exception e) {
			
			result = e.getMessage();
			
		} finally {

			try{
				if(pst!=null)
				if(!pst.isClosed())
					pst.close();

				}catch(Exception e){
				e.printStackTrace();
				}

			try { // Closing Connection Object
				if (connection != null) {

					if (!connection.isClosed())
						connection.close();

					if (logger.isDebugEnabled()) {
						logger.debug("Connection Closed for updateQuery");
					}
				}
			} catch (Exception e) {

				if (logger.isDebugEnabled()) {
					logger.debug("Connection not closed for updateQuery"
							+ e.getMessage());
				}
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("updateQuery -- END");
		}
		
		return result;
	}
	
	public String deleteQuery(String query) {
		
		if (logger.isInfoEnabled()) {
			logger.info("deleteQuery -- START");
		}
		
		String result = null;
		int resultOfQuery = 0;
		Connection connection = null;
		PreparedStatement pst = null;

		try {
			connection = dbConnection.Connect();
			pst = connection.prepareStatement(query);
			System.out.println("deleteQuery ---- >>>"+pst);
			resultOfQuery = pst.executeUpdate();
			if (resultOfQuery > 0)
				result = "Delete Successfully Done";
			else
				result = "Delete Can't Be Done";
			connection.commit();
			
		} catch (Exception e) {
			
			result = e.getMessage();
			
		} finally {

			try{
				if(pst!=null)
				if(!pst.isClosed())
					pst.close();

				}catch(Exception e){
				e.printStackTrace();
				}

			try { // Closing Connection Object
				if (connection != null) {

					if (!connection.isClosed())
						connection.close();

					if (logger.isDebugEnabled()) {
						logger.debug("Connection Closed for deleteQuery");
					}
				}
			} catch (Exception e) {

				if (logger.isDebugEnabled()) {
					logger.debug("Connection not closed for deleteQuery"
							+ e.getMessage());
				}
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("deleteQuery -- END");
		}
		
		return result;
	}
}
