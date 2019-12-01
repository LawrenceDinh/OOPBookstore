package editGUI;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dialog;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;


public class editItemViewer extends JDialog{

	private ItemClass choosen = null;

	public static final String TEXT_PANEL = "Text";
	
	private JPanel search_panel = new JPanel(new CardLayout());
	private JTextField item_name = new JTextField(30);
	private JButton view_button = new JButton("Confirm Changes");
	private JButton close_button = new JButton("Close");
	private JLabel title;
	private JLabel description;
	private ItemClass item;
	private JScrollPane scroll;
	private JTextArea display;
	private String itemDesc = null;
	private String itemTitle = null;
	CountDownLatch n;
	
	public editItemViewer(ItemClass itemSearched, CountDownLatch latch) throws InterruptedException
	{
		n = latch;
		item = itemSearched;
		title = new JLabel("Enter " + itemSearched.getItemName() + "'s New Title ");
		title.setHorizontalAlignment(JLabel.CENTER);

		
		description = new JLabel("Enter " + itemSearched.getItemName() + "'s New Description");
		description.setHorizontalAlignment(JLabel.CENTER);
	
		
		
		this.setLayout(new BorderLayout());
		
		JPanel topPortion = new JPanel();
		topPortion.setLayout(new GridLayout(0, 1));
		search_panel.add(item_name);
		topPortion.add(title);
		topPortion.add(search_panel, BorderLayout.PAGE_END);
		
		
		JPanel middlePortion = new JPanel();
		String name = new String ("Edit " + item.getItemName() + "'s Description");
        display = new JTextArea(4, 30);
		scroll = new JScrollPane(display);
		JLabel disp = new JLabel(name);
		disp.setHorizontalAlignment(JLabel.CENTER);
		disp.setVerticalAlignment(SwingConstants.BOTTOM);
		middlePortion.setLayout(new GridLayout(0, 1));
		middlePortion.add(disp);
		middlePortion.add(scroll);
		
		
		JPanel bottomPortion = new JPanel();
		bottomPortion.setLayout(new GridLayout(0, 1));
		bottomPortion.add(view_button);
		bottomPortion.add(close_button);
		
		
		
		this.add(topPortion, BorderLayout.PAGE_START);
		this.add(middlePortion, BorderLayout.CENTER);
		this.add(bottomPortion, BorderLayout.PAGE_END);
		
		  close_button.addActionListener(new ActionListener()
			{
				  public void actionPerformed(ActionEvent e)
				  {
				    // display/center the jdialog when the button is pressed
					  setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
					  setVisible(false);
					  dispose();
					  n.countDown();
				  }
				  
				});
			
			view_button.addActionListener(new ActionListener()
			{
				  public void actionPerformed(ActionEvent e)
				  {
				    // display/center the jdialog when the button is pressed
					  System.out.println("CONFIRMED CHANGES");
					  getDescription();
					  getItemName();
					  if (stringEmpty(getDescription())&& stringEmpty(getItemName())) {
			            	JOptionPane.showMessageDialog(null, "Please modify atleast one field.", "Error!",
									JOptionPane.ERROR_MESSAGE);
			
					  }
					  else {
						  if (!stringEmpty(getItemName())&& (!stringEmpty(getDescription()))){
							  System.out.println("this name: " + getItemName());
							  item.setItemName(getItemName());
							  System.out.println("this desc: " + getDescription());
							  item.setDescription(getDescription());
						  }
						  else if (!stringEmpty(getDescription())){
							  System.out.println("this desc: " + getDescription());
							  item.setDescription(getDescription());
						  }
						  
						  else if (!stringEmpty(getItemName())){
							  System.out.println("this name: " + getItemName());
							  item.setItemName(getItemName());
						  }
						  n.countDown();
					  }
					  
				  }
				});
			
		this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	
	//setter to change the table to a new model
	
	
	//swap the cards
	
	public boolean stringEmpty(String s) {
		if (s.trim().isEmpty() || s == null) {
			return true;
		}
		return false;
	}
	
	public String getDescription(){
		itemDesc = display.getText();
		if (!stringEmpty(itemDesc)) {
			
		}
		return itemDesc;
	}


	public String getItemName() {
		itemTitle = item_name.getText();
		if (!stringEmpty(itemTitle)) {
			
		}
		return itemTitle;
	}
	
	
	public void switchCards(String panel)
	{
		CardLayout cl = (CardLayout)search_panel.getLayout();
		cl.show(search_panel, panel);
	}
	
	/**
	  close_button.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
			    // display/center the jdialog when the button is pressed
				  System.out.println("WWW");
				  setVisible(false);
				  System.out.println(getTitle());
				  dispose();
			  }
			  
			});
		
		view_button.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
			    // display/center the jdialog when the button is pressed
				  System.out.println("GET");
				  System.out.println(getDescription());
				  
				  
			  }
			});
	 */
	
}

	
	

