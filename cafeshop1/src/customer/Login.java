package customer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login extends JFrame implements ActionListener{
	JPanel pan1, pan2, pan3, pan4, pan5, pan6, pan7, pan8, pan9, pan10,pan11;
	JLabel banner,banner1, lid, lpass, empty1, empty2, empty3,empty4, empty5, one, empty6, empty7;
	TextField tf1, tf2;
	JButton newID, ok, forid, forpass;
	ImageIcon image, image1;
	Graphics g;
	Graphics2D g2 = (Graphics2D)g;
	CustomerDAO dao = new CustomerDAO();
	public static void main(String[] args) {
		Login lg = new  Login();
	}
	Login(){
		
		
		
		GradientPaint gp = new GradientPaint(100, 100, Color.red,300,300,Color.blue);
		setFont(new Font("고딕체", Font.BOLD, 40));
		
		setResizable(true);
		setLayout(new FlowLayout());
		
		image=new ImageIcon("jojo1.png");
		image1=new ImageIcon("one.png");
		
		lid = new JLabel(" I              D    ");
		lpass = new JLabel("password    ");
		
		lid.setHorizontalAlignment(JLabel.RIGHT);
		lpass.setHorizontalAlignment(JLabel.RIGHT);
		
		banner = new JLabel();
		one = new JLabel();
		
		banner.setIcon(image);
		one.setIcon(image1);
		
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		pan4 = new JPanel();
		pan5 = new JPanel();
		pan6 = new JPanel();
		pan7 = new JPanel();
		pan8 = new JPanel();
		pan9 = new JPanel();
		pan10 = new JPanel();
		pan11 = new JPanel();
		
				
		tf1 = new TextField(20);
		tf2 = new TextField(20);
		ok = new JButton("로그인");
		forid = new JButton("아이디 찾기");
		forpass = new JButton("비밀번호 찾기");
		newID = new JButton("신규가입");
		ok.setPreferredSize(new Dimension(80, 40));
//		setContentAreaField(false);
//        setFocusPainted(false);


		empty1 = new JLabel("   ");
		empty2 = new JLabel("                                                  ");
		empty3 = new JLabel("   ");
		empty4 = new JLabel("   ");
	
		pan1.setLayout(new GridBagLayout());
		GridBagConstraints	cba = new GridBagConstraints();
		cba.weightx	= 0.1;
		cba.weighty	 = 0.1;
		pan2.setLayout(new GridBagLayout());
		GridBagConstraints	cbb = new GridBagConstraints();
		cbb.weightx	= 0.1;
		cbb.weighty	 = 0.1;
		pan3.setLayout(new GridBagLayout());
		GridBagConstraints	cbc = new GridBagConstraints();
		cbc.weightx	= 0.1;
		cbc.weighty	 = 0.1;
		pan4.setLayout(new GridBagLayout());
		GridBagConstraints	cbd = new GridBagConstraints();
		cbd.weightx	= 0.1;
		cbd.weighty	 = 0.1;
		pan5.setLayout(new GridBagLayout());
		GridBagConstraints	cbe = new GridBagConstraints();
		cbe.weightx	= 0.1;
		cbe.weighty	 = 0.1;
		pan6.setLayout(new GridBagLayout());
		GridBagConstraints	cbf = new GridBagConstraints();
		cbf.weightx	= 0.1;
		cbf.weighty	 = 0.1;
		pan7.setLayout(new GridBagLayout());
		GridBagConstraints	cbg = new GridBagConstraints();
		cbg.weightx	= 0.1;
		cbg.weighty	 = 0.1;
		pan8.setLayout(new GridBagLayout());
		GridBagConstraints	cbh = new GridBagConstraints();
		cbh.weightx	= 0.1;
		cbh.weighty	 = 0.1;
		pan9.setLayout(new GridBagLayout());
		GridBagConstraints	cbi = new GridBagConstraints();
		cbi.weightx	= 0.1;
		cbi.weighty	 = 0.1;
		pan10.setLayout(new GridBagLayout());
		GridBagConstraints	cbj = new GridBagConstraints();
		cbj.weightx	= 0.1;
		cbj.weighty	 = 0.1;
		pan11.setLayout(new GridBagLayout());
		GridBagConstraints	cbk = new GridBagConstraints();
		cbk.weightx	= 0.1;
		cbk.weighty	 = 0.1;
		
		cba.gridx	=	0;	 		cba.gridy	=  0;
		pan1.add(pan2,cba);
		
		cba.gridx	=	1;	 		cba.gridy	=  0;
		pan1.add(pan3,cba);

		cbb.gridx	=	1;	 		cbb.gridy	=  0;			
		pan2.add(banner,cbb);
	
		cbc.gridx	=	0;	 		cbc.gridy	=  0;
		pan3.add(pan4,cbc);
		
		cbc.gridx	=	0;	 		cbc.gridy	=  1;
		pan3.add(pan5,cbc);
		
		cbc.gridx	=	0;	 		cbc.gridy	=  2;
		pan3.add(pan6,cbc);
		
		cbc.gridx	=	0;	 		cbc.gridy	=  3;
		pan3.add(pan7,cbc);
		
		cbc.gridx	=	0;	 		cbc.gridy	=  4;
		pan3.add(pan8,cbc);
		
		cbc.gridx	=	0;	 		cbc.gridy	=  5;
		pan3.add(pan9,cbc);
		
		cbc.gridx	=	0;	 		cbc.gridy	=  6;
		pan3.add(pan10,cbc);
		
		cbd.gridx	=	0;	 		cbd.gridy	=  0;
		pan4.add(one,cbd);
		
		cbe.gridx	=	0;	 		cbe.gridy	=  0;
		pan5.add(empty1,cbe);
		
		cbf.gridx	=	0;	 		cbf.gridy	=  0;
		pan6.add(lid,cbf);
		
		cbf.gridx	=	1;	 		cbf.gridy	=  0;
		pan6.add(tf1,cbf);
		
		cbg.gridx	=	0;	 		cbg.gridy	=  0;
		pan7.add(lpass,cbg);
		
		cbg.gridx	=	1;	 		cbg.gridy	=  0;
		pan7.add(tf2,cbg);
		
		cbh.gridx	=	0;	 		cbh.gridy	=  0;
		pan8.add(empty2,cbh);

		cbh.gridx	=	1;	 		cbh.gridy	=  0;
		pan8.add(ok,cbh);

		cbi.gridx	=	0;	 		cbi.gridy	=  0;
		pan9.add(empty3,cbi);
		
		cbk.gridx	=	0;	 		cbk.gridy	=  0;
		pan11.add(empty4,cbk);

		cbj.gridx	=	0;	 		cbj.gridy	=  0;
		pan10.add(forid,cbj);
		
		cbj.gridx	=	1;	 		cbj.gridy	=  0;
		pan10.add(forpass,cbj);
		
		cbj.gridx	=	2;	 		cbj.gridy	=  0;
		pan10.add(newID,cbj);
		
		Color b = new Color(255,223,176);  
		pan1.setBackground(b);    
		pan2.setBackground(b);    
		pan3.setBackground(b);    
		pan4.setBackground(b);    
		pan5.setBackground(b);    
		pan6.setBackground(b);    
		pan7.setBackground(b);    
		pan8.setBackground(b);    
		pan9.setBackground(b);    
		pan10.setBackground(b);    
		pan11.setBackground(b);    
		  
		add(pan1);
		tf2.setEchoChar('*');
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//아이디 비밀 번호 찾기 버튼
		//아이디 비밀번호 찾기 
		//회원가입 창
		//
		
		
		ok.addActionListener(this);
		forid.addActionListener(this);
		forpass.addActionListener(this);
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
		else if(o==ok) {
			dao.loginCheck(this);
			
		}
		
	}
	
	
	
	
}