package com.air.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.air.dao.IUserDao;
import com.air.domain.User;
import com.air.util.Connector;

public class UserDaoImpl implements IUserDao {
	Connector factory = new Connector();
	private Connection con = null;
	private PreparedStatement prst = null;
	
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		String addSql = "insert into user(username, password, usershow) values(?,?,?)";
		con = factory.ConnectLocal();
		try{
			prst = con.prepareStatement(addSql);
			prst.setString(1, user.getUsername());
			prst.setString(2, user.getPassword());
			prst.setString(3, user.getUsershow());
			prst.executeUpdate();
			System.out.println("注册用户成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		String deletesql = "delete from user where username = ?";
		con = factory.ConnectLocal();
		try{
			prst = con.prepareStatement(deletesql);
			prst.setString(1, user.getUsername());
			prst.executeUpdate();
			System.out.println("删除用户成功");
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{ factory.closeCon(con); }
	}

	@SuppressWarnings("finally")
	@Override
	public boolean check(User user) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from user where username = ? and password = ?";
		con = factory.ConnectLocal();
		ResultSet res = null;
		int cnt = 0;
		try{
			prst = con.prepareStatement(sql);
			prst.setString(1, user.getUsername());
			prst.setString(2, user.getPassword());
			res = prst.executeQuery();
			System.out.println("查询用户中");
			if(res.next()) {
				cnt = res.getInt(1);
			}
			System.out.println(cnt);
		}
		catch(SQLException e){ e.printStackTrace(); }
		finally{
			factory.closeCon(con); 
			if(cnt >= 1) return true;
			else return false;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public boolean checkUsername(String username) {
		String sql = "select count(*) from user where username = ?";
		con = factory.ConnectLocal();
		ResultSet res = null;
		int cnt = 0;
		try {
			prst = con.prepareStatement(sql);
			prst.setString(1, username);
			res = prst.executeQuery();
			System.out.println("查询用户名中...");
			if(res.next()) {
				cnt = res.getInt(1);
			}
			System.out.println(cnt);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.closeCon(con);
			if (cnt >= 1) {
				return true;	
			}else {
				return false;
			}
		}
	}

}
