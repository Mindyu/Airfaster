package com.air.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import com.air.client.util.GlobalObject;
import com.air.client.util.JTableOperation;
import com.air.client.util.RefreshThread;
import com.air.dao.impl.Singleton;
import com.air.domain.Airline;
import com.air.domain.Airplane;
import com.air.domain.Airseat;
import com.air.domain.Flight;
import com.air.domain.Passage;

public class SelectSeatPanel extends JPanel {

	private JTable jTable;
	private Airplane airplane;
	private Airline airline;
	
	public SelectSeatPanel() {
		setLayout(new BorderLayout());

		JPanel seatJPanel = new JPanel();
		seatJPanel.setLayout(new BorderLayout());
		add(seatJPanel, BorderLayout.CENTER);

		// 显示飞机座位表
		airline = new Airline();
		airline.setUrl("jdbc:mysql://localhost:3306/changchengairline?useUnicode=true&characterEncoding=utf8");
		airline.setUsername("root"); airline.setPassword("123456");
		airline = Singleton.AirlineDao().getInfo(airline);
		//选座问题
		ArrayList<ArrayList<Airseat>> arrayLists = Singleton.AirseatDao().findAll(airline, GlobalObject.getFlight());
		Object[][] seatsInfo = new Object[arrayLists.size()][];
		for (int i = 0; i<arrayLists.size(); i++) {
			Object[] objects = { !arrayLists.get(i).get(0).getState()? "":"-1",!arrayLists.get(i).get(1).getState()? "":"-1",!arrayLists.get(i).get(2).getState()? "":"-1",
					"--过道--",!arrayLists.get(i).get(3).getState()? "":"-1",!arrayLists.get(i).get(4).getState()? "":"-1",!arrayLists.get(i).get(5).getState()? "":"-1" };
			seatsInfo[i] = objects;
		}
		String[] Names = { "A", "B", "C", "--过道--", "D", "E", "F" };
		jTable = new JTable(seatsInfo, Names);
		JScrollPane scrollPane = new JScrollPane(jTable);
		scrollPane.setSize(new Dimension(400, 400));
		JTableHeader jTableHeader = jTable.getTableHeader();
		seatJPanel.add(jTableHeader, BorderLayout.NORTH);
		seatJPanel.add(jTable, BorderLayout.CENTER);

		JPanel jPanel = new JPanel();
		add(jPanel, BorderLayout.EAST);
		
		JButton submit = new JButton("提交");
		jPanel.add(submit);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Passage> passages = GlobalObject.getPassages();
				int index = 0;
				for (int i = 0; i < jTable.getRowCount(); i++) {
					for (int j = 0; j < jTable.getColumnCount(); j++) {
						if ("1".equals(jTable.getValueAt(i, j).toString()) && j != 3) {
							passages.get(index).setSeatRow(i);
							passages.get(index++).setSeatCol(j);
						}
					}
				}
				GlobalObject.setPassages(passages);
				for(Passage passage : passages){
					Singleton.PassageDao().add(passage);         //添加乘客
					Singleton.AirseatDao().updateByPos(airline, GlobalObject.getFlight(), passage.getSeatRow(), passage.getSeatCol(), true);
				}
				Flight flight = GlobalObject.getFlight();
				flight.setLeftSeat(flight.getLeftSeat()-passages.size());
				Singleton.FlightDao().update(airline, flight);
//				airplane.setLeftSeat(airplane.getLeftSeat()-passages.size());
//				Singleton.AirplaneDao().update(airline, airplane);    //更新剩余座位
				
				JOptionPane.showMessageDialog(null, "选座成功", "",JOptionPane.INFORMATION_MESSAGE);
				MainFrame.deleteSeatPanel();
			}
		});
	}

}
