package  view;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

import model.rec.RentVO;

public class RentView extends JPanel
{
   JTextField   tfRentTel, tfRentCustName, tfRentVideoNum;
   JButton      bRent;

   JTextField   tfReturnVideoNum;
   JButton      bReturn;

   RecentListTableModel model;
   JTable      tableRecentList;

   //==============================================
   //    생성자 함수
   public RentView(){
      newObject();
      addLayout();
   }

   //==============================================
   //   멤버 변수를 객체 생성
   void newObject(){
      tfRentTel      = new JTextField( 15 );
      tfRentCustName   = new JTextField( 15 );
      tfRentVideoNum   = new JTextField( 15 );

      tfReturnVideoNum= new JTextField( 15 );

      bRent         = new JButton("   대   여   ");
      bReturn         = new JButton("   반   납   ");

      
      model         = new RecentListTableModel();
      tableRecentList   = new JTable( model );

   }

   //==============================================
   //   GUI 구성을 위해 Layout 지정하여 붙이기
   void addLayout(){
      // 위에 대여 , 반납 영역
      JPanel   pNorth   = new JPanel();
         // 대여부분
         JPanel   pNorthLeft   = new JPanel();
         pNorthLeft.setBorder( new TitledBorder(" 대      여   ") );
         pNorthLeft.setLayout( new GridLayout( 4, 2) );
         pNorthLeft.add( new JLabel(" 전 화 번 호 ") );
         pNorthLeft.add( tfRentTel );
         pNorthLeft.add( new JLabel(" 고  객   명 ") );
         pNorthLeft.add( tfRentCustName );
         pNorthLeft.add( new JLabel(" 비디오 번호 ") );
         pNorthLeft.add( tfRentVideoNum );
         pNorthLeft.add( bRent );


         // 반납부분
         JPanel   pNorthRight   = new JPanel();
         pNorthRight.setBorder( new TitledBorder(" 반   납   ") );
         pNorthRight.add( new JLabel(" 비디오 번호 ") );
         pNorthRight.add( tfReturnVideoNum );
         pNorthRight.add( bReturn );

      pNorth.setLayout( new GridLayout( 1, 2) );
      pNorth.add( pNorthLeft );
      pNorth.add( pNorthRight );


      // 아래 최신 비디오 목록 영역
      JPanel   pCenter = new JPanel();
      pCenter.setLayout( new BorderLayout() );
      pCenter.add("Center", new JScrollPane( tableRecentList ) );

      //
      // 전체 영역 붙이기
      setLayout( new BorderLayout() );
      add("Center", pCenter);
      add("North", pNorth );

   }

};

class RecentListTableModel extends AbstractTableModel { 
  
Vector data = new Vector();
String [] columnNames = { "비디오제목","감    독", "배   우", "반납예정일", "반납여부" };

//=============================================================
// 1. 기본적인 TabelModel  만들기
// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
// AbstractTabelModel에서 구현되지 않았기에...
// 반드시 사용자 구현 필수!!!!

    public int getColumnCount() { 
        return columnNames.length; 
    } 
     
    public int getRowCount() { 
        return data.size(); 
    } 

    public Object getValueAt(int row, int col) { 
      Vector temp = (Vector)data.elementAt( row );
        return temp.elementAt( col ); 
    }

//===============================================================
// 2. 지정된 컬럼명으로 변환하기
//
//      기본적으로 A, B, C, D 라는 이름으로 컬럼명이 지정된다
    public String getColumnName(int col) { 
        return columnNames[col]; 
    } 

}