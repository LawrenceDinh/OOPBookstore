import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
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
	
	public ItemManager()
	{
		itemMap=new HashMap<Long, ItemClass>();
		itemsFull=false;
		lastItemID=0;
		previousItemID=0;
		readItems();
	}
	
	public ItemClass searchItemID(long id)
	{
		return itemMap.get(id);
	}
	
	public HashMap<Long, ItemClass> searchItemName(String name)
	{
		Iterator<Map.Entry<Long, ItemClass>> itr = itemMap.entrySet().iterator();
		HashMap<Long, ItemClass> matches=new HashMap<Long, ItemClass>();
		while(itr.hasNext())
    	{
			
			ItemClass tempItem=itr.next().getValue();
			String checkedName=tempItem.getItemName();
			if(name.toLowerCase().equals(checkedName.toLowerCase()))
			{
				matches.put(tempItem.getItemID(), tempItem);
			}
    	}
		return matches;
	}
	
	public HashMap<Long, ItemClass> searchItemCategory(String category)
	{
		Iterator<Map.Entry<Long, ItemClass>> itr = itemMap.entrySet().iterator();
		HashMap<Long, ItemClass> matches=new HashMap<Long, ItemClass>();
		while(itr.hasNext())
    	{
			
			ItemClass tempItem=itr.next().getValue();
			String checkedName=tempItem.getCategory();
			if(category.toLowerCase().equals(checkedName.toLowerCase()))
			{
				matches.put(tempItem.getItemID(), tempItem);
			}
    	}
		return matches;
	}
	
	public ItemClass generateItem(String itemName, String category, String description, long sellerID)
	{
		lastItemID++;
		ItemClass current=new ItemClass(lastItemID, itemName, category, description, sellerID, false);
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
		ItemClass tempItem=itemMap.get(aItemID);
		tempItem.setDeletedStatus(true);
		itemMap.put(aItemID, tempItem);
	}
	
	public void readItems()
	{
		try
		{
			File file = new File("itemList.txt");
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
	        		String[] temp=new String[6];
	        		temp=line.split("~");
	        		boolean deletedStatus=false;
	        		if(temp[5]=="1")
	        		{
	        			deletedStatus=true;
	        		}
	        		ItemClass tempUser=new ItemClass(Long.parseLong(temp[0]), temp[1], temp[2], temp[3], Long.parseLong(temp[4]), deletedStatus);
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
			File file = new File("itemList.txt");
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
	        		String tempCategory=tempItem.getCategory();
	        		String tempDescription=tempItem.getDescription();
	            	String tempSellerID=""+tempItem.getSellerID();
	            	String deletionStatusString="0";
	            	if(tempItem.getDeletedStatus())
	            	{
	            		deletionStatusString="1";
	            	}

	                String line=tempStringID + "~" + tempItemName + "~" + tempCategory+ "~" + tempDescription + "~" + tempSellerID + "~" + deletionStatusString;
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
