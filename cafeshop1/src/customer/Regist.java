package customer;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import customer.rec.CustomerVO;


public class Regist extends JPanel implements ActionListener
{
	

	JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8,lab9,lab10,lab11,lab12,lab13,lab14,lab15;
	ImageIcon image;
	JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9;
	JPanel pan1,pan2,pan3,pan4,pan5,pan6,pan7, pan8, pan9, pan10, pan11;
	JButton button1, button3, button4;
	CustomerDAO dao = new CustomerDAO();
	CustomerVO vo = new CustomerVO();
	JRadioButton male, female;
	JComboBox bank, mail;
	
	Regist() {
		
		setLayout(new FlowLayout());
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();
		pan4=new JPanel();
		pan5=new JPanel();
		pan6=new JPanel();
		pan7=new JPanel();
		pan8=new JPanel();
		pan9=new JPanel();
		pan10=new JPanel();
		pan11=new JPanel();
		
		String	BankText[]= {"선택", "COSMO", "HAPPY" , "SINNAH", "GMOD" };
		String	MailText[]= {"직접입력", "naver.com", "gmail.com" , "hanmail.net", "nate.com", "paran.com" };
		bank		= new JComboBox(BankText);
		mail		= new JComboBox(MailText);
		male=new JRadioButton("남자");
		female= new JRadioButton("여자");
		ButtonGroup mf = new ButtonGroup();
		mf.add(male);
		mf.add(female);
		
		pan1.setLayout( new GridBagLayout() );
		GridBagConstraints	cba = new GridBagConstraints();
		cba.weightx	= 0.1;
		cba.weighty	 = 0.1;
		
		pan2.setLayout( new GridBagLayout() );
		GridBagConstraints	cbb = new GridBagConstraints();
		cbb.weightx	= 0.1;
		cbb.weighty	 = 0.1;
		
		
		pan3.setLayout( new GridBagLayout() );
		GridBagConstraints	cbc = new GridBagConstraints();
		cbc.weightx	= 0.1;
		cbc.weighty	 = 0.1;
		
		pan4.setLayout( new GridBagLayout() );
		GridBagConstraints	cbd = new GridBagConstraints();
		cbd.weightx	= 0.1;
		cbd.weighty	 = 0.1;
		
		pan5.setLayout( new GridBagLayout() );
		GridBagConstraints	cbe = new GridBagConstraints();
		cbe.weightx	= 0.1;
		cbe.weighty	 = 0.1;
		
		pan6.setLayout( new GridBagLayout() );
		GridBagConstraints	cbf = new GridBagConstraints();
		cbf.weightx	= 0.1;
		cbf.weighty	 = 0.1;
		
		pan7.setLayout( new GridBagLayout() );
		GridBagConstraints	cbg = new GridBagConstraints();
		cbg.weightx	= 0.1;
		cbg.weighty	 = 0.1;

		pan8.setLayout( new GridBagLayout() );
		GridBagConstraints	cby = new GridBagConstraints();
		cby.weightx	= 0.1;
		cby.weighty	 = 0.1;

		pan9.setLayout( new GridBagLayout() );
		GridBagConstraints	cbj = new GridBagConstraints();
		cbj.weightx	= 0.1;
		cbj.weighty	 = 0.1;
		
		pan10.setLayout( new GridBagLayout() );
		GridBagConstraints	cbk = new GridBagConstraints();
		cbk.weightx	= 0.1;
		cbk.weighty	 = 0.1;
		pan11.setLayout( new GridBagLayout() );
		GridBagConstraints	cbl = new GridBagConstraints();
		cbl.weightx	= 0.1;
		cbl.weighty	 = 0.1;
		
		
		
		lab1=new JLabel("프로필");
		//add(lab1);
		image = new ImageIcon("profile.png");
		lab3=new JLabel();
		lab3.setIcon(image);
		cbf.gridx	=	1;	 			cbf.gridy	=  0;			
		pan6.add(lab3,cbf);
		lab2=new JLabel("이	름");
		cba.gridx	=	1;	 			cba.gridy	=  0;			
		pan1.add(lab2,cba);
		tf1=new JTextField(10);
		cba.gridx	=2;	 			cba.gridy	=  0;			
		pan1.add(tf1,cba);
		cba.gridx	=1;	 			cba.gridy	=  1;
		pan1.add(male,cba);
		cba.gridx	=2;	 			cba.gridy	=  1;
		pan1.add(female,cba);
		
		cbg.gridx	=	1;	 			cbg.gridy	=  0;
		pan7.add(pan6,cbg);
		cbg.gridx	=	2;	 			cbg.gridy	=  0;
		pan7.add(pan1,cbg);
		
		lab4=new JLabel("ID");
		cbb.gridx=0;	cbb.gridy=0;	
		pan2.add(lab4,cbb);
		tf2=new JTextField(10);
		cbb.gridx=1;	cbb.gridy=0;	
		pan2.add(tf2,cbb);
		lab5=new JLabel("비밀번호");
		cbb.gridx=0;	cbb.gridy=1;	
		pan2.add(lab5,cbb);
		tf3=new JTextField(10);
		cbb.gridx=1;	cbb.gridy=1;	
		pan2.add(tf3,cbb);
		lab10=new JLabel("비밀번호확인");
		cbb.gridx=0;	cbb.gridy=2;	
		pan2.add(lab10,cbb);
		tf8=new JTextField(10);
		cbb.gridx=1;	cbb.gridy=2;	
		pan2.add(tf8,cbb);
		lab11=new JLabel("확인");
		lab11.setOpaque(true);
		lab11.setForeground(Color.red);
		cbb.gridx=1;	cbb.gridy=3;	
		pan2.add(lab11,cbb);
	
		
		
		lab6=new JLabel("전화번호");
		cbc.gridx=1;	cbc.gridy=0;	
		pan3.add(lab6,cbc);
		tf4=new JTextField(20);
		cbc.gridx=2;	cbc.gridy=0;
		pan3.add(tf4,cbc);
		
		lab13=new JLabel(" ");
		
		lab8=new JLabel("주        소");
		cby.gridx=1;	cby.gridy=0;
		pan8.add(lab8,cby);
		tf6=new JTextField(20);
		cby.gridx=2;	cby.gridy=0;
		pan8.add(tf6,cby);
		
		lab7=new JLabel(" email    ");
		cbf.gridx=1;	cbf.gridy=1;
		pan6.add(lab7,cbf);
		tf5=new JTextField(8);
		cbf.gridx=2;	cbf.gridy=1;
		pan6.add(tf5,cbf);
		lab11=new JLabel("@");
		cbf.gridx=3;	cbf.gridy=1;
		pan6.add(lab11,cbf);
		tf9=new JTextField(8);
		cbf.gridx=4;	cbf.gridy=1;
		pan6.add(tf9,cbf);
		cbf.gridx=5;	cbf.gridy=1;
		pan6.add(mail,cbf);
		
		cbj.gridx=0; cbj.gridy=0;
		pan11.add(lab3,cbj);
		cbj.gridx=1; cbj.gridy=0;
		pan11.add(pan7,cbj);
		cbj.gridx=0; cbj.gridy=0;
		pan9.add(pan8,cbj);
		cbj.gridx=0; cbj.gridy=1;
		pan9.add(lab13,cbj);
		cbj.gridx=0; cbj.gridy=2;
		pan9.add(pan3,cbj);
		cbk.gridx=0; cbk.gridy=1;
		pan10.add(pan11,cbk);
		cbk.gridx=0; cbk.gridy=2;
		pan10.add(pan2,cbk);
		cbk.gridx=0; cbk.gridy=3;
		pan10.add(pan9,cbk);
		cbk.gridx=0; cbk.gridy=4;
		pan10.add(pan6,cbk);
		cbk.gridx=0; cbk.gridy=5;
		pan10.add(pan4,cbk);
		cbk.gridx=0; cbk.gridy=6;
		pan10.add(pan5,cbk);
		
		
		lab9=new JLabel("계좌번호");
		cbd.gridx=0;	cbd.gridy=1;
		pan4.add(lab9,cbd);
		cbd.gridx=1;	cbd.gridy=1;
		pan4.add(bank,cbd);
		tf7=new JTextField(20);
		cbd.gridx=2;	cbd.gridy=1;
		pan4.add(tf7,cbd);
		this.setLayout(new GridLayout(1,1));
		//this.setSize(300,20);
		
		
		button1 = new JButton("가입하기");
		button3 = new JButton("취소");
		button4 = new JButton("중복확인");
		
		cbe.gridx=1;	cbe.gridy=1;
		pan5.add(button1,cbe);
		
		cbe.gridx=2;	cbe.gridy=1;
		lab14=new JLabel("      ");
		pan5.add(lab14,cbe);
		
		cbe.gridx=3;	cbe.gridy=1;
		pan5.add(button3,cbe);
		pan2.add(button4);
		
		add(pan10);
		
		//pack();
		//System.out.println(vo.getPgender().trim());
		
		//if(vo.getPgender().trim()=="M") 
			//male.setSelected(true);
		
		tf1.setText(vo.getPname());
		tf2.setText(vo.getPid());
		tf3.setText(vo.getPpwd());
		tf4.setText(vo.getPtel());
		tf5.setText(vo.getPemail());
		tf6.setText(vo.getPaddr());
		tf7.setText(vo.getPaccount());
		
		button1.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		setVisible(true);
	}
public void actionPerformed(ActionEvent ev) {
	Object o = ev.getSource();
	
	if(o == button1) {
		try {
			
		}catch (Exception e) { e.printStackTrace(); }
		}
	else if ( o == button3 ) {
		try {
			
		}catch (Exception e) { e.printStackTrace(); }
		}
}

public static void main(String[] args) {
	new Regist();
	}
}