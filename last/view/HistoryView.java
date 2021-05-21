package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DecimalFormat;

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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import dao.CustomerDAO;
import dao.HistoryDao;
import dto.HistoryVO;

public class HistoryView extends JPanel implements ActionListener, ItemListener{
	


	public JPanel panWest, panOutside;
	public JPanel panSouth;
	public JPanel panCenter;
	public JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9;
	public JLabel rowcount, space,lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8,lab9,lab10,lab11,lab12,lab13,lab14,lab15,lab16,lab17,lab18;
	public JTextField txtNo, txtId, txtName, txtSize, txtIh, txtPrice, txtDate;
	public JButton btnTotal, btnAdd, btnDel, btnSearch, btnCancel;
	public SelectFrame sfr = new SelectFrame();
	
	public JRadioButton rbtnSize_m, rbtnSize_l, rbtnOption_i, rbtnOption_h;
	
	HistoryDao dao = new HistoryDao();
	HistoryVO dto = new HistoryVO();
	CustomerDAO custdao= new CustomerDAO();
	int temp[] = new int[2];
// ��Ÿ����	
	String menuName[] = {"�������ּ���", "�Ƹ޸�ī��", "ī���", "ī���ī", "�ٴҶ��", "���ڶ�", "��ü��", "�ڹ�Ĩ����Ǫġ��",
				"����", "��Ʈ����Ĩ����Ǫġ��", "�ݵ���", "�ݵ����", "īǪġ��", "ī��Ḷ���ƶ�", "����������",
				"�ڸ���Ϻ�Ƽ", "�����Ѷ�������", "��ü�ݵ���", "����ġ������ũ", "ũ��ũ����", "��Ϻ극��", "���ڸ���",
				"���̱�", "Ƽ��̼�����ũ", "��Ʈ��������ũ", "����������Ʈ�κ���", "�ڸ����̵�", "�����̵�", "������̵�", 
				"������Ʈ������", "��纣�����Ʈ������", "Ű�����Ʈ������" };
	
	public JComboBox<String> comboBox;
	
