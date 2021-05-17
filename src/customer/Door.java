package customer;

import javax.swing.JFrame;

public class Door extends JFrame {
	
	public Door() {
		Regist regi= new Regist(this);
		add(regi);
		
		setSize(400, 700);
		setVisible(true);
		setResizable(false);
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
		
		public static void main(String[] args) {
			new Door();
		}
}
