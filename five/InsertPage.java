package five;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class InsertPage extends JFrame implements ActionListener{
	
	 JComboBox<String> txt1;
	 JTextField txt2;
	 JTextField txt3;
	 JButton insertBtn;
	 String ComboCNO[] = {"커피", "디저트", "기타"};
	 MenuDAO dao = new MenuDAO();
	 String sur1;
	public InsertPage () {
		JPanel jpaenl = new JPanel();
		
		jpaenl.add(new JLabel("상품 종류 :"));
		jpaenl.add(txt1 = new JComboBox<String>(ComboCNO));
		jpaenl.add(new JLabel("상 품 명 :"));
		jpaenl.add(txt2 = new JTextField(10));
		jpaenl.add(new JLabel("가 격 :"));
		jpaenl.add(txt3 = new JTextField(10));
		
		JPanel JPaenlBtn = new JPanel();
		JPaenlBtn.add(insertBtn = new JButton("추가"));
		add(JPaenlBtn,"South");
		add(jpaenl);
		
		insertBtn.addActionListener(this);
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350,250,530,120);
		setVisible(true);
		
	}
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == insertBtn) {
			if(MenuMain.count[0] <10 || MenuMain.count[1] <10 || MenuMain.count[2] <10) {
			if(txt1.getSelectedItem() == "커피") {
				String CnoC = "C";
				dao.CountCNO(MenuMain.modelTable,CnoC);
				MenuMain.count[0] = MenuDAO.count;
				 sur1 = "C0" + MenuMain.count[0];
				
			} else if(txt1.getSelectedItem() == "디저트") {
				String CnoC = "D";
				dao.CountCNO(MenuMain.modelTable,CnoC);
				MenuMain.count[1] = MenuDAO.count;
				 sur1 = "D0" + MenuMain.count[1];
				
			} else {
				String CnoC = "A";
				dao.CountCNO(MenuMain.modelTable,CnoC);
				MenuMain.count[2] = MenuDAO.count;
				 sur1 = "A0" + MenuMain.count[2];
				 MenuMain.count[2]++;
			}	
		} else {
			if(txt1.getSelectedItem() == "커피") {
				String CnoC = "C";
				dao.CountCNO(MenuMain.modelTable,CnoC);
				MenuMain.count[0] = MenuDAO.count;
				 sur1 = "C" + MenuMain.count[0];
				 MenuMain.count[0]++;
				
			} else if(txt1.getSelectedItem() == "디저트") {
				String CnoC = "D";
				dao.CountCNO(MenuMain.modelTable,CnoC);
				MenuMain.count[1] = MenuDAO.count;
				sur1 = "D" + MenuMain.count[1];
				 MenuMain.count[1]++;
				
			} else {
				String CnoC = "A";
				dao.CountCNO(MenuMain.modelTable,CnoC);
				MenuMain.count[2] = MenuDAO.count;
				 sur1 = "A" + MenuMain.count[2];
				 MenuMain.count[2]++;
			}
		}
			String sur2 = txt2.getText().trim();
			Integer sur3 = Integer.parseInt(txt3.getText());
			dao.InsertCname(sur1, sur2, sur3);
			dao.selectAll(MenuMain.modelTable);
			JOptionPane.showMessageDialog(null, "추가 성공");
			
			txt2.setText("");
			txt3.setText("");
		}
	}
}

