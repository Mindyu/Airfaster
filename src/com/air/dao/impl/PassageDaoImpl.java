package com.air.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.air.dao.IPassageDao;
import com.air.domain.Passage;
import com.air.util.Connector;

public class PassageDaoImpl implements IPassageDao {
	Connector factory = new Connector();
	private Connection con = null;
	private PreparedStatement prst = null;
	
	@Override
	public void add(Passage passage) {
		// TODO Auto-generated method stub
		String addSql = "insert into passage(orderID, name, pid, phone, seatRow, seatCol, seated) values(?,?,?,?,?,?,?)";
		con = factory.ConnectLocal();
		try{
			prst = con.prepareStatement(addSql);
			prst.setString(1, passage.getOrderID());
			prst.setString(2, passage.getName());
			prst.setString(3, passage.getPid());
			prst.setString(4, passage.getPhone());
			prst.setInt(5, passage.getSeatRow());
			prst.setInt(6, passage.getSeatCol());
			prst.setBoolean(7, passage.getSeated());
			
			prst.executeUpdate();
			System.out.println("添加乘客成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }	
	}

	@Override
	public void delete(Passage passage) {
		// TODO Auto-generated method stub
		String deletesql = "delete from passage where orderID = ?";
		con = factory.ConnectLocal();
		try{
			prst = con.prepareStatement(deletesql);
			prst.setString(1, passage.getOrderID());
			prst.executeUpdate();
			System.out.println("删除乘客成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<Passage> findByOrderID(String orderID) {
		// TODO Auto-generated method stub
		ArrayList<Passage> passages = new ArrayList<Passage>();
		String sql = "select * from passage where orderID = ?";
		con = factory.ConnectLocal();
		
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			prst.setString(1, orderID);
			res = prst.executeQuery();
			while(res.next()){	
				Passage passage = new Passage();
				passage.setOrderID(res.getString(1));
				passage.setName(res.getString(2));
				passage.setPid(res.getString(3));
				passage.setPhone(res.getString(4));
				passage.setSeatRow(res.getInt(5));
				passage.setSeatCol(res.getInt(6));
				passage.setSeated(res.getBoolean(7));
				passages.add(passage);
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return passages; }
	}

}
