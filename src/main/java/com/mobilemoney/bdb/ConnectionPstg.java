package com.mobilemoney.bdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionPstg {
	Connection co;
	Statement stmt;
	ResultSet rs;
	public Connection getConnection(){
		return this.co;
	}
	public void clear()throws Exception{
		co.close();
		stmt.close();
	}
	public Statement getStatement(){
		return this.stmt;
	}
	public ConnectionPstg(){
            
            try{
                Class.forName("org.postgresql.Driver");
                this.co = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mobilepostgres",
                   "postgres", "root");
                this.stmt = this.co.createStatement();
                this.co.setAutoCommit(false);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }	
	}
	public void setAutoCommit(boolean value)throws Exception{
		this.co.setAutoCommit(value);
	}
	public void commit()throws Exception{
		this.co.commit();
	}
	public void rollback()throws Exception{
		this.co.rollback();
	}
}
