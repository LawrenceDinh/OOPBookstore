import javax.swing.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.concurrent.CountDownLatch;
 
/**Displays a window with two textfields to sign up along  
 * with 'Login', 'Exit', and 'Back to Signup Screen' buttons. 
 * @author LUAT DINH
 */
public class LogInFrame extends JFrame{
 
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT =300;
    private boolean cont = false;

    static JTextField userNameField;
    static JTextField passwordField;
    private String passwordEntered = null;
    private String usernameEntered = null;
    private UserManager userData;
    private UserClass user;
   private CountDownLatch latch;
   private  JFrame sFrame;
 
    public LogInFrame(UserManager users, CountDownLatch signal, JFrame signUpFrame) throws FileNotFoundException
     {
    	sFrame = signUpFrame;
     userData = users;
     latch = signal;
     createComponents();
     setSize(FRAME_WIDTH, FRAME_HEIGHT);
     
     }

    /**Creates the Login JFrame and adds elements to it.
     * 
     */
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
 
    
    //exit 
    /**Exit button ActionListener
     * 
     */
    public class ClickListener1 implements ActionListener {
 
        public void actionPerformed(ActionEvent event) {
        	System.out.println("Terminating program");

        	userData.writeUsers();
        	setVisible(false);
        	System.exit(1);
        }
    }
 
 
    /**Login button ActionListener
     * 
     */
    public class ClickListener2 implements ActionListener {
 
        public void actionPerformed(ActionEvent event) {
            usernameEntered = userNameField.getText();
            passwordEntered = passwordField.getText();
        //    usernameEntered = "Bob";
          //  passwordEntered = "Pass";
      //      System.out.println(usernameEntered + passwordEntered);
           
            if((usernameEntered.isEmpty()) || (passwordEntered.isEmpty()))
            {
				System.out.println("failed");
            	JOptionPane.showMessageDialog(null, "Error logging into account.\nYou did not enter a valid username or password.\nPlease try again.", "Input Error!",
						JOptionPane.ERROR_MESSAGE);
            }
            
            else if(userData.searchCredentials(usernameEntered, passwordEntered)==null) {
				System.out.println("failed");
				System.out.println("input: " + usernameEntered + ", " + passwordEntered);
            	JOptionPane.showMessageDialog(null, "Error logging into account.\nPlease try again.", "Invalid Info!",
						JOptionPane.ERROR_MESSAGE);
            }
            else {
				System.out.println("input: " + usernameEntered + ", " + passwordEntered);
            	UserClass found = userData.searchCredentials(usernameEntered, passwordEntered);
            	JOptionPane.showMessageDialog(null, "You have successfully logged in.", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
            	//System.out.println("success: " + found.getUserName());
            	latch.countDown();
            	user = found;
            	setVisible(false);
            	cont = true;

            	latch.countDown();
            	
            	//call the next DASHBOARD GUI
            
            }
             //connected to the userData userManager object. 
 
            
            
            
        }
        
    }
   
    public UserClass getUser() {
    	return user;
    }
   

    /**Back to Sign Up Screen Page ActionListener
     * 
     */
    public class ClickListener3 implements ActionListener {
    	 
        public void actionPerformed(ActionEvent event) {

        	cont = true;
            System.out.println("back");
            setVisible(false);

      
				sFrame.setVisible(true);

			
      
        }
  }
        
    
   // public 

    public void setBool(Boolean bool) {
    	cont = bool;
    }
    
    public boolean getBool() {
        return cont;
    }
    
    public void updatesFrame(JFrame frame) {
    	sFrame = frame;
    }
    
}


