package com.cydeo.Repository;

import com.cydeo.Model.Employee;
import org.springframework.stereotype.Component;

@Component
public class DBEmployeeRep implements EmployeeRep{



    @Override
    public Double getHourlyRate() {
        Employee employee = new Employee("Messi", "Soccer", 95D);
        return employee.getHourlyRate();
    }


}
