



import java.util.*;

import static data.PersonnelRepository.isUserValid;


/**
 * Provides necessary methods to deal through the Warehouse management actions
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
     * Welcome User
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
//                System.out.println("This is the all available items in our 2 warehouses: ");
                this.listItemsByWarehouse();
                break;
            case 2:
                this.searchItemAndPlaceOrder();

                break;
            case 3:
                               //Version 1.1\\
//                System.out.println("\nThis are the categories available: ");
//                int iteration = 0;
//
//                for (String categories : Repository.getCategories()){
//                    iteration = iteration + 1;
//                    System.out.printf("\n%d. %s (%d)",iteration,categories,Repository.getItemsByCategory(categories).size());
//                }
//
//                System.out.print("\n\nType the number of category to browse: ");
//                int categoryChoice = Integer.parseInt(reader.nextLine());
//
//                System.out.println();
//                Repository.itemsInCategory(categoryChoice);
//
//                boolean choice = confirm("\nDo you want to order?");
//                if(choice){this.searchItemAndPlaceOrder();}
//
//                break;

                this.browserByCategory();

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

    }

    /**
     * Print a welcome message with the given user's name
     */
    private void greetUser() {
        System.out.println("\nNice to meet you, " + this.userName);
    }

    private void listItemsByWarehouse() {
        /*       Version 1.0 Introduction
        System.out.println("\nItems in Warehouse 1\n");
        this.listItems(WAREHOUSE1);


        System.out.println("\nItems in Warehouse 2\n");
        this.listItems(WAREHOUSE2);
        */

//                         //Version 1.1\\
//        Set<Integer> warehousesIds = Repository.getWarehouses();
//
//        for (int eachWarehouse : warehousesIds) {
//            System.out.printf("\nItems in warehouse %d:%n\n",eachWarehouse);
//            this.listItems(Repository.getItemsByWarehouse(eachWarehouse));
//        }
//
//        System.out.println();
//
//        // how many items in each warehouse
//        for(int eachWarehouse : warehousesIds ){
//            System.out.printf("Total items in warehouse %d: %d%n", eachWarehouse, Repository.getItemsByWarehouse(eachWarehouse).size());
//        }
//
        // Version 1.2\\
//        Set<Integer> warehousesIds = StockRepository.getWarehouses();
//        for (int eachWarehouse : warehousesIds) {
//            System.out.printf("\nItems in warehouse %d:%n\n",eachWarehouse);
//            this.listItems(StockRepository.getItemsByWarehouse(eachWarehouse));
//        }
//
//        System.out.println();
//
//        // how many items in each warehouse
//        for(int eachWarehouse : warehousesIds ){
//            System.out.printf("Total items in warehouse %d: %d%n", eachWarehouse, StockRepository.getItemsByWarehouse(eachWarehouse).size());
//        }


        System.out.println(Warehouse.getStock());
        SESSION_ACTIONS.add("Listed " + Warehouse.occupancy() + " items.");
    }

    private void listItems(List<Item> items) {
        for (Item item : items) {
            System.out.println("- "+ item.toString());

        }

    }

    private void searchItemAndPlaceOrder() {
//                                          VERSION INTRODUCTION 1.0
//        String itemName = askItemToOrder();
//        do {
//            if (getAvailableAmount(itemName) == 0) {
//                boolean answer;
//
//                answer = confirm("This item is not in stock, do you want to Enter another item?");
//                if (answer) {
//                    itemName = askItemToOrder();
//                } else {
//                    break; // or break if we want to continue with the program
//                }
//            }
//        } while (getAvailableAmount(itemName) == 0);
//
//
//        //amount available in both warehouses
//        System.out.println("The amount available is: " + (getAvailableAmount(itemName)));
//
//        //in which warehouse the item is found
//        System.out.println("Location: " + findLocation(itemName));
//
//        //the Maximum availability
//        System.out.println("Maximum availability: " + getMaximumAvailability(itemName));
//
//
//        this.askAmountAndConfirmOrder(getAvailableAmount(itemName));

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


       // List<Item> availableItems = this.listAvailableItems(itemName);
        this.getOrderAmount(itemName);

    //String printFinal = "Searched " + getAppropriateIndefiniteArticle(itemName);
    SESSION_ACTIONS.add("Searched " + getAppropriateIndefiniteArticle(itemName));

    }

    private List<Item> listAvailableItems(String itemName) {

//        List<Item> availableItems = this.find(itemName);
//        if(availableItems.size() == 0){return availableItems;}
//        int count = getAvailableAmount(itemName);
//        System.out.printf("Amount available: %d%n", count);
//
//        if (count > 0) {
//            System.out.println("Location: ");
//            for (Item item : availableItems) {
//                long numOfDaysInStock = TimeUnit.DAYS.convert((new Date().getTime() - item.getDateOfStock().getTime()), TimeUnit.MILLISECONDS);
//                System.out.printf("- Warehouse %d (in stock for %d days)%n", item.getWarehouse(), numOfDaysInStock);
//            }
//
//
//        int maxWarehouse = 0;
//        int maxAvailability = 0;
//

//                          // Version 1.1
//        Set<Integer> warehouses = Repository.getWarehouses();
//        for(int wh : warehouses){
//            int whCount = Repository.getItemsByWarehouse(wh,availableItems).size();
//            if (whCount > maxAvailability){
//                maxWarehouse = wh;
//                maxAvailability = whCount;
//            }
//
//            Set<Integer> warehouses = StockRepository.getWarehouses();
//            for(int wh : warehouses){
//                int whCount = StockRepository.getItemsByWarehouse(wh,availableItems).size();
//                if (whCount > maxAvailability){
//                    maxWarehouse = wh;
//                    maxAvailability = whCount;
//                }
//        }System.out.printf("Maximum availability: %d is in warehouse %d%n", maxAvailability, maxWarehouse);
//        }else{
//            System.out.println("Location: Not in stock");
//        }
//
//        return availableItems;

        return Warehouse.getStock();
    }

    /**
     * Ask the user to specify an Item to Order
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
//                      Version 1.0
//        int warehouse1Total = find(itemName, WAREHOUSE1);
//        int warehouse2Total = find(itemName, WAREHOUSE2);
//        return warehouse1Total + warehouse2Total
        return find(itemName).size();
    }

//                       Version 1.0
//    private String getMaximumAvailability(String itemName) {
////
////    {
////        int warehouse1Total = find(itemName, WAREHOUSE1);
////        int warehouse2Total = find(itemName, WAREHOUSE2);
////        String answer="";
////
////        if (warehouse1Total > warehouse2Total) {
////            answer = warehouse1Total + " in Warehouse 1";
////        } else if (warehouse2Total > warehouse1Total) {
////            answer = warehouse2Total + " in Warehouse 2";
////        } else if(warehouse2Total == 0 && warehouse1Total == 0) {
////            answer = " ";
////        } else if(warehouse2Total == warehouse1Total){
////            answer = warehouse1Total + " ... same amount in both Warehouses";
////        }
////        return answer;
////    }
//        // loop for each warehouse and count .size();
//
//                        return null;
//    }

    /**
     * Find the count of an item in a given warehouse
     *
     * @param itemName  the item
     * @return count
     */
    private List<Item> find(String itemName) {

        List<Item> itemsMached = new ArrayList<>();

 //                    //Version 1.1\\
//        for (Item item : Repository.getAllItems()) {
//            if (item.toString().equalsIgnoreCase(itemName)) {
//                itemsMached.add(item);
//            }
//                    Version 1.0
//       int result = 0;
//        for (int i = 0; i < warehouse.length; i++) {
//            if (warehouse[i].equals(itemName)) result++;
//        }
//        return result;
        for (Item item : StockRepository.getAllItems()) {
            if (item.toString().equalsIgnoreCase(itemName)) {
                itemsMached.add(item);
            }

        }return itemsMached;
    }

