package system.employee.utilities;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {

    public AgeCalculator() {
    }

    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
}
