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

//TODO: Try/Catch for write and read methods
//TODO: Add specific criterion search methods (name etc.)
//TODO: add comments
//TODO: write test program and test this code
public class ItemManager 
{	
	private HashMap<Long, ItemClass> itemMap;
	private boolean itemsFull;
	private long lastItemID;
	private long previousItemID;
	private String[] categories;
	
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
	
	public String[] getCategories()
	{
		return categories;
	}
	
	public String getCategory(int index)
	{
		return categories[index];
	}
	
	public ItemClass searchItemID(long id)
	{
		return itemMap.get(id);
	}
	
	public ArrayList<ItemClass> searchItemIDs(HashSet<Long> ids)
	{
		ArrayList<ItemClass> matches=new ArrayList<>();
		for(long id: ids)
		{
			matches.add(itemMap.get(id));
		}
		return matches;
	}
	
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

	public void deleteItem(Long aItemID)
	{
		itemMap.remove(aItemID);
	}
	
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
