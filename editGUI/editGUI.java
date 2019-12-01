package editGUI;

import java.util.concurrent.CountDownLatch;

import javax.swing.WindowConstants;

public class editGUI {

	public static void main(String []args) throws InterruptedException {
	//long itemID, String itemName, int categoryNumber, String description, String sellerName
	ItemClass item = new ItemClass (1, "itemname", 2, "desc", "sellername");
	CountDownLatch n = new CountDownLatch(1);
	editItemViewer editGUI = new editItemViewer(item, n);
	n.await();
	System.out.println("EXIT");
	editGUI.setVisible(false);
	System.out.println("main changed: " + item.getItemName());
	System.exit(1); //TO REMOVE WHEN IMPLEMENTING

	}
}
