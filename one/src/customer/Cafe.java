package customer;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.CustomerMyView;
import view.CustomerView;
import view.MenuTab;


public class Cafe extends JFrame {

	CustomerView customer;
	MenuTab customerMenu;
	
	public Cafe() {
		String id="lily93";
		customer = new CustomerView();
		customerMenu = new MenuTab(id);
		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("메뉴", customerMenu);
		pane.addTab("회원관리", customer );


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
