import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * A class that manages UserClass objects
 * @author BT
 *
 */
public class UserManager 
{
	private HashMap<String, UserClass> userMap;
	private boolean usersFull;
	private long lastUserID;
	private long previousUserID;
	
	/**
	 * Constructs a UserManager and reads UserClass data from file storage into Map storage
	 */
	public UserManager()
	{
		userMap=new HashMap<String, UserClass>();
		usersFull=false;
		lastUserID=0;
		previousUserID=0;
		readUsers();
	}
	
	/**
	 * Gets the UserClass object with the given name from Map storage
	 * @param name the name of the desired user
	 * @return the UserClass object with the given name
	 */
	public UserClass searchUserName(String name)
	{
		return userMap.get(name);
	}
	
	/**
	 * Gets the ID number of the user with the given name from Map storage
	 * @param name the name of the desired user
	 * @return the ID number of the desired user
	 */
	public long searchIDbyName(String name)
	{
		return searchUserName(name).getUserID();
	}
	
	/**
	 * Gets the IDs of the posted items of the user with the given name from Map storage
	 * @param name the name of the desired user
	 * @return the given user's posted items
	 */
	public HashSet<Long> getUsersListedItems(String name)
	{
		return userMap.get(name).getListedItemIDs();
	}
	
	/**
	 * Adds the given UserClass object into Map storage
	 * @param aUser the user to be added
	 */
	public void addUser(UserClass aUser)
	{
		userMap.put(aUser.getUserName(),aUser);
	}
	
	/**
	 * Creates a UserClass object with given characteristics and places it into Map storage
	 * @param userName the name of the new user
	 * @param password the password of the new user
	 * @return the new UserClass object
	 */
	public UserClass generateUser(String userName, String password)
	{
		lastUserID++;
		HashSet<Long> tempSet=new HashSet<Long>();
		tempSet.add((long) 0);
		UserClass current = new UserClass(lastUserID, userName, password, tempSet);
		userMap.put(userName, current);
		previousUserID=Long.valueOf(lastUserID);
		return current;
	}
	
	public long getPreviousUserID()
	{
		return previousUserID;
	}
	
	/**
	 * Checks whether the user name is in use for Account Signup
	 * @param name the user name to be checked
	 * @return the availability status of the user name
	 */
	public boolean userNameFree(String name)
	{
		if(userMap.containsKey(name))
		{
				return false;
		}
		return true;
	}
	
	/**
	 * Checks user credentials for Login
	 * @param name the user name entered
	 * @param pass the password entered
	 * @return the UserClass object with matching name and password
	 */
	public UserClass searchCredentials(String name, String pass)
	{
		Iterator<Map.Entry<String, UserClass>> itr = userMap.entrySet().iterator();
		while(itr.hasNext())
    	{
			UserClass tempUser=itr.next().getValue();
			String checkedName=tempUser.getUserName();
			String checkedPassword=tempUser.getPassword();
			if(name.equals(checkedName) && pass.equals(checkedPassword))
			{
				return tempUser;
			}
    	}
		return null;
	}
	
	/**
	 * Reads UserClass data from file storage into Map storage
	 */
	public void readUsers()
	{
		try
		{
			File file = new File("E:\\Eclipse\\0Fall151\\ws\\LabProject\\src\\userList.txt");
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
	        		lastUserID=Long.parseLong(line);
	        		first=false;
	        	}
	        	else
	        	{
	        		String[] temp=new String[4];
	        		temp=line.split("~");
	        		
	        		String[] tempListedItems=temp[3].split("`");
	        		HashSet<Long> listedItems=new HashSet<Long>();
	        		for(String tempListedItem: tempListedItems)
	        		{
	        			listedItems.add(Long.parseLong(tempListedItem));
	        		}
	        		
	        		UserClass tempUser=new UserClass(Long.parseLong(temp[0]), temp[1], temp[2], listedItems);
	            	userMap.put(temp[1],tempUser);
	        	}
	        }
		    br.close();
	        fr.close();
	        usersFull=true;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("User file not found!");
		}
		catch(IOException e)
		{
			System.out.println("Users read error!");
		}

    
        
	}
	
	/**
	 * Writes UserClass data from Map storage into file storage
	 */
	public void writeUsers()
	{
		try
		{
			File file = new File("E:\\Eclipse\\0Fall151\\ws\\LabProject\\src\\userList.txt");
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(""+lastUserID);
	        bw.newLine();
	        while(!userMap.isEmpty())
	        {
	        	Iterator<Map.Entry<String, UserClass>> itr = userMap.entrySet().iterator();
	        	while(itr.hasNext())
	        	{
	        		UserClass tempUser=itr.next().getValue();
	        		itr.remove();
	        		String tempStringID=""+tempUser.getUserID();
	        		String tempUserName=tempUser.getUserName();
	        		String tempPassword=tempUser.getPassword();
	            	String tempStringListedItems="";
	            	for(Long l: tempUser.getListedItemIDs())
	            	{
	            		tempStringListedItems+=(l+"`");
	            		
	            	}
	            	if(tempStringListedItems.length()>0)
	            	{
	            		tempStringListedItems=tempStringListedItems.substring(0,tempStringListedItems.length()-1);
	            	}
	                String line=tempStringID + "~" + tempUserName + "~" + tempPassword + "~" + tempStringListedItems;
	                bw.write(line);
	                bw.newLine();
	        	}
	        }
	        bw.close();
	        fw.close();
	        usersFull=false;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("User file not found!");
		}
		catch(IOException e)
		{
			System.out.println("Users write error!");
		}
		
		
	}
}