	String data[][] = new String[0][7]; 
	String title[] = { "NO", "���̵�", "��ǰ��", "������", "�ɼ�", "����", "�ֹ���¥" };
	
	
	DefaultTableModel model = new DefaultTableModel(data, title) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable table = new JTable(model);
	JScrollPane panScorllCenter = new JScrollPane(table);
	
// ���� �޸�
	DecimalFormat formatter = new DecimalFormat("###,###,###,###");
	
	
	public HistoryView(){
		table.setFont(new Font("Serif",Font.PLAIN, 20));
		   table.setRowHeight(table.getRowHeight() + 10);
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
		setLayout(new BorderLayout());
		panOutside = new JPanel(new GridLayout(1,0));
		panOutside.setBorder(new TitledBorder("�ֹ� ����"));
		
		panWest = new JPanel(new GridLayout(7,0));
		p1 = new JPanel(new GridLayout(3,0));
		lab1 = new JLabel("�� ȣ");
		lab1.setFont(font);
		p1.add(lab1);
		p1.add(txtNo = new JTextField(14));
		panWest.add(p1);
		
		p2 = new JPanel(new GridLayout(3,0));
		lab2=new JLabel("�� �� ��");
		lab2.setFont(font);
		p2.add(lab2);
		p2.add(txtId = new JTextField(14));
		panWest.add(p2);
		
		p3 = new JPanel(new GridLayout(3,0));
		lab3=new JLabel("�� ǰ ��");
		lab3.setFont(font);
		p3.add(lab3);
		
		comboBox = new JComboBox<String>(menuName);
		p3.add(comboBox);
		panWest.add(p3);
		
		p9 = new JPanel(new GridLayout(1,0));
		p4 = new JPanel(new GridLayout(2,0));
		p4.setBorder(new TitledBorder("�� �� ��"));
		
//		lab4 = new JLabel("�� �� ��");
		
//		lab4.setFont(font);
//		p4.add(lab4);
		ButtonGroup btnGroup = new ButtonGroup();		
		rbtnSize_m = new JRadioButton("Medium", true);
		rbtnSize_l = new JRadioButton("Large",false);
		btnGroup.add(rbtnSize_m); btnGroup.add(rbtnSize_l);
		p4.add(rbtnSize_m); p4.add(rbtnSize_l);
//		panWest.add(p4);
		p9.add(p4);
		
		rbtnSize_m.setFont(font);
		rbtnSize_l.setFont(font);
	
		p5 = new JPanel(new GridLayout(2,0));
		p5.setBorder(new TitledBorder("�� ��"));
//		lab5 = new JLabel("�� ��");
//		lab5.setFont(font);
//		p5.add(lab5);
		ButtonGroup btnGroupOp = new ButtonGroup();	
		rbtnOption_h = new JRadioButton("Hot",true);
		rbtnOption_i = new JRadioButton("Ice", false);
		rbtnOption_h.setFont(font);
		rbtnOption_i.setFont(font);
		btnGroupOp.add(rbtnOption_h); btnGroupOp.add(rbtnOption_i);
		p5.add(rbtnOption_h); p5.add(rbtnOption_i);
		p9.add(p5);
		panWest.add(p9);
		
		
		p6 = new JPanel(new GridLayout(3,0));
		lab6 = new JLabel("�� ��");
		lab6.setFont(font);
		p6.add(lab6);
		p6.add(txtPrice = new JTextField(14));
		panWest.add(p6);
		
		p7 = new JPanel(new GridLayout(3,0));
		lab7 =new JLabel("�� �� �� ¥");
		lab7.setFont(font);
		p7.add(lab7);
		p7.add(txtDate = new JTextField(14));
		panWest.add(p7);
		
		panOutside.add(panWest);
		add(panOutside, "West");
		
		panWest.setPreferredSize(new Dimension(250, 0));
		
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
		btnTotal.setFont(font);
		btnSearch.setFont(font);
		btnAdd.setFont(font);
		btnDel.setFont(font);
		space.setFont(font);
		rowcount.setFont(font);
		add(panSouth, "South");
		Color b = new Color(248,234,221);  
		panSouth.setBackground(b);
		panWest.setBackground(b);
		panScorllCenter.setBackground(b);
		
		p1.setBackground(b);
		p2.setBackground(b);
		p3.setBackground(b);
		p4.setBackground(b);
		p5.setBackground(b);
		p6.setBackground(b);
		p7.setBackground(b);
		
		
		rbtnSize_m.setBackground(b);
		rbtnSize_l.setBackground(b);
		rbtnOption_i.setBackground(b); 
		rbtnOption_h.setBackground(b);
		
		
		
		
		
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
	    
		         } else if(jbtnvalues.equals("Medium")) {
		        	 rbtnSize_m.setEnabled(true);
		        	 rbtnSize_l.setEnabled(true);
		        	 rbtnSize_m.setSelected(true);
		         }
		         
		         else if(jbtnvalues.equals("Large")) {
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
	         
		         else if(jbtnvaluesOp.equals("Hot")) {
		        	 rbtnOption_h.setEnabled(true);
		        	 rbtnOption_i.setEnabled(true);
		        	 rbtnOption_h.setSelected(true);
		         }
	         
// ice �κ� ����		         
		         else if(jbtnvaluesOp.equals("Ice")) {
		        	 if (comboBox.getSelectedItem() == "�ڹ�Ĩ����Ǫġ��" || comboBox.getSelectedItem() == "��Ʈ����Ĩ����Ǫġ��"
		     				|| comboBox.getSelectedItem() == "�ݵ���" || comboBox.getSelectedItem() == "�ݵ����"
		     				|| comboBox.getSelectedItem() == "�ڸ����̵�" || comboBox.getSelectedItem() == "�����̵�"
		     				|| comboBox.getSelectedItem() == "������̵�" || comboBox.getSelectedItem() == "������Ʈ������"
		     				|| comboBox.getSelectedItem() == "��纣�����Ʈ������" || comboBox.getSelectedItem() == "Ű�����Ʈ������"
		     				|| comboBox.getSelectedItem() == "��ü�ݵ���") {
		     	    	  rbtnOption_i.setSelected(true);
		     	    	  rbtnOption_h.setEnabled(false);
		     	    	  rbtnOption_i.setEnabled(false);
		     		}
		        	 else {
		        	 rbtnOption_h.setEnabled(true);
		        	 rbtnOption_i.setEnabled(true);
		        	 rbtnOption_i.setSelected(true);
		        	 }
		         }
		         
		         txtPrice.setText(dto.gethPrice());
		         txtDate.setText(String.valueOf(dto.gethDate()));
		         
		         
		         txtNo.setEditable(false);
		         txtId.setEditable(false);
		         txtPrice.setEditable(false);
		         txtDate.setEditable(false);
// Ż���� ȸ�� �ֹ����� ���� ���ϰ� ����		         
		         if(dto.gethID().equals("Ż����ȸ��") ) {
		        	 comboBox.setEnabled(false);
		        	 rbtnSize_m.setEnabled(false);
		        	 rbtnSize_l.setEnabled(false);
		        	 rbtnOption_h.setEnabled(false);
		        	 rbtnOption_i.setEnabled(false);
		         }
		         
		     }
		   } );
	
		setBounds(100, 100, 700, 300);
		setVisible(true);
		
		dao.dbConnect();
		temp=dao.allview(model, this);
		for(int i=0; i<table.getRowCount();i++) {
			if(table.getValueAt(i, 1)==null) {
				setForeground(Color.red);
			}
		}
			
			
		
