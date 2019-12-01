package editGUI;

import java.util.concurrent.CountDownLatch;

public class editGUI {

	public static void main(String []args) throws InterruptedException {
	//long itemID, String itemName, int categoryNumber, String description, String sellerName
	ItemClass item = new ItemClass (1, "itemname", 2, "desc", "sellername");
	CountDownLatch n = new CountDownLatch(1);
	editItemViewer editGUI = new editItemViewer(item, n);
	n.await();
	editGUI.setVisible(false);
	}
}
