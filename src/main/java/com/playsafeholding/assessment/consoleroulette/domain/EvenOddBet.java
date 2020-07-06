package com.playsafeholding.assessment.consoleroulette.domain;

import com.playsafeholding.assessment.consoleroulette.value.BetValue;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
public class EvenOddBet<EvenOddBetEnum> extends Bet<EvenOddBetEnum> {

    private BetValue<EvenOddBetEnum> betValue;
}
