package com.air.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.air.domain.Airline;

/**
 * @数据库操作类
 * @author Dua
 */

public class Connector {
	private final String dbDriver = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/local?useUnicode=true&characterEncoding=utf8";
	private final String username = "root";
	private final String password = "123456";

	private   String curConnected = "";
	protected Connection con = null;
	protected Statement state = null;
	protected ResultSet res = null;

	public Connection ConnectLocal() {
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(url, username, password);
			curConnected = "Local";
		} 
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (SQLException e) { e.printStackTrace(); }
		return con;
	}//连接本地数据库
	
	public Connection ConnectAirline(Airline airline) {
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection(airline.getUrl(), airline.getUsername(), airline.getPassword());
			curConnected =airline.getId();
		} 
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (SQLException e) { e.printStackTrace(); }
		return con;
	}//连接航空公司数据库
	
	public boolean UpdateLocal(String sql) {
		int result = 0;
		if (con == null || curConnected != "Local") { this.ConnectLocal(); }
		try {
			state = con.createStatement();
			result = state.executeUpdate(sql);
			if (result == 1) return true;
			else return false;
		} 
		catch (SQLException e) { e.printStackTrace(); return false; }
	}//更新本地数据库
	
	public boolean UpdateAirline(Airline airline, String sql) {
		int result = 0;
		if (con == null || curConnected != airline.getId()) { this.ConnectAirline(airline); }
		try {
			state = con.createStatement();
			result = state.executeUpdate(sql);
			if (result == 1) return true;
			else return false;
		} 
		catch (SQLException e) { e.printStackTrace(); return false; }
	}//更新航空公司数据库
	
	public ResultSet QueryLocal(String sql) {
		try {
			if (con == null || curConnected != "Local") { this.ConnectLocal(); }
			state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			try { res = state.executeQuery(sql); } 
			catch (SQLException e) { e.getMessage(); }
		} 
		catch (SQLException e) { e.printStackTrace(); }
		return res;
	}//查询本地数据库
	
	public ResultSet QueryAirline(Airline airline, String sql) {
		try {
			if (con == null || curConnected != airline.getId()) { this.ConnectAirline(airline); }
			state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			try { res = state.executeQuery(sql); } 
			catch (SQLException e) { e.getMessage(); }
		} 
		catch (SQLException e) { e.printStackTrace(); }
		return res;
	}//查询航空公司数据库

	public void closeAll(Connection con, Statement sta, ResultSet res) {
		try { if (res != null) res.close(); } 
		catch (SQLException e) { e.printStackTrace(); }
	
		try { if (sta != null) sta.close(); } 
		catch (SQLException e) { e.printStackTrace(); }
		
		try { if (con != null) con.close(); } 
		catch (SQLException e) { e.printStackTrace(); }
	}//关闭数据库

	public void closeCon(Connection con) {
		try { if (con != null) con.close(); } 
		catch (SQLException e) { e.printStackTrace(); }
	}
	
}



