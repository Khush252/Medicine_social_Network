import java.util.*;


public class MedicineSocialNetwork {
    
    private Scanner input;
	private HashMap<String,Manufacturer > manufacturerDetails;
   	private HashMap<String,Customer > customerDetails;
    private HashMap<String,Product > productDetails;   
    private HashMap<String,ShopsAndWarehouse > shopsAndWarehouseDetails;
    private HashMap<String,DeliveryAgent> deliveryAgentDetails;
    private HashMap<String,Integer> productAndQuantity;
    private HashMap<Integer, CustomerOrder> currentOrders;

    private int numberOfManufacturers;
	private int numberOfCustomers;
	private int numberOfProducts;
	private int numberOfShopsAndWarehouses;
	private int numberOfDeliveryAgents;
	private int numberOfOrders;

//CONSTRUCTOR:
	public MedicineSocialNetwork(){
    	this.input = new Scanner(System.in);

    	this.manufacturerDetails= new HashMap<>();
    	this.customerDetails= new HashMap<>();
    	this.productDetails= new HashMap<>();
    	this.shopsAndWarehouseDetails= new HashMap<>();
    	this.deliveryAgentDetails= new HashMap<>();
    	this.currentOrders= new HashMap<>();

    	this.numberOfManufacturers=0;
    	this.numberOfCustomers=0;
    	this.numberOfProducts=0;
    	this.numberOfShopsAndWarehouses=0;
    	this.numberOfDeliveryAgents=0;
    	this.numberOfOrders=0;

	}

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int op=0;
        MedicineSocialNetwork medicineSocialNetwork = new MedicineSocialNetwork();
        while(op!=11){
        	System.out.print("\nSELECT OPTION FROM LIST BELOW:\n\n" + 
        		"1>Create.\n"+
        		"2>Delete.\n"+
        		"3>Print.\n"+
        		"4>Add a product to manufacturer. \n"+
        		"5>Add a certain number of copies of a product to a shop. \n"+
        		"6>Add an order of a product from a customer.\n"+
        		"7>Process an order. \n" +
        		"8>List all the purchases made by a customer.\n"+
        		"9>List inventory of a shop.\n "+
        		"10>Products made by a manufacturer.\n "+
        		"11>Exit.\n");
        	
        	op=input.nextInt();
        	switch(op){
        		case 1:
        			medicineSocialNetwork.create();
        			break;
        		case 2:
        			medicineSocialNetwork.delete();
        			break;
        		case 3:
        			medicineSocialNetwork.print();
        			break;
        		case 4:
        			medicineSocialNetwork.addProductToManufacturer();
        			break;
        		case 5:
        			medicineSocialNetwork.addProductToShop();
        			break;
        		case 6:
        			medicineSocialNetwork.addAnOrder();
        			break;
        		case 7:
        			medicineSocialNetwork.processAnOrder();
        			break;
        		case 8:
        			medicineSocialNetwork.listAllPurchasesMadeByCustomer();
        			break;
        		case 9:
        			medicineSocialNetwork.listInventoryOfShop();
        			break;
        		case 10:
        			medicineSocialNetwork.productsMadeByManufacturer();
        			break;
        		case 11:
        			System.out.println("Exiting!");
        			break;
        		default:
				   System.out.println("Enter a valid option number.");
			}
        }

    }


   
