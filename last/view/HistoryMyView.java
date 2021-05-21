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

public class HistoryMyView extends JPanel implements ActionListener, ItemListener {

	private static final String Dialogtitle = null;
	private static final Component OriginalFrame = null;

	public JPanel panWest;
	public JPanel panSouth;
	public JPanel p0, p1, p2, p3, p4, p5, p6, p7;
	public JTextField txt1, txt2, txt3, txt4, txt5, txt6, txtNo;
	public JButton btn1, btn2, btn3, btn4, btn5;
	public JComboBox<String> cb1, cb2, cb3;
	public JLabel lab1, lab2, lab3, lab4, lab5, lab6, lab7, lab8, lab9, labNo, msg1, msg2;

	PreparedStatement pstmtSearch, pstmtSearchScroll;

	public HistoryDao dao = new HistoryDao();
	public HistoryVO dto = new HistoryVO();
	CustomerDAO custdao = new CustomerDAO();
	// public CustomerDAO custdao = new CustomerDAO();

	public String[] cbm = { "선택해주세요", "아메리카노", "카페라떼", "카페모카", "바닐라라떼", "초코라떼", "돌체라떼", "자바칩프라푸치노", "흑당라떼",
			"민트초코칩프라푸치노", "콜드브루", "콜드브루라떼", "카푸치노", "카라멜마끼아또", "에스프레소", "자몽허니블랙티", "제주한라산녹차라떼", "돌체콜드브루", "뉴욕치즈케이크",
			"크로크무슈", "허니브레드", "초코머핀", "베이글", "티라미수케이크", "민트초코케이크", "베리베리스트로베리", "자몽에이드", "레몬에이드", "사과에이드", "딸기요거트스무디",
			"블루베리요거트스무디", "키위요거트스무디" };

	public String[] cbs = { "size", "Large", "Medium" };
	public String[] cbo = { "option", "Hot", "Ice" };

	// 49~53 테이블 변경 수정
	String data[][] = new String[0][7];
	String[] title = { "NO", "아이디", "상품명", "사이즈", "옵션", "가격", "주문날짜" };
	DefaultTableModel model = new DefaultTableModel(data, title) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};

	public JTable table = new JTable(model);
	public JScrollPane panScorllCenter = new JScrollPane(table);
	public int row, col;

	CustomerVO user = new CustomerVO();
	CustomerMyView customerMyView;

	// 76~88 크기 조절
	public HistoryMyView(String id, CustomerMyView custMy) {
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
		table.setFont(new Font("Serif",Font.PLAIN, 20));
		   table.setRowHeight(table.getRowHeight() + 10);
		customerMyView = custMy;
		user.setPid(id);
		setLayout(new BorderLayout());
		panWest = new JPanel(new GridLayout(9, 8));
//		p0 = new JPanel(new GridLayout(2, 3));
//		labNo = new JLabel("주문번호");
//		labNo.setFont(font);
//		p0.add(labNo);
//		p0.add(txtNo = new JTextField(12));
//		panWest.add(p0);
		p1 = new JPanel(new GridLayout(2, 3));
		lab1 = new JLabel("구매일");
		lab1.setFont(font);
		p1.add(lab1);
		p1.add(txt1 = new JTextField(12));
		panWest.add(p1);
		p2 = new JPanel(new GridLayout(2, 3));
		lab2 = new JLabel("MENU");
		lab2.setFont(font);
		p2.add(lab2);
		cb3 = new JComboBox<String>(cbm);
		p2.add(cb3);
		panWest.add(p2);
		p5 = new JPanel(new GridLayout(2, 3));
		lab3 = new JLabel("PRICE");
		lab3.setFont(font);
		p5.add(lab3);
		p5.add(txt5 = new JTextField(12));
		panWest.add(p5);
		p6 = new JPanel(new GridLayout(2, 3));
		lab4 = new JLabel("SIZE");
		lab4.setFont(font);
		p6.add(lab4);
		p6.add(cb1 = new JComboBox<String>(cbs));
		panWest.add(p6);
		p7 = new JPanel(new GridLayout(2, 3));
		lab5 = new JLabel("OPTION");
		lab5.setFont(font);
		p7.add(lab5);
		p7.add(cb2 = new JComboBox<String>(cbo));
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
		dao.CusAllview(model, user.getPid());
		Color b = new Color(248, 234, 221);
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
		cb3.addItemListener(this);

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
					System.out.println("mouseClicked: " + ex.getMessage());
				}
				txt1.setText(String.valueOf(dto.gethDate()));
				cb3.setSelectedItem(dto.gethMenu());
				cb1.setSelectedItem(dto.gethSize());
				cb2.setSelectedItem(dto.gethOption());
				txt5.setText(dto.gethPrice());
