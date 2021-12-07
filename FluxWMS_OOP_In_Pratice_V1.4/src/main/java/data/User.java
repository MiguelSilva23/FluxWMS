package data;



import java.util.List;
import java.util.Scanner;

public abstract class User {
    // Fields
    protected String name;
    boolean isAuthenticated = false;
    private String password;


    // Constructor
    public User(String name) {
        this.name = name;
    }

    // Methods

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(){  isAuthenticated = true; }

    public abstract boolean authenticate();

    public String getName() {
        return name;
    }

    public boolean isNamed(String name1) {

        if(getName().equals(name1)){
            return true;
        }else{
            return false;
        }
    }



    public void greet(){
        System.out.println("Hello, " + this.name + "!\nWelcome to our data. Warehouse Database.\n" +
                "If you don't find what you are looking for,\nplease ask one of our staff members" +
                " assist you.");

        System.out.println(getClass().getSimpleName());
    }

    public abstract void bye(List<String> SESSION_ACTIONS);



}
