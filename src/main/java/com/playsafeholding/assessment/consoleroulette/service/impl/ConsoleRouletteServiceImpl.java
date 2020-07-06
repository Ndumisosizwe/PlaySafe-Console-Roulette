package com.playsafeholding.assessment.consoleroulette.service.impl;

import com.playsafeholding.assessment.consoleroulette.service.ConsoleRouletteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsoleRouletteServiceImpl implements ConsoleRouletteService, CommandLineRunner {

    private final ResourceLoader resourceLoader;

    private static List<String> PLAYER_NAMES;

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
        log.info("Player names read from file : {}", PLAYER_NAMES);
    }

    @Override
    public List<String> getPlayerNames() {
        return PLAYER_NAMES;
    }
}
