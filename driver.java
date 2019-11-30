import java.util.concurrent.CountDownLatch;

public class driver {

	private UserClass user;
	public driver(UserClass currentUser) {
		user = currentUser;
	}
	public void runDashboard() throws InterruptedException{
		System.out.println("logged: " + user.getUserName());
		CountDownLatch loginSignal = new CountDownLatch(1);
		int choice = -1;
		choiceSelected c = new choiceSelected(-1);
		main m = new main(loginSignal, choice, c);
		m.run();
		
		System.out.println("test: " + m.getChoice());

	
		System.out.println("success: " + (c.getChoice()+1));
	
		System.out.println("exit");	
		//System.out.println(n);
	}
	
}
