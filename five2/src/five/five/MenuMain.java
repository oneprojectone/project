package five;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

public class MenuMain extends JFrame implements ActionListener{
		JTextField txt;
		JPanel pan, South; 
		JPanel p1,p2;
		JTable table;
		JButton select, update, del,insert;
		JRadioButton c,d,a;
		MenuDAO dao = new MenuDAO();
		MenuDTO dto = new MenuDTO();
		String data[][] = new String[0][3];
		String[] title = {"�ڵ�", "��ǰ��", "����"};
		DefaultTableModel modelTable = new DefaultTableModel(data,title) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			};
	
	public MenuMain() {
		ButtonGroup Group = new ButtonGroup();
		a = new JRadioButton("��Ÿ");
		c = new JRadioButton("Ŀ��");
		d = new JRadioButton("����Ʈ");
		
		pan = new JPanel(new GridLayout(2,0));
		p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("��ǰ��"));
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
		South.add(select = new JButton("�˻�"));
		South.add(update = new JButton("����"));
		South.add(del= new JButton("����"));
		South.add(insert= new JButton("�߰�"));
		
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
				
				dto.setCno(table.getModel().getValueAt(row,0).toString());
				dto.setCname( table.getModel().getValueAt(row,1).toString());
				dto.setCprice((Integer)table.getModel().getValueAt(row,2));
			}
		}); 
		
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

			new UpPage(dto.getCno(),dto.getCname(),dto.getCprice());
		} else if(e.getSource() == del) {
			String sur = txt.getText().trim();
			dao.DeleteMyInfo(sur);
			dao.selectAll(modelTable);
		} else if(e.getSource() == insert) {
			
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
			
	public static void main(String[] args) {
		new MenuMain();
	}
}


