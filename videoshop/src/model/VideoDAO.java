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
			������ �Լ� 	- DB ����
			1. ����̹��� �ε�
			2. Connection ������
		*/

	}

	public void videoInsert( int count ) throws Exception
	{
		/*============================================
		 ���� ���� �Է�

			1.  sql �ۼ��ϱ�		( insert �� �ۼ� : ����ʵ带 ������ �����Ͽ� )
				[ MS-SQL ]
				INSERT INTO videoTab( videoJanre,videoTitle, videoActor, videoDirector,vodeoContent) values( ?, ?, ?, ?, ?, getDate() )

				[ ORACLE ]
				INSERT INTO videoTab values( videoNumSeq.nextval, ?, ?, ?, ?, ?, sysdate )

			2.  sql  ���۰�ü ������	( PreparedStatement�� �� ������ �� )
			3.  sql  ����			( �������� ?�� ���� )
			##	count ��ŭ �ݺ�
			4.  �ݱ�				( Connection�� ������ �ȵ� )
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
		���� �˻��ϱ�

			1.  sql �ۼ��ϱ�			( select �� �ۼ� : ���� �����Ͽ� )
				# SELECT videoNum, videoJanre, videoTitle, videoDirector, videoActor, videoRegDate FROM videoTab WHERE key�� like '%word��%'

			2.  sql  ���۰�ü ������	( Statement������ )
			3.  sql  ����				( executeQuery() �̿� )
										( ��� �޾� Vector �� ���� )
			4.  �ݱ�				( Connection�� ������ �ȵ� )
		*/
		Vector  list	= new Vector();




		return list;
	}

	public void findByNum( String vnum ) throws Exception{
		/*===================================
		�ش��ϴ� ������ȣ�� ���� ���� �˻��ϱ�

			1.  sql �ۼ��ϱ�			( select �� �ۼ� : ���� �����Ͽ� )
				# SELECT * FROM videoTab WHERE videoNum=?

			2.  sql  ���۰�ü ������	
			3.  sql  ����				( executeQuery() �̿� )
										( ��� �޾� ����ʵ忡 ���� )
			4.  �ݱ�				( Connection�� ������ �ȵ� )

		*/
	}
}
