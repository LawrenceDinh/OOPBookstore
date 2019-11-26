import java.util.HashSet;

public class UserClass 
{
	private final long userID;
	private String userName;
	private String password;
	private HashSet<Long> listedItemIDs;
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
	
}
