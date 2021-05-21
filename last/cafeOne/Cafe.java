package cafeOne;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.CustomerView;
import view.HistoryView;
import view.MenuView;
import index.Login;

public class Cafe extends JFrame implements ActionListener{

	CustomerView customer;
	MenuView customerMenu;
	HistoryView history;
	JButton logout = new JButton("로그아웃");
	Font font = new Font("MAKGEOLLI", Font.PLAIN, 30);
	
	public Cafe() {
		setTitle("ONE");
		customer = new CustomerView();
		customerMenu = new MenuView();
		history = new HistoryView();
		
		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("메뉴관리", customerMenu);
		pane.addTab("회원관리", customer );
		pane.addTab("주문 내역 관리", history );
		
		logout.setFont(font);
		//add(logout);
		JPanel pan1, pan2, pan3,pan4,pan5,pan6;
		pan1= new JPanel();
		pan2= new JPanel();
		pan3= new JPanel();
		pan4= new JPanel();
		pan5= new JPanel();
		pan6= new JPanel();
		pan1.setLayout(new GridLayout(1,4));
		pan5.setLayout(new GridLayout(1,2));
		pan1.add(pan2);
		pan1.add(pan3);
		pan1.add(pan4);
		pan5.add(pan6);
		pan5.add(logout);
		pan1.add(pan5);
		logout.addActionListener(this);
		
		
		

		pane.setFont(font);
		pane.setSelectedIndex(0);
		Color color = new Color(129,88,84);
		//pane.setBackground(color);
		
		getContentPane().add("Center", pane).setBackground(color);
		getContentPane().add("North", pan1);
		
		//setSize(300,200);
		pack();
		setVisible(true);
		setResizable(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o==logout) {
			Font fonta = new Font("MAKGEOLLI", Font.PLAIN, 20);
			 JLabel lab25 = new JLabel("창이 모두 닫힙니다. 로그아웃 하시겠습니까?");
             lab25.setFont(fonta);
          int result=JOptionPane.showConfirmDialog(null, lab25,"확인",JOptionPane.YES_NO_OPTION);
          if(result==JOptionPane.YES_OPTION) {
             System.exit(0);
             }
		}
	}
	
	
}
