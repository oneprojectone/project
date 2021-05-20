package dao;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import cafeOne.Cafe;
import cafeOne.CafeCustomer;
import dbConnect.Singleton;
import dto.CustomerVO;
import index.Login;
import loginview.Regist;

public class LoginDAO {
	Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
	Connection con=null; 	PreparedStatement pstmt; 	
	ResultSet rs; 		Statement stmt;
	String sql;
	JTable		tableCustomer;
	
	public	LoginDAO() {
		connectDB();
	}
	
	void connectDB() {
		try {
			con = Singleton.getConnection();
	} catch (Exception e) { e.printStackTrace();}
	}
	
	
	
	 public int findMyId(String name,String tel) {
		 String ID=null;
		 int count=0;
		 try {
			 connectDB();
			 sql="SELECT PID FROM CUSTOMER WHERE Pname = ? AND PTEL = ?";
			 pstmt=con.prepareStatement(sql);
			 pstmt.setString(1, name);
			 pstmt.setString(2, tel);
			 rs=pstmt.executeQuery();
			 if(rs.next()) {
				 ID=rs.getString("PID");
				 JLabel lab1 = new JLabel("�����Ǿ����ϴ�!\n"+"ȸ������ ID�� "+ID+"�Դϴ�.");
				 lab1.setFont(font);
				 JOptionPane.showMessageDialog(null, lab1);
				 count++;
				 return count;
			 }
			 else {
				 JLabel lab2 = new JLabel("��ġ�ϴ� ID�� �����ϴ�.\n"+"�̸��� ��ȭ��ȣ�� Ȯ�����ֽðų� ȸ���������ּ���.");
				 lab2.setFont(font);
				 JOptionPane.showMessageDialog(null, lab2);
				 return count;
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return count;
	 }
	 
	 public int findMyPwd(String id,String tel) {
		 
		 String password=null;
		 int count=0;
		 try {
			 connectDB();
			 sql="SELECT PPWD FROM CUSTOMER WHERE PID = ? AND PTEL = ?";
			 pstmt=con.prepareStatement(sql);
			 pstmt.setString(1, id);
			 pstmt.setString(2, tel);
			 rs=pstmt.executeQuery();
			 if(rs.next()) {
				 password=rs.getString("PPWD");
				 JLabel lab3 = new JLabel("�����Ǿ����ϴ�!\n"+"ȸ������ ��й�ȣ�� "+password+"�Դϴ�.");
				 lab3.setFont(font);
				 JOptionPane.showMessageDialog(null, lab3);
				 count++;
				 //Login login = new Login();
				 return count;
			 }
			 else {
				 JLabel lab4 = new JLabel("���� ���̵��̰ų� ��ȭ��ȣ�� ��ġ���� �ʽ��ϴ�!\n"+"���̵�� ��ȭ��ȣ�� Ȯ�����ֽðų� ȸ���������ּ���.");
				 lab4.setFont(font);
				 JOptionPane.showMessageDialog(null,lab4 );
				 return count;
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
			 try {
				 rs.close();
				 pstmt.close();
				 
			 }catch (SQLException se) {
				 se.printStackTrace();
			 }
		 }
		 return count;
	 }
	 
	 public void loginCheck(Login login) {
			
			String id = login.tf1.getText().trim();
			String pwd = login.tf2.getText().trim();
			if(id.length()==0) {
				JLabel lab5 = new JLabel("ID�Է�");
				lab5.setFont(font);
				JOptionPane.showMessageDialog(login,lab5);
				login.tf2.setText(null);
				login.tf1.requestFocus();
				//return;
				
			}else if(pwd.length()==0) {
				JLabel lab6 = new JLabel("����Է�");
				lab6.setFont(font);
				JOptionPane.showMessageDialog(login, lab6);
				login.tf2.requestFocus();
				return;
			}
			
			try {
				connectDB();
				sql="SELECT PPWD FROM CUSTOMER WHERE PID = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				if (rs.next()) {
					if(pwd.equals(rs.getString("PPWD"))) {
						if(id.equals("GMC01")) {
							JLabel lab7 = new JLabel("�����ڴ� ȯ���մϴ�");
							lab7.setFont(font);
						JOptionPane.showMessageDialog(login,lab7 );
						login.setVisible(false);
						Cafe cafe = new Cafe();
								}
						else {
							JLabel lab8 = new JLabel(id+"�� ȯ���մϴ�");
							lab8.setFont(font);
							JOptionPane.showMessageDialog(login, lab8);
							login.setVisible(false);
							CafeCustomer cafe = new CafeCustomer(login.tf1.getText());
						}
				}
					else {
						JLabel lab9 = new JLabel("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
						lab9.setFont(font);
						JOptionPane.showMessageDialog(login, lab9);
						login.tf1.setText(null);
						login.tf2.setText(null);
						login.tf1.requestFocus();
						return;
					}
					
				}
					else {
						JLabel lab10 = new JLabel("���̵� ��ġ���� �ʽ��ϴ�");
						lab10.setFont(font);
						JOptionPane.showMessageDialog(login, lab10);
						login.tf1.setText(null);
						login.tf2.setText(null);
						login.tf1.requestFocus();
						return;
					}
						
			} catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					rs.close();
					pstmt.close();
			
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}	
	 }
	 
	 public int checkID(Regist reg) {
		 int count = 0;
		 String id = reg.tf2.getText().trim();
		 if(id.length()==0) {
			 JLabel lab11 = new JLabel("ID�Է�");
				lab11.setFont(font);
				JOptionPane.showMessageDialog(reg, lab11);
				reg.tf2.requestFocus();
				count=0;
				return count;
			}
		 try {
				connectDB();
				System.out.println("����~");
				sql="SELECT PID FROM CUSTOMER WHERE PID = ?";
				//
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					JLabel lab12 = new JLabel(id+"�� �̹� �����ϴ� ID �Դϴ�!");
					lab12.setFont(font);
					JOptionPane.showMessageDialog(reg, lab12);
					reg.tf2.setText("");
					reg.tf2.requestFocus();
					count =0;
					return count;
				}
				else {
					JLabel lab13 = new JLabel(id+"�� ��� ������ ID �Դϴ�!");
					lab13.setFont(font);
					JOptionPane.showMessageDialog(reg, lab13);
					reg.tf3.requestFocus();
					count=count+1;
					return count;
				}
				
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return count;
		 
	 }
	 
	 
	 public void regist(CustomerVO vo) {
		 
		 try {
		 sql="INSERT INTO CUSTOMER (PNAME, PID, PPWD, PGENDER, PTEL, PEMAIL, PADDR, PACCOUNT) VALUES(?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getPname());
			pstmt.setString(2, vo.getPid());
			pstmt.setString(3, vo.getPpwd());
			pstmt.setString(4, vo.getPgender());
			pstmt.setString(5, vo.getPtel());
			pstmt.setString(6, vo.getPemail());
			pstmt.setString(7, vo.getPaddr());
			pstmt.setString(8, vo.getPaccount());
			int count=pstmt.executeUpdate();
			
			sql="INSERT INTO COUPON (PID) VALUES(?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getPid());
			pstmt.executeUpdate();
			
		 }catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
		
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
	 }
	 
	
	
	
}
