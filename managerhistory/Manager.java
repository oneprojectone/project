import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Manager extends JPanel implements ActionListener{
	JPanel panWest;
	JPanel panSouth;
	JPanel panCenter;
	JPanel p1, p2, p3, p4, p5, p6, p7;
	JTextField txtNo, txtId, txtName, txtSize, txtIh, txtPrice, txtDate;
	JButton btnTotal, btnAdd, btnDel, btnSearch, btnCancel;
	SelectFrame sfr = new SelectFrame();
//	SelectFrame sfrdao = new SelectFrame();
	
	HistoryDao dao = new HistoryDao();
	HistoryDto dto = new HistoryDto();
	
	String data[][] = new String[0][7]; // 0�� addRow�� �� ������, 7�� column ����
	String title[] = { "NO", "���̵�", "��ǰ��", "������", "�ɼ�", "����", "�ֹ���¥" };
	
	
	DefaultTableModel model = new DefaultTableModel(data, title) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	
	JTable table = new JTable(model); // �˻�, ��ü���⸦ ���� ���̺� ��ü ����
	JScrollPane panScorllCenter = new JScrollPane(table);
	
	
	public Manager() {
		
		setLayout(new BorderLayout());
		panWest = new JPanel(new GridLayout(7,0));
		p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(new JLabel("�� ȣ"));
		p1.add(txtNo = new JTextField(12));
		panWest.add(p1);
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(new JLabel("�� �� ��"));
		p2.add(txtId = new JTextField(12));
		panWest.add(p2);
		p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("�� ǰ ��"));
		p3.add(txtName = new JTextField(12));
		panWest.add(p3);
		p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4.add(new JLabel("�� �� ��"));
		p4.add(txtSize = new JTextField(12));
		panWest.add(p4);
		p5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p5.add(new JLabel("�� ��"));
		p5.add(txtIh = new JTextField(12));
		panWest.add(p5);
		p6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p6.add(new JLabel("�� ��"));
		p6.add(txtPrice = new JTextField(12));
		panWest.add(p6);
		p7 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p7.add(new JLabel("�� �� �� ¥"));
		p7.add(txtDate = new JTextField(12));
		panWest.add(p7);
		add(panWest, "West");
		
		add(panScorllCenter, "Center");
		
		
		panSouth = new JPanel();
		panSouth.add(btnTotal = new JButton("��ü����"));
		panSouth.add(btnSearch = new JButton("��  ��"));
		panSouth.add(btnAdd = new JButton("��  ��"));
		panSouth.add(btnDel = new JButton("��  ��"));
//		panSouth.add(btnCancel = new JButton("��  ��"));
		add(panSouth, "South");
		
/*		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) { System.exit(0); }

		});
*/		
		
		
		btnTotal.addActionListener(this);
		btnSearch.addActionListener(this);
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		sfr.btnOk.addActionListener(this);
		sfr.btnCancel.addActionListener(this);
//		btnCancel.addActionListener(this);
		// �� ���̺� ��ü ����
//		add(new JScrollPane(table = new JTable()), "Center");
		
		
		 table.addMouseListener(new MouseAdapter() {
	    	  
		      public void mouseClicked(MouseEvent e) {
		         int row = table.getSelectedRow();
		         int col = 0;
		         int Num = (Integer)table.getValueAt(row, col);
		         
//		          System.out.println("�ҷ���1");
		         try {
		            dto = dao.selectByPk(Num);
//		            System.out.println("�ҷ���2");
		         }catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "�˻� ���� : " + ex.getMessage());
		         }
		         
		         

		         txtNo.setText(dto.getNo());  
		         txtId.setText(dto.getId());
		         txtName.setText(dto.getName());
		         txtSize.setText(dto.getSize());
		         txtIh.setText(dto.getOption());
		         txtPrice.setText(dto.getPrice());
		         txtDate.setText(String.valueOf(dto.getDate()));
		     }
		   } );
		      
		 
