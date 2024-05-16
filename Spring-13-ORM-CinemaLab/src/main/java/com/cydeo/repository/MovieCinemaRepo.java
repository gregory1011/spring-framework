package com.cydeo.repository;

import com.cydeo.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieCinemaRepo extends JpaRepository<MovieCinema, Long> {


    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read movie cinema with id
    List<MovieCinema> findAllByCinemaId(Integer id);

    //Write a derived query to count all movie cinemas with a specific cinema id
    List<MovieCinema> countAllByCinemaId(Integer id);

    //Write a derived query to list all movie cinemas with higher than a specific date
    List<MovieCinema> findMovieCinemaByDateTimeGreaterThan(LocalDateTime dateTime);

    //Write a derived query to find the top 3 expensive movies
    List<MovieCinema> findFirst3ByOrderByMovie_Price();

    //Write a derived query to list all movie cinemas that contain a specific movie name
    List<MovieCinema> findMovieCinemaByMovieContaining(String name);

    //Write a derived query to list all movie cinemas in a specific location
    List<MovieCinema> findMovieCinemaByCinema_Location_Name(String location);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movie cinemas with higher than a specific date
    @Query(value = "select mc from MovieCinema mc where mc.date_time > ?1", nativeQuery = true)
    List<MovieCinema> findAllByDateTimeGreaterThan(LocalDateTime dateTime);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count all movie cinemas by cinema id
    @Query(value = "select * from moview_cinema where cinema_id = ?1", nativeQuery = true)
    List<MovieCinema> retrieveAllBasedOnCinemaId(Integer id);

    //Write a native query that returns all movie cinemas by location name
    @Query(value = "select * from movie_cinema m join cinema c on m.id = c.cinema_id join location l on c.id = l.location_id where l.name = ?1", nativeQuery = true)
    List<MovieCinema> retrieveAllBasedOnLocationName(String name);
}
