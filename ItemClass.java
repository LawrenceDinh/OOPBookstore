public class ItemClass 
{
	private final long itemID;
	private String itemName;
	private int categoryNumber;
	private String description;
	private final String sellerName;
	
	
	public ItemClass(long itemID, String itemName, int categoryNumber, String description, String sellerName)
	{
		this.itemID=itemID;
		this.itemName=itemName;
		this.categoryNumber = categoryNumber;
		this.description=description;
		this.sellerName=sellerName;
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
	
	public int getCategoryNumber()
	{
		return categoryNumber;
	}
	
	public void setCategory(int categoryNumber)
	{
		this.categoryNumber=categoryNumber;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String newDescription)
	{
		description=newDescription;
	}
	
	public String getSellerName()
	{
		return sellerName;
	}
	
}


