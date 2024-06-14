package com.techelevator.application;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineTest {
    VendingMachine vendingMachine = new VendingMachine();

    @After
    public void close(){
        vendingMachine.finishTransaction();
    }

    @Test
    public void test_feedMoney_input_5_return_5(){
        BigDecimal expected = new BigDecimal("5.00");
        BigDecimal actual = vendingMachine.feedMoney(new BigDecimal("5.00"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_feedMoney_input_10_return_10(){
        BigDecimal expected = new BigDecimal("10.00");
        BigDecimal actual = vendingMachine.feedMoney(new BigDecimal("10"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_feedMoney_input_0_return_0(){
        BigDecimal expected = new BigDecimal("0.00");
        BigDecimal actual = vendingMachine.feedMoney(new BigDecimal("0.00"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_feedMoney_input_7_return_0(){
        BigDecimal expected = new BigDecimal("0.00");
        BigDecimal actual = vendingMachine.feedMoney(new BigDecimal("7"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_selectItem_input_d4_return_Chiclets(){
        vendingMachine.feedMoney(new BigDecimal("20"));

        String expected = "Chiclets Chewy, Chewy, Lots O Bubbles!";
        String actual = vendingMachine.selectItem(vendingMachine.readFile(), "D4");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_selectItem_input_A1_return_UChews(){
        vendingMachine.feedMoney(new BigDecimal("20"));

        String expected = "U-Chews Chewy, Chewy, Lots O Bubbles!";
        String actual = vendingMachine.selectItem(vendingMachine.readFile(), "A1");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_selectItem_input_B1_return_not_enough_funds(){
        vendingMachine.feedMoney(new BigDecimal("0"));

        String expected = "Not enough funds for Stackers";
        String actual = vendingMachine.selectItem(vendingMachine.readFile(), "B1");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_finishTransaction_input_5_return_5_dollars(){
        vendingMachine.feedMoney(new BigDecimal("5"));

        String expected = "Returned 5 dollars 0 quarters 0 dimes 0 nickels";
        String actual = vendingMachine.finishTransaction();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void test_finishTransaction_input_5_a1_return_335(){
        vendingMachine.feedMoney(new BigDecimal("5"));
        vendingMachine.selectItem(vendingMachine.readFile(), "A1");
        String expected = "Returned 3 dollars 1 quarters 1 dimes 0 nickels";
        String actual = vendingMachine.finishTransaction();

        Assert.assertEquals(expected, actual);
    }
}