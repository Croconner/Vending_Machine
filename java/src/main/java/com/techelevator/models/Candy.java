package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends VendingItems{

    public Candy(String slotLocation, String itemName, BigDecimal price) {
       super(slotLocation, itemName, price, "Candy");
    }


    @Override
    public String toString() {
        return "Sugar, Sugar, so Sweet";
    }
}
