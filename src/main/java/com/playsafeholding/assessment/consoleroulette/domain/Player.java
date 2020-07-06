package com.playsafeholding.assessment.consoleroulette.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Player {

    private String name;
    private Set<Bet<?>> bets;
}
