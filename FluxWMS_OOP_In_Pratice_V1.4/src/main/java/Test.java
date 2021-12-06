import data.Employee;
import data.Item;
import data.Warehouse;
import data.WarehouseRepository;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Warehouse one = new Warehouse(1);
        Warehouse two = new Warehouse(2);
        one.addItem(new Item("barnd new", "keyboard", 0, new Date()));
        System.out.println("should result 1 : ");
        System.out.println(one.occupancy());
        System.out.println(one.getStock().hashCode());
        System.out.println("should result 0 : ");
        System.out.println(two.occupancy());
        System.out.println(two.getStock().hashCode());


        for (Warehouse warehouse : WarehouseRepository.WAREHOUSE_LIST) {
            System.out.println(warehouse.toString());
        }

        Employee one2 = new Employee("jonh","123","Admin",null);

        System.out.println(one2.getName());

    }

}
