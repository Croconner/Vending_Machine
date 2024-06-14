package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.audit.AuditFile;
import com.techelevator.models.VendingItems;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput{
    private static Scanner scanner = new Scanner(System.in);

    public static String getHomeScreenOption()
    {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Vending Machine Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toUpperCase();

        if (option.equals("D"))
        {
            return "display";
        }
        else if (option.equals("P"))
        {
            return "purchase";
        }
        else if (option.equals("E"))
        {
            return "exit";
        }
        else
        {
            return "";
        }

    }

    public static String getPurchaseScreenOption(){
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("M) Feed Money");
        System.out.println("S) Select Item");
        System.out.println("F) Finish Transaction");
        System.out.println("Balance is: " + VendingMachine.getWallet());

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toUpperCase();

        if (option.equals("M"))
        {
            return "money";
        }
        else if (option.equals("S"))
        {
            return "select";
        }
        else if (option.equals("F"))
        {
            return "finish";
        }
        else
        {
            return "";
        }
    }

    public static BigDecimal displayfeedMoney(){
        // ask how much to add int dollar amounts
       // System.out.print("How much money do you want to add? ");
        UserOutput.displayMessage("How much money do you want to add?");
        String dollarAmount = scanner.nextLine();
        BigDecimal loadAmount = new BigDecimal("0.00");
        try{
            loadAmount = new BigDecimal(dollarAmount);
        }catch(NumberFormatException e){
            UserOutput.displayMessage("Input needs to be in decimal form");
        }
        return loadAmount;
    }

    public static String getSelectItem(List<VendingItems> list) {
        UserOutput.displayList(list);
        UserOutput.displayMessage("Which is the item location? ");
      // System.out.print("Which is the item location? ");
        String slotLocal = scanner.nextLine();
        String slotLocalUpper = slotLocal.toUpperCase();
        return slotLocalUpper;
    }
}
