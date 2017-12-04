package com.air.client.util;

import java.awt.Event;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

import com.qt.datapicker.DatePicker;

public class ObservingTextField extends JTextField implements Observer{
	
	public void update(Observable o, Object arg) {
        Calendar calendar = (Calendar) arg;
        DatePicker dp = (DatePicker) o;
        setText("20"+dp.formatDate(calendar));
    }

}
