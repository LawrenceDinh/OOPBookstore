import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * A class that manages ItemClass objects
 * @author BT
 *
 */
public class ItemManager 
{	
	private HashMap<Long, ItemClass> itemMap;
	private boolean itemsFull;
	private long lastItemID;
	private long previousItemID;
	private String[] categories;
	
	/**
	 * Constructs an ItemManager and reads ItemClass data from file storage into Map storage
	 */
	public ItemManager()
	{
		itemMap=new HashMap<Long, ItemClass>();
		itemsFull=false;
		lastItemID=0;
		previousItemID=0;
		categories=new String[]{"Automotive", "Books", "Childcare Accesories", "Clothing", "Electronics", "Entertainment Media", 
				"Health & Beauty", "Home Improvement", "Sporting Goods", "Toys", "Other"};
		readItems();
	}
	
	/**
	 * Gets the String array of preselected item types
	 * @return the String array of categories
	 */
	public String[] getCategories()
	{
		return categories;
	}
	
	/**
	 * Gets the String name of the category with the provided index
	 * @param index the categoryNumber index
	 * @return the String category name
	 */
	public String getCategory(int index)
	{
		return categories[index];
	}
	
	/**
	 * Gets the ItemClass object with the provided item IDs from Map storage
	 * @param id the itemID of the desired ItemClass object
	 * @return the ItemClass with the given ID
	 */
	public ItemClass searchItemID(long id)
	{
		return itemMap.get(id);
	}
	
	/**
	 * Gets the ItemClass objects with the provided item IDs from Map storage
	 * @param ids the itemID of the desired ItemClass objects
	 * @return an ArrayList of ItemClass objects with the given IDs
	 */
	public ArrayList<ItemClass> searchItemIDs(HashSet<Long> ids)
	{
		ArrayList<ItemClass> matches=new ArrayList<>();
		for(long id: ids)
		{
			matches.add(itemMap.get(id));
		}
		return matches;
	}
	
	/**
	 * Gets the ItemClass objects with the provided item name from Map storage
	 * @param name the item name to be searched for
	 * @return an ArrayList of ItemClass objects with the given name
	 */
	public ArrayList<ItemClass> searchItemName(String name)
	{
		Iterator<Map.Entry<Long, ItemClass>> itr = itemMap.entrySet().iterator();
		ArrayList<ItemClass> matches=new ArrayList<ItemClass>();
		while(itr.hasNext())
    	{
			
			ItemClass tempItem=itr.next().getValue();
			String checkedName=tempItem.getItemName();
			if(name.toLowerCase().equals(checkedName.toLowerCase()))
			{
				matches.add(tempItem);
			}
    	}
		return matches;
	}
	
	/**
	 * Gets the ItemClass objects with the provided category numbers from Map storage
	 * @param categoryNum the category number to be searched for
	 * @return an ArrayList of ItemClass objects with the given category number
	 */
	public ArrayList<ItemClass> searchItemCategory(int categoryNum)
	{
		Iterator<Map.Entry<Long, ItemClass>> itr = itemMap.entrySet().iterator();
		ArrayList<ItemClass> matches=new ArrayList<ItemClass>();
		while(itr.hasNext())
    	{
			
			ItemClass tempItem=itr.next().getValue();
			int checkedCategoryNumber=tempItem.getCategoryNumber();
			if(checkedCategoryNumber==categoryNum)
			{
				matches.add(tempItem);
			}
    	}
		return matches;
	}
	
	/**
	 * Creates an ItemClass object with given characteristics and places it in Map storage
	 * @param itemName the name of the new item
	 * @param categoryNumber the category number of the new item
	 * @param description the description of the new item
	 * @param sellerName the name of the seller of the new item
	 * @return the new ItemClass object
	 */
	public ItemClass generateItem(String itemName, int categoryNumber, String description, String sellerName)
	{
		lastItemID++;
		ItemClass current=new ItemClass(lastItemID, itemName, categoryNumber, description, sellerName);
		itemMap.put(lastItemID, current);
		previousItemID=Long.valueOf(lastItemID);
		return current;
	}
	
	public long getPreviousItemID()
	{
		return previousItemID;
	}
	
	/**
	 * Removes the item with given ID from Map storage
	 * @param aItemID the ID of the item to be removed
	 */
	public void deleteItem(Long aItemID)
	{
		itemMap.remove(aItemID);
	}
	
	/**
	 * Reads ItemClass data from file storage into Map storage
	 */
	public void readItems()
	{
		try
		{
			File file = new File("E:\\Eclipse\\0Fall151\\ws\\LabProject\\src\\itemList.txt");
	        FileReader fr = new FileReader(file);
	        BufferedReader br = new BufferedReader(fr);
	        String line="";
	        boolean done=false;
	        boolean first=true;
	        while(!done)
	        {
	        	line=br.readLine();
	        	if(line==null)
	        	{
	        		done=true;
	        	}
	        	else if(first)
	        	{
	        		lastItemID=Long.parseLong(line);
	        		first=false;
	        	}
	        	else
	        	{
	        		String[] temp=new String[5];
	        		temp=line.split("~");
	        		ItemClass tempUser=new ItemClass(Long.parseLong(temp[0]), temp[1], Integer.parseInt(temp[2]), temp[3], temp[4]);
	            	itemMap.put(Long.parseLong(temp[0]),tempUser);
	        	}
	        }
	        br.close();
	        fr.close();
	        itemsFull=true;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Item file not found!");
		}
		catch(IOException e)
		{
			System.out.println("Items read error!");
		}
		
	}
	
	/**
	 * Writes ItemClass data from Map storage into file storage
	 */
	public void writeItems()
	{
		try
		{
			File file = new File("E:\\Eclipse\\0Fall151\\ws\\LabProject\\src\\itemList.txt");
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(""+lastItemID);
	        bw.newLine();
	        while(!itemMap.isEmpty())
	        {
	        	Iterator<Map.Entry<Long, ItemClass>> itr = itemMap.entrySet().iterator();
	        	while(itr.hasNext())
	        	{
	        		ItemClass tempItem=itr.next().getValue();
	        		itr.remove();
	        		String tempStringID=""+tempItem.getItemID();
	        		String tempItemName=tempItem.getItemName();
	        		String tempCategory=""+tempItem.getCategoryNumber();
	        		String tempDescription=tempItem.getDescription();
	            	String tempSellerName=""+tempItem.getSellerName();

	                String line=tempStringID + "~" + tempItemName + "~" + tempCategory+ "~" + tempDescription + "~" + tempSellerName;
	                bw.write(line);
	                bw.newLine();
	        	}
	        }
	        bw.close();
	        fw.close();
	        itemsFull=false;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Item file not found!");
		}
		catch(IOException e)
		{
			System.out.println("Items write error!");
		}
		
	}
	
	
}
