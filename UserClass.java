import java.util.HashSet;

/**
 * A class to store user data
 * @author BT
 *
 */
public class UserClass 
{
	private final long userID;
	private String userName;
	private String password;
	private HashSet<Long> listedItemIDs;
	
	/**
	 * Constructs a UserClass
	 * @param userID the user's ID number
	 * @param userName the user's name
	 * @param password the user's password
	 * @param listedItemIDs the ID numbers of the User's posted items
	 */
	public UserClass(long userID, String userName, String password, HashSet<Long> listedItemIDs)
	{
		this.userID=userID;
		this.userName=userName;
		this.password=password;
		this.listedItemIDs=listedItemIDs;
	}
	
	//getters & setters
	
	public long getUserID()
	{
		return userID;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUsername(String newName)
	{
		userName=newName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String newPassword)
	{
		password=newPassword;
	}
	
	public HashSet<Long> getListedItemIDs()
	{
		return listedItemIDs;
	}
	
	public void setListedItemIDs(HashSet<Long> newListedItemIDs)
	{
		listedItemIDs=newListedItemIDs;
	}
	
	public void addListedItemID(long newListedItemID)
	{
		listedItemIDs.add(newListedItemID);
	}
	
	public void removeListedItemID(long newListedItemID)
	{
		listedItemIDs.remove(newListedItemID);
	}
	
}
