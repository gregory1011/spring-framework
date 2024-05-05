package com.cydeo.repository;

import com.cydeo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {


    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to list all accounts with a specific country or state
    List<Account> readDistinctByCountryOrState(String country, String state);
    //Write a derived query to list all accounts with age lower than or equal to a specific value
    List<Account> findAccountsByAgeLessThanEqual(int age);

    //Write a derived query to list all accounts with a specific role
    List<Account> readAccountsByRole(String role);

    //Write a derived query to list all accounts between a range of ages
    List<Account> findAccountsByAgeBetween(int age1, int age2);

    //Write a derived query to list all accounts where the beginning of the address contains the keyword
    List<Account> findAccountsByAddressContaining(String address);

    //Write a derived query to sort the list of accounts with age
    List<Account> findAccountsByAgeOrderByAge(int age);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all accounts
    @Query("select a from Account a")
    List<Account> returnAllAccounts();

    //Write a JPQL query to list all admin accounts
    @Query("select a from Account a where a.user = ?1")
    List<Account> readAccountByAdmin(String role);

    //Write a JPQL query to sort all accounts with age
    @Query("select a from Account a where a.age=?1 group by a.age")
    List<Account> readAccountByAgeAndSort(int age);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all accounts with an age lower than a specific value
    @Query(value = "select * from employees where age < :age", nativeQuery = true)
    List<Account> readAccountByAgeLessThan(@Param("age") int age);

    //Write a native query to read all accounts that a specific value can be containable in the name, address, country, state, city
    @Query(value = "select * from employees where name or address or city or state or country like ('%', :word, '%')", nativeQuery = true)
    List<Account> readAccountByNameOrCityOrAddressOrCountryOrState(@Param("word") String word);

    //Write a native query to read all accounts with an age lower than a specific value
    @Query(value = "select * from employees where age < ?1", nativeQuery = true)
    List<Account> readAccountByAgeThatLessThan(int age);



}
