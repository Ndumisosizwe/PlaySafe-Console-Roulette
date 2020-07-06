package com.playsafeholding.assessment.consoleroulette.domain;

import com.playsafeholding.assessment.consoleroulette.value.BetValue;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class NumberBet<Integer> extends Bet<Integer> {

    private BetValue<Integer> betValue;

    public NumberBet(BetValue<Integer> betValue, BigDecimal betAmount) {
        super(betAmount);
        this.betValue = betValue;
    }

    @Override
    public String toString() {
        return "NumberBet{" +
                "betValue=" + betValue +
                ", betAmount=" + betAmount +
                '}';
    }
}
