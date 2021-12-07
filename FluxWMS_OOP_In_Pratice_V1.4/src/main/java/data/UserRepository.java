package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Data Repository
 *
 * @author pujanov
 *
 */
public class UserRepository {
    private static List<Employee> EMPLOYEE_LIST = new ArrayList<>();
    private static List<Admin> ADMIN_LIST = new ArrayList<>();

    /**
     * Load employee, records from the personnel.json file
     */
    static {
        // System.out.println("Loading items");
        BufferedReader reader = null;
        try {
            EMPLOYEE_LIST.clear();

            reader = new BufferedReader(new FileReader("src/main/resources/personnel.json"));
            Object data = JSONValue.parse(reader);
            if (data instanceof JSONArray) {
                JSONArray dataArray = (JSONArray) data;
                for (Object obj : dataArray) {
                    if (obj instanceof JSONObject) {
                        JSONObject jsonData = (JSONObject) obj;
                        String userName = jsonData.get("user_name").toString();
                        String password = jsonData.get("password").toString();
                        String role = jsonData.get("role").toString();
                        Employee employee = new Employee(userName, password, role, null);
                        EMPLOYEE_LIST.add(employee);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    static{
        for (Employee employee : EMPLOYEE_LIST) {
            if(employee.getRole().equalsIgnoreCase("Admin")){
                Admin adminUserList = new Admin(employee.getName(), employee.getPassword(), employee.getRole(), null);
                ADMIN_LIST.add((adminUserList));
            }
        }
    }

    /**
     * Get All persons
     *
     * @return
     */
    public static List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }

    public static List<Admin> getAdminList(){return ADMIN_LIST;}

    public static boolean isUserValid(String userName, String password) {


        for (Employee employee : getAllEmployees()) {
            if (userName.equals(employee.getName()) && password.equals(employee.getPassword())){
                return true;
                }
            }return false;
        }

    public static boolean isUserEmployee(String name) {

        for (Employee employee : getAllEmployees()) {
            if (name.equals(employee.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUserAdmin(String name){

        for (Employee admin: getAdminList()) {
            if (admin.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public static Employee getUserEmployeeObject(String name) {
        for (Employee employee : getAllEmployees()) {
            if (name.equals(employee.getName())) {
                return employee;
            }
        }
        return null;
    }

    public static Admin getUserAdminObject(String name){
        for (Admin admin: getAdminList()) {
            if (admin.getName().equalsIgnoreCase(name)){
                return admin;
            }
        }
        return null;
    }

    }



