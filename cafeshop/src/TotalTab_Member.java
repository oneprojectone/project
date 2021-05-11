import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TotalTab_Member extends JFrame{

   public TotalTab_Member() {
      JTabbedPane totalTabPanel = new JTabbedPane();
      MenuTab menuTab = new MenuTab();
      
      totalTabPanel.addTab("¸ñ·Ï", menuTab);
      add(totalTabPanel);
      setSize(800, 500);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   public static void main(String[] args) {
      new TotalTab_Member();
   }

}