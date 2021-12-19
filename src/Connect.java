import java.sql.*;

/**
 * @author sqlitetutorial.net
 */
public class Connect {
    private final String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";
    //Ask Benoit on public/private stuff tomorrow

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connect con = new Connect();
        con.connect();
        con.createNewTable();
        con.selectAll();
    }

    /**
     * Connect to the coffee databases
     */
    public void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void createNewTable() {
        String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";

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

    public void insert(int id, String customerName, int numberItems, double totalPrice) {
        String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";

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
