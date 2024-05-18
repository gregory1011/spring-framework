package com.cydeo.repository;

import com.cydeo.entity.Movie;
import com.cydeo.enums.MovieState;
import com.cydeo.enums.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a movie with a name
    List<Movie> findAllByName(String name);

    //Write a derived query to list all movies between a range of prices
    List<Movie> findMoviesByPriceBetween(int price1, int price2);

    //Write a derived query to list all movies where duration exists in the specific list of duration
    List<Movie> findMovieByDuration(int duration);

    //Write a derived query to list all movies with higher than a specific release date
    List<Movie> findMovieByReleaseDateGreaterThan(LocalDate date);

    //Write a derived query to list all movies with a specific state and type
    List<Movie> findMovieByStateAndType(MovieState state, MovieType type);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movies between a range of prices
    @Query("select m from Movie m where m.price between ?1 and ?2")
    List<Movie> getMoviesByPriceBetween(int price1, int price2);

    //Write a JPQL query that returns all movie names
    @Query("select m.name from Movie m")
    List<Movie> findMoviesByName();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns a movie by name
    @Query(value = "select * from movie where name = ?1", nativeQuery = true)
    List<Movie> retrieveMoviesByName(String name);

    //Write a native query that return the list of movies in a specific range of prices
    @Query(value = "select * from movie where price between ?1 and ?2", nativeQuery = true)
    List<Movie> retrieveMoviesByPriceBetween(int price1, int price2);

    //Write a native query to return all movies where duration exists in the range of duration
    @Query(value = "select * from movie where duration between ?1 and ?2", nativeQuery = true)
    List<Movie> readMoviesByDurationBetween(int duration1, int duration2);

    //Write a native query to list the top 5 most expensive movies
    @Query(value = "select * from movie order by price asc limit 6", nativeQuery = true)
    List<Movie> retrieveTop5MoviesByPriceAsc();
}
