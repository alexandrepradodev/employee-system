package system.employee.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public ConnectionFactory() {
    }

    public Connection connectToDB () {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/employeesDB", "root",
                    "alexandreprado123");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
