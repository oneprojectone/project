package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

//import menu.InsertPage;
import dao.MenuDAO;
import dto.MenuVO;
//import menu.UpPage;

//210518 12:54	테이블 더블클릭시 수정 안되게 변경 (45~47, 54~56)

public class MenuView extends JPanel implements ActionListener {
	JTextField txt;
	JPanel pan, South;
	JPanel p1, p2;
	JTable table, table1;
	JButton select, update, del, insert;
	JRadioButton c, d, a, all;
	boolean checkAll = false;
	//테이블목록 클릭 확인
	boolean check = false;
	MenuDAO dao = new MenuDAO();
	MenuVO dto = new MenuVO();

	static String data[][] = new String[0][3];
	static String[] title = { "상품 코드", "상품명", "가격" };
	static DefaultTableModel modelTable = new DefaultTableModel(data, title) {
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int col) {
			return false;
		}

	};
	static String data1[][] = new String[0][3];
	static String[] title1 = { "상품 코드", "상품명", "가격" };
	static DefaultTableModel modelTable1 = new DefaultTableModel(data1, title1) {
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int col) {
			return false;
		}

	};

	public MenuView() {
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
		setLayout(new BorderLayout());
		ButtonGroup Group = new ButtonGroup();
		all = new JRadioButton("전체보기");
		a = new JRadioButton("기타");
		c = new JRadioButton("커피");
		d = new JRadioButton("디저트");

		pan = new JPanel(new GridLayout(3, 0));

		p1 = new JPanel();

		p1.add(txt = new JTextField(12));
		p1.add(select = new JButton());
		select.setText("검색");
		TitledBorder tb = new TitledBorder("상품명");
		p1.setBorder(tb);
		pan.add(p1);

		p2 = new JPanel(new GridLayout(4, 0));
		
		Group.add(all);
		Group.add(c);
		Group.add(d);
		Group.add(a);
		p2.add(all);
		p2.add(c);
		p2.add(d);
		p2.add(a);
		p2.setBorder(new TitledBorder("종류"));
		pan.add(p2);
		
	
		
		// 추가한 메뉴가 나오는 테이블 그래픽 
		JPanel p0 = new JPanel(new BorderLayout());
		p0.setBorder(new TitledBorder("최근에 추가된 메뉴"));
		JScrollPane scroll2 = new JScrollPane(new JTable(modelTable1));
		scroll2.setPreferredSize(new Dimension(250, 200));
		p0.add(scroll2, "Center");

		pan.add(p0);

		add(pan, "West");
		South = new JPanel(new GridLayout(0, 7));
		South.add(new JPanel());
		South.add(update = new JButton("수정"));
		South.add(new JPanel());
		South.add(del = new JButton("삭제"));
		South.add(new JPanel());
		South.add(insert = new JButton("추가"));
		South.add(new JPanel());

		add(South, "South");
		select.addActionListener(this);
		update.addActionListener(this);
		del.addActionListener(this);
		insert.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		a.addActionListener(this);
		all.addActionListener(this);
		update.setFont(font);
		del.setFont(font);
		insert.setFont(font);
		select.setFont(font);
		a.setFont(font);
		c.setFont(font);
		d.setFont(font);
		all.setFont(font);
		add(new JScrollPane(table = new JTable(modelTable)), "Center");
		table.setFont(new Font("Serif",Font.PLAIN, 20));
		   table.setRowHeight(table.getRowHeight() + 10);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = table.getSelectedRow();
				check = true;
				dto.setcNo(table.getModel().getValueAt(row, 0).toString());
				dto.setcName(table.getModel().getValueAt(row, 1).toString());
				dto.setcPrice((Integer) table.getModel().getValueAt(row, 2));
				
			}
		});

		setVisible(true);
		
		 
		if(checkAll == false) {
			all.setSelected(true);
			checkAll = true;
		}
		dao.showMenuAll(modelTable);
		
		
		Color b = new Color(248,234,221); 
	      pan.setBackground(b);
	      South.setBackground(b);
	      p1.setBackground(b);
	      p2.setBackground(b);
	      p0.setBackground(b);
	      all.setBackground(b);
	      a.setBackground(b);
	      d.setBackground(b);
	      c.setBackground(b);
	      update.setBackground(b);
	      del.setBackground(b);
	      insert.setBackground(b);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == select) {
			String sur = txt.getText().trim();
			dao.searchName(modelTable, sur);
			check = false;
		} else if (e.getSource() == update) {
			if(check != true)
				JOptionPane.showMessageDialog(null, "테이블 선택 후 수정 버튼을 눌러주세요");
			else
				new UpPage(dto.getcNo(), dto.getcName(), dto.getcPrice());
		} else if (e.getSource() == del) {
			//삭제 시 클릭된 테이블 확인
			if (check == true) {
				int reslut = JOptionPane.showConfirmDialog(null, "삭제 하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);

				if (reslut == JOptionPane.CLOSED_OPTION) {
					JOptionPane.showMessageDialog(null, "다시 선택해주세요");
				} else if (reslut == JOptionPane.YES_OPTION) {
					dao.deleteMenu(dto.getcNo());
					dao.showMenuAll(modelTable);
					
					check = false;
				} else
					JOptionPane.showMessageDialog(null, "취소 하셨습니다");
			} else
				JOptionPane.showMessageDialog(null, "테이블 선택 후 삭제 버튼을 눌러주세요");

		} else if (e.getSource() == insert) {
			check = false;
			new InsertPage(modelTable);
			
		} else if (e.getSource() == c) {
			dao.selectType(modelTable, "C");
			check = false;
		} else if (e.getSource() == d) {
			dao.selectType(modelTable, "D");
			check = false;
		}else if (e.getSource() == all){ 
			dao.showMenuAll(modelTable);
			check = false;
		}else {
			dao.selectType(modelTable, "A");
			check = false;
		}
	}

}
