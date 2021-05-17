package five;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
//메인
public class Cafe extends JFrame 
{
	 MenuMain customer;
	public Cafe(){
		//각각의 화면을 관리하는 클래스 객체 생성
		
			customer = new MenuMain();

			JTabbedPane  pane = new JTabbedPane();
			pane.addTab("관리자 페이지", customer );

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