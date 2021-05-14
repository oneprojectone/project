package customer;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.CustomerView;


public class Cafe extends JFrame {

	CustomerView Gmc;
	public Cafe() {
		Gmc = new CustomerView();
		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("È¸¿ø", Gmc );

		
		pane.setSelectedIndex(0);	
		
		pack();
		setVisible(true);
		setResizable(false);
		Color b = new Color(255,223,176);  
		pane.setBackground(b);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	}

