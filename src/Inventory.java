import java.util.HashMap;

public class Inventory {

    private final HashMap<String, Integer> inventory;
    private final Connect con;

    public Inventory(Connect con) {
        this.inventory = new HashMap<String, Integer>();
        this.con = con;
    }

    public void addInventory(String inventory_name, int quantity){
        con.insertInventory(inventory_name, quantity);
    }


}
