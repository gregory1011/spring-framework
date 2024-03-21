package com.cydeo.Repository;

import com.cydeo.Model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DBEmployeeRep implements EmployeeRep{

    Employee employee;

    @Override
    public Double getHourlyRate() {
        return employee.getHourlyRate();
    }


}
