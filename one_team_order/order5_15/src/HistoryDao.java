import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
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

		String sql = "SELECT * FROM history WHERE hno = " + Num;
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
			String sql = "select * from history";
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

		} catch (Exception e) {
			System.out.println("전체 검색 실패");

		} finally {
			dbClose();
		}
	}
	
	 public void Searchview(DefaultTableModel model, String hmenu) {
	      try {
	         String sql = "SELECT * FROM history WHERE hmenu = '아메리카노'";
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

	 
	public void updateview(DefaultTableModel model, Order order) {
		HistoryDto dtoup = new HistoryDto();
		try {

			if (order.txt1.getText().length() == 0) {
				JOptionPane.showMessageDialog(order, "수정하고 싶은 데이터를 목록에서 선택해주세요.");
				return;
			}

			String sql = "SELECT * FROM history where hid = '" + order.txt1.getText() + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				dto.setHno(rs.getInt("hno"));
				dto.setHid(rs.getString("hid"));
				dto.setHmenu(rs.getString("hmenu"));
				dto.setHsize(rs.getString("hsize"));
				dto.setHoption(rs.getString("hoption"));
				dto.setHprice(rs.getString("hprice"));
				dto.setHdate(rs.getString("hdate"));

				if (dtoup.getHmenu().trim().equals(order.txt2.getText().trim())
						&& dtoup.getHsize().trim().equals(order.txt3.getText().trim())
						&& dtoup.getHoption().trim().equals(order.txt1.getText().trim())
						&& dtoup.getHprice().trim().equals(order.txt5.getText().trim())) {

					JOptionPane.showMessageDialog(order, "상품명, 사이즈, 옵션, 가격만 수정할 수 있습니다. 수정할 내용을 입력해주세요");
					return;
				}

				String sqlup = "update history set hmenu = '" + order.txt2.getText() + "', hsize = '"
						+ order.txt3.getText() + "', hoption = '" + order.txt1.getText() + "', hprice = '"
						+ order.txt5.getText() + "' where hid = '" + order.txt1.getText() + "'";
				ps = con.prepareStatement(sqlup);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("수정 실패");
		}

		JOptionPane.showMessageDialog(order, "정보가 수정되었습니다.");
		allview(model);
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
