package one;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Total extends JFrame{

	public Total() {
		JTabbedPane totalTabPanel = new JTabbedPane();
		MenuTab tabMenu = new MenuTab();
		
		totalTabPanel.add("��   ��", tabMenu);
		totalTabPanel.add("������", null);
		totalTabPanel.add("��   ��", null);
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
