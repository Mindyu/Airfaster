package com.air.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.air.dao.IAirseatDao;
import com.air.domain.Airline;
import com.air.domain.Airplane;
import com.air.domain.Airseat;
import com.air.domain.Flight;
import com.air.util.Connector;

public class AirseatDaoImpl implements IAirseatDao {
	Connector factory = new Connector();
	private Connection con = null;
	private PreparedStatement prst = null;
	
	@Override
	public void add(Airline airline, Airplane airplane, Airseat airseat) {
		// TODO Auto-generated method stub
		String addSql = "insert into airseat(row, col, airplaneID, state) values(?,?,?,?)";
		con = factory.ConnectAirline(airline);
		try{
			prst = con.prepareStatement(addSql);
			prst.setInt(1, airseat.getRow());
			prst.setInt(2, airseat.getCol());
			prst.setString(3, airplane.getId());
			prst.setBoolean(4, airseat.getState());
			prst.executeUpdate();
			System.out.println("插入座位成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@SuppressWarnings("finally")
	@Override
	public Airseat findByPos(Airline airline, Airplane airplane, int row, int col) {
		// TODO Auto-generated method stub
		String sql = "select * from airseat where row = ? and col = ? and airplaneID = ?";
		con = factory.ConnectAirline(airline);
		Airseat seat = new Airseat();
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			prst.setInt(1, row);
			prst.setInt(2, col);
			prst.setString(3, airplane.getId());
			res = prst.executeQuery();
			if(res.next()){			
				seat.setRow(res.getInt(1));
				seat.setCol(res.getInt(2));
				seat.setAirplaneID(res.getString(3));
				seat.setState(res.getBoolean(4));
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return seat; }
	}

	@Override
	public void deleteAll(Airline airline, Airplane airplane) {
		// TODO Auto-generated method stub
		String deletesql = "delete from airseat where airplaneID = ?";
		con = factory.ConnectAirline(airline);
		try{
			prst = con.prepareStatement(deletesql);
			prst.setString(1, airplane.getId());
			prst.executeUpdate();
			System.out.println("删除座位成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<ArrayList<Airseat>> findAll(Airline airline, Flight flight) {
		// TODO Auto-generated method stub
		String sql = "select * from airseat where flightID = ? order by row asc, col asc";
		con = factory.ConnectAirline(airline);
		ArrayList<ArrayList<Airseat> > seatss = new ArrayList<ArrayList<Airseat>>();
		ResultSet res = null;
		
		try{		
			prst = con.prepareStatement(sql);
			prst.setString(1, flight.getId());
			res = prst.executeQuery();
			int i = 0;
			ArrayList<Airseat> seats = new ArrayList<Airseat>();
			while(res.next()){
				Airseat seat = new Airseat();
				seat.setRow(res.getInt(1));
				seat.setCol(res.getInt(2));
				seat.setAirplaneID(res.getString(3));
				seat.setState(res.getBoolean(4));
				seats.add(seat);
				++i;
				if(i%6==0) {
					seatss.add(seats); seats = new ArrayList<Airseat>();
				}
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return seatss; }
	}
	
	@SuppressWarnings("finally")
	public Airseat findByPos(Airline airline, Flight flight, int row, int col) {
		String sql = "select * from airseat where row = ? and col = ? and flightID = ?";
		con = factory.ConnectAirline(airline);
		Airseat seat = new Airseat();
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			prst.setInt(1, row);
			prst.setInt(2, col);
			prst.setString(3, flight.getId());
			res = prst.executeQuery();
			if(res.next()){			
				seat.setRow(res.getInt(1));
				seat.setCol(res.getInt(2));
				seat.setAirplaneID(res.getString(3));
				seat.setState(res.getBoolean(4));
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return seat; }
	}
    public void updateByPos(Airline airline, Flight flight, int row, int col, boolean state) {
    	String sql = "update airseat set state = ? where row = ? and col = ? and flightID = ?";
		con = factory.ConnectAirline(airline);
		try{		
			prst = con.prepareStatement(sql);
			prst.setBoolean(1,state);
			prst.setInt(2, row);
			prst.setInt(3, col);
			prst.setString(4, flight.getId());
			int res = prst.executeUpdate();
			System.out.println(res);
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
    }

}
