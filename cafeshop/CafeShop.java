import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import View.CustomerView;

public class CafeShop extends JFrame {

	CustomerView customer;
	//MenuView menu;
	//HistoryView history;
	
	public CafeShop() {
		customer = new CustomerView();
		//menu = new MenuView();
		//history = new HistoryView();

		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("회원", customer );
		//pane.addTab("메뉴", menu );
		//pane.addTab("내역", history);

		
		pane.setSelectedIndex(0);	
		
		getContentPane().add("Center", pane);
		//setSize(300,200);
		pack();
		setVisible(true);
		
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	public static void main(String[] args) {
		new CafeShop();
	}
}
