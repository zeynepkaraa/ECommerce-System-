// Name: Zeynep Kara
// Student Id: 501102663
public class Shoes extends Product {
    private int size;
    private String colour;

    // Stock related information
    
    int blacksixStock;
    int blacksevenstock;
    int blackeightstock;
    int blackninestock;
    int blacktenstock;
    int brownsixstock;
    int brownsevenstock;
    int browneightstock;
    int brownninestock;
    int browntenstock;

    public Shoes( String name, String id, double price, int blacksixStock, int blacksevenstock ,int blackeightstock ,
      int blackninestock, int blacktenstock, int brownsixstock, int brownsevenstock, int browneightstock,int brownninestock,
    int browntenstock , int size, String colour)
    {
      super(name, id, price, 100000, Product.Category.SHOES);
      this.size = size;
      this.colour = colour;
      this.blacksixStock = blacksixStock;
      this.blacksevenstock = blacksevenstock;
      this.blackeightstock = blackeightstock;
      this.blackninestock = blackninestock;
      this.blacktenstock = blacktenstock;
      this.brownsixstock = brownsixstock;
      this.brownsevenstock = brownsevenstock;
      this.brownsevenstock = brownsevenstock;
      this.browneightstock = browneightstock;
      this.brownninestock = brownninestock;
      this.browntenstock = browntenstock;
    }
    

    // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for "Black" or "Brown" or size
  	// if it is one of these, return true, else return false
    if(productOptions.equalsIgnoreCase("6Black") || productOptions.equalsIgnoreCase("7Black") || productOptions.equalsIgnoreCase("8Black") ||
    productOptions.equalsIgnoreCase("9Black")|| productOptions.equalsIgnoreCase("10Black") || productOptions.equalsIgnoreCase("6Brown") || 
    productOptions.equalsIgnoreCase("7Brown") || productOptions.equalsIgnoreCase("8Brown") || productOptions.equalsIgnoreCase("9Brown")|| 
    productOptions.equalsIgnoreCase("10Brown"))
    {
      return true;
    }
    else{
      return false;
    }
  	
  }

  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and return) the number of stock for "6black" etc
  	
    if (productOptions.equals("6Black"))
    {
      return blacksixStock;
    }
    else if (productOptions.equals("7Black"))
    {
      return blacksevenstock;
    }
  	else if (productOptions.equals("8Black"))
    {
      return blackeightstock;
    }
    else if (productOptions.equals("9Black"))
    {
      return blackninestock;
    }
    else if (productOptions.equals("10Black"))
    {
      return blacktenstock;
    }
    else if (productOptions.equals("6Brown"))
    {
      return brownsixstock;
    }
    else if (productOptions.equals("7Brown"))
    {
      return brownsevenstock;
    }
    else if (productOptions.equals("8Brown"))
    {
      return browneightstock;
    }
    else if (productOptions.equals("9Brown"))
    {
      return brownninestock;
    }
    else 
    
      return browntenstock;
    
	}
  
  public void setStockCount(int stockCount, String productOptions)
	{
    // Use the productOptions to check for (and set) the number of stock for "6Black" etc
   	
    if (productOptions.equals("6Black"))
    {
      stockCount = blacksixStock;
    }
    else if (productOptions.equals("7Black"))
    {
      stockCount = blacksevenstock;
    }
    else if (productOptions.equals("8Black"))
    {
      stockCount = blackeightstock;
    }
    else if (productOptions.equals("9Black"))
    {
      stockCount = blackninestock;
    }
    else if (productOptions.equals("10Black"))
    {
      stockCount = blacktenstock;
    }
    else if (productOptions.equals("6Brown"))
    {
      stockCount = brownsixstock;
    }
  	else if (productOptions.equals("7Brown"))
    {
      stockCount = brownsevenstock;
    }
    else if (productOptions.equals("8Brown"))
    {
      stockCount = browneightstock;
    }
    else if (productOptions.equals("9Brown"))
    {
      stockCount = brownninestock;
    }
    else
      stockCount = browntenstock;
	}
  
  /*
   * When a shoe is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and reduce) the number of stock for "6Black" etc
   	// Use the variables stock values at the top. 
   	
     
    if (productOptions.equals("6Black"))
    {
       blacksixStock--;
    }
    else if (productOptions.equals("7Black"))
    {
       blacksevenstock--;
    }
    else if (productOptions.equals("8Black"))
    {
       blackeightstock--;
    }
    else if (productOptions.equals("9Black"))
    {
       blackninestock--;
    }
    else if (productOptions.equals("10Black"))
    {
       blacktenstock--;
    }
    else if (productOptions.equals("6Brown"))
    {
       brownsixstock--;
    }
    else if (productOptions.equals("7Brown"))
    {
       brownsevenstock--;
    }
    else if (productOptions.equals("8Brown"))
    {
       browneightstock--;
    }
    else if (productOptions.equals("9Brown"))
    {
       brownninestock--;
    }
    else
      browntenstock--;
	}
  public void print()
  {
  	// Replace the line below.
  	// Make use of the super class print() method and append the size and colour info. See the video
  	super.print();
    System.out.printf(" Shoe Size: %-6s Colour: %-9s ", size , colour);
    
  }
    
}
