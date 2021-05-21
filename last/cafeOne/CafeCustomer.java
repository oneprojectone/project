package cafeOne;
import java.awt.Color;
import java.awt.Font;

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
		setTitle("ONE");
		customerMy = new CustomerMyView(id);
		customerMenu=new MenuMyView(id,customerMy);
		customerhistory= new HistoryMyView(id,customerMy);
		
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 30);
		

		JTabbedPane pane = new JTabbedPane();
		
		pane.addTab("메뉴", customerMenu);
		pane.addTab("내 정보", customerMy );
		pane.addTab("내 주문 내역", customerhistory );
		
		pane.setSelectedIndex(0);	
		Color color = new Color(129,88,84);
		//pane.setBackground(color);
		
		getContentPane().add("Center", pane).setBackground(color);
	
		setSize(1000,800);
		setVisible(true);
		setResizable(true);
		
		pane.setFont(font);
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
		}
	
