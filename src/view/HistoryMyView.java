package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDAO;
import dao.HistoryDao;
import dto.CustomerVO;
import dto.HistoryVO;

public class HistoryMyView extends JPanel implements ActionListener,ItemListener {

	private static final String Dialogtitle = null;
	private static final Component OriginalFrame = null;

	public JPanel panWest;
	public JPanel panSouth;
	public JPanel p1, p2, p3, p4, p5, p6, p7;
	public JTextField txt1, txt2, txt3, txt4, txt5, txt6;
	public JButton btn1, btn2, btn3, btn4, btn5;
	public JComboBox cb1,cb2;
	public JLabel lab1, lab2, lab3, lab4, lab5,lab6,lab7, lab8, lab9;
	
	PreparedStatement pstmtSearch, pstmtSearchScroll;
	
	public HistoryDao dao = new HistoryDao();
	public HistoryVO dto = new HistoryVO();
	CustomerDAO custdao = new CustomerDAO();
	//public CustomerDAO custdao = new CustomerDAO();
	
	public String[] cbs={"  size   ", "l", "m"};
	public String[] cbo= {"   option   ","h", "i"};
	
	//49~53 테이블 변경 수정 
    String data[][] = new String[0][7];
	String [] title = { "NO", "아이디", "상품명", "사이즈", "옵션", "가격", "주문날짜" };
	DefaultTableModel model = new DefaultTableModel(data, title){
	public boolean isCellEditable(int row, int col) {
		return false;
	}
};

	public JTable table = new JTable(model);
	public JScrollPane panScorllCenter = new JScrollPane(table);
	public int row, col;
	
	CustomerVO user = new CustomerVO();
	CustomerMyView customerMyView;
	
	//76~88 크기 조절
	public HistoryMyView(String id,CustomerMyView custMy) {
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
		customerMyView=custMy;
		user.setPid(id);
		setLayout(new BorderLayout());
		panWest = new JPanel(new GridLayout(13, 8));
		p1 = new JPanel(new GridLayout(2,3));
		lab1=new JLabel("구매일");
		lab1.setFont(font);
		p1.add(lab1);
		p1.add(txt1 = new JTextField(12));
		panWest.add(p1);
		p2 = new JPanel(new GridLayout(2,3));
		lab2=new JLabel("MENU");
		lab2.setFont(font);
		p2.add(lab2);
		p2.add(txt2 = new JTextField(12));
		panWest.add(p2);
		p5 = new JPanel(new GridLayout(2,3));
		lab3=new JLabel("PRICE");
		lab3.setFont(font);
		p5.add(lab3);
		p5.add(txt5 = new JTextField(12));
		panWest.add(p5);
		p6 = new JPanel(new GridLayout(2,3));
		lab4=new JLabel("SIZE");
		lab4.setFont(font);
		p6.add(lab4);
		p6.add(cb1 = new JComboBox(cbs));
		panWest.add(p6);
		p7 = new JPanel(new GridLayout(2,3));
		lab5=new JLabel("OPTION");
		lab5.setFont(font);
		p7.add(lab5);
		p7.add(cb2 = new JComboBox(cbo));
		panWest.add(p7);
		add(panWest, "West");
		
		panSouth = new JPanel();
		panSouth.add(btn1 = new JButton("전체 검색"));
		panSouth.add(btn2 = new JButton("검      색"));
		panSouth.add(btn3 = new JButton("수      정"));
		panSouth.add(btn4 = new JButton("삭      제"));
		panSouth.add(btn5 = new JButton("결      제"));
		btn1.setFont(font);
		btn2.setFont(font);
		btn3.setFont(font);
		btn4.setFont(font);
		btn5.setFont(font);
		add(panSouth, "South");
		dao.CusAllview(model,user.getPid());
		Color b = new Color(248,234,221); 
		panWest.setBackground(b);
		panSouth.setBackground(b);
		p1.setBackground(b);
		p2.setBackground(b);
		
		
		p5.setBackground(b);
		p6.setBackground(b);
		p7.setBackground(b);
		
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		cb1.addItemListener(this);
		cb2.addItemListener(this);
		
		
		btn1.setFont(font);
		btn2.setFont(font);
		btn3.setFont(font);
		btn4.setFont(font);
		btn5.setFont(font);
		lab1.setFont(font);
		lab2.setFont(font);
		lab3.setFont(font);
		lab4.setFont(font);
		lab5.setFont(font);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				col = 0;
				int Num = Integer.parseInt(table.getValueAt(row, col).toString());

				System.out.println(Num);
				try {
					dto = dao.selectByPk(Num);
				} catch (Exception ex) {
					System.out.println("mouseClicked: "+ex.getMessage());
				}
				txt1.setText(String.valueOf(dto.gethDate()));
				txt2.setText(dto.gethMenu());
				cb1.setSelectedItem(dto.gethSize());
				cb2.setSelectedItem(dto.gethOption());
				txt5.setText(dto.gethPrice());


			}
		});
		
		this.add("Center", panScorllCenter);
		setBounds(250, 250, 800, 500);
		setVisible(true);
		dao.CusAllview(model,user.getPid() );
	}

	@Override 
	   public void actionPerformed(ActionEvent e) {
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
	      Object obj = e.getSource();
	      if (obj == btn1) {
	         dao.CusAllview(model,user.getPid() );
	      } else if (obj == btn2) {
		         if (txt2.getText().trim().length() > 0) {
		            dao.CusSearchview(model, txt2.getText(), user.getPid());
		         } else
		        	 lab6 = new JLabel("메뉴를 입력해주세요");
		         	lab6.setFont(font);
		   
		            JOptionPane.showMessageDialog(null, lab6);   
	      }  else if (obj == btn3) {
	    	  try {
	    		 
	    		    System.out.println("버튼실행");
	 				dto.sethMenu(txt2.getText().trim());
	 				dto.sethSize((String)(cb1.getSelectedItem()));
	 				dto.sethOption((String)(cb2.getSelectedItem()));
	 				
	 				System.out.println("dto세팅");
	    	 dto= dao.modifyhistory(dto);
	    	 lab7 = new JLabel("변경되었습니다");
	         	lab7.setFont(font);
	    	 JOptionPane.showMessageDialog(this, lab7);
	    	 custdao.countHistory(customerMyView);
	    	
	    	 //159 수정후 전체 조회
	    	 dao.CusAllview(model,user.getPid() );
	    	  }catch (Exception ex) {
	    		  JLabel lab10 = new JLabel("주문 변경할 상품을 선택해 주세요!");
	    		  lab10.setFont(font);
	    		  JOptionPane.showMessageDialog(this, lab10);
	    	  }
	      } else if (obj == btn5) {
	    	  lab8 = new JLabel("결제완료");
	         	lab8.setFont(font);
	         JOptionPane.showMessageDialog(null, lab8);
	      } else if (obj == btn4) {
	         int row = table.getSelectedRow();
	         int col = table.getModel().getColumnCount();
	        
	         //170 integer 수정
	         int data = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	         dao.deleteMyInfo(data);
	         lab9 = new JLabel("삭제완료");
	         	lab9.setFont(font);
	         JOptionPane.showMessageDialog(null, lab9);
	         custdao.countHistory(customerMyView);
	         custdao.checkStamp(customerMyView);
	         
	         // 175 삭제후 전체조회
	         dao.CusAllview(model,user.getPid() );
	         //custdao.countHistory(c);
	         
	      }
	   }

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	}
