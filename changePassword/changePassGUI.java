package changePassword;

import javax.swing.*;

import java.awt.Font;
import java.awt.GridLayout;
//Java program to create a blank text  
//field of definite number of columns. 
import java.awt.event.*; 
import javax.swing.*; 
class changePassGUI extends JFrame implements ActionListener { 
    // JTextField 
    static JTextField t; 
  
    // JFrame 
    static JFrame f; 
  
    // JButton 
    static JButton b; 
    static JButton exit; 
    // label to display text 
    static JLabel l, title; 
  
    // default constructor 
    changePassGUI() 
    { 
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
        changePassGUI te = new changePassGUI(); 
        

        
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
			  }
			  
			});
        f.show(); 
    } 
  
    // if the vutton is pressed 
    public void actionPerformed(ActionEvent e) 
    { 
        String s = e.getActionCommand(); 
        if (s.equals("Confirm Changes")) { 
            // set the text of the label to the text of the field 
            l.setText(t.getText()); 
  
            // set the text of field to blank 
            t.setText("  "); 
        } 
    } 
} 