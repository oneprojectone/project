import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.CustomerView;


public class CafeShop extends JFrame {

	CustomerView customer;
	
	public CafeShop() {
		customer = new CustomerView();
		//order = new OrderView();
		//menu = new MenuView();

		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("ȸ��", customer );
		//pane.addTab("����", history);
		//pane.addTab("�޴�", menu );

		
		pane.setSelectedIndex(0);	
		
		getContentPane().add("Center", pane);
		setSize(300,200);
		setVisible(true);
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	public static void main(String[] args) {
		new CafeShop();
	}
}
