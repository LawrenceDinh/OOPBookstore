import java.util.concurrent.CountDownLatch;

public class DashboardDriver {

	
	
	private int choice = -1;
	DashboardChoiceSelected c;
	ItemManager items;
	UserManager users;
	public DashboardDriver(ItemManager itemInstance, UserManager userInstance) {
		items = itemInstance;
		users = userInstance;
	}
	public void runDashboard() throws InterruptedException{
		CountDownLatch loginSignal = new CountDownLatch(1);
		int choice = -1;
		 c = new DashboardChoiceSelected(-1);
		DashboardFrame m = new DashboardFrame(loginSignal, choice, c);
		m.run();
	

	
		//System.out.println("success: " + (c.getChoice()));
	
		//System.out.println(n);
		choice = c.getChoice();
		/**
		switch(choice) {
		case 1:
			System.out.println("Post item UI");
			break;
		case 2:
			System.out.println("Search item UI");
			break;
		case 3:
			System.out.println("Change password UI");
			break;
		case 4:
			System.out.println("Program terminating");
			System.exit(1);
			break;
		}**/
		
	}
	public int getChoice() {
		choice = c.getChoice();
		return choice;
	}
	
	public void stepForward() {
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
			System.out.println("Change password UI");
			break;
		case 3:
			System.out.println("Program terminating");
			System.exit(1);
			break;
		}
	}
}
