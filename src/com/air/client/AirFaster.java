package com.air.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.air.client.util.GlobalObject;
import com.air.client.util.JTableOperation;
import com.air.client.util.RefreshThread;
import com.air.dao.impl.Singleton;
import com.air.domain.Airline;
import com.air.domain.Flight;
import com.air.domain.User;


public class AirFaster {

	private static MainFrame mainFrame;
	
	public static void main (String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//主窗体
				mainFrame = new MainFrame();
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
			}
		});
		
	}

}
