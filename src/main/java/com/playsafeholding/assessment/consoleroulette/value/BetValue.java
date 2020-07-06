package com.playsafeholding.assessment.consoleroulette.value;

public class BetValue<T> {

    private T value;

    public BetValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
