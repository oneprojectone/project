import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectFrame extends JFrame {
	
	JPanel panNorth;
	JPanel panWest;
	JPanel panSouth;
	JPanel p0, p1, p2, p3;
	JTextField txtId, txtName, txtDate;
	JButton btnOk, btnCancel;
	
	public SelectFrame() {
		setBounds(400, 200, 250, 300);
		panNorth = new JPanel();
		p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p0.add(new JLabel("무엇으로 검색하시겠습니까?"));
		panNorth.add(p0);
		add(panNorth, "North");
		panWest = new JPanel(new GridLayout(7,0));
		p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(new JLabel("아  이  디"));
		p1.add(txtId = new JTextField(12));
		panWest.add(p1);
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(new JLabel("상  품  명"));
		p2.add(txtName = new JTextField(12));
		panWest.add(p2);
		p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("주  문  날  짜"));
		p3.add(txtDate = new JTextField(12));
		panWest.add(p3);
		add(panWest, "Center");
		
		
		
		
		panSouth = new JPanel();
		panSouth.add(btnOk = new JButton("확  인"));
		panSouth.add(btnCancel = new JButton("취  소"));
		add(panSouth, "South");
		
		pack();
		//setVisible(true);
		
	}
/*	
	public static void main(String[] args) {
		SelectFrame sfr = new SelectFrame();

	}
*/
}
