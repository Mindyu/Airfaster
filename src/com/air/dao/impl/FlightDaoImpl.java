package com.air.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.air.dao.IFlightDao;
import com.air.domain.Airline;
import com.air.domain.Flight;
import com.air.util.Connector;

public class FlightDaoImpl implements IFlightDao {
	Connector factory = new Connector();
	private Connection con = null;
	private PreparedStatement prst = null;
	
	@Override
	public void add(Airline airline, Flight flight) {
		// TODO Auto-generated method stub
		String addSql = "insert into flight(id, name, airplaneID, startAirportID, arrivAirportID, startCity, arrivCity, startTime, arrivTime, cost) values(?,?,?,?,?,?,?,?,?,?)";
		con = factory.ConnectAirline(airline);
		try{
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp startTimestamp = Timestamp.valueOf(flight.getStartTime());
			Timestamp arrivTimestamp = Timestamp.valueOf(flight.getArrivTime());
			System.out.println(f.format(arrivTimestamp));
			
			prst = con.prepareStatement(addSql);
			prst.setString(1, flight.getId());
			prst.setString(2, flight.getName());
			prst.setString(3, flight.getAirplaneID());
			prst.setString(4, flight.getStartAirportID());
			prst.setString(5, flight.getArrivAirportID());
			prst.setString(6, flight.getStartCity());
			prst.setString(7, flight.getArrivCity());
			prst.setTimestamp(8, startTimestamp);
			prst.setTimestamp(9, arrivTimestamp);
			prst.setDouble(10, flight.getCost());
			
			prst.executeUpdate();
			System.out.println("插入航班成功");
		}
		catch(SQLException e){ e.printStackTrace(); } 
		finally{ factory.closeCon(con); }
	}

	@Override
	public void delete(Airline airline, Flight flight) {
		// TODO Auto-generated method stub
		String deletesql = "delete from flight where id = ?";
		con = factory.ConnectAirline(airline);
		try{
			prst = con.prepareStatement(deletesql);
			prst.setString(1, flight.getId());
			prst.executeUpdate();
			System.out.println("删除航班成功");
			
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@Override
	public void update(Airline airline, Flight flight) {
		// TODO Auto-generated method stub
		String addSql = "update flight set leftseat = ? where id = ?";
		con = factory.ConnectAirline(airline);
		try{
			prst = con.prepareStatement(addSql);
			prst.setInt(1, flight.getLeftSeat());
			prst.setString(2, flight.getId());
			int res = prst.executeUpdate();
			if (res>0) {
				System.out.println("修改航班座位数成功");	
			}
		}
		catch(SQLException e){ e.printStackTrace(); } 
		finally{ factory.closeCon(con); }
	}
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<Flight> findAirline(Airline airline, Flight flight) {
		// TODO Auto-generated method stub
		ArrayList<Flight> flights = new ArrayList<Flight>();
    	String sql = "select * from flight where startCity = ? and arrivCity = ? and startTime > ? and startTime < ? ";
		con = factory.ConnectAirline(airline);
		
		ResultSet res = null;
		try{		
			Timestamp startTimestamp = Timestamp.valueOf(flight.getStartTime());
			Timestamp arrivTimestamp = Timestamp.valueOf(flight.getArrivTime());
			System.out.println(arrivTimestamp);
			prst = con.prepareStatement(sql);
			prst.setString(1, flight.getStartCity());
			prst.setString(2, flight.getArrivCity());
			prst.setTimestamp(3, startTimestamp);
			prst.setTimestamp(4, arrivTimestamp);
			
			res = prst.executeQuery();
			while(res.next()){	
				String s = res.getTimestamp(8).toString();
				String a = res.getTimestamp(9).toString();
				
				Flight flightt = new Flight();
				flightt.setId(res.getString(1));
				flightt.setName(res.getString(2));
				flightt.setAirplaneID(res.getString(3));
				flightt.setStartAirportID(res.getString(4));
				flightt.setArrivAirportID(res.getString(5));
				flightt.setStartCity(res.getString(6));
				flightt.setArrivCity(res.getString(7));
				flightt.setStartTime(s);
				flightt.setArrivTime(a);
				flightt.setCost(res.getDouble(10));
				flightt.setLeftSeat(res.getInt(11));
				flights.add(flightt);
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return flights; }
	}

	@Override
	public ArrayList<Flight> findAll(Flight flight) {
		// TODO Auto-generated method stub
		ArrayList<Airline> airlines = Singleton.AirlineRegistDao().findAll();
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		for (Airline airline : airlines) {
			con = factory.ConnectAirline(airline);
			ArrayList<Flight> flightt = Singleton.FlightDao().findAirline(airline, flight);
			flights.addAll(flightt);
		}
		return flights;
		
	}
	
	@SuppressWarnings("finally")
	@Override
	public Flight findById(String flightID) {
		ArrayList<Airline> airlines = Singleton.AirlineRegistDao().findAll();
		Flight flight = new Flight();
		try{	
			for (Airline airline : airlines) {
				String sql = "select * from flight where id = ?";
				con = factory.ConnectAirline(airline);
				ResultSet res = null;
					
				prst = con.prepareStatement(sql);
				prst.setString(1, flightID);
				res = prst.executeQuery();
				if(res.next()){	
					String s = res.getTimestamp(8).toString();
					String a = res.getTimestamp(9).toString();
					
					flight.setId(res.getString(1));
					flight.setName(res.getString(2));
					flight.setAirplaneID(res.getString(3));
					flight.setStartAirportID(res.getString(4));
					flight.setArrivAirportID(res.getString(5));
					flight.setStartCity(res.getString(6));
					flight.setArrivCity(res.getString(7));
					flight.setStartTime(s);
					flight.setArrivTime(a);
					flight.setCost(res.getDouble(10));
					flight.setLeftSeat(res.getInt(11));
					break; 
				}
				
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return flight; }
	}

	@Override
	public ArrayList<Flight> findAirline(Airline airline){
		// TODO Auto-generated method stub
		ArrayList<Flight> flights = new ArrayList<Flight>();
    	String sql = "select * from flight ";
		con = factory.ConnectAirline(airline);
		
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			res = prst.executeQuery(sql);
			while(res.next()){	
				String s = res.getTimestamp(8).toString();
				String a = res.getTimestamp(9).toString();
				
				Flight flightt = new Flight();
				flightt.setId(res.getString(1));
				flightt.setName(res.getString(2));
				flightt.setAirplaneID(res.getString(3));
				flightt.setStartAirportID(res.getString(4));
				flightt.setArrivAirportID(res.getString(5));
				flightt.setStartCity(res.getString(6));
				flightt.setArrivCity(res.getString(7));
				flightt.setStartTime(s);
				flightt.setArrivTime(a);
				flightt.setCost(res.getDouble(10));
				flightt.setLeftSeat(res.getInt(11));
				flights.add(flightt);
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return flights; }
	}

	@Override
	public ArrayList<Flight> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Airline> airlines = Singleton.AirlineRegistDao().findAll();
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		for (Airline airline : airlines) {
			con = factory.ConnectAirline(airline);
			ArrayList<Flight> flightt = Singleton.FlightDao().findAirline(airline);
			flights.addAll(flightt);
		}
		return flights;
	}

}
