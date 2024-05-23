package com.cydeo.repository;

import com.cydeo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {


    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to count how many tickets a user bought
    Integer countAllByUserAccountId(Long id);

    //Write a derived query to list all tickets by specific email
    List<Ticket> readTicketByUserAccount_Email(String email);

    //Write a derived query to count how many tickets are sold for a specific movie
    Integer countAllByMovieCinema_Movie_Name(String name);

    //Write a derived query to list all tickets between a range of dates
    List<Ticket> findTicketByDateTimeBetween(LocalDateTime dateTime1, LocalDateTime dateTime2);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all tickets are bought from a specific user
    @Query("select t from Ticket t where t.userAccount.id= ?1")
    List<Ticket> findTicketsByUserAccount(Long id);

    //Write a JPQL query that returns all tickets between a range of dates
    @Query("select t from Ticket t where t.dateTime between ?1 and ?2")
    List<Ticket> readTicketByDateTimeBetween(LocalDateTime dateTime1, LocalDateTime dateTime2);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count the number of tickets a user bought
    @Query(value = "SELECT COUNT(*) FROM ticket WHERE user_account_id = ?1",nativeQuery = true)
    Integer countAllTicketsByUserAccount(Long userId);

    //Write a native query to count the number of tickets a user bought in a specific range of dates
    @Query(value = "SELECT COUNT(*) FROM ticket WHERE user_account_id = ?1 AND date_time BETWEEN ?2 AND ?3",nativeQuery = true)
    Integer countTicketByUserInDateRange(Long userId, LocalDateTime dateTime1, LocalDateTime dateTime2);

    //Write a native query to distinct all tickets by movie name
    @Query(value = "", nativeQuery = true)
    List<Ticket> readDistinctByMovieCinema_MovieName(String movie);

    //Write a native query to find all tickets by user email
    @Query(value = "", nativeQuery = true)
    List<Ticket> readTicketsByUserAccount_Email(String email);

    //Write a native query that returns all tickets
    @Query(value = "select * from Ticket",nativeQuery = true)
    List<Ticket> readAllBy();

    //Write a native query to list all tickets where a specific value should be containable in the username or name or movie name
    @Query(value = "SELECT * FROM ticket t JOIN user_account ua ON t.user_account_id = ua.id " +
            " JOIN account_details ad ON ua.account_details_id = ad.id JOIN movie_cinema mc " +
            " ON t.movie_cinema_id = mc.id JOIN movie m ON mc.movie_id = m.id " +
            " WHERE ua.username ILIKE concat('%',?1,'%') OR ad.name ILIKE concat('%',?1,'%') OR " +
            " m.name ILIKE concat('%',?1,'%') ",nativeQuery = true)
    List<Ticket> retrieveAllBySearchCriteria(String keyword);




}
