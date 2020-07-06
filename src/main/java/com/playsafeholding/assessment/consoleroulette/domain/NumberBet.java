package com.playsafeholding.assessment.consoleroulette.domain;

import com.playsafeholding.assessment.consoleroulette.value.BetValue;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
public class NumberBet<Integer> extends Bet<Integer> {

    private BetValue<Integer> betValue;
}
