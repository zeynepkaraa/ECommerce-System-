// Name: Zeynep Kara
// Student Id: 501102663

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Random;
import java.util.Scanner;



/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
    
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    
    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    private HashMap <String,Product> myProducts;
    private HashMap <Product,Integer> stats = new HashMap<Product,Integer>();
    
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem()
    {
    	// NOTE: do not modify or add to these objects!! - the TAs will use for testing
    	// If you do the class Shoes bonus, you may add shoe products
      try
      {
        myProducts = filetoMap("products.txt");
      }
      catch(IOException e){
        e.printStackTrace();
        System.exit(1);
      }
      
    	
      // Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }
    
    private HashMap <String,Product> filetoMap(String fileName) throws IOException{

      HashMap <String,Product> productsMap = new HashMap<String,Product>();
      
      
        File myObj = new File(fileName);
      
        Scanner in = new Scanner(myObj);
        
    
        while(in.hasNextLine()){

          String category = in.nextLine().toUpperCase();
          String name = in.nextLine().trim();
          Double price = Double.valueOf(in.nextLine());
          String stock = in.nextLine().trim();
          String options = in.nextLine().trim();
          String productId = generateProductId();

          if (category.equals("BOOKS")){
            String[] st = stock.split(" ");
            String[] op = options.split(":");
            productsMap.put(productId, new Book(name, productId, price, Integer.valueOf(st[0]), Integer.valueOf(st[1]) ,op[0], op[1],Integer.valueOf(op[2])));
          }
          
          
          else if(category.equals("COMPUTERS")){
            productsMap.put(productId, new Product(name,productId, price,  Integer.valueOf(stock), Product.Category.COMPUTERS));
          }
          else if(category.equals("FURNITURE")){
            productsMap.put(productId, new Product(name,productId, price,  Integer.valueOf(stock), Product.Category.FURNITURE));
          }
          else if(category.equals("CLOTHING")){
            productsMap.put(productId, new Product(name,productId, price,  Integer.valueOf(stock), Product.Category.CLOTHING));
          }
          else if(category.equals("GENERAL")){
            productsMap.put(productId, new Product(name,productId, price,  Integer.valueOf(stock), Product.Category.GENERAL));
          }



        }

          
        in.close();
        return productsMap;
    }
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
    
    public void printAllProducts()
    {
    	
      Iterator<String> iterator = myProducts.keySet().iterator();

      while (iterator.hasNext()) {
      String key = iterator.next().toString();
      Product value = myProducts.get(key);
      

      value.print();
      }
    
      
    }
    public ArrayList<Book> booksByAuthor(String author)
	  {
      ArrayList<Book> books = new ArrayList<Book>();

      Iterator<String> iterator = myProducts.keySet().iterator();

      while (iterator.hasNext()) {
      String key = iterator.next().toString();
      Product value = myProducts.get(key);
      
      if((value.getCategory().compareTo(Product.Category.BOOKS) == 0)){
        Book book = (Book) value;
        if (book.getAuthor().equals(author)){
          books.add(book);
        }
        
          }

      }
      
		return books;
	  }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
      Iterator<String> iterator = myProducts.keySet().iterator();

      while (iterator.hasNext()) {
      String key = iterator.next().toString();
      Product value = myProducts.get(key);
      
      if((value.getCategory().compareTo(Product.Category.BOOKS) == 0)){
        value.print();
      }
      
      }
    }
    
    // Print all current orders
    public void printAllOrders()
    {
    	for ( ProductOrder o: orders)
      {
        o.print();
      }
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {
    	for ( ProductOrder s: shippedOrders)
      {
        s.print();
      }
        
      
    }
    
    // Print all customers
    public void printCustomers()
    {
    	for (Customer c : customers)
      c.print();
      
    }

    public String ifValid(String productId)
    {
      String options= "";
      Product currentProduct = null;
      
      Iterator<String> iterator = myProducts.keySet().iterator();

      while (iterator.hasNext()) {
      String key = iterator.next().toString();
      Product value = myProducts.get(key);
      if (value.getId().equals((productId))){
        currentProduct = value;
      } 
      }
      if (currentProduct.getCategory() == Product.Category.BOOKS){
        options = "Book Options: Hardcover, Paperback, E-book";
        return options;
      }
      else if(currentProduct.getCategory() == Product.Category.SHOES){
        options = "Shoe Options: Size: \"6\" \"7\" \"8\" \"9\" \"10\" in colours Black or Brown ";
      }
      
      else{
        options = "There is no options for this product.";
      }
      return options;
     
      
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public void printOrderHistory(String customerId) throws UnknownCustomerException
    {
      // Make sure customer exists - check using customerId
    	// If customer does not exist, set errMsg String and return false
    	// see video for an appropriate error message string
    	// ... code here
      Customer currentcustomer =null; 
      Boolean isFound = false;
      
      for (Customer c : customers)
      {
       if(c.getId().equals(customerId))
        {
        isFound = true;
        currentcustomer = c;
        break;
        }
      else 
        isFound = false;
      }
      
      if (!isFound)
      {

        throw new UnknownCustomerException("customer id does not exist");
        
      }
        
        
      else{
        // Print current orders of this customer 
    	System.out.println("Current Orders of Customer " + customerId);

      for (ProductOrder p : orders)
      {
        if ( currentcustomer == p.getCustomer())
        {
          p.print();
        }
      }
      // Print shipped orders of this customer 
    	System.out.println("\nShipped Orders of Customer " + customerId);
    	//enter code here
      for (ProductOrder s : shippedOrders)
      {
        if (currentcustomer.equals(s.getCustomer()))
        {
          s.print();
        }
      }
      
      }
    	
    }
    
    public String orderProduct(String productId, String customerId, String productOptions) throws UnknownCustomerException, UnknownProductException,InvalidProductOpException, OutOfStockException
    {
    	// First check to see if customer object with customerId exists in array list customers
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Customer object
      
      Customer currentcustomer = null; 
      Boolean isFound = false;
      
      for (Customer c : customers)
      {
       if(c.getId().equals(customerId))
        {
        isFound = true;
        currentcustomer = c;
        break;
        }
      else 
        isFound = false;
      }
      
      if (!isFound)
      {
        throw new UnknownCustomerException("customer " + customerId + " Not Found");
      }
        
    
        
    	// Check to see if product object with productId exists in array list of products
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Product object 
      
      Boolean isProduct = false;
      
      Product currentProduct = null;
      
      Iterator<String> iterator = myProducts.keySet().iterator();

      while (iterator.hasNext()) {
      String key = iterator.next().toString();
      Product value = myProducts.get(key);
      if (value.getId().equals((productId))){
        isProduct = true;
        currentProduct = value;
        break;
      }
      else
      {
        isProduct = false;
      }
      }
      if (!isProduct){
        
        throw new UnknownProductException("Product " + productId + " Not Found");
      }
      
      
    	
    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// See class Product and class Book for the method validOptions()
    	// If options are not valid, set errMsg string and return null;
      
      
       if (!currentProduct.validOptions(productOptions))
        {
          throw new InvalidProductOpException("Product " + currentProduct.getName() + " ProductId " + currentProduct.getId() + " Invalid Options: " + productOptions);
        }
        
    	
    	// Check if the product has stock available (i.e. not 0)
    	// See class Product and class Book for the method getStockCount()
    	// If no stock available, set errMsg string and return null
      if (currentProduct.getStockCount(productOptions) == 0 )
      {
      throw new OutOfStockException(currentProduct.getName() + " is out of stock");
      }
      // Create a ProductOrder, (make use of generateOrderNumber() method above)
    	// reduce stock count of product by 1 (see class Product and class Book)
    	// Add to orders list and return order number string
      String orderNumber = generateOrderNumber();	
      ProductOrder order = new ProductOrder(orderNumber, currentProduct, currentcustomer, productOptions);
      orders.add(order);
      currentProduct.newOrder();

      
      stats.putIfAbsent(currentProduct, currentProduct.getCount());
      
      currentProduct.reduceStockCount(productOptions);
    
      return orderNumber; 
    }
    
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public void createCustomer(String name, String address) throws InvalidCustomerNameException, InvalidCustomerAddressException
    {
    	// Check name parameter to make sure it is not null or ""
    	// If it is not a valid name, set errMsg (see video) and return false
    	// Repeat this check for address parameter
    	
      if (name.equals("") || name.equals(null))
      {
        throw new InvalidCustomerNameException("Invalid Customer Name");
        
      }
      if (address.equals("") || address.equals(null))
      {
        throw new InvalidCustomerAddressException("Invalid Customer Address");
        
      }
      
    	// Create a Customer object and add to array list

    	customers.add(new Customer(generateCustomerId(),name, address));
      
    }
    
    public ProductOrder shipOrder(String orderNumber) throws InvalidOrderNumberException
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
    	// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    	// return a reference to the order
        ProductOrder po = null;
        Boolean isFound = false;
        for (ProductOrder o : orders)
        {
          if(o.getOrderNumber().equals(orderNumber))
          {
             isFound = true;
             po = o;
             break;
        
          }
          else 
          {
            isFound = false;
          }
        }
      
      if (!isFound)
      {
        throw new InvalidOrderNumberException("Order " + orderNumber + " not Found");
      }
      else
      {
        int index = orders.indexOf(po);
        orders.remove(index);
        shippedOrders.add(po);
        po.print();
    	  return po;
      }
       
    }
    
    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber) throws InvalidOrderNumberException
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
    	  ProductOrder po = null;
        Boolean isFound = false;
        for (ProductOrder o : orders)
        {
          if(o.getOrderNumber().equals(orderNumber))
          {
            isFound = true;
            po = o;
          }

          else 
          {
            isFound = false;
          }
      
        }
    
      if (!isFound)
      {
      throw new InvalidOrderNumberException("Order " + orderNumber + " not Found"); 
      }
      
      else
      {
        int index = orders.indexOf(po);
        orders.remove(index);
      
      }
      
    }
    
    // Sort products by increasing price
    public void printByPrice()
    {
      ArrayList<Product> values = new ArrayList<Product>();
      
      Iterator<String> iterator = myProducts.keySet().iterator();
      while (iterator.hasNext()) {
        String key = iterator.next().toString();
        Product value = myProducts.get(key);
        values.add(value);
      }
        class ProductPriceComparator implements Comparator<Product>{
        public int compare(Product p1, Product p2){
          return Double.compare(p1.getPrice(), p2.getPrice());
        }

      }
      Collections.sort(values, new ProductPriceComparator());
      for(Product p: values){
        p.print();
      }
      
  	
    }
    
    
    // Sort products alphabetically by product name
    public void printByName()
    {
      ArrayList<Product> values = new ArrayList<Product>();
      
      Iterator<String> iterator = myProducts.keySet().iterator();
      while (iterator.hasNext()) {
        String key = iterator.next().toString();
        Product value = myProducts.get(key);
        values.add(value);
      }
      class ProductNameComparator implements Comparator<Product>{
        public int compare(Product p1, Product p2){
          return p1.getName().compareTo(p2.getName());
        }

      }
      Collections.sort(values, new ProductNameComparator());
      for(Product p: values){
        p.print();
      }
      
      
      
  	
    }
    
        
    // Sort products alphabetically by product name
    public void sortCustomersByName()
    {
      

  	  class CustomerNameComparator implements Comparator<Customer>{
        public int compare(Customer c1,Customer c2){
          return c1.getName().compareTo(c2.getName());
        }
      }

      Collections.sort(customers, new CustomerNameComparator());
      
      
    
    }

    public void addToCart(String productId, String customerId, String productOptions) throws UnknownCustomerException, UnknownProductException , InvalidProductOpException{
      
      Customer currentcustomer = null; 
      Boolean isFound = false;
      
      for (Customer c : customers)
      {
       if(c.getId().equals(customerId))
        {
        isFound = true;
        currentcustomer = c;
        break;
        }
        else {
        isFound = false;
        }
      }
      if (!isFound)
      {
        
        throw new UnknownCustomerException("customer id does not exist");
        
      }

      
      Product currentProduct =null; 
      Boolean isProduct = false;
     
      
      Iterator<String> iterator = myProducts.keySet().iterator();

      while (iterator.hasNext()) {
      String key = iterator.next().toString();
      Product value = myProducts.get(key);
      if (value.getId().equals((productId))){
        isProduct = true;
        currentProduct = value;
        break;
      }
      else
      {
        isProduct = false;
      }
      }
      if (!isProduct){
        
        throw new UnknownProductException("Product " + productId + " Not Found");
      }
      
      if (!currentProduct.validOptions(productOptions))
        {
          throw new InvalidProductOpException("Product " + currentProduct.getName() + " ProductId " + currentProduct.getId() + " Invalid Options: " + productOptions);
        }

      CartItem item =  new CartItem(currentProduct, productOptions);

      
      Cart current = currentcustomer.getCart() ;
      current.ShoppingCart.add(item);
      
    } 
  
    
    


    public void printCart(String customerId) throws UnknownCustomerException {
      Customer currentcustomer = null; 
      Boolean isFound = false;
      
      for (Customer c : customers)
      {
       if(c.getId().equals(customerId))
        {
        isFound = true;
        currentcustomer = c;
        break;
        }
        else {
          isFound = false;
          }
      }
        if (!isFound)
        {
          
          throw new UnknownCustomerException("customer id does not exist");
          
        }

      
      Cart currentCart = currentcustomer.getCart();
      
      System.out.println("Current Cart includes: ");
      for (CartItem item : currentCart.ShoppingCart){
        item.print();
      }
      
      
    }
    public void orderItems(String customerId) throws UnknownCustomerException{

      Customer currentcustomer = null; 
      Boolean isFound = false;
      
      for (Customer c : customers)
      {
       if(c.getId().equals(customerId))
        {
        isFound = true;
        currentcustomer = c;
        break;
        }
        else {
          isFound = false;
          }
        
      }
        if (!isFound)
        {
          
          throw new UnknownCustomerException("customer id does not exist");
          
        }
      
      Cart currentCart = currentcustomer.getCart();

      for (CartItem item : currentCart.ShoppingCart){
        
        String orderNumber = generateOrderNumber();	
        orders.add (new ProductOrder(orderNumber, item.getProduct(), currentcustomer, item.getProductOptions(item.getProduct())));
        item.getProduct().reduceStockCount(item.getProductOptions(item.getProduct()));
        item.getProduct().newOrder();
    
      }
      currentCart.ShoppingCart.removeAll(currentCart.ShoppingCart);
    }
    
    public void removeCartItem(String productId, String customerId) throws UnknownCustomerException, UnknownProductException{
      Customer currentcustomer = null; 
      Boolean isFound = false;
      
      for (Customer c : customers)
      {
       if(c.getId().equals(customerId))
        {
        isFound = true;
        currentcustomer = c;
        break;
        }
        else {
          isFound = false;
          }
        }
        if (!isFound)
        {
          
          throw new UnknownCustomerException("customer id does not exist");
          
        }
      
      Cart currentCart = currentcustomer.getCart();
      CartItem currentItem = null;
      for (CartItem item : currentCart.ShoppingCart){
        if (item.getProduct().getId().equals(productId)){
          currentItem = item;
        }
        else{
          throw new UnknownProductException("This product is not in the cart");
        }
      }
      
      currentCart.ShoppingCart.remove(currentItem);
    }
    public void stats(){



      ArrayList<Product> statList = new ArrayList<Product>();
      Iterator<String> iterator = myProducts.keySet().iterator();

      while (iterator.hasNext()) {
      String key = iterator.next().toString();
      Product value = myProducts.get(key);
      stats.put(value, value.getCount());
      
      }
      Iterator<Product> sIterator = stats.keySet().iterator();

      while(sIterator.hasNext()){
        Product k = sIterator.next();
        statList.add(k);
      }
     

      class orderCountComparator implements Comparator<Product>{
        public int compare(Product p1, Product p2){

          return Integer.compare(p1.getCount(), p2.getCount());
        }

      }
      
      Collections.sort(statList, new orderCountComparator().reversed());
      
      for(Product p: statList){
        System.out.printf("\nId: %-5s Name: %-20s Number of Orders: %-8d", p.getId(), p.getName(), p.getCount());
      }
    
    
    

  }
}

class UnknownCustomerException extends RuntimeException{
  public UnknownCustomerException(String message){
    super(message);
  }
}
class UnknownProductException extends RuntimeException{
  public UnknownProductException(String message){
    super(message);
  }
}

class InvalidProductOpException extends RuntimeException{
  public InvalidProductOpException(String message){
    super(message);
  }
}
class OutOfStockException extends RuntimeException{
  public OutOfStockException(String message){
    super(message);
  }
}
class InvalidCustomerNameException extends RuntimeException{
  public InvalidCustomerNameException(String message){
    super(message);
  }
}
class InvalidCustomerAddressException extends RuntimeException{
  public InvalidCustomerAddressException(String message){
    super(message);
  }
}

class InvalidOrderNumberException extends RuntimeException{
  public InvalidOrderNumberException(String message){
    super(message);
  }
}



