
import data.*;

import java.util.*;

import static data.UserRepository.*;
import static data.WarehouseRepository.*;


/**
 * Provides necessary methods to deal through the data.Warehouse management actions
 *
 * @author riteshp
 */
public class TheWarehouseManager {
    // =====================================================================================
    // Member Variables
    // =====================================================================================

    // To read inputs from the console/CLI
    private final Scanner reader = new Scanner(System.in);
    private final String[] userOptions = {
            "1. List all items by each warehouse", "2. Search an item and place an order","3. Browse by category", "4. Quit"
    };
    // To refer the user provided name.
    private String userName;


    // =====================================================================================
    // Public Member Methods
    // =====================================================================================

    /**
     * Welcome
     */
    public void welcomeUser() {
        this.seekUserName();
        this.greetUser();

    }

    /**
     * Ask for user's choice of action
     */
    public int getUsersChoice() {
        System.out.println("\nWhat would you like to do?\nPlease choose one option: \n");
        for (String option : this.userOptions) {
            System.out.println(option);
        }
        int userChoice = Integer.parseInt(reader.nextLine());

        do {
            if (userChoice > 0 && userChoice <= this.userOptions.length) {
                return userChoice;
            } else {
                System.out.println("Incorrect input. Please just enter the options available: ");
                for (String option : this.userOptions) {
                    System.out.println(option);
                }
                userChoice = Integer.parseInt(reader.nextLine());
            }
        } while (!(userChoice > 0 && userChoice <= this.userOptions.length));
        return userChoice;
    }

    /**
     * Initiate an action based on given option
     */
    public void performAction(int option) {
        switch (option) {
            case 1:
                this.listItemsByWarehouse();
                break;
            case 2:
                this.searchItemAndPlaceOrder();

                break;
            case 3:

                Warehouse.getCategories();

                boolean choice = confirm("\nDo you want to order?");
                if(choice){this.searchItemAndPlaceOrder();}

                break;
            case 4:
                    System.out.println("Quit by user.");
                    quit();
                    break;


            default:
                System.out.println("work in progress");


        }
    }

    /**
     * Confirm an action
     *
     * @return action
     */
    public boolean confirm(String message) {
        System.out.printf("%s (y/n)%n", message);
        char choice = this.reader.nextLine().toLowerCase().charAt(0);

        while (choice != 'y' && choice != 'n') {
            System.out.println("Incorrect input. Please just enter Yes or No.");
            choice = reader.nextLine().toLowerCase().charAt(0);
        }
        if (choice == 'y') {
            return true;
        } else{
            return false;
        }
    }

    /**
     * End the application
     */
    public void quit() {
        System.out.printf("\nThank you for your visit, %s!\n", this.userName);

        listSessionActions();

        System.exit(0);
    }

    // =====================================================================================
    // Private Methods
    // =====================================================================================

    /**
     * Get user's name via CLI
     */
    private void seekUserName() {
        System.out.println("Hi there, please introduce yourself: ");
        this.userName = reader.nextLine();

        if(isUserAdmin(userName)){
            TheWarehouseApp.SESSION_USER = getUserAdminObject(userName);
        }else if(isUserEmployee(userName)){
            TheWarehouseApp.SESSION_USER = (getUserEmployeeObject(userName));
        }else{
            TheWarehouseApp.SESSION_USER = new Guest(userName);
        }

    }

    /**
     * Print a welcome message with the given user's name
     */
    private void greetUser() {

        TheWarehouseApp.SESSION_USER.greet();
    }

    private void listItemsByWarehouse() {

        int warehouseID;

        System.out.print("Please enter the warehouse ID: ");
        warehouseID = Integer.parseInt(reader.nextLine());

        System.out.println(getItemsByWarehouse(warehouseID,getAllItems()));
        SESSION_ACTIONS.add("Listed " + Warehouse.occupancy() + " items.");
    }

    private void listItems(List<Item> items) {
        for (Item item : items) {
            System.out.println("- "+ item.toString());

        }

    }

    private void searchItemAndPlaceOrder() {
        // TODO isUserValid


        String itemName = askItemToOrder();

        do{
            if (listAvailableItems(itemName).size() == 0){
            System.out.println("Your item is not available in our stock, or you enter an invalid item");
            boolean confirm = confirm("Do you want to enter another item? ");
            if(confirm){
                itemName = askItemToOrder();
            }else{
                return;
            }
        }
        }while(listAvailableItems(itemName).size() == 0);



        this.getOrderAmount(itemName);


    SESSION_ACTIONS.add("Searched " + getAppropriateIndefiniteArticle(itemName));

    }

    private List<Item> listAvailableItems(String itemName) {

        return Warehouse.getStock();
    }

    /**
     * Ask the user to specify an data.Item to Order
     *
     * @return String itemName
     */
    private String askItemToOrder() {
        System.out.println("What is the name of the item?");
        return this.reader.nextLine().toLowerCase();
    }

    /**
     * Calculate total availability of the given item
     *
     * @param itemName itemName
     * @return integer availableCount
     */
    private int getAvailableAmount(String itemName) {

        return Warehouse.search(itemName).size();
    }


