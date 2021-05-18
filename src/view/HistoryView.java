package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import dao.HistoryDao;
import dto.HistoryVO;

public class HistoryView extends JPanel implements ActionListener, ItemListener{
	public JPanel panWest;
	public JPanel panSouth;
	public JPanel panCenter;
	public JPanel p1, p2, p3, p4, p5, p6, p7, p8;
	public JLabel rowcount, space;
	public JTextField txtNo, txtId, txtName, txtSize, txtIh, txtPrice, txtDate;
	public JButton btnTotal, btnAdd, btnDel, btnSearch, btnCancel;
	public SelectFrame sfr = new SelectFrame();
	
	public JRadioButton rbtnSize_m, rbtnSize_l, rbtnOption_i, rbtnOption_h;
	
	HistoryDao dao = new HistoryDao();
	HistoryVO dto = new HistoryVO();
	int temp[] = new int[2];
	
	String menuName[] = {"선택해주세요", "아메리카노", "카페라떼", "카페모카", "바닐라라떼", "초코라떼", "돌체라떼", "자바치프라푸치노",
			"흑당라떼", "민트초코칩프라푸치노", "콜드브루", "콜드브루라떼", "카푸치노", "카라멜마끼아또", "에스프레소",
			"자몽허니블랙티", "제주한라산녹차라떼", "돌체콜드브루", "뉴욕치즈케이크", "크로크무슈", "허니브레드", "초코머핀",
			"베이글", "티라미수케이크", "민트초코케이크", "베리베리스트로베리", "자몽에이드", "레몬에이드", "사과에이드", 
			"딸기요거트스무디", "블루베리요거트스무디", "키위요거트스무디" };
	
	public JComboBox<String> comboBox;
	
	String data[][] = new String[0][7]; // 0은 addRow할 때 시작점, 7은 column 개수
	String title[] = { "NO", "아이디", "상품명", "사이즈", "옵션", "가격", "주문날짜" };
	
	
	DefaultTableModel model = new DefaultTableModel(data, title) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable table = new JTable(model);
	JScrollPane panScorllCenter = new JScrollPane(table);
	

	
	
	public HistoryView(){
		
		setLayout(new BorderLayout());
		panWest = new JPanel(new GridLayout(7,0));
		p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(new JLabel("번 호"));
		p1.add(txtNo = new JTextField(14));
		panWest.add(p1);
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(new JLabel("아 이 디"));
		p2.add(txtId = new JTextField(14));
		panWest.add(p2);
		
		p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("상 품 명"));
		comboBox = new JComboBox<String>(menuName);
		p3.add(comboBox);
		panWest.add(p3);
		
		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4.add(new JLabel("사 이 즈"));
		ButtonGroup btnGroup = new ButtonGroup();		
		rbtnSize_m = new JRadioButton("Medium", false);
		rbtnSize_l = new JRadioButton("Large",false);
		btnGroup.add(rbtnSize_m); btnGroup.add(rbtnSize_l);
		p4.add(rbtnSize_m); p4.add(rbtnSize_l);
		panWest.add(p4);
	
		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p5.add(new JLabel("옵 션"));
		ButtonGroup btnGroupOp = new ButtonGroup();	
		rbtnOption_h = new JRadioButton("Hot",false);
		rbtnOption_i = new JRadioButton("Ice", false);
		btnGroupOp.add(rbtnOption_h); btnGroupOp.add(rbtnOption_i);
		p5.add(rbtnOption_h); p5.add(rbtnOption_i);
		panWest.add(p5);
		
		
		p6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p6.add(new JLabel("가 격"));
		p6.add(txtPrice = new JTextField(14));
		panWest.add(p6);
		p7 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p7.add(new JLabel("주 문 날 짜"));
		p7.add(txtDate = new JTextField(14));
		panWest.add(p7);
		add(panWest, "West");
		
		add(panScorllCenter, "Center");
		
		rowcount = new JLabel();
		space = new JLabel("                                                                                                    ");
		
		panSouth = new JPanel();
		panSouth.add(btnTotal = new JButton("전체보기"));
		panSouth.add(btnSearch = new JButton("검  색"));
		panSouth.add(btnAdd = new JButton("수  정"));
		panSouth.add(btnDel = new JButton("삭  제"));
		panSouth.add(space);
		panSouth.add(rowcount);
		add(panSouth, "South");
		
