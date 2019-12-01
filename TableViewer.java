import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**Displays a simple window with a table in the center
 * with view and close buttons at the bottom
 * @author Kevin Ngo
 *
 */
public class TableViewer extends JDialog {

	// table that holds the list of items
	// it is uneditable
	private JTable table= new JTable() {
		
		public boolean isCellEditable(int row, int col)
		{
			return false;
		}
	};

	//view button interacts with the selected row
	private JButton view_button = new JButton("View");
	//close button closes the window
	private JButton close_button = new JButton("Close");

	//optional top portion, can be added to
	private JPanel topPortion = new JPanel();
	//optional bottom portion, can be added to
	private JPanel bottomPortion = new JPanel();

	public TableViewer() {
		this.setLayout(new BorderLayout());

		// create the middle portion contains
		// jtable for displaying items from list
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// add the table to a scrollpane
		JScrollPane middlePortion = new JScrollPane(table);

		//add view and close buttons to bottom of page
		addComponentToBottom(close_button);
		addComponentToBottom(view_button);

		this.add(topPortion, BorderLayout.PAGE_START);
		this.add(middlePortion, BorderLayout.CENTER);
		this.add(bottomPortion, BorderLayout.PAGE_END);
	}
	
	
	/**Draws the viewer
	 * 
	 */
	public void display()
	{
		this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	/**Adds a JComponent to the top of the page, at the left most position
	 * @param component the JComponent to add
	 */
	public void addComponentToTop(JComponent component) {
		topPortion.add(component, 0);
		this.pack();
		topPortion.validate();
	}

	/**Adds a JComponent to the bottom of the page, at the left most position
	 * @param component the JComponent to add
	 */
	public void addComponentToBottom(JComponent component) {
		bottomPortion.add(component, 0);
		this.pack();
		bottomPortion.validate();
	}
	
	/**Adds an ActionListener to the View button
	 * @param al the ActionListener to add
	 */
	public void addViewButtonAction(ActionListener al) {
		view_button.addActionListener(al);
	}

	/**Adds an ActionListener to the Close button
	 * @param al the ActionListener to add
	 */
	public void addCloseButtonAction(ActionListener al) {
		close_button.addActionListener(al);
	}

	/**Gets the Object at a specific cell in the table
	 * @param row the index of the Object's row in the table
	 * @param col the index of the Object's column in the table
	 * @return the Object at cell (row, col) in the table
	 */
	public Object getCellAt(int row, int col) {
		return table.getModel().getValueAt(row, col);
	}

	/**Gets the current selected row in the table
	 * @return the index number of the selected row in the table
	 */
	public int getSelectedRow() {
		return table.getSelectedRow();
	}

	/**Changes the model inside the table to a new model
	 * @param model the new model for the table
	 */
	public void setTable(DefaultTableModel model) {
		table.setModel(model);
		table.changeSelection(0, 0, false, false);
	}
	
	/**Removes a column from the table at the specified index
	 * @param column the index number of the column to be removed
	 */
	public void removeColumnAt(int column)
	{
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(column));
		table.changeSelection(0, 0, false, false);
	}

	/**Refreshes the table to show changes
	 * 
	 */
	public void updateTable() {
		table.validate();
	}

	/**Deletes a row from the table at the specified index
	 * @param row the index number of the row to be removed
	 */
	public void deleteRow(int row) {
		((DefaultTableModel) table.getModel()).removeRow(row);
		table.changeSelection(0, 0, false, false);
	}

	/**Checks if the table is empty
	 * @return true if the table is empty, false if not
	 */
	public boolean tableIsEmpty() {
		return (table.getModel().getRowCount() == 0);
	}
}
