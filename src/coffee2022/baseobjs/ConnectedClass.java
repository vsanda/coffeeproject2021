package coffee2022.baseobjs;

import java.sql.SQLException;

public class ConnectedClass {
    protected static Connect connection;

    public ConnectedClass() {
        try {
            connection = new Connect("jdbc:sqlite:C:/sqlite/db/chinook.db");
        } catch (SQLException e) {
            System.out.println("Cannot connect to the DB!\n"+e.getMessage());
        }
    }
}
