import system.employee.employee.Employee;
import system.employee.employee.Payment;
import system.employee.utilities.AgeCalculator;
import system.employee.utilities.IdGenerator;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmployeeApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int menuOption = 0;
        LocalDate now = LocalDate.now();
        List<Employee> employeeList = new ArrayList<>();

        System.out.println("-=-=-=-=--=-=-=-=");
        System.out.println("EMPLOYEE SYSTEM");
        System.out.println("-=-=-=-=--=-=-=-=");

        while (menuOption != 5) {
            System.out.println("\n1 - Cadastrar novo funcionário.");
            System.out.println("2 - Listar todos os funcionários.");
            System.out.println("3 - Excluir funcionário.");
            System.out.println("4 - Aumentar salário.");
            System.out.println("5 - Encerrar programa.");
            System.out.print("\nDigite a ação escolhida: ");
            menuOption = scanner.nextInt();
            scanner.nextLine();

            if (menuOption == 1) {
                System.out.print("\nNome do funcionário: ");
                String employeeName = scanner.nextLine();
                System.out.print("Data de nascimento(DD/MM/AAAA): ");
                String employeeBithDayStr = scanner.nextLine();
                System.out.print("Digite o email: ");
                String employeeEmail = scanner.nextLine();
                LocalDate employeeBithDay = LocalDate.parse(employeeBithDayStr, formatter);
                System.out.print("Cargo do funcionário: ");
                String employeeRole = scanner.nextLine();
                System.out.print("Salário base do funcionário: ");
                Double salary = scanner.nextDouble();

                IdGenerator idGenerator = new IdGenerator();

                boolean checkId = true;
                int employeeId = 0;

                /* Enquanto o Id existir, o programa vai gerar um ID que ainda não existe para que
                possa instanciar o funcionárioc com ID único.
                Esse ID será a chave primária do banco de dados que irei criar.*/

                while (checkId) {
                    employeeId = idGenerator.generateId();
                    checkId = idGenerator.isIdPresent(employeeList, employeeId);
                }
                // Calcula a idade do funcionário.
                AgeCalculator ageCalculator = new AgeCalculator();
                int employeeAge = ageCalculator.calculateAge(employeeBithDay, now);

                Payment employeePayment = new Payment(salary);



                Employee employee = new Employee(employeeName, employeeId, employeeBithDay, employeeEmail, employeeRole,
                        employeeAge, employeePayment);



                java.sql.Date sqlDate = java.sql.Date.valueOf(employeeBithDay);
                double salarySql = employeePayment.totalPayment();

                try {
                    Connection connection = DriverManager
                            .getConnection("jdbc:mysql://localhost:3306/employeesDB", "root",
                                    "alexandreprado123");

                    String sql = "INSERT INTO registro_funcionarios (NOME, ID, DATA_NASCIMENTO, EMAIL, CARGO, IDADE, SALÁRIO)"
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)";


                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, employeeName);
                    preparedStatement.setInt(2, employeeId);
                    preparedStatement.setDate(3, sqlDate);
                    preparedStatement.setString(4, employeeEmail);
                    preparedStatement.setString(5, employeeRole);
                    preparedStatement.setInt(6, employeeAge);
                    preparedStatement.setDouble(7, salarySql);

                    preparedStatement.executeUpdate();

                    connection.close();


                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }


                // employeeList.add(employee);


            } else if (menuOption == 2) {
                for (Employee employee : employeeList) {
                    System.out.println("\n" + employee);
                }
            } else if (menuOption == 3) {
                System.out.print("\nDigite a Id do funcionário que deseja excluir: ");
                Integer idToRemove = scanner.nextInt();
                Employee employee = new Employee();
                employee.removeEmployee(idToRemove, employeeList);
            }

            else if (menuOption == 4) {
                System.out.print("\nDigite o Id do funcionário que receberá o aumento: ");
                Integer idToIncrease = scanner.nextInt();
                System.out.print("Digite a porcentagem(%) de aumento no salário base: ");
                double increasePercentage = scanner.nextDouble();
                for (Employee employee : employeeList){
                    double increase = employee.getPayment().getSalary() * increasePercentage / 100;
                    double increasedSalary = employee.getPayment().getSalary() + increase;
                    employee.getPayment().setSalary(increasedSalary);
                }
            }
        }
        System.out.println("\nPrograma encerrado...");
    }
}


