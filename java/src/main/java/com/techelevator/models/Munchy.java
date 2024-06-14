package com.techelevator.models;

import java.math.BigDecimal;

public class Munchy extends VendingItems {

    public Munchy(String slotLocation, String itemName, BigDecimal price) {
        super(slotLocation, itemName, price, "Munchy");
    }

    @Override
    public String toString() {
        return "Munchy, Munchy, so Good!";
    }
}
