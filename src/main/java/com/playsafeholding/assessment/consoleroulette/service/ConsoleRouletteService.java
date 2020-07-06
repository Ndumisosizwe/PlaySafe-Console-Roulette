package com.playsafeholding.assessment.consoleroulette.service;

import java.util.List;

/**
 * Service responsible for Player transactions and Input file(player-names) processing, placing bets etc..
 *
 * @author Ndumiso
 */

public interface ConsoleRouletteService {

    /**
     * Should initialize players by reading lines from an input file (with player names) from the classpath.
     * The player initialization must be done on application start up (before the ConsoleRoulette game begins).
     *
     * @param fileLocation - The path to the input file. The file may be of any extension
     */
    void initializeAndReadPlayerNames(String fileLocation) throws Exception;

    /**
     * Returns the entire list of player names read from the input file.
     * @return a list of player names
     */
    List<String> getPlayerNames();


}
