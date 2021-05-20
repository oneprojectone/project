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
	
	String menuName[] = {"�������ּ���", "�Ƹ޸�ī��", "ī���", "ī���ī", "�ٴҶ��", "���ڶ�", "��ü��", "�ڹ�ġ����Ǫġ��",
			"����", "��Ʈ����Ĩ����Ǫġ��", "�ݵ���", "�ݵ����", "īǪġ��", "ī��Ḷ���ƶ�", "����������",
			"�ڸ���Ϻ�Ƽ", "�����Ѷ�������", "��ü�ݵ���", "����ġ������ũ", "ũ��ũ����", "��Ϻ극��", "���ڸ���",
			"���̱�", "Ƽ��̼�����ũ", "��Ʈ��������ũ", "����������Ʈ�κ���", "�ڸ����̵�", "�����̵�", "������̵�", 
			"������Ʈ������", "��纣�����Ʈ������", "Ű�����Ʈ������" };
	
	public JComboBox<String> comboBox;
	
	public SelectFrame() {
		setBounds(370, 120, 250, 300);
		panNorth = new JPanel();
		p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		p0.add(new JLabel("�������� �˻��Ͻðڽ��ϱ�?"));
		p0.add(new JLabel("<html>       �������� �˻��Ͻðڽ��ϱ�?<br>�ֹ� ��¥�� ��/��/�Ϸ� �˻����ּ���.</html>"));
		panNorth.add(p0);
		add(panNorth, "North");
		panWest = new JPanel(new GridLayout(7,0));
		p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(new JLabel("�� �� ��  "));
		p1.add(txtId = new JTextField(14));
		panWest.add(p1);
		
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(new JLabel("�� ǰ ��  "));
		comboBox = new JComboBox<String>(menuName);
		p2.add(comboBox);
		panWest.add(p2);

		p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("�ֹ�  ��¥"));
		p3.add(txtDate = new JTextField(6));
		p3.add(new JLabel(" ~ "));
		p3.add(txtDate2 = new JTextField(6));
		panWest.add(p3);
		add(panWest, "Center");
		
		panSouth = new JPanel();
		panSouth.add(btnOk = new JButton("Ȯ  ��"));
		panSouth.add(btnCancel = new JButton("��  ��"));
		add(panSouth, "South");
		
		pack();
		setTitle(" �� ��");
	}

}
