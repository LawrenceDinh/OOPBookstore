import java.util.concurrent.CountDownLatch;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ItemPoster extends JFrame{

	private static JTextArea descriptionField;
	//private static String NowDate="";
	
	private JTextField titleName;
	public JFrame frame;
	//public JButton uploadButton;
	public JButton postButton;
	public JButton cancelButton;
	
	public static JLabel imglbl;
	
	private ItemManager itemManager;
	public ItemClass itemplug = new ItemClass( 0,"", 0, "", "");
	//long itemID, String itemName, int categoryNumber, String description, String sellerName AS PARAMETER
	
	private String categories[];
	public JList cs; 
	
	private UserClass usr;
	private CountDownLatch cdLatch;
	 //long userID, String userName, String password, HashSet<Long> listedItemIDs) 
	
	public ItemPoster(CountDownLatch latch, ItemManager itemman, UserClass userInfo)
	{

		cdLatch = latch;
		itemManager = itemman;
		categories = itemManager.getCategories();
		usr = userInfo;
	}
	
	public JScrollPane categoryScroll(String list[]) //Creating J scrollCategory lists 
  {
	  cs = new JList(categories);
	  // create JList with categories
	  
      cs.setOpaque(true);
      cs.setVisibleRowCount(4);
      //4rows displayed
      cs.setSelectedIndex(0);
      //default selected index
      JScrollPane scrollCategory = new JScrollPane(cs);
      return scrollCategory;
  }
	
	
  public void GridLayOut() // layout with grid
  {
	  frame = new JFrame();
	  frame.setSize(600, 600);
	  	
	  	
		postButton = new JButton("Post");
		cancelButton = new JButton("cancel");
		//button groups

		buttonAction() ;// buttonAction method includes all button's actions

	    JPanel p1 = new JPanel();
	    JPanel p2 = new JPanel();
	    JPanel p3 = new JPanel();
	    // set the layout 
	    p1.setLayout(new GridLayout(3, 2)); 
	    p2.setLayout(new GridLayout(2, 2)); 
	    p3.setLayout(new GridLayout(1, 2));
	    // Creating Object P1, P2, P3 of JPanel class  
	    
	    final int FIELD_WIDTH = 35;
	    
	    JLabel titleQ = new JLabel("\t   Title : ");
	    JLabel useridQ = new JLabel("\t   The User ID : ");
	    JLabel categoryQ = new JLabel("\t   Category lists ");
	    JLabel descriptionQ = new JLabel("\t   Description ");
	    // all question Jlabel groups
	    
	    titleName = new JTextField(FIELD_WIDTH);
	    titleName.setFont(titleName.getFont().deriveFont(30f));
	    //adjust font size in Jtext field
	    
	    titleName.setText("Enter title:");
	   
	    JLabel guiId = new JLabel(usr.getUserName());
	    // get userId from user and assing in Id label
	    
	    descriptionField = new JTextArea(10, FIELD_WIDTH); // size of description field
	    descriptionField.setText("Enter description:");
	    
	    p1.add(titleQ);
	    p1.add(titleName);
	    p1.add(useridQ);
	    p1.add(guiId);
	    //add labels on p1
	    
	    p1.add(categoryQ);
	    p1.add(categoryScroll(categories));
	    // add category list
	    //p1.add(descriptionQ);
	    //p1.add(descriptionField);
	    
	   // p2.add(uploadButton);
	    //p2.add(imglbl);
	    p2.add(descriptionQ);
	    p2.add(descriptionField);
	    //add description fields
	    
	    p3.add(postButton);
	    p3.add(cancelButton);
	   //add buttons
	    
	    frame.add(p1, "North");
	    frame.add(p2, "Center");
	    frame.add(p3, "South");
	    //set the location of grided label
  }
  
  //In this method, there are button action groups 
  public void buttonAction() 
  {  
	  postButton.addActionListener(new ActionListener() { 
			 
      	  public void actionPerformed(ActionEvent e) 
	      {
      		
	        if (e.getActionCommand().equals("Post"))
	        {
	            
	            String itemTitle = titleName.getText();
	            //entered Jtext get converted into string item title 
	            
	            System.out.println(itemTitle);
	            
	            String descript = descriptionField.getText();
	            //entered Jtext get converted into string description 
	            
	            System.out.println(descript);
	            System.out.println(cs.getSelectedValue());
	           
	            
	            String catego = (String)cs.getSelectedValue();
	            //selected category 
	            
	            int categoNum =0;
	            
	            //categories == "Automotive", "Books", "Childcare Accesories", "Clothing", "Electronics", "Entertainment Media",
	            //"Health & Beauty", "Home Improvement", "Sporting Goods", "Toys", "Other"
		
	            
	            // set category number following selected category 
	            if(catego.equals(categories[0]))
	            	categoNum =0;
	            else if(catego.equals(categories[1]))
	            	categoNum =1;
	            else if(catego.equals(categories[2]))
	            	categoNum =2;
	            else if(catego.equals(categories[3]))
	            	categoNum =3;
	            else if(catego.equals(categories[4]))
	            	categoNum =4;
	            else if(catego.equals(categories[5]))
	            	categoNum =5;
	            else if(catego.equals(categories[6]))
	            	categoNum =6;
	            else if(catego.equals(categories[7]))
	            	categoNum =7;
	            else if(catego.equals(categories[8]))
	            	categoNum =8;
	            else if(catego.equals(categories[9]))
	            	categoNum =9;
	            else if(catego.equals(categories[10]))
	            	categoNum =10;
	          
	            itemplug.setCategory(categoNum);
	            
	            itemManager.generateItem(itemTitle, categoNum, descript, usr.getUserName());
	            //(String itemName, int categoryNumber, String description, String sellerName)
	           //generate item Id and register items on the list
	            
	            frame.dispose();
	            //end this window
	            
	           usr.addListedItemID(itemManager.getPreviousItemID());
	           // add item id
	           
	           cdLatch.countDown();
	            //They make an item element on the itemmanager
	        }
	     
	      }
	    });
	  
	  cancelButton.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) 
	        {
	          frame.setVisible(false);
	          //black out this window
	          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	          frame.dispose();
	          //close window
	        }
	    });
  }
  
  
  public void dragAndDropDisplay() {//display method to call displaying methods
	  
	    GridLayOut();
	
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
}
