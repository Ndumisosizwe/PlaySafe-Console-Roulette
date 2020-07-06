package com.playsafeholding.assessment.consoleroulette.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Bet<T> {

    protected BigDecimal betAmount;
}
