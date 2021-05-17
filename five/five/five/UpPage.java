package five;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpPage extends JFrame implements ActionListener {
	JButton checkUpdate;
	JPanel panel,panel1;
	JPanel n1,n2;
	JTextField cno,cname,cprice,Ncno,Ncname,Ncprice;
	
	 MenuDAO dao = new MenuDAO();
	 UpPage(String no, String name, int price) {
		panel = new JPanel(new GridLayout(0,2));
		n1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		n1.add(new JLabel("상품 코드"));
		n1.add(cno = new JTextField(10));
		cno.setEditable(false);
		n1.add(new JLabel("상품명"));
		n1.add(cname = new JTextField(10));
		n1.add(new JLabel("가 격"));
		n1.add(cprice = new JTextField(10));
		n2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		n2.add(new JLabel("뉴상품 코드"));
		n2.add(Ncno = new JTextField(10));
		Ncno.setEditable(false);
		n2.add(new JLabel("뉴상품명"));
		n2.add(Ncname = new JTextField(10));
		n2.add(new JLabel("뉴가 격"));
		n2.add(Ncprice = new JTextField(10));
		panel.add(n1);
		panel.add(n2);
		
		add(panel);
		
		panel1 = new JPanel();
		panel1.add(checkUpdate = new JButton("수정"));
		add(panel1, "South");
		
		checkUpdate.addActionListener(this);
		
		
	
		setBounds(250,590,250,280);
		setVisible(true);
		cno.setText(no);
		cname.setText(name);
		cprice.setText(Integer.toString(price));
		Ncno.setText(cno.getText());
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == checkUpdate) {
			
			String sur1 = cno.getText();
		
			String sur4 = Ncno.getText();
			String sur5 = Ncname.getText();
			Integer sur6 = Integer.parseInt(Ncprice.getText());
			
			dao.UpdateMyInfo(sur1,sur4,sur5,sur6);
			JOptionPane.showMessageDialog(null, "수정 성공");
			setVisible(false);
		}
	}


}
