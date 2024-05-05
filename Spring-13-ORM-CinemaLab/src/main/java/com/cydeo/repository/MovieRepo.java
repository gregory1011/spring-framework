package com.cydeo.repository;

import com.cydeo.entity.Movie;
import com.cydeo.enums.MovieState;
import com.cydeo.enums.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a movie with a name
    List<Movie> readMovieByName(String name);

    //Write a derived query to list all movies between a range of prices
    List<Movie> readMovieByDurationBetween(int duration1, int duration2);

    //Write a derived query to list all movies where duration exists in the specific list of duration
    List<Movie> readMovieByDuration(int duration);

    //Write a derived query to list all movies with higher than a specific release date
    List<Movie> readMovieByReleaseDateGreaterThan(LocalDate date);

    //Write a derived query to list all movies with a specific state and type
    List<Movie> readMovieByStateAndAndType(MovieState state, MovieType type);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movies between a range of prices
    @Query("select m from Movie m where m.price between ?1 and ?2")
    List<Movie> readMovieByPriceBetween(int price1, int price2);

    //Write a JPQL query that returns all movie names
    @Query("select m from Movie m")
    List<Movie> readMovieByName();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns a movie by name
    @Query(value = "select * from Movie where name = ?1", nativeQuery = true)
    List<Movie> readMoviesByName(String name);

    //Write a native query that return the list of movies in a specific range of prices
    @Query(value = "select * from Movie where price between ?1 and ?2", nativeQuery = true)
    List<Movie> readMoviesByPriceBetween(int price1, int price2);

    //Write a native query to return all movies where duration exists in the range of duration
    @Query(value = "select * from movie where duration between ?1 and ?2", nativeQuery = true)
    List<Movie> readMoviesByDurationBetween(int duration1, int duration2);

    //Write a native query to list the top 5 most expensive movies
    @Query(value = "select * from movie where price > ?1 order by price asc", nativeQuery = true)
    List<Movie> readTop5MovieByPriceOrderByPriceAsc(int price);
}
