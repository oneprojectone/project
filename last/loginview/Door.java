package loginview;

import javax.swing.JFrame;

public class Door extends JFrame {
	
	public Door() {
		setTitle("ONE-Regist");
		Regist regi= new Regist(this);
		add(regi);
		
		setSize(400, 700);
		setVisible(true);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
		
		public static void main(String[] args) {
			new Door();
		}
}
