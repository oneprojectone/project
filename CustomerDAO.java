import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class CustomerDAO {
	 Connection con;
	 PreparedStatement pstmt;  ResultSet rs;
	 String sql; 
	 JTable tableCustomer;
	 
	 public	CustomerDAO() {
		 
		}
	
	 void Connect() {
		 try {
				con =Singleton.getConnection();
				System.out.println("연결완료");
				}catch(Exception e) {
					e.printStackTrace();
				}
	 }
	 
	 void showMyInfo(String id,CustomerVO dto){
		try {
			Connect();
			sql="SELECT * FROM CUSTOMER WHERE PID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			//ArrayList list = new ArrayList();
			while(rs.next()) {
				dto.setPname(rs.getString("PNAME"));
				dto.setPgender(rs.getString("PGENDER"));
				dto.setPid(rs.getString("PID"));
				dto.setPpwd(rs.getString("PPWD"));
				dto.setPtel(rs.getString("PTEL"));
				dto.setPemail(rs.getString("PEMAIL"));
				dto.setPaddr(rs.getString("PADDR"));
				dto.setPaccount(rs.getString("PACCOUNT"));
				dto.setPdate(rs.getString("PDATE"));
			
				//list.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println("회원 정보를 불러올 수 없습니다.");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	 
	 void modifyMyInfo(CustomerVO dto) {
		 try {
			 Connect();
		sql="UPDATE CUSTOMER SET PNAME= ?, PPWD = ?, PGENDER = ?, PTEL = ?, PEMAIL = ?, PADDR = ?, PACCOUNT = ? WHERE PID = ?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dto.getPname());
		pstmt.setString(2, dto.getPpwd());
		pstmt.setString(3, dto.getPgender());
		pstmt.setString(4, dto.getPtel());
		pstmt.setString(5, dto.getPemail());
		pstmt.setString(6, dto.getPaddr());
		pstmt.setString(7, dto.getPaccount());
		pstmt.setString(8, dto.getPid());
		
		int count=pstmt.executeUpdate();
		System.out.println("정보수정 성공!");
		showMyInfo(dto.getPid(),dto);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
				try {
					rs.close();
					con.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
	 }
	 
	 void deleteMyInfo(String id) {
		 try {
			 Connect();
				sql="DELETE FROM CUSTOMER WHERE PID = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				 }catch(Exception e) {
					 e.printStackTrace();
				 }finally {
						try {
							rs.close();
							con.close();
						}catch (SQLException se) {
							se.printStackTrace();
						}
					}
	 }
	 
	 public int findMyPwd(String id,String tel) {
		 String password=null;
		 int count=0;
		 try {
			 Connect();
			 sql="SELECT PPWD FROM CUSTOMER WHERE PID = ? AND PTEL = ?";
			 pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, tel);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					password=rs.getString("PPWD");
					JOptionPane.showMessageDialog(null, "인증되었습니다!\n"+"회원님의 비밀번호는 "+password+"입니다.");
					count++;
					//Login login = new Login();
					return count;
				}
				else {
					JOptionPane.showMessageDialog(null, "없는 아이디이거나 전화번호가 일치하지 않습니다!\n"+"아이디와 전화번호를 확인해주시거나 회원가입해주세요.");
					return count;
				}
		}catch(Exception e) {
					 e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}
		}
		 return count;
	}
	 
	 public void loginCheck(Login login) {
			
			String id = login.tf_id.getText().trim();
			String pwd = login.tf_pass.getText().trim();
			if(id.length()==0) {
				JOptionPane.showMessageDialog(login, "ID입력");
				login.tf_pass.setText(null);
				login.tf_id.requestFocus();
				return;
				
			}else if(pwd.length()==0) {
				JOptionPane.showMessageDialog(login, "비번입력");
				login.tf_pass.requestFocus();
				return;
			}
			
			try {
				Connect();
				sql="SELECT PPWD FROM CUSTOMER WHERE PID = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				if (rs.next()) {
					if(pwd.equals(rs.getString("PPWD"))) {
						JOptionPane.showMessageDialog(login, "환영합니다");
						login.setVisible(false);
						Cafe cafe = new Cafe(login.tf_id.getText());
				}
					else {
						JOptionPane.showMessageDialog(login, "비밀번호가 일치하지 않습니다");
						login.tf_id.setText(null);
						login.tf_pass.setText(null);
						login.tf_id.requestFocus();
						return;
					}
					
				}
					else {
						JOptionPane.showMessageDialog(login, "아이디가 일치하지 않습니다");
						login.tf_id.setText(null);
						login.tf_pass.setText(null);
						login.tf_id.requestFocus();
						return;
					}
						
			} catch (Exception e) {e.printStackTrace();}
			finally {
				try {
					rs.close();
					pstmt.close();
					con.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}	
	 }
	 
	 public int checkID(Regist reg) {
		 int count = 0;
		 String id = reg.tf2.getText().trim();
		 if(id.length()==0) {
				JOptionPane.showMessageDialog(reg, "ID입력");
				reg.tf2.requestFocus();
				count=0;
				return count;
			}
		 try {
				Connect();
				sql="SELECT PID FROM CUSTOMER WHERE PID = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					JOptionPane.showMessageDialog(reg, id+"는 이미 존재하는 ID 입니다!");
					reg.tf2.setText("");
					reg.tf2.requestFocus();
					count =0;
					return count;
				}
				else {
					JOptionPane.showMessageDialog(reg, id+"는 사용 가능한 ID 입니다!");
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
			
		 }catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
	 }
}
	 

