package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a user with an email?
    Optional<User> findAllByEmail(String email);

    //Write a derived query to read a user with username?
    Optional<User> findAllByUsername(String username);

    //Write a derived query to list all users that contain a specific name?
    List<User> findByAccountNameContaining(String name);

    //Write a derived query to list all users that contain a specific name in the ignore case mode?
    List<User> findByAccountNameContainingIgnoreCase(String name);

    //Write a derived query to list all users with an age greater than a specified age?
    List<User> findUserByAccount_AgeGreaterThan(Integer age);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns a user read by email?
    @Query("select u from User u where u.email = ?1")
    Optional<User> retrieveByEmailJPQL(String email);

    //Write a JPQL query that returns a user read by username?
    @Query("select u from User u where u.username = ?1")
    Optional<User> retrieveByUsernameJPQL(String username);
    //Write a JPQL query that returns all users?
    @Query("select u from User u")
    List<User> fetchAllUsers();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns all users that contain a specific name?
    @Query(value = "select u.* user_account u join account_details a on u.account_details_id = a.id where a.name ILIKE concat('%', ?1, '%')", nativeQuery = true)
    List<User> retrieveAllByName(String name);

    //Write a native query that returns all users?
    @Query(value = "select * from user_account", nativeQuery = true)
    List<User> retrieveAllUsers();

    //Write a native query that returns all users in the range of ages?
    @Query(value = "select * from user_account u join account_details a ON u.account_details_id = a.id where age between ?1 and ?2",nativeQuery = true)
    List<User> retrieveAgeBetweenRange(Integer age1, Integer age2);

    //Write a native query to read a user by email?
    @Query(value = "select * from user_account where email =?1", nativeQuery = true)
    Optional<User> retrieveByEmail(String email);


}
