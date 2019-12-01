

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;

public class DashboardFrame extends JPanel
{
    private JList<String> itemlist;

    private CountDownLatch latch;
    private int option; 
    int[] selected = null;
    private DashboardChoiceSelected intInput;
	 //public static JTextField searchWords;
	 //public static HashSet<Long> list = new HashSet<Long>(); 
	 
	 
	// public DragAndDrop drp = new DragAndDrop();  
    


  public DashboardFrame(CountDownLatch signal, int select, DashboardChoiceSelected c) {

	  intInput = c;
	  latch = signal;
	  option = select;
		DefaultListModel<String> options = new DefaultListModel<>();
	    options.addElement("Post an Item");
	    options.addElement("Search for Item");
	    options.addElement("View your posted items");
	    options.addElement("Change Password");
	    options.addElement("Exit Program"); //optional
        itemlist = new JList<>(options);  
	  
      setLayout(new BorderLayout());
      
      JButton button = new JButton("Select");
      button.setFont(new Font("Arial",Font.BOLD,14));
      button.addActionListener(new PrintListener(intInput));

      //itemlist = new JList(items);
      itemlist.setFont(new Font("Arial",Font.BOLD,14));
   
      itemlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JScrollPane pane = new JScrollPane(itemlist);
      pane.setPreferredSize(new Dimension(500, 200));
      add(pane, BorderLayout.NORTH);
      add(button, BorderLayout.SOUTH);
      //add(post, BorderLayout.WEST);
      //add(null, BorderLayout.EAST);
  }
  
public int getChoice() {
	return option;
}

  public void run() throws InterruptedException {
	  
      JFrame frame = new JFrame("Dashboard User Interface");
      frame.setTitle("Dashboard");
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.setContentPane(new DashboardFrame(latch, option, intInput));
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

  // An inner class to respond to clicks on the Print button
 class PrintListener implements ActionListener{
	 
	 DashboardChoiceSelected temp;
	 PrintListener(DashboardChoiceSelected c){
		 c = intInput;
		 temp = c;
	 }
	 
	 
      public void actionPerformed(ActionEvent e) {
    	int n = search();
		//c.setChoice(n);
		//System.out.println("yay: " + n );
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