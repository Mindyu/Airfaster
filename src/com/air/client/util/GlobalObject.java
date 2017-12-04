package com.air.client.util;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.air.domain.Flight;
import com.air.domain.Passage;
import com.air.domain.User;

public class GlobalObject {
	/**
     * 全局jScrollPane
     */
    private static JScrollPane jScrollPane;
    public static JScrollPane getjScrollPane() {
        return jScrollPane;
    }
    public static void setjScrollPane(JScrollPane jScrollPane) {
        GlobalObject.jScrollPane = jScrollPane;
    }

    /**
     * 全局JTable
     */
    private static JTable jTable;
    public static JTable getjTable() {
        return jTable;
    }
    public static void setjTable(JTable jTable) {
        GlobalObject.jTable = jTable;
    }
    
    /**
     * 全局jScrollPane
     */
    private static JScrollPane jOrderScrollPane;
    public static JScrollPane getjOrderScrollPane() {
        return jOrderScrollPane;
    }
    public static void setjOrderScrollPane(JScrollPane jOrderScrollPane) {
        GlobalObject.jOrderScrollPane = jOrderScrollPane;
    }

    /**
     * 全局JTable
     */
    private static JTable jOrderTable;
    public static JTable getjOrderTable() {
        return jOrderTable;
    }
    public static void setjOrderTable(JTable jOrderTable) {
        GlobalObject.jOrderTable = jOrderTable;
    }
    
    /**
     * 全局JTable
     */
    private static JTable jBookTable;
    public static JTable getjBookTable() {
        return jBookTable;
    }
    public static void setjBookTable(JTable jBookTable) {
        GlobalObject.jBookTable = jBookTable;
    }
    
    private static User user;
	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		GlobalObject.user = user;
	}
    
    private static Flight flight = null;
	public static Flight getFlight() {
		return flight;
	}
	public static void setFlight(Flight flight) {
		GlobalObject.flight = flight;
	}
    
	private static ArrayList<Passage> passages;
	public static ArrayList<Passage> getPassages() {
		return passages;
	}
	public static void setPassages(ArrayList<Passage> passages) {
		GlobalObject.passages = passages;
	}
	
	public static String sortWay = "cost";
}
