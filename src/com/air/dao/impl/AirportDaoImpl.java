package com.air.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.air.dao.IAirportDao;
import com.air.domain.Airline;
import com.air.domain.Airport;
import com.air.util.Connector;

public class AirportDaoImpl implements IAirportDao {
	Connector factory = new Connector();
	private Connection con = null;
	private PreparedStatement prst = null;
	
	@Override
	public void add(Airline airline, Airport airport) {
		// TODO Auto-generated method stub
		String addSql = "insert into airport(id, name, city, connectTime) values(?,?,?,?)";
		con = factory.ConnectAirline(airline);
		try{
			prst = con.prepareStatement(addSql);
			prst.setString(1, airport.getId());
			prst.setString(2, airport.getName());
			prst.setString(3, airport.getCity());
			prst.setInt(4, airport.getConnectTime());
			prst.executeUpdate();
			System.out.println("插入机场成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@Override
	public void delete(Airline airline, Airport airport) {
		// TODO Auto-generated method stub
		String deletesql = "delete from airport where id = ?";
		con = factory.ConnectAirline(airline);
		try{
			prst = con.prepareStatement(deletesql);
			prst.setString(1, airport.getName());
			prst.executeUpdate();
			System.out.println("删除机场成功");
			
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}
	
	@SuppressWarnings("finally")
	@Override
	public Airport findById(Airline airline, String id) {
		String sql = "select * from airport where id = ?";
		Airport airport = new Airport();
		
		try{
			con = factory.ConnectAirline(airline);
			ResultSet res = null; 
					
			prst = con.prepareStatement(sql);
			prst.setString(1, id);
			res = prst.executeQuery();
			if(res.next()){			
				airport.setId(res.getString(1));
				airport.setName(res.getString(2));
				airport.setCity(res.getString(3));
				airport.setConnectTime(res.getInt(4));
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return airport; }
	}
	
	@SuppressWarnings("finally")
	@Override
	public Airport findByName(Airline airline, String name) {
		String sql = "select * from airport where name = ?";
		Airport airport = new Airport();
		
		try{
			con = factory.ConnectAirline(airline);
			ResultSet res = null; 
					
			prst = con.prepareStatement(sql);
			prst.setString(1, name);
			res = prst.executeQuery();
			if(res.next()){			
				airport.setId(res.getString(1));
				airport.setName(res.getString(2));
				airport.setCity(res.getString(3));
				airport.setConnectTime(res.getInt(4));
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return airport; }
	}
}
