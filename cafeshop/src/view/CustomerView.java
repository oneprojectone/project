package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import customer.CustomerDAO;
import customer.rec.CustomerVO;

public class CustomerView extends JPanel implements ActionListener, KeyListener {
	String data[][] = new String [0][9];
	String title[] = { "이름", "ID", "비밀번호", "성별", "EMAIL", "전화번호", "주소", "계좌번호", "가입날짜"};
	DefaultTableModel model = new DefaultTableModel(data, title) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	JTable tableCustomer = new JTable(model);
	
	JTextField   tfCustName, tfCustId, tfCustPwd, tfCustGender, 
				tfCustEmail, tfCustTel, tfCustAddr, tfCustAccount, tfCustDate;

	JTextField  tfCustNameSearch;
	
	JButton		bCustDelete, bCustModify, bCustNameSearch, bCustListAll; 
	
	CustomerDAO customer;
	
	public CustomerView(){
		newObject();
		addLayout();
		setStyle();
		eventProc();
		
		
		   try {
			   customer = new CustomerDAO();
			   System.out.println("DB연결 성공");
		   } catch (Exception e) {
			   JOptionPane.showMessageDialog(null, "DB연결 실패" + e.getMessage());
		   }
	   }

	public void	newObject(){
		
		
		tfCustName = new JTextField(30); 
			
		tfCustId = new JTextField(30);
		tfCustPwd = new JTextField(30); 		tfCustGender = new JTextField(30);
		tfCustEmail = new JTextField(30); 		tfCustTel = new JTextField(30);
		tfCustAddr = new JTextField(30); 		tfCustAccount = new JTextField(30);
		tfCustDate = new JTextField(30); 		

		tfCustNameSearch = new JTextField(10);
		bCustListAll = new JButton("조회");
	    //bCustNameSearch = new JButton("검색");
	    bCustModify = new JButton("수정");
	    bCustDelete	= new JButton("삭제");
	}
	
	
	
    void addLayout(){
	
	JPanel pWest = new JPanel();
	pWest.setBorder(new TitledBorder("회원정보"));
	
	JPanel pWestNorth = new JPanel();
	
	JPanel pWestNorthUp	= new JPanel();
	
	pWestNorthUp.setLayout(new GridLayout(9,2));
	pWestNorthUp.add(new JLabel("이름"));
	pWestNorthUp.add(tfCustName);
	pWestNorthUp.add(new JLabel("ID"));
	pWestNorthUp.add(tfCustId);
	pWestNorthUp.add(new JLabel("비밀번호"));
	pWestNorthUp.add(tfCustPwd);
	pWestNorthUp.add(new JLabel("성별"));
	pWestNorthUp.add(tfCustGender);
	pWestNorthUp.add(new JLabel("이메일"));
	pWestNorthUp.add(tfCustEmail);
	pWestNorthUp.add(new JLabel("전화번호"));
	pWestNorthUp.add(tfCustTel);
	pWestNorthUp.add(new JLabel("주소"));
	pWestNorthUp.add(tfCustAddr);
	pWestNorthUp.add(new JLabel("계좌"));
	pWestNorthUp.add(tfCustAccount);
	pWestNorthUp.add(new JLabel("가입날짜"));
	pWestNorthUp.add(tfCustDate);
	

	JPanel	pWestNorthDown	= new JPanel();
	
	pWestNorth.setLayout( new BorderLayout() );
	pWestNorth.add("Center", pWestNorthUp );
	pWestNorth.add("South",  pWestNorthDown );
	
	JPanel	pWestSouth	= new JPanel();
	JPanel	pWestSouthUp	= new JPanel();
	JPanel	pWestSouthDown	= new JPanel();
	pWestSouthDown.setLayout( new GridLayout( 1, 3 ) );
	pWestSouthDown.add( bCustListAll );
	//pWestSouthDown.add( bCustNameSearch );
	pWestSouthDown.add( bCustModify );
	pWestSouthDown.add( bCustDelete );
	
	pWestSouth.setLayout( new GridLayout( 2, 1 ) );
	pWestSouth.add(pWestSouthUp);
	pWestSouth.add(pWestSouthDown);
	
	pWest.setLayout( new BorderLayout() );
	pWest.add("Center", pWestNorth );
	pWest.add("South",  pWestSouth );
	
	JPanel	pEast		= new JPanel();
	JPanel	pEastNorth	= new JPanel();
	pEastNorth.setBorder( new TitledBorder("회원목록"));
	
	pEastNorth.setLayout( new BorderLayout() );
	pEastNorth.add("Center", new JScrollPane(tableCustomer) );
	
	pEast.setLayout( new BorderLayout() );
	pEast.add("Center",  pEastNorth);
	
	setLayout( new GridLayout( 1, 2 ) );
	add( pWest );
	add( pEast );
    }
    
    void setStyle() {
	
    	tfCustId.setEditable( false );
    	tfCustGender.setEditable( false );
    	tfCustAccount.setEditable( false );
    	tfCustDate.setEditable( false );
	
    }
	
