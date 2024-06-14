package com.techelevator.models;

import java.math.BigDecimal;

public abstract class VendingItems {
    private String slotLocation;
    private String itemName;
    private BigDecimal price;
    private String type;
    private int quantity = 6;

    public VendingItems(String slotLocation, String itemName, BigDecimal price, String type) {
        this.slotLocation = slotLocation;
        this.itemName = itemName;
        this.price = price;
        this.type = type;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
