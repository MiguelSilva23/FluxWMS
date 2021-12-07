package data;

import java.util.*;

import static data.WarehouseRepository.getAllItems;

public class Warehouse {

    // Fields
    private int id;
    private static List<Item> stock;

    // Constructor
    public Warehouse(int warehouseId){

        this.id = warehouseId;
       this.stock = new ArrayList<>();
       loadingStock();

    }


    public int getId() {
        return id;
    }

    public static int occupancy() {return stock.size();}

    public void loadingStock(){

        for ( Item item : getAllItems()){
            if(item.getWarehouse() == this.id){
                stock.add(item);
            }
        }
    }

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