    void eventProc(){
    	
    	bCustListAll.addActionListener( this );
    	//bCustNameSearch.addActionListener( this );
        bCustModify.addActionListener( this );
        bCustDelete.addActionListener( this );
        tfCustName.addKeyListener(this);
        
        tableCustomer.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		int row = tableCustomer.getSelectedRow();
				int col = 1;
				String custName = (String)tableCustomer.getValueAt(row, col);
				CustomerVO vo = new CustomerVO();
				try {
					vo = customer.selectByPk(custName);
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "회원 검색 실패 : " + ex.getMessage());
				}
				tfCustName.setText(vo.getCustName());
			    tfCustId.setText(vo.getCustId());
			    tfCustPwd.setText(vo.getCustPwd());
			    tfCustGender.setText(vo.getCustGender());
			    tfCustEmail.setText(vo.getCustEmail());
		  	    tfCustTel.setText(vo.getCustTel());
		  	    tfCustAddr.setText(vo.getCustAddr());
		  	    tfCustAccount.setText(vo.getCustAccount());
		  	    tfCustDate.setText(vo.getCustDate());
        	}
        });
        
     }
    
public void actionPerformed( ActionEvent ev ){
	Object o = ev.getSource();
	
	model.setRowCount(0);
//	if(o == bCustNameSearch) {
//		
//		try {
//            String name = tfCustName.getText();
//            m = customer.searchName(name);
//            
//		    tfCustName.setText(vo.getCustName());
//		    tfCustId.setText(vo.getCustId());
//		    tfCustPwd.setText(vo.getCustPwd());
//		    tfCustGender.setText(vo.getCustGender());
//		    tfCustEmail.setText(vo.getCustEmail());
//	  	    tfCustTel.setText(vo.getCustTel());
//	  	    tfCustAddr.setText(vo.getCustAddr());
//	  	    tfCustAccount.setText(vo.getCustAccount());
//	  	    tfCustDate.setText(vo.getCustDate());
//		
//	  	    CustomerDAO dao = new CustomerDAO();
//	  	    dao.ListAll(model);
//	  	    
//	  	   // if (name == tfCustName.setText(vo.getCustName())) {
//	  	    	
//	  	   // }
//	  	    
//		}catch (Exception e) {
//             JOptionPane.showMessageDialog
//             (null, "회원 검색 실패 : " + e.getMessage());
//		}
//	}else 
	if ( o == bCustListAll ) {
		try {
		CustomerDAO dao = new CustomerDAO();
		dao.ListAll(model);
		clearLayout();
		}catch (Exception e)
		{e.printStackTrace(); }
		
		
		
		
	}else if ( o == bCustModify ) {
		
		String name = tfCustName.getText();
		String id = tfCustId.getText();
		String pwd = tfCustPwd.getText();
		String gender = tfCustGender.getText();
		String email = tfCustEmail.getText();
		String tel = tfCustTel.getText();
		String addr = tfCustAddr.getText();
		String account = tfCustAccount.getText();
		String date = tfCustDate.getText();
		
		CustomerVO vo = new CustomerVO();
		
		vo.setCustName(name);
		vo.setCustId(id);
		vo.setCustPwd(pwd);
		vo.setCustGender(gender);
		vo.setCustEmail(email);
		vo.setCustTel(tel);
		vo.setCustAddr(addr);
		vo.setCustAccount(account);
		vo.setCustDate(date);

		try {
			customer.modifyCustomer(vo);
			System.out.println("수정 성공");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "수정 실패 : " + e.getMessage());
		}
		clearLayout();
		
	}else if ( o == bCustDelete ) {
		
		String name = tfCustName.getText();
        try {
        customer.deleteCustomer(name);
        System.out.println("삭제 성공");
        }catch(Exception e) {
           JOptionPane.showMessageDialog(null, "삭제 실패 : " + e.getMessage());
			
        }
	}
}

@Override
public void keyTyped(KeyEvent e) {
	String data[][] = new String [0][9];
	String title[] = { "이름", "ID", "비밀번호", "성별", "EMAIL", "전화번호", "주소", "계좌번호", "가입날짜"};
	DefaultTableModel key = new DefaultTableModel(data, title) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	Object o = e.getSource();
	key.setRowCount(0);
	if (o == tfCustName) {
		try {
            String name = tfCustName.getText();
            key = customer.searchName(name);
            
		  
	  	    
		}catch (Exception ex) { ex.printStackTrace();}
		}	
	}
@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
}
public void clearLayout() {
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
}	
//	class MenuTab extends JPanel{
//		   public MenuTab() {
//		      setLayout(new BorderLayout());
//		      SearchPanel westPanel = new SearchPanel();
//		      ListPanel center = new ListPanel();
//		      ButtonPanel southPanel = new ButtonPanel();
//		      
//		     
//		      add(westPanel, BorderLayout.WEST);
//		      add(center, BorderLayout.CENTER);
//		      add(southPanel, BorderLayout.SOUTH);
//		   }
//		}
//		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		