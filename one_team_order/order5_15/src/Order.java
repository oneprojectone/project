import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Order extends JFrame implements ActionListener {

	private static final String Dialogtitle = null;
	private static final Component OriginalFrame = null;

	JPanel panWest;
	JPanel panSouth;
	JPanel p1, p2, p3, p4, p5, p6, p7;
	JTextField txt1, txt2, txt3, txt4, txt5, txt6;
	JButton btn1, btn2, btn3, btn4, btn5;
	JComboBox cb1,cb2;
	
	PreparedStatement pstmtSearch, pstmtSearchScroll;
	
	HistoryDao dao = new HistoryDao();
	HistoryDto dto = new HistoryDto();
	
	String[] cbs={" 사이즈 선택 ", " L ", " M "};
	String[] cbo= {" 옵션 선택 "," h ", " i "};
	String data[][] = new String[0][7];
	String title[] = { "NO", "아이디", "상품명", "사이즈", "옵션", "가격", "주문날짜" };

	DefaultTableModel model = new DefaultTableModel(data, title);

	JTable table = new JTable(model);
	JScrollPane panScorllCenter = new JScrollPane(table);

	public Order() {
		super("구매 내역");

		panWest = new JPanel(new GridLayout(10, 0));
		p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(new JLabel("구매일"));
		p1.add(txt1 = new JTextField(12));
		panWest.add(p1);
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(new JLabel("MENU"));
		p2.add(txt2 = new JTextField(12));
		panWest.add(p2);
		p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("SIZE"));
		p3.add(txt3 = new JTextField(12));
		panWest.add(p3);
		p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4.add(new JLabel("OPTION"));
		p4.add(txt4 = new JTextField(12));
		panWest.add(p4);
		p5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p5.add(new JLabel("PRICE"));
		p5.add(txt5 = new JTextField(12));
		panWest.add(p5);
		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p6.add(cb1 = new JComboBox(cbs));
		panWest.add(p6);
		p7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
		dao.allview(model);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = 0;
				int Num = (Integer) table.getValueAt(row, col);
				System.out.println(Num);
				try {
					dto = dao.selectByPk(Num);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				txt1.setText(String.valueOf(dto.getHdate()));
				txt2.setText(dto.getHmenu());
				txt3.setText(dto.getHsize());
				txt4.setText(dto.getHoption());
				txt5.setText(dto.getHprice());

			}
		});

		this.add("Center", panScorllCenter);
		setBounds(250, 250, 800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	
	
	public static void main(String[] args) {
		new Order();
	}

	@Override
	   public void actionPerformed(ActionEvent e) {
	      Object obj = e.getSource();
	      if(obj == btn1) {
	         dao.allview(model);
	    	  
	      } else if(obj == btn4) {
	         int row = table.getSelectedRow();
	         int col = table.getModel().getColumnCount();
	         int data = (int) table.getModel().getValueAt(row, 0);
	         dao.deleteMyInfo(data);
	         dao.allview(model);
	         JOptionPane.showMessageDialog(null, "삭제완료");
	      
	      } else if (obj == btn5) {
		         JOptionPane.showMessageDialog(null,"결제완료");
	      }
	}
}