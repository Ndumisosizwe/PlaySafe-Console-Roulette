package com.playsafeholding.assessment.consoleroulette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConsoleRouletteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleRouletteApplication.class, args);
    }
}
