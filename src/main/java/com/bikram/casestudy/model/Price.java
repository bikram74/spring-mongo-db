package com.bikram.casestudy.model;

import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

/**
 * @author Bikram Das
 *
 * Enity Class for Price
 */
public class Price implements Serializable {
    private static final long serialVersionUID = 2L;

    private Double value;
    private String currency_code;

    @PositiveOrZero
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public Price() {
    }

    public Price(Double value, String currency_code) {
        this.value = value;
        this.currency_code = currency_code;
    }
}
