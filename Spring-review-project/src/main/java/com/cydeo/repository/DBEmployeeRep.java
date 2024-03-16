package com.cydeo.repository;

import com.cydeo.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DBEmployeeRep implements com.cydeo.repository.EmployeeRep {

    Employee employee;

    @Override
    public Double getHourlyRate() {
        return employee.getHourlyRate();
    }


}
