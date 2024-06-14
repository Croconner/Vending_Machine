package com.techelevator.models;

import java.math.BigDecimal;

public class Gum extends VendingItems{

    public Gum(String slotLocation, String itemName, BigDecimal price) {
        super(slotLocation, itemName, price, "Gum");
    }

    @Override
    public String toString() {
        return "Chewy, Chewy, Lots O Bubbles!";
    }
}
