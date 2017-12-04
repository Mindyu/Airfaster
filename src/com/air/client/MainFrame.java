package com.air.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.table.JTableHeader;

import com.air.client.util.GlobalObject;
import com.air.client.util.JTableOperation;
import com.air.client.util.RefreshThread;
import com.air.dao.impl.Singleton;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 820;
	private static final int DEFAULT_HEIGHT = 400;

	private static JTabbedPane tabbedPane;
	private JPanel panel1, panel2, panel3,panel4;

	public MainFrame() {
		setLayout(new BorderLayout());
		setTitle("飞机订票系统");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		Toolkit toolkit = Toolkit.getDefaultToolkit(); // 显示在中央
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeigit = screenSize.height;
		setLocation((screenWidth - DEFAULT_WIDTH) / 2,
				(screenHeigit - DEFAULT_HEIGHT) / 2);

		setBackground(Color.WHITE);

		Container c = getContentPane();
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT); // 创建选项卡面板对象

		// 创建面板
		panel1 = new SearchPanel();
		panel2 = new LogRegPanel();
		panel3 = new BookTicketPanel();
		panel4 = new OrderTicketPanel();

		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		panel1.add(jPanel, BorderLayout.CENTER);
		
		// 中间的table区域
		JTableOperation jTableInit = new JTableOperation();
		JTable jTable = jTableInit.JtableDataInit();
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
						//System.out.println("行："+row+"列"+col);
						String flightID = GlobalObject.getjTable().getValueAt(row, 0).toString(); //返回航班信息
						//System.out.println("所选数据"+flight);
						if (GlobalObject.getUser()==null) {
							JOptionPane.showMessageDialog(null, "请先登录~", "",JOptionPane.INFORMATION_MESSAGE);
							tabbedPane.setSelectedIndex(1);
						}else{
							tabbedPane.setSelectedIndex(2);   //跳转到订票
							GlobalObject.setFlight(Singleton.FlightDao().findById(flightID));
							JTableOperation jTableOperation = new JTableOperation();
							jTableOperation.reloadBookJTable(GlobalObject.getjBookTable());
						}
					}
				}
				super.mouseClicked(e);
			}
		});
		// 将scrollPane与jTable 添加到全局变量中，这里非常重要，下面介绍
		GlobalObject.setjScrollPane(scrollPane);
		GlobalObject.setjTable(jTable);
		Thread thread = new Thread(new RefreshThread()); // 异步刷新线程
		thread.start();
		JTableHeader jTableHeader = GlobalObject.getjTable().getTableHeader();
		jPanel.add(jTableHeader,BorderLayout.NORTH);
		jPanel.add(GlobalObject.getjTable(), BorderLayout.CENTER);

		// 将标签面板加入到选项卡面板对象上
		tabbedPane.addTab("查询", null, panel1, "");
		tabbedPane.addTab("账户", null, panel2, "");
		tabbedPane.addTab("订票", null, panel3, "");
		tabbedPane.addTab("订单", null, panel4, "");

		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
			    int selectedIndex = tabbedPane.getSelectedIndex();
			    JTableOperation jTableOperation = new JTableOperation();
			    switch (selectedIndex) {
				case 0:
	                jTableOperation.reloadJTable(GlobalObject.getjTable());
					break;
				case 3:
	                jTableOperation.reloadOrderJTable(GlobalObject.getjOrderTable());
					break;
				default:
					break;
				}
			}
		});
		
		c.add(tabbedPane, BorderLayout.WEST);
	}

	public static void addSeatPanel(){
		JPanel panel5 = new SelectSeatPanel();
		tabbedPane.addTab("选座", null, panel5, "");
	}
	
	public static void deleteSeatPanel(){
		tabbedPane.remove(4);
	}
	
	public static void toSeatPanel(){
		tabbedPane.setSelectedIndex(4);;
	}
}
