package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import dao.CustomerDAO;
import dto.CouponVO;
import dto.CustomerVO;

public class CustomerMyView extends JPanel implements ActionListener,ItemListener
{
   public JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8,lab9,lab10,lab11,lab12,lab13,lab14,lab15,lab16,lab17;
   public ImageIcon imagem,imagef,imagecoffee, image0,image1, image2, image3,image4,image5,image6,image7,image8,image9,image10;
   
   
   JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9;
   JPanel pan1,pan2,pan3,pan4,pan5,pan6,pan7,pan8,pan9,pan10,pan11,pan12,pan13,pan14;
   public JButton button1, button2, button3, button4,button1m,button2m,button3m,button4m,button5m,button6m,button7m,button8m,button9m,button10m,button11m,button12m,buttoncoupon_5,buttoncoupon_10;
   CustomerDAO dao=new CustomerDAO();
   CustomerVO dto = new CustomerVO();
   JRadioButton male, female;
   JComboBox account;
   JScrollPane jsp, jspcoupon;
   ImageIcon image1m,image2m,image3m,image4m,image5m,image6m,image7m,image8m,image9m,image10m,image11m,image12m,image1_1m,image2_1m,image3_1m,image4_1m,image5_1m,image6_1m,image7_1m,image8_1m,image9_1m,image10_1m,image11_1m,image12_1m;
   public String myid;
   CouponVO coupondao =new CouponVO();
   Calendar cal =Calendar.getInstance();
   
