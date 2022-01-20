package data;

import java.util.List;

public class Admin extends Employee {


    public Admin(String userName, String password,String role, List<Employee> headOf) {
        super(userName, password,role,headOf);
    }

    @Override
    public boolean authenticate() {

        if(!super.authenticate()){
            super.authenticate();
        }else{
            isAuthenticated = true;
        }
        return true;

    }

    @Override
    public void order(String item, int amount) {
        super.order(item, amount);
    }

    @Override
    public boolean isNamed(String name1) {
        return super.isNamed(name1);
    }

    @Override
    public void greet() {
        System.out.println("Hello, " +  getName() +"\n" +
                "Welcome to the Admin Panel.\n" +
                "With higher authority comes higher responsibility.");

        System.out.println(getClass().getSimpleName());
    }

    @Override
    public void bye(List<String> SESSION_ACTIONS) {
        super.bye(SESSION_ACTIONS);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
