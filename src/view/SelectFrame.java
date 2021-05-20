package view;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectFrame extends JFrame {
	
	public JPanel panNorth;
	public JPanel panWest;
	public JPanel panSouth;
	public JPanel p0, p1, p2, p3;
	public JTextField txtId, txtName, txtDate, txtDate2;
	public JButton btnOk, btnCancel;
	
	String menuName[] = {"선택해주세요", "아메리카노", "카페라떼", "카페모카", "바닐라라떼", "초코라떼", "돌체라떼", "자바치프라푸치노",
			"흑당라떼", "민트초코칩프라푸치노", "콜드브루", "콜드브루라떼", "카푸치노", "카라멜마끼아또", "에스프레소",
			"자몽허니블랙티", "제주한라산녹차라떼", "돌체콜드브루", "뉴욕치즈케이크", "크로크무슈", "허니브레드", "초코머핀",
			"베이글", "티라미수케이크", "민트초코케이크", "베리베리스트로베리", "자몽에이드", "레몬에이드", "사과에이드", 
			"딸기요거트스무디", "블루베리요거트스무디", "키위요거트스무디" };
	
	public JComboBox<String> comboBox;
	
	public SelectFrame() {
		setBounds(370, 120, 250, 300);
		panNorth = new JPanel();
		p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		p0.add(new JLabel("무엇으로 검색하시겠습니까?"));
		p0.add(new JLabel("<html>       무엇으로 검색하시겠습니까?<br>주문 날짜는 년/월/일로 검색해주세요.</html>"));
		panNorth.add(p0);
		add(panNorth, "North");
		panWest = new JPanel(new GridLayout(7,0));
		p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(new JLabel("아 이 디  "));
		p1.add(txtId = new JTextField(14));
		panWest.add(p1);
		
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(new JLabel("상 품 명  "));
		comboBox = new JComboBox<String>(menuName);
		p2.add(comboBox);
		panWest.add(p2);

		p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("주문  날짜"));
		p3.add(txtDate = new JTextField(6));
		p3.add(new JLabel(" ~ "));
		p3.add(txtDate2 = new JTextField(6));
		panWest.add(p3);
		add(panWest, "Center");
		
		panSouth = new JPanel();
		panSouth.add(btnOk = new JButton("확  인"));
		panSouth.add(btnCancel = new JButton("취  소"));
		add(panSouth, "South");
		
		pack();
		setTitle(" 검 색");
	}

}
