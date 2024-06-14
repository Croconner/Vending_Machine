package com.techelevator.application;

import com.techelevator.audit.AuditFile;
import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class VendingMachine {
    private AuditFile auditFile = new AuditFile("auditFile.txt");
    private List<VendingItems> vendingItems = readFile();
    private static BigDecimal wallet = new BigDecimal("0.00");
    private int count = 1;

    public static BigDecimal getWallet() {
        return wallet;
    }

    public void run() {

        while (true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if (choice.equals("display")) {
                UserOutput.displayList(vendingItems);
            } else if (choice.equals("purchase")) {
                purchaseMenu(vendingItems);
            } else if (choice.equals("exit")) {
                // good bye
                break;
            }
        }
    }

    public List<VendingItems> readFile() {
        List<VendingItems> list = new ArrayList<>();
        File file = new File("catering.csv");
        if (!file.exists()) {
           // System.out.println("Error reading file");
            UserOutput.displayMessage("Error reading from file");
            System.exit(1);
        }
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] splitLine = line.split(",");
                String slotlocation = splitLine[0];
                String name = splitLine[1];
                String type = splitLine[3];
                String price = splitLine[2];
                if (type.equals("Candy")) {
                    list.add(new Candy(slotlocation, name, new BigDecimal(price)));
                } else if (type.equals("Drink")) {
                    list.add(new Drink(slotlocation, name, new BigDecimal(price)));
                } else if (type.equals("Gum")) {
                    list.add(new Gum(slotlocation, name, new BigDecimal(price)));
                } else if (type.equals("Munchy")) {
                    list.add(new Munchy(slotlocation, name, new BigDecimal(price)));
                }
            }
        } catch (FileNotFoundException e) {
            //System.out.println("Can't read from file");
            UserOutput.displayMessage("Can't read from file");

        }
        return list;
    }

    public void purchaseMenu(List<VendingItems> vendingItems) {

        while (true) {
            UserOutput.displayPurchaseScreen();
            String choice2 = UserInput.getPurchaseScreenOption();

            if (choice2.equals("money")) {
                feedMoney(UserInput.displayfeedMoney());
                // ask how much to add int dollar amounts
                // add to total amount added
                // write to audit file
                // display purchase menu again
            } else if (choice2.equals("select")) {
                selectItem(vendingItems, UserInput.getSelectItem(readFile()));
                // ask for slot number
                // reduce inventory by 1
                // subtract price of item from total
                // write to audit file
                // display purchase menu again
            } else if (choice2.equals("finish")) {
                String output = finishTransaction();
                UserOutput.displayMessage(output);
                break;
                // give change
                // write to audit file
                // go back to main menu
            }
        }
    }

    public BigDecimal feedMoney(BigDecimal loadAmount) {
        // ask how much to add int dollar amounts
   //     BigDecimal loadAmount = UserInput.displayfeedMoney();
        //     BigDecimal wallet = new BigDecimal("0");
        // add to total amount added

       if (loadAmount.equals(new BigDecimal("1")) || loadAmount.equals(new BigDecimal("5")) || loadAmount.equals(new BigDecimal("10")) || loadAmount.equals(new BigDecimal("20"))
       || loadAmount.equals(new BigDecimal("1.00")) || loadAmount.equals(new BigDecimal("5.00")) || loadAmount.equals(new BigDecimal("10.00")) || loadAmount.equals(new BigDecimal("20.00"))) {
            wallet = wallet.add(loadAmount);
            UserOutput.displayMessage(loadAmount + " was added to wallet!");
      //      System.out.println(loadAmount + " was added to wallet!");
        } else {
            UserOutput.displayMessage("Unable to load money, only valid dollar amounts taken");
      //      System.out.println("Unable to load money, only valid dollar amounts taken");
        }
        // write to audit file

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        String dateString = dateFormat.format(new Date()).toString();
        String moneyFed = "MONEY FED: ";
        String formatedString = String.format("%-20s %6.2f %7.2f", moneyFed, loadAmount, wallet);
        auditFile.write(dateString + " " + formatedString);
        // display purchase menu again
        return wallet;
    }

    public String selectItem(List<VendingItems> list, String slotLocation) {
        BigDecimal startingBalance = VendingMachine.getWallet();

        for (int i = 0; i < list.size(); i++) {
            VendingItems item = list.get(i);
            if (count % 2 == 0) {
                item.setPrice(item.getPrice().subtract(new BigDecimal("1")));
            }
            if (!(count % 2 == 0) && (count != 1)) {
                item.setPrice(item.getPrice().add(new BigDecimal("1")));
            }
        }
     //   String slotLocalUpper = UserInput.getSelectItem(list);
        String formattedString = "";
        String outputItem = "";

        for (int i = 0; i < list.size(); i++) {
            VendingItems item = list.get(i);
            String slot = item.getSlotLocation();
            int walletStatus = VendingMachine.getWallet().compareTo(item.getPrice());

            if (slot.equals(slotLocation) && item.getQuantity() > 0 && walletStatus >= 0) {
                item.setQuantity(item.getQuantity() - 1);
                wallet = wallet.subtract(item.getPrice());
               // System.out.println("Purchase of " + item.getItemName() + " " + item + " complete!");
                UserOutput.displayMessage("Purchase of " + item.getItemName() + " " + item + " complete!");
                count++;
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
                String dateString = dateFormat.format(new Date()).toString();
                formattedString = String.format(" %-17s %2s %6.2f  %6.2f", item.getItemName(), item.getSlotLocation(), startingBalance, wallet);
                auditFile.write(dateString + formattedString);
                outputItem = item.getItemName() + " " + item;

            } else if (slot.equals(slotLocation) && item.getQuantity() == 0) {
                outputItem = "Item is sold out!";
                UserOutput.displayMessage(outputItem);
            } else if (slot.equals(slotLocation) && walletStatus == -1) {
                outputItem = "Not enough funds for " + item.getItemName();
                UserOutput.displayMessage(outputItem);
            }

        } return outputItem;
    }
        public String finishTransaction(){
            // give change
            BigDecimal change = wallet;
            int walletStatus = wallet.compareTo(new BigDecimal("1.00"));
            int dollarCounter = 0;
            int quarterCounter = 0;
            int dimeCounter = 0;
            int nickelCounter = 0;
            count = 1;


            while (walletStatus >= 0){
                wallet = wallet.subtract(new BigDecimal("1"));
                dollarCounter++;
                walletStatus = wallet.compareTo(new BigDecimal("1.00"));
            }
            walletStatus = wallet.compareTo(new BigDecimal(".25"));
            while (walletStatus >= 0){
                wallet = wallet.subtract(new BigDecimal(".25"));
                quarterCounter ++;
                walletStatus = wallet.compareTo(new BigDecimal("0.25"));
            }
            walletStatus = wallet.compareTo(new BigDecimal(".10"));
            while (walletStatus >= 0){
                wallet = wallet.subtract(new BigDecimal(".10"));
                dimeCounter ++;
                walletStatus = wallet.compareTo(new BigDecimal("0.10"));
            }
            walletStatus = wallet.compareTo(new BigDecimal(".05"));
            while (walletStatus >= 0){
                wallet = wallet.subtract(new BigDecimal(".05"));
                nickelCounter ++;
                walletStatus = wallet.compareTo(new BigDecimal("0.05"));
            }
            wallet = new BigDecimal("0.00");

            String output = ("Returned " + dollarCounter + " dollars " + quarterCounter + " quarters " + dimeCounter + " dimes " +
                    nickelCounter + " nickels");


            // write to audit file
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
            String dateString = dateFormat.format(new Date()).toString();
            String changeGiven = " CHANGE GIVEN: ";
            String formatedString = String.format("%-21s %6.2f %7.2f",changeGiven, change, wallet);
            auditFile.write(dateString + formatedString);

            // go back to main menu
            return output;
        }
    }


