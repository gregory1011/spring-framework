package com.cydeo.repository;

import com.cydeo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    // display all employees with email address''
    List<Employee> findEmployeeByEmail(String email);

    // display all employee with first name ' ' and last name ' ' , also show all employees with an email address
    List<Employee> findEmployeeByFirstNameAndLastNameOrEmail(String  firstName, String lastName, String email);

    //display all employees that first name is not ' '
    List<Employee> findByFirstNameIsNot(String firstName);

    //display all employees where last name starts with ' '
    List<Employee> findByLastNameStartingWith(String pattern);

    // display all employees with salary higher than ' '
    List<Employee> findBySalaryGreaterThan(Integer salary);

    // display all employees with salaries less than ' '
    List<Employee> findBySalaryLessThanEqual(Integer salary);

    // display all employees that has been hired between ' ' and ' '
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    // display all employees where salaries grater and equal to ' ' in order
    List<Employee> findBySalaryIsGreaterThanEqualOrderBySalaryDesc(Integer salary);

    // display unique top 3 employees that is making less than ' '
    List<Employee> findDistinctTop3BySalaryLessThan(Integer salary);

    // display all employees that doesn't have email address
    List<Employee> findByEmailIsNull();



    @Query("select e from Employee e where e.email = 'jhookd@booking.com'")
    Employee getEmployeeDetail();

    @Query("SELECT e.salary FROM Employee e WHERE e.email = 'jhookd@booking.com'")
    Integer getEmployeeSalary();

    @Query("Select e from Employee e where e.email=?1")
    Optional<Employee> getEmployeeDetail(String email);

    @Query("select e from Employee e where e.email=?1 and e.salary=?2")
    Employee getEmployeeDetail(String email, int salary);


}
