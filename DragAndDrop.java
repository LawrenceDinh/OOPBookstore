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

public class DragAndDrop extends JFrame{

	private static JTextArea descriptionField;
	//private static String NowDate="";
	
	private JTextField titleName;
	public JFrame frame;
	public JButton uploadButton;
	public JButton postButton;
	public JButton cancelButton;
	
	public static JLabel imglbl;
	
	private ItemManager itemManager;
	public ItemClass itemplug = new ItemClass( 0,"", 0, "", "");
	//long itemID, String itemName, int categoryNumber, String description, String sellerName
	
	private String categories[] =itemManager.getCategories();
	public JList cs = new JList(categories) ; 
	
	private UserClass usr;
	 //long userID, String userName, String password, HashSet<Long> listedItemIDs) 
	
	public DragAndDrop(ItemManager itemman, UserClass userInfo)
	{
		itemManager = itemman;
		usr = userInfo;
	}
	
	public JScrollPane categoryScroll(String list[])
  {
	  categories = itemManager.getCategories();  
      cs.setOpaque(true);
      cs.setVisibleRowCount(4);
	
      JScrollPane scrollCategory = new JScrollPane(cs);
      return scrollCategory;
  }
	/*
  public ImageIcon ResizeImage(String ImagePath)
  {
      ImageIcon MyImage = new ImageIcon(ImagePath);
      Image img = MyImage.getImage();
      Image newImg = img.getScaledInstance(300, 180, Image.SCALE_SMOOTH);
      ImageIcon image = new ImageIcon(newImg);
      return image;
  }
  
  public String times()
  {
	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	    LocalDateTime now = LocalDateTime.now();
		 String time = dtf.format(now);         
		 return time;
  }
  */
  
  public void GridLayOut() 
  {
	  frame = new JFrame();
	  frame.setSize(600, 600);
	  	
	  	uploadButton = new JButton("Choose photo");
		postButton = new JButton("post");
		cancelButton = new JButton("cancel");
		imglbl = new JLabel();

		buttonAction() ;

	    JPanel p1 = new JPanel();
	    JPanel p2 = new JPanel();
	    JPanel p3 = new JPanel();
	    // set the layout 
	    p1.setLayout(new GridLayout(3, 2)); 
	    p2.setLayout(new GridLayout(2, 2)); 
	    p3.setLayout(new GridLayout(1, 2));
	    // Creating Object P2 of JPanel class  
	    
	    final int FIELD_WIDTH = 35;
	    
	    JLabel titleQ = new JLabel("\t   Title : ");
	    JLabel useridQ = new JLabel("\t   The User ID : ");
	    JLabel categoryQ = new JLabel("\t   Category lists ");
	    JLabel descriptionQ = new JLabel("\t   Description ");
	    JLabel imgQ = new JLabel("\t image : ");
	    
	    titleName = new JTextField(FIELD_WIDTH);
	    titleName.setFont(titleName.getFont().deriveFont(30f));
	    //adjust font size in Jtext field
	    titleName.setText("Enter title");
	   
	    JLabel guiId = new JLabel(usr.getUserName());
	    
	    descriptionField = new JTextArea(10, FIELD_WIDTH);
	    descriptionField.setText("Enter description");
	    
	    p1.add(titleQ);
	    p1.add(titleName);
	    p1.add(useridQ);
	    p1.add(guiId);
	    
	    p1.add(categoryQ);
	    p1.add(categoryScroll(categories));
	    //p1.add(descriptionQ);
	    //p1.add(descriptionField);
	    
	    p2.add(uploadButton);
	    //p2.add(imglbl);
	    p2.add(descriptionQ);
	    p2.add(descriptionField);
	    
	    p3.add(postButton);
	    p3.add(cancelButton);
	   
	    frame.add(p1, "North");
	    frame.add(p2, "Center");
	    frame.add(p3, "South");
	    
  }
  
  //In this method, there are button action groups 
  public void buttonAction() 
  {
	  /*uploadButton.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	        
	          JFileChooser file = new JFileChooser();
	          file.setCurrentDirectory(new File(System.getProperty("user.home")));
	          //filter the files
	          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
	          file.addChoosableFileFilter(filter);
	          int result = file.showSaveDialog(null);
	          
	          if(result == JFileChooser.APPROVE_OPTION){
	              File selectedFile = file.getSelectedFile();
	              String path = selectedFile.getAbsolutePath();
	              imglbl.setIcon(ResizeImage(path));
	          }

	          else if(result == JFileChooser.CANCEL_OPTION){
	              System.out.println("No File Select");
	          }
	        }
	    });*/
	  
	  postButton.addActionListener(new ActionListener() { 
			 
      	  public void actionPerformed(ActionEvent e) 
	      {
      		
	        if (e.getActionCommand().equals("post"))
	        {
	            //int index = cs.getSelectedIndex();
	            
	        
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
	            //They make an item element on the itemmanager
	        }
	     
	      }
	    });
	  
	  cancelButton.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) 
	        {
	          frame.setVisible(false);
	          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	          frame.dispose();
	          //close window
	        }
	    });
  }
  
  
  public JFrame dragAndDropDisplay() {
	
	  	
	  	//List<BufferedImage> list = new ArrayList<BufferedImage> ();
	    //Container contentPane = frame.getContentPane();
	    //GroupLayout groupLayout = new GroupLayout(contentPane);  

	    GridLayOut();
	      
	    /*
	    groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
	    		.addGroup(groupLayout.createParallelGroup(LEADING)
	    				.addComponent(titleQ).addComponent(titleName)
	    		
	    		.addGroup(groupLayout.createParallelGroup(CENTER)
	    				.addComponent(uploadButton)
	    				.addComponent(postButton)
	    				.addComponent(cancelButton)
	    				)
	    			.addGroup(groupLayout.createParallelGroup(LEADING)
		    				.addComponent(imglbl)
	    			)
	    			.addGroup(groupLayout.createParallelGroup(LEADING)
		    				.addComponent(categoryQ).addComponent(categoryScroll(categories))
		    				
	    					)
	    			
	    			.addGroup(groupLayout.createParallelGroup(LEADING)
		    				.addComponent(useridQ)
		    				.addComponent(guiId)
		    				
		    				)
	    			
	    			.addGroup(groupLayout.createParallelGroup(LEADING)
	    					.addGroup(groupLayout.createParallelGroup(LEADING)
	    							.addGroup(groupLayout.createParallelGroup(LEADING).addComponent(descriptionField))
	    							)		    				
	    					)
	    			
	    				));
	    
	    
	    groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
	    		.addGroup(groupLayout.createSequentialGroup()
	    				.addGroup(groupLayout.createParallelGroup(BASELINE)
	    						.addComponent(titleQ).addComponent(titleName)))
	    		.addGroup(groupLayout.createParallelGroup(BASELINE)
	    				.addComponent(uploadButton).addComponent(postButton).addComponent(cancelButton))
	    		
	    		.addGroup(groupLayout.createParallelGroup(BASELINE)
	    				.addComponent(imglbl))
 		
	    		.addGroup(groupLayout.createParallelGroup(BASELINE)
	    				.addComponent(useridQ).addComponent(guiId))
	    		
	    		.addGroup(groupLayout.createParallelGroup(BASELINE)
	    				.addComponent(descriptionField)
	    				
	    				)
	    		.addGroup(groupLayout.createParallelGroup(BASELINE)
	    				
	    				)
	    		);
	    */
	 
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    return frame;
  }
  
}





