package com.coalsal.common.database;

import java.sql.Connection;
import java.sql.DriverManager;

 
public class DBConnection {
   private String username="ebase";
   private String password="admin";
   private String driver="com.mysql.jdbc.Driver";  
   private String url="jdbc:mysql://localhost:3306/coalbusiness";//?useUnicode=true&characterEncoding=utf8
   private Connection conn; 
   public DBConnection() throws Exception{
	   
	    
	   Class.forName(driver); 
	   
	  conn= DriverManager.getConnection(url, username, password);
	   
   }
   public Connection getConnection(){
	   return this.conn;
   }
   
   public static void main(String args[]) throws Exception{
	   DBConnection db=new DBConnection();
	   Connection conn=db.getConnection();
	   System.out.println(conn==null);
   }
   
   
   
}
