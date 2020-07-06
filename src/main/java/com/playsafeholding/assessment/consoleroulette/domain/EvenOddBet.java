package com.playsafeholding.assessment.consoleroulette.domain;

import com.playsafeholding.assessment.consoleroulette.value.BetValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EvenOddBet<EvenOddBetEnum> extends Bet<EvenOddBetEnum> {

    private BetValue<EvenOddBetEnum> betValue;

    public EvenOddBet(BetValue<EvenOddBetEnum> betValue, BigDecimal betAmount) {
        super(betAmount);
        this.betValue = betValue;
    }

    @Override
    public String toString() {
        return "EvenOddBet{" +
                "betValue=" + betValue +
                ", betAmount=" + betAmount +
                '}';
    }
}
