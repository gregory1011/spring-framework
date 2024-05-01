package com.cydeo.bootstrap;

import com.cydeo.entity.Payment;
import com.cydeo.repository.PaymentRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataGenerator implements CommandLineRunner {


    // inject dependency
    private final PaymentRepo paymentRepo;


    @Override
    public void run(String... args) throws Exception {

    }


}
