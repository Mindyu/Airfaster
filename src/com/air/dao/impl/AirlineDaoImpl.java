package com.air.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.air.dao.IAirlineDao;
import com.air.domain.Airline;
import com.air.util.Connector;

public class AirlineDaoImpl implements IAirlineDao {
	Connector factory = new Connector();
	private Connection con = null;
	private PreparedStatement prst = null;
	
	@Override
	public void setInfo(Airline airline) {
		// TODO Auto-generated method stub
		String delSql = "delete from airline";
		String addSql = "insert into airline(id, name, discount, url, username, password) values(?,?,?,?,?,?)";
		con = factory.ConnectAirline(airline);
		
		try{
			prst = con.prepareStatement(delSql);
			prst.executeUpdate();
			prst = con.prepareStatement(addSql);
			prst.setString(1, airline.getId());
			prst.setString(2, airline.getName());
			prst.setInt(3, airline.getDiscount());
			prst.setString(4, airline.getUrl());
			prst.setString(5, airline.getUsername());
			prst.setString(6, airline.getPassword());
			prst.executeUpdate();
			System.out.println("配置航空公司信息成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@SuppressWarnings("finally")
	@Override
	public Airline getInfo(Airline airline) {
		// TODO Auto-generated method stub
		String sql = "select * from airline";
		con = factory.ConnectAirline(airline);
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			res = prst.executeQuery();
			if(res.next()){			
				airline.setId(res.getString(1));
				airline.setName(res.getString(2));
				airline.setDiscount(res.getInt(3));
				airline.setUrl(res.getString(4));
				airline.setUsername(res.getString(5));
				airline.setPassword(res.getString(6));
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return airline; }
	}

}
