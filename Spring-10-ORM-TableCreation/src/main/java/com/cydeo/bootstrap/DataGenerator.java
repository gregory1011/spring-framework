package com.cydeo.bootstrap;

import com.cydeo.entity.Car;
import com.cydeo.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {

    private final CarRepository carRepository;

    public DataGenerator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Car car1 = new Car("BMW", "M5");
        Car car2 = new Car("Audi", "A6");
        Car car3 = new Car("Honda", "Civic");

        // save these objects to DB
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
    }

}
