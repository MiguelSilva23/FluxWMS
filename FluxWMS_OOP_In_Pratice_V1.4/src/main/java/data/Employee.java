package data;

import java.util.List;

public class Employee extends User{

    // Fields
    protected String name;
    boolean isAuthenticated = false;
    private String password;
    private String role;
    List<Employee> headOf;

    public Employee(String userName){
        super(userName);
    };
    public Employee(String userName, String password,String role, List<Employee> headOf) {
        super(userName);
        this.password = password;
        this.headOf = headOf;
        this.role = role;
    }

    @Override
    public boolean authenticate(String password) {


        if(getPassword().equals(password)){return true;}else{return false;}
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
        System.out.println("Hello, " + this.name + "!\nIf you experience a problem with the system," +
                " please contact technical support.");
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