/*		 this.addWindowListener(new WindowAdapter() {
	          @Override
	          public void windowClosing(WindowEvent e) {
	              try { 
	                  if(dao.rs!=null) dao.rs.close();
	                  if(dao.pstmt!=null) dao.pstmt.close();
	                  if(dao.con!=null) dao.con.close();                    
	              }catch(Exception e2) {
	                  System.exit(0);
	              }
	          }
	      });
*/		
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		setVisible(true);
		
		dao.dbConnect();
		dao.allview(model, this);
	}
	
	
	public void actionPerformed(ActionEvent e) {

		Object ob = e.getSource();

		if (ob == btnTotal) {
			
			dao.allview(model,this);
			
		}
		
		else if (ob == btnSearch) {
			
			dao.init(this);
			sfr.setVisible(true);
			sfr.txtId.setEditable(false);
        	sfr.txtName.setEditable(false);
        	sfr.txtDate.setEditable(false);
			dao.mouseview(sfr);
		}
		
		else if (ob == sfr.btnOk) {
			dao.searchview(model, sfr);
			
			if(sfr.txtId.getText().length() == 0 && sfr.txtName.getText().length() == 0 
					&& sfr.txtDate.getText().length() == 0) {
				JOptionPane.showMessageDialog(sfr, "�˻��ϰ� ���� �����͸� �Է����ּ���.");
				sfr.setVisible(true);
				sfr.txtId.setEditable(false);
	        	sfr.txtName.setEditable(false);
	        	sfr.txtDate.setEditable(false);
				dao.mouseview(sfr);
			}
			else if(sfr.txtId.getText().length() == 0 || sfr.txtName.getText().length() == 0 
					|| sfr.txtDate.getText().length() == 0) {			
//				JOptionPane.showMessageDialog(sfr, "������ �˻� �Ǿ����ϴ�.");
				sfr.txtId.setText("");
				sfr.txtName.setText("");
				sfr.txtDate.setText("");
				sfr.setVisible(false);
			}
			
		}
		else if (ob == sfr.btnCancel) {
			sfr.txtId.setText("");
			sfr.txtName.setText("");
			sfr.txtDate.setText("");
			sfr.setVisible(false);
		}

		
		else if (ob == btnAdd) {
			dao.updateview(this);
		}
		
		else if (ob == btnDel) {
			dao.deleteview(this);
		}

/*					
			try {
				if(ob == sfr.btnOk) {
				searchview();
				System.out.println("Ȯ�ι�ư");
			    }
			}catch (Exception i) { System.out.println("����"); }
			
//			txtId.setEditable(true);
//			txtName.setEditable(true);
//			txtDate.setEditable(true);
//			setTitle(e.getActionCommand());
*/			
		}
	
	
	
	
/*	

//			login.setVisible(false); 			submit.setVisible(true);

		} else if (ob == submit.submit) {

			String la = submit.submit.getText();

			if (la.equals("��  ��")) {

				if (!checkID(id)) {

					JOptionPane.showMessageDialog(submit, "�̹� ��� �� ID�Դϴ�.");

					return;

				}

				insertProcess(); 				submit.tf_id.setText("");

				submit.tf_pass1.setText("");	submit.tf_pass2.setText("");

				submit.tf_name.setText("");		submit.tf_jumin1.setText("");

				submit.tf_jumin2.setText("");	submit.tf_tel1.setText("");

				submit.tf_tel2.setText("");		submit.tf_tel3.setText("");

				submit.tf_addr.setText("");		

			} else {

				updateProcess();

			}

		} else if (ob == show) {

			showProcess();

		} else if (ob == del) {

			deleteProcess();

		} else if (ob == login.ok) {

			loginCheck();

		} else if (ob == submit.idCheck) {

			id = submit.tf_id.getText().trim();

			if (id.equals("")) {

				JOptionPane.showMessageDialog(submit, "���̵� �Է�");

				submit.tf_id.requestFocus();

				return;

			}

			if (checkID(id)) {

				JOptionPane.showMessageDialog(submit, "��밡���� ID�Դϴ�.");

				submit.tf_pass1.requestFocus();

			} else {

				JOptionPane.showMessageDialog(submit, "��� �� ID�Դϴ�. �ٽ� �Է��ϼ���.");

				submit.tf_id.setText(null);

				submit.tf_id.requestFocus();

				return;

			}

		} else if (ob == update) {

			updateShow();

		} else if (ob == exit) {

			System.exit(0);

		} else if (ob == admin) {

			if (!id.equals("admin") || !pass.equals("admin")) {

				JOptionPane.showMessageDialog(submit, 

						"������ ������ �ʿ��մϴ�. \n �����ڷ� �α����ϼ���.");

				return;

			} else {

				JOptionPane.showMessageDialog(submit, "������  �α��� �Ǿ����ϴ�.");

				del.setEnabled(true);

				flag = true;

			}

		}

	}
	*/
	
	   
}
