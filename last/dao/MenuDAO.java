package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import dbConnect.Singleton;
import dto.HistoryVO;

//210518	16:57	장바구니 전체 주문용 메소드 생성 insertShoppingBasket();

public class MenuDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	Statement stmt;
	private String sql;
	static int count;

	public MenuDAO() {
		try {
			connectDB();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void connectDB() {
		try {
			con = Singleton.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getHistoryNo() {
		Integer result = 0;
		String query = "select * from history order by hno"; //정렬 조건 추가 21.05.19(김수정)
		try {
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			if (rs.last()) {
				System.out.println(rs.getInt(1));
				result = rs.getInt(1) + 1;
				System.out.println("no :" + result );
			} else {
				result = 1;
				System.out.println("no :" + result);
			}
		} catch (SQLException se) {
			System.out.println("getHistoryNo : " + se.toString());
		} finally {
			try {
			
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	public int getMenuNo(DefaultTableModel model, String cno) {
		int count = -1;
		String sql = "select * from menu where  cno like '%' || ? || '%' order by cno";
		try {
			int equlas = 0;
			int countcount = 0;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				countcount++;
				String name = rs.getString("cno").substring(1, 4);
				equlas = Integer.parseInt(name);
				if (countcount != equlas + 1 && count == -1) {
					count = equlas - 1;
				}
			}
			if (count == -1) {
				count = countcount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				pstmt.close();
			} catch (SQLException se) {
				System.out.println("닫기 실패" + se);
			}
		}
		return count;
	}

	public int insertOrder(HistoryVO history) {
		int result = 0;
		String sql = "insert into history (hno, hid, hmenu, hsize, hoption) " + " values (?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, history.gethNo());
			pstmt.setString(2, history.gethID());
			pstmt.setString(3, history.gethMenu());
			pstmt.setString(4, history.gethSize());
			pstmt.setString(5, history.gethOption());
			result = pstmt.executeUpdate();
			System.out.println("insertOrder : " + history);
		} catch (SQLException se) {
			System.out.println("insertOrder : " + se.toString());
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertShoppingBasket(ArrayList<HistoryVO> list) {
		int result = 0;
		int count = 0;
		String sql = "insert into history (hno, hid, hmenu, hsize, hoption) " + " values (?, ?, ?, ?, ?)";
		try {
			con.setAutoCommit(false);
			System.out.println("insertShoppingBasket size : " + list.size());
			for (HistoryVO dto : list) {
				dto.sethNo(getHistoryNo());
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.gethNo());
				pstmt.setString(2, dto.gethID());
				pstmt.setString(3, dto.gethMenu());
				pstmt.setString(4, dto.gethSize());
				pstmt.setString(5, dto.gethOption());
				result = pstmt.executeUpdate();
				count += result;
				System.out.println("insertOrder : " + dto);
				System.out.println("count : " + count);
			}
		} catch (SQLException se) {
			System.out.println("insertShoppingBasket : " + se.toString());
		} finally {
			if (count == list.size()) {
				try {
					con.commit();
					con.setAutoCommit(true);
					System.out.println("insert Shopping Basket Commit " );
				} catch (Exception e) {
					System.out.println("insert Shopping Basket : " + e.toString());
				}
			} else {
				try {
					con.rollback();
					System.out.println("insert Shopping Basket Rollback " );
				} catch (Exception e) {
				}
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public void insertMenu(String cno, String cname, Integer cprice) {
		String sql = "insert into menu values(?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cno);
			pstmt.setString(2, cname);
			pstmt.setInt(3, cprice);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				pstmt.close();
			} catch (SQLException se) {
				System.out.println("닫기 실패" + se);
			}
		}
	}

	
	public void insertTable1(DefaultTableModel model1, String cno) {
		sql = "select * from menu where cno = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Object[] data = { rs.getString("cno"), rs.getString("cname"), rs.getInt("cprice") };
				model1.addRow(data);
			}

		} catch (SQLException se) {
			System.out.println("selectAll : " + se.toString());
		} finally {
			try {
				
				pstmt.close();
			} catch (SQLException se) {
				System.out.println("닫기 실패" + se);
			}
		}
	}

	public void deleteMenu(String cno) {
		try {
			String sql = "delete from menu where cno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				pstmt.close();
			} catch (SQLException se) {
				System.out.println("닫기 실패" + se);
			}
		}
	}

	public void updateMenu(String sur1, String sur4, String sur5, Integer sur6) {

		String sql = "UPDATE MENU " + "SET cno ='" + sur4 + "', cname = '" + sur5 + "', cprice = " + sur6
				+ " WHERE cno ='" + sur1 + "'";
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("업뎃 실패" + e);
		} finally {
			try {
				
				pstmt.close();
			} catch (SQLException se) {
				System.out.println("닫기 실패" + se);
			}
		}
	}

	public void showMenuAll(DefaultTableModel model) {
		String query = "select cno, cname, cprice from menu order by cno";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
				Object[] data = { rs.getString("cno"), rs.getString("cname"), rs.getInt("cprice") };
				model.addRow(data);
			}
		} catch (SQLException se) {
			System.out.println("selectAll : " + se.toString());
		} finally {
			try {
				
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean searchName(DefaultTableModel model, String cName) {
		boolean result = false;
		String query = "select cno, cname, cprice from menu where cname like '%" + cName + "%'";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
				Object[] data = { rs.getString("cno"), rs.getString("cname"), rs.getInt("cprice") };
				model.addRow(data);
				result = true;
			}
		} catch (SQLException se) {
			System.out.println("selectName : " + se.toString());
		} finally {
			try {
				
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void selectType(DefaultTableModel model, String cNo) {
		String query = "select cno, cname, cprice from menu where cno like '%" + cNo + "%'";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
				Object[] data = { rs.getString("cno"), rs.getString("cname"), rs.getInt("cprice") };
				model.addRow(data);
			}

		} catch (SQLException se) {
			System.out.println("selectType : " + se.toString());
		} finally {
			try {
			
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	int CountCNO(DefaultTableModel model, String cno) {
		sql = "select * from menu where  cno like '%' || ? || '%' order by cno";
		try {
			count = 0;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
