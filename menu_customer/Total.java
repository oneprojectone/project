package one;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Total extends JFrame{

	public Total() {
		JTabbedPane totalTabPanel = new JTabbedPane();
		MenuTab tabMenu = new MenuTab();
		
		totalTabPanel.add("목   록", tabMenu);
		totalTabPanel.add("내정보", null);
		totalTabPanel.add("내   역", null);
		add(totalTabPanel);
		
		setSize(800, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Total();
	}

}
