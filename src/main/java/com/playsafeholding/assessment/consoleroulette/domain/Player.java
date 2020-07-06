package com.playsafeholding.assessment.consoleroulette.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Player {

    @EqualsAndHashCode.Include
    private String name;

    private Set<Bet<?>> bets;

    private int numberOfWins;

    private BigDecimal totalAmountWonSoFar;
}
