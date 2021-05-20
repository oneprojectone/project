package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.MenuDAO;


public class UpPage extends JFrame implements ActionListener {
	JButton checkUpdate;
	JPanel panel,panel1;
	JPanel n1,n2;
	JTextField cno,cname,cprice,Ncno,Ncname,Ncprice;
	JLabel lab1, lab2, lab3, lab4, lab5, lab6, lab7;
	 MenuDAO dao = new MenuDAO();
	 public UpPage(DefaultTableModel model,String no, String name, int price) {
		 Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
		n1 = new JPanel();
		 panel = new JPanel(new GridLayout(0,2));
		lab1 = new JLabel("상품 코드");
		lab1.setFont(font);
		n1.add(lab1);
		n1.add(cno = new JTextField(10));
		cno.setEditable(false);
		lab2 = new JLabel("상품 명");
		lab2.setFont(font);
		n1.add(lab2);
		n1.add(cname = new JTextField(10));
// setEnabled 추가
		cname.setEditable(false);
		lab3 = new JLabel("가격");
		lab3.setFont(font);
		n1.add(lab3);
		n1.add(cprice = new JTextField(10));
// setEnabled 추가
		cprice.setEditable(false);
		n2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lab4 = new JLabel("뉴 상품 코드");
		lab4.setFont(font);
		n2.add(lab4);
		n2.add(Ncno = new JTextField(10));
		Ncno.setEditable(false);
		lab5 = new JLabel("뉴 상품 명");
		lab5.setFont(font);
		n2.add(lab5);
		n2.add(Ncname = new JTextField(10));
		lab6 = new JLabel("뉴 가격");
		lab6.setFont(font);
		n2.add(lab6);
		n2.add(Ncprice = new JTextField(10));

		panel.add(n1);
		panel.add(n2);
		
		add(panel);
		
		panel1 = new JPanel();
		panel1.add(checkUpdate = new JButton("수정"));
		checkUpdate.setFont(font);
		add(panel1, "South");

		
		checkUpdate.addActionListener(this);
		
		
		
		setBounds(350,250,250,280);
		setVisible(true);
		cno.setText(no);
		cname.setText(name);
		cprice.setText(Integer.toString(price));
		Ncno.setText(cno.getText());
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
		
		 String sur5 = "";
		 Integer sur6 = 0;
		 
		if(e.getSource() == checkUpdate) {
			
			String sur1 = cno.getText();
		
			String sur4 = Ncno.getText();
			
			if(Ncname.getText().length() == 0 || Ncprice.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "수정할 값을 입력해주세요");
				return;
			} else {
				sur5 = Ncname.getText();
				sur6 = Integer.parseInt(Ncprice.getText());
			}
			
			dao.updateMenu(sur1,sur4,sur5,sur6);
			lab7 = new JLabel("수정 성공");
			lab7.setFont(font);
			JOptionPane.showMessageDialog(null, lab7);

			setVisible(false);
		}
	}


}