		comboBox.addItemListener(this);
		btnTotal.addActionListener(this);
		btnSearch.addActionListener(this);
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		sfr.btnOk.addActionListener(this);
		sfr.btnCancel.addActionListener(this);

		
		
		 table.addMouseListener(new MouseAdapter() {
	    	  
		      public void mouseClicked(MouseEvent e) {
		         int row = table.getSelectedRow();
		         int col = 0;
		         int Num = Integer.parseInt(table.getValueAt(row, col).toString());

		         try {
		        	 dto = dao.selectByPk(Num);
		         }catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "검색 실패 : " + ex.getMessage());
		         }

		         txtNo.setText(dto.gethNo());  
		         txtId.setText(dto.gethID());
		         comboBox.setSelectedItem(dto.gethMenu());
		         comboBox.setEnabled(true);
		         
		         String jbtnvalues = dto.gethSize();
		         
		         if(jbtnvalues == null) {
		        	 rbtnSize_m.setEnabled(false);
		        	 rbtnSize_l.setEnabled(false);
		         } else if(jbtnvalues.equals("m")) {
		        	 rbtnSize_m.setEnabled(true);
		        	 rbtnSize_l.setEnabled(true);
		        	 rbtnSize_m.setSelected(true);
		         }
		         else if(jbtnvalues.equals("l")) {
		        	 rbtnSize_m.setEnabled(true);
		        	 rbtnSize_l.setEnabled(true);
		        	 rbtnSize_l.setSelected(true);
		         }
		         
		         String jbtnvaluesOp = dto.gethOption();
		         
		         if(jbtnvaluesOp == null) {
		        	 System.out.println("널값");
		        	 rbtnOption_h.setEnabled(false);
		        	 rbtnOption_i.setEnabled(false);
		         }
		         
		         else if(jbtnvaluesOp.equals("h")) {
		        	 rbtnOption_h.setEnabled(true);
		        	 rbtnOption_i.setEnabled(true);
		        	 rbtnOption_h.setSelected(true);
		         }
		         else if(jbtnvaluesOp.equals("i")) {
		        	 rbtnOption_h.setEnabled(true);
		        	 rbtnOption_i.setEnabled(true);
		        	 rbtnOption_i.setSelected(true);
		         }
		         
		         txtPrice.setText(dto.gethPrice());
		         txtDate.setText(String.valueOf(dto.gethDate()));
		         
		         
		         txtNo.setEditable(false);
		         txtId.setEditable(false);
		         txtPrice.setEditable(false);
		         txtDate.setEditable(false);
		         
		     }
		   } );
		      
		setBounds(100, 100, 700, 300);
		setVisible(true);
		
		dao.dbConnect();
		temp=dao.allview(model, this);
		rowcount.setText("총 개수 : " + temp[0] + "개," +"    총 가격 : "+ temp[1] + "원");
	}
	
	
	public void actionPerformed(ActionEvent e) {

		Object ob = e.getSource();

		if (ob == btnTotal) {
			
			temp=dao.allview(model,this);
			//model.
			rowcount.setText("총 개수 : " + temp[0] + "개," +"    총 가격 : "+ temp[1] + "원");
			
		}
		
		else if (ob == btnSearch) {
			
			dao.init(this);
			sfr.setVisible(true);
			sfr.txtId.setEditable(false);
			sfr.comboBox.setSelectedItem("선택해주세요");
			sfr.comboBox.setEditable(false);
        	sfr.txtDate.setEditable(false);
			dao.mouseview(sfr);
		}
		
		else if (ob == sfr.btnOk) {
			temp=dao.searchview(model, sfr, this);
			System.out.println(temp[0]+"성공!");
			System.out.println(temp[1]+"성공!");
			rowcount.setText("총 개수 : " + temp[0] + "개," +"    총 가격 : "+ temp[1] + "원");
			
			if(sfr.txtId.getText().length() == 0 && sfr.comboBox.getSelectedItem() == "선택해주세요" 
					&& sfr.txtDate.getText().length() == 0) {
				JOptionPane.showMessageDialog(sfr, "검색하고 싶은 데이터를 입력해주세요.");
				sfr.setVisible(true);
				sfr.txtId.setEditable(false);
	        	sfr.comboBox.setEditable(false);
	        	sfr.txtDate.setEditable(false);
				dao.mouseview(sfr);
			}
			else if(sfr.txtId.getText().length() == 0 ||sfr.comboBox.getSelectedItem() == "선택해주세요" 
					|| sfr.txtDate.getText().length() == 0) {			

				sfr.txtId.setText("");
				sfr.comboBox.setSelectedItem("선택해주세요");
				sfr.txtDate.setText("");
				sfr.setVisible(false);
				
			}
			
		}
		else if (ob == sfr.btnCancel) {
			sfr.txtId.setText("");
			sfr.comboBox.setSelectedItem("선택해주세요");;
			sfr.txtDate.setText("");
			sfr.setVisible(false);
			
		}

		
		else if (ob == btnAdd) {
			dao.updateview(model, this);
			temp = dao.allview(model, this);
			rowcount.setText("총 개수 : " + temp[0] + "개," +"    총 가격 : "+ temp[1] + "원");
			
		}
		
		else if (ob == btnDel) {
			dao.deleteview(model, this);
			temp = dao.allview(model, this);
			rowcount.setText("총 개수 : " + temp[0] + "개," +"    총 가격 : "+ temp[1] + "원");
		}
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(comboBox.getSelectedItem() == "아메리카노" || comboBox.getSelectedItem() == "카페라떼"
				|| comboBox.getSelectedItem() == "카페모카" || comboBox.getSelectedItem() == "바닐라라떼"
				|| comboBox.getSelectedItem() == "초코라떼" || comboBox.getSelectedItem() == "돌체라떼"
				|| comboBox.getSelectedItem() == "흑당라떼" || comboBox.getSelectedItem() == "카푸치노"
				|| comboBox.getSelectedItem() == "카라멜마끼아또" || comboBox.getSelectedItem() == "에스프레소"
				|| comboBox.getSelectedItem() == "자몽허니블랙티" || comboBox.getSelectedItem() == "제주한라산녹차라떼" ) {
			  rbtnSize_m.setEnabled(true);
	    	  rbtnSize_l.setEnabled(true);  		
	    	  rbtnOption_h.setEnabled(true);
	    	  rbtnOption_i.setEnabled(true);
		}
		else if (comboBox.getSelectedItem() == "자바칩프라푸치노" || comboBox.getSelectedItem() == "민트초코칩프라푸치노"
				|| comboBox.getSelectedItem() == "콜드브루" || comboBox.getSelectedItem() == "콜드브루라떼"
				|| comboBox.getSelectedItem() == "자몽에이드" || comboBox.getSelectedItem() == "레몬에이드"
				|| comboBox.getSelectedItem() == "사과에이드" || comboBox.getSelectedItem() == "딸기요거트스무디"
				|| comboBox.getSelectedItem() == "블루베리요거트스무디" || comboBox.getSelectedItem() == "키위요거트스무디"
				|| comboBox.getSelectedItem() == "돌체콜드브루") {
			 rbtnSize_m.setEnabled(true);
	    	  rbtnSize_l.setEnabled(true);  
	    	  rbtnOption_i.setSelected(true);
	    	  rbtnOption_h.setEnabled(false);
	    	  rbtnOption_i.setEnabled(false);
		}
		else if (comboBox.getSelectedItem() == "뉴욕치즈케이크" || comboBox.getSelectedItem() == "크로크무슈"
				  || comboBox.getSelectedItem() == "허니브레드" || comboBox.getSelectedItem() == "초코머핀"
				  || comboBox.getSelectedItem() == "베이글" || comboBox.getSelectedItem() == "티라미수케이크"
				  || comboBox.getSelectedItem() == "민트초코케이크" || comboBox.getSelectedItem() == "베리베리스트로베리") {
			 rbtnSize_m.setEnabled(false);
	    	  rbtnSize_l.setEnabled(false);  		
	    	  rbtnOption_h.setEnabled(false);
	    	  rbtnOption_i.setEnabled(false);
		}

	}
}
	
	
	
	

