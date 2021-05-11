package model;

import java.sql.*;
import java.util.Vector;

public class VideoDAO
{
	// member field
	int videoNum;
	String videoJanre;
	String videoTitle;	
	String videoDirector;	
	String videoActor;	
	String videoContent;
	String videoRegDate;

	// setter
	public void setVideoJanre	( String janre )	{		videoJanre		= janre;		}
	public void setVideoTitle	( String title )	{		videoTitle		= title;		}
	public void setVideoDirector( String director ) {		videoDirector	= director;		}
	public void setVideoActor	( String actor )	{		videoActor		= actor;		}
	public void setVideoContent	( String content )	{		videoContent	= content;		}

	// getter
	public int		getVideoNum()		{	return videoNum;		}
	public String	getVideoJanre()		{	return videoJanre;		}
	public String	getVideoTitle()		{	return videoTitle;		}
	public String	getVideoDirector()	{	return videoDirector;	}
	public String	getVideoActor()		{	return videoActor;		}
	public String	getVideoContent()	{	return videoContent;	}
	public String	getVideoRegDate()	{	return videoRegDate;	}

	
	//############################################

	Connection con;


	public VideoDAO() throws Exception
	{
		/*============================================
			생성자 함수 	- DB 연결
			1. 드라이버를 로딩
			2. Connection 얻어오기
		*/

	}

	public void videoInsert( int count ) throws Exception
	{
		/*============================================
		 비디오 정보 입력

			1.  sql 작성하기		( insert 문 작성 : 멤버필드를 변수로 지정하여 )
				[ MS-SQL ]
				INSERT INTO videoTab( videoJanre,videoTitle, videoActor, videoDirector,vodeoContent) values( ?, ?, ?, ?, ?, getDate() )

				[ ORACLE ]
				INSERT INTO videoTab values( videoNumSeq.nextval, ?, ?, ?, ?, ?, sysdate )

			2.  sql  전송객체 얻어오기	( PreparedStatement가 더 적합할 듯 )
			3.  sql  전송			( 전송전에 ?로 지정 )
			##	count 만큼 반복
			4.  닫기				( Connection은 닫으면 안됨 )
		*/
		
	}

	public void videoModify() throws Exception
	{

	}

	public void videoDelete() throws Exception
	{

	}

	public Vector videoSearch( String key, String word ) throws Exception
	{
		/*============================================
		비디오 검색하기

			1.  sql 작성하기			( select 문 작성 : 조건 지정하여 )
				# SELECT videoNum, videoJanre, videoTitle, videoDirector, videoActor, videoRegDate FROM videoTab WHERE key값 like '%word값%'

			2.  sql  전송객체 얻어오기	( Statement여야함 )
			3.  sql  전송				( executeQuery() 이용 )
										( 결과 받아 Vector 에 지정 )
			4.  닫기				( Connection은 닫으면 안됨 )
		*/
		Vector  list	= new Vector();




		return list;
	}

	public void findByNum( String vnum ) throws Exception{
		/*===================================
		해당하는 비디오번호에 의한 비디오 검색하기

			1.  sql 작성하기			( select 문 작성 : 조건 지정하여 )
				# SELECT * FROM videoTab WHERE videoNum=?

			2.  sql  전송객체 얻어오기	
			3.  sql  전송				( executeQuery() 이용 )
										( 결과 받아 멤버필드에 지정 )
			4.  닫기				( Connection은 닫으면 안됨 )

		*/
	}
}