//CLASSES*****************************************************************************************

     public class Manufacturer{
	    int id;
	    String name;
	    HashSet <String> products;
	    public Manufacturer(int numberOfManufacturers,String name){
	    	this.id=10*(numberOfManufacturers)+1;
	    	this.name=name;
	    	this.products=new HashSet<>();
	    }
	   
	}   

	public class Customer{
	    int id, zipcode;
	    String name;
	    HashMap<String, Integer> purchases;
	   	public Customer(int numberOfCustomers,String name,int zipcode){
	   		this.id=10*(numberOfCustomers)+2;
	    	this.name=name;
	    	this.zipcode=zipcode;
	    	this.purchases = new HashMap<>();
	    }
	}  

	public class Product{
	    int id;
	    String name;
	    String manufacturerName;
	 	public Product(int numberOfProducts,String name,String manufacturerName){
	    	this.id=10*(numberOfProducts)+3;
	    	this.name=name;
	    	this.manufacturerName=manufacturerName;
	    }
	   
	}  

	public class ShopsAndWarehouse{
	    int id,zipcode;
	    String name;
	    HashMap<String, Integer>  productAndQuantity ;
	    public ShopsAndWarehouse(int numberOfShopsAndWarehouses,String name,int zipcode){
	    	this.id=10*(numberOfShopsAndWarehouses)+4;
	    	this.name=name;
	    	this.zipcode=zipcode;
	    	this.productAndQuantity= new HashMap<>();
	    }
	}  

	public class DeliveryAgent{
	    int id, zipcode, numberOfProductsDelivered;
	    String name;
	   	public DeliveryAgent(int numberOfDeliveryAgents,String name,int zipcode){
	    	this.id=10*(numberOfDeliveryAgents)+5;
	    	this.name=name;
	    	this.zipcode=zipcode;
	    }
	}

	public class CustomerOrder{
		int orderId;
		HashMap<String, Integer> productAndQuantity;
		String customer;
		public CustomerOrder(int numberOfOrders, String customer){
			this.orderId = numberOfOrders*10+6;
			this.customer = customer;
			this.productAndQuantity=new HashMap<>();

		}
	}
		
 //1************************************Create**********************************************************   
    private void create(){
		System.out.print("Select The Entity Which You Want To Create:(options range:[1,5])\n"+
		"1>Manufacturer\n"+
		"2>Customer\n"+
		"3>Product\n"+
		"4>Shops And Warehouse\n"+
		"5>DeliveryAgent\n");
		int type=0;
		type=this.input.nextInt();
		switch(type){
			case 1:
				this.createManufacturer();
			break;
			case 2:
				this.createCustomer();
			break;
			case 3:
				this.createProduct();
			break;
			case 4:
				this.createShopsAndWarehouse();
			break;
			case 5:
				this.createDeliveryAgent();
			break;
			default:
			System.out.println("\n\tCOULDN'T CREATE ENTITY. Invalid option. Should be an integer in range [1, 5]");
			return;
		}
	}
	
	

	private void createManufacturer(){
		System.out.println("Enter the name of the Manufacturer:\n");
		this.input.nextLine();
		String name=this.input.nextLine();
		if(this.manufacturerDetails.containsKey(name)){
			System.out.println("Manufacturer "+name +" already exists. Id="+this.manufacturerDetails.get(name).id);
		}
		else createManufacturer(name);
		
	}
	private void createManufacturer(String name){
		Manufacturer m= new Manufacturer(this.numberOfManufacturers,name);
		this.manufacturerDetails.put(name,m);
		this.numberOfManufacturers++;
		System.out.println("Created Manufacturer:"+m.name+" - "+Integer.toString(m.id));

	}

	private void createCustomer(){
		System.out.println("Enter the zipcode:\n");

		int zipcode=this.input.nextInt();
		System.out.println("Enter the name of the Customer:\n");
		this.input.nextLine();
		String name=this.input.nextLine();
		if(this.customerDetails.containsKey(name)){
			System.out.println("Customer "+name +" already exists. Id="+this.customerDetails.get(name).id);
		}
	    else createCustomer(name,zipcode);
		
	}
	private void createCustomer(String name,int zipcode){
		Customer c= new Customer(this.numberOfCustomers,name,zipcode);
		this.customerDetails.put(name,c);
		this.numberOfCustomers++;
		System.out.println("Created Customer:"+c.name+" "+Integer.toString(c.id));
	}
	private void createProduct(){
		System.out.println("Enter the name of the Product:\n");
		this.input.nextLine();
		String name=this.input.nextLine();
		Product p= new Product(this.numberOfProducts,name,null);
		this.productDetails.put(name,p);
		this.numberOfProducts++;
		System.out.println("Created Product:"+p.name+" - "+Integer.toString(p.id));
	}

	private void createShopsAndWarehouse(){
		System.out.println("Enter the zipcode:\n");
		int zipcode=this.input.nextInt();
		System.out.println("Enter the name of the ShopsAndWarehouse:\n");
		this.input.nextLine();
		String name=this.input.nextLine();
		if(this.shopsAndWarehouseDetails.containsKey(name) ){
			System.out.println("Shop "+name +" already exists. Id="+this.shopsAndWarehouseDetails.get(name).id);
		}
		else createShopsAndWarehouse(name,zipcode);
		
	}
	private void createShopsAndWarehouse(String name,int zipcode){

		ShopsAndWarehouse s= new ShopsAndWarehouse(this.numberOfShopsAndWarehouses,name,zipcode);
		this.shopsAndWarehouseDetails.put(name,s);
		this.numberOfShopsAndWarehouses++;
		System.out.println("Created Shop:"+s.name+" - "+Integer.toString(s.id));
	}

	private void createDeliveryAgent(){
		System.out.println("Enter the zipcode:\n");
		int zipcode=this.input.nextInt();
		System.out.println("Enter the name of the DeliveryAgent:\n");
		this.input.nextLine();
		String name=this.input.nextLine();
		if(this.deliveryAgentDetails.containsKey(name) ){
			System.out.println("Shop "+name +" already exists. Id="+this.deliveryAgentDetails.get(name).id);
		}
		else createDeliveryAgent(name,zipcode);
		
	}
	private void createDeliveryAgent(String name,int zipcode){
		DeliveryAgent a= new DeliveryAgent(this.numberOfDeliveryAgents,name,zipcode);
		this.deliveryAgentDetails.put(name,a);
		this.numberOfDeliveryAgents++;
		System.out.println("Created Delivery Agent:"+a.name+" - "+Integer.toString(a.id));
	}

 //2***************************************Delete*******************************************************   
	private void delete(){
		System.out.print("Select The Entity From Which You Want To Delete:(options range:[1,5])\n"+
		"1>Manufacturer\n"+
		"2>Customer\n"+
		"3>Product\n"+
		"4>Shops And Warehouse\n"+
		"5>DeliveryAgent\n");
		int type=0;
		type=this.input.nextInt();
		switch(type){
			case 1:
				this.DeleteManufacturer();
			break;
			case 2:
				this.DeleteCustomer();
			break;
			case 3:
				this.DeleteProduct();
			break;
			case 4:
				this.DeleteShopsAndWarehouse();
			break;
			case 5:
				this.DeleteDeliveryAgent();
			break;
			default:
			System.out.println("\n\tCOULDN'T DELETE ENTITY. Invalid option. Should be an integer in range [1, 5]");
			return;
		}
	}

	private void DeleteManufacturer(){
		System.out.print("Enter The name of the Manufacturer Which You Want To Delete from the given options:\n");
		printManufacturer();
		this.input.nextLine();
		String name=this.input.nextLine();
		if(!manufacturerDetails.containsKey(name)){
			System.out.print("Invaid Manufacturer name.\n");
		}
		else{
			for(String p: manufacturerDetails.get(name).products){
				this.productDetails.get(p).manufacturerName=null;
			}
			manufacturerDetails.remove(name);
		}

	}
	private void DeleteCustomer(){
		System.out.print("Enter The name of the Customer Which You Want To Delete from the given options:\n");
		printCustomer();
		this.input.nextLine();
		String name=this.input.nextLine();
		if(!customerDetails.containsKey(name)){
			System.out.print("Invaid Customer name.\n");
		}
		else{
			customerDetails.remove(name);
		}

	}
	private void DeleteProduct(){
		System.out.print("Enter The name of the Product Which You Want To Delete from the given options:\n");
		printProduct();
		this.input.nextLine();
		String name=this.input.nextLine();
		if(!productDetails.containsKey(name)){
			System.out.print("Invaid Product name.\n");
		}
		else{
			String m=productDetails.get(name).manufacturerName;
			if(m!=null){
				manufacturerDetails.get(m).products.remove(name);
			}
			productDetails.remove(name);
		}

	}
	private void DeleteShopsAndWarehouse(){
		System.out.print("Enter The name of the ShopsAndWarehouse Which You Want To Delete from the given options:\n");
		printShopsAndWarehouse();
		this.input.nextLine();
		String name=this.input.nextLine();
		if(!shopsAndWarehouseDetails.containsKey(name)){
			System.out.print("Invaid ShopsAndWarehouse name.\n");
		}
		else{
			shopsAndWarehouseDetails.remove(name);
		}

	}
	private void DeleteDeliveryAgent(){
		System.out.print("Enter The name of the DeliveryAgent Which You Want To Delete from the given options:\n");
		printDeliveryAgent();
		this.input.nextLine();
		String name=this.input.nextLine();
		if(!deliveryAgentDetails.containsKey(name)){
			System.out.print("Invaid DeliveryAgent name.\n");
		}
		else{
			deliveryAgentDetails.remove(name);
		}

	}
 //3***************************************Print*******************************************************   
	private void print(){
		System.out.print("Select The Entity Which You Want To Print:(options range:[1,5])\n"+
		"1>Manufacturer\n"+
		"2>Customer\n"+
		"3>Product\n"+
		"4>Shops And Warehouse\n"+
		"5>DeliveryAgent\n");
		int type=0;
		type=this.input.nextInt();
		switch(type){
			case 1:
				this.printManufacturer();
			break;
			case 2:
				this.printCustomer();
			break;
			case 3:
				this.printProduct();
			break;
			case 4:
				this.printShopsAndWarehouse();
			break;
			case 5:
				this.printDeliveryAgent();
			break;
			default:
			System.out.println("\n\tCOULDN'T PRINT ENTITY. Invalid option. Should be an integer in range [1, 5]");
			return;
		}
	}

	public void printManufacturer(){
			System.out.println("List of Manufacturers:\n");
			this.manufacturerDetails.forEach((key, value) -> System.out.println(key));
	}
	public void printCustomer(){
			System.out.println("List of Customers:\n");
			this.customerDetails.forEach((key, value) -> System.out.println(key));
	}
	public void printProduct(){
			System.out.println("List of Products:\n");
			this.productDetails.forEach((key, value) -> System.out.println(key));
	}
	public void printShopsAndWarehouse(){
			System.out.println("List of ShopsAndWarehouses:\n");
			this.shopsAndWarehouseDetails.forEach((key, value) -> System.out.println(key));
	}
	public void printDeliveryAgent(){
			System.out.println("List of DeliveryAgents:\n");
			this.deliveryAgentDetails.forEach((key, value) -> System.out.println(key));
	}


 //4***************************************Add Product To Manufacturer*******************************************************   
	
	private void addProductToManufacturer(){
		System.out.println("Enter the name of the Product:\n");
		// this.input.nextLine();
		String name=this.input.nextLine();
		System.out.println("Enter the name of the Manufacturer:\n");
		// this.input.nextLine();
		String manufacturerName=this.input.nextLine();
		if(!this.manufacturerDetails.containsKey(manufacturerName)){
			System.out.println("Manufacturer "+manufacturerName +" doesn't exist.\n");	
		}
		if(!this.productDetails.containsKey(name)){
			System.out.println("Product "+name +" doesn't exist.\n");	

		}
		else{
			if(this.productDetails.get(name).manufacturerName==null){
				this.productDetails.get(name).manufacturerName=manufacturerName;
				this.manufacturerDetails.get(manufacturerName).products.add(name);
				System.out.println("Added Product "+name+" to Manufacturer "+manufacturerName);
			}
			else{

				System.out.println("Product "+name+" already has Manufacturer "+this.productDetails.get(name).manufacturerName);
			}
		}
	}
