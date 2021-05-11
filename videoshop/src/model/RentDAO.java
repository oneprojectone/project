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
		// 1. ����̹��� �޸𸮿� �ε�
		// 2. Connection ������

	}

	public String findCustName( String custTel ) throws Exception
	{


		String custName = null;

		//=========================================
		// 1. sql ���� �����
		//		` �Է¹��� ��ȭ��ȣ�� ������ �˻��϶�
		//		` select custName from customerTab where custTel=�Ѱܹ������ڰ�
		// 2. sql ���� ��ü ������
		// 3. sql �����ϱ�
		// 4. ������� �޾� custName ������ ����
		// 5. sql �ݱ�


		return custName;
	}

	public void videoRent( String custTel, String custName, String videoNum ) throws Exception
	{


		//=========================================
		// 1. sql ���� �����
		//		` rentNum�� �ڵ�������
		//		` ��ȭ��ȣ,����, ������ȣ�� �Է¹޾� �Ѱܹ��� ����Ÿ�� rentTab�� �Է�
		//		` �뿩���� sysdate ( getData() ) �̿�
		//		` �뿩�������� �뿩�� + 3
		//		` �뿩��¥�� �Է����� �ʴ´�
		//		` �뿩���´� ������ true�� �Է�
		//		` �뿩�ݾ��� ������ 500������ ����
		//			( ���߿� ���� ��� ��¥�� ���� �ݾ� ���� )
		//
		//		# MS-SQL
		//			insert into rentTab( rentCustTel, rentCustName, videoNum, rentDate, returnScheduled, returnFlag, rentCharge) values(?, ?, ?, getDate(), getDate()+3, 'true', 500)
		//
		//		# ORACLE
		//			insert into rentTab values( rentNumSeq.nextval,?, ?, ?, sysdate, sysdate+3, 'true', 500)
		//
		// 2. sql ���� ��ü ������
		// 3. sql �����ϱ�
		// 5. sql �ݱ�

		
	}

	public void videoReturn( String videoNum ) throws Exception
	{
		//=========================================
		// 1. sql ���� �����
		//		` �Է¹��� ������ȣ�� rentTab ���̺��� returnFlag �÷��� true�� �����϶�
		//		` update rentTab set returnFlag='false', returndate=sysdate where videoNum=�Ѱܹ������ڰ� AND	 returnFlag='true'
		//
		// 2. sql ���� ��ü ������
		// 3. sql �����ϱ�
		// 4. sql �ݱ�

	}

	public Vector recentList() throws Exception
	{
		Vector list = new Vector();

		//=========================================
		// 1. sql ���� �����
		//		` �������̺�� �뿩���̺��� �����Ͽ� ��������, ����, ���, �ݳ�������, �ݳ����θ� �˻��Ͽ� �ֱ� ��� �� 10�� �̳��� �˻��϶�
		//		# oracle
		//		` select v.videoTitle, v.videoDirector, v.videoActor, r.returnScheduled, r.returnFlag from rentTab r , videoTab v   where r.videoNum(+)=v.videoNum and rownum<=10 order by v.videoNum desc;
		//		# MS-SQL
		//		` select top 10 v.videoTitle, v.videoDirector, v.videoActor, r.returnScheduled, r.returnFlag from rentTab r right outer join videoTab v on r.videoNum=v.videoNum order by v.videoNum desc;
		// 2. sql ���� ��ü ������
		// 3. sql �����ϱ�
		// 4. ������� �޾� Vector ������ ����
		// 5. sql �ݱ�


		return list;
	}

};