package com.cydeo.repository;

import com.cydeo.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.beans.Transient;
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


    // not equal
    @Query("select e from Employee e where e.salary <> ?1") // JPQL query: Employee comes from model package: Employee class
    List<Employee> getEmployeeSalaryNotEqual(int salary);

    // like/ contains/startsWith/endsWith
    @Query("select  e from Employee  e where e.firstName like ?1")
    List<Employee> getEmployeeFirstNameLike(String pattern);

    // less than
    @Query("select e from Employee e where e.salary < ?1")  // ?1 represents parameter salary from the method
    List<Employee> getEmployeeSalaryLessThan(int salary);

    /// grater than
    @Query("select e from Employee e where e.salary > ?1")  // ?1 represents parameter salary from the method
    List<Employee> getEmployeeSalaryGreaterThan(int salary);

    // before
    @Query("select e from Employee e where  e.hireDate > ?1")
    List<Employee> getEmployeeHideDateBefore(LocalDate date);

    // between
    @Query("select e from Employee  e where e.salary between ?1 and ?2")
    List<Employee> getEmployeeSalaryBetween(int salary1, int salary2);

    // Null
    @Query("select e from Employee  e where e.email is null")
    List<Employee> getEmployeeEmailIsNull();

    // Not Null
    @Query("select e from Employee e where e.email IS NOT NULL")
    List<Employee> getEmployeeEmailIsNotNull();


    // sorting in ascending order
    @Query("select e from Employee e order by e.salary")
    List<Employee> getEmployeeSalaryOrderAsc();


    // sorting in descending order
    @Query("select e from Employee e order by e.salary desc ")
    List<Employee> getEmployeeSalaryOrderDesc();



    // ----------  Using native Query below  -------------- //

    @Query(value = "select * from employees where salary ?1", nativeQuery = true) // employees comes from table name not class name as before
    List<Employee> readEmployeeDetailsBySalary(int salary);


    @Query("select e from Employee e where e.salary = :salary")
    List<Employee> getEmployeeSalary(@Param("salary") int salary);


    // we need to update data in database we have to use those 2 annotations
    @Modifying
    @Transactional
    @Query("update Employee e set e.email ='admin2gmail.com' where  e.id= :id")
    void updateEmployeeJPQL(@Param("id") int id);


    @Modifying
    @Transactional
    @Query(value = "update Employee  set email ='admin2gmail.com' where  id= ?1", nativeQuery = true)
    void updateEmployeeNativeQuery(int id);

//------------------------------

}
