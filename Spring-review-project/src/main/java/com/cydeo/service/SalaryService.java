package com.cydeo.Service;

import com.cydeo.Model.Employee;
import com.cydeo.Repository.RegularHours;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SalaryService {

    Employee employee;
    RegularHours regularHours;

    public void calculateRegularSalary() {
        System.out.println((employee.getHourlyRate() * regularHours.getHours()));
    }


}
