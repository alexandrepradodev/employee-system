package system.employee.employee;

public class Payment {
    private Double salary;
    public Payment() {
    }

    public Payment(Double salary) {
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    public double totalPayment() {
        return getSalary();
    }
}
