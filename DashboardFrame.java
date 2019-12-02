

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;
/**Construct a dashboard Jframe with 5 options to 'Post an Item', 
 * 'Search for Item', 'View your posted items', 'Change Password', 
 * 'Exit Program', and a JButton to confirm what you selected.
 * @author LUAT DINH
 *
 */
public class DashboardFrame extends JPanel
{
    private JList<String> itemlist;

    private CountDownLatch latch;
    private int option; 
    int[] selected = null;
    private DashboardChoiceSelected intInput;
    private UserClass userName;

	 //public static JTextField searchWords;
	 //public static HashSet<Long> list = new HashSet<Long>(); 
	 
	 
	// public DragAndDrop drp = new DragAndDrop();  
    

//create dashboard frame and attach objects in specific layouts 
	/**
	 * Constructs a DashboardFrame
	 * @param signal this countdownlatch
	 * @param select dashboard choice selected
	 * @param c dashboardchoiceselected getting the int
	 * @param user is the userclass currently logged in
	 */
  public DashboardFrame(CountDownLatch signal, int select, DashboardChoiceSelected c, UserClass user) {
	  userName = user;
	  intInput = c;
	  latch = signal;
	  option = select;
	    String name = userName.getUserName();
	    JLabel loggedUser = new JLabel("Logged in as: " + name);
	    loggedUser.setFont(new Font("Arial", Font.BOLD, 14));
		DefaultListModel<String> options = new DefaultListModel<>();
	    options.addElement("Post an Item");
	    options.addElement("Search for Item");
	    options.addElement("View your posted items");
	    options.addElement("Change Password");
	    options.addElement("Exit Program"); //optional

        itemlist = new JList<>(options);  
	  
      setLayout(new BorderLayout());
      
      JButton button = new JButton("Confirm Selection");
      button.setFont(new Font("Arial",Font.BOLD,14));
      button.addActionListener(new PrintListener(intInput));

      //itemlist = new JList(items);
      itemlist.setFont(new Font("Arial",Font.BOLD,14));
   
      itemlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JScrollPane pane = new JScrollPane(itemlist);
      pane.setPreferredSize(new Dimension(500, 200));
      add(pane, BorderLayout.NORTH);
      add(loggedUser, BorderLayout.WEST);
      add(button, BorderLayout.SOUTH);
      //add(post, BorderLayout.WEST);
      //add(null, BorderLayout.EAST);
  }
  
public int getChoice() {
	return option;
}

/**Construct a new Jframe and attach the dashboard object to it. 
 * 
 *
 */
  public void run() throws InterruptedException {
	  
      JFrame frame = new JFrame("Dashboard User Interface");
      frame.setTitle("Dashboard");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new DashboardFrame(latch, option, intInput, userName));
      frame.setPreferredSize(new Dimension(500, 300));
      frame.pack();
      frame.setVisible(true);
     // System.out.println();
      //System.out.println(selected + "sele");

      //System.out.println("wwwww" + selected[0]);
      intInput.setChoice(option);
      latch.await();
      //System.out.println(option);
    //  while(search)
      //search();
      
      //search();
      frame.setVisible(false);
  }
  

  
  public void setOption(int i)
  {
	  option = i;
  }

  /**ActionListener for when user clicks on 'Confirm Selected' button.
   * Checks if the choice selected is valid and saves choice to 
   * DashboardChoiceSelected.
   *
   */
 class PrintListener implements ActionListener{
	 
	 DashboardChoiceSelected temp;
	 PrintListener(DashboardChoiceSelected c){
		 c = intInput;
		 temp = c;
	 }
	 
	 
      public void actionPerformed(ActionEvent e) {
    	int n = search();
    	if (n == -1) {
        	JOptionPane.showMessageDialog(null, "Please select atleast one field.", "Error!",
					JOptionPane.ERROR_MESSAGE);
    	}
    	else {
		temp.setChoice(n);
		latch.countDown();
    	}
          }
      }
  
 /** Returning the selected choice as an Int.
  *
  * @return int choice the choice selected
  */
  public int search(){
	  if(!itemlist.isSelectionEmpty()){
		  
	  selected = itemlist.getSelectedIndices();
          int choice = selected[0];
          setOption(choice);

          return choice;
	  }
      return -1;
  }


 
}