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

    public void setName(String name){
        this.name = name;
    }

    public boolean isNamed(String name1) {

        if(getName().equals(name1)){
            return true;
        }else{
            return false;
        }
    }

    public void changeUser(){
        Scanner reader = new Scanner(System.in);

        System.out.print("This option is only for authenticated personnel. \nPlease enter a valid username: ");


        this.name = reader.nextLine();

    }



    public void greet(){
        System.out.println("\nHello, " + this.name + "!\nWelcome to our data. Warehouse Database.\n" +
                "If you don't find what you are looking for,\nplease ask one of our staff members" +
                " assist you.");

    }

    public abstract void bye(List<String> SESSION_ACTIONS);



}
