package com.air.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.air.dao.ICityRegistDao;
import com.air.domain.City;
import com.air.util.Connector;

public class CityRegistDaoImpl implements ICityRegistDao {
	Connector factory = new Connector();
	private Connection con = null;
	private PreparedStatement prst = null;
	
	@Override
	public void add(City city) {
		// TODO Auto-generated method stub
		String addSql = "insert into city(name, country) values(?,?)";
		con = factory.ConnectLocal();
		try{
			prst = con.prepareStatement(addSql);
			prst.setString(1, city.getName());
			prst.setString(2, city.getCountry());
			prst.executeUpdate();
			System.out.println("注册城市成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		String deletesql = "delete from city where name = ?";
		con = factory.ConnectLocal();
		try{
			prst = con.prepareStatement(deletesql);
			prst.setString(1, name);
			prst.executeUpdate();
			System.out.println("解除城市成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
		
	}

	@SuppressWarnings("finally")
	@Override
	public City findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from city where name = ?";
		con = factory.ConnectLocal();
		City city = new City(); city.setName("no");
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			prst.setString(1, name);
			res = prst.executeQuery();
			if(res.next()){			
				city.setName(res.getString(1));
				city.setCountry(res.getString(2));
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return city; }
	}
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<City> findAll() {
		ArrayList<City> cities = new ArrayList<City>();
		String sql = "select * from city";
		con = factory.ConnectLocal();
		
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			res = prst.executeQuery();
			while(res.next()){	
				City city = new City();
				city.setName(res.getString(1));
				city.setCountry(res.getString(2));
				cities.add(city);
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return cities; }
	}//查询所有的城市信息

}
