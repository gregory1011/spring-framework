package com.cydeo.bootstrap;

import com.cydeo.entity.Genre;
import com.cydeo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

//        List<Genre> comedy = genreRepo.getGenreByName("Comedy");
//        System.out.println("comedy = " + comedy);

            List<Genre> genreById = genreRepo.getGenreById(1);
            System.out.println("genreById = " + genreById);


        // accountRepo.findByAgeLessThanEqual(40).forEach(System.out::println);
        // ------------------- Account JPQL QUERIES ------------------- //


        // ------------------- Account Native QUERIES ------------------- //



    }



}
