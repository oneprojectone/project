package cafeOne;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.CustomerView;
import view.HistoryView;
import view.MenuView;


public class Cafe extends JFrame {

	CustomerView customer;
	MenuView customerMenu;
	HistoryView history;
	
	public Cafe() {
		customer = new CustomerView();
		customerMenu = new MenuView();
		history = new HistoryView();
		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("메뉴관리", customerMenu);
		pane.addTab("회원관리", customer );
		pane.addTab("총 매출 관리", history );


		pane.setSelectedIndex(0);	
		
		getContentPane().add("Center", pane).setBackground(Color.red);
		//setSize(300,200);
		pack();
		setVisible(true);
		setResizable(true);
		Color b = new Color(255,223,176);  
		pane.setBackground(b);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}
