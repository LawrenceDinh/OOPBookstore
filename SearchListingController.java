import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**Controls a SearchListingViewer and adds ActionListeners.
 * Also manages the search bar in the SearchListingViewer.
 * @author Kevin Ngo
 *
 */
public class SearchListingController extends TableController{

	public SearchListingController(SearchListingViewer viewer, ItemManager itemManager, UserManager userManager) {
		super(viewer, itemManager, userManager);
	}
	
	public void start()
	{
		((SearchListingViewer)this.viewer).setSearchCategories(itemManager.getCategories());
		((SearchListingViewer)this.viewer).addSearchButtonAction(new SearchAction());
		((SearchListingViewer)this.viewer).addSearchParametersAction(new SearchParametersAction());
		super.start();
	}
	
	/**The ActionListener for when Search is clicked.
	 * Will search for item listings based on what the
	 * user specified before clicking Search. Will update
	 * the table with the new listings. Displays a message
	 * box if no results are found.
	 * @author Kevin Ngo
	 *
	 */
	private class SearchAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//add the list to the TableModel
			ArrayList<ItemClass> items = new ArrayList<ItemClass>();
			
			String searchChoice = ((SearchListingViewer)viewer).getSearchComboChoice();
			String input = ((SearchListingViewer)viewer).getSearchText();
			int index = ((SearchListingViewer)viewer).getCategoryComboIndex();
			//use the right search parameters depending on user choice
			if (searchChoice.equals(SearchListingViewer.ITEM_NAME)) 
			{
				System.out.println("Search by " + SearchListingViewer.ITEM_NAME);
				items = itemManager.searchItemName(input);
			} else if (searchChoice.equals(SearchListingViewer.ITEM_CATEGORY)) 
			{
				items = itemManager.searchItemCategory(index);
			} else if (searchChoice.equals(SearchListingViewer.SELLER_NAME)) 
			{
				System.out.println("Search by " + SearchListingViewer.SELLER_NAME);
				UserClass user = userManager.searchUserName(input);
				if (user != null)
				{
					items = itemManager.searchItemIDs(userManager.getUsersListedItems(input));
				}
			}
			
			//check if the db actually found something
			if (!items.isEmpty())
			{
				DefaultTableModel model = new DefaultTableModel(TABLE_COLUMNS, 0);
				String[] categories = itemManager.getCategories();
				for (int i = 0; i < items.size(); i++)
				{
					if (items.get(i) != null)
					{
						String itemID = Long.toString(items.get(i).getItemID());
						String categoryIndex = Integer.toString(items.get(i).getCategoryNumber());
						String seller = items.get(i).getSellerName();
						String itemName = items.get(i).getItemName();
						String itemCategory = categories[items.get(i).getCategoryNumber()];
						String[] attributes = new String[] {itemID, categoryIndex, seller, itemName, itemCategory};
						model.addRow(attributes);
					}
					
				}
				viewer.setTable(model);
				viewer.removeColumnAt(0);
				viewer.removeColumnAt(0);
				viewer.updateTable();
			} else
			{
				//if nothing is found, show a dialog to the user
				//don't update the table
				JOptionPane.showMessageDialog(viewer, "No results found.", "Search Results", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
	}
	
	/**The ActionListener for when the search parameter changes.
	 * Will switch the input type from the user depending on the
	 * search parameter.
	 * @author Kevin Ngo
	 *
	 */
	private class SearchParametersAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e)
		{
			if (((SearchListingViewer)viewer).getSearchComboChoice().equals(SearchListingViewer.ITEM_CATEGORY))
			{
				((SearchListingViewer)viewer).switchCards(SearchListingViewer.CATEGORY_PANEL);
			} else
			{
				((SearchListingViewer)viewer).switchCards(SearchListingViewer.TEXT_PANEL);
			}
		}
	}

}
