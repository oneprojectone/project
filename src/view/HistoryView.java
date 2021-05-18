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
	
	String menuName[] = {"�������ּ���", "�Ƹ޸�ī��", "ī���", "ī���ī", "�ٴҶ��", "���ڶ�", "��ü��", "�ڹ�ġ����Ǫġ��",
			"����", "��Ʈ����Ĩ����Ǫġ��", "�ݵ���", "�ݵ����", "īǪġ��", "ī��Ḷ���ƶ�", "����������",
			"�ڸ���Ϻ�Ƽ", "�����Ѷ�������", "��ü�ݵ���", "����ġ������ũ", "ũ��ũ����", "��Ϻ극��", "���ڸ���",
			"���̱�", "Ƽ��̼�����ũ", "��Ʈ��������ũ", "����������Ʈ�κ���", "�ڸ����̵�", "�����̵�", "������̵�", 
			"������Ʈ������", "��纣�����Ʈ������", "Ű�����Ʈ������" };
	
	public JComboBox<String> comboBox;
	
	String data[][] = new String[0][7]; // 0�� addRow�� �� ������, 7�� column ����
	String title[] = { "NO", "���̵�", "��ǰ��", "������", "�ɼ�", "����", "�ֹ���¥" };
	
	
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
		p1.add(new JLabel("�� ȣ"));
		p1.add(txtNo = new JTextField(14));
		panWest.add(p1);
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(new JLabel("�� �� ��"));
		p2.add(txtId = new JTextField(14));
		panWest.add(p2);
		
		p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("�� ǰ ��"));
		comboBox = new JComboBox<String>(menuName);
		p3.add(comboBox);
		panWest.add(p3);
		
		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4.add(new JLabel("�� �� ��"));
		ButtonGroup btnGroup = new ButtonGroup();		
		rbtnSize_m = new JRadioButton("Medium", false);
		rbtnSize_l = new JRadioButton("Large",false);
		btnGroup.add(rbtnSize_m); btnGroup.add(rbtnSize_l);
		p4.add(rbtnSize_m); p4.add(rbtnSize_l);
		panWest.add(p4);
	
		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p5.add(new JLabel("�� ��"));
		ButtonGroup btnGroupOp = new ButtonGroup();	
		rbtnOption_h = new JRadioButton("Hot",false);
		rbtnOption_i = new JRadioButton("Ice", false);
		btnGroupOp.add(rbtnOption_h); btnGroupOp.add(rbtnOption_i);
		p5.add(rbtnOption_h); p5.add(rbtnOption_i);
		panWest.add(p5);
		
		
		p6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p6.add(new JLabel("�� ��"));
		p6.add(txtPrice = new JTextField(14));
		panWest.add(p6);
		p7 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p7.add(new JLabel("�� �� �� ¥"));
		p7.add(txtDate = new JTextField(14));
		panWest.add(p7);
		add(panWest, "West");
		
		add(panScorllCenter, "Center");
		
		rowcount = new JLabel();
		space = new JLabel("                                                                                                    ");
		
		panSouth = new JPanel();
		panSouth.add(btnTotal = new JButton("��ü����"));
		panSouth.add(btnSearch = new JButton("��  ��"));
		panSouth.add(btnAdd = new JButton("��  ��"));
		panSouth.add(btnDel = new JButton("��  ��"));
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
		            JOptionPane.showMessageDialog(null, "�˻� ���� : " + ex.getMessage());
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
		        	 System.out.println("�ΰ�");
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
		rowcount.setText("�� ���� : " + temp[0] + "��," +"    �� ���� : "+ temp[1] + "��");
	}
	
	
	public void actionPerformed(ActionEvent e) {

		Object ob = e.getSource();

		if (ob == btnTotal) {
			
			temp=dao.allview(model,this);
			//model.
			rowcount.setText("�� ���� : " + temp[0] + "��," +"    �� ���� : "+ temp[1] + "��");
			
		}
		
		else if (ob == btnSearch) {
			
			dao.init(this);
			sfr.setVisible(true);
			sfr.txtId.setEditable(false);
			sfr.comboBox.setSelectedItem("�������ּ���");
			sfr.comboBox.setEditable(false);
        	sfr.txtDate.setEditable(false);
			dao.mouseview(sfr);
		}
		
		else if (ob == sfr.btnOk) {
			temp=dao.searchview(model, sfr, this);
			System.out.println(temp[0]+"����!");
			System.out.println(temp[1]+"����!");
			rowcount.setText("�� ���� : " + temp[0] + "��," +"    �� ���� : "+ temp[1] + "��");
			
			if(sfr.txtId.getText().length() == 0 && sfr.comboBox.getSelectedItem() == "�������ּ���" 
					&& sfr.txtDate.getText().length() == 0) {
				JOptionPane.showMessageDialog(sfr, "�˻��ϰ� ���� �����͸� �Է����ּ���.");
				sfr.setVisible(true);
				sfr.txtId.setEditable(false);
	        	sfr.comboBox.setEditable(false);
	        	sfr.txtDate.setEditable(false);
				dao.mouseview(sfr);
			}
			else if(sfr.txtId.getText().length() == 0 ||sfr.comboBox.getSelectedItem() == "�������ּ���" 
					|| sfr.txtDate.getText().length() == 0) {			

				sfr.txtId.setText("");
				sfr.comboBox.setSelectedItem("�������ּ���");
				sfr.txtDate.setText("");
				sfr.setVisible(false);
				
			}
			
		}
		else if (ob == sfr.btnCancel) {
			sfr.txtId.setText("");
			sfr.comboBox.setSelectedItem("�������ּ���");;
			sfr.txtDate.setText("");
			sfr.setVisible(false);
			
		}

		
		else if (ob == btnAdd) {
			dao.updateview(model, this);
			temp = dao.allview(model, this);
			rowcount.setText("�� ���� : " + temp[0] + "��," +"    �� ���� : "+ temp[1] + "��");
			
		}
		
		else if (ob == btnDel) {
			dao.deleteview(model, this);
			temp = dao.allview(model, this);
			rowcount.setText("�� ���� : " + temp[0] + "��," +"    �� ���� : "+ temp[1] + "��");
		}
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(comboBox.getSelectedItem() == "�Ƹ޸�ī��" || comboBox.getSelectedItem() == "ī���"
				|| comboBox.getSelectedItem() == "ī���ī" || comboBox.getSelectedItem() == "�ٴҶ��"
				|| comboBox.getSelectedItem() == "���ڶ�" || comboBox.getSelectedItem() == "��ü��"
				|| comboBox.getSelectedItem() == "����" || comboBox.getSelectedItem() == "īǪġ��"
				|| comboBox.getSelectedItem() == "ī��Ḷ���ƶ�" || comboBox.getSelectedItem() == "����������"
				|| comboBox.getSelectedItem() == "�ڸ���Ϻ�Ƽ" || comboBox.getSelectedItem() == "�����Ѷ�������" ) {
			  rbtnSize_m.setEnabled(true);
	    	  rbtnSize_l.setEnabled(true);  		
	    	  rbtnOption_h.setEnabled(true);
	    	  rbtnOption_i.setEnabled(true);
		}
		else if (comboBox.getSelectedItem() == "�ڹ�Ĩ����Ǫġ��" || comboBox.getSelectedItem() == "��Ʈ����Ĩ����Ǫġ��"
				|| comboBox.getSelectedItem() == "�ݵ���" || comboBox.getSelectedItem() == "�ݵ����"
				|| comboBox.getSelectedItem() == "�ڸ����̵�" || comboBox.getSelectedItem() == "�����̵�"
				|| comboBox.getSelectedItem() == "������̵�" || comboBox.getSelectedItem() == "������Ʈ������"
				|| comboBox.getSelectedItem() == "��纣�����Ʈ������" || comboBox.getSelectedItem() == "Ű�����Ʈ������"
				|| comboBox.getSelectedItem() == "��ü�ݵ���") {
			 rbtnSize_m.setEnabled(true);
	    	  rbtnSize_l.setEnabled(true);  
	    	  rbtnOption_i.setSelected(true);
	    	  rbtnOption_h.setEnabled(false);
	    	  rbtnOption_i.setEnabled(false);
		}
		else if (comboBox.getSelectedItem() == "����ġ������ũ" || comboBox.getSelectedItem() == "ũ��ũ����"
				  || comboBox.getSelectedItem() == "��Ϻ극��" || comboBox.getSelectedItem() == "���ڸ���"
				  || comboBox.getSelectedItem() == "���̱�" || comboBox.getSelectedItem() == "Ƽ��̼�����ũ"
				  || comboBox.getSelectedItem() == "��Ʈ��������ũ" || comboBox.getSelectedItem() == "����������Ʈ�κ���") {
			 rbtnSize_m.setEnabled(false);
	    	  rbtnSize_l.setEnabled(false);  		
	    	  rbtnOption_h.setEnabled(false);
	    	  rbtnOption_i.setEnabled(false);
		}

	}
}
	
	
	
	

