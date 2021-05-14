package five;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class MenuDAO {
	Connection con;
	 PreparedStatement pstmt;  
	 ResultSet rs;
	 Statement stmt;
	 String sql; 
	
	
	 
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
		
	
	 void selectAll(DefaultTableModel model) {
			 sql = "select * from menu order by cno";
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				for(int i =0; i< model.getRowCount();) {
					model.removeRow(0);
				}
				while (rs.next()) {
				Object[] data = { rs.getString("cno"),rs.getString("cname"),
				 rs.getInt("cprice")
				 };
				 model.addRow(data);
				}
				
			} catch (SQLException se) {
				System.out.println("selectAll : " + se.toString());
			} finally {
			
			}
	}
	
	 void SearchCname(DefaultTableModel model, String cName) {
			sql = "select * from menu where  cname like '%' || ? || '%' order by cno";
		try {
			for(int i =0; i< model.getRowCount();) {
				model.removeRow(0);
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Object[] data = { rs.getString("cno"),rs.getString("cname"),
				 rs.getInt("cprice")
				 };
				 model.addRow(data);
			}
		} catch(Exception e) {
			System.out.println("selectcname" + e);
		}
	}
	 void InsertCname(String cno, String cname, Integer cprice) {
		sql = "insert into menu values(?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cno);
			pstmt.setString(2, cname);
			pstmt.setInt(3, cprice);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
