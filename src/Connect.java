import java.sql.*;

/**
 * @author sqlitetutorial.net
 */
public class Connect {
    // Set the url private to not be modified by anything else other than the Connect class itself.
    private final String url = "jdbc:sqlite:C:/temp/db/chinook.db";

    // Store the connection to the database
    private Connection connection = null;


    /**
     * @param args the command line arguments
     */
    // TODO: main should not have to connect() and createNewTable() but only:
    // Connect con = new Connect();
    // con.selectAll();
    public static void main(String[] args) {
        Connect con = new Connect();
        con.connect();
        con.createNewTable();
        con.selectAll();
    }

    /**
     * Connect to the coffee databases
     * TODO: This function should let the SQLException bubble up instead of catching it
     */
    public void connect() {
        try {
            // create a connection to the database
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * This function execute a query on the database.
     * SQLException will be thrown if something went wrong
     * TODO: Write this function and use it in any of the new functions
     * @param statement The SQL statement to execute
     * @return ResultSet if any results, else null
     */
    private ResultSet executeQuery(PreparedStatement statement) throws SQLException{
        // Check if the connection is open. If not -> Open it
        // Execute the statement
        // Catch and display errors if any. Also let the exception bubble
        // (Optional) Create and return the ResultSet containing the results
        return null;
    }

    // TODO: This function should actually be the constructor: It will create the database when the class is instantiated if it does not exist already.
    // TODO: Also replace the connection and execution of the query with the executeQuery() function
    public void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS order_management (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	customer_name text NOT NULL,\n"
                + "	number_items integer NOT NULL,\n"
                + "	total_price double NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table successfully created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // TODO: replace the connection and execution of the query with the executeQuery() function
    public void insert(int id, String customerName, int numberItems, double totalPrice) {
        String sql = "INSERT INTO order_management(id, customer_name, number_items, total_price) VALUES(?,?,?,?)";

        //Ask benoit if I'm already calling connect() function in OM Class, does the code below need to be modified?
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, customerName);
            pstmt.setInt(3, numberItems);
            pstmt.setDouble(4, totalPrice);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * select all rows in the order_management data
     */
    // TODO: replace the connection and execution of the query with the executeQuery() function
    public void selectAll() {
        System.out.println("This query will select all rows from the table");
        String sql = "SELECT id, customer_name, number_items, total_price FROM order_management";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("id \t customer_name \t number_items \t total_price ");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("customer_name") + "\t\t\t" +
                        rs.getInt("number_items") + "\t\t\t\t" +
                        rs.getDouble("total_price"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Building this function to get more specific queries
    // TODO: replace the connection and execution of the query with the executeQuery() function
    public void getItemsGreaterThan(int limit){
        System.out.println("This query will select all rows for all items greater than " + limit + " from the table");
        String sql = "SELECT id, customer_name, number_items, total_price FROM order_management WHERE number_items > ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();

            // looping through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("customer_name") + "\t\t\t" +
                        rs.getInt("number_items") + "\t\t\t\t" +
                        rs.getDouble("total_price"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    // Created this function with the intend to automate customerID
    // TODO: replace the connection and execution of the query with the executeQuery() function
    public int getLastCustomerID(){
        String sql = "SELECT max(id) as id FROM order_management";
        int maxId = 0;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                maxId = rs.getInt("id");
//                System.out.println("The last customer ID on the order management is " + maxId);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return maxId;
    }

    // Delete any entries specified by the id

    // TODO: replace the connection and execution of the query with the executeQuery() function
    public void deleteById(int id){
        String sql = "DELETE FROM order_management WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Row with id " + id + " was successfully deleted.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
