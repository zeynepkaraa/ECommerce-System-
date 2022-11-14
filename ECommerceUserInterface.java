// Name: Zeynep Kara
// Student Id: 501102663
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			try{
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";
				
				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();
				
				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();
				
				amazon.createCustomer(name, address);
				
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
					String orderNumber = "";
        
					System.out.print("Order Number: ");
					// Get order number from scanner
					orderNumber = scanner.nextLine();
					
					// Ship order to customer (see ECommerceSystem for the correct method to use
					
					amazon.shipOrder(orderNumber);
					
					
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				customerId = scanner.nextLine();
				// Print all current orders and all shipped orders for this customer
				amazon.printOrderHistory(customerId);
				
				
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";

				System.out.print("Product Id: ");
			  // Get product Id from scanner
				productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				customerId = scanner.nextLine();
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				
				String productOptions = "";
				
				String order = amazon.orderProduct(productId, customerId, productOptions);
			
				 // Print Order Number string returned from method in ECommerceSystem
				System.out.println ("Order #" + order);
				
				 
				
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				customerId = scanner.nextLine();
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book forma and store in options string
				options = scanner.nextLine();
				
				// Order product. Check for error mesage set in ECommerceSystem
				String order = amazon.orderProduct(productId, customerId, options);
				
				
				 
				 // Print Order Number string returned from method in ECommerceSystem
				 // Print order number string if order number is not null
				System.out.println ("Order #" + order);
				
				
			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
			{
				String productId = "";
				String customerId = "";
				String options = "";
				
				System.out.print("Product Id: ");
				// get product id
				productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				customerId = scanner.nextLine();
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size and store in options	
				options = scanner.nextLine();
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color and append to options
				options += scanner.nextLine();
				
				//order shoes
				
				String order = amazon.orderProduct(productId, customerId, options);
				
					
				 // Print Order Number string returned from method in ECommerceSystem
				 // Print order number string if order number is not null
				System.out.println ("Order #" + order);
				
				
			}
			
			
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				orderNumber = scanner.nextLine();
				// cancel order. Check for error
				amazon.cancelOrder(orderNumber);
				
			}
			else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
			{
				amazon.printByPrice();
			}
			else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.printByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			else if(action.equalsIgnoreCase("ADDTOCART")) // Adds a product to the customer’s cart
			{
				String productId = "";
				String customerId = "";
				String productOptions = "";
				

				System.out.print("Product Id: ");
				// get product id
				productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				customerId = scanner.nextLine();
				
				System.out.println(amazon.ifValid(productId));
				
				System.out.print("\nProduct Options: ");
				productOptions = scanner.nextLine();
				
				amazon.addToCart(productId, customerId, productOptions);

			}
			else if(action.equalsIgnoreCase("REMCARTITEM")) //Removes a product from the customer’s cart
			{
				String productId = "";
				String customerId = "";
				System.out.print("Product Id: ");
				// get product id
				productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				customerId = scanner.nextLine();
				amazon.removeCartItem(productId, customerId);
			}
			else if(action.equalsIgnoreCase("PRINTCART")) //Prints all the products in the cart
			{
				String customerId = "";
				System.out.print("\nCustomer Id: ");
				// get customer id
				customerId = scanner.nextLine();
				amazon.printCart(customerId);

			}
			else if(action.equalsIgnoreCase("ORDERITEMS")) //order everything in the cart
			{
				String customerId = "";
				System.out.print("\nCustomer Id: ");
				// get customer id
				customerId = scanner.nextLine();
				amazon.orderItems(customerId);
			}
			else if(action.equalsIgnoreCase("STATS")){
				amazon.stats();
			}
			}
			catch(UnknownCustomerException e){
				System.out.println(e.getMessage());
			}
			catch(UnknownProductException u){
				System.out.println(u.getMessage());
			}
			catch(InvalidCustomerNameException i){
				System.out.println(i.getMessage());
			}
			catch(InvalidCustomerAddressException c){
				System.out.println(c.getMessage());
			}
			catch(InvalidProductOpException p){
				System.out.println(p.getMessage());
			}
			catch(InvalidOrderNumberException o){
				System.out.println(o.getMessage());
			}
			catch(OutOfStockException s){
				System.out.println(s.getMessage());
			}
			System.out.print("\n>");
			
		}


		}
		
		
		
	
}
