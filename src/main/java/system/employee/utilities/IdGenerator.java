package system.employee.utilities;

import system.employee.employee.Employee;

import java.util.List;

public class IdGenerator {

    public IdGenerator() {
    }

    public int generateId() {
        int min = 100;
        int max = 300;
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    // Essa checagem não vai funcionar para o servidor Local MySQL, pois o programa está checando aquilo que está
    // guardado na memória do programa.

    public boolean isIdPresent(List<Employee> list, int id) {
        for (Employee employee : list) {
            if (employee.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
