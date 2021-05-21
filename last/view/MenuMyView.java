package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import dao.CustomerDAO;
import dao.MenuDAO;
import dto.CustomerVO;
import dto.HistoryVO;
import dto.MenuVO;

//210518 11:30	라디오버튼 -전체보기 추가
//				옵션 HOT/ICE 라디오버튼 -> 체크박스로 변경
//				옵션, 사이즈 DB 칼럼값 변경으로 Hot/Ice , Medium/Large 로 변경
//210518 12:47	Hot/Ice 기타류에서 선택 안되게 변경
//210518 16:57	장바구니 삭제 부분 추가 / 장바구니 전체 주문 메소드 변경
//210518 18:01	ImageIcon 부분 안나오는 부분 수정

public class MenuMyView extends JPanel implements ActionListener, ItemListener {
	JRadioButton rBtnAllMenu = new JRadioButton("전체보기", true);
	JRadioButton rBtnCoffeeMenu = new JRadioButton("커 피 류", false);
	JRadioButton rBtnEtcMenu = new JRadioButton("기 타 류", false);
	JRadioButton rBtnDessertMenu = new JRadioButton("디 저 트", false);
	JTextField textFieldSearchName = new JTextField(12);
	Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);

	JCheckBox cBoxHot = new JCheckBox("Hot", false);
	JCheckBox cBoxIce = new JCheckBox("Ice", false);
	JComboBox cBoxSize = new JComboBox();
	public String mTem = "";
	public String mSize = "";

	JButton btnSearch = new JButton("검  색");
	JButton btnPut = new JButton("상품담기");
	JButton btnDel = new JButton("상품빼기");
	JButton btnOrder = new JButton("주  문");

	ImageIcon one = new ImageIcon("empty.png");
	ImageIcon americano = new ImageIcon("americano.png");
	ImageIcon apple = new ImageIcon("apple.png");
	ImageIcon bagle = new ImageIcon("bagle.png");
	ImageIcon berrybingsoo = new ImageIcon("berrybingsoo.png");
	ImageIcon blackdanglatte = new ImageIcon("blackdanglatte.png");
	ImageIcon blueberry = new ImageIcon("blueberry.png");
	ImageIcon cafelatte = new ImageIcon("cafelatte.png");
	ImageIcon cafemoca = new ImageIcon("cafemoca.png");
	ImageIcon capuchino = new ImageIcon("capuchino.png");
	ImageIcon caramell = new ImageIcon("caramell.png");
	ImageIcon cheesecake = new ImageIcon("cheesecake.png");
	ImageIcon chocolatte = new ImageIcon("chocolatte.png");
	ImageIcon chocomupin = new ImageIcon("chocomupin.png");
	ImageIcon coldbrew = new ImageIcon("coldbrew.png");
	ImageIcon coldbrewlatte = new ImageIcon("coldbrewlatte.png");
	ImageIcon dolchecoldbrew = new ImageIcon("dolchecoldbrew.png");
	ImageIcon dolchelatte = new ImageIcon("dolchelatte.png");
	ImageIcon espresso = new ImageIcon("espresso.png");
	ImageIcon greentealatte = new ImageIcon("greentealatte.png");
	ImageIcon honeybread = new ImageIcon("honeybread.png");
	ImageIcon jamong = new ImageIcon("jamong.png");
	ImageIcon jamonghoney = new ImageIcon("jamonghoney.png");
	ImageIcon javachip = new ImageIcon("javachip.png");
	ImageIcon kiwi = new ImageIcon("kiwi.png");
	ImageIcon lemon = new ImageIcon("lemon.png");
	ImageIcon mintchococake = new ImageIcon("mintchococake.png");
	ImageIcon mintchocochip = new ImageIcon("mintchocochip.png");
	ImageIcon musho = new ImageIcon("musho.png");
	ImageIcon strawberry = new ImageIcon("strawberry.png");
	ImageIcon tiramisoo = new ImageIcon("tiramisoo.png");
	ImageIcon vanilalatte = new ImageIcon("vanilalatte.png");

	CustomerVO user = new CustomerVO();
	CustomerDAO custdao = new CustomerDAO();

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
	JTable tableSelected = new JTable(modelSelectedTable);

	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	MenuDAO dao = new MenuDAO();
	ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
	ArrayList<HistoryVO> shoppingBasket = new ArrayList<HistoryVO>();

	CustomerMyView customermyview;

	public MenuMyView(String id, CustomerMyView custmyview) {
		tableMenu.setFont(new Font("Serif",Font.PLAIN, 20));
		   tableMenu.setRowHeight(tableMenu.getRowHeight() + 10);
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
		btnSearch.setFont(font);
		btnPut.setFont(font);
		btnDel.setFont(font);
		btnOrder.setFont(font);
		rBtnAllMenu.setFont(font);
		rBtnCoffeeMenu.setFont(font);
		rBtnEtcMenu.setFont(font);
		rBtnDessertMenu.setFont(font);
		customermyview = custmyview;
		user.setPid(id);
		Color b = new Color(248, 234, 221);
		JPanel panelWest = new JPanel();
		panelWest.setBackground(b);
		ButtonGroup btnGroupType = new ButtonGroup();
		JLabel labelSearchName = new JLabel("상품명", SwingConstants.CENTER);
		labelSearchName.setFont(font);
		panelWest.setLayout(new GridLayout(3, 1));
		JPanel panelWestType = new JPanel(new GridLayout(4, 1));
		panelWestType.setBackground(b);
		btnGroupType.add(rBtnAllMenu);
		btnGroupType.add(rBtnCoffeeMenu);
		btnGroupType.add(rBtnEtcMenu);
		btnGroupType.add(rBtnDessertMenu);
		rBtnAllMenu.setBackground(b);
		rBtnCoffeeMenu.setBackground(b);
		rBtnEtcMenu.setBackground(b);
		rBtnDessertMenu.setBackground(b);
		panelWestType.setBorder(new TitledBorder("종류"));
		panelWestType.add(rBtnAllMenu);
		panelWestType.add(rBtnCoffeeMenu);
		panelWestType.add(rBtnEtcMenu);
		panelWestType.add(rBtnDessertMenu);

		panelWest.add(panelWestType);
		JPanel panelWestSearch = new JPanel(new GridLayout(6, 1));
		panelWestSearch.setBorder(new TitledBorder("검색"));
		panelWestSearch.add(labelSearchName);
		panelWestSearch.add(textFieldSearchName);
		panelWest.add(panelWestSearch);
		panelWestSearch.setBackground(b);

		JLabel coffee = new JLabel();
		coffee.setIcon(one);
		panelWest.add(coffee);

		panelWest.setPreferredSize(new Dimension(150, 500));
		rBtnAllMenu.addItemListener(this);
		rBtnCoffeeMenu.addItemListener(this);
		rBtnEtcMenu.addItemListener(this);
		rBtnDessertMenu.addItemListener(this);

		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(b);
		JScrollPane scrollPan1 = new JScrollPane(tableMenu);
		scrollPan1.setBackground(b);
		TableColumnModel tcm1 = tableMenu.getColumnModel();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		tcm1.getColumn(0).setCellRenderer(dtcr);
		tcm1.getColumn(1).setCellRenderer(dtcr);
		tcm1.getColumn(2).setCellRenderer(dtcr);
		tcm1.getColumn(0).setPreferredWidth(400 / (tcm1.getColumnCount() * 2));
		tcm1.getColumn(1).setPreferredWidth(400 / (tcm1.getColumnCount() * 2) * 4);
		tcm1.getColumn(2).setPreferredWidth(400 / (tcm1.getColumnCount() * 2));
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(scrollPan1, "Center");
		panelCenter.setBorder(new TitledBorder("메뉴"));
		cBoxHot.setBackground(b);
		cBoxIce.setBackground(b);
		cBoxHot.setFont(font);
		cBoxIce.setFont(font);
		cBoxSize.setFont(font);
		tableMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tableMenu.getSelectedRow();
				String no = tableMenu.getValueAt(row, 0).toString().substring(0, 1);
//메뉴에 따라 Ice, Hot 수정
				String menuName = tableMenu.getValueAt(tableMenu.getSelectedRow(), 1).toString();
				System.out.println(menuName);
				if (menuName.equals("아메리카노") || menuName.equals("카페라떼")
						|| menuName.equals("카페모카") || menuName.equals("바닐라라떼")
						|| menuName.equals("초코라떼") || menuName.equals("돌체라떼")
						|| menuName.equals("흑당라떼") || menuName.equals("카푸치노")
						|| menuName.equals("카라멜마끼아또") || menuName.equals("에스프레소")
						|| menuName.equals("자몽허니블랙티") || menuName.equals("제주한라산녹차라떼") ) {
					cBoxHot.setEnabled(true);
					cBoxIce.setEnabled(true);
					cBoxHot.setSelected(false);
					cBoxIce.setSelected(false);
					cBoxSize.setEnabled(true);
				} else if (no.equals("A") ||  menuName.equals("자바칩프라푸치노") || menuName.equals("민트초코칩프라푸치노") 
						|| menuName.equals("콜드브루") || menuName.equals("콜드브루라떼") || menuName.equals("돌체콜드브루")) {
					cBoxHot.setEnabled(false);
					cBoxIce.setEnabled(false);
					cBoxHot.setSelected(false);
					cBoxIce.setSelected(true);
					mTem = "Ice";
					cBoxSize.setEnabled(true);
				} else if (no.equals("D")) {
					cBoxHot.setEnabled(false);
					cBoxIce.setEnabled(false);
					cBoxHot.setSelected(false);
					cBoxIce.setSelected(false);
					mTem = "";
					cBoxSize.setSelectedIndex(0);
					cBoxSize.setEnabled(false);
					mSize = "";
				}
				String coffeeno = tableMenu.getValueAt(row, 0).toString();

				if (coffeeno.equals("A000")) {
					coffee.setIcon(jamong);
				} else if (coffeeno.equals("A001")) {
					coffee.setIcon(lemon);
				} else if (coffeeno.equals("A002")) {
					coffee.setIcon(apple);
				} else if (coffeeno.equals("A003")) {
					coffee.setIcon(strawberry);
				} else if (coffeeno.equals("A004")) {
					coffee.setIcon(blueberry);
				} else if (coffeeno.equals("A005")) {
					coffee.setIcon(kiwi);
				} else if (coffeeno.equals("C001")) {
					coffee.setIcon(americano);
				} else if (coffeeno.equals("C002")) {
					coffee.setIcon(cafelatte);
				} else if (coffeeno.equals("C003")) {
					coffee.setIcon(cafemoca);
				} else if (coffeeno.equals("C004")) {
					coffee.setIcon(vanilalatte);
				} else if (coffeeno.equals("C005")) {
					coffee.setIcon(chocolatte);
				} else if (coffeeno.equals("C006")) {
					coffee.setIcon(dolchelatte);
				} else if (coffeeno.equals("C007")) {
					coffee.setIcon(javachip);
				} else if (coffeeno.equals("C008")) {
					coffee.setIcon(blackdanglatte);
				} else if (coffeeno.equals("C009")) {
					coffee.setIcon(mintchocochip);
				} else if (coffeeno.equals("C010")) {
					coffee.setIcon(coldbrew);
				} else if (coffeeno.equals("C011")) {
					coffee.setIcon(coldbrewlatte);
				} else if (coffeeno.equals("C012")) {
					coffee.setIcon(capuchino);
				} else if (coffeeno.equals("C013")) {
					coffee.setIcon(caramell);
				} else if (coffeeno.equals("C014")) {
					coffee.setIcon(espresso);
				} else if (coffeeno.equals("C015")) {
					coffee.setIcon(jamonghoney);
				} else if (coffeeno.equals("C016")) {
					coffee.setIcon(greentealatte);
				} else if (coffeeno.equals("C017")) {
					coffee.setIcon(dolchecoldbrew);
				} else if (coffeeno.equals("D000")) {
					coffee.setIcon(cheesecake);
				} else if (coffeeno.equals("D001")) {
					coffee.setIcon(musho);
				} else if (coffeeno.equals("D002")) {
					coffee.setIcon(honeybread);
				} else if (coffeeno.equals("D003")) {
					coffee.setIcon(chocomupin);
				} else if (coffeeno.equals("D004")) {
					coffee.setIcon(bagle);
				} else if (coffeeno.equals("D005")) {
					coffee.setIcon(tiramisoo);
				} else if (coffeeno.equals("D006")) {
					coffee.setIcon(mintchococake);
				} else if (coffeeno.equals("D007")) {
					coffee.setIcon(berrybingsoo);
				}
			}
		});

		JPanel panelEast = new JPanel();
		panelEast.setBackground(b);
		panelEast.setLayout(new GridLayout(2, 1));
		JPanel panelEastUp = new JPanel(new GridLayout(2, 1));
		JPanel panelEastTemp = new JPanel(new GridLayout(1, 1));
		panelEastUp.setBackground(b);
		panelEastTemp.setBackground(b);
		panelEastTemp.setBorder(new TitledBorder("HOT/ICE"));
		panelEastTemp.add(cBoxHot);
		panelEastTemp.add(cBoxIce);
		JPanel panelEastSize = new JPanel();
		panelEastSize.setBackground(b);
		cBoxSize.addItem("선택해주세요");
		cBoxSize.addItem("Medium");
		cBoxSize.addItem("Large");
		panelEastSize.setBorder(new TitledBorder("SIZE"));
		panelEastSize.add(cBoxSize);
		panelEastUp.add(panelEastTemp);
		panelEastUp.add(panelEastSize);
		panelEast.add(panelEastUp);

		JScrollPane scrollPan2 = new JScrollPane(tableSelected);
		JPanel panelEastSelectedTable = new JPanel();
		scrollPan2.setBackground(b);
		panelEastSelectedTable.setBackground(b);
		TableColumnModel tcm2 = tableSelected.getColumnModel();
		tcm2.getColumn(0).setCellRenderer(dtcr);
		tcm2.getColumn(1).setCellRenderer(dtcr);
		tcm2.getColumn(2).setCellRenderer(dtcr);
		tcm2.getColumn(0).setPreferredWidth(200 / (tcm2.getColumnCount() * 2) * 4);
		tcm2.getColumn(1).setPreferredWidth(200 / (tcm2.getColumnCount() * 2));
		tcm2.getColumn(2).setPreferredWidth(200 / (tcm2.getColumnCount() * 2));
		panelEastSelectedTable.setLayout(new BorderLayout());
		panelEastSelectedTable.add(scrollPan2, "Center");
		scrollPan2.setPreferredSize(new Dimension(150, 500));
		panelEastSelectedTable.setBorder(new TitledBorder("장바구니"));
		panelEast.add(panelEastSelectedTable);
		tableSelected.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("" +tableSelected.getSelectedRow());
			}
		});
		cBoxHot.addItemListener(this);
		cBoxIce.addItemListener(this);
		cBoxSize.addItemListener(this);

		JPanel panelSouth = new JPanel(new GridLayout(1, 8));
		panelSouth.setBackground(b);
		JLabel labelWelcome = new JLabel(user.getPid() + "님");
		labelWelcome.setFont(font);
		panelSouth.add(labelWelcome);
		panelSouth.add(new JLabel());
		panelSouth.add(btnSearch);
		panelSouth.add(new JLabel());
		panelSouth.add(btnPut);
		panelSouth.add(btnDel);
		panelSouth.add(new JLabel());
		panelSouth.add(btnOrder);
		btnSearch.addActionListener(this);
		btnOrder.addActionListener(this);
		btnPut.addActionListener(this);
		btnDel.addActionListener(this);

		setLayout(new BorderLayout());

		add(panelWest, "West");
		add(panelCenter, "Center");
		add(panelEast, "East");
		add(panelSouth, "South");
		setBackground(b);

		dao.showMenuAll(modelMenuTable);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (obj == rBtnAllMenu) {
				dao.selectType(modelMenuTable, "");
				cBoxHot.setEnabled(true);
				cBoxIce.setEnabled(true);
				cBoxHot.setSelected(false);
				cBoxIce.setSelected(false);
				cBoxSize.setEnabled(true);
				tableMenu.clearSelection();
			} else if (obj == rBtnCoffeeMenu) {
				dao.selectType(modelMenuTable, "C");
				cBoxHot.setEnabled(true);
				cBoxIce.setEnabled(true);
				cBoxHot.setSelected(false);
				cBoxIce.setSelected(false);
				cBoxSize.setEnabled(true);
				cBoxSize.setEnabled(true);
				tableMenu.clearSelection();
			} else if (obj == rBtnDessertMenu) {
				dao.selectType(modelMenuTable, "D");
				cBoxHot.setEnabled(false);
				cBoxIce.setEnabled(false);
				cBoxHot.setSelected(false);
				cBoxIce.setSelected(false);
				mTem = null;
				cBoxSize.setSelectedIndex(0);
				cBoxSize.setEnabled(false);
				mSize = null;
				tableMenu.clearSelection();
			} else if (obj == rBtnEtcMenu) {
				dao.selectType(modelMenuTable, "A");
				cBoxHot.setEnabled(false);
				cBoxIce.setEnabled(false);
				cBoxHot.setSelected(false);
				cBoxIce.setSelected(true);
				mTem = "Ice";
				cBoxSize.setEnabled(true);
				tableMenu.clearSelection();
			}
			if (obj == cBoxHot) {
				mTem = "Hot";
				cBoxIce.setSelected(false);
			} else if (obj == cBoxIce) {
				mTem = "Ice";
				cBoxHot.setSelected(false);
			}
			if (obj == cBoxSize) {
				if (cBoxSize.getSelectedIndex() != 0) {
					mSize = e.getItem().toString();
				}
			}
		} else if (e.getStateChange() == ItemEvent.DESELECTED) {
			System.out.println("deselected");
			if (obj == cBoxHot) {

				mTem = "";
				cBoxHot.setSelected(false);
				cBoxIce.setSelected(false);
				System.out.println("deselected Hot button : " + mTem);
			} else if (obj == cBoxIce) {
				mTem = "";
				cBoxHot.setSelected(false);
				cBoxIce.setSelected(false);
				System.out.println("deselected Ice button : " + mTem);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Font font = new Font("MAKGEOLLI", Font.PLAIN, 20);
		if (e.getSource() == btnSearch) {
			String name = null;
			name = textFieldSearchName.getText().trim();

			if (name.length() > 0) {
				boolean result = false;
				result = dao.searchName(modelMenuTable, name);
				if (!result) {
					JLabel lab1 = new JLabel("검색되는 상품이 없습니다.");
					lab1.setFont(font);
					JOptionPane.showMessageDialog(this, lab1, "검색", JOptionPane.YES_OPTION);
				}
			} else {
				JLabel lab2 = new JLabel("상품명을 입력해주세요");
				lab2.setFont(font);
				JOptionPane.showMessageDialog(this, lab2, "검색", JOptionPane.YES_OPTION);
			}
		} else if (e.getSource() == btnDel) {
			if (tableSelected.getRowCount() > 0) {
				System.out.println("selected table row num : " + tableSelected.getSelectedRow());
				System.out.println("selected table history : " + shoppingBasket.get(tableSelected.getSelectedRow()));
				System.out.println("tabelSelected row num: " + tableSelected.getSelectedRow());
				HistoryVO d = shoppingBasket.remove(tableSelected.getSelectedRow());
				System.out.println(d +"");
				for (int i = 0; i < modelSelectedTable.getRowCount();) {
					modelSelectedTable.removeRow(0);
				}
				for (HistoryVO dto : shoppingBasket) {
					Object data[] = { dto.gethMenu(), dto.gethSize(), dto.gethOption() };
					modelSelectedTable.addRow(data);
				}
			}
		} else if (e.getSource() == btnOrder) {
			if (shoppingBasket.isEmpty()) {
				if (tableMenu.getSelectedColumn() > 0) {
					String menuNo = tableMenu.getValueAt(tableMenu.getSelectedRow(), 0).toString();
					String menuName = tableMenu.getValueAt(tableMenu.getSelectedRow(), 1).toString();
					int menuPrice = Integer.parseInt(tableMenu.getValueAt(tableMenu.getSelectedRow(), 2).toString());
//if 조건 수정				
					if (menuName.equals("아메리카노") || menuName.equals("카페라떼")
							|| menuName.equals("카페모카") || menuName.equals("바닐라라떼")
							|| menuName.equals("초코라떼") || menuName.equals("돌체라떼")
							|| menuName.equals("흑당라떼") || menuName.equals("카푸치노")
							|| menuName.equals("카라멜마끼아또") || menuName.equals("에스프레소")
							|| menuName.equals("자몽허니블랙티") || menuName.equals("제주한라산녹차라떼")) {
						if (mTem.equals("") || mSize.equals("")) {
							JLabel lab3 = new JLabel("옵션을 선택해주세요");
							lab3.setFont(font);
							JOptionPane.showMessageDialog(this, lab3, "옵션", JOptionPane.YES_OPTION);
							return;
						}
//else if 조건 수정					
					} else if (menuNo.substring(0, 1).equals("A") || menuName.equals("자바칩프라푸치노") || menuName.equals("민트초코칩프라푸치노") 
							|| menuName.equals("콜드브루") || menuName.equals("콜드브루라떼") || menuName.equals("돌체콜드브루")) {
						if (mSize.equals("")) {
							JLabel lab4 = new JLabel("옵션을 선택해주세요");
							lab4.setFont(font);
							JOptionPane.showMessageDialog(this, lab4, "옵션", JOptionPane.YES_OPTION);
							return;
						}
					}
					MenuVO menu = new MenuVO(menuNo, menuName, menuPrice);
					HistoryVO history = new HistoryVO(user.getPid(), menu.getcName(), mSize, mTem);

					JLabel lab5 = new JLabel("선택하신 상품을 주문 하시겠습니까?");
					lab5.setFont(font);
					int result = JOptionPane.showConfirmDialog(this, lab5, "주문내역", JOptionPane.YES_NO_OPTION);
					if (result == 0) {
						history.sethNo(dao.getHistoryNo());
						result = dao.insertOrder(history);
						custdao.countHistory(customermyview);
						custdao.checkStamp(customermyview);
					} else
						return;
				}else{
					JLabel noSelected = new JLabel("메뉴를 선택해주세요");
					noSelected.setFont(font);
					JOptionPane.showMessageDialog(this, noSelected, "옵션", JOptionPane.YES_OPTION);
					
				}
			} else {
				int count = 0;
				JLabel lab6 = new JLabel("장바구니의 상품을 주문 하시겠습니까?");
				lab6.setFont(font);
				int result = JOptionPane.showConfirmDialog(this, lab6, "주문내역", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					result = dao.insertShoppingBasket(shoppingBasket);
					if (result == shoppingBasket.size()) {
						shoppingBasket.clear();
						for (int i = 0; i < modelSelectedTable.getRowCount();) {
							modelSelectedTable.removeRow(0);
						}
					} else {
						JLabel lab7 = new JLabel("장바구니의 상품 주문에 실패 하였습니다.");
						lab7.setFont(font);
						JOptionPane.showMessageDialog(this, lab7);
					}
					custdao.countHistory(customermyview);
					custdao.checkStamp(customermyview);
				} else
					return;
			}
		} else if (e.getSource() == btnPut) {
			String menuNo = tableMenu.getValueAt(tableMenu.getSelectedRow(), 0).toString();
			String menuName = tableMenu.getValueAt(tableMenu.getSelectedRow(), 1).toString();
			int menuPrice = Integer.parseInt(tableMenu.getValueAt(tableMenu.getSelectedRow(), 2).toString());
			if (menuNo.substring(0, 1).equals("C")) {
				if (mTem.equals("") || mSize.equals("")) {
					JLabel lab3 = new JLabel("옵션을 선택해주세요");
					lab3.setFont(font);
					JOptionPane.showMessageDialog(this, lab3, "옵션", JOptionPane.YES_OPTION);
					return;
				}
			} else if (menuNo.substring(0, 1).equals("A")) {
				if (mSize.equals("")) {
					JLabel lab4 = new JLabel("옵션을 선택해주세요");
					lab4.setFont(font);
					JOptionPane.showMessageDialog(this, lab4, "옵션", JOptionPane.YES_OPTION);
					return;
				}
			}
			MenuVO menu = new MenuVO(menuNo, menuName, menuPrice);
			HistoryVO history = new HistoryVO(user.getPid(), menu.getcName(), mSize, mTem);

			shoppingBasket.add(history);
			for (int i = 0; i < modelSelectedTable.getRowCount();) {
				modelSelectedTable.removeRow(0);
			}
			for (HistoryVO dto : shoppingBasket) {
				Object data[] = { dto.gethMenu(), dto.gethSize(), dto.gethOption() };
				modelSelectedTable.addRow(data);
			}

		}

	}
}
