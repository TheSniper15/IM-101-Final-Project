/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SP.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bjbab
 */
public class dbConn {
    public Connection con;
	public PreparedStatement pst;
	public ResultSet rs;
        public ResultSetMetaData rss;
	
	private final String uName = "root";
	private final String pass = "";
	private final String host = "jdbc:mysql://localhost:3306/library";
	
	public void Connect()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(host, uName, pass);
		} 
		catch (ClassNotFoundException ex) 
		{
			Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (SQLException ex) 
		{
			Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
