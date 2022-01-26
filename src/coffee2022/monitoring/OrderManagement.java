package coffee2022.monitoring;

import coffee2022.baseobjs.ConnectedClass;
import coffee2022.objs.Customer;
import coffee2022.objs.Recipe;

import java.sql.SQLException;
import java.util.ArrayList;


public class OrderManagement extends ConnectedClass {

    public Customer customer;
    public ArrayList<Recipe> items;

    public OrderManagement() {
        this.items = new ArrayList<Recipe>();
    }

    public void addItem(Recipe r) {
        if (r != null) {
            this.items.add(r);
        }
    }

    /**
     * Save the order to the database
     */
    public void save() throws SQLException {
        int id = getNewId();
        connection.insert(id, customer.name, items.size(), countGrandTotal());
        // for testing
        System.out.printf("%d %s %d %.2f %n", id, customer.name, items.size(), countGrandTotal());

    }

    /**
     * List the orders from the database
     * - Create the query
     * - Execute the query
     * - List the results
     */
    public void showOrders() {
         connection.selectAll();
    }

    public void printList() {
        for (Recipe item : items) {
            System.out.println(item.name + ", " + item.price + ".");
        }
    }

    public double countGrandTotal(){
        return countTotal() + countTax();
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
        return connection.getLastCustomerID() + 1;
    }

}
