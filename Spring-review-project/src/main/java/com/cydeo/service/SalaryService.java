package com.cydeo.Service;

import com.cydeo.Repository.EmployeeRep;
import com.cydeo.Repository.HoursRep;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SalaryService {

    private final EmployeeRep employeeRep;
    private final HoursRep hoursRep;

    public SalaryService(EmployeeRep employeeRep, @Qualifier ("regularHours") HoursRep hoursRep) {
        this.employeeRep = employeeRep;
        this.hoursRep = hoursRep;
    }

    public void calculateRegularSalary() {
        System.out.println((employeeRep.getHourlyRate() * hoursRep.getHours()));
    }


}
