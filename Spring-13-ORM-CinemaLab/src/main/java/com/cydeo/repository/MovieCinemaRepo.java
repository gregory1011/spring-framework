package com.cydeo.repository;

import com.cydeo.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieCinemaRepo extends JpaRepository<MovieCinema, Long> {


    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read movie cinema with id
    List<MovieCinema> readAllById(int id);

    //Write a derived query to count all movie cinemas with a specific cinema id
    List<MovieCinema> countMovieCinemaById(int id);

    //Write a derived query to list all movie cinemas with higher than a specific date
    List<MovieCinema> readMovieCinemaByDateTimeGreaterThan(LocalDateTime dateTime);

    //Write a derived query to find the top 3 expensive movies
    List<MovieCinema> readTop3ByMovie_Price(int price);

    //Write a derived query to list all movie cinemas that contain a specific movie name
    List<MovieCinema> readDistinctByMovie_Name(String name);

    //Write a derived query to list all movie cinemas in a specific location
    List<MovieCinema> readMovieCinemaByCinema_Location_Name(String name);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movie cinemas with higher than a specific date
    @Query("select mc from movie_cinema mc where date_time > ?1")
    List<MovieCinema> readMovieCinemasByDateTimeGreaterThan(LocalDateTime dateTime);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count all movie cinemas by cinema id
    @Query(value = "select * from moviecinemas where id = ?1", nativeQuery = true)
    List<MovieCinema> readMovieCinemaByCinema_Id(int id);

    //Write a native query that returns all movie cinemas by location name

}
