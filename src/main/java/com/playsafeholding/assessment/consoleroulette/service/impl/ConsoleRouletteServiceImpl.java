package com.playsafeholding.assessment.consoleroulette.service.impl;

import com.playsafeholding.assessment.consoleroulette.domain.EvenOddBet;
import com.playsafeholding.assessment.consoleroulette.domain.NumberBet;
import com.playsafeholding.assessment.consoleroulette.domain.Player;
import com.playsafeholding.assessment.consoleroulette.service.ConsoleRouletteService;
import com.playsafeholding.assessment.consoleroulette.value.BetValue;
import com.playsafeholding.assessment.consoleroulette.value.EvenOddBetEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.playsafeholding.assessment.consoleroulette.value.EvenOddBetEnum.valueOf;

@Service
@Slf4j
@Scope("singleton")
public class ConsoleRouletteServiceImpl implements ConsoleRouletteService, CommandLineRunner {

    private final ResourceLoader resourceLoader;

    private static List<String> PLAYER_NAMES;

    private static int winningNumber;

    private static final Map<String, Player> PLAYERS = new HashMap<>();

    @Value("${number-of-times-to-play}")
    private String numberOfTimeToPlay;

    @Value("${player-name.file.location}")
    private String playerNameFileLocation;

    public ConsoleRouletteServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing player names");
        initializeAndReadPlayerNames(playerNameFileLocation);
    }

    @Override
    public void initializeAndReadPlayerNames(String fileLocation) throws Exception {
        Resource resource = resourceLoader.getResource(fileLocation);
        log.info("Reading player names from file : {}", resource.getFilename());
        PLAYER_NAMES = Files.lines(resource.getFile().toPath())
                .collect(Collectors.toList());
        log.info("Player names initialized from file : {}", PLAYER_NAMES);
        PLAYER_NAMES.forEach(n -> PLAYERS.put(n, Player.builder()
                .bets(new HashSet<>())
                .name(n)
                .build())
        );
        Scanner scanner = new Scanner(System.in);
        IntStream.rangeClosed(1, 5).forEach(value -> {
            System.out.println("\n Please enter 3 params, in a single line in this format [YourName] [YourBetValue (ODD OR EVEN)] [BetAmount]");
            System.out.print("Input : ");
            String[] inputLine = scanner.nextLine().split(" ");
            validatePlayerInput(inputLine);
        });

    }

    @Override
    public List<String> getPlayerNames() {
        return PLAYER_NAMES;
    }

    private void validatePlayerInput(String[] inputString) {
        String playerName = inputString[0];
        if (!PLAYER_NAMES.contains(playerName))
            throw new RuntimeException("Invalid Player Name. Specified player name does not exist on File");
        String betType = inputString[1];
        if (this.getPlayerNames().contains(playerName)) {
            placeBetForPlayer(inputString[2], playerName, betType);
        }
    }

    /**
     * Places bets for each player. Each player can have one or more bets.
     *
     * @param inputBetAmount - The input amount player is willing to bet with
     * @param playerName     - The player's name
     * @param betType        - Is it a number in the range (1-36) or is it ODD or EVEN.
     */
    private void placeBetForPlayer(String inputBetAmount, String playerName, String betType) {
        if (betType.matches("[0-9]+")) {
            int inputBetNumber = Integer.parseInt(betType);
            if (inputBetNumber <= 36 && inputBetNumber >= 0) {
                PLAYERS.get(playerName)
                        .getBets()
                        .add(new NumberBet<>(new BetValue<>(inputBetNumber), new BigDecimal(inputBetAmount)));
                logPlayerAndTheirBets(PLAYERS.get(playerName));
            }
        } else {
            if (valueOf("ODD").toString().equalsIgnoreCase(betType)) {
                PLAYERS.get(playerName)
                        .getBets()
                        .add(new EvenOddBet<>(new BetValue<>(EvenOddBetEnum.ODD), new BigDecimal(inputBetAmount)));
                logPlayerAndTheirBets(PLAYERS.get(playerName));
            } else if (valueOf("EVEN").toString().equalsIgnoreCase(betType)) {
                PLAYERS.get(playerName)
                        .getBets()
                        .add(new EvenOddBet<>(new BetValue<>(EvenOddBetEnum.EVEN), new BigDecimal(inputBetAmount)));
                logPlayerAndTheirBets(PLAYERS.get(playerName));
            }
        }
    }

    @Scheduled(cron = "${random-number.interval}")
    public void generatesRandomNumberFrom0To36() {
        Random random = new Random();
        IntStream ints = random.ints(0, (36 + 1));
        ints.forEach(System.out::println);
        winningNumber = ints.findFirst().getAsInt();
        log.info("New number generated ... {} ", winningNumber);
    }

    public void logPlayerAndTheirBets(Player player) {
        log.info("Current player and their bets : {}", player);
    }
}
