package one;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MenuTab extends JPanel implements ActionListener, ItemListener {
	JRadioButton rBtnCoffeeMenu = new JRadioButton("커 피 류", false);
	JRadioButton rBtnEtcMenu = new JRadioButton("기 타 류", false);
	JRadioButton rBtnDessertMenu = new JRadioButton("디 저 트", false);
	JTextField textFieldSearchName = new JTextField(12);

	JRadioButton rBtnHot = new JRadioButton("HOT", false);
	JRadioButton rBtnIce = new JRadioButton("ICE", false);
	JComboBox cBoxSize = new JComboBox();
	public String mTem = "";
	public String mSize = "";

	JButton btnSearch = new JButton("검  색");
	JButton btnPut = new JButton("담  기");
	JButton btnOrder = new JButton("주  문");


	DTO_Customer user = new DTO_Customer();

	String dataMenuTable[][] = new String[0][3]; 
	String[] colNameMenuTable = { "코드", "상품명", "가격" };
	DefaultTableModel modelMenuTable = new DefaultTableModel(dataMenuTable, colNameMenuTable) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	JTable tableMenu = new JTable(modelMenuTable);
	String dataSelectedTable[][] = new String[0][3];
	String[] colNameSelectedTable = { "상품명", "크기", "옵션" };
	DefaultTableModel modelSelectedTable = new DefaultTableModel(dataSelectedTable, colNameSelectedTable) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	DAO_Menu dao = new DAO_Menu();
	ArrayList<DTO_Menu> menuList = new ArrayList<DTO_Menu>();
	ArrayList<DTO_History> shoppingBasket = new ArrayList<DTO_History>();
	
	public MenuTab() {
		user.setpID("lily93");
		JPanel panelWest = new JPanel();
		ButtonGroup btnGroupType = new ButtonGroup();
		JLabel labelSearchName = new JLabel("상품명", SwingConstants.CENTER);
		panelWest.setLayout(new GridLayout(3, 1));
		JPanel panelWestType = new JPanel(new GridLayout(3, 1));
		btnGroupType.add(rBtnCoffeeMenu);
		btnGroupType.add(rBtnEtcMenu);
		btnGroupType.add(rBtnDessertMenu);
		panelWestType.setBorder(new TitledBorder("종류"));
		panelWestType.add(rBtnCoffeeMenu);
		panelWestType.add(rBtnEtcMenu);
		panelWestType.add(rBtnDessertMenu);
		panelWest.add(panelWestType);
		JPanel panelWestSearch = new JPanel(new GridLayout(6, 1));
		panelWestSearch.setBorder(new TitledBorder("검색"));
		panelWestSearch.add(labelSearchName);
		panelWestSearch.add(textFieldSearchName);
		panelWest.add(panelWestSearch);
		panelWest.setPreferredSize(new Dimension(150, 500));
		rBtnCoffeeMenu.addItemListener(this);
		rBtnEtcMenu.addItemListener(this);
		rBtnDessertMenu.addItemListener(this);

		JPanel panelCenter = new JPanel();
		JScrollPane scrollPan1 = new JScrollPane(tableMenu);
		TableColumnModel tcm1 = tableMenu.getColumnModel();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		tcm1.getColumn(0).setCellRenderer(dtcr);
		tcm1.getColumn(1).setCellRenderer(dtcr);
		tcm1.getColumn(2).setCellRenderer(dtcr);
		tcm1.getColumn(0).setPreferredWidth(400/(tcm1.getColumnCount()*2));
		tcm1.getColumn(1).setPreferredWidth(400/(tcm1.getColumnCount()*2)*4);
		tcm1.getColumn(2).setPreferredWidth(400/(tcm1.getColumnCount()*2));
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(scrollPan1, "Center");
		panelCenter.setBorder(new TitledBorder("메뉴"));
		tableMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tableMenu.getSelectedRow();
				String no = tableMenu.getValueAt(row, 0).toString().substring(0, 1);
				if (no.equals("C")) {
					rBtnHot.setEnabled(true);
					rBtnIce.setEnabled(true);
					cBoxSize.setEnabled(true);
				} else if (no.equals("A")) {
					rBtnHot.setEnabled(false);
					rBtnIce.setSelected(true);
					rBtnIce.setEnabled(false);
					mTem = "i";
					cBoxSize.setEnabled(true);
				} else if (no.equals("D")) {
					rBtnHot.setEnabled(false);
					rBtnIce.setEnabled(false);
					mTem = "";
					cBoxSize.setSelectedIndex(0);
					cBoxSize.setEnabled(false);
					mSize = "";
				}
			}
		});

		JPanel panelEast = new JPanel();
		ButtonGroup btnGroupTemp = new ButtonGroup();
		panelEast.setLayout(new GridLayout(2, 1));
		JPanel panelEastUp = new JPanel(new GridLayout(2, 1));

		JPanel panelEastTemp = new JPanel(new GridLayout(1, 1));
		btnGroupTemp.add(rBtnHot);
		btnGroupTemp.add(rBtnIce);
		panelEastTemp.setBorder(new TitledBorder("HOT/ICE"));
		panelEastTemp.add(rBtnHot);
		panelEastTemp.add(rBtnIce);
		JPanel panelEastSize = new JPanel();
		cBoxSize.addItem("선택해주세요");
		cBoxSize.addItem("small");
		cBoxSize.addItem("medium");
		cBoxSize.addItem("large");
		panelEastSize.setBorder(new TitledBorder("SIZE"));
		panelEastSize.add(cBoxSize);
		panelEastUp.add(panelEastTemp);
		panelEastUp.add(panelEastSize);
		panelEast.add(panelEastUp);

		JTable tableSelected = new JTable(modelSelectedTable);
		JScrollPane scrollPan2 = new JScrollPane(tableSelected);
		JPanel panelEastSelectedTable = new JPanel();
		TableColumnModel tcm2 = tableSelected.getColumnModel();
		tcm2.getColumn(0).setCellRenderer(dtcr);
		tcm2.getColumn(1).setCellRenderer(dtcr);
		tcm2.getColumn(2).setCellRenderer(dtcr);
		tcm2.getColumn(0).setPreferredWidth(200/(tcm2.getColumnCount()*2)*4);
		tcm2.getColumn(1).setPreferredWidth(200/(tcm2.getColumnCount()*2));
		tcm2.getColumn(2).setPreferredWidth(200/(tcm2.getColumnCount()*2));
		panelEastSelectedTable.setLayout(new BorderLayout());
		panelEastSelectedTable.add(scrollPan2, "Center");
		scrollPan2.setPreferredSize(new Dimension(150, 500));
		panelEastSelectedTable.setBorder(new TitledBorder("장바구니"));
		panelEast.add(panelEastSelectedTable);

		rBtnHot.addItemListener(this);
		rBtnIce.addItemListener(this);
		cBoxSize.addItemListener(this);

		JPanel panelSouth = new JPanel(new GridLayout(1, 8));
		JLabel labelWelcome = new JLabel(user.getpID() + "님");
		// DTO_Customer에서 제대로 받아오게끔 수정해야한다.
		panelSouth.add(labelWelcome);
		panelSouth.add(new JLabel());
		panelSouth.add(btnSearch);
		panelSouth.add(new JLabel());
		panelSouth.add(new JLabel());
		panelSouth.add(btnPut);
		panelSouth.add(new JLabel());
		panelSouth.add(btnOrder);
		btnSearch.addActionListener(this);
		btnOrder.addActionListener(this);
		btnPut.addActionListener(this);

		setLayout(new BorderLayout());

		add(panelWest, "West");
		add(panelCenter, "Center");
		add(panelEast, "East");
		add(panelSouth, "South");

		dao.selectAll(menuList);
		updateMenuTable(menuList);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (obj == rBtnCoffeeMenu) {
				dao.selectType(menuList, "C");
				updateMenuTable(menuList);
			}
			else if (obj == rBtnDessertMenu) {
				dao.selectType(menuList, "D");
				updateMenuTable(menuList);
				}
			else if (obj == rBtnEtcMenu) {
				dao.selectType(menuList, "A");
				updateMenuTable(menuList);
			}
			if (obj == rBtnHot)
				mTem = "h";
			else if (obj == rBtnIce)
				mTem = "i";
			if (obj == cBoxSize) {
				if (cBoxSize.getSelectedIndex() != 0) {
					mSize = e.getItem().toString().substring(0, 1);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			String name = null;
			name = textFieldSearchName.getText().trim();

			if (name.length() > 0) {
				boolean result = false;
				result = dao.selectName(menuList, name);
				updateMenuTable(menuList);
				if (!result) {
					JOptionPane.showMessageDialog(this, "검색되는 상품이 없습니다.", "검색", JOptionPane.YES_OPTION);
				}
			} else {
				JOptionPane.showMessageDialog(this, "상품명을 입력해주세요", "검색", JOptionPane.YES_OPTION);
			}
		} else {
			String menuNo = tableMenu.getValueAt(tableMenu.getSelectedRow(), 0).toString();
			String menuName = tableMenu.getValueAt(tableMenu.getSelectedRow(), 1).toString();
			int menuPrice = Integer.parseInt(tableMenu.getValueAt(tableMenu.getSelectedRow(), 2).toString());
			if (menuNo.substring(0, 1).equals("C")) {
				if (mTem.equals("") || mSize.equals("")) {
					JOptionPane.showMessageDialog(this, "옵션을 선택해주세요", "옵션", JOptionPane.YES_OPTION);
					return;
				}
			} else if (menuNo.substring(0, 1).equals("A")) {
				if (mSize.equals("")) {
					JOptionPane.showMessageDialog(this, "옵션을 선택해주세요", "옵션", JOptionPane.YES_OPTION);
					return;
				}
			}
			DTO_Menu menu = new DTO_Menu(menuNo, menuName, menuPrice);
			DTO_History history = new DTO_History(1, user.getpID(), menu.getcName(), mSize, mTem, menu.getcPrice());

			if (e.getSource() == btnPut) {
				shoppingBasket.add(history);
				for (int i = 0; i < modelSelectedTable.getRowCount();) {
					modelSelectedTable.removeRow(0);
				}
				for (DTO_History dto : shoppingBasket) {
					Object data[] = { dto.gethMenu(), dto.gethSize(), dto.gethOption() };
					modelSelectedTable.addRow(data);
				}
			} else if (e.getSource() == btnOrder) {
				if (shoppingBasket.isEmpty()) {
					int result = JOptionPane.showConfirmDialog(this, "선택하신 상품을 주문 하시겠습니까?", "주문내역",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == 0) {
						history.sethNo(dao.getHistoryNo());
						result = dao.insertOrder(history);
					}
					else
						return;
					if (result > 0) {
						System.out.println("insert :" + history);
					} else {
						System.out.println("insert fail...");
					}
				} else {
					int count = 0;
					int result = JOptionPane.showConfirmDialog(this, "장바구니의 상품을 주문 하시겠습니까?", "주문내역",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == 0) {
						for (DTO_History dto : shoppingBasket) {
							dto.sethNo(dao.getHistoryNo());
							result = dao.insertOrder(dto);
							if (result > 0) {
								count += 1;
							}
							result = 0;
						}
						if (shoppingBasket.size() == count) {
							// commit;
							System.out.println("insert Commit");
							shoppingBasket.clear();
							for (int i = 0; i < modelSelectedTable.getRowCount();) {
								modelSelectedTable.removeRow(0);
							}
						} else {
							// rollback;
							System.out.println("insert Rollback");
						}
					} else
						return;
				}

			}
		}
	}
	
	void updateMenuTable(ArrayList<DTO_Menu> list) {
		for (int i = 0; i < modelMenuTable.getRowCount();) {
			modelMenuTable.removeRow(0);
		}
		for(DTO_Menu dto : list) {
			Object[] data = { dto.getcNo(), dto.getcName(), dto.getcPrice() };
			modelMenuTable.addRow(data);
		}
		list.clear();
	}
}