   int count;
   
   
   public  CustomerMyView(String id) {
      
      Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
      myid=id;
      pan1=new JPanel();
      pan2=new JPanel();
      pan3=new JPanel();
      pan4=new JPanel();
      pan5=new JPanel();
      pan6=new JPanel();
      pan7=new JPanel();
      pan8=new JPanel();
      pan9=new JPanel();
      pan10=new JPanel();
      pan11=new JPanel();
      pan12=new JPanel();
      pan13=new JPanel(); 
      pan14=new JPanel(); 
      
      imagem = new ImageIcon("m.png");
      imagef = new ImageIcon("f.png");
      imagecoffee = new ImageIcon("jojo1.png");
      image0 = new ImageIcon("0.png");
      image1 = new ImageIcon("1.png");
      image2 = new ImageIcon("2.png");
      image3 = new ImageIcon("3.png");
      image4 = new ImageIcon("4.png");
      image5 = new ImageIcon("5.png");
      image6 = new ImageIcon("6.png");
      image7 = new ImageIcon("7.png");
      image8 = new ImageIcon("8.png");
      image9 = new ImageIcon("9.png");
      image10 = new ImageIcon("10.png");
      
      male=new JRadioButton("남자");
      female= new JRadioButton("여자");
   		male.setEnabled(false);
   		female.setEnabled(false);
      ButtonGroup mf = new ButtonGroup();
      mf.add(male);
      mf.add(female);
      male.addItemListener(this);
      female.addItemListener(this);
      String   accountText[]= {"선택", "KOSMO", "HAPPY" , "SINNAH", "GMOD" };
      account= new JComboBox(accountText);
      account.addItemListener(this);
      
      image1m=new ImageIcon("1m.png");
      image2m=new ImageIcon("2m.png");
      image3m=new ImageIcon("3m.png");
      image4m=new ImageIcon("4m.png");
      image5m=new ImageIcon("5m.png");
      image6m=new ImageIcon("6m.png");
      image7m=new ImageIcon("7m.png");
      image8m=new ImageIcon("8m.png");
      image9m=new ImageIcon("9m.png");
      image10m=new ImageIcon("10m.png");
      image11m=new ImageIcon("11m.png");
      image12m=new ImageIcon("12m.png");
      
      image1_1m=new ImageIcon("1_1m.png");
      image2_1m=new ImageIcon("2_1m.png");
      image3_1m=new ImageIcon("3_1m.png");
      image4_1m=new ImageIcon("4_1m.png");
      image5_1m=new ImageIcon("5_1m.png");
      image6_1m=new ImageIcon("6_1m.png");
      image7_1m=new ImageIcon("7_1m.png");
      image8_1m=new ImageIcon("8_1m.png");
      image9_1m=new ImageIcon("9_1m.png");
      image10_1m=new ImageIcon("10_1m.png");
      image11_1m=new ImageIcon("11_1m.png");
      image12_1m=new ImageIcon("12_1m.png");
      
      button1m=new JButton(image1m);
      button2m=new JButton(image2m);
      button3m=new JButton(image3m);
      button4m=new JButton(image4m);
      button5m=new JButton(image5m);
      button6m=new JButton(image6m);
      button7m=new JButton(image7m);
      button8m=new JButton(image8m);
      button9m=new JButton(image9m);
      button10m=new JButton(image10m);
      button11m=new JButton(image11m);
      button12m=new JButton(image12m);
      buttoncoupon_5=new JButton("아메리카노 무료!");
      buttoncoupon_10 = new JButton("원하는 음료 무료!");
      buttoncoupon_5.setFont(font);
      buttoncoupon_10.setFont(font);
      
      
      
      
      pan1.setLayout( new GridBagLayout() );
      GridBagConstraints   cba = new GridBagConstraints();
      cba.weightx   = 0.2;
      cba.weighty    = 0.2;
      
      pan2.setLayout( new GridBagLayout() );
      GridBagConstraints   cbb = new GridBagConstraints();
      cbb.weightx   = 0.2;
      cbb.weighty    = 0.2;
      
      
      pan3.setLayout( new GridBagLayout() );
      GridBagConstraints   cbc = new GridBagConstraints();
      cbc.weightx   = 0.2;
      cbc.weighty    = 0.2;
      
      pan4.setLayout( new GridBagLayout() );
      GridBagConstraints   cbd = new GridBagConstraints();
      cbd.weightx   = 0.2;
      cbd.weighty    = 0.2;
      
      pan5.setLayout( new GridBagLayout() );
      GridBagConstraints   cbe = new GridBagConstraints();
      cbe.weightx   = 0.2;
      cbe.weighty    = 0.2;
      
      pan6.setLayout( new GridBagLayout() );
      GridBagConstraints   cbf = new GridBagConstraints();
      cbf.weightx   = 0.2;
      cbf.weighty    = 0.2;
      
      pan7.setLayout( new GridBagLayout() );
      GridBagConstraints   cbg = new GridBagConstraints();
      cbg.weightx   = 0.2;
      cbg.weighty    = 0.2;
      
      pan8.setLayout( new GridBagLayout() );
      GridBagConstraints   cbh = new GridBagConstraints();
      cbh.weightx   = 0.2;
      cbh.weighty    = 0.2;
      
      pan9.setLayout( new GridBagLayout() );
      GridBagConstraints   cbi = new GridBagConstraints();
      cbi.weightx   = 0.2;
      cbi.weighty    = 0.2;
      
      pan10.setLayout( new GridBagLayout() );
      GridBagConstraints   cbj = new GridBagConstraints();
      cbj.weightx   = 0.2;
      cbj.weighty    = 0.2;
      
      pan11.setLayout( new GridBagLayout() );
      GridBagConstraints   cbk = new GridBagConstraints();
      cbk.weightx   = 0.2;
      cbk.weighty    = 0.2;
      
      pan12.setLayout(new BorderLayout());
      
      pan14.setLayout( new GridBagLayout() );
      GridBagConstraints   cbl = new GridBagConstraints();
      cbl.weightx   = 0.2;
      cbl.weighty    = 0.2;
      
      
      
      lab1=new JLabel("프로필");
      //add(lab1);
      
      lab3=new JLabel();
      
      cbf.gridx   =   1;             cbf.gridy   =  0;         
      pan6.add(lab3,cbf);
      lab2=new JLabel("이   름 : ");
      cba.gridx   =   1;             cba.gridy   =  0;         
      pan1.add(lab2,cba);
      tf1=new JTextField(10);
      cba.gridx   =2;             cba.gridy   =  0;         
      pan1.add(tf1,cba);
      cba.gridx   =1;             cba.gridy   =  1;
      pan1.add(male,cba);
      cba.gridx   =2;             cba.gridy   =  1;
      pan1.add(female,cba);
      
      cbg.gridx   =   1;             cbg.gridy   =  0;
      pan7.add(pan6,cbg);
      cbg.gridx   =   2;             cbg.gridy   =  0;
      pan7.add(pan1,cbg);
      
      lab4=new JLabel("ID : ");
      cbb.gridx=1;   cbb.gridy=0;   
      pan2.add(lab4,cbb);
      tf2=new JTextField(10);
      cbb.gridx=2;   cbb.gridy=0;   
      pan2.add(tf2,cbb);
      lab5=new JLabel("비번 : ");
      cbb.gridx=1;   cbb.gridy=1;   
      pan2.add(lab5,cbb);
      tf3=new JTextField(10);
      cbb.gridx=2;   cbb.gridy=1;   
      pan2.add(tf3,cbb);
      
   
      
      
      lab6=new JLabel("전화번호: ");
      cbc.gridx=1;   cbc.gridy=0;   
      pan3.add(lab6,cbc);
      tf4=new JTextField(20);
      cbc.gridx=2;   cbc.gridy=0;
      pan3.add(tf4,cbc);
      lab7=new JLabel("email: ");
      cbc.gridx=1;   cbc.gridy=1;
      pan3.add(lab7,cbc);
      tf5=new JTextField(20);
      cbc.gridx=2;   cbc.gridy=1;
      pan3.add(tf5,cbc);
      lab8=new JLabel("주소 : ");
      cbc.gridx=1;   cbc.gridy=2;
      pan3.add(lab8,cbc);
      tf6=new JTextField(20);
      cbc.gridx=2;   cbc.gridy=2;
      pan3.add(tf6,cbc);
      
      lab9=new JLabel("내 계좌: ");
      cbd.gridx=1;   cbd.gridy=0;
      pan4.add(lab9,cbd);
      cbd.gridx=2;   cbd.gridy=0;
      pan4.add(account,cbd);
      tf7=new JTextField(15);
      cbd.gridx=3;   cbd.gridy=0;
      pan4.add(tf7,cbd);
      lab10=new JLabel("가입 날짜 : ");
      cbe.gridx=1;   cbe.gridy=0;
      pan5.add(lab10,cbe);
      lab11=new JLabel();
      cbe.gridx=2;   cbe.gridy=0;
      pan5.add(lab11,cbe);
      pan8.setLayout(new GridLayout(5,1));
      //this.setSize(300,20);

      button1 = new JButton("내 정보 수정");
      button2 = new JButton("수정 취소");
      button3 = new JButton("탈퇴");
      button4 = new JButton("로그아웃");
      cbe.gridx=1;   cbe.gridy=1;
      pan5.add(button1,cbe);
      cbe.gridx=2;   cbe.gridy=1;
      pan5.add(button2,cbe);
      cbe.gridx=3;   cbe.gridy=1;
      pan5.add(button3,cbe);
      pan2.add(button4);
      
      //스탬프 부분
      lab12=new JLabel();
      cbi.gridx=0;   cbi.gridy=0;
      pan13.add(lab12,cbi);
      dao.countHistory(this);
      
         
      cbj.gridx=0;   cbj.gridy=0;
      pan10.add(button1m,cbj);
      cbj.gridx=0;   cbj.gridy=1;
      pan10.add(button2m,cbj);
      cbj.gridx=0;   cbj.gridy=2;
      pan10.add(button3m,cbj);
      cbj.gridx=0;   cbj.gridy=3;
      pan10.add(button4m,cbj);
      cbj.gridx=0;   cbj.gridy=4;
      pan10.add(button5m,cbj);
      cbj.gridx=0;   cbj.gridy=5;
      pan10.add(button6m,cbj);
      cbj.gridx=0;   cbj.gridy=6;
      pan10.add(button7m,cbj);
      cbj.gridx=0;   cbj.gridy=7;
      pan10.add(button8m,cbj);
      cbj.gridx=0;   cbj.gridy=8;
      pan10.add(button9m,cbj);
      cbj.gridx=0;   cbj.gridy=9;
      pan10.add(button10m,cbj);
      cbj.gridx=0;   cbj.gridy=10;
      pan10.add(button11m,cbj);
      cbj.gridx=0;   cbj.gridy=11;
      pan10.add(button12m,cbj);
      
      lab16=new JLabel();
      cbk.gridx=0;   cbk.gridy=0;
      pan11.add(lab16,cbk);
      lab17=new JLabel();
      cbk.gridx=1;   cbk.gridy=0;
      pan11.add(lab17,cbk);
      cbk.gridx=0;   cbk.gridy=1;
      pan11.add(buttoncoupon_5,cbk);
      cbk.gridx=1;   cbk.gridy=1;
      pan11.add(buttoncoupon_10,cbk);
      
      
      pan8.add(pan7);
      pan8.add(pan2);
      pan8.add(pan3);
      pan8.add(pan4);
      pan8.add(pan5);
      
      //jsp = new JScrollPane(pan8, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
      //jsp.setPreferredSize(new Dimension(300, 500));
      add(pan8);
      
      
      jspcoupon = new JScrollPane(pan10, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
      jspcoupon.setPreferredSize(new Dimension(350, 300));
      cbi.gridx=0;   cbi.gridy=1;
      pan9.add(jspcoupon,cbi);
      pan9.setBorder(new TitledBorder("My Coupon"));
      
      pan12.add(pan11,"North");
      pan12.add(pan13,"Center");
      pan12.setBorder(new TitledBorder("My Stamp"));
      
      cbl.gridx=0;   cbl.gridy=0;
      pan14.add(pan12,cbl);
      cbl.gridx=0;   cbl.gridy=1;
      pan14.add(pan9,cbl);
      add(pan14);
      
      
      dao.showMyInfo(id,dto);
      male.setSelected(true);
          
         switch((cal.get(Calendar.MONTH) + 1)) {
         
         case 1 : button1m.setEnabled(true); break ; 
         case 2 : button2m.setEnabled(true);
                  button1m.setIcon(image1_1m);
                  button1m.setEnabled(false);
                  break ;
         case 3 : button3m.setEnabled(true); 
         button1m.setIcon(image1_1m);
         button2m.setIcon(image2_1m);
         button1m.setEnabled(false);
         button2m.setEnabled(false);
         break ; 
        
         case 4 : button4m.setEnabled(true); 
         button1m.setIcon(image1_1m);
         button2m.setIcon(image2_1m);
         button3m.setIcon(image3_1m);
         button1m.setEnabled(false);
         button2m.setEnabled(false);
         button3m.setEnabled(false);
       
         break ;
         case 5 : button5m.setEnabled(true); 
         button1m.setIcon(image1_1m);
         button2m.setIcon(image2_1m);
         button3m.setIcon(image3_1m);
         button4m.setIcon(image4_1m);
         button1m.setEnabled(false);
         button2m.setEnabled(false);
         button3m.setEnabled(false);
         button4m.setEnabled(false);
         break ; 
         case 6 : button6m.setEnabled(true); 
         button1m.setIcon(image1_1m);
         button2m.setIcon(image2_1m);
         button3m.setIcon(image3_1m);
         button4m.setIcon(image4_1m);
         button5m.setIcon(image5_1m);
         button1m.setEnabled(false);
         button2m.setEnabled(false);
         button3m.setEnabled(false);
         button4m.setEnabled(false);
         button5m.setEnabled(false);
         
         break ;
         case 7 : button7m.setEnabled(true);
         button1m.setIcon(image1_1m);
         button2m.setIcon(image2_1m);
         button3m.setIcon(image3_1m);
         button4m.setIcon(image4_1m);
         button5m.setIcon(image5_1m);
         button6m.setIcon(image6_1m);
         button1m.setEnabled(false);
         button2m.setEnabled(false);
         button3m.setEnabled(false);
         button4m.setEnabled(false);
         button5m.setEnabled(false);
         button6m.setEnabled(false);
         break ; 
         case 8 : button8m.setEnabled(true); 
         button1m.setIcon(image1_1m);
         button2m.setIcon(image2_1m);
         button3m.setIcon(image3_1m);
         button4m.setIcon(image4_1m);
         button5m.setIcon(image5_1m);
         button6m.setIcon(image6_1m);
         button7m.setIcon(image7_1m);
         button1m.setEnabled(false);
         button2m.setEnabled(false);
         button3m.setEnabled(false);
         button4m.setEnabled(false);
         button5m.setEnabled(false);
         button6m.setEnabled(false);
         button7m.setEnabled(false);
         break ;
         case 9 : button9m.setEnabled(true);
         button1m.setIcon(image1_1m);
         button2m.setIcon(image2_1m);
         button3m.setIcon(image3_1m);
         button4m.setIcon(image4_1m);
         button5m.setIcon(image5_1m);
         button6m.setIcon(image6_1m);
         button7m.setIcon(image7_1m);
         button8m.setIcon(image8_1m);
         button1m.setEnabled(false);
         button2m.setEnabled(false);
         button3m.setEnabled(false);
         button4m.setEnabled(false);
         button5m.setEnabled(false);
         button6m.setEnabled(false);
         button7m.setEnabled(false);
         button8m.setEnabled(false);
         
         break ; 
         case 10 : button10m.setEnabled(true); 
         button1m.setIcon(image1_1m);
         button2m.setIcon(image2_1m);
         button3m.setIcon(image3_1m);
         button4m.setIcon(image4_1m);
         button5m.setIcon(image5_1m);
         button6m.setIcon(image6_1m);
         button7m.setIcon(image7_1m);
         button8m.setIcon(image8_1m);
         button9m.setIcon(image9_1m);
         button1m.setEnabled(false);
         button2m.setEnabled(false);
         button3m.setEnabled(false);
         button4m.setEnabled(false);
         button5m.setEnabled(false);
         button6m.setEnabled(false);
         button7m.setEnabled(false);
         button8m.setEnabled(false);
         button9m.setEnabled(false);
         break ;
         case 11 : button11m.setEnabled(true);
         button1m.setIcon(image1_1m);
         button2m.setIcon(image2_1m);
         button3m.setIcon(image3_1m);
         button4m.setIcon(image4_1m);
         button5m.setIcon(image5_1m);
         button6m.setIcon(image6_1m);
         button7m.setIcon(image7_1m);
         button8m.setIcon(image8_1m);
         button9m.setIcon(image9_1m);
         button10m.setIcon(image10_1m);
         button1m.setEnabled(false);
         button2m.setEnabled(false);
         button3m.setEnabled(false);
         button4m.setEnabled(false);
         button5m.setEnabled(false);
         button6m.setEnabled(false);
         button7m.setEnabled(false);
         button8m.setEnabled(false);
         button9m.setEnabled(false);
         button10m.setEnabled(false);
         break ; 
         case 12 : button12m.setEnabled(true);
         button1m.setIcon(image1_1m);
         button2m.setIcon(image2_1m);
         button3m.setIcon(image3_1m);
         button4m.setIcon(image4_1m);
         button5m.setIcon(image5_1m);
         button6m.setIcon(image6_1m);
         button7m.setIcon(image7_1m);
         button8m.setIcon(image8_1m);
         button9m.setIcon(image9_1m);
         button10m.setIcon(image10_1m);
         button11m.setIcon(image11_1m);
         button1m.setEnabled(false);
         button2m.setEnabled(false);
         button3m.setEnabled(false);
         button4m.setEnabled(false);
         button5m.setEnabled(false);
         button6m.setEnabled(false);
         button7m.setEnabled(false);
         button8m.setEnabled(false);
         button9m.setEnabled(false);
         button10m.setEnabled(false);
         button11m.setEnabled(false);
         break ;
          
 }
         
      dao.checkCoupon(this, coupondao);
      
      tf1.setText(dto.getPname());
      tf2.setText(dto.getPid());
      tf2.setEditable(false);
      tf3.setText(dto.getPpwd());
      tf4.setText(dto.getPtel());
      tf5.setText(dto.getPemail());
      tf6.setText(dto.getPaddr());
      lab11.setText(dto.getPdate());

      int i=0;
      String bankname="";
      while(!(dto.getPaccount().charAt(i)==' ')) {
         bankname=bankname+dto.getPaccount().charAt(i);
         i++;
      }
      account.setSelectedItem(bankname.trim());
      tf7.setText(dto.getPaccount().substring(i, dto.getPaccount().length()).trim());
      
  
      if(dto.getPgender().trim().equals("M")) {
         male.setSelected(true);
         lab3.setIcon(imagem);
      }
      else if(dto.getPgender().trim().equals("F")) {
         female.setSelected(true);
         lab3.setIcon(imagef);
      }
      
      buttoncoupon_5.setEnabled(false);
      buttoncoupon_10.setEnabled(false);
      count=dao.checkStamp(this);
      
      button1.addActionListener(this);
      button2.addActionListener(this);
      button3.addActionListener(this);
      button4.addActionListener(this);
      
      button1m.addActionListener(this);
      button2m.addActionListener(this);
      button3m.addActionListener(this);
      button4m.addActionListener(this);
      button5m.addActionListener(this);
      button6m.addActionListener(this);
      button7m.addActionListener(this);
      button8m.addActionListener(this);
      button9m.addActionListener(this);
      button10m.addActionListener(this);
      button11m.addActionListener(this);
      button12m.addActionListener(this);
      
      buttoncoupon_5.addActionListener(this);
      buttoncoupon_10.addActionListener(this);
      
      Color b = new Color(248,234,221); 
         pan1.setBackground(b);
         pan2.setBackground(b);
         pan3.setBackground(b);
         pan4.setBackground(b);
         pan5.setBackground(b);
         pan6.setBackground(b);
         pan7.setBackground(b);
         pan8.setBackground(b);
         pan9.setBackground(b);
         pan10.setBackground(b);
         pan11.setBackground(b);
         pan12.setBackground(b);
         pan13.setBackground(b);
         pan14.setBackground(b);
         male.setBackground(b);
         female.setBackground(b);
         setBackground(b);
         setFont(font);
         
         
         lab1.setFont(font);
         lab2.setFont(font);
         lab3.setFont(font);
         lab4.setFont(font);
         lab5.setFont(font);
         lab6.setFont(font);
         lab7.setFont(font);
         lab8.setFont(font);
         lab9.setFont(font);
         lab10.setFont(font);
         lab11.setFont(font);
         lab12.setFont(font);
         //lab13.setFont(font);
        // lab14.setFont(font);
        // lab15.setFont(font);
         lab16.setFont(font);
         lab17.setFont(font);
         button1.setFont(font);
         button2.setFont(font);
         button3.setFont(font);
         button4.setFont(font);
         male.setFont(font);
         female.setFont(font);
        
        
      
      
   }
   
      public void actionPerformed( ActionEvent ev ){
         Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
         Object o = ev.getSource();
         
         if(o == button1) {
            try {
                  
            	if (tf6.getText().length() >= 3) {
            		JLabel lab60 = new JLabel( "주소는 2글자까지만 가능합니다!");
            		lab16.setFont(font);
            		JOptionPane.showMessageDialog(this, lab60);
            		return;
            	}
            	if (tf3.getText().length() == 0) {
            		JLabel lab61 = new JLabel( "비밀번호는 필수항목입니다! ");
            		lab16.setFont(font);
            		JOptionPane.showMessageDialog(this, lab61);
            		return;
            	}
            	if (tf3.getText().length() > 20) {
            		JLabel lab61 = new JLabel( "비밀번호는 최대20자까지만 가능합니다! ");
            		lab16.setFont(font);
            		JOptionPane.showMessageDialog(this, lab61);
            		return;
            	}
            	if (tf4.getText().length() == 0) {
            		JLabel lab62 = new JLabel( "전화번호는 필수항목입니다!");
            		lab16.setFont(font);
            		JOptionPane.showMessageDialog(this, lab62);
            		return;
            	}
            	if (tf7.getText().length() == 0) {
            		JLabel lab62 = new JLabel( "계좌번호는 필수항목입니다!");
            		lab16.setFont(font);
            		JOptionPane.showMessageDialog(this, lab62);
            		return;
            	}
            	if(account.getSelectedItem().equals("선택")) {
            		JLabel lab63 = new JLabel( "은행을 선택해주세요!");
            		lab63.setFont(font);
        			JOptionPane.showMessageDialog(this, lab63);
        			return;
        		}
            	
                  dto.setPname(tf1.getText());
                  dto.setPid(tf2.getText());
                  dto.setPpwd(tf3.getText());
                  dto.setPtel(tf4.getText());
                  dto.setPemail(tf5.getText());
                  dto.setPaddr(tf6.getText());
                  String fullaccount=(String)account.getSelectedItem()+" "+tf7.getText();
                  dto.setPaccount(fullaccount);
                  dto.setPgender(radiocheck());
                  
                  
                  
                  dao.modifyMyInfo(dto);
                  JLabel lab18 = new JLabel("회원정보가 수정되었습니다!");
                  lab18.setFont(font);
                  JOptionPane.showMessageDialog(this, lab18);
                  
                tf1.setText(dto.getPname());
                tf2.setText(dto.getPid());
                tf3.setText(dto.getPpwd());
                tf4.setText(dto.getPtel());
                tf5.setText(dto.getPemail());
                tf6.setText(dto.getPaddr());
                
                lab11.setText(dto.getPdate());
                int i=0;
                String bankname="";
                while(!(dto.getPaccount().charAt(i)==' ')) {
                   bankname=bankname+dto.getPaccount().charAt(i);
                   i++;
                }
                account.setSelectedItem(bankname.trim());
                tf7.setText(dto.getPaccount().substring(i, dto.getPaccount().length()).trim());
                
                System.out.println(dto.getPgender().equals("M"));
                if(dto.getPgender()=="M") {
                   male.setSelected(true);
                }
            
            }catch (Exception e) {
               JLabel lab19 = new JLabel("내 정보 수정 실패 : ");
                  lab19.setFont(font);
                   JOptionPane.showMessageDialog
                   (null, lab19 + e.getMessage());
            }
         }
         else if ( o == button2 ) {
            try {
               JLabel lab20 = new JLabel("회원정보 수정을 취소합니다.");
                  lab20.setFont(font);
               JOptionPane.showMessageDialog(null, lab20);
               tf1.setText(dto.getPname());
                tf2.setText(dto.getPid());
                tf3.setText(dto.getPpwd());
                tf4.setText(dto.getPtel());
                tf5.setText(dto.getPemail());
                tf6.setText(dto.getPaddr());
                
                lab11.setText(dto.getPdate());
                int i=0;
                String bankname="";
                while(!(dto.getPaccount().charAt(i)==' ')) {
                   bankname=bankname+dto.getPaccount().charAt(i);
                   i++;
                }
                account.setSelectedItem(bankname.trim());
                tf7.setText(dto.getPaccount().substring(i, dto.getPaccount().length()).trim());
            
            }catch (Exception e)
            {e.printStackTrace(); }
            
         }else if ( o == button3 ) {
            try {
               JLabel lab21 = new JLabel("탈퇴하시겠습니까? 다시 서비스를 이용하시려면 새롭게 회원가입이 필요합니다.");
                  lab21.setFont(font);
               int result=JOptionPane.showConfirmDialog(null, lab21,"탈퇴 경고",JOptionPane.YES_NO_OPTION);
               if(result==JOptionPane.YES_OPTION) {
                  JLabel lab22 = new JLabel("정말 탈퇴하시겠어요?ㅠㅠ");
                     lab22.setFont(font);
                  int res=JOptionPane.showConfirmDialog(null, lab22,"탈퇴 경고",JOptionPane.YES_NO_OPTION);
                  if(res==JOptionPane.YES_OPTION) { 
                	  JLabel lab30 = new JLabel("진짜?");
                      lab30.setFont(font);
                   int res2=JOptionPane.showConfirmDialog(null, lab30,"탈퇴 경고",JOptionPane.YES_NO_OPTION);
                   if(res2==JOptionPane.YES_OPTION)  {
                   String id = tf2.getText();
                     dao.deleteMyInfo(id);
                     JLabel lab23 = new JLabel("탈퇴되었습니다. 저희 카페 ONE을 이용해 주셔서 감사합니다.");
                        lab23.setFont(font);
                     JOptionPane.showMessageDialog(null, lab23);
                     System.exit(0);
                     
                     }
                  }
               }
               
            }catch (Exception e) {
               JLabel lab24 = new JLabel("삭제 실패 : ");
                  lab24.setFont(font);
               JOptionPane.showMessageDialog(null, lab24 + e.getMessage());
            }
         }
         else if ( o == button4 ) {
            JLabel lab25 = new JLabel("창이 모두 닫힙니다. 로그아웃 하시겠습니까?");
               lab25.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab25,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               System.exit(0);
               }
            }
            
         
         
         //쿠폰 버튼 이벤트
         else if(o==button1m) {
            JLabel lab26 = new JLabel("1월 달 쿠폰을 사용하시겠습니까?");
               lab26.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab26,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<1) {
                  JLabel lab27 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab27.setFont(font);
                  JOptionPane.showMessageDialog(null, lab27);
               }
               else {
               String month ="JAN"; 
               dao.couponCount(month, myid);
               dao.checkCoupon(this, coupondao);}
         }
         }
         
         else if(o==button2m) {
            JLabel lab28 = new JLabel("2월 달 쿠폰을 사용하시겠습니까?");
               lab28.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab28,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<2) {
                  JLabel lab29 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab29.setFont(font);
                  JOptionPane.showMessageDialog(null, lab29);
               }
               else {
                  String month ="FAB";
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
            
         }
         
         else if(o==button3m) {
            JLabel lab30 = new JLabel("3월 달 쿠폰을 사용하시겠습니까?");
               lab30.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab30,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<3) {
                  JLabel lab31 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab31.setFont(font);
                  JOptionPane.showMessageDialog(null, lab31);
               }
               else {
                  String month ="MAR";
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
            
         }
         else if(o==button4m) {
            JLabel lab32 = new JLabel("4월 달 쿠폰을 사용하시겠습니까?");
               lab32.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab32,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<4) {
                  JLabel lab33 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab33.setFont(font);
                  JOptionPane.showMessageDialog(null, lab33);
               }
               else {
                  String month ="APR";
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
         }
         else if(o==button5m) {
            JLabel lab34 = new JLabel("5월 달 쿠폰을 사용하시겠습니까?");
               lab34.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab34,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<5) {
                  JLabel lab35 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab35.setFont(font);
                  JOptionPane.showMessageDialog(null, lab35);
               }
               else {
                  String month ="MAY";
                  
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
         }
         
         else if(o==button6m) {
            JLabel lab36 = new JLabel("6월 달 쿠폰을 사용하시겠습니까?");
               lab36.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab36,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<6) {
                  JLabel lab37 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab37.setFont(font);
                  JOptionPane.showMessageDialog(null, lab37);
               }
               else {
                  String month ="JUN";
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
            
         }
         
         else if(o==button7m) {
            JLabel lab38 = new JLabel("7월 달 쿠폰을 사용하시겠습니까?");
               lab38.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab38,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<7) {
                  JLabel lab39 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab39.setFont(font);
                  JOptionPane.showMessageDialog(null, lab39);
               }
               else {
                  String month ="JUL";
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
            
         }
         
         else if(o==button8m) {
            JLabel lab40 = new JLabel("8월 달 쿠폰을 사용하시겠습니까?");
               lab40.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab40,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<8) {
                  JLabel lab41 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab41.setFont(font);
                  JOptionPane.showMessageDialog(null, lab41);
               }
               else {
                  String month ="AUG";
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
            
         }
         
         else if(o==button9m) {
            JLabel lab42 = new JLabel("9월 달 쿠폰을 사용하시겠습니까?");
               lab42.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab42,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<9) {
                  JLabel lab43 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab43.setFont(font);
                  JOptionPane.showMessageDialog(null, lab43);
               }
               else {
                  String month ="SEP";
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
            
         }
         
         else if(o==button10m) {
            JLabel lab44 = new JLabel("10월 달 쿠폰을 사용하시겠습니까?");
               lab44.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab44,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<10) {
                  JLabel lab45 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab45.setFont(font);
                  JOptionPane.showMessageDialog(null, lab45);
               }
               else {
                  String month ="OCT";
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
            
         }
         
         else if(o==button11m) {
            JLabel lab46 = new JLabel("11월 달 쿠폰을 사용하시겠습니까?");
               lab46.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab46,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<11) {
                  JLabel lab47 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab47.setFont(font);
                  JOptionPane.showMessageDialog(null, lab47);
               }
               else {
                  String month ="NOV";
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
            
         }
         
         else if(o==button12m) {
            JLabel lab48 = new JLabel("12월 달 쿠폰을 사용하시겠습니까?");
               lab48.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab48,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               if((cal.get(Calendar.MONTH) + 1)<12) {
                  JLabel lab49 = new JLabel("쿠폰 사용 기간이 아닙니다!");
                     lab49.setFont(font);
                  JOptionPane.showMessageDialog(null, lab49);
               }
               else {
                  String month ="DEC";
                  dao.couponCount(month, myid);
                  dao.checkCoupon(this, coupondao);}
            }
            
         }
         else if(o==buttoncoupon_5) {
            JLabel lab50 = new JLabel("아메리카노 무료 쿠폰을 사용하시겠어요?");
               lab50.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab50,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               dao.useCoupon5(this);
               dao.checkStamp(this);            
               }
            }
         else if(o==buttoncoupon_10) {
            JLabel lab51 = new JLabel("원하는 음료 무료 쿠폰을 사용하시겠어요?");
               lab51.setFont(font);
            int result=JOptionPane.showConfirmDialog(null, lab51,"확인",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
               JLabel lab52 = new JLabel("원하는 음료를 직원에게 알려주세요!");
                  lab52.setFont(font);
               JOptionPane.showMessageDialog(null, lab52);
               dao.useCoupon10(this);
               dao.checkStamp(this);            
               }
         }
         
         
   }
      @Override
      public void itemStateChanged(ItemEvent e) {
   
         Object o = e.getSource();
         if(male.isSelected()) {
            radiocheck();
         }
         else if(female.isSelected()) {
            radiocheck();
         }
         else radiocheck();
      }
      public String radiocheck() {
         
         if(male.isSelected()) {
            lab3.setIcon(imagem);
            return "M";}
         else if (female.isSelected()) {
            lab3.setIcon(imagef);   
            return "F";}
         else return "";
      }
      
   

}

   
   
   
   
              