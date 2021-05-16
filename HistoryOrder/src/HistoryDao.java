import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class HistoryDao {

	PreparedStatement ps = null;
	ResultSet rs = null;
	Statement st = null;
	Connection con = DBConn.getConnection();

	HistoryDto dto = new HistoryDto();

	public void init(Order or) {

		or.txt1.setText("");
		or.txt2.setText("");
		or.txt3.setText("");
		or.txt4.setText("");
		or.txt5.setText("");
		
	}

	public HistoryDto selectByPk(Integer Num) throws Exception {
		String sql = "select history.hno, history.hid, history.hmenu, history.hsize, history.hoption, menu.cprice, history.hdate, "
                + "case hsize " 
                + "when 'l' then cprice + 500 " 
                + "else cprice "
                + "end as hprice "
                + "from history, menu where history.hmenu IN (menu.cname) and history.hno = " + Num;
		
		st = con.createStatement();
		rs = st.executeQuery(sql);
		if (rs.next()) {

			dto.setHno(rs.getInt("hno"));
			dto.setHid(rs.getString("hid"));
			dto.setHmenu(rs.getString("hmenu"));
			dto.setHsize(rs.getString("hsize"));
			dto.setHoption(rs.getString("hoption"));
			dto.setHprice(rs.getString("hprice"));
			dto.setHdate(rs.getString("hdate"));
		}
		rs.close();
		st.close();
		return dto;
	}

	public void allview(DefaultTableModel model) {

		try {
			String id ="lily93";
			String sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice ,history.hdate, "  
					               + "case hsize "  
					               + "when 'l' then cprice " 
					               + "else cprice " 
					               + "end as hprice " 
					               + "from history, menu where history.hmenu IN (menu.cname) and hid = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			

			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
				dto.setHno(rs.getInt("hno"));
				dto.setHid(rs.getString("hid"));
				dto.setHmenu(rs.getString("hmenu"));
				dto.setHsize(rs.getString("hsize"));
				dto.setHoption(rs.getString("hoption"));
				dto.setHprice(rs.getString("hprice"));
				dto.setHdate(rs.getString("hdate"));
				Object[] data = { dto.getHno(), dto.getHid(), dto.getHmenu(), dto.getHsize(), dto.getHoption(),
						dto.getHprice(), dto.getHdate() };
				model.addRow(data);
			}

		} catch (Exception e) {
			System.out.println("전체 검색 실패");

		} finally {
			dbClose();
		}
	}
	
	 public void Searchview(DefaultTableModel model, String hmenu ) {
	      try {
	         String sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice ,history.hdate, " 
	         							               + "case hsize "  
	         							               + "when 'l' then cprice " 
	         							               + "else cprice "  
	         							               + "end as hprice "  
	         							               + "from history, menu where history.hmenu IN (menu.cname) and hmenu like '%" + hmenu.trim() + "%'";
	         ps = con.prepareStatement(sql);
	         rs = ps.executeQuery();
	         
	         for (int i = 0; i < model.getRowCount();) {
	             model.removeRow(0);
	         }
	             while (rs.next()) {
	 				dto.setHno(rs.getInt("hno"));
	 				dto.setHid(rs.getString("hid"));
	 				dto.setHmenu(rs.getString("hmenu"));
	 				dto.setHsize(rs.getString("hsize"));
	 				dto.setHoption(rs.getString("hoption"));
	 				dto.setHprice(rs.getString("hprice"));
	 				dto.setHdate(rs.getString("hdate"));
	 				Object[] data = { dto.getHno(), dto.getHid(), dto.getHmenu(), dto.getHsize(), dto.getHoption(),
	 						dto.getHprice(), dto.getHdate() };
	 				model.addRow(data);
	 			}     
	         } catch (SQLException e) {
	             System.out.println(e + "=> 실패");
	          } finally {
	             dbClose();
	          } 
	 }    
	             
	 public HistoryDto modifyhistory(HistoryDto dto) throws Exception
		{    
		 String sql = "UPDATE history SET  "
					+ "hsize = ?, hmenu = ?, hoption = ? "
					+ "WHERE hno = ?";
		 
		 
		 ps = con.prepareStatement(sql);
		 
		 ps.setString(1, dto.getHsize());
		 ps.setString(2, dto.getHmenu());
		 ps.setString(3, dto.getHoption());
		 ps.setInt(4, dto.getHno());
		 ps.executeUpdate();
		
			 rs.close();
			 st.close();
			 return dto;
		}
	 
	public void deleteMyInfo(int data) {
		try {
			String sql = "DELETE FROM history WHERE hno = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, data);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}

	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}

}
