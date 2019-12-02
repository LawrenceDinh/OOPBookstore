


import javax.swing.*;


import java.awt.Font;
import java.awt.GridLayout;
//Java program to create a blank text  
//field of definite number of columns. 
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

import javax.swing.*; 
public class changePassGUI extends JFrame implements ActionListener { 
    // JTextField 
    private static JTextField t; 
  
    // JFrame s
    private static JFrame f; 
  
    // JButton 
    private static JButton b; 
    private static JButton exit; 
    // label to display text 
    private static JLabel l, title; 
  
    private UserClass thisUser;
	private CountDownLatch latch;
    // default constructor 
    public changePassGUI(UserClass thisUser2, CountDownLatch cd) 
    { 
    	latch = cd;
    	thisUser = thisUser2;
    } 
  
    // main class 
    public void changePasswordUI()
    {

        // create a new frame to store text field and button 
        f = new JFrame("Change User's Password"); 
  
        title = new JLabel("Change User's Password");
        title.setFont(new Font("Arial", Font.BOLD, 14));
        // create a label to display text 
        l = new JLabel("Nothing entered"); 
  
        // create a new button 
        b = new JButton("Confirm Changes"); 
        exit = new JButton("Close Window"); 
        JLabel n = new JLabel("");
        // create a object of the text class 
        changePassGUI te = new changePassGUI(thisUser, latch); 
        

        
        // addActionListener to button 
        b.addActionListener(te); 
 
  
        // create a object of JTextField with 16 columns 
        t = new JTextField(16); 
  
        // create a panel to add buttons and textfield 
        JPanel p = new JPanel(); 
        p.setLayout(new GridLayout(0, 1));
        // add buttons and textfield to panel 
        p.add(title);
        p.add(t); 
        p.add(l); 
        p.add(n);
        p.add(b); 
        p.add(exit);
  
        // add panel to frame 
        f.add(p); 
  
        // set the size of frame 
        f.setSize(300, 200); 
        exit.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
			    // display/center the jdialog when the button is pressed
				  f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				  f.setVisible(false);
				  f.dispose();
				  latch.countDown();
			  }
			  
			});
        f.setVisible(true); 
    } 
  
    // if the vutton is pressed 
    public void actionPerformed(ActionEvent e) 
    { 
        String s = e.getActionCommand(); 
        if (s.equals("Confirm Changes")) { 
            // set the text of the label to the text of the field 
        	if(!stringEmpty(t.getText())) {
            l.setText(t.getText()); 
            thisUser.setPassword(t.getText());
            System.out.println("New pass: " + t.getText());
            // set the text of field to blank 
            t.setText("  "); 
            //System.out.println(thisUser.getPassword());
			  f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			  f.setVisible(false);
			  f.dispose();
			  latch.countDown();
        	}
        	else {
            	JOptionPane.showMessageDialog(null, "Please enter a new password.", "Error!",
						JOptionPane.ERROR_MESSAGE);

		  }
        } 
    } 
	public boolean stringEmpty(String s) {
		if (s.trim().isEmpty() || s == null) {
			return true;
		}
		return false;
	}
} 