//5*********************Add a certain number of copies of a product to a shop.******************************************************************************************
	private void addProductToShop(){

		System.out.println("Enter the name of the Shop:\n");
		// this.input.nextLine();
		String shopName=this.input.nextLine();
		if(!this.shopsAndWarehouseDetails.containsKey(shopName)){
			System.out.println("Invalid ShopAndWarehouse Name.\n");
		}

		else{
			System.out.println("Enter the name of the Product:\n");
			// this.input.nextLine();
			String productName=this.input.nextLine();
			if(!this.productDetails.containsKey(productName)){
				System.out.println("Invalid Product Name.\n");
			}
			else{
				System.out.println("Enter the quantity to be added:\n");
				int quantity=this.input.nextInt();
				if(this.shopsAndWarehouseDetails.get(shopName).productAndQuantity.containsKey(productName)){
					int t=this.shopsAndWarehouseDetails.get(shopName).productAndQuantity.get(productName);
					t+=quantity;
					this.shopsAndWarehouseDetails.get(shopName).productAndQuantity.put(productName,t);  
				
				}
				else{ 
					this.shopsAndWarehouseDetails.get(shopName).productAndQuantity.put(productName,quantity);  
				}
			}
		}	
	}

	private void printOrder(Integer orderId){
		if(this.currentOrders.containsKey(orderId)){
			System.out.println("ORDER: "+Integer.toString(orderId)+"\n");
			this.currentOrders.get(orderId).productAndQuantity.forEach((key, value) -> System.out.println(key+"-"+Integer.toString(value)));
		}else{
			System.out.println("This order doesnt exist currently\n");
		}
	}


	private void addAnOrder(){
		System.out.println("Enter Customer Name from the list:\n");
		this.printCustomer();

		String customerName=this.input.nextLine();
		if(!this.customerDetails.containsKey(customerName)){
			System.out.println("Invalid Customer Name.\n");
		}else{
			CustomerOrder order = new CustomerOrder(this.numberOfOrders, customerName);
			this.numberOfOrders++;
			int type = 1;
			while(type!=0){
				System.out.println("Enter 1 to add a product or 0 to finish order\n");
				type = this.input.nextInt();
				if(type==1){
					System.out.println("Enter product name: \n");
					this.input.nextLine();
					String productName = this.input.nextLine();
					System.out.println("Enter product quantity: \n");
					int quantity = this.input.nextInt();
					order.productAndQuantity.put(productName,quantity);
					this.customerDetails.get(customerName).purchases.put(productName,quantity);
				}
			}
			this.currentOrders.put(order.orderId, order);
			printOrder(order.orderId);
		}
	}

	private void processAnOrder(){
		System.out.println("Enter Order ID to be processed:\n");
		int orderId = this.input.nextInt();
		if(!this.currentOrders.containsKey(orderId)){
			System.out.println("Invalid Order ID.\n");
		}else{
			System.out.println("Executing order: \n");
			printOrder(orderId);
			CustomerOrder order = this.currentOrders.get(orderId);
			int zipcode = this.customerDetails.get(order.customer).zipcode;
			for(String shopName: this.shopsAndWarehouseDetails.keySet()){
				int q = 0;
				if(this.shopsAndWarehouseDetails.get(shopName).zipcode==zipcode){
					for(String productName: order.productAndQuantity.keySet()){
						if(this.shopsAndWarehouseDetails.get(shopName).productAndQuantity.containsKey(productName)){
								if(this.shopsAndWarehouseDetails.get(shopName).productAndQuantity.get(productName)>= order.productAndQuantity.get(productName)){
									q++;
								}else{
									break;
								}
							}
						}
					}
					if(q==order.productAndQuantity.keySet().size()){
						System.out.println("Order processed at shop:"+shopName);
						break;
					}
				}
			}
		}
	

	private void listAllPurchasesMadeByCustomer(){
		System.out.println("Enter Customer Name from the list:\n");
		this.printCustomer();
		this.input.nextLine();
		String customerName=this.input.nextLine();
		if(!this.customerDetails.containsKey(customerName)){
			System.out.println("Invalid Customer Name.\n");
		}else{
			System.out.println("Customer Orders:\n");
			this.customerDetails.get(customerName).purchases.forEach((key, value) -> System.out.println(key+"-"+Integer.toString(value)));
		}
	}

	private void listInventoryOfShop(){
		System.out.println("Enter Shop Name from the list:\n");
		this.printShopsAndWarehouse();
		this.input.nextLine();
		String shopName=this.input.nextLine();
		if(!this.shopsAndWarehouseDetails.containsKey(shopName)){
			System.out.println("Invalid Shop Name.\n");
		}else{
			System.out.println("Shop Inventory:\n");
			this.shopsAndWarehouseDetails.get(shopName).productAndQuantity.forEach((key, value) -> System.out.println(key+"-"+Integer.toString(value)));
		}
	}

	private void productsMadeByManufacturer(){
		System.out.println("Enter Manufacturer Name from the list:\n");
		this.printManufacturer();
		this.input.nextLine();
		String manufacturerName=this.input.nextLine();
		if(!this.manufacturerDetails.containsKey(manufacturerName)){
			System.out.println("Invalid Manufacturer Name.\n");
		}else{
			System.out.println("Manufacturer Products:\n");
			for(String p: manufacturerDetails.get(manufacturerName).products){
				System.out.println(p);
			}
		}
	}

	
}


