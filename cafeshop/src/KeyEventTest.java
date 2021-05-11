import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KeyEventTest extends JFrame implements KeyListener {
	private JPanel panel;
	private JTextField field;
	private JTextArea area;
	
	public KeyEventTest() {
		panel = new JPanel(new GridLayout(0, 2));
		panel.add(new JLabel("문자를 입력하시오"));
		field = new JTextField(10);
		panel.add(field);
		area = new JTextArea(3, 30);
		add(panel, BorderLayout.NORTH);
		add(area, BorderLayout.CENTER);
		field.addKeyListener(this);
		
		setTitle("Key Event Test");
		setSize(400, 200);
		setVisible(true);
	}
		  
		  public void keyTyped(KeyEvent e) {
			  display(e, "Key Typed");
    	  }
    	  public void keyReleased(KeyEvent e) {
    		  display(e, "Key Released");
    	  }
    	  public void keyPressed(KeyEvent e) {
    		  display(e, "Key Pressed");
    	  }
    	  
    	  protected void display(KeyEvent e, String s) {
    		  char c = e.getKeyChar();
    		  
    	  }
    	  public static void main(String[] args) {
    		  KeyEventTest k = new KeyEventTest();
		}
	}
