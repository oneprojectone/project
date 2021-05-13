import javax.swing.JFrame;
import javax.swing.JTabbedPane;

//메인
public class Cafe extends JFrame {
	Manager mg;
	
	public Cafe(){
		//각각의 화면을 관리하는 클래스 객체 생성
			mg = new Manager();

			JTabbedPane pane = new JTabbedPane();
			pane.addTab("관리자 내역", mg );
			

			//pane.setSelectedIndex(2);
			setBounds(100, 100, 700, 300);
			getContentPane().add("Center", pane );
//			setLayout(new FlowLayout());
			setSize(800,350);
			setVisible(true);
			add(pane);
			
			
			
			setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
	}
	
	public static void main(String[] args){
			new Cafe();
	}
}
