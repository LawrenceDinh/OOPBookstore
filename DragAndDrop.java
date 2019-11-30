//Jin's File for Posting Items
import java.util.HashSet;
import static javax.swing.GroupLayout.Alignment.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import java.io.*;



public class DragAndDrop extends JFrame{

	private static JTextArea descriptionField;
	public static JTextField titleName;
	public static JButton uploadButton;
	public static JButton postButton;
	public static JButton cancelButton;
	public static String categories[] = { "Electric devices", "Cars", "Furnitures", "Toys", "Clothes"};
	
	//public static ItemClass itemplug = new ItemClass( 0,"","", "", 0, false);
	//long itemID, String itemName, String description, long sellerID, boolean deleted
	
	UserClass userr= new UserClass(0, "" , "" ,  new HashSet<Long>() ); 
	//long userID, String userName, String password, HashSet<Long> listedItemIDs
	
	private static String NowDate="";
	
	private static BufferedImage resize(BufferedImage img, int height, int width) 
	{
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
  
	public static JScrollPane categoryScroll(String list[])
  {
	  
	  JList cs = new JList(categories) ;  	  
	  

	
	  postButton.addActionListener(new ActionListener() { 
		 
      	  public void actionPerformed(ActionEvent e) 
	      {
      		//  itemplug.setItemName("ss");
      		
	        if (e.getActionCommand().equals("post"))
	        {
	            int index = cs.getSelectedIndex();
	        
	            String itemTitle = titleName.getText();
	            System.out.println(itemTitle);
	          //  itemplug.setItemName(itemTitle);
	            
	            String descript = descriptionField.getText();
	            System.out.println(descript);
	         //   itemplug.setDescription(itemTitle);
	            
	            
	            String category = (String) cs.getSelectedValue();
	            System.out.println(category);
	         //   itemplug.setCategory(category);
	        }
	     
	      }
	    });
      
      cs.setOpaque(true);
      cs.setVisibleRowCount(4);
	
      JScrollPane scrollCategory = new JScrollPane(cs);
      return scrollCategory;
  }
  
  public static ImageIcon ResizeImage(String ImagePath)
  {
      ImageIcon MyImage = new ImageIcon(ImagePath);
      Image img = MyImage.getImage();
      Image newImg = img.getScaledInstance(500, 350, Image.SCALE_SMOOTH);
      ImageIcon image = new ImageIcon(newImg);
      return image;
  }
  
  public static JLabel timeLabel()
  {
	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	    LocalDateTime now = LocalDateTime.now();
	    
		 JLabel timelbl = new JLabel(dtf.format(now));         
		 return timelbl;
  }
  
  
  
  public static void main(String avg[]) throws Exception {
	
	  	
	  	uploadButton = new JButton("Choose photo");
		postButton = new JButton("post");
		cancelButton = new JButton("cancel");
	   
		
	 	JFrame frame = new JFrame();
	    frame.setLayout(new FlowLayout());
	    frame.setSize(800, 800);
	    
	    Container contentPane = frame.getContentPane();
	    GroupLayout groupLayout = new GroupLayout(contentPane);  

	    final int FIELD_WIDTH = 35;
	    
	    JLabel titleQ = new JLabel("Title : ");
	    titleName = new JTextField(FIELD_WIDTH);
	    titleName.setText("Enter title");
	    
	    String ID = "user_id1";
	    JLabel useridQ = new JLabel("\t The User ID : ");
	    JLabel guiId = new JLabel(ID);
	    
	    JLabel categoryQ = new JLabel(" \tCategory lists ");
	    
	    descriptionField = new JTextArea(10, FIELD_WIDTH);
	    descriptionField.setText("Enter description");
	    
	    
	    
	    JLabel imglbl = new JLabel();
	    ImageIcon icon = new ImageIcon();
	    
	    uploadButton.addActionListener(new ActionListener() {

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
	    });
	    
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
	    		
	    		
	    		);
    
	    
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  
  
  
  
}





//http://www.java2s.com/Tutorials/Java/Graphics_How_to/Image/Display_Image_with_Swing_GUI.html
