package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import customer.CustomerDAO;
import customer.rec.CustomerVO;

public class CustomerView extends JPanel implements ActionListener {
	
	JTextField   tfCustName, tfCustId, tfCustPwd, tfCustGender, 
				tfCustEmail, tfCustTel, tfCustAddr, tfCustAccount, tfCustDate;
	
	JButton		bCustDelete, bCustModify, bCustNameSearch;
	
	JTextField  tfCustNameSearch;
	
	CustomerDAO customer = null;
	
	public CustomerView(){
		   addLayout();
		   
		   try {
			   customer = new CustomerDAO();
			   System.out.println("DB연결 성공");
		   } catch (Exception e) {
			   JOptionPane.showMessageDialog(null, "DB연결 실패" + e.getMessage());
		   }
	   }

public void addLayout(){
	tfCustName = new JTextField(30); 		tfCustId = new JTextField(30);
	tfCustPwd = new JTextField(30); 		tfCustGender = new JTextField(30);
	tfCustEmail = new JTextField(30); 		tfCustTel = new JTextField(30);
	tfCustAddr = new JTextField(30); 		tfCustAccount = new JTextField(30);
	tfCustDate = new JTextField(30); 		
	
	tfCustNameSearch = new JTextField(30);
	
	bCustDelete	= new JButton("삭제");
    bCustModify = new JButton("수정");
    bCustNameSearch = new JButton("검색");
    
    JPanel pModify = new JPanel();
    pModify.setLayout( new GridBagLayout() );
       GridBagConstraints cbc = new GridBagConstraints();
       cbc.weightx = 1.0;
       cbc.weighty = 1.0;
       cbc.fill    = GridBagConstraints.BOTH;
    cbc.gridx = 0;             cbc.gridy = 0;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(new JLabel("   이   름   "), cbc);
    cbc.gridx = 1;             cbc.gridy = 0;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(tfCustName, cbc);
    cbc.gridx = 2;             cbc.gridy = 0;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(new JLabel("   I   D   "), cbc);
    cbc.gridx = 3;             cbc.gridy = 0;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(tfCustId, cbc);
    cbc.gridx = 0;             cbc.gridy = 1;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(new JLabel("  PASSWORD  "), cbc);
    cbc.gridx = 1;             cbc.gridy = 1;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(tfCustPwd, cbc);
    cbc.gridx = 2;             cbc.gridy = 1;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(new JLabel("   성   별   "), cbc);
    cbc.gridx = 3;             cbc.gridy = 1;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(tfCustGender, cbc);
    cbc.gridx = 0;             cbc.gridy = 2;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(new JLabel("   이 메 일   "), cbc);
    cbc.gridx = 1;             cbc.gridy = 2;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(tfCustEmail, cbc);
    cbc.gridx = 2;             cbc.gridy = 2;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(new JLabel("  전 화 번 호  "), cbc);
    cbc.gridx = 3;             cbc.gridy = 2;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(tfCustTel, cbc);
    cbc.gridx = 0;             cbc.gridy = 3;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(new JLabel("   주   소   "), cbc);
    cbc.gridx = 1;             cbc.gridy = 3;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(tfCustAddr, cbc);
    cbc.gridx = 2;             cbc.gridy = 3;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(new JLabel("  계 좌 번 호  "), cbc);
    cbc.gridx = 3;             cbc.gridy = 3;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(tfCustAccount, cbc);
    cbc.gridx = 0;             cbc.gridy = 4;         cbc.gridwidth = 1;         cbc.gridheight = 1;
    pModify.add(new JLabel("  가 입 날 짜  "), cbc);
    cbc.gridx = 1;             cbc.gridy = 4;         cbc.gridwidth = 3;         cbc.gridheight = 1;
    pModify.add(tfCustDate, cbc);
    
    JPanel pSearch = new JPanel();
    pSearch.setLayout( new GridLayout(2, 1) );
    JPanel pSearchName = new JPanel();
    pSearchName.add(new JLabel("이   름"));
    pSearchName.add(tfCustNameSearch);
    pSearchName.add(bCustNameSearch);
    pSearch.add(pSearchName);
    
    setLayout( new BorderLayout() );
    //add("Center",pRegist );
    add("South",pSearch );
    
    bCustDelete.addActionListener( this );
    bCustModify.addActionListener( this );
    bCustNameSearch.addActionListener( this );
}

public void actionPerformed( ActionEvent ev ){
	Object o = ev.getSource();
	
	if(o == bCustNameSearch) {
		
		try {
            String name = tfCustNameSearch.getText();
            CustomerVO vo = customer.searchName(name);
            
		    tfCustName.setText(vo.getCustName());
		    tfCustId.setText(vo.getCustId());
		    tfCustPwd.setText(vo.getCustPwd());
		    tfCustGender.setText(vo.getCustGender());
		    tfCustEmail.setText(vo.getCustEmail());
	  	    tfCustTel.setText(vo.getCustTel());
	  	    tfCustAddr.setText(vo.getCustAddr());
	  	    tfCustAccount.setText(vo.getCustAccount());
	  	    tfCustDate.setText(vo.getCustDate());
		
		}catch (Exception e) {
             // TODO: handle exception
             JOptionPane.showMessageDialog
             (null, "이름검색 실패 : " + e.getMessage());
		}
	}
}
public void layoutClean() {
	   tfCustName.setText("");
	   tfCustId.setText("");
	   tfCustPwd.setText("");
	   tfCustGender.setText("");
	   tfCustEmail.setText("");
 	   tfCustTel.setText("");
 	   tfCustAddr.setText("");
 	   tfCustAccount.setText("");
 	   tfCustDate.setText(""); 
	  
	}
};                
  	 // CustomerVO vo = new CustomerVO
  		//	  (name, id, pwd, gender, email, tel, addr, account, date);