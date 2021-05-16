package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import customer.CustomerDAO;
import customer.CustomerVO;

public class CustomerView extends JPanel implements ActionListener, KeyListener {
	 private JPanel panel;
	     
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
	JFrame frame;
	CustomerDAO dao=new CustomerDAO();
	
	Statement stmt;
	Connection con;
	ResultSet rs;
	
	public CustomerView(){
		newObject();
		addLayout();
		setStyle();
		eventProc();
		 panel = new JPanel();
		
		
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
	    bCustListAll.setPreferredSize(new Dimension(20, 30));
	    bCustModify.setPreferredSize(new Dimension(20, 30));
	    bCustDelete.setPreferredSize(new Dimension(20, 30));
	}
	
	
	
    void addLayout(){
	
	JPanel pWest = new JPanel();
	pWest.setBorder(new TitledBorder("회원정보"));
	
	JPanel pWestNorth = new JPanel();
	
	JPanel pWestNorthUp	= new JPanel();
	
	//tfCustName.setFont(tfCustName.getFont().deriveFont(10.0f));
	
	pWestNorthUp.setLayout(new GridLayout(9,2));
	pWestNorthUp.add(new JLabel("                              이                          름"));
	pWestNorthUp.add(tfCustName);
	pWestNorthUp.add(new JLabel("                              I                              D"));
	pWestNorthUp.add(tfCustId);
	pWestNorthUp.add(new JLabel("                              비      밀      번      호"));
	pWestNorthUp.add(tfCustPwd);
	pWestNorthUp.add(new JLabel("                              성                          별"));
	pWestNorthUp.add(tfCustGender);
	pWestNorthUp.add(new JLabel("                              이           메           일"));
	pWestNorthUp.add(tfCustEmail);
	pWestNorthUp.add(new JLabel("                              전      화      번      호"));
	pWestNorthUp.add(tfCustTel);
	pWestNorthUp.add(new JLabel("                              주                          소"));
	pWestNorthUp.add(tfCustAddr);
	pWestNorthUp.add(new JLabel("                              계      좌      번      호"));
	pWestNorthUp.add(tfCustAccount);
	pWestNorthUp.add(new JLabel("                              가      입      날      짜"));
	pWestNorthUp.add(tfCustDate);
	

	JPanel	pWestNorthDown	= new JPanel();
	
	pWestNorth.setLayout( new BorderLayout() );
	pWestNorth.add("Center", pWestNorthUp );
	pWestNorth.add("South",  pWestNorthDown );
	
	JPanel	pWestSouth	= new JPanel();
	JPanel	pWestSouthUp	= new JPanel();
	JPanel	pWestSouthDown	= new JPanel();
	pWestSouthDown.setLayout( new GridLayout( 1, 3,120,120 ) );
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
	Color b = new Color(255,223,176);  
	pEast.setBackground(b);
	pWest.setBackground(b);
	pEastNorth.setBackground(b);
	pWestNorth.setBackground(b);
	pWestNorthUp.setBackground(b);
	pWestNorthDown.setBackground(b);
	pWestSouth.setBackground(b);
	pWestSouthDown.setBackground(b);
	pWestSouthUp.setBackground(b);
	CustomerDAO dao = new CustomerDAO();
	dao.ListAll(model);
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
				
				vo = dao.selectByPk(custName);
				
				
				tfCustName.setText(vo.getPname());
			    tfCustId.setText(vo.getPid());
			    tfCustPwd.setText(vo.getPpwd());
			    tfCustGender.setText(vo.getPgender());
			    tfCustEmail.setText(vo.getPemail());
		  	    tfCustTel.setText(vo.getPtel());
		  	    tfCustAddr.setText(vo.getPaddr());
		  	    tfCustAccount.setText(vo.getPaccount());
		  	    tfCustDate.setText(vo.getPdate());
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
		
		vo.setPname(name);
		vo.setPid(id);
		vo.setPpwd(pwd);
		vo.setPgender(gender);
		vo.setPemail(email);
		vo.setPtel(tel);
		vo.setPaddr(addr);
		vo.setPaccount(account);
		vo.setPdate(date);

	
			dao.modifyCustomer(vo);
			System.out.println("수정 성공");
		
		clearLayout();
		
	}else if ( o == bCustDelete ) {
		
		String name = tfCustName.getText();
        try {
        int result = JOptionPane.showConfirmDialog(null, "정말로 삭제하실거에요?", "주의", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.CLOSED_OPTION) {
        	dao.ListAll(model);
        }
        else if(result == JOptionPane.YES_OPTION) {
        	dao.deleteCustomer(name);
        }
        else {
        	dao.ListAll(model);
        }
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
            //key = customer.searchName(name);
            System.out.println(e.getKeyChar());
            
            String kkk = e.getKeyChar() + "";
            name =  name + kkk;

            System.out.println(name);
           dao.searchName(model, name);
           // String sql = "SELECT * FROM customer where pname like '%"+name+"%'";
           //stmt = con.createStatement();
           // rs = stmt.executeQuery(sql);
	  	    
		}catch (Exception ex) { 
			//JOptionPane.showMessageDialog(null, "검색 실패 : " + ex.getMessage());	
			ex.printStackTrace();}
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