// ���� �޸�			
		rowcount.setText("�� ���� :  " + temp[0] + " ��," +"    �� ���� :  "+ formatter.format(temp[1]) + " ��");
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);

		Object ob = e.getSource();

		if (ob == btnTotal) {
			
			temp=dao.allview(model,this);
// ���� �޸�			
			rowcount.setText("�� ���� :  " + temp[0] + " ��," +"    �� ���� :  "+ formatter.format(temp[1]) + " ��");
			
		}
		
		else if (ob == btnSearch) {
			
			dao.init(this);
			sfr.setVisible(true);
			sfr.txtId.setEditable(false);
			sfr.comboBox.setSelectedItem("�������ּ���");
			sfr.comboBox.setEditable(false);
        	sfr.txtDate.setEditable(false);
// txtDate2 �߰�
        	sfr.txtDate2.setEditable(false);
			dao.mouseview(sfr);
		}
		
		else if (ob == sfr.btnOk) {
			temp=dao.searchview(model, sfr, this);
			System.out.println(temp[0]+"����!");
			System.out.println(temp[1]+"����!");
// ���� �޸�			
			rowcount.setText("�� ���� :  " + temp[0] + " ��," +"    �� ���� :  "+ formatter.format(temp[1]) + " ��");
			
			if(sfr.txtId.getText().length() == 0 && sfr.comboBox.getSelectedItem() == "�������ּ���" 
					&& sfr.txtDate.getText().length() == 0) {
				lab8 = new JLabel("�˻��ϰ� ���� �����͸� �Է����ּ���.");
				lab8.setFont(font);
				JOptionPane.showMessageDialog(sfr, lab8);
				sfr.setVisible(true);
				sfr.txtId.setEditable(false);
	        	sfr.comboBox.setEditable(false);
	        	sfr.txtDate.setEditable(false);
// txtDate2 �߰�	        	
	        	sfr.txtDate2.setEditable(false);
				dao.mouseview(sfr);
			}
			else if(sfr.txtId.getText().length() == 0 ||sfr.comboBox.getSelectedItem() == "�������ּ���" 
					|| sfr.txtDate.getText().length() == 0) {			

				sfr.txtId.setText("");
				sfr.comboBox.setSelectedItem("�������ּ���");
				sfr.txtDate.setText("");
// txtDate2 �߰�					
				sfr.txtDate2.setText("");
				sfr.setVisible(false);
				
			}
			
		}
		else if (ob == sfr.btnCancel) {
			sfr.txtId.setText("");
			sfr.comboBox.setSelectedItem("�������ּ���");;
			sfr.txtDate.setText("");
// txtDate2 �߰�					
			sfr.txtDate2.setText("");
			sfr.setVisible(false);
			
		}

		
		else if (ob == btnAdd) {
			dao.updateview(model, this);
			temp = dao.allview(model, this);
// ���� �޸�			
			rowcount.setText("�� ���� :  " + temp[0] + " ��," +"    �� ���� :  "+ formatter.format(temp[1]) + " ��");
			
		}
		
		else if (ob == btnDel) {
			dao.deleteview(model, this);
			temp = dao.allview(model, this);
// ���� �޸�			
			rowcount.setText("�� ���� :  " + temp[0] + " ��," +"    �� ���� :  "+ formatter.format(temp[1]) + " ��");
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
	
	
	
	

