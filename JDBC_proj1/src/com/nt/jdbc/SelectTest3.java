package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {
     
	public static void main(String[] args) {
		 Scanner sc=null;
		 Connection con=null;
		 Statement st=null;
		 ResultSet rs=null;
		 try {
			 //read inputs
			 sc=new Scanner(System.in);
			 String initChars=null;
			 if(sc!=null) {
			 System.out.println("Enter the initial characters of Employee name::");
			 initChars=sc.next(); //gives s
			 }
			 //Convert input value as required  for the sql query
			 initChars="'"+initChars+"%'"; //gives 's%'
			 
			 //Register JDBC driver by loading JDBC driver class
			 		//Class.forName("oracle.jdbc.driver.OracleDriver");
			 
			 //Establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Appa");
			 //create Statement object
			 if(con!=null) 
				 st=con.createStatement();
				 //prepare a SQL query 
			 
				 //select empno,ename,job,sal from emp where ename like 'S%';
				 String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE"+initChars;
				 System.out.println(query);
				 		if(st!=null) 
					 rs=st.executeQuery(query);
				 
				 //Process the ResultSet object
				 if(rs!=null) {
					 boolean flag=false;
					 while(rs.next()!=false) {
						 flag=true;
						 System.out.println(rs.getInt(1)+"          "+rs.getString(2)+"       "+rs.getString(3)+"        "+rs.getFloat(4));
					 }//while
					 if(flag==false)
						 System.out.println("No Records found");
				 }//if
			 }//try
			catch(SQLException se) {
				if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
					System.out.println("Invalid  column names or table names or SQL Keywords");
				se.printStackTrace();
			}
			catch(Exception e) {
			e.printStackTrace();
			} 
			finally {
				//close jdbc objects
				try {
					if(rs!=null)
						rs.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
				
				try {
					if(con!=null)
						con.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
				
				try {
					if(sc!=null)
						rs.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			} //finally
		 	
		 }
	}



/*

Enter the initial characters of Employee name::
S
SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE'S%'
7369          SMITH       CLERK             800.0
7788          SCOTT       ANALYST        3000.0

*/