//                              Version 1.0
//    private String findLocation(String itemName) {
//
//        String answer;
//
//        if (find(itemName, WAREHOUSE1) > 0 && find(itemName, WAREHOUSE2) > 0) {
//            answer = "Both Warehouses.";
//        } else if (find(itemName, WAREHOUSE1) > 0) {
//            answer = "In Warehouse 1.";
//        } else if (find(itemName, WAREHOUSE2) > 0) {
//            answer = "In Warehouse 2.";
//        } else {
//            answer = "Not in stock.";
//        }
//        return answer;
//
//                     return null;
//        }

//                              Version 1.0
//    /**
//     * Ask order amount and confirm order
//     */
//    private void askAmountAndConfirmOrder(int availableAmount, String item) {
//
//        boolean choice;
////                               Version 1.0
////        if(availableAmount == 0) {
////            return;
////        }
////            boolean choice = confirm("\nWould you like to order this item?");
////            if (choice) {
////                getOrderAmount(availableAmount);
////
////            } else{
////                    System.out.println("Order failed.");//STOP
////                return;
////                } //
//
//
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


    private int getTotalListedItems(){
        Set<Integer> warehousesIds = StockRepository.getWarehouses();
        int totalItems = 0;
        for(int eachWarehouse : warehousesIds ){
            totalItems += StockRepository.getItemsByWarehouse(eachWarehouse).size();
        }
        return totalItems;
    }

    private String getAppropriateIndefiniteArticle(String itemName){
        itemName = itemName.toLowerCase();
        if(itemName.startsWith("a") || itemName.startsWith("e") || itemName.startsWith("i") || itemName.startsWith("o") || itemName.startsWith("u") ){
            return "an " + itemName;
        }else{
            return "a " + itemName;
        }
    }

    public void browserByCategory(){
        System.out.println("\nThis are the categories available: ");
        int iteration = 0;

        for (String categories : StockRepository.getCategories()){
            iteration = iteration + 1;
            System.out.printf("\n%d. %s (%d)",iteration,categories,StockRepository.getItemsByCategory(categories).size());
        }
        System.out.println(iteration);
        System.out.print("\n\nType the number of category to browse: ");
        int categoryChoice = Integer.parseInt(reader.nextLine());


        System.out.println();

        Map<Integer, String> mappingItems = new HashMap<Integer, String>();
        int iteration2 = 0;

        for (String categories : StockRepository.getCategories()) {
            iteration2 += 1;
            mappingItems.put(iteration2, categories);
        }


        String showCategoryList = mappingItems.get(categoryChoice);

        for (Item itemsInCategories : StockRepository.getItemsByCategory(showCategoryList)){
            System.out.printf("- %s\n",itemsInCategories);
        }

        //String printFinal = "Browsed the category " + showCategoryList;
        SESSION_ACTIONS.add("Browsed the category " + showCategoryList);

    }

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




