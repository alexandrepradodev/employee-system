import system.employee.employee.Employee;
import system.employee.employee.Payment;
import system.employee.utilities.AgeCalculator;
import system.employee.utilities.IdGenerator;

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

        System.out.println("-=-=-=-=--=-");
        System.out.println("EMPLOYEE APP");
        System.out.println("-=-=-=-=--=-");

        while (menuOption != 4) {
            System.out.println("\n1 - Cadastrar novo funcionário.");
            System.out.println("2 - Listar todos os funcionários.");
            System.out.println("3 - Excluir funcionário.");
            System.out.println("4 - Encerrar programa.");
            System.out.print("\nDigite a ação escolhida: ");
            menuOption = scanner.nextInt();
            scanner.nextLine();

            if (menuOption == 1) {
                System.out.print("\nNome do funcionário: ");
                String employeeName = scanner.nextLine();
                System.out.print("Data de nascimento(DD/MM/AAAA): ");
                String employeeBithDayStr = scanner.nextLine();
                LocalDate employeeBithDay = LocalDate.parse(employeeBithDayStr, formatter);
                System.out.print("Cargo do funcionário: ");
                String employeeRole = scanner.nextLine();
                System.out.print("Salário base do funcionário: ");
                Double salary = scanner.nextDouble();
                Payment baseSalary = new Payment(salary);

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

                Employee employee = new Employee(employeeName, employeeId, employeeBithDay, employeeRole,
                        employeeAge, baseSalary);

                employeeList.add(employee);


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
        }
        System.out.println("Programa encerrado");
    }
}


