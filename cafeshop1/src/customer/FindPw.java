package customer;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class FindPw extends JFrame implements ActionListener{
	JPanel  pan1, pan2, pan3, pan4,pan5,pan6;
	JLabel lid, ltel,mes, adtel, adem,message,message2,empty;
	TextField tf_id, tf_tel;
	JButton ok;
	CustomerDAO dao = new CustomerDAO();
	
	public static void main(String[] args) {
		FindPw Fpw = new  FindPw();
	}
	
	FindPw() {
		setFont(new Font("���ü", Font.BOLD,40));
		
		setBackground(Color.LIGHT_GRAY);
		setResizable(true);
		
		setLayout( new GridBagLayout() );
		GridBagConstraints	cb = new GridBagConstraints();
		cb.weightx	= 0.2;
		cb.weighty	 = 0.2;
		
		
		lid = new JLabel("ID   ");
		ltel = new JLabel("��ȭ��ȣ   ");
		mes = new JLabel("������ �Ұ����� ��� �Ʒ��� ����ó�� �����ٶ��ϴ�.");
		adem = new JLabel("e-mail : GMC01@naver.com  tel: 111-1111-1111");
		message= new JLabel("��й�ȣ�� �ؾ�����̳���?");
		message2= new JLabel("ID�� ��ȭ��ȣ�� ��Ȯ�� �Է����ּ���!");
		lid.setHorizontalAlignment(JLabel.RIGHT);
		ltel.setHorizontalAlignment(JLabel.RIGHT);	
		tf_id = new TextField(10);
		tf_tel = new TextField(10);
		empty= new JLabel("     ");
	

		pan1= new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		pan4 = new JPanel();
		pan5 = new JPanel();
		pan6= new JPanel();
		
		ok=new JButton("����");
		ok.addActionListener(this);

		pan2.setLayout( new GridBagLayout() );
		GridBagConstraints	cba = new GridBagConstraints();
		cba.weightx	= 0.2;
		cba.weighty	 = 0.2;
		
		pan3.setLayout(new GridLayout(2,1));
		pan6.setLayout(new GridLayout(2,1));
		
		cba.gridx	=	3;	 			cba.gridy	=  0;
		pan2.add(message,cba);
		cba.gridx	=	3;	 			cba.gridy	=  1;
		pan2.add(message2,cba);
		//cba.gridx	=	2;	 			cba.gridy	=  2;
		pan3.add(lid);
		//cba.gridx	=	3;	 			cba.gridy	=  2;
		//cba.gridx	=	2;	 			cba.gridy	=  3;
		//pan3.add(empty);
		pan3.add(ltel);

		pan6.add(tf_id);
		pan6.add(tf_tel);

		pan5.setLayout(new GridLayout(1,3,10,10));
		pan5.add(pan3);
		pan5.add(pan6);
		pan5.add(ok);
		
		
		
		
		
		pan4.setBorder(new TitledBorder("Contact Us"));

		pan4.setLayout(new GridLayout(2,1));

		pan4.add(mes);

		pan4.add(adem);

		pan1.setLayout(new GridLayout(3,1));
		pan1.add(pan2);
		pan1.add(pan5);
		pan1.add(pan4);
	
		
		add(pan1);
		
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(430, 240);
		setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		int count;
		if(o==ok) {
			String id;
			String tel;
			id = tf_id.getText();
			tel=tf_tel.getText();
			if(id.length()==0) {
				JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���!");
				return;
			}
			else if(tel.length()==0) {
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �Է��ϼ���!");
				return;
			}
			count = dao.findMyPwd(id,tel);
			
			if(count==1) {
			setVisible(false);}
			else {
				tf_id.setText("");
				tf_tel.setText("");
				return;}
			}
		}
	}	
