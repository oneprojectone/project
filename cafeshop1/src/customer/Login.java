package customer;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login extends JFrame implements ActionListener{
	JPanel pan1, pan2, pan3, pan4, pan5;
	JLabel banner, lid, lpass, empty1, empty2, empty3,empty4, empty5, one, empty6, empty7;
	TextField tf_id, tf_pass;
	JButton newID, ok, forid, forpass;
	ImageIcon image;
	Graphics g;
	Graphics2D g2 = (Graphics2D)g;
	public static void main(String[] args) {
		Login lg = new  Login();
	}
	Login(){
		
		
		
		GradientPaint gp = new GradientPaint(100, 100, Color.red,300,300,Color.blue);
		setFont(new Font("���ü", Font.BOLD, 40));
		
		setBackground(Color.LIGHT_GRAY);
		//setBounds(400,300,600,400);
		setResizable(true);
		setLayout(new FlowLayout());
		image=new ImageIcon("jojo.png");
		lid = new JLabel("ID   ");
		lpass = new JLabel("password   ");
		lid.setHorizontalAlignment(JLabel.RIGHT);
		lpass.setHorizontalAlignment(JLabel.RIGHT);
		banner = new JLabel();
		banner.setIcon(image);
		pan1=new JPanel();
		pan1.add(banner);
				
		tf_id = new TextField(10);
		tf_pass = new TextField(10);
		newID = new JButton("�ű԰���");
		ok = new JButton("�α���");
		
		//ok.setSize(50, 50);
		forid = new JButton("���̵� ã��");
		forpass = new JButton("��й�ȣ ã��");
		empty1 = new JLabel("   ");
		empty2 = new JLabel("   ");
		empty3 = new JLabel("   ");
		empty4 = new JLabel("   ");
		empty5 = new JLabel("   ");
		empty6 = new JLabel("   ");
		empty7 = new JLabel("   ");
		one = new JLabel("�ΰ� �̹���");
	
		add(pan1,"West");
		
		pan2 = new JPanel();
		pan3 = new JPanel();
		pan4 = new JPanel();
		pan5 = new JPanel();
		pan2.setLayout(new GridLayout(3,2));
		pan3.setLayout(new GridLayout(3,2));
		pan2.add(empty6);
		pan2.add(empty7);
		pan2.add(lid);
		pan2.add(tf_id);
		pan2.add(lpass);
		pan2.add(tf_pass);
		pan3.add(empty1);
		pan3.add(ok);
		pan3.add(empty2);
		pan3.add(empty3);
		pan3.add(empty4);
		pan3.add(empty5);
		
		pan4.setLayout(new GridLayout(4,1));
		pan4.add(one);
		pan4.add(pan2);
		pan4.add(pan3);
		pan5.add(forid);
		pan5.add(forpass);
		pan5.add(newID);
		pan4.add(pan5);
		add(pan4,"East");
		tf_pass.setEchoChar('*');
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���̵� ��� ��ȣ ã�� ��ư
		//���̵� ��й�ȣ ã�� 
		//ȸ������ â
		//
		
		
		forpass.addActionListener(this);
		forid.addActionListener(this);
		newID.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o==forpass) {
			FindPw fpw = new FindPw();
		}
		else if(o==forid) {
			FindId fid = new FindId();
		}
		else if(o==newID) {
			Door door = new Door();
		}
		
	}
	
	
	
	
}