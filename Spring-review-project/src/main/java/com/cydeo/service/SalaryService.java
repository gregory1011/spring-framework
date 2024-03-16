

package com.cydeo.service;
import com.cydeo.model.Employee;
import com.cydeo.repository.RegularHours;
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
