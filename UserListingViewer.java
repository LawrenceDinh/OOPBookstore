import java.awt.event.ActionListener;

import javax.swing.JButton;

/**Displays a simple window with a table in the center
 * with view and close buttons at the bottom.
 * Lists all item listings from a specific user.
 * Contains additional Edit and Delete buttons on the bottom.
 * @author Kevin Ngo
 *
 */
public class UserListingViewer extends TableViewer{
	
	private JButton edit_button = new JButton("Edit");
	private JButton delete_button = new JButton("Delete");
	
	public UserListingViewer()
	{
		addComponentToBottom(delete_button);
		addComponentToBottom(edit_button);
	}
	
	public void display()
	{
		setTitle("Postings you own");
		super.display();
	}
	
	/**Adds an ActionListener to the Edit button
	 * @param al the ActionListener to add
	 */
	public void addEditButtonAction(ActionListener al)
	{
		edit_button.addActionListener(al);
	}
	
	/**Adds an ActionListener to the Delete button
	 * @param al the ActionListener to add
	 */
	public void addDeleteButtonAction(ActionListener al)
	{
		delete_button.addActionListener(al);
	}
	
	
}
