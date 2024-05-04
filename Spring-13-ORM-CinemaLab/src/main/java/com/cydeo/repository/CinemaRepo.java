package com.cydeo.repository;

import com.cydeo.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRepo extends JpaRepository<Cinema, Long> {


    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to get cinema with a specific name
    List<Cinema> getCinemaByName(String name);

    //Write a derived query to read sorted the top 3 cinemas that contains a specific sponsored name
    List<Cinema> getCinemaTop3BySponsoredNameOrderBySponsoredName(String sponsorName);

    //Write a derived query to list all cinemas in a specific country
    List<Cinema> getCinemaByLocation_Country(String country);

    //Write a derived query to list all cinemas with a specific name or sponsored name
    List<Cinema> getCinemaByNameOrSponsoredName(String name);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to read the cinema name with a specific id


    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all cinemas by location country

    //Write a native query to read all cinemas by name or sponsored name contains a specific pattern

    //Write a native query to sort all cinemas by name

    //Write a native query to distinct all cinemas by sponsored name
}
