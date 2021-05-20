package cafeOne;
import java.awt.Color;
import java.awt.Font;

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
		setTitle("ONE");
		customer = new CustomerView();
		customerMenu = new MenuView();
		history = new HistoryView();
		
		
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 30);
		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("�޴�����", customerMenu);
		pane.addTab("ȸ������", customer );
		pane.addTab("�ֹ� ���� ����", history );

		pane.setFont(font);
		pane.setSelectedIndex(0);
		Color color = new Color(129,88,84);
		//pane.setBackground(color);
		
		getContentPane().add("Center", pane).setBackground(color);
		//setSize(300,200);
		pack();
		setVisible(true);
		setResizable(true);
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}
