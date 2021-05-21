package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import dao.CustomerDAO;
import dto.CustomerVO;

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
   
   JLabel lab1, lab2, lab3, lab4, lab5, lab6, lab7, lab8, lab9,lab10 ;
   
   JButton      bCustDelete, bCustModify, bCustNameSearch, bCustListAll; 
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

   public void   newObject(){
      Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
      
      tfCustName = new JTextField(15); 
         
      tfCustId = new JTextField(15);
      tfCustPwd = new JTextField(15);       tfCustGender = new JTextField(15);
      tfCustEmail = new JTextField(15);       tfCustTel = new JTextField(15);
      tfCustAddr = new JTextField(15);       tfCustAccount = new JTextField(15);
      tfCustDate = new JTextField(15);       

      tfCustNameSearch = new JTextField(10);
      bCustListAll = new JButton("조회");
       bCustModify = new JButton("수정");
       bCustDelete   = new JButton("삭제");
       bCustListAll.setPreferredSize(new Dimension(20, 30));
       bCustModify.setPreferredSize(new Dimension(20, 30));
       bCustDelete.setPreferredSize(new Dimension(20, 30));
       bCustListAll.setFont(font);
      bCustModify.setFont(font);
      bCustDelete.setFont(font);
   }
   
   
   
    void addLayout(){
    Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
   
   JPanel pWest = new JPanel();
   pWest.setBorder(new TitledBorder("회원정보"));
   
   JPanel pWestNorth = new JPanel();
   
   JPanel pWestNorthUp   = new JPanel();
   
   pWestNorthUp.setLayout(new GridLayout(18,1));
   lab1 = new JLabel("이 름");
   pWestNorthUp.add(lab1);
   pWestNorthUp.add(tfCustName);
   lab2 = new JLabel("I D");
   pWestNorthUp.add(lab2);
   pWestNorthUp.add(tfCustId);
   lab3 = new JLabel("비밀번호");
   pWestNorthUp.add(lab3);
   pWestNorthUp.add(tfCustPwd);
   lab4 = new JLabel("성 별");
   pWestNorthUp.add(lab4);
   pWestNorthUp.add(tfCustGender);
   lab5 = new JLabel("이 메 일");
   pWestNorthUp.add(lab5);
   pWestNorthUp.add(tfCustEmail);
   lab6 = new JLabel("전화번호");
   pWestNorthUp.add(lab6);
   pWestNorthUp.add(tfCustTel);
   lab7 = new JLabel("주 소");
   pWestNorthUp.add(lab7);
   pWestNorthUp.add(tfCustAddr);
   lab8 = new JLabel("계좌번호");
   pWestNorthUp.add(lab8);
   pWestNorthUp.add(tfCustAccount);
   lab9 = new JLabel("가입날짜");
   pWestNorthUp.add(lab9);
   pWestNorthUp.add(tfCustDate);
   
   lab1.setFont(font);
   lab2.setFont(font);
   lab3.setFont(font);
   lab4.setFont(font);
   lab5.setFont(font);
   lab6.setFont(font);
   lab7.setFont(font);
   lab8.setFont(font);
   lab9.setFont(font);
   

   JPanel   pWestNorthDown   = new JPanel();
   
   pWestNorth.setLayout( new BorderLayout() );
   pWestNorth.add("Center", pWestNorthUp );
   pWestNorth.add("South",  pWestNorthDown );
   
   JPanel   pWestSouth   = new JPanel();
   JPanel   pWestSouthUp   = new JPanel();
   JPanel   pWestSouthDown   = new JPanel();
   pWestSouthDown.setLayout( new GridLayout( 1, 3) );
   pWestSouthDown.add( bCustListAll );
   pWestSouthDown.add( bCustModify );
   pWestSouthDown.add( bCustDelete );
   
   pWestSouth.setLayout( new GridLayout( 2, 1 ) );
   pWestSouth.add(pWestSouthUp);
   pWestSouth.add(pWestSouthDown);
   
   pWest.setLayout( new BorderLayout() );
   pWest.add("Center", pWestNorth );
   pWest.add("South",  pWestSouth );
   
   JPanel   pEast      = new JPanel();
   JPanel   pEastNorth   = new JPanel();
   pEastNorth.setBorder( new TitledBorder("회원목록"));
   tableCustomer.setFont(new Font("Serif",Font.PLAIN, 20));
   tableCustomer.setRowHeight(tableCustomer.getRowHeight() + 10);
   pEastNorth.setLayout( new BorderLayout() );
   pEastNorth.add("Center", new JScrollPane(tableCustomer) );
   
   pEast.setLayout( new BorderLayout() );
   pEast.add("Center",  pEastNorth);
   
   pWest.setPreferredSize(new Dimension(250, 0));
   setLayout( new BorderLayout() );
   add( pWest, "West" );
   add( pEast, "Center");
   Color b = new Color(248,234,221);  
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
   Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
   Object o = ev.getSource();
   
   model.setRowCount(0);

   if ( o == bCustListAll ) {
      try {
      
      dao.ListAll(model);
      clearLayout();
      }catch (Exception e)
      {e.printStackTrace(); }
      
      
      
      
   }else if ( o == bCustModify ) {
      try {
      String name = tfCustName.getText();
      String id = tfCustId.getText().trim();
      String pwd = tfCustPwd.getText();
      String gender = tfCustGender.getText();
      String email = tfCustEmail.getText();
      String tel = tfCustTel.getText();
      String addr = tfCustAddr.getText();
      String account = tfCustAccount.getText();
      String date = tfCustDate.getText();
      System.out.println(id.equals(""));
      if (id.equals("")) {
         JLabel lab15 = new JLabel("선택된 회원이 없습니다!");
         lab15.setFont(font);
         JOptionPane.showMessageDialog(this, lab15);
         return;
      } if (addr.length() >= 3) {
         JLabel lab16 = new JLabel( "주소는 2글자까지만 가능합니다!");
         lab16.setFont(font);
         JOptionPane.showMessageDialog(this, lab16);
         return;
      }
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
      JLabel lab11 = new JLabel("회원정보가 수정되었습니다!");
      lab11.setFont(font);
        JOptionPane.showMessageDialog(this, lab11);
         System.out.println("수정 성공");
         
      }catch (Exception e) {
         JLabel lab12 = new JLabel("수정 실패 : ");
            lab12.setFont(font);
         JOptionPane.showMessageDialog(null, lab12 + e.getMessage());
      }
      	String name = tfCustName.getText();
		clearLayout();
		tfCustName.setText(name);
		dao.searchName(model, name);
      
   }else if ( o == bCustDelete ) {
      
      String id = tfCustId.getText();
        try {
           if (id.equals("")) {
              JLabel lab14 = new JLabel("선택된 회원이 없습니다!");
              lab14.setFont(font);
              JOptionPane.showMessageDialog(this,lab14);
              return;
           }
           lab10 = new JLabel("정말로 삭제하시겠습니까?");
           lab10.setFont(font);
           int result = JOptionPane.showConfirmDialog(null, lab10, "확인", JOptionPane.YES_NO_OPTION);
           if(result == JOptionPane.CLOSED_OPTION) {
           dao.ListAll(model);
        }
        else if(result == JOptionPane.YES_OPTION) {
           dao.deleteCustomer(id);
            JLabel lab13 = new JLabel("회원이 삭제 되었습니다!");
           lab13.setFont(font);
             JOptionPane.showMessageDialog(this, lab13);
        }
        else {
           dao.ListAll(model);
        }
        }catch(Exception e) {
           JOptionPane.showMessageDialog(null, "삭제 실패 : " + e.getMessage());
         
        }
        clearLayout();
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
//   class MenuTab extends JPanel{
//         public MenuTab() {
//            setLayout(new BorderLayout());
//            SearchPanel westPanel = new SearchPanel();
//            ListPanel center = new ListPanel();
//            ButtonPanel southPanel = new ButtonPanel();
//            
//           
//            add(westPanel, BorderLayout.WEST);
//            add(center, BorderLayout.CENTER);
//            add(southPanel, BorderLayout.SOUTH);
//         }
//      }
//      