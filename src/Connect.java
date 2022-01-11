import java.sql.*;

/**
 * @author sqlitetutorial.net
 */
public class Connect {

    private static Connection connection;
    private final Statement stmt;

    // TODO: This function should actually be the constructor: It will create the database when the class is instantiated if it does not exist already.
    // TODO: Also replace the connection and execution of the query with the executeQuery() function
    public Connect(String url) throws SQLException {
        //later down the road, inventory_order_sql: date, inventory_id, quantity, cost_per_item, total_cost
        connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite has been established.");
        stmt = connection.createStatement();
        //executeQuery() function does not work here because it does not return ResultSet
    }

    /**
     * @param args the command line arguments
     */
    // Notes: the functions below are for testing purposes only in this class, will delete after prod
    public static void main(String[] args) throws SQLException {

    }


    /**
     * This function execute a query on the database.
     * SQLException will be thrown if something went wrong
     * TODO: Write this function and use it in any of the new functions
     *
     * @param sql The SQL statement to execute
     * @return ResultSet if any results, else null
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        // Check if the connection is open. If not -> Open it
        // Execute the statement
        // Catch and display errors if any. Also let the exception bubble
        // (Optional) Create and return the ResultSet containing the results
        if (sql == null) {
            throw new SQLException("The SQL statement can't be null");
        }
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //      System.out.println("Query was successfully executed.");
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void createTables() throws SQLException {
        String order_management_sql = "CREATE TABLE IF NOT EXISTS order_management (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	customer_name text NOT NULL,\n"
                + "	number_items integer NOT NULL,\n"
                + "	total_price double NOT NULL\n"
                + ");";
        String inventory_sql = "CREATE TABLE IF NOT EXISTS inventory (\n"
                // let's see how to serialize it
                + "	inventory_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	inventory_name text NOT NULL,\n"
                + "	quantity integer\n"
                + ");";
        stmt.execute(order_management_sql);
        stmt.execute(inventory_sql);
    }

    /**
     * select all rows in the order_management data
     */
    // TODO: replace the connection and execution of the query with the executeQuery() function
    public void selectAll() {
        System.out.println("This query will select all rows from the table");
        String sql = "SELECT id, customer_name, number_items, total_price FROM order_management";
        System.out.println("id \t customer_name \t number_items \t total_price ");
        try {
            ResultSet rs = executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("customer_name") + "\t\t\t" +
                        rs.getInt("number_items") + "\t\t\t\t" +
                        rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAllInventory() {
        System.out.println("This query will select all rows from the table:");
        String sql = "SELECT inventory_id, inventory_name, quantity FROM inventory";
        System.out.println("inventory_id \t inventory_name \t quantity");
        try {
            ResultSet rs = executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("inventory_id") + "\t\t\t\t" +
                        rs.getString("inventory_name") + "\t\t\t\t\t" +
                        rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Created this function with the intend to automate customerID
    // TODO: replace the connection and execution of the query with the executeQuery() function
    public int getLastCustomerID() {
        String sql = "SELECT max(id) as id FROM order_management";
        int maxId = 0;
        try {
            ResultSet rs = executeQuery(sql);
            while (rs.next()) {
                maxId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return maxId;
    }

    // TODO: replace the connection and execution of the query with the executeQuery() function
    // Notes: need to prepare a diff function for PreparedStatement;
    public void insert(int id, String customerName, int numberItems, double totalPrice) throws SQLException {
        String sql = "INSERT INTO order_management(id, customer_name, number_items, total_price) VALUES(?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, customerName);
        pstmt.setInt(3, numberItems);
        pstmt.setDouble(4, totalPrice);
        pstmt.executeUpdate();

    }

    public void insertInventory(String inventory_name, int quantity) {
        String sql = "INSERT INTO inventory(inventory_name, quantity) VALUES(?,?)";
        try (
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, inventory_name);
            pstmt.setInt(2, quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Building this function to get more specific queries
    // TODO: replace the connection and execution of the query with the executeQuery() function
    // Notes: need to prepare a diff function for PreparedStatement;
    public void getItemsGreaterThan(int limit) {
        System.out.println("This query will select all rows for all items greater than " + limit + " from the table");
        String sql = "SELECT id, customer_name, number_items, total_price FROM order_management WHERE number_items > ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();

            // looping through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("customer_name") + "\t\t\t" +
                        rs.getInt("number_items") + "\t\t\t\t" +
                        rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete any entries specified by the id
    // TODO: replace the connection and execution of the query with the executeQuery() function
    // Notes: need to prepare a diff function for PreparedStatement;
    public void deleteById(int id) {
        String sql = "DELETE FROM order_management WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Row with id " + id + " was successfully deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
