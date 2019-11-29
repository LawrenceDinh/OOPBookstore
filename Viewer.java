import javax.swing.JFrame;
import java.io.*;
import java.util.concurrent.CountDownLatch;

public class Viewer {

	UserManager users;
	JFrame LogInFrame;
	JFrame SignUpFrame;
	CountDownLatch latch;
	UserClass user = null;

	public Viewer(UserManager userData, CountDownLatch signal) {
		users = userData;
		latch = signal;
	}

	public UserClass returnUser() {
		return user;
	}
	
	public void display() throws FileNotFoundException, InterruptedException {


			// System.out.println(users.searchUserID(1).getUserName());

			// users = UserManager Users
			LogInFrame = new LogInFrame(users, latch, SignUpFrame);
			SignUpFrame = new SignUpFrame(users, LogInFrame, latch);

			((LogInFrame) LogInFrame).updatesFrame(SignUpFrame);

			LogInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			SignUpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

			SignUpFrame.setVisible(true);

			latch.await();
			
			user = ((LogInFrame) LogInFrame).getUser();
			
			}
			

		//SignUpFrame.setVisible(false);
		/**
		System.out.println(user);
		int i =0;
			while(((LogInFrame) LogInFrame).returnUser()==null){
				//looping through the login UI to check if 
				//login was successful
				//System.out.println("t");
				System.out.println(i);
				i++;
				if (((LogInFrame) LogInFrame).returnUser()!=null) {
				System.out.println(((LogInFrame) LogInFrame).returnUser().getUserName());
			}}
		System.out.println("testpppp");**/

	

}