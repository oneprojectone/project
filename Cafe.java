import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

//����
public class Cafe extends JFrame 
{
	CustomerView	customer;
	public Cafe(String id){
		//������ ȭ���� �����ϴ� Ŭ���� ��ü ����
		
			customer = new CustomerView(id);

			JTabbedPane  pane = new JTabbedPane();
			pane.addTab("���� ������", customer );

			getContentPane().add("Center", pane );
			setLayout(new FlowLayout());
			setSize(500,600);
			setVisible( true );
			
			setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
	}
	
}