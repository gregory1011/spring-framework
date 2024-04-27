package com.cydeo.bootstrap;

import com.cydeo.entity.Department;
import com.cydeo.entity.Employee;
import com.cydeo.enums.Gender;
import com.cydeo.repository.DepartmentRepo;
import com.cydeo.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DataGenerator implements CommandLineRunner {

    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;


    @Override
    public void run(String... args) throws Exception {

        List<Employee> empList = new ArrayList<>();
        List<Department> depList = new ArrayList<>();


        Employee e1 = new Employee("Berrie", "Manueau", "bmanueau0@dion.ne.jp", LocalDate.of(2006,04,20), Gender.Female, 154864D);
        Employee e2 = new Employee("Aeriell", "McNee", "amcnee1@google.es", LocalDate.of(2009,01,26), Gender.Female, 56752D);
        Employee e3 = new Employee("Sydney", "Symonds", "ssymonds2@hhs.gov", LocalDate.of(2010,05,17), Gender.Female, 95313D);
        Employee e4 = new Employee("Avrom", "Rowantree", null, LocalDate.of(2014,03,02), Gender.Male, 119764D);
        Employee e5 = new Employee("Feliks", "Morffew", "fmorffew4@a8.net", LocalDate.of(2003,01,14), Gender.Male, 55307D);

        empList.addAll(Arrays.asList(e1,e2,e3,e4,e5));
        employeeRepo.saveAll(empList);

        Department d1 = new Department("Sports","Outdoors");
        Department d2 = new Department("Tools","Hardware");
        Department d3 = new Department("Clothing","Home");
        Department d4 = new Department("Phones & Tablets","Electronics");
        Department d5 = new Department("Computers","Electronics");

        depList.addAll(Arrays.asList(d1,d2,d3,d4,d5));
        departmentRepo.saveAll(depList);

    }
}
