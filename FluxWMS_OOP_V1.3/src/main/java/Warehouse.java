

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    // Fields
    private int id;
   private List<Item> stock;

    // Constructor
    public Warehouse(int warehouseId){

        this.id = warehouseId;
       this.stock = new ArrayList<>();

    }

//    public Warehouse(Warehouse another){
//        this.stock = another.stock;
//    }

    //Methods

//    public List<Item> clearList(){
//        if(!stock.isEmpty()){return List<Item> stock2 = new ArrayList<>();}
//        return stock;
//    }

    public int getId() {
        return id;
    }

    public int occupancy() {return stock.size();}

    public void addItem(Item item) {
        stock.add(item);
    }

    public List<Item> search(String searchTerm) {

        List<Item> tempList = new ArrayList<>();

        for (Item item: stock) {
            if (item.getState().equalsIgnoreCase(searchTerm)){ tempList.add(item);}
        }

        return tempList;}

    public  List<Item> getStock(){
        return stock;
    }


}
