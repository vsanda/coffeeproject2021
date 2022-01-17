import java.util.ArrayList;

public class OrderManagement {

    public String customer;
    public ArrayList<Recipe> items;

    public OrderManagement() {
        this.items = new ArrayList<Recipe>();
    }

    public void addItem(Recipe r) {
        if (r != null) {
            items.add(r);
        }
    }
}
