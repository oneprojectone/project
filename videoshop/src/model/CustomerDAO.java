package	  model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.rec.CustomerVO;

public class  CustomerDAO
{
		//	member field
		private		String		custTel;
		private		String		custName;
		private		String		custTelAid;
		private		String		custAddr;
		private		String		custEmail;


		//  getter	method
		public	 String	 getCustTel()		{			return custTel;			}
		public	 String	 getCustName()		{			return custName;		}
		public	 String	 getCustTelAid()	{			return custTelAid;		}
		public	 String	 getCustAddr()		{			return custAddr;	 	}
		public	 String	 getCustEmail()		{			return custEmail;		}

		// settern method
		public void setCustTel	(	String tel		)	{	custTel		= tel;		}
		public void setCustName	(	String name		)	{	custName	= name;		}
		public void setCustTelAid(	String telAid	)	{	custTelAid	= telAid;	}
		public void setCustAddr	(	String addr		)	{	custAddr	= addr;		}
		public void setCustEmail(	String email	)	{	custEmail	= email;	}


		//  constructor
		public	CustomerDAO() throws Exception{
			connectDB();
		}

		//###########################################################
		//	DB  control method
		Connection				con;
		String url= "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String pass = "tiger";
		PreparedStatement pstmt = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		void  connectDB() throws Exception {
				/*
						1. 드라이버를 드라이버메니저에 등록
						2. 연결 객체 얻어오기
				*/
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pass);
		}

		// 회원가입 메소드
		public  void regist(CustomerVO cust)   {
				/*
						1.  sql 작성하기		( insert 문 작성 : 멤버필드를 변수로 지정하여 )
						2.  sql  전송객체 얻어오기	( PreparedStatement가 더 적합할 듯 )
						3.  sql  전송			( 전송전에 ?로 지정 )
						4.  닫기				( Connection은 닫으면 안됨 )
				*/
			try {
			  String name = cust.getCustName();
	    	  String tel = cust.getCustTel();
	    	  String addtel = cust.getCustTelAid();
	    	  String addr = cust.getCustAddr();
	    	  String email = cust.getCustEmail();
			
			String sql = "insert into customertab(custname, custtel, custtelaid, custaddr, custemail) values(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			pstmt.setString(3, addtel);
			pstmt.setString(4, addr);
			pstmt.setString(5, email);
			
			pstmt.executeUpdate();
			pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}


		public	void		modify() throws Exception{
				/*
						1.  sql 작성하기		( update 문 작성 : 멤버필드를 변수로 지정하여 )
						2.  sql  전송객체 얻어오기	( PreparedStatement가 더 적합할 듯 )
						3.  sql  전송			( 전송전에 ?로 지정 )
						4.  닫기				( Connection은 닫으면 안됨 )
				*/
			

		}


		public  void		searchName(	 String  name )  throws Exception {
				/*
						1.  sql 작성하기	 ( select 문 : 함수의 인자로 넘어온 이름과 같은 조건의 레코드 검색 )
						2.  sql  전송객체 얻어오기	( Statement / PreparedStatement 모두 적합 )
						3.  sql  전송		 ( ResultSet 의 데이타를 얻어서 멤버 필드에 지정 )
						4.  닫기			 ( Connection은 닫으면 안됨 )

						#   생각해 보기
							- 동명 이인이 여러명 인 경우는 어떻게 처리할까?
				*/

		}


		public CustomerVO searchTel(  String   tel )  throws Exception  {
				/*
						1.  sql 작성하기	 ( select 문 : 함수의 인자로 넘어온 전화번호과 같은 조건의 레코드 검색 )
						2.  sql  전송객체 얻어오기	( Statement / PreparedStatement 모두 적합 )
						3.  sql  전송		 ( ResultSet 의 데이타를 얻어서 멤버 필드에 지정 )
						4.  닫기			 ( Connection은 닫으면 안됨 )
				*/
			   String sql = "SELECT * FROM customertab where custtel = ?";
		         //System.out.println(tel);
		         PreparedStatement ps = con.prepareStatement(sql);
		         ps.setString(1, tel);
		         //5. 전송하기
		         ResultSet rs = ps.executeQuery();
		         CustomerVO vo = new CustomerVO();
		      if (rs.next()){
		            
		            vo.setCustName(rs.getString("CUSTNAME")); 
		            vo.setCustTel(rs.getString("CUSTTEL"));
		            vo.setCustTelAid(rs.getString("CUSTTELaid"));
		            vo.setCustAddr(rs.getString("CUSTADDR"));
		            vo.setCustEmail(rs.getString("CUSTEMAIL"));
		            
		         }
		         //6. 닫기
		         rs.close();
		         ps.close();

		         return vo;
		}
}
