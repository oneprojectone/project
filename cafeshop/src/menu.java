import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

class SearchPanel extends JPanel implements ActionListener{
   ButtonGroup btnGroupType = new ButtonGroup();
   
   JRadioButton rBtnCoffeeMenu = new JRadioButton("커 피 류");
   JRadioButton rBtnEtcMenu = new JRadioButton("기 타 류");
   JRadioButton rBtnDessertMenu = new JRadioButton("디 저 트");
   
   JLabel labelSearchName = new JLabel("상품명", SwingConstants.CENTER);
   JTextField textFieldSearchName = new JTextField(12);
   
   
   public SearchPanel() {

      setLayout(new GridLayout(2,1));
   
      
      btnGroupType.add(rBtnCoffeeMenu);
      btnGroupType.add(rBtnEtcMenu);
      btnGroupType.add(rBtnDessertMenu);
      JPanel panelType = new JPanel(new GridLayout(4, 1));
      panelType.add(rBtnCoffeeMenu);
      panelType.add(rBtnEtcMenu);
      panelType.add(rBtnDessertMenu);
      panelType.setBorder(new TitledBorder("종류"));
      add(panelType);
      
      
      JPanel panelSearch = new JPanel(new GridLayout(4, 1));
      panelSearch.add(labelSearchName);
      panelSearch.add(textFieldSearchName);
      panelSearch.setBorder(new TitledBorder("검색"));
      add(panelSearch);
      
      //setSize(200, 500);   
      
      rBtnCoffeeMenu.addActionListener(this);
      rBtnEtcMenu.addActionListener(this);
      rBtnDessertMenu.addActionListener(this);

      
   }
   
   
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      
   }
   
}
class ListPanel extends JPanel{
   String data[][] = new String[0][2]; // 0은 addRow 할 때 시작점, 7은 column의 개수
   String[] colName = { "상품명", "가격" };

   DefaultTableModel model = new DefaultTableModel(data, colName) {
      public boolean isCellEditable(int row, int col) {
         return false;
      }
   };
   JTable table = new JTable(model);
   JScrollPane scrollPan = new JScrollPane(table);
   
   public ListPanel() {
      add(scrollPan);
      setVisible(true);
   }
}
class ButtonPanel extends JPanel implements ActionListener{
   JButton btnSearch, btnOrder;
   
   public ButtonPanel() {
      btnSearch = new JButton("검  색");
      btnOrder = new JButton("주  문");
      
      add(btnSearch);
      add(btnOrder);
      
      btnSearch.addActionListener(this);
      btnOrder.addActionListener(this);
   }

   @Override
   public void actionPerformed(ActionEvent arg0) {
      // TODO Auto-generated method stub
      
   }   
}
class OptionPanel extends JPanel implements ActionListener, ItemListener{
   JLabel labelOpt1 = new JLabel("HOT/ICE", SwingConstants.CENTER);
   ButtonGroup btnGroupTemp = new ButtonGroup();
   JRadioButton jBtnHot = new JRadioButton("HOT");
   JRadioButton jBtnIce = new JRadioButton("ICE");
   
   JLabel labelOpt2 = new JLabel("SIZE", SwingConstants.CENTER);
   JComboBox cBoxSize = new JComboBox();
   
   public OptionPanel() {
      setLayout(new GridLayout(3, 1));
      
      JPanel panelTemp = new JPanel(new GridLayout(3, 1));
      btnGroupTemp.add(jBtnHot);
      btnGroupTemp.add(jBtnIce);
      panelTemp.add(jBtnHot);
      panelTemp.add(jBtnIce);
      panelTemp.setBorder(new TitledBorder("HOT/ICE"));
      add(panelTemp);
      
      JPanel panelSize = new JPanel(new GridLayout(2, 1));
      
      cBoxSize.addItem("Samll");
      cBoxSize.addItem("Medium");
      cBoxSize.addItem("Large");
      panelSize.add(cBoxSize);
      add(panelSize);
         
      jBtnHot.addActionListener(this);
      jBtnIce.addActionListener(this);
      cBoxSize.addItemListener(this);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void itemStateChanged(ItemEvent arg0) {
      // TODO Auto-generated method stub
      
   }
   
   
   
}

class MenuTab extends JPanel{
   public MenuTab() {
      setLayout(new BorderLayout());
      SearchPanel westPanel = new SearchPanel();
      ListPanel centerPanel = new ListPanel();
      OptionPanel eastPanel = new OptionPanel();
      ButtonPanel southPanel = new ButtonPanel();
      
      
      add(westPanel, BorderLayout.WEST);
      add(centerPanel, BorderLayout.CENTER);
      add(southPanel, BorderLayout.SOUTH);
      add(eastPanel, BorderLayout.EAST);
   }
}