import java.util.concurrent.CountDownLatch;

/** Control class for Dashboard UserInterface. 
 * Calls methods to run DashboardFrame GUI and 
 * get the userChoice to use in a switch statement.
 * The switch statement then displays the desired UI.
 * @author LUAT DINH
 */


public class DashboardDriver {

	
	
	private int choice = -1;
	DashboardChoiceSelected c;
	ItemManager items;
	UserManager users;
	UserClass thisUser;
	
	/**
	 * Constructs a LogInFrame
	 * @param itemInstance is instance of ItemManager
	 * @param userInstance is instance of userManager
	 * @param currentUser is instance of UserClass currently logged in.
	 */
	public DashboardDriver(ItemManager itemInstance, UserManager userInstance, UserClass currentUser) {
		items = itemInstance;
		users = userInstance;
		thisUser = currentUser;
	}
	
	/**
	 * Generates a new dashboard frame and wait for the user to 
	 * select an input.
	 *
	 */
	public void runDashboard() throws InterruptedException{
		CountDownLatch loginSignal = new CountDownLatch(1);
		int choice = -1;
		 c = new DashboardChoiceSelected(-1);
		DashboardFrame m = new DashboardFrame(loginSignal, choice, c, thisUser);
		m.run();
	
		//System.out.println("success: " + (c.getChoice()));
	
		//System.out.println(n);
		choice = c.getChoice();
		stepForward();
		
	}
	public int getChoice() {
		choice = c.getChoice();
		return choice;
	}
	
	public UserClass getCurrentUser() {
		return thisUser;
	}
	
	/**
	 * Switch statement that displays the UI that users chooses from dashboard.
	 * Calls runDashboard again after closing to redisplay dashboard. 
	 *
	 */
	public void stepForward() throws InterruptedException {
		System.out.println("logged in as: " + getCurrentUser().getUserName());
		switch(c.getChoice()) {
		case 0:
			System.out.println("Post item UI");
			CountDownLatch aLatch = new CountDownLatch(1);
			ItemPoster postItem = new ItemPoster(aLatch, items, getCurrentUser());
			postItem.dragAndDropDisplay();
			aLatch.await();
			runDashboard();
			
			break;
		case 1:
			System.out.println("Search item UI");
			//CountDownLatch n = new CountDownLatch(1);
			//n.await();
			CountDownLatch a = new CountDownLatch(1);
			SearchListingViewer slv = new SearchListingViewer();
			SearchListingController slc = new SearchListingController(a,slv, items, users);
			slc.start();
			a.await();
			runDashboard();
			break;
		case 2:
			System.out.println("View Own Items UI");
			
			UserListingViewer ulv = new UserListingViewer();
			CountDownLatch n = new CountDownLatch(1);
			UserListingController ulc = new UserListingController(n,ulv, items, users, getCurrentUser().getUserName());

			ulc.start();

			n.await();
			runDashboard();
			break;
		case 3:
			System.out.println("Change password UI");
			CountDownLatch l = new CountDownLatch(1);
			changePassGUI c = new changePassGUI(thisUser, l);

			c.changePasswordUI();
			l.await();
			System.out.println("from switch: " + thisUser.getPassword());
			
			runDashboard();
			break;
		case 4:
			break;
		}
	}
}
