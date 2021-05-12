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
//			System.out.println("���������� �ε��Ǿ���");
//			
//		}
//		catch(ClassNotFoundException e){
//			System.out.println("�ش� ����̹��� ã�� �� �����ϴ�.1");
//		}
//		catch(SQLException se){
//			System.out.println("�ش� ����̹��� ã�� �� �����ϴ�.2");
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
//			System.out.println("ȸ�� ������ �ҷ��� �� �����ϴ�.");
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
//		System.out.println("�������� ����!");
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
//					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�!\n"+"ȸ������ ��й�ȣ�� "+password+"�Դϴ�.");
//					count++;
//					Login login = new Login();
//					return count;
//				}
//				else {
//					JOptionPane.showMessageDialog(null, "���� ���̵��̰ų� ��ȭ��ȣ�� ��ġ���� �ʽ��ϴ�!\n"+"���̵�� ��ȭ��ȣ�� Ȯ�����ֽðų� ȸ���������ּ���.");
//					return count;
//				}
//		}catch(Exception e) {
//					 e.printStackTrace();
//		}
//		 return count;
//	}
//}
