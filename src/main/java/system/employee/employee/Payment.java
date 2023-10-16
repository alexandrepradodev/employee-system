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

    public double getMealAllowance() {
        return salary * 0.2;
    }
    public double getBonus() {
        return salary * 0.1;
    }
    public double getTransportation() {
        return 20.0 * 26;
    }


    public double totalPayment() {
        return salary + getBonus() + getTransportation() + getMealAllowance();
    }
}
