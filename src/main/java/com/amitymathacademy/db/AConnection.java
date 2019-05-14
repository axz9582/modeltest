package com.amitymathacademy.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class AConnection {
	String server;
    String dbname;
    static String username = "dev_user";
    static String password = "dev_pass";
    static String url = "jdbc:mysql://localhost/modeltestapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static Connection connection;
    public AConnection()
    {
    	this(url);
    }
    public AConnection(String u)
    {
        this.url=u;
        try
        {
            BufferedReader tr = new BufferedReader(new FileReader(new File("sgdz.properties")));
            String s = tr.readLine();
            String [] up = s.split("[=]");
            if (up != null && up.length > 1)
            {
                username = up[0];
                password = up[1];
            }
            tr.close();

        }
        catch (Exception e)
        {
        }
        try
        {

            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        	connection =    DriverManager.getConnection(url,username, password);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }

    }

    public  static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {

                Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            	connection =    DriverManager.getConnection(url,username, password);
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            	//show it
            }
        }
        return connection;
    }

    public  static Connection getNewConnection()
    {
    	Connection newConn = null;
        //if (connection == null)
        {
            try
            {

                Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            	newConn =    DriverManager.getConnection(url,username, password);
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            	//show it
            }
        }
        return newConn;
    }
}
