package com.cydeo.bootstrap;

import com.cydeo.repository.AccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataGenerator implements CommandLineRunner {

    // dependency injection
//    private final AccountRepo accountRepo;


    @Override
    public void run(String... args) throws Exception {

        // ------------------- Account DERIVED QUERIES ------------------- //


        // ------------------- Account JPQL QUERIES ------------------- //


        // ------------------- Account Native QUERIES ------------------- //



    }



}
