package com.cydeo.bootstrap;

import com.cydeo.entity.Genre;
import com.cydeo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class DataGenerator implements CommandLineRunner {

    // dependency injection
    private final AccountRepo accountRepo;
    private final CinemaRepo cinemaRepo;
    private final GenreRepo genreRepo;
    private final MovieCinemaRepo movieCinemaRepo;
    private final MovieRepo movieRepo;
    private final TicketRepo ticketRepo;
    private final UserRepo userRepo;


    @Override
    public void run(String... args) throws Exception {

        // ------------------- Account DERIVED QUERIES ------------------- //


        // accountRepo.findByAgeLessThanEqual(40).forEach(System.out::println);
        // ------------------- Account JPQL QUERIES ------------------- //


        // ------------------- Account Native QUERIES ------------------- //

// instructor code below to validate queries:

        System.out.println(cinemaRepo.findCinemaByName("Hall 1 - EMPIRE"));
        System.out.println(accountRepo.findAdminAccounts());
        System.out.println(cinemaRepo.retrieveDistinctCinemasBySponsoredName());
        System.out.println(movieRepo.retrieveMoviesByName("The Gentleman"));
        System.out.println(movieCinemaRepo.countAllByCinemaId(4L));
        System.out.println(movieCinemaRepo.findMovieCinemaByCinema_Location_Name("AMC Empire 25"));
        System.out.println(ticketRepo.findTicketsByUserAccountId(4L));
        System.out.println(ticketRepo.readTicketByDateTimeBetween(LocalDateTime.now().minusDays(2000), LocalDateTime.now()));
        System.out.println(genreRepo.retrieveAll());
        System.out.println(userRepo.fetchAllUsers());
        System.out.println(userRepo.findAllByEmail("bernard@email.com"));
        System.out.println(" = ------- |||||||||||||||||||| --------");
         System.out.println(ticketRepo.retrieveAllBySearchCriteria("it"));


    }



}
