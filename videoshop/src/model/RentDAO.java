package model;

import java.sql.*;
import java.util.Vector;
import java.util.Date;

public class RentDAO
{
	Connection con;

	public RentDAO() throws Exception
	{
		//=========================================
		// 1. 드라이버를 메모리에 로딩
		// 2. Connection 얻어오기

	}

	public String findCustName( String custTel ) throws Exception
	{


		String custName = null;

		//=========================================
		// 1. sql 쿼리 만들기
		//		` 입력받은 전화번호의 고객명을 검색하라
		//		` select custName from customerTab where custTel=넘겨받은인자값
		// 2. sql 전송 객체 얻어오기
		// 3. sql 전송하기
		// 4. 결과값을 받아 custName 변수에 지정
		// 5. sql 닫기


		return custName;
	}

	public void videoRent( String custTel, String custName, String videoNum ) throws Exception
	{


		//=========================================
		// 1. sql 쿼리 만들기
		//		` rentNum은 자동증가수
		//		` 전화번호,고객명, 비디오번호은 입력받아 넘겨받은 데이타로 rentTab에 입력
		//		` 대여일은 sysdate ( getData() ) 이용
		//		` 대여예정일은 대여일 + 3
		//		` 대여날짜는 입력하지 않는다
		//		` 대여상태는 무조건 true로 입력
		//		` 대여금액은 지금은 500원으로 지정
		//			( 나중에 비디오 등록 날짜에 따라 금액 산정 )
		//
		//		# MS-SQL
		//			insert into rentTab( rentCustTel, rentCustName, videoNum, rentDate, returnScheduled, returnFlag, rentCharge) values(?, ?, ?, getDate(), getDate()+3, 'true', 500)
		//
		//		# ORACLE
		//			insert into rentTab values( rentNumSeq.nextval,?, ?, ?, sysdate, sysdate+3, 'true', 500)
		//
		// 2. sql 전송 객체 얻어오기
		// 3. sql 전송하기
		// 5. sql 닫기

		
	}

	public void videoReturn( String videoNum ) throws Exception
	{
		//=========================================
		// 1. sql 쿼리 만들기
		//		` 입력받은 비디오번호로 rentTab 테이블의 returnFlag 컬럼을 true로 변경하라
		//		` update rentTab set returnFlag='false', returndate=sysdate where videoNum=넘겨받은인자값 AND	 returnFlag='true'
		//
		// 2. sql 전송 객체 얻어오기
		// 3. sql 전송하기
		// 4. sql 닫기

	}

	public Vector recentList() throws Exception
	{
		Vector list = new Vector();

		//=========================================
		// 1. sql 쿼리 만들기
		//		` 비디오테이블과 대여테이블에서 조인하여 비디오제목, 감독, 배우, 반납예정일, 반납여부를 검색하여 최근 목록 중 10개 이내로 검색하라
		//		# oracle
		//		` select v.videoTitle, v.videoDirector, v.videoActor, r.returnScheduled, r.returnFlag from rentTab r , videoTab v   where r.videoNum(+)=v.videoNum and rownum<=10 order by v.videoNum desc;
		//		# MS-SQL
		//		` select top 10 v.videoTitle, v.videoDirector, v.videoActor, r.returnScheduled, r.returnFlag from rentTab r right outer join videoTab v on r.videoNum=v.videoNum order by v.videoNum desc;
		// 2. sql 전송 객체 얻어오기
		// 3. sql 전송하기
		// 4. 결과값을 받아 Vector 변수에 지정
		// 5. sql 닫기


		return list;
	}

};