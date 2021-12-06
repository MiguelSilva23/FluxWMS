package data;



import java.util.List;

public abstract class User {
    // Fields
    protected String name;
    boolean isAuthenticated = false;


    // Constructor
    public User(String name) {
        this.name = name;
    }

    // Methods

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public abstract boolean authenticate(String password);

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
        System.out.println("Hello, " + this.name + "!\nWelcome to our data.Warehouse Database.\n" +
                "If you don't find what you are looking for,\nplease ask one of our staff members" +
                "assist you.");
    }

    public abstract void bye(List<String> SESSION_ACTIONS);


}
