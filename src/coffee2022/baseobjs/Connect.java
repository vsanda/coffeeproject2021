package coffee2022.baseobjs;

import java.sql.*;

public class Connect {

    private final String url;

    public Connect(String url) throws SQLException {
        this.url = url;

        // Create the tables if needed
        createTables();
    }

    /**
     * This function execute a query on the database.
     * SQLException will be thrown if something went wrongs
     *
     * @param stmt The SQL statement to execute
     * @return ResultSet if any results, else null
     */
    public ResultSet executeQuery(String stmt, Object... args) {
        // Create the connection
        Connection connection = null;

        // Retrieve the type of query
        boolean dml = (stmt.startsWith("INSERT") || stmt.startsWith("UPDATE") || stmt.startsWith("DELETE") || stmt.startsWith("CREATE"));

        try {
            connection = DriverManager.getConnection(this.url);
        } catch (SQLException e) {
            System.out.println("Error while connecting to the DB:" + e.getMessage());
            return null;
        }

        // Execute the statement
        if (args.length == 0) {
            // We have a normal statement
            try {
                Statement statement = connection.createStatement();
                if (!dml) {
                    return statement.executeQuery(stmt);
                } else {
                    statement.execute(stmt);
                    return null;
                }
            } catch (SQLException e) {
                System.out.println("Error while executing the query \"" + stmt + "\"\n" + e.getMessage());
            }
        }

        try {
            PreparedStatement statement = connection.prepareStatement(stmt);
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof Integer) {
                    statement.setInt(i + 1, Integer.parseInt(arg.toString()));
                } else if (arg instanceof Float) {
                    statement.setFloat(i + 1, Float.parseFloat(arg.toString()));
                } else {
                    statement.setString(i + 1, arg.toString());
                }
            }
            if (dml) {
                statement.executeUpdate();
            } else {
                return statement.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("Error while executing the query \"" + stmt + "\"\n" + e.getMessage());
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
        executeQuery(order_management_sql);
        executeQuery(inventory_sql);
    }

    /**
     * select all rows in the order_management data
     */
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

    // Notes: need to prepare a diff function for PreparedStatement;
    public void insert(int id, String customerName, int numberItems, double totalPrice) throws SQLException {
        String sql = "INSERT INTO order_management(id, customer_name, number_items, total_price) VALUES(?,?,?,?)";
        executeQuery(sql, id, customerName, numberItems, totalPrice);

    }

    public void insertInventory(String inventory_name, int quantity) {
        String sql = "INSERT INTO inventory(inventory_name, quantity) VALUES(?,?)";
        executeQuery(sql, inventory_name, quantity);
    }

    // Building this function to get more specific queries
    // Notes: need to prepare a diff function for PreparedStatement;
    public void getItemsGreaterThan(int limit) {
        System.out.println("This query will select all rows for all items greater than " + limit + " from the table");
        String sql = "SELECT id, customer_name, number_items, total_price FROM order_management WHERE number_items > ?";
        try {
            ResultSet rs = executeQuery(sql, limit);
            // looping through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("customer_name") + "\t\t\t" +
                        rs.getInt("number_items") + "\t\t\t\t" +
                        rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            System.out.println("Error when retrieving items: " + e.getMessage());
        }
    }

    // Delete any entries specified by the id
    // Notes: need to prepare a diff function for PreparedStatement;
    public void deleteById(int id) {
        String sql = "DELETE FROM order_management WHERE id = ?";
        executeQuery(sql, id);
        System.out.println("Row with id " + id + " was successfully deleted.");
    }
}
