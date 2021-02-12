package com.bikram.casestudy.model;

/**
 * @author Bikram Das
 *
 * Enity Class for Products
 */

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private Price current_price;

    //Getters and Setters
    public Price getCurrent_price() {
        return current_price;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrent_price(Price current_price) {
        this.current_price = current_price;
    }

    //Constructors
    public Product() {
    }

    public Product(String id, String name, Price current_price) {
        this.id = id;
        this.name = name;
        this.current_price = current_price;
    }
}
