import javax.swing.JFrame;
import java.io.*;
import java.util.concurrent.CountDownLatch;

/**Used to launch the Sign Up/Log In Frames and feeds in userManager 
 * to the constructor to authenticate user credentials.
 * @author LUAT DINH
 *
 */
public class Viewer {

	private UserManager users;
	private JFrame LogInFrame;
	private JFrame SignUpFrame;
	private CountDownLatch latch;
	private UserClass user = null;
	
	/**constructor to pass in userData and latch
	 * @param userData current instance of userManager
	 * @param signal current countDownLatch
	 */
	public Viewer(UserManager userData, CountDownLatch signal) {
		users = userData;
		latch = signal;
	}

	public UserClass returnUser() {
		return user;
	}
	
	
	/**instantiates new login and signup frame objects
	 * 
	 */
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