package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.VendingItems;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 * 
 * Dependencies: None
 */
public class UserOutput
{

    public static void displayMessage(String message)
    {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void displayHomeScreen()
    {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayPurchaseScreen(){
        {
            System.out.println();
            System.out.println("***************************************************");
            System.out.println("                      Purchase");
            System.out.println("***************************************************");
            System.out.println();
            System.out.println("Now running Buy One Get One for a dollar off special!");
        }
    }

    public static void displayList(List<VendingItems> list){
        String itemName = "";
        BigDecimal itemPrice = new BigDecimal("0");
        String itemType = "";
        String slotLocation = "";
        String menu = "";
        for (int i = 0; i < list.size(); i++) {
            VendingItems item = list.get(i);
            /*
            itemName = item.getItemName();
            itemPrice = item.getPrice();
            itemType =item.getType();
            slotLocation = item.getSlotLocation();
            menu += "Slot location: " + slotLocation + " Item Name: " + itemName + " Price: " + itemPrice + " Type: " + itemType + "\n";*/
            System.out.printf("%-4s %-20s $%-6.2f %-8s\n", item.getSlotLocation(), item.getItemName(), item.getPrice(), item.getType());
        }
//        System.out.println(menu);
    }
}
