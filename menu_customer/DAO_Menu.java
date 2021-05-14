package one;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class DAO_Menu {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public DAO_Menu() {
		try {
			connectDB();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void connectDB() {
		try {
			conn = Singleton.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getHistoryNo() {
		int result = 0;
		String query = "select count(*) as count from history";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1) + 1;
				System.out.println("no :" + result);
			}
		} catch (SQLException se) {
			System.out.println("insertOrder : " + se.toString());
		} finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertOrder(DTO_History history) {
		int result = 0;
		String sql = "insert into history (hno, hid, hmenu, hsize, hoption) " + " values (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, history.gethNo());
			pstmt.setString(2, history.gethID());
			pstmt.setString(3, history.gethMenu());
			pstmt.setString(4, history.gethSize());
			pstmt.setString(5, history.gethOption());
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println("insertOrder : " + se.toString());
		} finally {
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void selectAll(ArrayList<DTO_Menu> list) {
		list.clear();
		String query = "select cno, cname, cprice from menu order by cno";

		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DTO_Menu menu = new DTO_Menu(rs.getString("cno"), rs.getString("cname"), rs.getInt("cprice"));
				list.add(menu);
			}
		} catch (SQLException se) {
			System.out.println("selectAll : " + se.toString());
		} finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean selectName(ArrayList<DTO_Menu> list, String cName) {
		list.clear();
		boolean result = false;
		String query = "select cno, cname, cprice from menu where cname like '%" + cName + "%'";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DTO_Menu menu = new DTO_Menu(rs.getString("cno"), rs.getString("cname"), rs.getInt("cprice"));
				list.add(menu);
				result = true;
			}
		} catch (SQLException se) {
			System.out.println("selectName : " + se.toString());
		} finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void selectType(ArrayList<DTO_Menu> list, String cNo) {
		list.clear();
		String query = "select cno, cname, cprice from menu where cno like '%" + cNo + "%'";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DTO_Menu menu = new DTO_Menu(rs.getString("cno"), rs.getString("cname"), rs.getInt("cprice"));
				list.add(menu);
			}

		} catch (SQLException se) {
			System.out.println("selectType : " + se.toString());
		} finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
