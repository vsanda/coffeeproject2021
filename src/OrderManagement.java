import java.util.ArrayList;


public class OrderManagement {

    public Customer customer;
    public ArrayList<Recipe> items;

    public OrderManagement() {
        this.items = new ArrayList<Recipe>();
    }

    // for testing or validation purposes, will delete after launch
    public static void main(String[] args) {
        Connect connect = new Connect();
         connect.connect();
//         connect.deleteById(1005);
         connect.selectAll();
//         connect.getItemsGreaterThan(1);
//         connect.getLastCustomerID();

        OrderManagement om = new OrderManagement();
//        om.save("George", 2, 15.50);
//        om.save("Rufus", 3, 17.25);
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
    public void save(String name, int numberItems, double totalPrice) {
        //  is try and catch necessary here?
        Connect con = new Connect();
        con.connect();
        // is this method best practice to automate the customer id?
        int id = getNewId();
        con.createNewTable();
        con.insert(id, name, numberItems, totalPrice);
        // for testing
        System.out.printf("%d %s %d %.2f %n", id, name, numberItems, totalPrice);

        /* old code
        if (id == 0) {
            con.createNewTable();
            id = 1001;
            con.insert(id, name, numberItems, totalPrice);
            // for testing below
            System.out.printf("%d %s %d %.2f %n", id, name, numberItems, totalPrice);
            id++;
        } else {
            con.insert(id, name, numberItems, totalPrice);
            id++;
        }
        */
    }

    /**
     * List the orders from the database
     * - Connect to the SQLite database
     * - Create the query
     * - Execute the query
     * - List the results
     */
    public void showOrders() {
        Connect con = new Connect();
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

    public int getNewId(){
        Connect connect = new Connect();
        return connect.getLastCustomerID() + 1;
    }

}
