package five;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsertPage extends JFrame implements ActionListener{
	
	 JTextField txt1;
	 JTextField txt2;
	 JTextField txt3;
	 JButton insertBtn;
	 PreparedStatement pstmtInsert;
	 Connection conn;
	private String sqlInsert = "insert into menu values(?,?,?)";
	InsertPage () {
		JPanel jpaenl = new JPanel();
		
		jpaenl.add(new JLabel("상품 코드"));
		jpaenl.add(txt1 = new JTextField(10));
		jpaenl.add(new JLabel("상 품 명"));
		jpaenl.add(txt2 = new JTextField(10));
		jpaenl.add(new JLabel("가 격"));
		jpaenl.add(txt3 = new JTextField(10));
		
		JPanel JPaenlBtn = new JPanel();
		JPaenlBtn.add(insertBtn = new JButton("추가"));
		add(JPaenlBtn,"South");
		add(jpaenl);
		
		insertBtn.addActionListener(this);
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				new MenuMain();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350,250,530,100);
		setVisible(true);
		dbConnect();
	}
	private void dbConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "lion", "1234");
			
			pstmtInsert = conn.prepareStatement(sqlInsert);
			System.out.println("연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 public void insert() {
		 String sur1 = txt1.getText();
		String sur2 = txt2.getText();
		String sur3 = txt3.getText();
		 try {
		pstmtInsert.setString(1,sur1);
		pstmtInsert.setString(2,sur2);
		pstmtInsert.setString(3,sur3);
		pstmtInsert.executeUpdate();
		JOptionPane.showMessageDialog(null, "추가 성공");
		
		txt1.setText("");
		txt2.setText("");
		txt3.setText("");
		
		
		 } catch(Exception e) {
			 System.out.println("실패" + e);
	 }
		 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertBtn) {
			insert();
		}
	}
}
