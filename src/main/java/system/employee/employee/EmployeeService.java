package system.employee.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class EmployeeService {

    private ConnectionFactory connection;

    public EmployeeService(ConnectionFactory connection) {
        this.connection = connection;

    }

    public void addNewEmployee(String name, Integer id, java.sql.Date birthday, String email, String role, Integer age,
                               Double payment) {



        String sql = "INSERT INTO registro_funcionarios (NOME, ID, DATA_NASCIMENTO, EMAIL, CARGO, IDADE, SALÁRIO)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection conn = connection.connectToDB();
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.setDate(3, birthday);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, role);
            preparedStatement.setInt(6, age);
            preparedStatement.setDouble(7, payment);

            preparedStatement.execute();

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    public void removeEmployee(Integer idToRemove, List<Employee> employeeList) {
        Iterator<Employee> employeeIterator = employeeList.iterator();
        while (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            if (idToRemove.equals(employee.getId())) {
                employeeIterator.remove();
            }
        }
    }
}
