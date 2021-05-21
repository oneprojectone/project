package index;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dao.CustomerDAO;
import dao.LoginDAO;
import loginview.Door;
import loginview.FindId;
import loginview.FindPw;

public class Login extends JFrame implements ActionListener{
   public JPanel pan1, pan2;
   public JLabel banner,banner1, lid, lpass, empty1;
   public TextField tf1, tf2;
   public JButton newID, ok, forid, forpass;
   Icon icon;
   CustomerDAO dao = new CustomerDAO();
   LoginDAO daolg = new LoginDAO();
   Image image1;
   
   public static void main(String[] args) {
      Login lg = new  Login();
      SwingUtilities.invokeLater(new Runnable() {
          @Override
            public void run() {
             
      }
   });
   }
   public Login(){
	   setTitle("ONE-Login");
      
      setResizable(true);
      
      image1 = Toolkit.getDefaultToolkit().createImage("one.gif");
      
      lid = new JLabel("ID");
      lpass = new JLabel("password");
      empty1 = new JLabel("                                                 ");
      
      Icon icon = new ImageIcon("one.gif");
      banner = new JLabel(icon);
      
      pan1 = new JPanel();
      pan2 = new JPanel();
      
      ImageIcon normalIcon = new ImageIcon("normal.png");
       ImageIcon rolloverIcon = new ImageIcon("rollover.png");
       ImageIcon pressedIcon = new ImageIcon("pressed.png");
         
       ImageIcon normalIcon1 = new ImageIcon("normal1.png");
       ImageIcon rolloverIcon1 = new ImageIcon("rollover1.png");
       ImageIcon pressedIcon1 = new ImageIcon("pressed1.png");
         
       ImageIcon normalIcon2 = new ImageIcon("normal2.png");
       ImageIcon rolloverIcon2 = new ImageIcon("rollover2.png");
       ImageIcon pressedIcon2 = new ImageIcon("pressed2.png");
         
       ImageIcon normalIcon3 = new ImageIcon("normal3.png");
       ImageIcon rolloverIcon3 = new ImageIcon("rollover3.png");
       ImageIcon pressedIcon3 = new ImageIcon("pressed3.png");
      
      tf1 = new TextField(20);
      tf2 = new TextField(20);
      ok = new JButton("",normalIcon);
      forid = new JButton("",normalIcon1);
      forpass = new JButton("",normalIcon2);
      newID = new JButton("",normalIcon3);

      ok.setRolloverIcon(rolloverIcon);
      ok.setPressedIcon(pressedIcon);
      forid.setRolloverIcon(rolloverIcon1);
      forid.setPressedIcon(pressedIcon1);
      forpass.setRolloverIcon(rolloverIcon2);
      forpass.setPressedIcon(pressedIcon2);
      newID.setRolloverIcon(rolloverIcon3);
      newID.setPressedIcon(pressedIcon3);      

      ok.setPreferredSize(new Dimension(163, 35));
      forid.setPreferredSize(new Dimension(100, 35));
      forpass.setPreferredSize(new Dimension(100, 35));
      newID.setPreferredSize(new Dimension(100, 35));

      pan2.add(lid);
      pan2.add(tf1);
      pan2.add(lpass);
      pan2.add(tf2);
      pan2.add(ok);
      pan2.add(empty1);
      pan2.add(forid);
      pan2.add(forpass);
      pan2.add(newID);
      
      pan1.setLayout(new BorderLayout());
      pan1.add(getContentPane().add(banner));
      pan1.add(pan2, "North");
      add(pan1);
      
      Color b = new Color(248,234,221);  
      
      pan1.setBackground(b);    
      pan2.setBackground(b);    
      
      tf2.setEchoChar('*');
      
      setVisible(true);
      pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      ok.addActionListener(this);
      forid.addActionListener(this);
      forpass.addActionListener(this);
      newID.addActionListener(this);
      
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      Object o = e.getSource();
      
      if(o==forpass) {
         FindPw fpw = new FindPw();
      }
      else if(o==forid) {
         FindId fid = new FindId();
      }
      else if(o==newID) {
         Door door = new Door();
      }
      else if(o==ok) {
         daolg.loginCheck(this);
      }
      
   }
   
   class ImagePanel extends JPanel {

        Image image;

        public ImagePanel() {
          image = Toolkit.getDefaultToolkit().createImage("one.gif");
        }

        @Override
        public void paintComponent(Graphics g) {
          super.paintComponent(g);
          if (image1 != null) {
            g.drawImage(image1, 0, 0, this);
          }
        }

      }
}