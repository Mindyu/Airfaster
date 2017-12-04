package com.air.client.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.air.dao.impl.Singleton;
import com.air.domain.Airplane;
import com.air.domain.Flight;
import com.air.domain.Order;

public class JTableOperation {
	public JTable JtableDataInit() {
		Singleton.setFlight(Singleton.FlightDao().findAll());
		System.out.println("flight数:" + Singleton.Flight().size());
		Collections.sort(Singleton.Flight());
		Object[][] flightInfo = new Object[Singleton.Flight().size()][];
		int i = 0;
		for (Flight flight2 : Singleton.Flight()) {
			Object[] objects = { flight2.getId(),flight2.getName(), flight2.getStartTime(),
					flight2.getArrivTime(), "" + flight2.getStartCity(),
					"" + flight2.getArrivCity(), flight2.getCost(), flight2.getLeftSeat() };
			flightInfo[i++] = objects;
		}
		String[] Names = { "航班信息", "航班名称", "出发时间", "到达时间", "From", "To", "价格", "剩余座位" };
		JTable table = new JTable(flightInfo, Names);
		return table;
	}

	/**
	 * 重载JTable 的数据 该方法在JTableOperation类中
	 */
	public void reloadJTable(JTable jtable) {
		Object[][] flightInfo = new Object[Singleton.Flight().size()][];
		int i = 0;
		for (Flight flight2 : Singleton.Flight()) {
			Object[] objects = { flight2.getId(),flight2.getName(), flight2.getStartTime(),
					flight2.getArrivTime(), "" + flight2.getStartCity(),
					"" + flight2.getArrivCity(), flight2.getCost(),  flight2.getLeftSeat()  };
			flightInfo[i++] = objects;
		}
		String[] Names = { "航班信息",  "航班名称", "出发时间", "到达时间", "From", "To", "价格", "剩余座位" };
		DefaultTableModel dModel = new DefaultTableModel(flightInfo, Names);
		jtable.setModel(dModel);
		// jtable = new JTable(flightInfo, Names);
		// MyActionListener myActionListener = new MyActionListener();
		GlobalObject.setjTable(jtable);
		// GlobalObject.getjScrollPane().setViewportView(jtable);
		// jtable.revalidate();
		// jtable.repaint();

	}

	public JTable JtableOrderDataInit() {
		ArrayList<Order> orders = new ArrayList<Order>();
		if (GlobalObject.getUser() != null) {
			orders = Singleton.OrderDao().findByUser(
					GlobalObject.getUser().getUsername());
		}
		Object[][] orderInfo = new Object[orders.size()][];
		int i = 0;
		for (Order order : orders) {
			Object[] objects = {
					order.getId(),
					order.getUsername(),
					Singleton.FlightDao().findById(order.getFlightID())
							.getName(), order.getPassageNum(), order.getCost(),
					order.getDroped() ? "完成" : "已取消" };
			orderInfo[i++] = objects;
		}
		String[] Names = { "订单号", "用户", "航班", "乘客数", "总额", "状态" };
		JTable table = new JTable(orderInfo, Names);
		return table;
	}

	/**
	 * 重载JTable 的数据 该方法在JTableOperation类中
	 */
	public void reloadOrderJTable(JTable jtable) {
		ArrayList<Order> orders = new ArrayList<Order>();
		if (GlobalObject.getUser() != null) {
			orders = Singleton.OrderDao().findByUser(
					GlobalObject.getUser().getUsername());
		}
		Object[][] orderInfo = new Object[orders.size()][];
		int i = 0;
		for (Order order : orders) {
			Object[] objects = {
					order.getId(),
					order.getUsername(),
					Singleton.FlightDao().findById(order.getFlightID())
							.getName(), order.getPassageNum(), order.getCost(),
					order.getDroped() ? "完成" : "已取消" };
			orderInfo[i++] = objects;
		}
		String[] Names = { "订单号", "用户", "航班", "乘客数", "总额", "状态" };
		DefaultTableModel dModel = new DefaultTableModel(orderInfo, Names);
		jtable.setModel(dModel);
		GlobalObject.setjOrderTable(jtable);
	}

	public void reloadBookJTable(JTable jtable) {
		// 底部的table区域
		Object[][] flightInfo = new Object[1][];
		Flight flight2 = GlobalObject.getFlight();
		if (flight2 != null) {
			Object[] objects = { flight2.getName(), flight2.getStartTime(),
					flight2.getArrivTime(), "" + flight2.getStartCity(),
					"" + flight2.getArrivCity(), flight2.getCost() };
			flightInfo[0] = objects;
		} else {
			Object[] objects = { "", "", "", "", "", "" };
			flightInfo[0] = objects;
		}
		String[] Names = { "航班信息", "出发时间", "到达时间", "From", "To", "价格" };		
		DefaultTableModel dModel = new DefaultTableModel(flightInfo, Names);
		jtable.setModel(dModel);
		GlobalObject.setjBookTable(jtable);
	}

}
