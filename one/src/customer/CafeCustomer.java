package customer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.CustomerMyView;
import view.MenuTab;


public class CafeCustomer extends JFrame implements ComponentListener{

	
	CustomerMyView customerMy;
	MenuTab customerMenu;
	public CafeCustomer(String id) {
		customerMy = new CustomerMyView(id);
		customerMenu=new MenuTab(id);

		JTabbedPane pane = new JTabbedPane();
		
		pane.addTab("메뉴", customerMenu);
		pane.addTab("내 정보 관리", customerMy );
		
		pane.setSelectedIndex(1);	
		
		getContentPane().add("Center", pane).setBackground(Color.red);
	
		setSize(800,800);
		setVisible(true);
		setResizable(true);
		Color b = new Color(255,223,176);  
		pane.setBackground(b);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.addComponentListener(this);
	}
			@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
			@Override

			public void componentResized(ComponentEvent e)

			{

			Dimension di = e.getComponent().getSize();

						

			this.setPreferredSize( di );

			}

		



		

		}
	
