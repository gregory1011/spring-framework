package com.cydeo.repository;

import com.cydeo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, String> {

    // display all departments in furniture department.
    List<Department>findByDepartment(String department);

    //display all departments in the Health division
    List<Department>findByDivisionEquals(String department);

    //Display top 3 divisions name includes 'Hea' without duplicates
    List<Department>findDistinctTop3ByDivisionContains(String department);
}
