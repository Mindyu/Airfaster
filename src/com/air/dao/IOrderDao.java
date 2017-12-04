package com.air.dao;

import java.util.ArrayList;

import com.air.domain.Order;


public interface IOrderDao {
	public void add(Order order);
	public void delete(String orderid);
	public ArrayList<Order> findByUser(String username);
	public Order findById(String Id);
}
