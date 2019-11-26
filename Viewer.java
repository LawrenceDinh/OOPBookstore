import javax.swing.JFrame;
import java.io.*;
 
public class Viewer {

    UserManager users;
    JFrame LogInFrame;
    JFrame SignUpFrame;
    
    public Viewer(UserManager userData) {
    	users=userData;
    }
    
public void display() {
       
  
		try {
	//		System.out.println(users.searchUserID(1).getUserName());
			
			//users = UserManager Users
		    LogInFrame = new LogInFrame(users); 
		    SignUpFrame = new SignUpFrame(users, LogInFrame);
		    LogInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    SignUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
		    
		    
		    while (((SignUpFrame) SignUpFrame).getBool()) {
	        	SignUpFrame.setVisible(true);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		SignUpFrame.setVisible(false);
        LogInFrame.setVisible(true);
      

	}
    
}