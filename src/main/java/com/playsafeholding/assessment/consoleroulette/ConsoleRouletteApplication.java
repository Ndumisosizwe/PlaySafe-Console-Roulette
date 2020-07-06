package com.playsafeholding.assessment.consoleroulette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
@EnableScheduling
public class ConsoleRouletteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleRouletteApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 3 params, in a single line in this format [YourName] [YourBetValue (ODD OR EVEN)] [BetAmount]");
        System.out.print("Input : ");
        String[] inputLine = scanner.nextLine().split(" ");
        System.out.println(Arrays.toString(inputLine));
    }

    public static void validatePlayerInput(String[] inputString){

    }
}
