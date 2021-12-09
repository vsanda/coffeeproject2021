import java.util.ArrayList;


public class OrderManagement {

    public Customer customer;
    public ArrayList<Recipe> items;

    public OrderManagement(){
        this.items = new ArrayList<Recipe>();
    }

    public void addItem(Recipe r){
        if (r != null){
            this.items.add(r);
        }
    }

    /**
     * Save the order to the database
     * - Connect to the SQLite database
     * - Create the query
     * -- Orders: Id (auto-increment), customer_name, number_items, total_price
     * - Execute the query
     */
    public void save() {

    }

    /**
     * List the orders from the database
     * - Connect to the SQLite database
     * - Create the query
     * - Execute the query
     * - List the results
     */
    public void showOrders(){

    }

    public void printList(){
        for (Recipe item : items) {
            System.out.println(item.name + ", " + item.price + ".");
        }
    }

    public double countTotal(){
        double total = 0.0;
        for (Recipe item : this.items) {
            total += item.price;
        }
        return total;
    }

    public double countTax(){
        double total = countTotal();
        double tax = 0.073;
        return total * tax;
    }

    public int countPrepTime(){
        int totalTime = 0;
        for (Recipe item: this.items){
            totalTime += item.preptime;
        }
        return totalTime;
    }

}
