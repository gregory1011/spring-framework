package com.cydeo.bootstrap;


import com.cydeo.model.Department;
import com.cydeo.model.Employee;
import com.cydeo.model.Region;
import com.cydeo.repository.CourseRepo;
import com.cydeo.repository.DepartmentRepo;
import com.cydeo.repository.EmployeeRepo;
import com.cydeo.repository.RegionRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DataGenerator implements CommandLineRunner {

    // dependencies injection
    private final RegionRepo regionRepo;
    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;
    private final CourseRepo courseRepo;


    @Override
    public void run(String... args) throws Exception {

        System.out.println("------------ Region test Start --------------");

        // how I will access to method that I just built.
        List<Region> canada = regionRepo.findByCountry("Canada");
        System.out.println("findByCountry Canada= "+canada);

        List<Region> canada1 = regionRepo.findDistinctByCountry("Canada");
        System.out.println("findDistinctByCountry Canada= "+canada);

        List<Region> united = regionRepo.findByCountryContaining("United");
        System.out.println("findByCountryContaining United= "+united);

        List<Region> united1 = regionRepo.findByCountryContainingOrderByCountry("Asia");
        System.out.println("findByCountryContainingOrderByCountry Asia= "+united1);

        List<Region> canada2 = regionRepo.findTop2ByCountry("Canada");
        System.out.println("findTop2ByCountry Canada= "+canada2);
        System.out.println("------------- Region test End --------------------");

        System.out.println("------------- Department test Start --------------");

        List<Department> furniture = departmentRepo.findByDepartment("Furniture");
        System.out.println("findByDepartment Furniture= "+furniture);

        List<Department> health = departmentRepo.findByDivisionEquals("Health");
        System.out.println("findByDivisionEquals Health= "+health);

        List<Department> hea = departmentRepo.findDistinctTop3ByDivisionContains("Hea");
        System.out.println("findDistinctTop3ByDivisionContains Hea= "+hea);

        System.out.println("------------- Department test End ----------------");


        System.out.println("------------- Employee test Start ----------------");

        Employee employeeDetail = employeeRepo.getEmployeeDetail();
        System.out.println("employeeDetail = " + employeeDetail);

        Integer employeeSalary = employeeRepo.getEmployeeSalary();
        System.out.println("employeeSalary = " + employeeSalary);

        Optional<Employee> employeeDetail1 = employeeRepo.getEmployeeDetail("jhookd@booking.com");
        System.out.println("employeeDetail1 = " + employeeDetail1);

        Employee employeeDetail2 = employeeRepo.getEmployeeDetail("lcasarolib@plala.or.jp", 99504);
        System.out.println("employeeDetail2 = " + employeeDetail2);


        System.out.println("------------- Employee test End ------------------");


        System.out.println("------------- Course test Start ------------------");

        courseRepo.findByCategory("Spring").forEach(System.out::println);
        System.out.println("==========================");
        courseRepo.findByCategoryOrderByName("Spring").forEach(System.out::println);

        System.out.println(courseRepo.existsByName("JavaScript for All"));

        System.out.println(courseRepo.countByCategory("Spring"));

        System.out.println(courseRepo.findByNameStartingWith("Scalable"));



        System.out.println("------------- Course test End --------------------");


    }

}
