package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a user with an email?
    List<User> findAllByEmail(String email);

    //Write a derived query to read a user with username?
    List<User> readAllByUsername(String username);

    //Write a derived query to list all users that contain a specific name?
    List<User> readAllByUsernameContaining(String name);

    //Write a derived query to list all users that contain a specific name in the ignore case mode?
    List<User> readAllByUsernameContains(String name);

    //Write a derived query to list all users with an age greater than a specified age?
    List<User> findUserByAccount_AgeGreaterThan(int age);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns a user read by email?

    //Write a JPQL query that returns a user read by username?

    //Write a JPQL query that returns all users?
    @Query("SELECT u FROM User u")
    List<User> fetchAllUsers();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns all users that contain a specific name?

    //Write a native query that returns all users?

    //Write a native query that returns all users in the range of ages?

    //Write a native query to read a user by email?




}
