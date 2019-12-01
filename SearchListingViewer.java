import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**Displays a simple window with a table in the center
 * with view and close buttons at the bottom.
 * Contains a search bar at the top to find listings.
 * @author Kevin Ngo
 *
 */
public class SearchListingViewer extends TableViewer{

		//the combo box to search by different things
		public static final String ITEM_NAME = "Item Name";
		public static final String ITEM_CATEGORY = "Item Category";
		public static final String SELLER_NAME = "Seller Name";
		private JComboBox<String> searchParameters_combo;
		
		//the combo box to select item categories
		public static final String CATEGORY_PANEL = "Category";
		public static final String TEXT_PANEL = "Text";
		private JComboBox<String> category_combo = new JComboBox<String>();
		
		//the card deck to switch from combo box to text panel
		private JPanel search_panel = new JPanel(new CardLayout());
		
		//textfield for searching
		private JTextField search_text = new JTextField(20);
		
		//buttons on the top-portion of screen
		private JButton search_button = new JButton("Search");
		
		public SearchListingViewer()
		{
			super();
			
			//build and add the combo box that handles search parameters
			String[] search_types = new String[]{ITEM_NAME, ITEM_CATEGORY, SELLER_NAME};
			search_panel.add(search_text, TEXT_PANEL);
			search_panel.add(category_combo, CATEGORY_PANEL);
			searchParameters_combo = new JComboBox<String>(search_types);
			
			addComponentToTop(search_button);
			addComponentToTop(search_panel);
			addComponentToTop(searchParameters_combo);
		}
		
		public void display()
		{
			setTitle("Search for postings");
			super.display();
		}
		
		/**Adds an ActionListener to the Search Parameters combo box
		 * @param al the ActionListener to add
		 */
		public void addSearchParametersAction(ActionListener al)
		{
			searchParameters_combo.addActionListener(al);
		}
		
		/**Adds an ActionListener to the Close button
		 * @param al the ActionListener to add
		 */
		public void addSearchButtonAction(ActionListener al)
		{
			search_button.addActionListener(al);
		}
		
		//getters for the fields user interacts with
		/**Gets the String inside the Search Parameters combo box
		 * @return the Search Parameter chosen by the user
		 */
		public String getSearchComboChoice()
		{
			return (String)searchParameters_combo.getSelectedItem();
		}
		
		/**Gets the index of the selected item in the Search Parameters combo box
		 * @return the index of the selected Search Paramer chosen by the user
		 */
		public int getSearchComboIndex()
		{
			return searchParameters_combo.getSelectedIndex();
		}
		
		
		/**Gets the text inside of the search text field
		 * @return the search input from the user
		 */
		public String getSearchText()
		{
			return search_text.getText();
		}
		
		/**Gets the category name of the items the user requests
		 * @return the category name chosen by the user
		 */
		public String getCategoryComboChoice()
		{
			return (String)category_combo.getSelectedItem();
		}
		
		/**Gets the index of the category inside the category combo box.
		 * The index is the same as the one used inside the box's model.
		 * @return the index of the category chosen by the user
		 */
		public int getCategoryComboIndex()
		{
			return category_combo.getSelectedIndex();
		}
		
		/**Sets the categories to be used in the category combo box
		 * @param categories the array of categories to be chosen in the combo box
		 */
		public void setSearchCategories(String[] categories)
		{
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(categories);
			category_combo.setModel(model);
		}
		
		/**Swaps the input type inside the top search bar
		 * @param cardLabel the label of the card
		 */
		public void switchCards(String cardLabel)
		{
			CardLayout cl = (CardLayout)search_panel.getLayout();
			cl.show(search_panel, cardLabel);
		}
}
