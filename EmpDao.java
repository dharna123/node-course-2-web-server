package com.emp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.emp.entity.Employee;

public class EmpDao {
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
	String dbDriver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/";
	String dbname="demo";
	String username="root";
	String password="root";
	Class.forName(dbDriver);
	Connection con=DriverManager.getConnection(url+dbname,username,password);
	return con;
	}
	
	public static int save(Employee e)
	{
		int status=0;
		try
		{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into emp(name,password,email)values(?,?,?)");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			status=ps.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
}
