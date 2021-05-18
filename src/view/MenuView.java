package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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

public class MenuView extends JPanel implements ActionListener {
	JTextField txt;
	JPanel pan, South;
	JPanel p1, p2;
	JTable table;
	JButton select, update, del, insert;
	JRadioButton c, d, a;
	MenuDAO dao = new MenuDAO();
	MenuVO dto = new MenuVO();

	String data[][] = new String[0][3];
	String[] title = { "상품 코드", "상품명", "가격" };
	DefaultTableModel modelTable = new DefaultTableModel(data, title) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	};

	public MenuView() {
		setLayout(new BorderLayout());
		ButtonGroup Group = new ButtonGroup();
		a = new JRadioButton("기타");
		c = new JRadioButton("커피");
		d = new JRadioButton("디저트");

		pan = new JPanel(new GridLayout(3, 0));

		JPanel p0 = new JPanel();
		pan.add(p0);

		p1 = new JPanel();

		p1.add(txt = new JTextField(12));
		p1.add(select = new JButton());
		select.setText("검색");
		p1.setBorder(new TitledBorder("상품명"));
		pan.add(p1);

		p2 = new JPanel(new GridLayout(3, 0));

		Group.add(c);
		Group.add(d);
		Group.add(a);
		p2.add(c);
		p2.add(d);
		p2.add(a);
		p2.setBorder(new TitledBorder("종류"));
		pan.add(p2);

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

		add(new JScrollPane(table = new JTable(modelTable)), "Center");

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				dto.setcNo(table.getModel().getValueAt(row, 0).toString());
				dto.setcName(table.getModel().getValueAt(row, 1).toString());
				dto.setcPrice((Integer) table.getModel().getValueAt(row, 2));
			}
		});

		setVisible(true);

		dao.showMenuAll(modelTable);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == select) {
			String sur = txt.getText().trim();
			dao.searchName(modelTable, sur);
		} else if (e.getSource() == update) {
			new UpPage(modelTable, dto.getcNo(), dto.getcName(), dto.getcPrice());
		} else if (e.getSource() == del) {
			dao.deleteMenu(dto.getcNo());
			dao.showMenuAll(modelTable);
		} else if (e.getSource() == insert) {
			new InsertPage(modelTable);
		} else if (e.getSource() == c) {
			dao.selectType(modelTable, "C");
		} else if (e.getSource() == d) {
			dao.selectType(modelTable, "D");
		} else {
			dao.selectType(modelTable, "A");
		}
	}

}
