import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

//����
public class Cafe extends JFrame 
{
	CustomerView	customer;
	public Cafe(){
		//������ ȭ���� �����ϴ� Ŭ���� ��ü ����
			customer = new CustomerView();

			JTabbedPane  pane = new JTabbedPane();
			pane.addTab("���� ������", customer );
			


			//pane.setSelectedIndex(2);

			getContentPane().add("Center", pane );
			setLayout(new FlowLayout());
			setSize(500,600);
			setVisible( true );
			
			setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
	}
	public static void main(String[] args) 
	{
			new Cafe();
	}
}