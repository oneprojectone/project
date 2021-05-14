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

	public int getHistoryNo() {
		int result = 0;
		String query = "select hno from history";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			if (!rs.last()) {
				result = 1;
			} else {
				result = (rs.getInt("hno") + 1);
			}
		} catch (SQLException se) {
			System.out.println("insertOrder : " + se.toString());
		} finally {
			DBConn.close(pstmt);
			DBConn.close();
		}
		return result;
	}

	public int insertOrder(DTO_History history) {
		int result = 0;
		String sql = "insert into history (hno, hid, hmenu, hsize, hoption, hprice) " + " values (?, ?, ?, ?, ?, ?)";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, history.gethNo());
			pstmt.setString(2, history.gethID());
			pstmt.setString(3, history.gethMenu());
			pstmt.setString(4, history.gethSize());
			pstmt.setString(5, history.gethOption());
			pstmt.setInt(6, history.gethPrice());
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println("insertOrder : " + se.toString());
		} finally {
			DBConn.close(pstmt);
			DBConn.close();
		}
		return result;
	}

	public void selectAll(ArrayList<DTO_Menu> list) {
		String query = "select cno, cname, cprice from menu order by cno";
		conn = DBConn.getConnection();
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
			DBConn.close(rs);
			DBConn.close(pstmt);
			DBConn.close();
		}
	}

	public boolean selectName(ArrayList<DTO_Menu> list, String cName) {
		boolean result = false;
		String query = "select cno, cname, cprice from menu where cname like '%" + cName + "%'";
		conn = DBConn.getConnection();
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
			DBConn.close(rs);
			DBConn.close(pstmt);
			DBConn.close();
		}
		return result;
	}

	public void selectType(ArrayList<DTO_Menu> list, String cNo) {
		String query = "select cno, cname, cprice from menu where cno like '%" + cNo + "%'";
		conn = DBConn.getConnection();
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
			DBConn.close(rs);
			DBConn.close(pstmt);
			DBConn.close();
		}
	}
}
