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
	 
	public UpPage() {
		panel = new JPanel(new GridLayout(0,2));
		n1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
		n2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		n2.add(new JLabel("뉴상품 코드"));
		n2.add(Ncno = new JTextField(10));
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
		
		
		setSize(250,280);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == checkUpdate) {
			
			String sur4 = Ncno.getText();
			String sur5 = Ncname.getText();
			Integer sur6 = Integer.parseInt(Ncprice.getText());
			
			
			JOptionPane.showMessageDialog(null, "수정 성공");
			cno.setText("");
			cname.setText("");
			cprice.setText("");
		}
	}

	

}
