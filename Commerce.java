import java.util.HashMap;
import java.util.HashSet;

public class Commerce {

	public static void main(String[]args) {
		UserManager sessionUsers=new UserManager();
		ItemManager sessionItems=new ItemManager();

		//[Long user id] [string name] [string password] 
		// [long id].[longid].[longid] for a user with 3 items posted.
		HashSet<Long> items = new HashSet<Long>();
		items.add((long) 470694636);
		items.add((long) 273528742);
		items.add((long) 218463281);

		//Creating 3 test data objects of the User Class
		// Manually from constructors
		sessionUsers.generateUser("Bob", "Pass", items);
		sessionUsers.generateUser("Alice", "Pass2", items);
		sessionUsers.generateUser("Trudy", "Pass3", items);
		Viewer view = new Viewer(sessionUsers); //passing in UserManager sessionUsers variable as a parameter 
		view.display(); //start the GUI system
	}
}
