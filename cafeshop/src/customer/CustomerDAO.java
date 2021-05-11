package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import customer.rec.CustomerVO;

public class CustomerDAO {

	private	String custName;
	private	String custId;
	private	String custPwd;
	private	String custGender;
	private	String custEmail;
	private	String custTel;
	private	String custAddr;
	private	String custAccount;
	private	String custDate;
	
	public String getCustName() { return custName; }
	public String getCustId() { return custId; }
	public String getCustPwd() { return custPwd; }
	public String getCustGender() { return custGender; }
	public String getCustEmail() { return custEmail; }
	public String getCustTel() { return custTel; }
	public String getCustAddr() { return custAddr; }
	public String getCustAccount() { return custAccount; }
	public String getCustDate() { return custDate; }
	
	public void setCustName(String name) { custName = name; }
	public void setCustId(String id) { custId = id; }
	public void setCustPwd(String pwd) { custPwd = pwd; }
	public void setCustGender(String gender) { custGender = gender; }
	public void setCustEmail(String email) { custEmail = email; }
	public void setCustTel(String tel) { custTel = tel; }
	public void setCustAddr(String addr) { custAddr = addr; }
	public void setCustAccount(String account) { custAccount = account; }
	public void setCustDate(String date) { custDate = date; }
	
	public	CustomerDAO() throws Exception{
		connectDB();
	}
	
	Connection con;
	String url= "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "scott";
	String pass = "tiger";
	PreparedStatement pstmt = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	void connectDB() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url,user,pass);
	}
	
	public CustomerVO searchName(String name) throws Exception{
		
	   String sql = "SELECT * FROM customer where custname = ?";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, name);
         ResultSet rs = ps.executeQuery();
         CustomerVO vo = new CustomerVO();
         
         if(rs.next()) {
            vo.setCustName(rs.getString("CUSTNAME")); 
            vo.setCustId(rs.getString("CUSTID")); 
            vo.setCustPwd(rs.getString("CUSTPWD")); 
            vo.setCustGender(rs.getString("CUSTGENDER")); 
            vo.setCustEmail(rs.getString("CUSTEMAIL")); 
            vo.setCustTel(rs.getString("CUSTTEL"));
            vo.setCustAddr(rs.getString("CUSTADDR"));
            vo.setCustAccount(rs.getString("CUSTACCOUNT"));
            vo.setCustDate(rs.getString("CUSTDATE"));
         }
         rs.close();
         ps.close();
         return vo;
	}	
	
	public void modify(CustomerVO cust) throws Exception{
	
		String sql = "UPDATE customer SET custname = ?, custid = ?, custpwd = ?, "
				+ "custgender = ?, custemail = ?, custtel = ?, custaddr = ?, "
				+ "custAccount = ?, custdate = ? WHERE custtel = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cust.getCustName());
		ps.setString(2, cust.getCustId());
		ps.setString(3, cust.getCustPwd());
		ps.setString(4, cust.getCustGender());
		ps.setString(5, cust.getCustEmail());
		ps.setString(6, cust.getCustTel());
		ps.setString(7, cust.getCustAddr());
		ps.setString(8, cust.getCustAccount());
		ps.setString(9, cust.getCustDate());
		ps.executeUpdate();
		ps.close();
	}
	
	public void delete(String name) throws Exception{
		
	}
	
	
	
}