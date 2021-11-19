package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Data Repository
 *
 * @author riteshp
 *
 */
public class PersonnelRepository {
    private static List<Person> PERSON_LIST = new ArrayList<Person>();

    /**
     * Load item records from the stock.json file
     */
    static {
        // System.out.println("Loading items");
        BufferedReader reader = null;
        try {
            PERSON_LIST.clear();

            reader = new BufferedReader(new FileReader("src/main/resources/personnel.json"));
            Object data = JSONValue.parse(reader);
            if (data instanceof JSONArray) {
                JSONArray dataArray = (JSONArray) data;
                for (Object obj : dataArray) {
                    if (obj instanceof JSONObject) {
                        JSONObject jsonData = (JSONObject) obj;
                        Person person = new Person();
                        person.setUserName(jsonData.get("user_name").toString());
                        person.setPassword(jsonData.get("password").toString());
                        //person.setHeadOf((Person) jsonData.getOrDefault("head_of", null));
                        PERSON_LIST.add(person);
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

    /**
     * Get All persons
     *
     * @return
     */
    public static List<Person> getAllPersons() {
        return PERSON_LIST;
    }


    public static void isUserValid(String userName2, String password2) {          //?????????????????

        Person test2 = new Person();
        test2.setUserName(userName2);
        test2.setPassword(password2);

        for (Person person : getAllPersons()) {
            if(test2.equals(person)){
                System.out.println("GOOD");
            }else{
                System.out.println("BAD");
            }
        }

//        List<Person> test1 = new ArrayList<Person>();
//
//        boolean flag = false;
//        System.out.println(test.get(0));
//        System.out.println(test2);
//

//
//        if(getAllPersons().contains(userName,password)){
//            System.out.println("GOOOD");
//        }else{
//            System.out.println("not good");
//        }
//
//        Map<String, String> mappingItems = new HashMap<String, String>();
//
//        List<Person> persons = new ArrayList<>();
//
//
//        for (Person user: getAllPersons())
//        {
//            if(getAllPersons().contains(userName)){
//                System.out.println("GOOOD");
//            }
//        }
//        //System.out.println(persons);
       // return valid;
    }
}
