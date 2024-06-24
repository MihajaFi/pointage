package school.hei.pointage.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import school.hei.pointage.Calendar;
import school.hei.pointage.Salary;
import school.hei.pointage.WorkDay;
import school.hei.pointage.employee.Employee;
@Data
@AllArgsConstructor
public class Calculator {
    public static Salary calculateSalary(Employee employee, Calendar calendar) {
        // Calcul du salaire brut
        double brut = 0;
        for (WorkDay workDay : employee.getWorkDays()) {
            double dailyRate = employee.getCategory().getSalaryPerWeek() / 7.0; // 1 semaine = 7 jours
            double hourlyRate = dailyRate / (workDay.isNight() ? 14.0 : 10.0); // 1 nuit = 14h, 1 jour = 10h

            if (workDay.isNight()) {
                brut += workDay.getHoursWorked() * hourlyRate * 1.3; // Majoration de 30% pour les heures de nuit
            } else {
                brut += workDay.getHoursWorked() * hourlyRate;
            }
        }

        // Calcul du salaire net
        double net = brut * 0.8;

        return new Salary(brut, net);
    }
}