    /**
     * Find the count of an item in a given warehouse
     *
     * @param itemName  the item
     * @return count
     */

     //Old version\\
//    private List<data.Item> find(String itemName) {
//
//        List<data.Item> itemsMached = new ArrayList<>();
//
//        for (data.Item item : StockRepository.getAllItems()) {
//            if (item.toString().equalsIgnoreCase(itemName)) {
//                itemsMached.add(item);
//            }
//
//        }return itemsMached;
//    }


    /**
     * Get amount of order
     *
     * @param
     * @return
     */
    private int getOrderAmount(String itemName) {

        int availableAmount = getAvailableAmount(itemName);
        System.out.print("\nHow many do you like? ");
        int orderAmount = Integer.parseInt(reader.nextLine());

        while (orderAmount <= 0) {
            System.out.println("\nYou order is not valid.\n" + "\nPlease edit your order request: ");
            orderAmount = Integer.parseInt(reader.nextLine());
        }
        // Correct the order == maximum
        do {
            if (orderAmount > 0 && orderAmount < availableAmount) {
                System.out.println("Order has been placed. Thank you!");
            } else if (orderAmount == availableAmount) {
                System.out.println("The maximum items available is ordered. Thank you!");
                break;
            } else if (orderAmount > availableAmount) {
                System.out.println("You ordered more than available items...");
                boolean answer = confirm("Do you want to order the maximum available items? ");
                if (answer) {
                    System.out.println("The maximum items available is ordered. Thank you!");
                    return orderAmount;
                } else {
                    boolean answer2 = confirm("Do you want to edit your order?");
                    if (answer2) {
                        System.out.print("How many do you like? ");
                        orderAmount = Integer.parseInt(reader.nextLine());
                        while (orderAmount <= 0) {
                            System.out.println("\nYou order is not valid.\n" + "\nPlease edit your order request: ");
                            orderAmount = Integer.parseInt(reader.nextLine());
                        }
                        if (orderAmount > 0 && orderAmount < availableAmount) {
                            System.out.println("Order has been placed. Thank you!");
                        } else {
                            System.out.print("You still order more then available items in stock...Please edit your order request: ");
                            orderAmount = Integer.parseInt(reader.nextLine());
                            if (orderAmount > 0 && orderAmount < availableAmount) {
                                System.out.println("Order has been placed. Thank you!");
                            }
                        }
                    }
                }
            } else if (orderAmount <= 0) {
                System.out.println("\nYou order is not valid.\n" + "\nPlease edit your order request: ");
                orderAmount = Integer.parseInt(reader.nextLine());
            }
        }
            while (!(orderAmount > 0 && orderAmount < availableAmount)) ;

            return orderAmount;
        }


    private  List<String> SESSION_ACTIONS = new ArrayList<>();

   //Old version\\
//    private int getTotalListedItems(){
//        Set<Integer> warehousesIds = StockRepository.getWarehouses();
//        int totalItems = 0;
//        for(int eachWarehouse : warehousesIds ){
//            totalItems += StockRepository.getItemsByWarehouse(eachWarehouse).size();
//        }
//        return totalItems;
//    }

    private String getAppropriateIndefiniteArticle(String itemName){
        itemName = itemName.toLowerCase();
        if(itemName.startsWith("a") || itemName.startsWith("e") || itemName.startsWith("i") || itemName.startsWith("o") || itemName.startsWith("u") ){
            return "an " + itemName;
        }else{
            return "a " + itemName;
        }
    }

    //old version\\
//    public void browserByCategory(){
//        System.out.println("\nThis are the categories available: ");
//        int iteration = 0;
//
//        for (String categories : StockRepository.getCategories()){
//            iteration = iteration + 1;
//            System.out.printf("\n%d. %s (%d)",iteration,categories,StockRepository.getItemsByCategory(categories).size());
//        }
//        System.out.println(iteration);
//        System.out.print("\n\nType the number of category to browse: ");
//        int categoryChoice = Integer.parseInt(reader.nextLine());
//
//
//        System.out.println();
//
//        Map<Integer, String> mappingItems = new HashMap<Integer, String>();
//        int iteration2 = 0;
//
//        for (String categories : StockRepository.getCategories()) {
//            iteration2 += 1;
//            mappingItems.put(iteration2, categories);
//        }
//
//
//        String showCategoryList = mappingItems.get(categoryChoice);
//
//        for (data.Item itemsInCategories : StockRepository.getItemsByCategory(showCategoryList)){
//            System.out.printf("- %s\n",itemsInCategories);
//        }
//
//        //String printFinal = "Browsed the category " + showCategoryList;
//        SESSION_ACTIONS.add("Browsed the category " + showCategoryList);
//
//    }

    public void listSessionActions(){
        int iterator = 0;
        if(SESSION_ACTIONS.size() == 0){
            System.out.println("In this session there's no record.");
            return;
        }else{
            for ( String text : SESSION_ACTIONS) {
                iterator++;

                System.out.println(iterator + ". " + text);
            }

        }

    }







    }




