import java.util.concurrent.CountDownLatch;

public class DashboardDriver {

	
	
	private int choice = -1;
	DashboardChoiceSelected c;
	ItemManager items;
	UserManager users;
	UserClass thisUser;
	public DashboardDriver(ItemManager itemInstance, UserManager userInstance, UserClass currentUser) {
		items = itemInstance;
		users = userInstance;
		thisUser = currentUser;
	}
	public void runDashboard() throws InterruptedException{
		CountDownLatch loginSignal = new CountDownLatch(1);
		int choice = -1;
		 c = new DashboardChoiceSelected(-1);
		DashboardFrame m = new DashboardFrame(loginSignal, choice, c, thisUser);
		
		m.run();
	

	
		//System.out.println("success: " + (c.getChoice()));
	
		//System.out.println(n);
		choice = c.getChoice();
		
	}
	public int getChoice() {
		choice = c.getChoice();
		return choice;
	}
	
	public UserClass getCurrentUser() {
		return thisUser;
	}
	
	public void stepForward() throws InterruptedException {
		System.out.println("logged in as: " + getCurrentUser().getUserName());
		switch(c.getChoice()) {
		case 0:
			System.out.println("Post item UI");
			break;
		case 1:
			System.out.println("Search item UI");
			//CountDownLatch n = new CountDownLatch(1);
			//n.await();
			break;
		case 2:
			//System.out.println("View Own Items UI");
			UserListingViewer ulv = new UserListingViewer();
			UserListingController ulc = new UserListingController(ulv, items, users, getCurrentUser().getUserName());
			CountDownLatch n = new CountDownLatch(1);
			ulc.start();

			//n.await();
			break;
		case 3:
			System.out.println("Change password UI");
			CountDownLatch l = new CountDownLatch(1);
			changePassGUI c = new changePassGUI(thisUser, l);

			c.changePasswordUI();
			l.await();
			System.out.println("from switch: " + thisUser.getPassword());
			break;
		case 4:
			System.out.println("Program terminating");
			System.exit(0);
		}
	}
}
