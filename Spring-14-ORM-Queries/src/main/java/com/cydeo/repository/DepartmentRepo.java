package com.cydeo.repository;

import com.cydeo.model.Department;
import com.cydeo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, String> {

    // display all departments in furniture department.
    List<Department>findByDepartment(String department);

    //display all departments in the Health division
    List<Department>findByDivisionEquals(String department);

    //Display top 3 divisions name includes 'Hea' without duplicates
    List<Department>findDistinctTop3ByDivisionContains(String department);


    // display department by divisions
    @Query("select d from Department d where d.division IN ?1")
    List<Department> getDepartmentByDivision(List<String> divisions);



    // --------------------- META -INF - properties file =---------//

    List<Department> retrieveDepartmentByDivision(String division);


    @Query(nativeQuery = true)
    List<Department> retrieveDepartmentByDivisionContain(String pattern);





}
