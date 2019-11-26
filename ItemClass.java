public class ItemClass 
{
	private final long itemID;
	private String itemName;
	private String category;
	private String description;
	private final long sellerID;
	private boolean deletedBool;
	
	
	public ItemClass(long itemID, String itemName, String category, String description, long sellerID, boolean deleted)
	{
		this.itemID=itemID;
		this.itemName=itemName;
		this.category = category;
		this.description=description;
		this.sellerID=sellerID;
	}
	
	//getters and setters
	
	
	public long getItemID()
	{
		return itemID;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public void setItemName(String newName)
	{
		itemName=newName;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category=category;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String newDescription)
	{
		description=newDescription;
	}
	
	public Long getSellerID()
	{
		return sellerID;
	}
	
	public boolean getDeletedStatus()
	{
		return deletedBool;
	}
	
	public void setDeletedStatus(boolean aDeletionStatus)
	{
		deletedBool=aDeletionStatus;
	}
	
	
	
	
	
	
	
}


