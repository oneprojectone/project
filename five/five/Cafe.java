package five;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
//����
public class Cafe extends JFrame 
{
	 MenuMain customer;
	public Cafe(){
		//������ ȭ���� �����ϴ� Ŭ���� ��ü ����
		
			customer = new MenuMain();

			JTabbedPane  pane = new JTabbedPane();
			pane.addTab("������ ������", customer );

			getContentPane().add("Center", pane );
			pane.setSelectedIndex(0);
			
			
			setSize(800,500);
			setVisible( true );
			
			setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
	}
	
	public static void main(String[] args) {
		new Cafe();
	}
	
}