package five;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class MenuMain extends JPanel implements ActionListener{
		JTextField txt;
		JPanel pan, South; 
		JPanel p1,p2;
		JTable table,table1;
		JButton select, update, del,insert;
		JRadioButton c,d,a;
		boolean check = false;
		MenuDAO dao = new MenuDAO();
		MenuDTO dto = new MenuDTO();
		
		static String data[][] = new String[0][3];
		static String[] title = {"��ǰ �ڵ�", "��ǰ��", "����"};
		static DefaultTableModel modelTable = new DefaultTableModel(data,title) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			};
		static String data1[][] = new String[0][3];
		static String[] title1 = {"��ǰ �ڵ�", "��ǰ��", "����"};
		static DefaultTableModel modelTable1 = new DefaultTableModel(data1,title1) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			};
	
	public MenuMain() {
		setLayout(new BorderLayout());
		ButtonGroup Group = new ButtonGroup();
		a = new JRadioButton("��Ÿ");
		c = new JRadioButton("Ŀ��");
		d = new JRadioButton("����Ʈ");
		
		pan = new JPanel(new GridLayout(3,0));
		
		
		
		p1 = new JPanel();
		
		p1.add(txt = new JTextField(12));
		p1.add(select = new JButton());
		select.setText("�˻�");
		p1.setBorder(new TitledBorder("��ǰ��"));
		pan.add(p1);
		
		
		
		p2 = new JPanel(new GridLayout(3,0));
		
		Group.add(c);
		Group.add(d);
		Group.add(a);
		p2.add(c);
		p2.add(d);
		p2.add(a);
		p2.setBorder(new TitledBorder("����"));
		pan.add(p2);
		
		JPanel p0 = new JPanel(new BorderLayout());
		p0.setBorder(new TitledBorder("�ֱٿ� �߰��� �޴�"));
		JScrollPane scroll2 = new JScrollPane(new JTable(modelTable1));
		scroll2.setPreferredSize(new Dimension(250, 200));
		p0.add(scroll2, "Center");
		
		pan.add(p0);
		
		add(pan, "West");
		South = new JPanel(new GridLayout(0,7));
		South.add(new JPanel());
		South.add(update = new JButton("����"));
		South.add(new JPanel());
		South.add(del= new JButton("����"));
		South.add(new JPanel());
		South.add(insert= new JButton("�߰�"));
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
				 check = true;
				dto.setCno(table.getModel().getValueAt(row,0).toString());
				dto.setCname( table.getModel().getValueAt(row,1).toString());
				dto.setCprice((Integer)table.getModel().getValueAt(row,2));
			}
		}); 
		
		
		
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
			if(check == true) {
			int reslut = JOptionPane.showConfirmDialog(null, "���� �Ͻðڽ��ϱ�?","Confirm",JOptionPane.YES_NO_OPTION);
			
			if(reslut == JOptionPane.CLOSED_OPTION) {
				JOptionPane.showMessageDialog(null, "�ٽ� �������ּ���");
			}
			else if(reslut == JOptionPane.YES_OPTION) {
				dao.DeleteMyInfo(dto.getCno());
				dao.selectAll(modelTable);
			}else 
				JOptionPane.showMessageDialog(null, "��� �ϼ̽��ϴ�");
			} else
				JOptionPane.showMessageDialog(null, "���̺� ���� �� ���� ��ư�� �����ּ���");
			
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
		
}


