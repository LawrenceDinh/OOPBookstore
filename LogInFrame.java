import javax.swing.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
 
public class LogInFrame extends JFrame{
 
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT =300;
    private boolean cont = true;

    private boolean prev = false;
    static JTextField userNameField;
    static JTextField passwordField;
    private String passwordEntered = null;
    private String usernameEntered = null;
    private UserManager userData;
   
 
    public LogInFrame(UserManager users) throws FileNotFoundException
     {
     userData = users;
     createComponents();
     setSize(FRAME_WIDTH, FRAME_HEIGHT);
     }
   
 
    private void createComponents() throws FileNotFoundException {
        userNameField = new JTextField(10);
        passwordField = new JTextField(10);
 
        JLabel userNameLabel = new JLabel("Enter Username:");
        JLabel passwordLabel = new JLabel("Enter Password:");
        JLabel title = new JLabel("Log In Screen");
        JLabel empty = new JLabel(" ");
        title.setHorizontalAlignment(JLabel.CENTER);
        JButton loginButton = new JButton("Login");
   
        JButton exitButton = new JButton("Exit Program");
        JButton signUpPage = new JButton("Back to Sign Up Page");
       
 
        JPanel panel = new JPanel(new GridLayout(0, 1));
        title.setFont(new Font("Arial", Font.PLAIN, 25));
        
        panel.add(title);
 
        panel.add(empty);
        panel.add(userNameLabel);
        panel.add(userNameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(empty);
        panel.add(signUpPage);
        panel.add(exitButton);

        add(panel);
 
        ActionListener exitListener = new ClickListener1();
        ActionListener loginListener = new ClickListener2();
        ActionListener signUpListener = new ClickListener3();
        exitButton.addActionListener(exitListener);
        loginButton.addActionListener(loginListener);
        signUpPage.addActionListener(signUpListener);
    }
 
    public class ClickListener1 implements ActionListener {
 
        public void actionPerformed(ActionEvent event) {
        	cont = false;
        	setVisible(false);
        }
    }
 
 
 
    public class ClickListener2 implements ActionListener {
 
        public void actionPerformed(ActionEvent event) {
            usernameEntered = userNameField.getText();
            passwordEntered = passwordField.getText();
        //    usernameEntered = "Bob";
          //  passwordEntered = "Pass";
      //      System.out.println(usernameEntered + passwordEntered);
            usernameEntered =  usernameEntered.replaceAll("\\s+","");
           
            passwordEntered =  passwordEntered.replaceAll("\\s+","");
           
            if((usernameEntered.isEmpty()) || (passwordEntered.isEmpty()))
            {
				System.out.println("failed");
            	JOptionPane.showMessageDialog(null, "Error logging into account.\nYou did not enter a valid username or password.\nPlease try again.", "Input Error!",
						JOptionPane.ERROR_MESSAGE);
            }
            
            else if(userData.searchCredentials(usernameEntered, passwordEntered)==null) {
				System.out.println("failed");
				System.out.println("w: " + usernameEntered + passwordEntered);
            	JOptionPane.showMessageDialog(null, "Error logging into account.\nPlease try again.", "Invalid Info!",
						JOptionPane.ERROR_MESSAGE);
            }
            else {
				System.out.println("w: " + usernameEntered + passwordEntered);
            	UserClass found = userData.searchCredentials(usernameEntered, passwordEntered);
            	JOptionPane.showMessageDialog(null, "You have successfully logged in.", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
            	System.out.println(found.getUserName());
            	setVisible(false);
            	cont = false;
            	
            	//call the next DASHBOARD GUI
            
            }
             //connected to the userData userManager object. 
 
            
   
            
            
        }
        
    }
   
   
    public class ClickListener3 implements ActionListener {
    	 
        public void actionPerformed(ActionEvent event) {

        	cont = true;
        	prev = true;
            System.out.println("back");
            setVisible(false);

            try {
                LogInFrame n = new LogInFrame(userData);
				new SignUpFrame(userData, n).setVisible(true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
            
        }

        }
    
   // public 

    public void setBool(Boolean bool) {
    	prev = bool;
    }
    public boolean getPrev() {
    	return prev;
    }
    public boolean getBool() {
        return cont;
    }
}