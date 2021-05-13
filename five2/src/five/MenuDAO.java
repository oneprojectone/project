package five;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;

public class MenuDAO {
	Connection con;
	 PreparedStatement pstmt;  
	 ResultSet rs;
	 Statement stmt;
	 String sql; 
	 JTable tableCustomer;
	
	 
	 public	MenuDAO() {
		 try {
			connectDB();
			System.out.println("연결 완료");
		}catch(Exception e)
		 {
			e.printStackTrace();
		 }}
	 
		void  connectDB() {
			try {
			con = Singleton.getConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	public void selectAll(MenuDTO dto) {
			String query = "select * from menu order by cno";
			try {
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					
				}
			} catch (SQLException se) {
				System.out.println("selectAll : " + se.toString());
			}
	}
	public void SearchCname(MenuDTO dto, String cName) {
		
	}
	
	void DeleteMyInfo(String cname) {
		try {
		sql = "delete from menu where cname = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, cname);
		pstmt.executeUpdate();
		}catch(Exception e) { 
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	void UpdateMyInfo(MenuDTO dto) {
		try {
			sql ="update menu set cno = ?, cname = ?, cprice = ?";
		} catch(Exception e) {
			System.out.println("업뎃 실패");
		}
	}

}
