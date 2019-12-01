import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**Controls a UserListingController and adds ActionListeners.
 * @author Kevin Ngo
 *
 */
public class UserListingController extends TableController{
	
	private String user;

	public UserListingController(UserListingViewer viewer, ItemManager itemManager, UserManager userManager, String user) {
		super(viewer, itemManager, userManager);
		this.user = user;
	}
	
	public void start()
	{
		ArrayList<ItemClass> items = itemManager.searchItemIDs(userManager.getUsersListedItems(user));
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
		this.viewer.setTable(model);
		viewer.setTable(model);
		viewer.removeColumnAt(0);
		viewer.removeColumnAt(0);
		viewer.updateTable();
		
		((UserListingViewer)this.viewer).addEditButtonAction(new EditAction());
		((UserListingViewer)this.viewer).addDeleteButtonAction(new DeleteAction());
		super.start();
	}
	
	/**The ActionListener for when Edit is clicked.
	 * Creates a window to edit the selected item in the table.
	 * A warning pops up if nothing is selected.
	 * @author Kevin Ngo
	 *
	 */
	private class EditAction implements ActionListener{

//		private ItemClass item;
		
		public void actionPerformed(ActionEvent e) {
			if (!viewer.tableIsEmpty())
			{
				//open up edit screen to edit the item
				System.out.println("Edit");
			} else
			{
				JOptionPane.showMessageDialog(viewer, "Nothing to edit.", "No item selection", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	/**The ActionListener for when Edit is clicked.
	 * Deletes the selected item from the table.
	 * Also updates the manager classes.
	 * A warning pops up if nothing is selected.
	 * @author Kevin Ngo
	 *
	 */
	private class DeleteAction implements ActionListener{

//		private ItemClass item;
		
		public void actionPerformed(ActionEvent e) {
			if (!viewer.tableIsEmpty())
			{
				//delete the item from the db
				System.out.println("Delete");
				
				//get selected row
				//delete that item in ItemManager.deleteItem
				//and also delete it in UserClass.removeListedItemID
				int row = viewer.getSelectedRow();
				String selectedItemID = (String)(viewer.getCellAt(row, ITEM_ID_COL));
				long itemID = Long.parseLong(selectedItemID);
				itemManager.deleteItem(itemID);
				String seller = (String)viewer.getCellAt(row, SELLER_ID_COL);
//				String seller = "Sol Badguy";
//				System.out.println(userManager.searchUserName(seller) == null);
				userManager.searchUserName(seller).removeListedItemID(itemID);
				
				//update the table
				viewer.deleteRow(row);
			} else
			{
				JOptionPane.showMessageDialog(viewer, "Nothing to delete.", "No item selection", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
	}

	
}
