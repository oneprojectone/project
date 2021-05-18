package cafeOne;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.CustomerMyView;
import view.HistoryMyView;
import view.MenuMyView;


public class CafeCustomer extends JFrame  {

	
	CustomerMyView customerMy;
	MenuMyView customerMenu;
	HistoryMyView customerhistory;
	
	public CafeCustomer(String id) {
		customerMy = new CustomerMyView(id);
		customerMenu=new MenuMyView(id,customerMy);
		customerhistory= new HistoryMyView(id);

		JTabbedPane pane = new JTabbedPane();
		
		pane.addTab("메뉴", customerMenu);
		pane.addTab("내 정보 관리", customerMy );
		pane.addTab("내 주문 내역", customerhistory );
		
		pane.setSelectedIndex(0);	
		
		getContentPane().add("Center", pane).setBackground(Color.red);
	
		setSize(800,800);
		setVisible(true);
		setResizable(true);
		Color b = new Color(255,223,176);  
		pane.setBackground(b);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
		}
	
