package data;

import data.Item;

import java.util.*;

public class Warehouse {

    // Fields
    private int id;
   private static List<Item> stock;

    // Constructor
    public Warehouse(int warehouseId){

        this.id = warehouseId;
       this.stock = new ArrayList<>();

    }

//    public data.Warehouse(data.Warehouse another){
//        this.stock = another.stock;
//    }

    //Methods

//    public List<data.Item> clearList(){
//        if(!stock.isEmpty()){return List<data.Item> stock2 = new ArrayList<>();}
//        return stock;
//    }

    public int getId() {
        return id;
    }

    public static int occupancy() {return stock.size();}

    public void addItem(Item item) {
        stock.add(item);
    }

    public static List<Item> search(String searchTerm) {

        List<Item> tempList = new ArrayList<>();

        for (Item item: stock) {
            if (item.getState().equalsIgnoreCase(searchTerm)){ tempList.add(item);}
        }

        return tempList;}

    public static List<Item> getStock(){
        return stock;
    }

    public static Set<String> getCategories() {

        Set<String> categoryList = new TreeSet<>();

        for (Item item : stock) {

            categoryList.add(item.getCategory());
        }

        return categoryList;
    }


}
