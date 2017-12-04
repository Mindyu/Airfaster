package com.air.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.air.dao.IAirlineRegistDao;
import com.air.domain.Airline;
import com.air.domain.City;
import com.air.util.Connector;

public class AirlineRegistDaoImpl implements IAirlineRegistDao {
	Connector factory = new Connector();
	private Connection con = null;
	private PreparedStatement prst = null;
	
	@Override
	public void add(Airline airline) {
		// TODO Auto-generated method stub
		String addSql = "insert into airline(id, name, discount, url, username, password) values(?,?,?,?,?,?)";
		con = factory.ConnectLocal();
		try{
			prst = con.prepareStatement(addSql);
			prst.setString(1, airline.getId());
			prst.setString(2, airline.getName());
			prst.setInt(3, airline.getDiscount());
			prst.setString(4, airline.getUrl());
			prst.setString(5, airline.getUsername());
			prst.setString(6, airline.getPassword());
			prst.executeUpdate();
			System.out.println("注册航空公司成功");
			
			//更新城市信息
			ArrayList<City> cities = Singleton.CityDao().findAll(airline);
			for (City city : cities) {
				City tmp = Singleton.CityRegistDao().findByName(city.getName());
				if(tmp.getName().equals(city.getName()) == false) Singleton.CityRegistDao().add(city);
			}
			System.out.println("更新本地城市成功");
			
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@Override
	public void deleteById(String airlineID) {
		// TODO Auto-generated method stub
		String deletesql = "delete from airline where id = ?";
		con = factory.ConnectLocal();
		try{
			
			prst = con.prepareStatement(deletesql);
			prst.setString(1, airlineID);
			prst.executeUpdate();
			System.out.println("解除航空公司成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
		
	}
	
	@Override
	public void deleteByName(String name) {
		String deletesql = "delete from airline where name = ?";
		con = factory.ConnectLocal();
		try{
			
			prst = con.prepareStatement(deletesql);
			prst.setString(1, name);
			prst.executeUpdate();
			System.out.println("解除航空公司成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@SuppressWarnings("finally")
	@Override
	public Airline findById(String airlineID) {
		// TODO Auto-generated method stub
		String sql = "select * from airline where id = ?";
		con = factory.ConnectLocal();
		Airline airline = new Airline();
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			prst.setString(1, airlineID);
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
	
	@SuppressWarnings("finally")
	@Override
	public Airline findByName(String name) {
		String sql = "select * from airline where name = ?";
		con = factory.ConnectLocal();
		Airline airline = new Airline();
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			prst.setString(1, name);
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
	
	
    @SuppressWarnings("finally")
    @Override
	public ArrayList<Airline> findAll() {
    	ArrayList<Airline> airlines = new ArrayList<Airline>();
    	String sql = "select * from airline ";
		con = factory.ConnectLocal();
		
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			res = prst.executeQuery();
			while(res.next()){	
				Airline airline = new Airline();
				airline.setId(res.getString(1));
				airline.setName(res.getString(2));
				airline.setDiscount(res.getInt(3));
				airline.setUrl(res.getString(4));
				airline.setUsername(res.getString(5));
				airline.setPassword(res.getString(6));
				airlines.add(airline);
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return airlines; }
    }

}
