package main;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import history.HistoryDao;
import history.HistoryDto;

public class Order extends JFrame implements ActionListener{
   JPanel panWest;
   JPanel panSouth;
   JPanel p1, p2, p3, p4, p5;
   JTextField txt1, txt2, txt3, txt4, txt5 ;
   JButton btn1, btn2, btn3, btn4, btn5;
    
   HistoryDao dao = new HistoryDao();
   HistoryDto dto = new HistoryDto();
   
   Object ob[][]=new Object[0][7];
   DefaultTableModel model;
   JTable table;
   JScrollPane js;
   String str[]= {"NO", "ID", "MENU", "SIZE", "OPTION", "PRICE", "DATE"};
   
   Connection con=null;
   PreparedStatement pstmt=null; 
   ResultSet rs=null;
   
   public Order() {
     super("구매 내역");
     
     
      panWest = new JPanel(new GridLayout(10,0));
      p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      p1.add(new JLabel("구매일"));
      p1.add(txt1 = new JTextField(12));
      panWest.add(p1);
      p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      p2.add(new JLabel("MENU"));
      p2.add(txt2 = new JTextField(12));
      panWest.add(p2);
      p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      p3.add(new JLabel("SIZE"));
      p3.add(txt3 = new JTextField(12));
      panWest.add(p3);
      p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      p4.add(new JLabel("OPTION"));
      p4.add(txt4 = new JTextField(12));
      panWest.add(p4);
      p5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      p5.add(new JLabel("PRICE"));
      p5.add(txt5 = new JTextField(12));
      panWest.add(p5);
      add(panWest, "West");
      
      
      
      
      
      panSouth = new JPanel();
      panSouth.add(btn1 = new JButton("전체 검색"));
      panSouth.add(btn2 = new JButton("검      색"));
      panSouth.add(btn3 = new JButton("수      정"));
      panSouth.add(btn4 = new JButton("삭      제"));
      panSouth.add(btn4 = new JButton("결      제"));
      add(panSouth, "South");
      model=new DefaultTableModel(ob, str);
      table=new JTable(model); 
     

      js=new JScrollPane(table);
      this.add("Center", js);
      setBounds(250, 250, 800, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
      
      
      this.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
              try { 
                  if(rs!=null) rs.close();
                  if(pstmt!=null) pstmt.close();
                  if(con!=null) con.close();                    
              }catch(Exception e2) {
                  System.exit(0);
              }
          }
      });
      
   }
   
   public static void main(String[] args) {
      new Order();
   }
 

@Override
public void actionPerformed(ActionEvent e) {
   
}
}