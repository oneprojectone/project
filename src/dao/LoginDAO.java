package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import cafeOne.Cafe;
import cafeOne.CafeCustomer;
import dbConnect.Singleton;
import dto.CustomerVO;
import index.Login;
import loginview.Regist;

public class LoginDAO {
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
				 JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�!\n"+"ȸ������ ID�� "+ID+"�Դϴ�.");
				 count++;
				 return count;
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "��ġ�ϴ� ID�� �����ϴ�.\n"+"�̸��� ��ȭ��ȣ�� Ȯ�����ֽðų� ȸ���������ּ���.");
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
				 JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�!\n"+"ȸ������ ��й�ȣ�� "+password+"�Դϴ�.");
				 count++;
				 //Login login = new Login();
				 return count;
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "���� ���̵��̰ų� ��ȭ��ȣ�� ��ġ���� �ʽ��ϴ�!\n"+"���̵�� ��ȭ��ȣ�� Ȯ�����ֽðų� ȸ���������ּ���.");
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
				JOptionPane.showMessageDialog(login, "ID�Է�");
				login.tf2.setText(null);
				login.tf1.requestFocus();
				//return;
				
			}else if(pwd.length()==0) {
				JOptionPane.showMessageDialog(login, "����Է�");
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
						JOptionPane.showMessageDialog(login, "�����ڴ� ȯ���մϴ�");
						login.setVisible(false);
						Cafe cafe = new Cafe();
								}
						else {
							JOptionPane.showMessageDialog(login, id+"�� ȯ���մϴ�");
							login.setVisible(false);
							CafeCustomer cafe = new CafeCustomer(login.tf1.getText());
						}
				}
					else {
						JOptionPane.showMessageDialog(login, "��й�ȣ�� ��ġ���� �ʽ��ϴ�");
						login.tf1.setText(null);
						login.tf2.setText(null);
						login.tf1.requestFocus();
						return;
					}
					
				}
					else {
						JOptionPane.showMessageDialog(login, "���̵� ��ġ���� �ʽ��ϴ�");
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
				JOptionPane.showMessageDialog(reg, "ID�Է�");
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
					JOptionPane.showMessageDialog(reg, id+"�� �̹� �����ϴ� ID �Դϴ�!");
					reg.tf2.setText("");
					reg.tf2.requestFocus();
					count =0;
					return count;
				}
				else {
					JOptionPane.showMessageDialog(reg, id+"�� ��� ������ ID �Դϴ�!");
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
			
			sql="INSERT INTO COUPON (ID) VALUES(?)";
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
