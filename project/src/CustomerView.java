
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class CustomerView extends JPanel implements ActionListener
{
	JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8,lab9,lab10,lab11,lab12,lab13,lab14,lab15;
	ImageIcon image;
	JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9;
	JPanel pan1,pan2,pan3,pan4,pan5,pan6,pan7;
	JButton button1, button2, button3, button4;
	CustomerDAO dao=new CustomerDAO();
	cusData dto = new cusData();
	JRadioButton male, female;
	
	CustomerView() {
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();
		pan4=new JPanel();
		pan5=new JPanel();
		pan6=new JPanel();
		pan7=new JPanel();
		
		male=new JRadioButton("남자");
		female= new JRadioButton("여자");
		ButtonGroup mf = new ButtonGroup();
		mf.add(male);
		mf.add(female);
		
		pan1.setLayout( new GridBagLayout() );
		GridBagConstraints	cba = new GridBagConstraints();
		cba.weightx	= 0.2;
		cba.weighty	 = 0.2;
		
		pan2.setLayout( new GridBagLayout() );
		GridBagConstraints	cbb = new GridBagConstraints();
		cbb.weightx	= 0.2;
		cbb.weighty	 = 0.2;
		
		
		pan3.setLayout( new GridBagLayout() );
		GridBagConstraints	cbc = new GridBagConstraints();
		cbc.weightx	= 0.2;
		cbc.weighty	 = 0.2;
		
		pan4.setLayout( new GridBagLayout() );
		GridBagConstraints	cbd = new GridBagConstraints();
		cbd.weightx	= 0.2;
		cbd.weighty	 = 0.2;
		
		pan5.setLayout( new GridBagLayout() );
		GridBagConstraints	cbe = new GridBagConstraints();
		cbe.weightx	= 0.2;
		cbe.weighty	 = 0.2;
		
		pan6.setLayout( new GridBagLayout() );
		GridBagConstraints	cbf = new GridBagConstraints();
		cbf.weightx	= 0.2;
		cbf.weighty	 = 0.2;
		
		pan7.setLayout( new GridBagLayout() );
		GridBagConstraints	cbg = new GridBagConstraints();
		cbg.weightx	= 0.2;
		cbg.weighty	 = 0.2;
		
		
		
		lab1=new JLabel("프로필");
		//add(lab1);
		image = new ImageIcon("profile.png");
		lab3=new JLabel();
		lab3.setIcon(image);
		cbf.gridx	=	1;	 			cbf.gridy	=  0;			
		pan6.add(lab3,cbf);
		lab2=new JLabel("이	름 : ");
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
		
		lab4=new JLabel("ID : ");
		cbb.gridx=1;	cbb.gridy=0;	
		pan2.add(lab4,cbb);
		tf2=new JTextField(10);
		cbb.gridx=2;	cbb.gridy=0;	
		pan2.add(tf2,cbb);
		lab5=new JLabel("비번 : ");
		cbb.gridx=1;	cbb.gridy=1;	
		pan2.add(lab5,cbb);
		tf3=new JTextField(10);
		cbb.gridx=2;	cbb.gridy=1;	
		pan2.add(tf3,cbb);
	
		
		
		lab6=new JLabel("전화번호: ");
		cbc.gridx=1;	cbc.gridy=0;	
		pan3.add(lab6,cbc);
		tf4=new JTextField(20);
		cbc.gridx=2;	cbc.gridy=0;
		pan3.add(tf4,cbc);
		lab7=new JLabel("email: ");
		cbc.gridx=1;	cbc.gridy=1;
		pan3.add(lab7,cbc);
		tf5=new JTextField(20);
		cbc.gridx=2;	cbc.gridy=1;
		pan3.add(tf5,cbc);
		lab8=new JLabel("주소 : ");
		cbc.gridx=1;	cbc.gridy=2;
		pan3.add(lab8,cbc);
		tf6=new JTextField(20);
		cbc.gridx=2;	cbc.gridy=2;
		pan3.add(tf6,cbc);
		
		lab9=new JLabel("내 계좌: ");
		cbd.gridx=1;	cbd.gridy=0;
		pan4.add(lab9,cbd);
		tf7=new JTextField(25);
		cbd.gridx=2;	cbd.gridy=0;
		pan4.add(tf7,cbd);
		lab10=new JLabel("가입 날짜 : ");
		cbe.gridx=1;	cbe.gridy=0;
		pan5.add(lab10,cbe);
		lab11=new JLabel();
		cbe.gridx=2;	cbe.gridy=0;
		pan5.add(lab11,cbe);
		this.setLayout(new GridLayout(5,1));
		this.setSize(300,20);

		button1 = new JButton("내 정보 수정");
		button2 = new JButton("수정 취소");
		button3 = new JButton("탈퇴");
		button4 = new JButton("로그아웃");
		cbe.gridx=1;	cbe.gridy=1;
		pan5.add(button1,cbe);
		cbe.gridx=2;	cbe.gridy=1;
		pan5.add(button2,cbe);
		cbe.gridx=3;	cbe.gridy=1;
		pan5.add(button3,cbe);
		pan2.add(button4);
		
		add(pan7);
		add(pan2);
		add(pan3);
		add(pan4);
		add(pan5);
		
		dao.showMyInfo(dto);
		if(dto.getPgender()=="M") {
			male.getSelectedIcon();
		}
		else female.getSelectedIcon();
		
		tf1.setText(dto.getPname());
		tf2.setText(dto.getPid());
		tf3.setText(dto.getPpwd());
		tf4.setText(dto.getPtel());
		tf5.setText(dto.getPemail());
		tf6.setText(dto.getPaddr());
		tf7.setText(dto.getPaccount());
		lab11.setText(dto.getPdate());
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		
	}
		public void actionPerformed( ActionEvent ev ){
			Object o = ev.getSource();
			
			if(o == button1) {
				try {
		            
		          
		            dto.setPname(tf1.getText());
		            dto.setPid(tf2.getText());
		            dto.setPpwd(tf3.getText());
		            dto.setPtel(tf4.getText());
		            dto.setPemail(tf5.getText());
		            dto.setPaddr(tf6.getText());
		            dto.setPaccount(tf7.getText());
		            dao.modifyMyInfo(dto);
		            
		            tf1.setText(dto.getPname());
		    		tf2.setText(dto.getPid());
		    		tf3.setText(dto.getPpwd());
		    		tf4.setText(dto.getPtel());
		    		tf5.setText(dto.getPemail());
		    		tf6.setText(dto.getPaddr());
		    		tf7.setText(dto.getPaccount());
		    		lab11.setText(dto.getPdate());
				
				}catch (Exception e) {
		             JOptionPane.showMessageDialog
		             (null, "내 정보 수정 실패 : " + e.getMessage());
				}
			}
			else if ( o == button2 ) {
				try {
					
					JOptionPane.showMessageDialog(null, "회원정보 수정을 취소합니다.");
					tf1.setText(dto.getPname());
		    		tf2.setText(dto.getPid());
		    		tf3.setText(dto.getPpwd());
		    		tf4.setText(dto.getPtel());
		    		tf5.setText(dto.getPemail());
		    		tf6.setText(dto.getPaddr());
		    		tf7.setText(dto.getPaccount());
		    		lab11.setText(dto.getPdate());
				
				}catch (Exception e)
				{e.printStackTrace(); }
				
			}else if ( o == button3 ) {
				try {
					String id = tf2.getText();
					dao.deleteMyInfo(id);
					System.out.println("삭제 성공");
					System.exit(0);
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "삭제 실패 : " + e.getMessage());
				}
			}
			else if ( o == button4 ) {
				System.exit(0);
			}
	}

}

   
   
   
   
              