package com.cydeo.service;

import com.cydeo.repository.EmployeeRep;
import com.cydeo.repository.HoursRep;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SalaryService {

    private final EmployeeRep employeeRep;
    private final HoursRep hoursRep;

    public SalaryService(EmployeeRep employeeRep, @Qualifier("regularHours") HoursRep hoursRep) {
        this.employeeRep = employeeRep;
        this.hoursRep = hoursRep;
    }

    public void calculateRegularSalary() {
        //HourlyRate * Regular Hours
        System.out.println("Net salary = $"+(employeeRep.getHourlyRate() * hoursRep.getHours()));
    }


}
