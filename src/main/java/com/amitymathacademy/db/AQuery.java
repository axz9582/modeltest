package com.amitymathacademy.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

public class AQuery {

    Connection connection;
    String query;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet rs;
    static Logger logger = null;
    public AQuery() 
    {

        if (logger == null) logger = Logger.getLogger(AQuery.class);
        connection = AConnection.getConnection();
    }
    public AQuery(String query)
    {
        this.query = query;
        if (logger == null) logger = Logger.getLogger(AQuery.class);
        logger.debug(query);
        connection =AConnection.getConnection();
        try
        {
	        statement = connection.createStatement();
	        rs = statement.executeQuery(query);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }

    public long getLong (String colName)
    {
        long s = 0;
        try
        {
        	s = rs.getLong(colName);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return s;
    
    	
    }
    public String getString(String colName)
    {
        String s = null;
        try
        {
        	s = rs.getString(colName);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return s;
    }

    public int getInt(int col)
    {
        int k = 0;
        try
        {
            k = rs.getInt(col);
        }
        catch (Exception e) { }
        return k;
    }


    public int getInt(String colName)
    {
        int k = 0;
        try
        {
            k = rs.getInt(colName);
        }
        catch (Exception e) { }
        return k;
    }

    public boolean getBoolean(String colName)
    {
        boolean b = false;
        try
        {
        	b = rs.getInt(colName)==1;
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return b;
    }

    public double getDouble(String colName)
    {
        double d = 0;
        try
        {
        	d= rs.getDouble(colName);
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        return d;
    }


    public Boolean next()
    {
    	try
    	{
        
    		if(rs==null) return false;
    		else return rs.next();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return false;
    }

    public PreparedStatement preparedStatement(String sql) throws Exception{
    	if(preparedStatement == null){
    		 preparedStatement = connection.prepareStatement(sql);
    	}
    	return preparedStatement;
    }
    
    public PreparedStatement preparedStatement(String sql, boolean force) throws Exception{
    	if(force){
    		 preparedStatement = connection.prepareStatement(sql);
    	}
    	return preparedStatement;
    }

    public void close()
    {
    	DbUtils.closeQuietly(null, statement, rs);
    
    }
    
    public void closePreparedStatement(){
    	try{
    		preparedStatement.close();
    	}
    	catch(Exception ignore){}
    }

    public void executeUpdate(String query)
    {System.out.println("----> "+query);
        try
        {
            logger.debug(query);
            statement = connection.createStatement();// new MySqlCommand(query, connection);
            statement.executeUpdate(query);
        }
        catch (Exception e)
        {
        	System.err.println(query);
            e.printStackTrace();
            throw new RuntimeException("error with: "+query);
            
        }
    }

}
