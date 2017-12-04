package com.air.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.air.client.util.GlobalObject;
import com.air.client.util.JTableOperation;
import com.air.client.util.ObservingTextField;
import com.air.dao.impl.AirlineRegistDaoImpl;
import com.air.dao.impl.CityRegistDaoImpl;
import com.air.dao.impl.Singleton;
import com.air.domain.Airline;
import com.air.domain.City;
import com.air.domain.Flight;
import com.qt.datapicker.DatePicker;


public class SearchPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JComboBox startCity;
	private JComboBox arrivCity;
	private JComboBox airlineCom;
	private ObservingTextField startField;
	private ObservingTextField arrivField;
	private JButton search;
	
	@SuppressWarnings("deprecation")
	public SearchPanel() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		
		JPanel jPanel = new JPanel();
		
		JLabel label=new JLabel("起始城市:");  
		jPanel.add(label);  
        startCity=new JComboBox();  
        CityRegistDaoImpl cityDaoImpl = new CityRegistDaoImpl();
		ArrayList<City> cities = cityDaoImpl.findAll();
        for (int i = 0; i < cities.size(); i++) {
        	startCity.addItem(cities.get(i).getName());
		}
        jPanel.add(startCity);
        
        JLabel label1=new JLabel("终点城市:");  
        jPanel.add(label1);  
        arrivCity=new JComboBox();  
        for (int i = 0; i < cities.size(); i++) {
        	arrivCity.addItem(cities.get(i).getName());
		}
        jPanel.add(arrivCity);
        
        JLabel label2=new JLabel("出发日期：");  
        jPanel.add(label2);  
        startField = new ObservingTextField();
        startField.setBounds( 360, 180,  220, 32);
        startField.setText(new Date().toLocaleString().substring(0,10));
        jPanel.add(startField);
        label2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				DatePicker dp = new DatePicker(startField, getLocale());
		        // previously selected date
		        Date selectedDate = dp.parseDate(startField.getText());
		        dp.setSelectedDate(selectedDate);
		        dp.start(startField);
			}
		});
        
        JLabel label3=new JLabel("到达日期：");
        jPanel.add(label3);  
        arrivField = new ObservingTextField();
        arrivField.setBounds( 400, 180,  220, 32);
        arrivField.setText(new Date().toLocaleString().substring(0,10));
        jPanel.add(arrivField);
        label3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				DatePicker dp = new DatePicker(arrivField, getLocale());
		        // previously selected date
		        Date selectedDate = dp.parseDate(arrivField.getText());
		        dp.setSelectedDate(selectedDate);
		        dp.start(arrivField);
			}
		});
         
        airlineCom = new JComboBox();
        AirlineRegistDaoImpl airlineRegistDaoImpl = new AirlineRegistDaoImpl();
		ArrayList<Airline> airlines = airlineRegistDaoImpl.findAll();
		airlineCom.addItem("航空公司（可选）");
        for (int i = 0; i < airlines.size(); i++) {
        	airlineCom.addItem(airlines.get(i).getName());
		}
        jPanel.add(airlineCom);
        
        search = new JButton("查询");
        search.addActionListener(this);
        jPanel.add(search);
       
        add(jPanel,BorderLayout.NORTH);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == search) {
			Airline airline = new Airline();
			//查询某个航空公司的航班信息
			airline.setUrl("jdbc:mysql://localhost:3306/changchengairline?useUnicode=true&characterEncoding=utf8");
			airline.setUsername("root"); airline.setPassword("123456");
			airline = Singleton.AirlineDao().getInfo(airline);
			
			Flight flight = new Flight();
			flight.setStartCity((String)startCity.getSelectedItem());
			flight.setArrivCity((String)arrivCity.getSelectedItem());
			String startString = startField.getText()+" 00:00:00" ;
			flight.setStartTime(startString);
			String arriveString = arrivField.getText()+" 00:00:00";
			flight.setArrivTime(arriveString);
			
			Singleton.setFlight(Singleton.FlightDao().findAirline(airline, flight));
			for (Flight flight2 : Singleton.Flight()) {
				System.out.println(flight2.getName());
			}
			JTableOperation jTableOperation = new JTableOperation();
			jTableOperation.reloadJTable(GlobalObject.getjTable());
		}
	}

	
	
}
