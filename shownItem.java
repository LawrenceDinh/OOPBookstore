import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import java.io.*;

public class shownItem extends JFrame{

	private JTextArea descriptionField;
	private JLabel titleName;
	private JLabel guiId;
	
	//public JButton backButton;
	public JButton closeButton;
	private JFrame frame;
	private JLabel category;
	
	private ItemClass itemplug;// = new ItemClass(0,"",0,"","");
	//long itemID, String itemName, int categoryNumber, String description, String sellerID
	
	public shownItem(ItemClass itemOBJ) 
	{
		itemplug = itemOBJ;
    }
	//constructor to get ItemClass object
	
	public void GridLayOut()  //grid layout method
	{
	  
	  frame = new JFrame();
	  frame.setSize(600, 600);
	  	
	  //backButton = new JButton("back");
	  
		closeButton = new JButton("close");
		//button
		
	    JPanel p1 = new JPanel();
	    JPanel p2 = new JPanel();
	    JPanel p3 = new JPanel();
	    // set the layout with these panels
	    
	    p1.setLayout(new GridLayout(3, 2)); 
	    p2.setLayout(new GridLayout(1, 2)); 
	    p3.setLayout(new GridLayout(1, 1));
	    // Creating p1,P2,p3 of JPanel class  
	    
	    final int FIELD_WIDTH = 35;
	    
	    JLabel titleQ = new JLabel("\t   Title : ");
	    JLabel useridQ = new JLabel("\t   The User ID : ");
	    JLabel categoryQ = new JLabel("\t   Category lists : ");
	    JLabel descriptionQ = new JLabel("\t   Description ");
	    // questionair Jlabel
	    
	    titleName = new JLabel(itemplug.getItemName());
	    titleName.setFont(titleName.getFont().deriveFont(18f));
	    //Adjust font size
	    
	    guiId = new JLabel(itemplug.getSellerName());
	    
	    descriptionField = new JTextArea(10, FIELD_WIDTH);
	    descriptionField.setText(itemplug.getDescription());
	    
	    JScrollPane pane = new JScrollPane(descriptionField);
	     pane.setPreferredSize(new Dimension(450, 200));
	     pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	     //scroll function to scroll JTextbox vertically and horizontally
	    
	     buttonAction() ;// buttonAction method includes all button's actions
	     
	    p1.add(titleQ);
	    p1.add(titleName);
	    p1.add(useridQ);
	    p1.add(guiId);
	    
	    p1.add(categoryQ);
	    p1.add(category);
	    
	    p2.add(descriptionQ);
	    p2.add(pane);
	    
	    p3.add(closeButton);
	   
	    frame.add(p1, "North");
	    frame.add(p2, "Center");
	    frame.add(p3, "South");
	    
  }
  
  public void buttonAction() // buttonAction method includes all button's actions
  {
	  closeButton.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	        
	          frame.setVisible(false);
	          frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	          frame.dispose();
	          //close
	        }
	    });

	  /*backButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          
	        }
	    });
		*/
  }
  
  public JFrame display_selectedItem() {
	

	    final int FIELD_WIDTH = 40;
	    
	    String categor = "" ;
	    //set actual category following categorynumber 
	    if(itemplug.getCategoryNumber()==0)
	    	categor = "Automotive";	
	    else if(itemplug.getCategoryNumber()==1)
	    	categor = "Books";
	    else if(itemplug.getCategoryNumber()==2)
	    	categor = "Childcare Accesories";
	    else if(itemplug.getCategoryNumber()==3)
	    	categor = "Clothing";
	    else if(itemplug.getCategoryNumber()==4)
	    	categor = "Electronics";
	    else if(itemplug.getCategoryNumber()==5)
	    	categor = "Entertainment Media";
	    else if(itemplug.getCategoryNumber()==6)
	    	categor = "Health & Beauty";
	    else if(itemplug.getCategoryNumber()==7)
	    	categor = "Home Improvement";	
	    else if(itemplug.getCategoryNumber()==8)
	    	categor = "Sporting Goods";
	    else if(itemplug.getCategoryNumber()==9)
	    	categor ="Toys";
	    else if(itemplug.getCategoryNumber()==10)
	    	categor = "Other";
	    //"Automotive", "Books", "Childcare Accesories", "Clothing", "Electronics", "Entertainment Media", 
		//"Health & Beauty", "Home Improvement", "Sporting Goods", "Toys", "Other"};
			
	    category = new JLabel(categor);
	    //assign selected category in JLabel
	    	    
	    GridLayOut();// call Gridlayout
	    
	  
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    return frame;
  }
  
  
}