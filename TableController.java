import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JOptionPane;

/**Controls a TableViewer and adds ActionListeners.
 * @author Kevin Ngo
 *
 */
public class TableController {
	
	protected static final String[] TABLE_COLUMNS = {"Item ID", "Category Index", "Seller", "Item Name", "Item Category"};
	protected static final int ITEM_ID_COL = 0;
	protected static final int CATEGORY_INDEX_COL = 1;
	protected static final int SELLER_ID_COL = 2;
	protected static final int ITEM_NAME_COL = 3;
	protected static final int ITEM_CATEGORY_COL = 4;
	
	protected TableViewer viewer;
	protected ItemManager itemManager;
	protected UserManager userManager;
	private CountDownLatch cdLatch;
	
	public TableController(CountDownLatch latch, TableViewer viewer, ItemManager itemManager, UserManager userManager)
	{
		cdLatch = latch;
		this.viewer = viewer;
		this.itemManager = itemManager;
		this.userManager = userManager;
	}
	
	/**Adds the appropriate ActionListeners to the TableViewer's buttons
	 * and other JComponents. Displays the TableViewer afterwards.
	 * 
	 */
	public void start()
	{
		this.viewer.addViewButtonAction(new ViewAction());
		this.viewer.addCloseButtonAction(new CloseAction());
		viewer.display();
	}
	
	/**The ActionListener for when View is clicked.
	 * Creates a window to show additional information about
	 * the selected item. A warning pops up if nothing is selected.
	 * @author Kevin Ngo
	 *
	 */
	private class ViewAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (!viewer.tableIsEmpty())
			{
				//ItemManager m = new ItemManager();
				//ItemClass getitem = m.searchItemID(8);
				//CountDownLatch a = new CountDownLatch(1);
				//System.out.println("this name: " + getitem.getItemName());
				//shownItem n = new shownItem(getitem);
				
				//open up view item screen
				System.out.println("View");
				int row = viewer.getSelectedRow();
				long itemID = Long.parseLong((String)viewer.getCellAt(row, ITEM_ID_COL));
				ItemClass item = itemManager.searchItemID(itemID);
				System.out.println("this item selected: " + item.getItemName());
				shownItem show = new shownItem(item);
				show.display_selectedItem();

			} else 
			{
				JOptionPane.showMessageDialog(viewer, "Nothing to view.", "No item selection", JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	
	/**The ActionListener for when Close is clicked.
	 * Closes the TableViewer.
	 * @author Kevin Ngo
	 *
	 */
	private class CloseAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.out.println("Close");
			viewer.dispose();
			cdLatch.countDown();
		}
		
	}
}
