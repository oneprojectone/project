package view;

import java.awt.Font;
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
	 JLabel lab1, lab2, lab3, lab4;
	public InsertPage (DefaultTableModel model) {
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);

		this.model = model;
		JPanel jpaenl = new JPanel();
		
		lab1 = new JLabel("상품 종류 :");
		lab1.setFont(font);
		jpaenl.add(lab1);
		jpaenl.add(txt1 = new JComboBox<String>(ComboCNO));
		lab2 = new JLabel("상품 명 :");
		lab2.setFont(font);
		jpaenl.add(lab2);
		jpaenl.add(txt2 = new JTextField(10));
		lab3 = new JLabel("가격 :");
		lab3.setFont(font);
		jpaenl.add(lab3);
		jpaenl.add(txt3 = new JTextField(10));

		
		JPanel JPaenlBtn = new JPanel();
		JPaenlBtn.add(insertBtn = new JButton("추가"));
		insertBtn.setFont(font);
		add(JPaenlBtn,"South");
		add(jpaenl);

		
		insertBtn.addActionListener(this);
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100,800,530,120);
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
				if(txt2.getText().equals("") || txt3.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "값을 입력해주세요");
							txt2.setText("");
							txt3.setText("");
					} else  {
						String chck = txt3.getText();
						boolean isNumberIc = chck.chars().allMatch(Character :: isDigit);
						if(isNumberIc == true) {
							String sur2 = txt2.getText().trim();
							Integer sur3 = Integer.parseInt(txt3.getText());
		
							dao.insertMenu(sur1, sur2, sur3);
							dao.showMenuAll(model);
							dao.insertTable1(MenuView.modelTable1, sur1);
							JOptionPane.showMessageDialog(null, "추가 성공");
						
							txt2.setText("");
							txt3.setText("");	
						} else {
							JOptionPane.showMessageDialog(null, "숫자가 아닙니다");
							txt2.setText("");
							txt3.setText("");
						}
					}
				}
			}
		}


