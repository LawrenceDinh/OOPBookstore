/////////////////
 
/***
NOTE:
EDGE CASE- The program fails if textfields are left empty during account creation
***/
 
import javax.swing.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
 
public class SignUpFrame extends JFrame{
 
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT =300;
    private boolean cont = true;

    static JTextField userNameField;
    static JTextField passwordField;
    private String passwordEntered;
    private String usernameEntered;
    private UserManager userData;
    private JFrame frame;
    //public static useClass userObj= new userClass();
 
    public SignUpFrame(UserManager users, JFrame login) throws FileNotFoundException
     {
     createComponents();
     setSize(FRAME_WIDTH, FRAME_HEIGHT);
     userData = users;
     frame = login;
     }
   

 
    private void createComponents() throws FileNotFoundException {
        userNameField = new JTextField(10);
        passwordField = new JTextField(10);
 
        JLabel userNameLabel = new JLabel("Enter New Username:");
        JLabel passwordLabel = new JLabel("Enter Password:");
        JLabel title = new JLabel("Sign Up Screen");
        JLabel empty = new JLabel(" ");
        title.setHorizontalAlignment(JLabel.CENTER);
        JButton loginButton = new JButton("Create Account");
        JButton exitButton = new JButton("Exit Program");
       
        JButton cont = new JButton("Login Existing Account");
 
        JPanel panel = new JPanel(new GridLayout(0, 1));
        title.setFont(new Font("Arial", Font.PLAIN, 25)); //set title font
        panel.add(title);
 
        panel.add(empty);
        panel.add(userNameLabel);
        panel.add(userNameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(empty);
        panel.add(cont);
        panel.add(exitButton);
        add(panel);
 
        ActionListener exitListener = new ClickListener1();
        ActionListener loginListener = new ClickListener2();
        ActionListener contListener = new ClickListener3();
        exitButton.addActionListener(exitListener);
        loginButton.addActionListener(loginListener);
        cont.addActionListener(contListener);
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
            
            usernameEntered =  usernameEntered.replaceAll("\\s+","");
            
            passwordEntered =  passwordEntered.replaceAll("\\s+","");
           
            if((usernameEntered.isEmpty()) || (passwordEntered.isEmpty()))
            {
				System.out.println("failed");
            	JOptionPane.showMessageDialog(null, "Error registering account.\nYou did not enter a valid username or password.\nPlease try again.", "Input Error!",
						JOptionPane.ERROR_MESSAGE);
            }
            
            else if(userData.searchCredentials(usernameEntered, passwordEntered)!=null) {
				System.out.println("already exists");
            	JOptionPane.showMessageDialog(null, "This account already exists!\nPlease use the Login page to sign in.", "Error!",
						JOptionPane.ERROR_MESSAGE);
            }
            else {
            	UserClass found = userData.generateUser(usernameEntered, passwordEntered, null);
            	JOptionPane.showMessageDialog(null, "You have successfully created an account.\nPlease log in.", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
            	System.out.println(found.getUserName());
            	
            	cont = false;
            	setVisible(false);
            	frame.setVisible(true);
            	
            	//call the next DASHBOARD GUI
            }
             //connected to the userData userManager object. 
 
        }
        
    }
   
    public class ClickListener3 implements ActionListener {
 
        public void actionPerformed(ActionEvent event) {
        	setVisible(false);
            frame.setVisible(true);
            cont = false;
        }
    }
   


    public void setBool(Boolean bool) {
    	cont = bool;
    }
    
    public boolean getBool() {
        return cont;
    }
}
