package com.air.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.air.client.util.GlobalObject;
import com.air.client.util.JTableOperation;
import com.air.client.util.RefreshThread;

public class LogRegPanel extends JPanel {

	private JTabbedPane tabbedPane; 
	private JPanel logPanel,RegPanel;
	
	public LogRegPanel() {
		setLayout(new BorderLayout());
		
        tabbedPane=new JTabbedPane();   //创建选项卡面板对象 
         
        //创建面板  
        logPanel=new LoginPanel();
        RegPanel=new RegisterPanel();  
        
        //将标签面板加入到选项卡面板对象上  
        tabbedPane.addTab("登录",null,logPanel,"");  
        tabbedPane.addTab("注册",null,RegPanel,"");  

        add(tabbedPane,BorderLayout.CENTER); 
        
	}
	
	
}
