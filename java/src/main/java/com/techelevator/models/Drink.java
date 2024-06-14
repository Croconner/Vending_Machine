package com.techelevator.models;

import java.math.BigDecimal;

public class Drink extends VendingItems {

    public Drink(String slotLocation, String itemName, BigDecimal price) {
        super(slotLocation, itemName, price, "Drink");
    }


    @Override
    public String toString() {
        return "Drinky, Drinky, Slurp Slurp!";
    }
}
