package com.playsafeholding.assessment.consoleroulette.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class Bet<T> {

    protected BigDecimal betAmount;
}
