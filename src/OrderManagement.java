import java.sql.SQLException;
import java.util.ArrayList;


public class OrderManagement {

    public Customer customer;
    public ArrayList<Recipe> items;
    private final Connect con;

    public OrderManagement(Connect con) {
        this.items = new ArrayList<Recipe>();
        this.con = con;
    }

    // for testing or validation purposes, will delete after launch
    public static void main(String[] args) {


    }

    public void addItem(Recipe r) {
        if (r != null) {
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
    public void save(String name, int numberItems, double totalPrice) throws SQLException {
        int id = getNewId();
        con.insert(id, name, numberItems, totalPrice);
        // for testing
        System.out.printf("%d %s %d %.2f %n", id, name, numberItems, totalPrice);

    }

    /**
     * List the orders from the database
     * - Connect to the SQLite database
     * - Create the query
     * - Execute the query
     * - List the results
     */
    public void showOrders() {
         con.selectAll();
    }

    public void printList() {
        for (Recipe item : items) {
            System.out.println(item.name + ", " + item.price + ".");
        }
    }

    public double countTotal() {
        double total = 0.0;
        for (Recipe item : this.items) {
            total += item.price;
        }
        return total;
    }

    public double countTax() {
        double total = countTotal();
        double tax = 0.073;
        return total * tax;
    }

    public int countPrepTime() {
        int totalTime = 0;
        for (Recipe item : this.items) {
            totalTime += item.preptime;
        }
        return totalTime;
    }

    public int getNumberItems(){
        return items.size();
    }

    public int getNewId()  {
        return con.getLastCustomerID() + 1;
    }

}
