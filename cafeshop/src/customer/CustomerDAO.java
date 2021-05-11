package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import customer.rec.CustomerVO;

public class CustomerDAO {
	JTable		tableCustomer;
	/*
	 * private String custName; private String custId; private String custPwd;
	 * private String custGender; private String custEmail; private String custTel;
	 * private String custAddr; private String custAccount; private String custDate;
	 * 
	 * public String getCustName() { return custName; } public String getCustId() {
	 * return custId; } public String getCustPwd() { return custPwd; } public String
	 * getCustGender() { return custGender; } public String getCustEmail() { return
	 * custEmail; } public String getCustTel() { return custTel; } public String
	 * getCustAddr() { return custAddr; } public String getCustAccount() { return
	 * custAccount; } public String getCustDate() { return custDate; }
	 * 
	 * public void setCustName(String name) { custName = name; } public void
	 * setCustId(String id) { custId = id; } public void setCustPwd(String pwd) {
	 * custPwd = pwd; } public void setCustGender(String gender) { custGender =
	 * gender; } public void setCustEmail(String email) { custEmail = email; }
	 * public void setCustTel(String tel) { custTel = tel; } public void
	 * setCustAddr(String addr) { custAddr = addr; } public void
	 * setCustAccount(String account) { custAccount = account; } public void
	 * setCustDate(String date) { custDate = date; }
	 */
	
	public	CustomerDAO() throws Exception{
		connectDB();
	}
	ResultSet rs;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	String url= "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "one";
	String pass = "cafe";
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	void connectDB() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url,user,pass);
	}
	public void ListAll(DefaultTableModel model) {
 
		String sql = "SELECT * FROM customer";
		String temp[] = new String[9];
		
		tableCustomer = new JTable(model);
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
		while (rs.next()) {
			temp[0] = rs.getString("pname");
			temp[1] = rs.getString("pid");
			temp[2] = rs.getString("ppwd");
			temp[3] = rs.getString("pgender");
			temp[4] = rs.getString("pemail");
			temp[5] = rs.getString("ptel");
			temp[6] = rs.getString("paddr");
			temp[7] = rs.getString("paccount");
			temp[8] = rs.getString("pdate");
			model.addRow(temp);
		}
		} catch(Exception e) {
		e.printStackTrace();
	}
		}
	public DefaultTableModel searchName(String name) throws Exception{
		
	   String sql = "SELECT * FROM customer where pname like '%"+name+"%'";
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);
         CustomerVO vo = new CustomerVO();
         String data[][] = new String [0][9];
     	String title[] = { "이름", "ID", "비밀번호", "성별", "EMAIL", "전화번호", "주소", "계좌번호", "가입날짜"};
     	DefaultTableModel model = new DefaultTableModel(data, title) {
     		public boolean isCellEditable(int row, int col) {
     			return false;
     		}
     	};
     	String temp[] = new String[9];
         tableCustomer = new JTable(model);
         if(rs.next()) {
        	 temp[0] = rs.getString("pname");
 			temp[1] = rs.getString("pid");
 			temp[2] = rs.getString("ppwd");
 			temp[3] = rs.getString("pgender");
 			temp[4] = rs.getString("pemail");
 			temp[5] = rs.getString("ptel");
 			temp[6] = rs.getString("paddr");
 			temp[7] = rs.getString("paccount");
 			temp[8] = rs.getString("pdate");
 			model.addRow(temp);
         }
         rs.close();
         stmt.close();
         return model;
	}	
	
	public void modifyCustomer(CustomerVO cust) throws Exception{
	
		String sql = "UPDATE customer SET pname = ?, "
				+ "pgender = ?, pemail = ?, ptel = ?, paddr = ? "
				+ "WHERE pid = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cust.getCustName());
		ps.setString(2, cust.getCustGender());
		ps.setString(3, cust.getCustEmail());
		ps.setString(4, cust.getCustTel());
		ps.setString(5, cust.getCustAddr());
		ps.setString(6, cust.getCustId());
		ps.executeUpdate();
		ps.close();
	}
	
	public void deleteCustomer(String name) throws Exception{
		String sql = "delete from customer where pname = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.executeUpdate();
		pstmt.close();
		
	}
	public CustomerVO selectByPk(String id) throws Exception{
		CustomerVO vo = new CustomerVO();
		
		String sql = "SELECT * FROM customer where pid = '"+id+"'";
		
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		if( rs.next() )
		{
			vo.setCustName(rs.getString("PNAME")); 
            vo.setCustId(rs.getString("PID")); 
            vo.setCustPwd(rs.getString("PPWD")); 
            vo.setCustGender(rs.getString("PGENDER")); 
            vo.setCustEmail(rs.getString("PEMAIL")); 
            vo.setCustTel(rs.getString("PTEL"));
            vo.setCustAddr(rs.getString("PADDR"));
            vo.setCustAccount(rs.getString("PACCOUNT"));
            vo.setCustDate(rs.getString("PDATE"));
		}
		
		rs.close();
		stmt.close();
		
		return vo;
	}
	
	
}