package menu;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import customer.Singleton;

public class DAO_Menu {
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public	DAO_Menu() {
		connectDB();
	}
	
	void connectDB() {
		try {
			con = Singleton.getConnection();
	} catch (Exception e) { e.printStackTrace();}
	}

	public int getHistoryNo() {
		int result = 0;
		String query = "select hno from history";
		try {
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			if (!rs.last()) {
				result = 1;
			} else {
				result = (rs.getInt("hno") + 1);
			}
		} catch (SQLException se) {
			System.out.println("insertOrder : " + se.toString());
		} 
		return result;
	}

	public int insertOrder(DTO_History history) {
		int result = 0;
		String sql = "insert into history (hno, hid, hmenu, hsize, hoption, hprice) " + " values (?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, history.gethNo());
			pstmt.setString(2, history.gethID());
			pstmt.setString(3, history.gethMenu());
			pstmt.setString(4, history.gethSize());
			pstmt.setString(5, history.gethOption());
			pstmt.setInt(6, history.gethPrice());
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println("insertOrder : " + se.toString());
		} 
		return result;
	}

	public void selectAll(ArrayList<DTO_Menu> list) {
		String query = "select cno, cname, cprice from menu order by cno";
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DTO_Menu menu = new DTO_Menu(rs.getString("cno"), rs.getString("cname"), rs.getInt("cprice"));
				list.add(menu);
			}
		} catch (SQLException se) {
			System.out.println("selectAll : " + se.toString());
		} 
	}

	public boolean selectName(ArrayList<DTO_Menu> list, String cName) {
		boolean result = false;
		String query = "select cno, cname, cprice from menu where cname like '%" + cName + "%'";
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DTO_Menu menu = new DTO_Menu(rs.getString("cno"), rs.getString("cname"), rs.getInt("cprice"));
				list.add(menu);
				result = true;
			}
		} catch (SQLException se) {
			System.out.println("selectName : " + se.toString());
		} 
		return result;
	}

	public void selectType(ArrayList<DTO_Menu> list, String cNo) {
		String query = "select cno, cname, cprice from menu where cno like '%" + cNo + "%'";
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DTO_Menu menu = new DTO_Menu(rs.getString("cno"), rs.getString("cname"), rs.getInt("cprice"));
				list.add(menu);
			}

		} catch (SQLException se) {
			System.out.println("selectType : " + se.toString());
		}
	}
}
