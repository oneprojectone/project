package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerDAO {
	Connection con=null; 	PreparedStatement pstmt; 	
	ResultSet rs; 		Statement stmt;
	String sql;
	JTable		tableCustomer;
	
	public	CustomerDAO() {
		try {
		connectDB();
		System.out.println("연결 완료");
	} catch(Exception e) { e.printStackTrace(); } 
	}
	
	void connectDB() {
		try {
			con = Singleton.getConnection();
	} catch (Exception e) { e.printStackTrace();}
	}
	
	public void ListAll(DefaultTableModel model) {
 
		String sql = "SELECT * FROM customer";
		String temp[] = new String[9];
		
		tableCustomer = new JTable(model);
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
		while (rs.next()) {
			temp[0] = rs.getString("pname");
			temp[1] = rs.getString("pid");
			temp[2] = rs.getString("ppwd");
			temp[3] = rs.getString("pgender");
			temp[4] = rs.getString("pemail");
			temp[5] = rs.getString("ptel");
			temp[6] = rs.getString("paddr");
			temp[7] = rs.getString("paccount");
			temp[8] = rs.getString("pdate");
			model.addRow(temp);
		}
		} catch(Exception e) {
		e.printStackTrace();
	}
		}
	public DefaultTableModel searchName(DefaultTableModel model, String name) throws Exception{
		
	   String sql = "SELECT * FROM customer where pname like '%"+name+"%'";
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);
         CustomerVO vo = new CustomerVO();
         String data[][] = new String [0][9];
     	String title[] = { "이름", "ID", "비밀번호", "성별", "EMAIL", "전화번호", "주소", "계좌번호", "가입날짜"};
     	for (int i = 0; i < model.getRowCount();) {
            model.removeRow(0);
         }
     	
     	String temp[] = new String[9];
         tableCustomer = new JTable(model);
         while(rs.next()) {
        	 temp[0] = rs.getString("pname");
 			temp[1] = rs.getString("pid");
 			temp[2] = rs.getString("ppwd");
 			temp[3] = rs.getString("pgender");
 			temp[4] = rs.getString("pemail");
 			temp[5] = rs.getString("ptel");
 			temp[6] = rs.getString("paddr");
 			temp[7] = rs.getString("paccount");
 			temp[8] = rs.getString("pdate");
 			model.addRow(temp);
         }
         rs.close();
         stmt.close();
         return model;
	}	
	
	public void modifyCustomer(CustomerVO cust) throws Exception{
	
		String sql = "UPDATE customer SET pname = ?, "
				+ "pgender = ?, pemail = ?, ptel = ?, paddr = ? "
				+ "WHERE pid = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, cust.getPname());
		pstmt.setString(2, cust.getPgender());
		pstmt.setString(3, cust.getPemail());
		pstmt.setString(4, cust.getPtel());
		pstmt.setString(5, cust.getPaddr());
		pstmt.setString(6, cust.getPid());
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void deleteCustomer(String name) throws Exception{
		String sql = "delete from customer where pname = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.executeUpdate();
		pstmt.close();
		
	}
	public CustomerVO selectByPk(String id) throws Exception{
		CustomerVO vo = new CustomerVO();
		
		String sql = "SELECT * FROM customer where pid = '"+id+"'";
		
		stmt = con.createStatement();
		
		rs = stmt.executeQuery(sql);
		if( rs.next() )
		{
			vo.setPname(rs.getString("PNAME")); 
            vo.setPid(rs.getString("PID")); 
            vo.setPpwd(rs.getString("PPWD")); 
            vo.setPgender(rs.getString("PGENDER")); 
            vo.setPemail(rs.getString("PEMAIL")); 
            vo.setPtel(rs.getString("PTEL"));
            vo.setPaddr(rs.getString("PADDR"));
            vo.setPaccount(rs.getString("PACCOUNT"));
            vo.setPdate(rs.getString("PDATE"));
		}
		
		rs.close();
		stmt.close();
		
		return vo;
	}
	
	
	public int findMyId(String name,String tel) {
		 String ID=null;
		 int count=0;
		 try {
			 sql="SELECT PID FROM CUSTOMER WHERE Pname = ? AND PTEL = ?";
			 pstmt=con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, tel);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					ID=rs.getString("PID");
					JOptionPane.showMessageDialog(null, "인증되었습니다!\n"+"회원님의 ID는 "+ID+"입니다.");
					count++;
					return count;
				}
				else {
					JOptionPane.showMessageDialog(null, "일치하는 ID가 없습니다.\n"+"이름과 전화번호를 확인해주시거나 회원가입해주세요.");
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
			}catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return count;
	}
	
	public void showMyInfo(String id,CustomerVO dto){
		try {
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
			}catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	 
	public void modifyMyInfo(CustomerVO dto) {
		 try {
			
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
					
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
	 }
	 
	 public void deleteMyInfo(String id) {
		 try {
			
				sql="DELETE FROM CUSTOMER WHERE PID = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				 }catch(Exception e) {
					 e.printStackTrace();
				 }finally {
						try {
							rs.close();
							
						}catch (SQLException se) {
							se.printStackTrace();
						}
					}
	 }
	 
	 
	 public void loginCheck(Login login) {
			
			String id = login.tf1.getText().trim();
			String pwd = login.tf2.getText().trim();
			if(id.length()==0) {
				JOptionPane.showMessageDialog(login, "ID입력");
				login.tf2.setText(null);
				login.tf1.requestFocus();
				return;
				
			}else if(pwd.length()==0) {
				JOptionPane.showMessageDialog(login, "비번입력");
				login.tf2.requestFocus();
				return;
			}
			
			try {
			
				sql="SELECT PPWD FROM CUSTOMER WHERE PID = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				if (rs.next()) {
					if(pwd.equals(rs.getString("PPWD"))) {
						if(id.equals("GMC01")) {
							Cafe cafe = new Cafe();
							JOptionPane.showMessageDialog(login, "관리자님 환영합니다");
						}else {
							CafeCustomer cc = new CafeCustomer(id);
						JOptionPane.showMessageDialog(login, id + "님 환영합니다");
						login.setVisible(false);}
						
				}
					else {
						JOptionPane.showMessageDialog(login, "비밀번호가 일치하지 않습니다");
						login.tf1.setText(null);
						login.tf2.setText(null);
						login.tf1.requestFocus();
						return;
					}
					
				}
					else {
						JOptionPane.showMessageDialog(login, "아이디가 일치하지 않습니다");
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
	 
	 public int checkID(Regist2 reg) {
		 int count = 0;
		 String id = reg.tf2.getText().trim();
		 if(id.length()==0) {
				JOptionPane.showMessageDialog(reg, "ID입력");
				reg.tf2.requestFocus();
				count=0;
				return count;
			}
		 try {
			
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
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		 
	 }
}