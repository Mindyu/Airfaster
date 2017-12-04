package com.air.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.air.dao.IOrderDao;
import com.air.domain.Order;
import com.air.util.Connector;

public class OrderDaoImpl implements IOrderDao {
	Connector factory = new Connector();
	private Connection con = null;
	private PreparedStatement prst = null;
	
	@Override
	public void add(Order order) {
		// TODO Auto-generated method stub
		String addSql = "insert into orders(id, username, flightID, passageNum, cost, droped) values(?,?,?,?,?,?)";
		con = factory.ConnectLocal();
		try{
			prst = con.prepareStatement(addSql);
			prst.setString(1, order.getId());
			prst.setString(2, order.getUsername());
			prst.setString(3, order.getFlightID());
			prst.setInt(4, order.getPassageNum());
			prst.setDouble(5, order.getCost());
			prst.setBoolean(6, true);  // order.getDroped() 
			
			prst.executeUpdate();
			System.out.println("添加订单成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }	
	}

	@Override
	public void delete(String orderid) {
		// TODO Auto-generated method stub
		String deletesql = " update orders set droped = '0' where id = ? ";    //delete from orders where username = ?
		con = factory.ConnectLocal();
		try{
			prst = con.prepareStatement(deletesql);
			prst.setString(1, orderid);
			int res =prst.executeUpdate();
			if (res!=0) {
				System.out.println("删除订单成功");
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<Order> findByUser(String username) {
		// TODO Auto-generated method stub
		ArrayList<Order> orders = new ArrayList<Order>();
		String sql = "select * from orders where username = ?";
		con = factory.ConnectLocal();
		
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			prst.setString(1, username);
			res = prst.executeQuery();
			while(res.next()){	
				Order order = new Order();
				order.setId(res.getString(1));
				order.setUsername(res.getString(2));
				order.setFlightID(res.getString(3));
				order.setPassageNum(res.getInt(4));
				order.setCost(res.getDouble(5));
				order.setDroped(res.getBoolean(6));
				orders.add(order);
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return orders; }
	}

	@SuppressWarnings("finally")
	@Override
	public Order findById(String Id) {
		// TODO Auto-generated method stub
		Order order = new Order();
		String sql = "select * from orders where id = ?";
		con = factory.ConnectLocal();
		
		ResultSet res = null;
		try{		
			prst = con.prepareStatement(sql);
			prst.setString(1, Id);
			res = prst.executeQuery();
			while(res.next()){	
				order.setId(res.getString(1));
				order.setUsername(res.getString(2));
				order.setFlightID(res.getString(3));
				order.setPassageNum(res.getInt(4));
				order.setCost(res.getDouble(5));
				order.setDroped(res.getBoolean(6));
			}
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); return order; }
	}
}
