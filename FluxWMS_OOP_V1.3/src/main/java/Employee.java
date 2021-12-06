import java.util.ArrayList;
import java.util.List;

public class Employee extends User{

    // Fields
    protected String name = "Anonymous";
    boolean isAuthenticated = false;
    private String password;
    List<Employee> headOf = new ArrayList<Employee>();

    Employee(String userName, String password,List<Employee> headOf) {
        super(userName);
        this.password = password;
        this.headOf = headOf;
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
        int iterator = 0;
        if(SESSION_ACTIONS.size() == 0){
            System.out.println("In this session you have not done anything.");
            return;
        }else{
            for ( String text : SESSION_ACTIONS) {
                iterator++;

                System.out.println(iterator + ". " + text);
            }

        }
    }

    // Getters
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
