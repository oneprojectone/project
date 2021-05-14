package five;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MenuMain extends JFrame implements ActionListener, MouseListener {
		JTextField txt;
		JPanel pan, South; 
		JPanel p1,p2;
		JTable table;
		JButton select, update, del,insert;
		JRadioButton c,d,a;
		
		MenuDAO dao = new MenuDAO();
		
		String data[][] = new String[0][3];
		String[] title = {"코드", "상품명", "가격"};
		DefaultTableModel modelTable = new DefaultTableModel(data,title) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			
};
	
	public MenuMain() {
		ButtonGroup Group = new ButtonGroup();
		a = new JRadioButton("기타");
		c = new JRadioButton("커피");
		d = new JRadioButton("디저트");
		
		pan = new JPanel(new GridLayout(2,0));
		p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("상품명"));
		p1.add(txt = new JTextField(12));
		pan.add(p1);
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		Group.add(c);
		Group.add(d);
		Group.add(a);
		p2.add(c);
		p2.add(d);
		p2.add(a);
		pan.add(p2);
		
		add(pan, "West");
		South = new JPanel();
		South.add(select = new JButton("검색"));
		South.add(update = new JButton("수정"));
		South.add(del= new JButton("삭제"));
		South.add(insert= new JButton("추가"));
		
		add(South, "South");
		select.addActionListener(this);
		update.addActionListener(this);
		del.addActionListener(this);
		insert.addActionListener(this);
		c.addActionListener(this);		
		d.addActionListener(this);	
		a.addActionListener(this);
			
		
		add(new JScrollPane(table = new JTable(modelTable)), "Center");
		
		table.addMouseListener(new mymouseAdapter()); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,700,300);
		setVisible(true);
		
		dao.selectAll(modelTable);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == select) {
			String sur = txt.getText().trim();
			dao.SearchCname(modelTable,sur);
		} else if(e.getSource() == update) {
			new UpPage();
		} else if(e.getSource() == del) {
			String sur = txt.getText().trim();
			dao.DeleteMyInfo(sur);
			dao.selectAll(modelTable);
		} else if(e.getSource() == insert) {
			setVisible(false);
			new InsertPage();
		} else if(e.getSource() == c) {
			dao.RdaioSelectType(modelTable, "C");
		}
		else if(e.getSource() == d) {
			dao.RdaioSelectType(modelTable, "D");
		} else {
			dao.RdaioSelectType(modelTable, "A");
		}
	}
	class mymouseAdapter extends MouseAdapter {
	
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				  for (int i = 0; i < table.getColumnCount(); i++) {
					   Object[] updata = {table.getModel().getValueAt(row,i )};
					
				  }
				  		
				  } 
			}
			
	public static void main(String[] args) {
		new MenuMain();
	}
	}


