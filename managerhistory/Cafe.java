import javax.swing.JFrame;
import javax.swing.JTabbedPane;

//����
public class Cafe extends JFrame {
	Manager mg;
	
	public Cafe(){
		//������ ȭ���� �����ϴ� Ŭ���� ��ü ����
			mg = new Manager();

			JTabbedPane pane = new JTabbedPane();
			pane.addTab("������ ����", mg );
			

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
