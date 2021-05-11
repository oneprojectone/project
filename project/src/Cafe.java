import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

//메인
public class Cafe extends JFrame 
{
	CustomerView	customer;
	public Cafe(){
		//각각의 화면을 관리하는 클래스 객체 생성
			customer = new CustomerView();

			JTabbedPane  pane = new JTabbedPane();
			pane.addTab("마이 페이지", customer );
			


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