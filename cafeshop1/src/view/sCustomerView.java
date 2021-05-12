
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import customer.sCustomerDAO;
import customer.rec.scusData;


public class sCustomerView extends JPanel implements ActionListener
{
	JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8,lab9,lab10,lab11,lab12,lab13,lab14,lab15;
	ImageIcon image;
	JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9;
	JPanel pan1,pan2,pan3,pan4,pan5,pan6,pan7;
	JButton button1, button2, button3, button4;
	sCustomerDAO sdao=new sCustomerDAO();
	scusData sdto = new scusData();
	JRadioButton male, female;
	
	sCustomerView() {
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();
		pan4=new JPanel();
		pan5=new JPanel();
		pan6=new JPanel();
		pan7=new JPanel();
		
		male=new JRadioButton("����");
		female= new JRadioButton("����");
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
		
		
		
		lab1=new JLabel("������");
		//add(lab1);
		image = new ImageIcon("profile.png");
		lab3=new JLabel();
		lab3.setIcon(image);
		cbf.gridx	=	1;	 			cbf.gridy	=  0;			
		pan6.add(lab3,cbf);
		lab2=new JLabel("��	�� : ");
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
		lab5=new JLabel("��� : ");
		cbb.gridx=1;	cbb.gridy=1;	
		pan2.add(lab5,cbb);
		tf3=new JTextField(10);
		cbb.gridx=2;	cbb.gridy=1;	
		pan2.add(tf3,cbb);
	
		
		
		lab6=new JLabel("��ȭ��ȣ: ");
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
		lab8=new JLabel("�ּ� : ");
		cbc.gridx=1;	cbc.gridy=2;
		pan3.add(lab8,cbc);
		tf6=new JTextField(20);
		cbc.gridx=2;	cbc.gridy=2;
		pan3.add(tf6,cbc);
		
		lab9=new JLabel("�� ����: ");
		cbd.gridx=1;	cbd.gridy=0;
		pan4.add(lab9,cbd);
		tf7=new JTextField(25);
		cbd.gridx=2;	cbd.gridy=0;
		pan4.add(tf7,cbd);
		lab10=new JLabel("���� ��¥ : ");
		cbe.gridx=1;	cbe.gridy=0;
		pan5.add(lab10,cbe);
		lab11=new JLabel();
		cbe.gridx=2;	cbe.gridy=0;
		pan5.add(lab11,cbe);
		this.setLayout(new GridLayout(5,1));
		this.setSize(300,20);

		button1 = new JButton("�� ���� ����");
		button2 = new JButton("���� ���");
		button3 = new JButton("Ż��");
		button4 = new JButton("�α׾ƿ�");
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
		
		sdao.showMyInfo(sdto);
		
	
		
		System.out.println(sdto.getPgender().trim());
		
		
		
		
		if(sdto.getPgender().trim()=="M") 
			male.setSelected(true);
		
		tf1.setText(sdto.getPname());
		tf2.setText(sdto.getPid());
		tf3.setText(sdto.getPpwd());
		tf4.setText(sdto.getPtel());
		tf5.setText(sdto.getPemail());
		tf6.setText(sdto.getPaddr());
		tf7.setText(sdto.getPaccount());
		lab11.setText(sdto.getPdate());
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		
	}
		public void actionPerformed( ActionEvent ev ){
			Object o = ev.getSource();
			
			if(o == button1) {
				try {
		            
		          
		            sdto.setPname(tf1.getText());
		            sdto.setPid(tf2.getText());
		            sdto.setPpwd(tf3.getText());
		            sdto.setPtel(tf4.getText());
		            sdto.setPemail(tf5.getText());
		            sdto.setPaddr(tf6.getText());
		            sdto.setPaccount(tf7.getText());
		            sdao.modifyMyInfo(sdto);
		            
		            tf1.setText(sdto.getPname());
		    		tf2.setText(sdto.getPid());
		    		tf3.setText(sdto.getPpwd());
		    		tf4.setText(sdto.getPtel());
		    		tf5.setText(sdto.getPemail());
		    		tf6.setText(sdto.getPaddr());
		    		tf7.setText(sdto.getPaccount());
		    		lab11.setText(sdto.getPdate());
				
				}catch (Exception e) {
		             JOptionPane.showMessageDialog
		             (null, "�� ���� ���� ���� : " + e.getMessage());
				}
			}
			else if ( o == button2 ) {
				try {
					
					JOptionPane.showMessageDialog(null, "ȸ������ ������ ����մϴ�.");
					tf1.setText(sdto.getPname());
		    		tf2.setText(sdto.getPid());
		    		tf3.setText(sdto.getPpwd());
		    		tf4.setText(sdto.getPtel());
		    		tf5.setText(sdto.getPemail());
		    		tf6.setText(sdto.getPaddr());
		    		tf7.setText(sdto.getPaccount());
		    		lab11.setText(sdto.getPdate());
				
				}catch (Exception e)
				{e.printStackTrace(); }
				
			}else if ( o == button3 ) {
				try {
					String id = tf2.getText();
					sdao.deleteMyInfo(id);
					System.out.println("���� ����");
					System.exit(0);
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "���� ���� : " + e.getMessage());
				}
			}
			else if ( o == button4 ) {
				System.exit(0);
			}
	}

}

   
   
   
   
              