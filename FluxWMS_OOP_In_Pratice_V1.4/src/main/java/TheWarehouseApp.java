import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.*;

/**
 *
 * @author riteshp
 */
public class TheWarehouseApp {/**
 * Execute the <i>TheWarehouseApp</i>
 *
 * @param args
 */

public static List<String> SESSION_ACTIONS = new ArrayList<String>();
    public static boolean IS_EMPLOYEE = false;
    public static User SESSION_USER;


    public static void main(String[] args) {

        TheWarehouseManager theManager = new TheWarehouseManager();


        theManager.welcomeUser();

        // Get the user's choice of action and perform action
        do {
            if(!UserRepository.isUserAdmin(TheWarehouseApp.SESSION_USER.getName())) {
                int choice = theManager.getUsersChoice();
                theManager.performAction(choice);

            } else { //If user is admin

                // prompt for password and allow further actions if authenticated:
                if(!SESSION_USER.isAuthenticated()) {
                    SESSION_USER.authenticate();
                }

                AdminServiceImpl adminService = new AdminServiceImpl();

                if(!SESSION_USER.isAuthenticated()) {
                    adminService.authenticateAdmin();
                }

                int choice = adminService.getAdminsChoice();

                adminService.performAction(choice);

            }
            // confirm to do more
            if (theManager.confirm("Do you want to perform another action?") == false) {
                theManager.quit();
            }

        } while (true);
    }
}

