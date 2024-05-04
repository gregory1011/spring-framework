package com.cydeo.repository;

import com.cydeo.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query("SELECT c from Cinema c where c.id= :id")
    List<Cinema> readCinemaById(@Param("id") int id);


    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all cinemas by location country
    @Query(value = "select * from C cinemas left join C locations on L locations where L country = :country", nativeQuery = true)
    List<Cinema> readCinemaByLocation_Country(@Param("country") String country);

    //Write a native query to read all cinemas by name or sponsored name contains a specific pattern
    @Query(value = "SELECT * from cinemas where name or sponsoredName like('%',?1,'%')", nativeQuery = true)
    List<Cinema> readAllByNameOrderBySponsoredNameContaining(String name);

    //Write a native query to sort all cinemas by name
    @Query(value = "select * from cinemas order by name;", nativeQuery = true)
    List<Cinema> readCinemaAndOrderByNameDesc();

    //Write a native query to distinct all cinemas by sponsored name
    @Query(value = "select distinct(sponsoreName) from cinemas ", nativeQuery = true)
    List<Cinema> readDistinctBySponsoredName();
}
