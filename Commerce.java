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

		//Test users and items
		//sessionUsers.generateUser("Bob", "Pass");
//		sessionUsers.generateUser("Alice", "Pass2");
//		sessionUsers.generateUser("Trudy", "Pass3");
//		sessionUsers.generateUser("Simon", "Pass 4");
//		sessionItems.generateItem("duck", "toy", "it's a waterfowl toy", (long) 1);
//		sessionUsers.searchUserID(1).addListedItemID(sessionItems.getPreviousItemID());
//		sessionItems.generateItem("duck", "toy", "duk", (long) 2);
//		sessionUsers.searchUserID(2).addListedItemID(sessionItems.getPreviousItemID());
//		sessionItems.generateItem("goose", "toy", "a bird of a different color, for sale", (long) 1);
//		sessionItems.searchItemID(sessionItems.getPreviousItemID()).setDescription("The best goose for sale");
//		sessionUsers.searchUserID(1).addListedItemID(sessionItems.getPreviousItemID());
//		sessionItems.generateItem("lawnmower", "yard equipment", "please buy my lawnmower cost $100000 I know what I have so no lowballing", (long)3);
//		sessionUsers.searchUserID(3).addListedItemID(sessionItems.getPreviousItemID());
		
//		System.out.println("@@@"+sessionUsers.searchUserID(2).getUserName());
//		System.out.println("III"+sessionItems.searchItemID(4).getItemName());
		/**
		 * 3
1~Bob~Pass~0`1`3
2~a~b~0
3~alice~a~0
4~Simon~Pass 4~0
		 */
		
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

		System.out.println("this end");
		driver d = new driver(); //runs the dashboard and passes current instance of user
		d.runDashboard();
		System.out.println(d.getChoice());
		sessionItems.writeItems();
		sessionUsers.writeUsers();
		System.exit(0);
		}
}
