package data;

import java.util.List;
import java.util.Scanner;

public class Employee extends User{

    // Fields
    protected String name;
    boolean isAuthenticated = false;
    private String password;
    private String role;
    List<Employee> headOf;


    public Employee(String userName, String password,String role, List<Employee> headOf) {
        super(userName);
        this.password = password;
        this.headOf = headOf;
        this.role = role;
    }

    @Override
    public boolean authenticate() {
        System.out.print("Please introduce your Password: ");
        Scanner scanner = new Scanner(System.in);
        String password2 = scanner.nextLine();

        if(this.getPassword().equals(password2)){
            setAuthenticated();
            return true;
        }else{
            System.out.println("Wrong password !!");
            return authenticate();
        }



    }

    public void order(String item, int amount){
        System.out.println("Name of the item: " + item +".\nAmount: " + amount);
    }

    @Override
    public boolean isNamed(String name1) {
        return super.isNamed(name1);
    }

    @Override
    public void greet() {
        System.out.println("Hello, " + getName() + "!\nIf you experience a problem with the system," +
                " please contact technical support.");

        System.out.println(getClass().getSimpleName());
    }

    @Override
    public void bye(List<String> SESSION_ACTIONS) {
        System.out.printf("\nThank you for your visit, %s!\n", this.name);

    }

    // Getters
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getRole(){
        return this.role;
    }


}
