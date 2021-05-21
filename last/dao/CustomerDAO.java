package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dbConnect.Singleton;
import dto.CouponVO;
import dto.CustomerVO;
import view.CustomerMyView;

public class CustomerDAO {
	Connection con=null; 	PreparedStatement pstmt; 	
	ResultSet rs; 		Statement stmt;
	String sql;
	JTable		tableCustomer;
	
	public	CustomerDAO() {
		connectDB();
	}
	
	void connectDB() {
		try {
			con = Singleton.getConnection();
	} catch (Exception e) { e.printStackTrace();}
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
	public DefaultTableModel searchName(DefaultTableModel model, String name) {
		
		try {
	
	   String sql = "SELECT * FROM customer where pname like '%"+name+"%'";
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);
         CustomerVO vo = new CustomerVO();
         String data[][] = new String [0][9];
     	String title[] = { "이름", "ID", "비밀번호", "성별", "EMAIL", "전화번호", "주소", "계좌번호", "가입날짜"};
     	for (int i = 0; i < model.getRowCount();) {
            model.removeRow(0);
         }
     	
     	String temp[] = new String[9];
         tableCustomer = new JTable(model);
         while(rs.next()) {
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
		}catch(Exception e) {
			e.printStackTrace();
			
		}
     
         return model;
	}	
	
	//5-18 12:50 수정
	public void modifyCustomer(CustomerVO cust) {
	      
	      try {
	   connectDB();
	      String sql = "UPDATE customer SET pname = ?, "
	            + "ppwd = ?, pemail = ?, ptel = ?, paddr = ? "
	            + "WHERE pid = ?";
	      PreparedStatement ps = con.prepareStatement(sql);
	      ps.setString(1, cust.getPname());
	      ps.setString(2, cust.getPpwd());
	      ps.setString(3, cust.getPemail());
	      ps.setString(4, cust.getPtel());
	      ps.setString(5, cust.getPaddr());
	      ps.setString(6, cust.getPid());
	      ps.executeUpdate();
	      ps.close();
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	public void deleteCustomer(String id) throws Exception{
		connectDB();
		String sql = "delete from customer where pid = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.executeUpdate();
		pstmt.close();
		
		
		 sql="UPDATE HISTORY SET HID = '탈퇴한회원' WHERE HID = '"+id+"'";
		 pstmt=con.prepareStatement(sql);
		 pstmt.executeUpdate();
		
	}
	public CustomerVO selectByPk(String id) {
		CustomerVO vo = new CustomerVO();
		
		try {
		connectDB();
		
		String sql = "SELECT * FROM customer where pid = '"+id+"'";
		
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		if( rs.next() )
		{
			vo.setPname(rs.getString("PNAME")); 
            vo.setPid(rs.getString("PID")); 
            vo.setPpwd(rs.getString("PPWD")); 
            vo.setPgender(rs.getString("PGENDER")); 
            vo.setPemail(rs.getString("PEMAIL")); 
            vo.setPtel(rs.getString("PTEL"));
            vo.setPaddr(rs.getString("PADDR"));
            vo.setPaccount(rs.getString("PACCOUNT"));
            vo.setPdate(rs.getString("PDATE"));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		
		return vo;
	}
	
	
	
	public void showMyInfo(String id,CustomerVO dto){
		try {
			
			connectDB();
			sql="SELECT * FROM CUSTOMER WHERE PID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				dto.setPname(rs.getString("PNAME"));
				dto.setPgender(rs.getString("PGENDER"));
				dto.setPid(rs.getString("PID"));
				dto.setPpwd(rs.getString("PPWD"));
				dto.setPtel(rs.getString("PTEL"));
				dto.setPemail(rs.getString("PEMAIL"));
				dto.setPaddr(rs.getString("PADDR"));
				dto.setPaccount(rs.getString("PACCOUNT"));
				dto.setPdate(rs.getString("PDATE"));
			
			
			}
			
		}catch(Exception e) {
			System.out.println("회원 정보를 불러올 수 없습니다.");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			
			}catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	 
	public void modifyMyInfo(CustomerVO dto) {
		 try {
			 connectDB();
		sql="UPDATE CUSTOMER SET PNAME= ?, PPWD = ?, PGENDER = ?, PTEL = ?, PEMAIL = ?, PADDR = ?, PACCOUNT = ? WHERE PID = ?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dto.getPname());
		pstmt.setString(2, dto.getPpwd());
		pstmt.setString(3, dto.getPgender());
		pstmt.setString(4, dto.getPtel());
		pstmt.setString(5, dto.getPemail());
		pstmt.setString(6, dto.getPaddr());
		pstmt.setString(7, dto.getPaccount());
		pstmt.setString(8, dto.getPid());
		
		int count=pstmt.executeUpdate();
		System.out.println("정보수정 성공!");
		showMyInfo(dto.getPid(),dto);
		
		
		
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
				try {
					rs.close();
				
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
	 }
	 
	 public void deleteMyInfo(String id) {
		 try {
			 connectDB();
				sql="DELETE FROM CUSTOMER WHERE PID = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				
				 sql="UPDATE HISTORY SET HID = '탈퇴한회원' WHERE HID = '"+id+"'";
				 pstmt=con.prepareStatement(sql);
				 pstmt.executeUpdate();
				
				 }catch(Exception e) {
					 e.printStackTrace();
				 }finally {
						try {
							rs.close();
						
						}catch (SQLException se) {
							se.printStackTrace();
						}
					}
	 }
	 

	 public void couponCount(String month,String id) {
		 try {
			 sql="UPDATE COUPON SET "+month+" = 1 WHERE PID = '"+id+"'";
			 pstmt=con.prepareStatement(sql);
			 pstmt.executeUpdate();
			 System.out.println("업데이트");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void checkCoupon(CustomerMyView myview,CouponVO couponvo) {
		 try {
				//connectDB();
				System.out.println(myview.myid);
				sql="SELECT * FROM COUPON WHERE PID= '"+myview.myid+"'";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				//ArrayList list = new ArrayList();
				while(rs.next()) {
					couponvo.setJan(rs.getInt("JAN"));
					couponvo.setFeb(rs.getInt("FAB"));
					couponvo.setMar(rs.getInt("MAR"));
					couponvo.setApr(rs.getInt("APR"));
					couponvo.setMay(rs.getInt("MAY"));
					couponvo.setJun(rs.getInt("JUN"));
					couponvo.setJul(rs.getInt("JUL"));
					couponvo.setAug(rs.getInt("AUG"));
					couponvo.setSep(rs.getInt("SEP"));
					couponvo.setOct(rs.getInt("OCT"));
					couponvo.setNov(rs.getInt("NOV"));
					couponvo.setDec(rs.getInt("DEC"));
					
				}
				if(couponvo.getJan()==1) {
					myview.button1m.setEnabled(false);
				}
				if(couponvo.getFeb()==1) {
					myview.button2m.setEnabled(false);
				}
				if(couponvo.getMar()==1) {
					myview.button3m.setEnabled(false);
				}
				if(couponvo.getApr()==1) {
					myview.button4m.setEnabled(false);
				}
				if(couponvo.getMay()==1) {
					myview.button5m.setEnabled(false);
				}
				if(couponvo.getJun()==1) {
					myview.button6m.setEnabled(false);
				}
				if(couponvo.getJul()==1) {
					myview.button7m.setEnabled(false);
				}
				if(couponvo.getAug()==1) {
					myview.button8m.setEnabled(false);
				}
				if(couponvo.getSep()==1) {
					myview.button9m.setEnabled(false);
				}
				if(couponvo.getOct()==1) {
					myview.button10m.setEnabled(false);
				}
				if(couponvo.getNov()==1) {
					myview.button11m.setEnabled(false);
				}
				if(couponvo.getDec()==1) {
					myview.button12m.setEnabled(false);
				}
				
			}catch(Exception e) {
				System.out.println("회원 정보를 불러올 수 없습니다.");
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
				
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
	 }
	 
	 public int countHistory(CustomerMyView myview) {
		 int count=0;
		 try {
		 sql="SELECT COUNT(*) FROM HISTORY WHERE HID= '"+myview.myid+"'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
			count=rs.getInt("COUNT(*)");}
			if(!(count==0)) {
				if(count%10==1) {
				myview.lab12.setIcon(myview.image1);}
				else if(count%10==2) {
					myview.lab12.setIcon(myview.image2);}
				else if(count%10==3) {
					myview.lab12.setIcon(myview.image3);}
				else if(count%10==4) {
					myview.lab12.setIcon(myview.image4);}
				else if(count%10==5) {
					myview.lab12.setIcon(myview.image5);}
				else if(count%10==6) {
					myview.lab12.setIcon(myview.image6);}
				else if(count%10==7 ) {
					myview.lab12.setIcon(myview.image7);}
				else if(count%10==8) {
					myview.lab12.setIcon(myview.image8);}
				else if(count%10==9) {
					myview.lab12.setIcon(myview.image9);}
				else if(count%10==0) {
					myview.lab12.setIcon(myview.image10);}
			}
			else {
				myview.	lab12.setIcon(myview.image0);
			}
			System.out.println(count);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 return count;
		 
	 }
//21.05.19 스탬프 부분 구현	추가(김수정) 
	 public int checkStamp(CustomerMyView myview) {
		 int count=0;
		 int count_use5=0;
		 int count_use10=0;
		 int count_5=0;
		 int count_10=0;
		 try {
			 sql="SELECT COUNT(*) FROM HISTORY WHERE HID= '"+myview.myid+"'";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
				count=rs.getInt("COUNT(*)");}
				
				 sql="SELECT BUTTON_5_COUNT, BUTTON_10_COUNT FROM COUPON WHERE PID= '"+myview.myid+"'";
					pstmt=con.prepareStatement(sql);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						count_use5=rs.getInt("BUTTON_5_COUNT");
						count_use10=rs.getInt("BUTTON_10_COUNT");
						}
										
				if(count>0) {
					
					count_5=(count/5)-(count/10)-(count_use5);
					System.out.println(count_5);
					if(count_5>=1) {
						myview.buttoncoupon_5.setEnabled(true);
					}
					
					count_10=(count/10)-(count_use10);
					System.out.println(count_10);
					if(count_10>=1) {
						myview.buttoncoupon_10.setEnabled(true);
					}
					if(count_5==0) {
						myview.buttoncoupon_5.setEnabled(false);
					}
					if(count_10==0) {
						myview.buttoncoupon_10.setEnabled(false);
					}
				}
				
				myview.lab16.setText("남은 아메리카노 쿠폰 : "+count_5);
				myview.lab17.setText("남은 원하는 음료 쿠폰 : "+count_10);
				
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return count;
	 }
	 
	 public void useCoupon5(CustomerMyView myview) {
		 int count_5=0;
		 
		   try {
			   connectDB();
			   
			   sql="SELECT BUTTON_5_COUNT FROM COUPON WHERE PID= '"+myview.myid+"'";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					count_5=rs.getInt("BUTTON_5_COUNT");
					}
				System.out.println(count_5);
				
			   sql = "UPDATE COUPON SET BUTTON_5_COUNT = ? "
			            + "WHERE PID = ?";
			      PreparedStatement ps = con.prepareStatement(sql);
			      count_5++;
			      ps.setInt(1, count_5);
			      ps.setString(2, myview.myid);
			      ps.executeUpdate();
			      ps.close();
			      }catch (Exception e) {
			         e.printStackTrace();
			      }
	 }


	public void useCoupon10(CustomerMyView myview) {
		 int count_10=0;
		   try {
			   connectDB();
			   
			   sql="SELECT BUTTON_10_COUNT FROM COUPON WHERE PID= '"+myview.myid+"'";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					count_10=rs.getInt("BUTTON_10_COUNT");
					}
				
			   sql = "UPDATE COUPON SET BUTTON_10_COUNT = ? "
			            + "WHERE PID = ?";
			      PreparedStatement ps = con.prepareStatement(sql);
			      count_10++;
			      ps.setInt(1, count_10);
			      ps.setString(2, myview.myid);
			      ps.executeUpdate();
			      ps.close();
			      }catch (Exception e) {
			         e.printStackTrace();
			      }
	  }
	
	 
	 
}