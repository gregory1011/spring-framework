package com.cydeo.repository;

import com.cydeo.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class DBEmployeeRep implements EmployeeRep{


    @Override
    public Double getHourlyRate() {

        Employee employee = new Employee("Messi", "Soccer", 90D);

        return employee.getHourlyRate();
    }


}
