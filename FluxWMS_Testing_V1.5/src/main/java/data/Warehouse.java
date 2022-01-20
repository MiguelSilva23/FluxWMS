package data;

import java.util.*;

import static data.WarehouseRepository.getAllItems;

public class Warehouse {

    // Fields
    private int id;
    List<Item> stock;

    // Constructor
    public Warehouse(int warehouseId) {
        this.id = warehouseId;
        this.stock = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public int occupancy() {
        return getStock().size();
    }

    public void addItem(Item item) {
        getStock().add(item);
    }

    public boolean search(String searchTerm) {

        for (Item item : getStock()) {
            if (item.toString().equalsIgnoreCase(searchTerm)) {
                return true;
            }
        }
        return false;
    }

    public List<Item> getItemsInStock(String itemName) {

        List<Item> allItemsInThisWarehouse = new ArrayList<Item>();

        for (Item item : getStock()) {
            if (item.toString().equalsIgnoreCase(itemName)){
                allItemsInThisWarehouse.add(item);
            }
        }
        return allItemsInThisWarehouse;
    }

    public List<Item> getStock() {
        return stock;
    }

    public Set<String> getCategories() {

        Set<String> categoryList = new TreeSet<>();

        for (Item item : getStock()) {

            categoryList.add(item.getCategory());
        }

        return categoryList;
    }


}
