package system.employee.employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class Employee {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private String name;
    private Integer id;
    private LocalDate birthDay;
    private String role;
    private Integer age;
    private Payment payment;

    public Employee() {
    }

    public Employee(String name, Integer id, LocalDate birthDay, String role, Integer age, Payment payment) {
        this.name = name;
        this.id = id;
        this.birthDay = birthDay;
        this.role = role;
        this.age = age;
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(id, employee.id)
                && Objects.equals(birthDay, employee.birthDay)
                && Objects.equals(role, employee.role) && Objects.equals(age, employee.age)
                && Objects.equals(payment, employee.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, birthDay, role, age, payment);
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
    @Override
    public String toString() {
        return "\nId: "
        + id
        + "\nNome: "
        + name
        + "\nidade: "
        + age
        + " anos"
        + "\nData de nascimento: "
        + birthDay.format(formatter)
        + "\nCargo: "
        + role
        + "\nSalário total: R$ "
        + String.format("%.2f", getPayment().totalPayment());
     }
}
