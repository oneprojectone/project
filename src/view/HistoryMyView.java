package view;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
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
	
	PreparedStatement pstmtSearch, pstmtSearchScroll;
	
	public HistoryDao dao = new HistoryDao();
	public HistoryVO dto = new HistoryVO();
	
	public String[] cbs={"  size   ", "l", "m"};
	public String[] cbo= {"   option   ","h", "i"};
	public String data[][] = new String[0][7];
	public String title[] = { "NO", "아이디", "상품명", "사이즈", "옵션", "가격", "주문날짜" };

	public DefaultTableModel model = new DefaultTableModel(data, title);

	public JTable table = new JTable(model);
	public JScrollPane panScorllCenter = new JScrollPane(table);
	public int row, col;
	
	CustomerVO user = new CustomerVO();
	
	public HistoryMyView(String id) {
		user.setPid(id);
		setLayout(new BorderLayout());
		panWest = new JPanel(new GridLayout(7, 0));
		p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(new JLabel("구매일"));
		p1.add(txt1 = new JTextField(12));
		panWest.add(p1);
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(new JLabel("MENU"));
		p2.add(txt2 = new JTextField(12));
		panWest.add(p2);
		p5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p5.add(new JLabel("PRICE"));
		p5.add(txt5 = new JTextField(12));
		panWest.add(p5);
		p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p6.add(new JLabel(" SIZE     "));
		p6.add(cb1 = new JComboBox(cbs));
		panWest.add(p6);
		p7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p7.add(new JLabel("OPTION"));
		p7.add(cb2 = new JComboBox(cbo));
		panWest.add(p7);
		add(panWest, "West");

		panSouth = new JPanel();
		panSouth.add(btn1 = new JButton("전체 검색"));
		panSouth.add(btn2 = new JButton("검      색"));
		panSouth.add(btn3 = new JButton("수      정"));
		panSouth.add(btn4 = new JButton("삭      제"));
		panSouth.add(btn5 = new JButton("결      제"));
		add(panSouth, "South");
		dao.CusAllview(model,user.getPid());
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		cb1.addItemListener(this);
		cb2.addItemListener(this);
		
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
	}
	
	

	@Override 
	   public void actionPerformed(ActionEvent e) {
	      Object obj = e.getSource();
	      if (obj == btn1) {
	         dao.CusAllview(model,user.getPid() );
	      } else if (obj == btn2) {
		         if (txt2.getText().trim().length() > 0) {
		            dao.CusSearchview(model, txt2.getText());
		         } else
		            JOptionPane.showMessageDialog(null, "메뉴를 입력해주세요");   
	      }  else if (obj == btn3) {
	    	  try {
	    		   // dto.setHno(Integer.valueOf(dto.getHno()));
	 				//dto.setHid(getString("hid"));
	    		    System.out.println("버튼실행");
	 				dto.sethMenu(txt2.getText().trim());
	 				dto.sethSize((String)(cb1.getSelectedItem()));
	 				dto.sethOption((String)(cb2.getSelectedItem()));
	 				//dto.setHprice(getString("hprice"));
	 				//dto.setHdate(getString("hdate"));
	 				System.out.println("dto세팅");
	    	 dto= dao.modifyhistory(dto);
	    	 JOptionPane.showMessageDialog(this, "변경 되었습니다");
	    	  }catch (Exception ex) {
	    		  ex.printStackTrace();
	    	  }
	      } else if (obj == btn5) {
	         JOptionPane.showMessageDialog(null, "결제완료");
	      } else if (obj == btn4) {
	         int row = table.getSelectedRow();
	         int col = table.getModel().getColumnCount();
	         int data = (int) table.getModel().getValueAt(row, 0);
	         dao.deleteMyInfo(data);
	         JOptionPane.showMessageDialog(null, "삭제완료");
	      }
	   }

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	}
