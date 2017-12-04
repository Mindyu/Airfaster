package com.air.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import com.air.client.util.GlobalObject;
import com.air.client.util.JTableOperation;
import com.air.client.util.RefreshThread;
import com.air.dao.impl.Singleton;
import com.air.domain.Flight;
import com.air.domain.Order;
import com.air.domain.Passage;
import com.air.util.GetUUID;

public class BookTicketPanel extends JPanel {

	private JPanel passJPanel;
	private ArrayList<JPanel> passengersJPanels = new ArrayList<JPanel>();

	public BookTicketPanel() {
		setLayout(new BorderLayout());

		JPanel topjPanel = new JPanel();
		add(topjPanel, BorderLayout.NORTH);
		JButton submitButton = new JButton("提交订单");
		topjPanel.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (GlobalObject.getFlight() == null) {
					JOptionPane.showMessageDialog(null, "请先选择航班~", "",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					Order order = new Order();
					order.setId(GetUUID.getID());
					order.setUsername(GlobalObject.getUser().getUsername());
					order.setFlightID(GlobalObject.getFlight().getId());
					order.setPassageNum(passengersJPanels.size());
					order.setCost(GlobalObject.getFlight().getCost()
							* passengersJPanels.size());
					Singleton.OrderDao().add(order);

					ArrayList<Passage> passages = new ArrayList<Passage>(); // 乘客集合
					String res = "订单号：" + order.getId() + "\n";
					for (JPanel passenger : passengersJPanels) {
						Passage passage = new Passage();
						passage.setOrderID(order.getId());
						passage.setName(((TextField) passenger.getComponent(1))
								.getText());
						System.out.println(passage.getName());
						passage.setPid(((TextField) passenger.getComponent(3))
								.getText());
						passage.setPhone(((TextField) passenger.getComponent(5))
								.getText());
						res += "姓名：" + passage.getName() + "身份证："
								+ passage.getPid() + "电话：" + passage.getPhone()
								+ "\n";
						passages.add(passage);
					}
//					JOptionPane.showMessageDialog(null, res, "乘客数："
//							+ passengersJPanels.size(),
//							JOptionPane.INFORMATION_MESSAGE);
					Object[] options = {"选座","无所谓"};
					int response = JOptionPane.showOptionDialog(null, res+"\n去选座？",
							"乘客数："+ passengersJPanels.size(), JOptionPane.YES_OPTION,
							JOptionPane.CANCEL_OPTION, null, options, options[0]);
					GlobalObject.setPassages(passages);
					if (response == 0) {	//进行选座
						MainFrame.addSeatPanel();
						MainFrame.toSeatPanel();
					}else { 	//随机选座
						
					}
				}
			}
		});
		JButton addButton = new JButton("添加乘客");
		topjPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (passJPanel.getComponentCount() < 9) {
					addPassengers();
				}
			}
		});
		JButton deleteButton = new JButton("删除乘客");
		topjPanel.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (passJPanel.getComponentCount() > 1) {
					passJPanel.remove(passJPanel.getComponentCount() - 1);
					passengersJPanels.remove(passJPanel.getComponentCount() - 1);
					repaint();
				}
			}
		});

		JPanel flightjPanel = new JPanel(new BorderLayout());
		add(flightjPanel, BorderLayout.SOUTH);

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
		String[] Names = { "航班信息", "出发时间", "到达时间", "From", "To", "单价" };
		JTable jTable = new JTable(flightInfo, Names);
		jTable.setEnabled(false);
		jTable.setRowHeight(40);
		GlobalObject.setjBookTable(jTable);
		JTableHeader jTableHeader = jTable.getTableHeader();
		flightjPanel.add(jTableHeader, BorderLayout.NORTH);
		flightjPanel.add(jTable, BorderLayout.CENTER);

		passJPanel = new JPanel(new GridLayout(9, 1));
		add(passJPanel, BorderLayout.CENTER);

		addPassengers();
	}

	public void addPassengers() {
		final JPanel passengerJPanel = new JPanel();
		JLabel label = new JLabel("乘客：");
		passengerJPanel.add(label);
		TextField nameField = new TextField();
		nameField.setColumns(12);
		passengerJPanel.add(nameField);
		JLabel label1 = new JLabel("身份证：");
		passengerJPanel.add(label1);
		TextField pidField = new TextField();
		pidField.setColumns(12);
		passengerJPanel.add(pidField);
		JLabel label2 = new JLabel("联系电话：");
		passengerJPanel.add(label2);
		TextField phoneField = new TextField();
		phoneField.setColumns(12);
		passengerJPanel.add(phoneField);
		passJPanel.add(passengerJPanel);
		passengersJPanels.add(passengerJPanel);
	}

}
