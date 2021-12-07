package data;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static data.OrderRepository.getAllOrders;


public class AdminServiceImpl implements AdminService{

    private boolean isAuthenticated = false;


    // To read inputs from the console/CLI
    private Scanner reader = new Scanner(System.in);

    private String[] adminOptions = { "1. List orders by warehouse", "2. List orders by status",
            "3. List Orders whose total cost greater than provided value", "4. Quit" };


    @Override
    public void authenticateAdmin() {

            // SESSION_USER.authenticate();


        // TODO implement
        //prompt for passsword

        System.out.print("Please introduce your admin password:");
        String password = reader.nextLine();

        //prompt for password while admin is valid i.e the password matches
//        do{
//           TheWarehouseApp.SESSION_USER.greet();
//
//
//        }while(Admin is valid);

        //set the SESSION_USER's isAuthenticated property to true.


    }

    @Override
    public int getAdminsChoice() {
        // TODO implement
        int choice = 0;
        // List all options
        System.out.println("What would you like to do?");
        for (String option : this.adminOptions) {
            System.out.println(option);
        }

        System.out.print("Type the number of the operation: ");
        // Keep asking for admin's choice until a valid value is received
        do {
            String selectedOption = this.reader.nextLine();
            try {
                choice = Integer.parseInt(selectedOption);
            } catch (Exception e) {
                choice = 0;
            }
            // Guide the user to enter correct value
            if (choice < 1 || choice > adminOptions.length) {
                System.out.printf("Sorry! Enter an integer between 1 and %d. ", this.adminOptions.length);
            }
        } while (choice < 1 || choice > adminOptions.length);

        // return the valid choice
        return choice;
    }

    @Override
    public void performAction(int option) {


        switch (option) {
            case 1: // "1. List orders by warehouse"
                this.listOrdersByWarehouse();
                break;
            case 2: // "2. List order by status"
                this.listOrdersByStatus();
                break;
            case 3: // 3. Sort orders as per totalCost (descending)
                this.listOrdersWhoseTotalCostGreaterThan();
                break;
            case 4: // "4. Quit"
                this.quit();
                break;
            default:
                System.out.println("Sorry! Invalid option.");
        }
    }

    @Override
    public void listOrdersByWarehouse() {

        //get all orders using OrderRepository
        printsListOfOrdersToConsole(getAllOrders());


        //prompt user to enter the warehouse id
        System.out.print("Please enter the warehouse id: ");
        int warehouseId = Integer.parseInt(reader.nextLine());


        //create an empty list of order
        //Iterate over all the orders obtained from OrderRepository and add the orders belonging to the desired warehouse to the list

        List<Order> listOrderByWarehouse = new ArrayList<>();
        for(Order order : getAllOrders()){
            if(order.getWarehouse() == warehouseId){
                listOrderByWarehouse.add(order);
            }
        }


        // use the printsListOfOrdersToConsole method and pass the created list as argument.
        printsListOfOrdersToConsole(listOrderByWarehouse);


        //Add the action detail string to the SESSION_ACTIONS in TheWarehouseApp. Eg: 'Listed Orders of warehouse ' + {warehouseId}
       // TheWarehouseApp.SESSION_ACTIONS.add("Listed Orders of warehouse " + warehouseId);

    }

    @Override
    public void listOrdersByStatus() {

        // Keep asking for user's choice until a valid value is received
        int choice = 0;
        System.out.println("List Orders by Status : choose 1, 2 or 3");
        System.out.println("1. NEW");
        System.out.println("2. PROCESSING");
        System.out.println("3. DELIVERED");

        // prompt user to select 1, 2 or 3 unless valid choice is made.
        do {
            String selectedOption = this.reader.nextLine();
            try {
                choice = Integer.parseInt(selectedOption);
            } catch (Exception e) {
                choice = 0;
            }
            // Guide the user to enter correct value
            if (choice < 1 || choice > 3) {
                System.out.println("Sorry! Enter an integer between 1 and 3");
            }
        } while (choice < 1 || choice > 3);

        String status = "";

        switch(choice) {
            case 1:
                status = "NEW";
                break;
            case 2:
                status = "PROCESSING";
                break;
            case 3:
                status = "DELIVERED";
                break;
            default:
                break;

        }

        //initialize  new empty list of order
        List<Order> listOfOrderByStatus = new ArrayList<>();


        //iterate over all the orders from OrderRepository and add only the orders with desired status to the list
        for (Order order : getAllOrders()){
            if(order.getStatus().equalsIgnoreCase(status)){
                listOfOrderByStatus.add(order);
            }
        }


        // use the printsListOfOrdersToConsole method and pass the created list as argument.
        printsListOfOrdersToConsole(listOfOrderByStatus);


        //Add the action detail string to the SESSION_ACTIONS in TheWarehouseApp. Eg: 'Listed Orders with status ' + {status}
        // TheWarehouseApp.SESSION_ACTIONS.add("Listed Orders with status " + status);

    }

    @Override
    public void listOrdersWhoseTotalCostGreaterThan() {

        double marginalValue = 0;

        //prompt user to enter marginal value for total cost until a valid numerical value is entered.
        System.out.print("Please enter marginal value of total cost: ");
        marginalValue = Double.parseDouble(reader.nextLine());
        do {
            if (marginalValue < 0) {
                System.out.println("Invalid number: Only positive numbers please");
                System.out.print("Please enter marginal value of total cost: ");
                marginalValue = Double.parseDouble(reader.nextLine());
            }
        }
            while (marginalValue < 0) ;


            //initialize an empty list of orders
            List<Order> listOfOrderGreaterThan = new ArrayList<>();


            //iterate over all the orders from OrderRepository and add only the orders that meets the criteria to the list
            for (Order order : getAllOrders()) {
                if (order.getTotalCost() > marginalValue) {
                    listOfOrderGreaterThan.add(order);
                }
            }


            // use the printsListOfOrdersToConsole method and pass the created list as argument.
            printsListOfOrdersToConsole(listOfOrderGreaterThan);


            //Add the action detail string to the SESSION_ACTIONS in TheWarehouseApp. Eg: 'Listed Orders with total cost greater than ' + {marginalValue}
            // TheWarehouseApp.SESSION_ACTIONS.add("Listed Orders with total cost greater than " + marginalValue);

        }



    @Override
    public void printsListOfOrdersToConsole(List<Order> orders) {
        if(orders.size() == 0) {
            System.out.println("No order found");
        }
        for(Order order: orders) {
            System.out.println("===============================================================================================================================");
            System.out.println("status : "+ order.getStatus() + ", isPaymentDone : " + order.isPaymentDone() + ", warehouse: " + order.getWarehouse() + ", dateOfOrder : " + order.getDateOfOrder()
                    + ", totalCost : " + order.getTotalCost());
            System.out.println( "orderItems : " + order.getOrderItems());
            System.out.println("================================================================================================================================");
        }

    }

    @Override
    public void quit() {
        // TODO implement

        System.exit(0);

        //implement as similar to TheWarehouseManager.quit();


    }
}
