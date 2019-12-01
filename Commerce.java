import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

//TODO: Improve field editing capability (e.g. changing item description) between sessions
public class Commerce {

	public static void main(String[]args) throws FileNotFoundException, InterruptedException {
		UserManager sessionUsers=new UserManager();
		ItemManager sessionItems=new ItemManager();
		
		CountDownLatch loginSignal = new CountDownLatch(1);
		//Commented out for testing non-gui things; uncomment to use
		Viewer view = new Viewer(sessionUsers, loginSignal);  //passing in UserManager sessionUsers variable as a parameter 
		view.display();
		//loginSignal.countDown();
		//loginSignal.await();
		UserClass test = null;
		if (view.returnUser() != null) { //retrieving user instance from login
		test = view.returnUser();
		System.out.println("passed: " + test.getUserName());
		}

		
		//System.out.println(sessionUsers.getUserMap().toString());
		//when ready to end the session


		DashboardDriver d = new DashboardDriver(); //runs the dashboard and passes current instance of user
		d.runDashboard();
		
		
		//DragAndDrop ditem = new DragAndDrop(sessionItems, view.returnUser());
		//ditem.dragAndDropDisplay();
		System.out.println("runs");
		SearchListingViewer slv = new SearchListingViewer();
		UserListingViewer ulv = new UserListingViewer();
		
		switch(d.getChoice()) {
		case 0:
			System.out.println("Post item UI");
			break;
		case 1:
			System.out.println("Search item UI");
			//CountDownLatch n = new CountDownLatch(1);
			slv.display();
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

		
	//	SearchListingController slc = new SearchListingController(slv, sessionItems, sessionUsers);
	//	UserListingController ulc = new UserListingController(ulv, sessionItems, sessionUsers, "Sol Badguy");
	//	CountDownLatch n = new CountDownLatch(1);
	//	slc.start();
	//	ulc.start();
		
		
		sessionItems.writeItems();
		sessionUsers.writeUsers();
		System.out.println("this end");
		System.exit(0);
		}
}
