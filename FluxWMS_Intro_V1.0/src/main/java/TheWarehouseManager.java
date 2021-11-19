package main.java;
import static main.java.data.Repository.*;
import java.util.Locale;
import java.util.Scanner;

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
            "1. List items by warehouse", "2. Search an item and place an order", "3. Quit"
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
                System.out.println("This is the all available items in our 2 warehouses: ");
                this.listItemsByWarehouse();
                break;
            case 2:
                this.searchItemAndPlaceOrder();

                break;
            case 3:
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

        do {
            if (choice == 'y') {
                return true;

            } else if (choice == 'n') {
                return false;
            } else {
                System.out.println("Incorrect input. Please just enter Yes or No.");
                choice = reader.nextLine().toLowerCase(Locale.ROOT).charAt(0);
            }
        } while (choice != 'y' && choice != 'n');


        return false;
    }

    /**
     * End the application
     */
    public void quit() {
        System.out.printf("\nThank you for your visit, %s!\n", this.userName);
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


        System.out.println("\nItems in Warehouse 1\n");
        this.listItems(WAREHOUSE1);


        System.out.println("\nItems in Warehouse 2\n");
        this.listItems(WAREHOUSE2);
    }

    private void listItems(String[] warehouse) {
        for (String itemsInWarehouse : warehouse) {
            System.out.println("- " + itemsInWarehouse);
        }

    }

    private void searchItemAndPlaceOrder() {
        String itemName = askItemToOrder();
        do {
            if (getAvailableAmount(itemName) == 0) {
                boolean answer;

                answer = confirm("This item is not in stock, do you want to Enter another item?");
                if (answer) {
                    itemName = askItemToOrder();
                } else {
                   break; // or break if we want to continue with the program
                }
            }
        } while (getAvailableAmount(itemName) == 0);


        //amount available in both warehouses
        System.out.println("The amount available is: " + (getAvailableAmount(itemName)));

        //in which warehouse the item is found
        System.out.println("Location: " + findLocation(itemName));

        //the Maximum availability
        System.out.println("Maximum availability: " + getMaximumAvailability(itemName));

        //


        this.askAmountAndConfirmOrder(getAvailableAmount(itemName));

    }

    /**
     * Ask the user to specify an Item to Order
     *
     * @return String itemName
     */
    private String askItemToOrder() {
        System.out.println("What is the name of the item?");
        String itemName = reader.nextLine();
        return itemName;
    }

    /**
     * Calculate total availability of the given item
     *
     * @param itemName itemName
     * @return integer availableCount
     */
    private int getAvailableAmount(String itemName) {
        int warehouse1Total = find(itemName, WAREHOUSE1);
        int warehouse2Total = find(itemName, WAREHOUSE2);
        return warehouse1Total + warehouse2Total;
    }

    private String getMaximumAvailability(String itemName) {
        int warehouse1Total = find(itemName, WAREHOUSE1);
        int warehouse2Total = find(itemName, WAREHOUSE2);
        String answer="";

        if (warehouse1Total > warehouse2Total) {
            answer = warehouse1Total + " in Warehouse 1";
        } else if (warehouse2Total > warehouse1Total) {
            answer = warehouse2Total + " in Warehouse 2";
        } else if(warehouse2Total == 0 && warehouse1Total == 0) {
            answer = " ";
        } else if(warehouse2Total == warehouse1Total){
            answer = warehouse1Total + " ... same amount in both Warehouses";
        }
        return answer;
    }

    /**
     * Find the count of an item in a given warehouse
     *
     * @param itemName  the item
     * @param warehouse the warehouse
     * @return count
     */
    private int find(String itemName, String[] warehouse) {
        int result = 0;

        for (int i = 0; i < warehouse.length; i++) {
            if (warehouse[i].equals(itemName)) result++;
        }
        return result;

    }

    private String findLocation(String itemName) {

        String answer;

        if (find(itemName, WAREHOUSE1) > 0 && find(itemName, WAREHOUSE2) > 0) {
            answer = "Both Warehouses.";
        } else if (find(itemName, WAREHOUSE1) > 0) {
            answer = "In Warehouse 1.";
        } else if (find(itemName, WAREHOUSE2) > 0) {
            answer = "In Warehouse 2.";
        } else {
            answer = "Not in stock.";
        }
        return answer;
    }

    /**
     * Ask order amount and confirm order
     */
    private void askAmountAndConfirmOrder(int availableAmount) {
        if(availableAmount == 0) {
            return;
        }
            boolean choice = confirm("\nWould you like to order this item?");
            if (choice) {
                getOrderAmount(availableAmount);

            } else{
                    System.out.println("Order failed.");//STOP
                return;
                }
    }

    /**
     * Get amount of order
     *
     * @param availableAmount
     * @return
     */
    private int getOrderAmount(int availableAmount) {
        System.out.print("How many do you like? ");
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
                            break;
                        }

                    }
                }
            }
        }
            while (!(orderAmount > 0 && orderAmount < availableAmount)) ;

            return orderAmount;
        }
    }




