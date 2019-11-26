import javax.swing.*;
 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
 
public class LogInFrame extends JFrame{
 
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT =300;
    private static boolean cont = true;

    static JTextField userNameField;
    static JTextField passwordField;
    private String passwordEntered;
    private String usernameEntered;
    
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
       
        panel.add(title);
 
        panel.add(empty);
        panel.add(userNameLabel);
        panel.add(userNameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(exitButton);
        panel.add(signUpPage);
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
            System.exit(0);
        }
    }
 
 
 
    public class ClickListener2 implements ActionListener {
 
        public void actionPerformed(ActionEvent event) {
            usernameEntered = userNameField.getText();
            passwordEntered = passwordField.getText();
        //    usernameEntered = "Bob";
          //  passwordEntered = "Pass";
      //      System.out.println(usernameEntered + passwordEntered);
            if(userData.searchCredentials(usernameEntered, passwordEntered)==null) {
				System.out.println("you failed");
            	JOptionPane.showMessageDialog(null, "Error logging into account.\nPlease try again.", "Error!",
						JOptionPane.ERROR_MESSAGE);
            }
            else {
            	UserClass found = userData.searchCredentials(usernameEntered, passwordEntered);
            	JOptionPane.showMessageDialog(null, "You have successfully logged in.", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
            	System.out.println(found.getUserName());
            	setVisible(false);
            	
            	//call the next DASHBOARD GUI
            }
             //connected to the userData userManager object. 
 
        }
        
    }
   
   
    public class ClickListener3 implements ActionListener {
    	 
        public void actionPerformed(ActionEvent event) {

            
            setVisible(false);
            cont = false;
        }

        }
    
   // public 

    public boolean getBool() {
        return cont;
    }
}