package com.cydeo.repository;

import com.cydeo.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema, Long> {


    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to get cinema with a specific name
    Optional<Cinema> findCinemaByName(String name);

    //Write a derived query to read sorted the top 3 cinemas that contains a specific sponsored name
    List<Cinema> findFirst3BySponsoredNameContainingOrderBySponsoredName(String sponsorName);

    //Write a derived query to list all cinemas in a specific country: this is join query in location for country
    List<Cinema> findCinemaByLocation_Country(String country);

    //Write a derived query to list all cinemas with a specific name or sponsored name
    List<Cinema> findAllByNameOrSponsoredName(String name, String sponsoredName);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to read the cinema name with a specific id
    @Query("SELECT c.name from Cinema c where c.id = ?1")
    String retrieveById(Long id);


    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all cinemas by location country
    @Query(value = "select * from cinema c join location l on c.location_id = l.id where l.country = ?1", nativeQuery = true)
    List<Cinema> retrieveAllBasedOnLocationCountry( String country);

    //Write a native query to read all cinemas by name or sponsored name contains a specific pattern
    @Query(value = "select * from cinema where name ILIKE concat('%', ?1, '%') or sponsored_name ILIKE concat('%', ?1, '%')", nativeQuery = true)
    List<Cinema> retrieveAllByNameOrSponsoredName(String keyword);

    //Write a native query to sort all cinemas by name
    @Query(value = "select * from cinema order by name", nativeQuery = true)
    List<Cinema> retrieveCinemasAndSortByName();

    //Write a native query to distinct all cinemas by sponsored name
    @Query(value = "select distinct(sponsored_name) from cinema ", nativeQuery = true)
    List<String> retrieveDistinctCinemasBySponsoredName();
}
