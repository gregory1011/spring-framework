package com.cydeo.service;

import com.cydeo.repository.EmployeeRep;
import com.cydeo.repository.HoursRep;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OvertimeSalaryService {

    private final EmployeeRep employeeRep;
    private final HoursRep hoursRep;

    public OvertimeSalaryService(EmployeeRep employeeRep, @Qualifier("overtimeHours") HoursRep hoursRep) {
        this.employeeRep = employeeRep;
        this.hoursRep = hoursRep;
    }

    public void calculateOvertimeSalary() {
       // hourlyRate * regularHours
        System.out.println("Overtime salary = $"+( employeeRep.getHourlyRate() * hoursRep.getHours() ));
    }
}
