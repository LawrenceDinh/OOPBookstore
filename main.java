

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;

public class main extends JPanel
{
    private JList<String> itemlist;

    private CountDownLatch latch;
    private int option; 
    int[] selected = null;
    private choiceSelected intInput;
	 //public static JTextField searchWords;
	 //public static HashSet<Long> list = new HashSet<Long>(); 
	 
	 
	// public DragAndDrop drp = new DragAndDrop();  
    


  public main(CountDownLatch signal, int select, choiceSelected c) {

	  intInput = c;
	  latch = signal;
	  option = select;
		DefaultListModel<String> options = new DefaultListModel<>();
	    options.addElement("Post an Item");
	    options.addElement("Search for Item");
	    options.addElement("Search for User");
	    options.addElement("Message User");
	    options.addElement("Display Messages"); //optional
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
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.setContentPane(new main(latch, option, intInput));
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
	 
	 choiceSelected temp;
	 PrintListener(choiceSelected c){
		 c = intInput;
		 temp = c;
	 }
	 
	 
      public void actionPerformed(ActionEvent e) {
    	int n = search();
		//c.setChoice(n);
		//System.out.println("yay: " + n );
		temp.setChoice(n);
		latch.countDown();

          }
      }
  
  public int search(){
	  selected = itemlist.getSelectedIndices();
 
          int choice = selected[0];
          setOption(choice);

          return choice;
          
  			
  }


 
}