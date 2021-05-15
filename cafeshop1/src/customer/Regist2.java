package customer;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Regist2 extends JPanel implements ActionListener, KeyListener {

	

	JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8,lab9,lab10,lab11,lab12,lab13,lab14,lab15,lab16,lab17,lab18,lab19,lab20;
	ImageIcon imagem, imagef;
	JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9;
	JPanel pan1,pan2,pan3,pan4,pan5,pan6,pan7, pan8, pan9, pan10, pan11,pan12,pan13,pan14,pan15,pan16,pan17;
	JButton button1,button2, button3;
	CustomerDAO dao = new CustomerDAO();
	CustomerVO vo = new CustomerVO();
	JRadioButton male, female;
	JComboBox bank, mail;
	
	Regist2() {
		
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
		pan12=new JPanel();
		pan13=new JPanel();
		pan14=new JPanel();
		pan15=new JPanel();
		pan16=new JPanel();
		pan17=new JPanel();
		Font font = new Font("고딕체", Font.BOLD, 12);
		
		String	BankText[]= {"선택", "COSMO", "HAPPY" , "SINNAH", "GMOD" };
		String	MailText[]= {"직접입력", "naver.com", "gmail.com" , "hanmail.net", "nate.com", "paran.com" };
		bank		= new JComboBox(BankText);
		mail		= new JComboBox(MailText);
		imagem = new ImageIcon("m.png");
		imagef = new ImageIcon("f.png");
		lab1 = new JLabel();
		male=new JRadioButton("남자");
		female= new JRadioButton("여자");
		ButtonGroup mf = new ButtonGroup();
		male.setSelected(true);
		if(male.isSelected()) {
			lab1.setIcon(imagem);
		}
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
		GridBagConstraints	cbh = new GridBagConstraints();
		cbh.weightx	= 0.1;
		cbh.weighty	 = 0.1;

		pan9.setLayout( new GridBagLayout() );
		GridBagConstraints	cbi = new GridBagConstraints();
		cbi.weightx	= 0.1;
		cbi.weighty	 = 0.1;
		
		pan10.setLayout( new GridBagLayout() );
		GridBagConstraints	cbj = new GridBagConstraints();
		cbj.weightx	= 0.1;
		cbj.weighty	 = 0.1;
		
		pan11.setLayout( new GridBagLayout() );
		GridBagConstraints	cbk = new GridBagConstraints();
		cbk.weightx	= 0.1;
		cbk.weighty	 = 0.1;
		
		pan12.setLayout( new GridBagLayout() );
		GridBagConstraints	cbl = new GridBagConstraints();
		cbl.weightx	= 0.1;
		cbl.weighty	 = 0.1;
		
		pan13.setLayout( new GridBagLayout() );
		GridBagConstraints	cbm = new GridBagConstraints();
		cbm.weightx	= 0.1;
		cbm.weighty	 = 0.1;
		
		pan14.setLayout( new GridBagLayout() );
		GridBagConstraints	cbn = new GridBagConstraints();
		cbn.weightx	= 0.1;
		cbn.weighty	 = 0.1;
		
		pan15.setLayout( new GridBagLayout() );
		GridBagConstraints	cbo = new GridBagConstraints();
		cbo.weightx	= 0.1;
		cbo.weighty	 = 0.1;
		
		pan16.setLayout( new GridBagLayout() );
		GridBagConstraints	cbp = new GridBagConstraints();
		cbp.weightx	= 0.1;
		cbp.weighty	 = 0.1;
		
		pan17.setLayout( new GridBagLayout() );
		GridBagConstraints	cbq = new GridBagConstraints();
		cbq.weightx	= 0.1;
		cbq.weighty	 = 0.1;
		
		
		cba.gridx = 0; 		cba.gridy = 0;
		pan1.add(pan2, cba);
		cba.gridx = 0; 		cba.gridy = 1;
		pan1.add(pan6, cba);
		cba.gridx = 0; 		cba.gridy = 2;
		pan1.add(pan7, cba);
		cba.gridx = 0; 		cba.gridy = 3;
		pan1.add(pan8, cba);
		cba.gridx = 0; 		cba.gridy = 4;
		pan1.add(pan9, cba);
		cba.gridx = 0; 		cba.gridy = 5;
		pan1.add(pan10, cba);
		cba.gridx = 0; 		cba.gridy = 6;
		pan1.add(pan11, cba);
		cba.gridx = 0; 		cba.gridy = 7;
		pan1.add(pan14, cba);
		cba.gridx = 0; 		cba.gridy = 8;
		pan1.add(pan15, cba);
		cba.gridx = 0; 		cba.gridy = 9;
		pan1.add(pan16, cba);
		
		
		
		cbb.gridx=0; 		cbb.gridy=0;
		pan2.add(pan5, cbb);
		lab16=new JLabel("        ");
		cbb.gridx=1; 		cbb.gridy=0;
		pan2.add(lab16, cbb);
		cbb.gridx=2; 		cbb.gridy=0;
		pan2.add(pan17, cbb);
		
		cbb.gridx =0;	 			cbc.gridy	=  0;
		pan5.add(lab1,cbe);
		
		cbq.gridx=0; 		cbq.gridy=1;
		pan17.add(pan3, cbq);
		tf1=new JTextField(10);
		cbq.gridx=0; 		cbq.gridy=2;
		pan17.add(tf1, cbq);
		cbq.gridx=0; 		cbq.gridy=3;
		pan17.add(pan4, cbq);
		
		lab2=new JLabel("이	름");
		cbc.gridx	=0;	 			cbc.gridy	=  0;
		pan3.add(lab2,cbc);
		lab3=new JLabel("                         ");
		cbc.gridx	=1;	 			cbc.gridy	=  0;
		pan3.add(lab3,cbc);
		
		cbd.gridx	=0;	 			cbd.gridy	=  0;
		pan4.add(male,cbd);
		cbd.gridx	=1;	 			cbd.gridy	=  0;
		pan4.add(female,cbd);
		
		lab4=new JLabel("      I                   D      ");
		cbf.gridx=0; 		cbf.gridy=0;
		pan6.add(lab4, cbf);
		
		tf2=new JTextField(15);
		cbf.gridx=1; 		cbf.gridy=0;
		pan6.add(tf2, cbf);

		lab15=new JLabel("  ");
		cbf.gridx=2; 		cbf.gridy=0;
		pan6.add(lab15, cbf);
		
		button1 = new JButton("중복확인");
		cbf.gridx=3; 		cbf.gridy=0;
		pan6.add(button1, cbf);
		
		lab5=new JLabel("비   밀   번   호    ");
		cbg.gridx=0; 		cbg.gridy=0;
		pan7.add(lab5, cbg);
		
		tf3=new JTextField(15);
		cbg.gridx=1; 		cbg.gridy=0;
		pan7.add(tf3, cbg);
		
		lab8=new JLabel("                         ");
		cbg.gridx=2; 		cbg.gridy=0;
		pan7.add(lab8, cbg);
 
		lab6=new JLabel("비밀번호확인     ");
		cbh.gridx=0; 		cbh.gridy=0;
		pan8.add(lab6, cbh);
		
		tf4=new JTextField(15);
		cbh.gridx=1; 		cbh.gridy=0;
		pan8.add(tf4, cbh);
		
		lab9=new JLabel("                         ");
		cbh.gridx=2; 		cbh.gridy=0;
		pan8.add(lab9, cbh);
		
		lab7=new JLabel("비밀번호를 입력해주세요.");
		cbh.gridx=1; 		cbh.gridy=1;
		pan8.add(lab7, cbh);
		
		lab10=new JLabel("    주                 소     ");
	    cbk.gridx=0;   cbk.gridy=0;
	    pan10.add(lab10,cbk);
	    tf5=new JTextField(15);
	    cbk.gridx=1;   cbk.gridy=0;
	    pan10.add(tf5,cbk);
	    lab18=new JLabel("                             ");
	    cbk.gridx=2; 	cbk.gridy=0;
	    pan10.add(lab18, cbk);
	      
	    lab11=new JLabel("    전   화   번   호    ");
	    cbl.gridx=0;   cbl.gridy=0;   
	    pan11.add(lab11,cbl);
	    tf6=new JTextField(15);
	    cbl.gridx=1;   cbl.gridy=0;
	    pan11.add(tf6,cbl);
	    lab19=new JLabel("                             ");
		cbl.gridx=2; 	cbl.gridy=0;
		pan11.add(lab19, cbl);
	      
	      
	      lab12=new JLabel("      E   -   M  A  I  L     ");
	      cbo.gridx=1;   cbo.gridy=0;
	      pan14.add(lab12,cbo);
	      tf7=new JTextField(6);
	      cbm.gridx=0;   cbm.gridy=0;
	      pan12.add(tf7,cbm);
	      lab13=new JLabel(" @ ");
	      cbm.gridx=1;   cbm.gridy=0;
	      pan12.add(lab13,cbm);
	      tf8=new JTextField(7);
	      cbm.gridx=2;   cbm.gridy=0;
	      pan12.add(tf8,cbm);
	      cbm.gridx=5;   cbm.gridy=0;
	      pan12.add(mail,cbm);
	      cbo.gridx=2;   cbo.gridy=0;
	      pan14.add(pan12,cbo);

	      lab14=new JLabel("   계   좌   번   호     ");
	      cbp.gridx=0;   cbp.gridy=0;
	      pan15.add(lab14,cbp);
	      cbn.gridx=0;   cbn.gridy=0;
	      pan13.add(bank,cbn);
	      lab17=new JLabel("   ");
	      cbn.gridx=1;   cbn.gridy=0;
	      pan13.add(lab17,cbn);
	      tf9=new JTextField(16);
	      cbn.gridx=2;   cbn.gridy=0;
	      pan13.add(tf9,cbn);
	      cbp.gridx=1;   cbp.gridy=0;
	      pan15.add(pan13,cbp);
		
	      button2 = new JButton("가입하기");
	      cbq.gridx=1;   cbq.gridy=1;
	      pan16.add(button2,cbq);
	      
	      cbq.gridx=2;   cbq.gridy=1;
	      lab15=new JLabel("      ");
	      pan16.add(lab15,cbq);
	      
	      button3 = new JButton("취     소");
	      cbq.gridx=3;   cbq.gridy=1;
	      pan16.add(button3,cbq);
	      
	      add(pan1);
	      
	      
		this.setLayout(new GridLayout(1,1));
		
		male.addActionListener(this);
		female.addActionListener(this);
		tf3.addKeyListener(this);
		tf4.addKeyListener(this);
		lab7.setFont(font);
		setVisible(true);
		
	}
public void actionPerformed(ActionEvent ev) {
	Object o = ev.getSource();
	if(o == male) {
		if(male.isSelected()) {
			lab1.setIcon(imagem);
		}
	} else {
		if(female.isSelected()){
			lab1.setIcon(imagef);
		}
	}
}

public void keyTyped(KeyEvent e) {
	Object o = e.getSource();
	if (o == tf3) {
            String pwd1 = tf3.getText();
            System.out.println(e.getKeyChar());
            String kkk = e.getKeyChar() + "";
            pwd1 =  pwd1 + kkk;
            if (
                	(tf3.getText().length()== 0) && (tf4.getText().length()== 0)) {
                	lab7.setText("비밀번호를 입력해주세요.");
                	lab7.setForeground(Color.black);
                }
            if(pwd1.equals(tf4.getText())) {
            	lab7.setText("비밀번호가 일치합니다.");
            	lab7.setForeground(Color.GREEN);
            } else {
            	lab7.setText("비밀번호가 일치하지 않습니다.");
            	lab7.setForeground(Color.red);
            } 
            System.out.println(pwd1);
			}
	
	if (o == tf4) {
			String pwd2 = tf4.getText();
			System.out.println(e.getKeyChar());
			String kkk = e.getKeyChar() + "";
			pwd2 =  pwd2 + kkk;
			if (
                	tf3.getText().length()== 0 && tf4.getText().length()== 0) {
                	lab7.setText("비밀번호를 입력해주세요.");
                	lab7.setForeground(Color.black);
                }
			if(pwd2.equals(tf3.getText())) {
            	lab7.setText("비밀번호가 일치합니다.");
            	lab7.setForeground(Color.GREEN);
            } else {
            	lab7.setText("비밀번호가 일치하지 않습니다.");
            	lab7.setForeground(Color.red);
            } 
			
			System.out.println(pwd2);
			}
		}

public void keyPressed(KeyEvent e) {
}
public void keyReleased(KeyEvent e) {
}
}
	
