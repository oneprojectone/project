import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerDAO {
	 Connection con = null;
	 PreparedStatement pstmt;  ResultSet rs;
	 String sql; 

	public  void connect(){
		 String jdbc_url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		 String db_id ="one";
		 String db_pwd="cafe";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(jdbc_url,db_id,db_pwd);
			System.out.println("성공적으로 로딩되었음");
			
		}
		catch(ClassNotFoundException e){
			System.out.println("해당 드라이버를 찾을 수 없습니다.1");
		}
		catch(SQLException se){
			System.out.println("해당 드라이버를 찾을 수 없습니다.2");
		}
	}
	
	 void showMyInfo(cusData dto){
		connect();
		
		try {
			String id = "GMC01";
			sql="SELECT * FROM CUSTOMER WHERE PID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			ArrayList list = new ArrayList();
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
			
				list.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println("회원 정보를 불러올 수 없습니다.");
			e.printStackTrace();
		}
	}
	 
	 void modifyMyInfo(cusData dto) {
		
		 try {
		
		sql="UPDATE CUSTOMER SET PNAME= ?, PPWD = ?, PTEL = ?, PEMAIL = ?, PADDR = ?, PACCOUNT = ? WHERE PID = ?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dto.getPname());
		pstmt.setString(2, dto.getPpwd());
		pstmt.setString(3, dto.getPtel());
		pstmt.setString(4, dto.getPemail());
		pstmt.setString(5, dto.getPaddr());
		pstmt.setString(6, dto.getPaccount());
		pstmt.setString(7, dto.getPid());
		
		int count=pstmt.executeUpdate();
		System.out.println("정보수정 성공!");
		showMyInfo(dto);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 
	 }
	 
	 void deleteMyInfo(String id) {
		 try {
				sql="DELETE FROM CUSTOMER WHERE PID = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
	 }
	 
	 
}
