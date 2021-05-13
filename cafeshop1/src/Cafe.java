import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import customer.CustomerDAO;
import view.CustomerView;


public class Cafe extends JFrame {

	CustomerView customer;
	public Cafe() {
		customer = new CustomerView();
		//order = new OrderView();
		//menu = new MenuView();

		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("회원", customer );
		//pane.addTab("내역", history);
		//pane.addTab("메뉴", menu );

		
		pane.setSelectedIndex(0);	
		
		getContentPane().add("Center", pane).setBackground(Color.red);
		//setSize(300,200);
		pack();
		setVisible(true);
		setResizable(false);
		Color b = new Color(255,223,176);  
		pane.setBackground(b);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	public static void main(String[] args) {
		new Cafe();
	}
}
