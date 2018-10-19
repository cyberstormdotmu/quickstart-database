/**
 * 项目名称：quickstart-datasource 
 * 文件名：UpdateRecords.java
 * 版本信息：
 * 日期：2018年10月15日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.jdbc.simple;

/**
 * UpdateRecords 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年10月15日 下午9:51:37 
 * @since 1.0
 */
import java.sql.*;

public class UpdateRecords {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/jdbc_db";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "123456";

   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql = "UPDATE student " +
                   "SET age = 22 WHERE id in (100, 101)";
      stmt.executeUpdate(sql);

      // Now you can extract all the records
      // to see the updated records
      sql = "SELECT id, first, last, age FROM student";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("id");
         int age = rs.getInt("age");
         String first = rs.getString("first");
         String last = rs.getString("last");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", Age: " + age);
         System.out.print(", First: " + first);
         System.out.println(", Last: " + last);
      }
      rs.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end JDBCExample

