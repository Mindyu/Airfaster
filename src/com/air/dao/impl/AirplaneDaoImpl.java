package com.air.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.air.dao.IAirplaneDao;
import com.air.domain.Airline;
import com.air.domain.Airplane;
import com.air.domain.Airseat;
import com.air.util.Connector;

public class AirplaneDaoImpl implements IAirplaneDao {
	Connector factory = new Connector();
	private Connection con = null;
	private PreparedStatement prst = null;
	
	@Override
	public void add(Airline airline, Airplane airplane) {
		// TODO Auto-generated method stub
		String addSql = "insert into airplane(id, name, airlineID, row, leftSeat) values(?,?,?,?,?)";
		con = factory.ConnectAirline(airline);
		try{
			prst = con.prepareStatement(addSql);
			prst.setString(1, airplane.getId());
			prst.setString(2, airplane.getName());
			prst.setString(3, airplane.getAirlineID());
			prst.setInt(4, airplane.getRow());
			prst.setInt(5, airplane.getLeftSeat());
			prst.executeUpdate();
			System.out.println("插入飞机成功");
			Airseat seat = new Airseat();
			AirseatDaoImpl airseatDaoImpl = new AirseatDaoImpl();
			for(int i = 1; i <= airplane.getRow(); ++i) {
				for(int j = 1; j <= 6; ++j) {
					seat.setRow(i); seat.setCol(j); 
					seat.setAirplaneID(airplane.getId());
					seat.setState(false);
					airseatDaoImpl.add(airline, airplane, seat);
				}
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@Override
	public void delete(Airline airline, Airplane airplane) {
		// TODO Auto-generated method stub
		String deletesql = "delete from airplane where id = ?";
		con = factory.ConnectAirline(airline);
		try{
			AirseatDaoImpl airseatDaoImpl = new AirseatDaoImpl();
			airseatDaoImpl.deleteAll(airline, airplane);
			
			prst = con.prepareStatement(deletesql);
			prst.setString(1, airplane.getId());
			prst.executeUpdate();
			System.out.println("删除飞机成功");
			
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
		
	}

	@Override
	public void update(Airline airline, Airplane airplane) {
		// TODO Auto-generated method stub
		//update orders set droped = '0' where id = ?
		String updateSql = "update airplane set leftSeat = ? where id = ? ";
		con = factory.ConnectAirline(airline);
		try{
			prst = con.prepareStatement(updateSql);
			prst.setInt(1, airplane.getLeftSeat());
			prst.setString(2, airplane.getId());
			prst.executeUpdate();
			System.out.println("修改飞机成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}
	
	@SuppressWarnings("finally")
	@Override
	public Airplane findByID(String airplaneID) {
		// TODO Auto-generated method stub
		String sql = "select * from airplane where id = ?";
		ArrayList<Airline> airlines = Singleton.AirlineRegistDao().findAll();
		Airplane airplane = new Airplane();
		
		try{
			for (Airline airline : airlines) {
				con = factory.ConnectAirline(airline);
				ResultSet res = null; 
						
				prst = con.prepareStatement(sql);
				prst.setString(1, airplaneID);
				res = prst.executeQuery();
				if(res.next()){			
					airplane.setId(res.getString(1));
					airplane.setName(res.getString(2));
					airplane.setAirlineID(res.getString(3));
					airplane.setRow(res.getInt(4));
					airplane.setLeftSeat(res.getInt(5));
					break; 
				}
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return airplane; }

	}

	@SuppressWarnings("finally")
	@Override
	public Airplane findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from airplane where name = ?";
		ArrayList<Airline> airlines = Singleton.AirlineRegistDao().findAll();
		Airplane airplane = new Airplane();
		
		try{
			for (Airline airline : airlines) {
				con = factory.ConnectAirline(airline);
				ResultSet res = null; 
						
				prst = con.prepareStatement(sql);
				prst.setString(1, name);
				res = prst.executeQuery();
				if(res.next()){			
					airplane.setId(res.getString(1));
					airplane.setName(res.getString(2));
					airplane.setAirlineID(res.getString(3));
					airplane.setRow(res.getInt(4));
					airplane.setLeftSeat(res.getInt(5));
					break; 
				}
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return airplane; }
	}
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<Airplane> findAll(Airline airline) {
		ArrayList<Airplane> airplanes = new ArrayList<Airplane>();
    	String sql = "select * from airplane";
		con = factory.ConnectAirline(airline);
		
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			res = prst.executeQuery();
			while(res.next()){	
				Airplane airplane = new Airplane();
				airplane.setId(res.getString(1));
				airplane.setName(res.getString(2));
				airplane.setAirlineID(res.getString(3));
				airplane.setRow(res.getInt(4));
				airplane.setLeftSeat(res.getInt(5));
				airplanes.add(airplane);
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return airplanes; }
	}
	
	@Override
	public void deleteAll(Airline airline) {
		ArrayList<Airplane> airplanes = findAll(airline);
		for (Airplane airplane : airplanes) {
			delete(airline, airplane);
		}
	}
	
}
