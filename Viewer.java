import javax.swing.JFrame;
import java.io.*;
import java.util.concurrent.CountDownLatch;

public class Viewer {

	private UserManager users;
	private JFrame LogInFrame;
	private JFrame SignUpFrame;
	private CountDownLatch latch;
	private UserClass user = null;

	public Viewer(UserManager userData, CountDownLatch signal) {
		users = userData;
		latch = signal;
	}

	public UserClass returnUser() {
		return user;
	}
	
	public void display() throws FileNotFoundException, InterruptedException {


			LogInFrame = new LogInFrame(users, latch, SignUpFrame);
			SignUpFrame = new SignUpFrame(users, LogInFrame, latch);

			((LogInFrame) LogInFrame).updatesFrame(SignUpFrame);

			LogInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			SignUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			SignUpFrame.setVisible(true);

			latch.await();
			
			user = ((LogInFrame) LogInFrame).getUser();
			//users.writeUsers();
			}
			

	

}