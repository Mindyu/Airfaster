package com.air.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.air.dao.impl.Singleton;
import com.air.domain.User;


public class RegisterPanel extends JPanel implements ActionListener {

	private JButton registerButton;
	private TextField nameArea;
	private TextField passwordArea;
	private TextField userShow;
	
	public RegisterPanel() {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout());
		
		JLabel label = new JLabel("用户名:",SwingConstants.CENTER);
		add(label);
		
		nameArea = new TextField("",SwingConstants.CENTER);
		nameArea.setColumns(12);
		add(nameArea);
		
		JLabel label1 = new JLabel("密码:",SwingConstants.CENTER);
		add(label1);
		
		passwordArea = new TextField("",SwingConstants.CENTER);
		passwordArea.setColumns(12);
		add(passwordArea);

		JLabel label2 = new JLabel("昵称:",SwingConstants.CENTER);
		add(label2);
		
		userShow = new TextField("",SwingConstants.CENTER);
		userShow.setColumns(12);
		add(userShow);
		
		registerButton = new JButton("注册");
		registerButton.addActionListener(this);
		add(registerButton);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == registerButton) {
			User user = new User();
			String nameString = nameArea.getText().trim();
			user.setUsername(nameString);
			String passString = passwordArea.getText().trim();
			user.setPassword(passString);
			boolean res = Singleton.UserDao().check(user);
			if (!res) {
				user.setUsershow(userShow.getText());
				Singleton.UserDao().add(user);
			}
			
		}
	}

}
