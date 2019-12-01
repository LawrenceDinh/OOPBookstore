
public class SearchTester {
	/**
	public static void main(String args[])
	{
		
		UserManager sessionUsers=new UserManager();
		ItemManager sessionItems=new ItemManager();
		
//		SearchItemViewer selfViewer = new SearchItemViewer(SearchItemViewer.SEARCH_CURRENT_USER);
//		SearchItemController selfControl = new SearchItemController(selfViewer, sessionItems, sessionUsers);
		
//		SearchItemViewer allViewer = new SearchItemViewer();
//		SearchItemController allControl = new SearchItemController(allViewer, sessionItems, sessionUsers);
//		
//		SelfPostingsViewer selfViewer = new SelfPostingsViewer();
//		SelfPostingsController selfControl = new SelfPostingsController(selfViewer, sessionItems, sessionUsers, "Sol Badguy");
	
		SearchListingViewer slv = new SearchListingViewer();
		UserListingViewer ulv = new UserListingViewer();
		
		SearchListingController slc = new SearchListingController(slv, sessionItems, sessionUsers);
		UserListingController ulc = new UserListingController(ulv, sessionItems, sessionUsers, "Sol Badguy");
		
		slc.start();
		ulc.start();
	}
**/
}
