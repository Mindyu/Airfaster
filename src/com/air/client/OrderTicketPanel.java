package com.air.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import com.air.domain.Flight;
import com.air.domain.Order;
import com.air.domain.Passage;

public class OrderTicketPanel extends JPanel {

	public OrderTicketPanel() {
		setLayout(new BorderLayout());
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		add(jPanel, BorderLayout.CENTER);
		
		// 中间的table区域
		JTableOperation jTableInit = new JTableOperation();
		JTable jTable = jTableInit.JtableOrderDataInit();
		JScrollPane scrollPane = new JScrollPane(jTable);
		scrollPane.setSize(new Dimension(400, 400));
		jTable.setEnabled(false);
		scrollPane.setEnabled(false);
		// 增加监听 这里我重写了监听方法 如果只显示与刷新可不比添加监听
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2)// 实现双击
				{
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
					int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint()); // 获得列位置 String
					if (row>=0) {
						String id = GlobalObject.getjOrderTable().getValueAt(row, 0).toString(); //返回航班信息
						Object[] options = {"确认", "返回"};
						int response=JOptionPane.showOptionDialog ( null, "取消订单？","订单操作",JOptionPane.YES_OPTION ,JOptionPane.CANCEL_OPTION,
						null, options, options[0] ) ;
						if (response == 0){
							Order order = Singleton.OrderDao().findById(id);
							Singleton.OrderDao().delete(id);
							new JTableOperation().reloadOrderJTable(GlobalObject.getjOrderTable());
							Flight flight = GlobalObject.getFlight();
							flight.setLeftSeat(flight.getLeftSeat());
							Airline airline = new Airline();
							//查询某个航空公司的航班信息
							airline.setUrl("jdbc:mysql://localhost:3306/changchengairline?useUnicode=true&characterEncoding=utf8");
							airline.setUsername("root"); airline.setPassword("123456");
							airline = Singleton.AirlineDao().getInfo(airline);
							Singleton.FlightDao().update(airline, flight);
//							ArrayList<Passage> passages = ;
//							for(Passage passage : passages){
//								Singleton.AirseatDao().updateByPos(airline, GlobalObject.getFlight(), passage.getSeatRow(), passage.getSeatCol(), false);
//							}
						}
					}
				}
				super.mouseClicked(e);
			}
		});
		// 将scrollPane与jTable 添加到全局变量中，这里非常重要，下面介绍
		GlobalObject.setjOrderScrollPane(scrollPane);
		GlobalObject.setjOrderTable(jTable);
		JTableHeader jTableHeader = GlobalObject.getjOrderTable().getTableHeader();
		jPanel.add(jTableHeader,BorderLayout.NORTH);
		jPanel.add(GlobalObject.getjOrderTable(), BorderLayout.CENTER);
		
	}
}
