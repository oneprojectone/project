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
	
	 MenuDAO dao = new MenuDAO();
	
	 
	
	InsertPage () {
		JPanel jpaenl = new JPanel();
		
		jpaenl.add(new JLabel("��ǰ �ڵ�"));
		jpaenl.add(txt1 = new JTextField(10));
		jpaenl.add(new JLabel("�� ǰ ��"));
		jpaenl.add(txt2 = new JTextField(10));
		jpaenl.add(new JLabel("�� ��"));
		jpaenl.add(txt3 = new JTextField(10));
		
		JPanel JPaenlBtn = new JPanel();
		JPaenlBtn.add(insertBtn = new JButton("�߰�"));
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
		
	}
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertBtn) {
			 String sur1 = txt1.getText().trim();
			String sur2 = txt2.getText().trim();
			Integer sur3 = Integer.parseInt(txt3.getText());
			dao.InsertCname(sur1, sur2, sur3);
			
			JOptionPane.showMessageDialog(null, "�߰� ����");
			txt1.setText("");
			txt2.setText("");
			txt3.setText("");
		}
	}
}
