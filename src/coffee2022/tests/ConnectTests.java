package coffee2022.tests;

import coffee2022.dbMgmt.Connect;
import org.junit.jupiter.api.*;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectTests {

   Path dbPath;
   String tempdir;
   Connection connection;
   Connect c;

    @BeforeEach
    void setUp() throws IOException, SQLException {
        // Creates a temporary directory
        tempdir = Files.createTempDirectory("tempdb").toFile().getAbsolutePath();
        dbPath = Path.of(tempdir, "db.db");

        // Test if we actually got the tables created
        connection = DriverManager.getConnection("jdbc:sqlite:"+dbPath.toString());

        // Create the Connect class
        c = new Connect("jdbc:sqlite:"+dbPath.toString());

    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the temporary directory
        FileSystemUtils.deleteRecursively(new File(tempdir));
    }

    @Test
    public void connect_create_tables_successfully() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery("SELECT * from order_management");
        statement.executeQuery("SELECT * from inventory");
    }

    @Test
    public void get_last_customer_id() throws SQLException {
        // Database should be empty -> should return 0
        assertEquals(c.getLastCustomerID(), 0);

        // Insert a new order and make sure we get id = 1
        c.insert(134, "name", 1, 12);
        assertEquals(c.getLastCustomerID(), 134);
    }

    @Test
    public void test3() {

    }

}
