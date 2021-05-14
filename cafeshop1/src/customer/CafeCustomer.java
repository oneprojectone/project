package customer;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.CustomerMyView;


public class CafeCustomer extends JFrame {

	CustomerMyView Customer;
	public CafeCustomer(String id) {
		Customer = new CustomerMyView(id);

		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("È¸¿ø", Customer );

		
		pane.setSelectedIndex(0);	
		
		pack();
		setVisible(true);
		setResizable(false);
		Color b = new Color(255,223,176);  
		pane.setBackground(b);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}
