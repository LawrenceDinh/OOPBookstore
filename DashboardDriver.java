import java.util.concurrent.CountDownLatch;

public class DashboardDriver {

	
	/**
	 * 7
1~ww~a~0
6~a c ~a b ~0
3~a~my p~0`1`2`3
4~ab~a~0
5~a b ~a~0
7~Bob~pass1~0`4`5
2~aaww~a~0

	 */
	
	
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
		stepForward();
		
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
			System.out.println("Program terminating");
			break;
		}
	}
}
