import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.*; 

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
	
	//public UserClass usr= new UserClass(0, "" , "" ,  new HashSet<Long>() ); 
	//long userID, String userName, String password, HashSet<Long> listedItemIDs
	
	//private static String NowDate="";
	
	public shownItem(ItemClass itemOBJ) 
	{
		itemplug = itemOBJ;
    }
  
	/*private static BufferedImage resize(BufferedImage img, int height, int width) 
	{
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
  */
	
  /*public static ImageIcon ResizeImage(String ImagePath)
  {
      ImageIcon MyImage = new ImageIcon(ImagePath);
      Image img = MyImage.getImage();
      Image newImg = img.getScaledInstance(500, 350, Image.SCALE_SMOOTH);
      ImageIcon image = new ImageIcon(newImg);
      return image;
  }*/
  
  /*public static JLabel timeLabel()
  {
	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	    LocalDateTime now = LocalDateTime.now();
	    
		 JLabel timelbl = new JLabel(dtf.format(now));         
		 return timelbl;
  }*/
  
  public void GridLayOut() 
  {
	  
	  frame = new JFrame();
	  frame.setSize(600, 600);
	  	
	  //backButton = new JButton("back");
	  
		closeButton = new JButton("close");
		JLabel imglbl = new JLabel();
	    ImageIcon icon = new ImageIcon();
		
	    JPanel p1 = new JPanel();
	    JPanel p2 = new JPanel();
	    JPanel p3 = new JPanel();
	    // set the layout 
	    p1.setLayout(new GridLayout(3, 2)); 
	    p2.setLayout(new GridLayout(1, 2)); 
	    p3.setLayout(new GridLayout(1, 1));
	    // Creating Object P2 of JPanel class  
	    
	    final int FIELD_WIDTH = 35;
	    
	    JLabel titleQ = new JLabel("\t   Title : ");
	    JLabel useridQ = new JLabel("\t   The User ID : ");
	    JLabel categoryQ = new JLabel("\t   Category lists : ");
	    JLabel descriptionQ = new JLabel("\t   Description ");
	    JLabel imgQ = new JLabel("\t image : ");
	    
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
	    
	     buttonAction() ;
	    p1.add(titleQ);
	    p1.add(titleName);
	    p1.add(useridQ);
	    p1.add(guiId);
	    
	    p1.add(categoryQ);
	    p1.add(category);
	    //p1.add(descriptionQ);
	    //p1.add(descriptionField);
	    p2.add(descriptionQ);
	    p2.add(pane);
	    
	    p3.add(closeButton);
	   
	    frame.add(p1, "North");
	    frame.add(p2, "Center");
	    frame.add(p3, "South");
	    
  }
  
  public void buttonAction() 
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
	
	  	//List<BufferedImage> list = new ArrayList<BufferedImage> ();

//	    Container contentPane = frame.getContentPane();
//	    GroupLayout groupLayout = new GroupLayout(contentPane);  

	    final int FIELD_WIDTH = 40;
	    
	    String categor = "" ;
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
	    	    
	    GridLayOut();
	    
	   /*
	    *  groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
	    
	    		.addGroup(groupLayout.createParallelGroup(LEADING)
	    				.addComponent(titleQ).addComponent(titlenm)
	    		
	    		.addGroup(groupLayout.createParallelGroup(CENTER)
	    				.addComponent(backButton)
	    				.addComponent(cancelButton)
	    				)
	    			.addGroup(groupLayout.createParallelGroup(LEADING)
		    				.addComponent(imglbl)
	    			)
	    			.addGroup(groupLayout.createParallelGroup(LEADING)
		    				.addComponent(categoryQ).addComponent(category)
		    				
	    					)
	    			
	    			.addGroup(groupLayout.createParallelGroup(LEADING)
		    				.addComponent(useridQ)
		    				.addComponent(guiId)
		    				
		    				)
	    			
	    			.addGroup(groupLayout.createParallelGroup(LEADING)
	    					.addGroup(groupLayout.createParallelGroup(LEADING)
	    							.addGroup(groupLayout.createParallelGroup(LEADING).addComponent(pane))
	    							)		    				
	    					)
	    			
	    				));
	    
	    
	    groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
	    		.addGroup(groupLayout.createSequentialGroup()
	    				.addGroup(groupLayout.createParallelGroup(BASELINE)
	    						.addComponent(titleQ).addComponent(titlenm)))
	    		.addGroup(groupLayout.createParallelGroup(BASELINE)
	    				.addComponent(backButton).addComponent(cancelButton))
	    		
	    		.addGroup(groupLayout.createParallelGroup(BASELINE)
	    				.addComponent(imglbl))
 		
	    		.addGroup(groupLayout.createParallelGroup(BASELINE)
	    				.addComponent(categoryQ).addComponent(category))
	    		
	    		.addGroup(groupLayout.createParallelGroup(BASELINE)
	    				.addComponent(useridQ).addComponent(guiId))
	    		
	    		.addGroup(groupLayout.createParallelGroup(BASELINE)
	    				.addComponent(pane)
	    				)
	    		);
	 */   
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    return frame;
  }
  
  
}


