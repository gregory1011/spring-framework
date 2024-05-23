package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.entity.User;
import com.cydeo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {


    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to list all accounts with a specific country or state
    List<Account> findAccountsByCountryOrState(String country, String state);

    //Write a derived query to list all accounts with age lower than or equal to a specific value
    List<Account> findAccountsByAgeLessThanEqual(Integer age);
    //Write a derived query to list all accounts with a specific role
    List<Account> getAccountsByRole(UserRole role);

    //Write a derived query to list all accounts between a range of ages
    List<Account> findAccountsByAgeBetween(Integer ageStart, Integer ageEnd);

    //Write a derived query to list all accounts where the beginning of the address contains the keyword
    List<Account> findAccountsByAddressStartingWith(String keyword);

    //Write a derived query to sort the list of accounts with age
    List<Account> findAccountsByOrderByAgeDesc();

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all accounts
    @Query("select a from Account a")
    List<Account> getAllAccounts();

    //Write a JPQL query to list all admin accounts
    @Query("select a from Account a where a.role = 'USER'")
    List<Account> findAdminAccounts();


    //Write a JPQL query to sort all accounts with age
    @Query("select a from Account a order by a.age")
    List<Account> findAccountsAndOrderByAge();


    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all accounts with an age lower than a specific value
    @Query(value = "select * from account_details where age < : age", nativeQuery = true)
    List<Account> retrieveAllBasedOnAge(@Param("age") Integer age);

    //Write a native query to read all accounts that a specific value can be containable in the name, address, country, state, city

    // ILIKE to make it lower case because sql aer caise sensitive
    @Query(value = "select * from account_details where name ILIKE concat('%', ?1, '%') or address ILIKE concat('%', ?1, '%') or city ILIKE concat('%', ?1, '%') or state ILIKE concat('%', ?1, '%') or country ILIKE ('%', ?1, '%')", nativeQuery = true)
    List<Account> readAccountByNameOrCityOrAddressOrCountryOrState( String word);

    //Write a native query to read all accounts with an age lower than a specific value
    @Query(value = "select * from account_details where age < ?1", nativeQuery = true)
    List<Account> findByAgeLessThan(Integer age);

    @Query(value = "select * from account_details where age < ?1", nativeQuery = true)
    List<Account> readAccountByAgeThatLessThan(int age);



}
