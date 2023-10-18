import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection connectToDB() {
        try {
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/employeesDB?user=root&password=alexandreprado123");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

