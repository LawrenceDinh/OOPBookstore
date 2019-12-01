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


		DashboardDriver d = new DashboardDriver(sessionItems, sessionUsers, test); //runs the dashboard and passes current instance of user
		d.runDashboard();
		d.stepForward();
		
		
		//DragAndDrop ditem = new DragAndDrop(sessionItems, view.returnUser());
		//ditem.dragAndDropDisplay();
		System.out.println("runs");
		
		sessionItems.writeItems();
		sessionUsers.writeUsers();
		System.out.println("this end");
		System.exit(0);
		}
}
