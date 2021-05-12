//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import javax.swing.JOptionPane;
//
//import customer.rec.scusData;
//
//public class sCustomerDAO {
//	 Connection con = null;
//	 PreparedStatement pstmt;  ResultSet rs;
//	 String sql; 
//
//	public  void connect(){
//		 String jdbc_url="jdbc:oracle:thin:@localhost:1521:orcl";
//		 String db_id ="one";
//		 String db_pwd="cafe";
//		try{
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			con = DriverManager.getConnection(jdbc_url,db_id,db_pwd);
//			System.out.println("성공적으로 로딩되었음");
//			
//		}
//		catch(ClassNotFoundException e){
//			System.out.println("해당 드라이버를 찾을 수 없습니다.1");
//		}
//		catch(SQLException se){
//			System.out.println("해당 드라이버를 찾을 수 없습니다.2");
//		}
//	}
//	
//	 void showMyInfo(scusData sdto){
//		connect();
//		
//		try {
//			String id = "lily93";
//			sql="SELECT * FROM CUSTOMER WHERE PID=?";
//			pstmt=con.prepareStatement(sql);
//			pstmt.setString(1, id);
//			rs=pstmt.executeQuery();
//			ArrayList list = new ArrayList();
//			while(rs.next()) {
//				sdto.setPname(rs.getString("PNAME"));
//				sdto.setPgender(rs.getString("PGENDER"));
//				sdto.setPid(rs.getString("PID"));
//				sdto.setPpwd(rs.getString("PPWD"));
//				sdto.setPtel(rs.getString("PTEL"));
//				sdto.setPemail(rs.getString("PEMAIL"));
//				sdto.setPaddr(rs.getString("PADDR"));
//				sdto.setPaccount(rs.getString("PACCOUNT"));
//				sdto.setPdate(rs.getString("PDATE"));
//			
//				list.add(sdto);
//			}
//			
//		}catch(Exception e) {
//			System.out.println("회원 정보를 불러올 수 없습니다.");
//			e.printStackTrace();
//		}
//	}
//	 
//	 void modifyMyInfo(scusData sdto) {
//		
//		 try {
//		
//		sql="UPDATE CUSTOMER SET PNAME= ?, PPWD = ?, PTEL = ?, PEMAIL = ?, PADDR = ?, PACCOUNT = ? WHERE PID = ?";
//		pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, sdto.getPname());
//		pstmt.setString(2, sdto.getPpwd());
//		pstmt.setString(3, sdto.getPgender());
//		pstmt.setString(4, sdto.getPtel());
//		pstmt.setString(5, sdto.getPemail());
//		pstmt.setString(6, sdto.getPaddr());
//		pstmt.setString(7, sdto.getPaccount());
//		pstmt.setString(8, sdto.getPid());
//		
//		int count = pstmt.executeUpdate();
//		System.out.println("정보수정 성공!");
//		showMyInfo(sdto);
//		 }catch(Exception e) {
//			 e.printStackTrace();
//		 }
//	 
//	 }
//	 
//	 void deleteMyInfo(String id) {
//		 try {
//				sql="DELETE FROM CUSTOMER WHERE PID = ?";
//				pstmt=con.prepareStatement(sql);
//				pstmt.setString(1, id);
//				pstmt.executeUpdate();
//				 }catch(Exception e) {
//					 e.printStackTrace();
//				 }
//	 }
//	 
//	 
//	 public int findMyPwd(String id,String tel) {
//		 connect();
//		 String password=null;
//		 int count=0;
//		 try {
//			 sql="SELECT PPWD FROM CUSTOMERTEST WHERE PID = ? AND PTEL = ?";
//			 pstmt=con.prepareStatement(sql);
//				pstmt.setString(1, id);
//				pstmt.setString(2, tel);
//				rs=pstmt.executeQuery();
//				if(rs.next()) {
//					password=rs.getString("PPWD");
//					JOptionPane.showMessageDialog(null, "인증되었습니다!\n"+"회원님의 비밀번호는 "+password+"입니다.");
//					count++;
//					Login login = new Login();
//					return count;
//				}
//				else {
//					JOptionPane.showMessageDialog(null, "없는 아이디이거나 전화번호가 일치하지 않습니다!\n"+"아이디와 전화번호를 확인해주시거나 회원가입해주세요.");
//					return count;
//				}
//		}catch(Exception e) {
//					 e.printStackTrace();
//		}
//		 return count;
//	}
//}
