import java.util.List;

public class User {
    // Fields
    protected String name = "Anonymous";
    boolean isAuthenticated = false;

    // Constructor
    User(String userName){
        this.name = userName;
    }


    // Methods
    public void user(String userName){}

    public boolean authenticate(String password){return false;}

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
        System.out.println("Hello, " + this.name + "!\nWelcome to our Warehouse Database.\n" +
                "If you don't find what you are looking for,\nplease ask one of our staff members" +
                "assist you.");
    }

    public void bye(List<String> SESSION_ACTIONS){
        System.out.println("Thank you");
    }


}
