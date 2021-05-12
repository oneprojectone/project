import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.CustomerView;


public class Cafe extends JFrame {

	CustomerView customer;
	
	public Cafe() {
		customer = new CustomerView();
		//order = new OrderView();
		//menu = new MenuView();

		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("ȸ��", customer );
		//pane.addTab("����", history);
		//pane.addTab("�޴�", menu );

		
		pane.setSelectedIndex(0);	
		
		getContentPane().add("Center", pane).setBackground(Color.red);
		//setSize(300,200);
		pack();
		setVisible(true);
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	public static void main(String[] args) {
		new Cafe();
	}
}
