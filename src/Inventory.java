import java.util.HashMap;

public class Inventory extends ConnectedClass {

    private final HashMap<String, Integer> inventory;

    public Inventory() {
        this.inventory = new HashMap<String, Integer>();
    }

    public void addInventory(String inventory_name, int quantity){
        connection.insertInventory(inventory_name, quantity);
    }


}
