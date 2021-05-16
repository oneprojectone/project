import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class Cafe extends JFrame {
	
	Order order;
	
	public Cafe(){
			order = new Order();
			JTabbedPane pane = new JTabbedPane();
			pane.addTab("회원 구매 내역 ", order);
			
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