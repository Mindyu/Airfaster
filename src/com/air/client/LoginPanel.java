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

import com.air.client.util.GlobalObject;
import com.air.dao.impl.Singleton;
import com.air.domain.User;

public class LoginPanel extends JPanel implements ActionListener {
	
	private JButton login,exitButton;
	private TextField nameArea;
	private TextField passwordArea;
	private JLabel usernameLabel, label, label1;

	public LoginPanel() {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout());

		label = new JLabel("用户名:", SwingConstants.CENTER);
		add(label);

		nameArea = new TextField("", SwingConstants.CENTER);
		nameArea.setColumns(12);
		add(nameArea);

		label1 = new JLabel("密码:", SwingConstants.CENTER);
		add(label1);

		passwordArea = new TextField("", SwingConstants.CENTER);
		passwordArea.setColumns(12);
		add(passwordArea);

		login = new JButton("登陆");
		login.addActionListener(this);
		add(login);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == login) {
			User user = new User();
			String nameString = nameArea.getText().trim();
			user.setUsername(nameString);
			String passString = passwordArea.getText().trim();
			user.setPassword(passString);
			GlobalObject.setUser(user);
			boolean res = Singleton.UserDao().check(user);
			System.out.println(res);
			if (res) {
				usernameLabel = new JLabel("");
				usernameLabel.setText("用户名：" + nameString);
				add(usernameLabel);
				
				exitButton = new JButton("退出");
				exitButton.addActionListener(this);
				add(exitButton);
				
				setComVisible(false);
			}
		}else if( source == exitButton){
			GlobalObject.setUser(null);;
			setComVisible(true);
		}
	}

	public void setComVisible(Boolean flag) {
		nameArea.setVisible(flag);
		passwordArea.setVisible(flag);
		label.setVisible(flag);
		label1.setVisible(flag);
		login.setVisible(flag);
		usernameLabel.setVisible(!flag);
		exitButton.setVisible(!flag);
	}
	
}
