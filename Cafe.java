import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

//메인
public class Cafe extends JFrame 
{
	CustomerView	customer;
	public Cafe(String id){
		//각각의 화면을 관리하는 클래스 객체 생성
		
			customer = new CustomerView(id);

			JTabbedPane  pane = new JTabbedPane();
			pane.addTab("마이 페이지", customer );

			getContentPane().add("Center", pane );
			setLayout(new FlowLayout());
			setSize(500,600);
			setVisible( true );
			
			setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
	}
	
}