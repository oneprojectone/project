package view;

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

import view.MenuView;
import dao.MenuDAO;

public class InsertPage extends JFrame implements ActionListener{
	
	 JComboBox<String> txt1;
	 JTextField txt2;
	 JTextField txt3;
	 JButton insertBtn;
	 String ComboCNO[] = {"커피", "디저트", "기타"};
	 MenuDAO dao = new MenuDAO();
	 String sur1;
	 DefaultTableModel model = null;
	public InsertPage (DefaultTableModel model) {
		this.model = model;
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
			if(txt1.getSelectedItem() == "커피") {
				
				int count =dao.getMenuNo(model, "C");
				if(count < 10) {
					sur1 = "C00" + count;
				}else if(count <100) {
					sur1 = "C0" + count;
				} else 
					sur1 = "C" + count;
			}else if(txt1.getSelectedItem() == "디저트") {
				int count = dao.getMenuNo(model,"D");
				if(count < 10) {
					sur1 = "D00" + count;
				}else if(count <100) {
					sur1 = "D0" + count;
				} else 
					sur1 = "D" + count;
			}else {
				int count = dao.getMenuNo(model, "A");
				if(count < 10) {
					sur1 = "A00" + count;
				}else if(count <100) {
					sur1 = "A0" + count;
				} else 
					sur1 = "A" + count;
			}
		}
			String sur2 = txt2.getText().trim();
			Integer sur3 = Integer.parseInt(txt3.getText());
			dao.insertMenu(sur1, sur2, sur3);
			dao.showMenuAll(model);
			JOptionPane.showMessageDialog(null, "추가 성공");
			
			txt2.setText("");
			txt3.setText("");
		}
	}