//				txtNo.setText(dto.gethNo());

			}
		});

		this.add("Center", panScorllCenter);
		setBounds(250, 250, 800, 500);
		setVisible(true);
		dao.CusAllview(model, user.getPid());
		txt5.setEditable(false);
		txt1.setEditable(false);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
		Object obj = e.getSource();
		if (obj == btn1) {
			dao.CusAllview(model, user.getPid());
			txt1.setText("");
			cb3.setSelectedItem("선택해주세요");
			txt5.setText("");
			cb1.setSelectedItem("size");
			cb1.setEnabled(false);
			cb2.setSelectedItem("option");
			cb2.setEnabled(false);
			
		} else if (obj == btn2) {
			if (!(cb3.getSelectedItem().equals("선택해주세요"))) {
				dao.CusSearchview(model, cb3.getSelectedItem().toString(), user.getPid());
			} else
				lab6 = new JLabel("메뉴를 선택 해주세요");

			// lab6.setFont(font);

			JOptionPane.showMessageDialog(this, lab6);
		} else if (obj == btn3) {
			if (txt1.getText().length() > 0) {
				
				System.out.println("버튼실행");
				System.out.println(cb3.getSelectedItem());
				
				
				dto.sethMenu((String)(cb3.getSelectedItem()));
				
				
//				if( cb3.getSelectedItem().toString() == "뉴욕치즈케이크" || cb3.getSelectedItem().toString() == "크로크무슈"
//					  || cb3.getSelectedItem().toString() == "허니브레드" || cb3.getSelectedItem().toString() == "초코머핀"
//					  || cb3.getSelectedItem().toString() == "베이글" || cb3.getSelectedItem().toString() == "티라미수케이크"
//					  || cb3.getSelectedItem().toString() == "민트초코케이크" || cb3.getSelectedItem().toString() == "베리베리스트로베리") {
//						cb1.setEnabled(false);
//						cb2.setEnabled(false);
//					}
				if (cb3.getSelectedItem().toString() == "자바칩프라푸치노" || cb3.getSelectedItem().toString() == "민트초코칩프라푸치노"
						|| cb3.getSelectedItem().toString() == "콜드브루" || cb3.getSelectedItem().toString() == "콜드브루라떼"
						|| cb3.getSelectedItem().toString() == "자몽에이드" || cb3.getSelectedItem().toString() == "레몬에이드"
						|| cb3.getSelectedItem().toString() == "사과에이드" || cb3.getSelectedItem().toString() == "딸기요거트스무디"
						|| cb3.getSelectedItem().toString() == "블루베리요거트스무디" || cb3.getSelectedItem().toString() == "키위요거트스무디"
						|| cb3.getSelectedItem().toString() == "돌체콜드브루") {
					if(cb1.getSelectedItem() == null) {
//						System.out.println("null 됨");
						msg1 = new JLabel("사이즈를 선택해주세요.");
						msg1.setFont(font);
						JOptionPane.showMessageDialog(this, msg1);
						return;
						}
//						cb1.setEnabled(true);
//						cb2.setSelectedItem("Ice");
//						cb2.setEnabled(false);
				} else if(cb3.getSelectedItem() == "아메리카노" || cb3.getSelectedItem() == "카페라떼"
						|| cb3.getSelectedItem() == "카페모카" || cb3.getSelectedItem() == "바닐라라떼"
						|| cb3.getSelectedItem() == "초코라떼" || cb3.getSelectedItem() == "돌체라떼"
						|| cb3.getSelectedItem() == "흑당라떼" || cb3.getSelectedItem() == "카푸치노"
						|| cb3.getSelectedItem() == "카라멜마끼아또" || cb3.getSelectedItem() == "에스프레소"
						|| cb3.getSelectedItem() == "자몽허니블랙티" || cb3.getSelectedItem() == "제주한라산녹차라떼" ) {
					if(cb1.getSelectedItem() == null || cb2.getSelectedItem() == null) {
						msg2 = new JLabel("사이즈와 옵션을 선택해주세요.");
						msg2.setFont(font);
						JOptionPane.showMessageDialog(this, msg2);
						return;
						}
				}
//				else {
//					cb1.setEnabled(true);
//					cb2.setEnabled(true);
//				}
//				
				dto.sethSize((String)(cb1.getSelectedItem()));
				dto.sethOption((String)(cb2.getSelectedItem()));
//				dto.sethNo(txtNo.getText());
				
				System.out.println("dto세팅");
				dto = dao.modifyhistory(dto);
				lab7 = new JLabel("변경되었습니다");
				lab7.setFont(font);
				JOptionPane.showMessageDialog(this, lab7);
				custdao.countHistory(customerMyView);

		
				dao.CusAllview(model, user.getPid());
				txt1.setText("");
				txt5.setText("");
				cb3.setSelectedIndex(0);
				cb1.setSelectedIndex(0);
				cb2.setSelectedIndex(0);
			} else {
				JLabel lab10 = new JLabel("주문 변경할 상품을 선택해 주세요!");
				lab10.setFont(font);
				JOptionPane.showMessageDialog(this, lab10);
			}
		} else if (obj == btn5) {
			lab8 = new JLabel("결제가 완료되었습니다.");
			lab8.setFont(font);
			JOptionPane.showMessageDialog(this, lab8);
		} else if (obj == btn4) {
			if (table.getSelectedRow() >= 0) {
				int row = table.getSelectedRow();
				int col = table.getModel().getColumnCount();
				int data = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
				dao.deleteMyInfo(data);
				lab9 = new JLabel("삭제가 완료되었습니다.");
				lab9.setFont(font);
				JOptionPane.showMessageDialog(this, lab9);
				custdao.countHistory(customerMyView);
				custdao.checkStamp(customerMyView);
				dao.CusAllview(model, user.getPid());
			}else {
				JLabel lab10 = new JLabel("삭제할 상품을 선택해 주세요!");
				lab10.setFont(font);
				JOptionPane.showMessageDialog(this, lab10);
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(cb3.getSelectedItem() == "아메리카노" || cb3.getSelectedItem() == "카페라떼"
				|| cb3.getSelectedItem() == "카페모카" || cb3.getSelectedItem() == "바닐라라떼"
				|| cb3.getSelectedItem() == "초코라떼" || cb3.getSelectedItem() == "돌체라떼"
				|| cb3.getSelectedItem() == "흑당라떼" || cb3.getSelectedItem() == "카푸치노"
				|| cb3.getSelectedItem() == "카라멜마끼아또" || cb3.getSelectedItem() == "에스프레소"
				|| cb3.getSelectedItem() == "자몽허니블랙티" || cb3.getSelectedItem() == "제주한라산녹차라떼" ) {
			cb1.setEnabled(true);
			cb2.setEnabled(true);
		}
		else if (cb3.getSelectedItem().toString() == "자바칩프라푸치노" || cb3.getSelectedItem().toString() == "민트초코칩프라푸치노"
				|| cb3.getSelectedItem().toString() == "콜드브루" || cb3.getSelectedItem().toString() == "콜드브루라떼"
				|| cb3.getSelectedItem().toString() == "자몽에이드" || cb3.getSelectedItem().toString() == "레몬에이드"
				|| cb3.getSelectedItem().toString() == "사과에이드" || cb3.getSelectedItem().toString() == "딸기요거트스무디"
				|| cb3.getSelectedItem().toString() == "블루베리요거트스무디" || cb3.getSelectedItem().toString() == "키위요거트스무디"
				|| cb3.getSelectedItem().toString() == "돌체콜드브루") { 
					cb1.setEnabled(true);
//					cb2.setSelectedItem("Ice");
					cb2.setEnabled(false);
		}
		else if ( cb3.getSelectedItem().toString() == "뉴욕치즈케이크" || cb3.getSelectedItem().toString() == "크로크무슈"
				  || cb3.getSelectedItem().toString() == "허니브레드" || cb3.getSelectedItem().toString() == "초코머핀"
				  || cb3.getSelectedItem().toString() == "베이글" || cb3.getSelectedItem().toString() == "티라미수케이크"
				  || cb3.getSelectedItem().toString() == "민트초코케이크" || cb3.getSelectedItem().toString() == "베리베리스트로베리") {
//					cb1.setSelectedItem("size");
//					cb2.setSelectedItem("option");
					cb1.setEnabled(false);
					cb2.setEnabled(false);
					
				}


	}

}
