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
                this.co = DriverManager.getConnection("jdbc:postgresql://ec2-54-155-35-88.eu-west-1.compute.amazonaws.com:5432/d6ffhaoav331nd",
                   "cmnssbokuojoyd", "a92a451fcf24df85597d95be05b5212b09b9f3bd1d38eeac7df365fd763f1a9d");
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
