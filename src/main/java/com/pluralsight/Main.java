package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Transaction> real_arrayList2 = Ledger.real_arrayList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Starter screen for Coffee shop called "JAVA BREW" - CHALLENGE YOURSELF FEATURE
        System.out.println("""
            **********************************************
            *                                              *
            *          ☕ WELCOME TO JAVABREW LEDGER ☕     *
            *     Your Coffee Sales & Expense Tracker      *
            *                                              *
            **********************************************

            Loading system... please wait :)
            """);

        //give it a slow loading effect
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("Error");
        }


        String mainMenu = """
                ------ Home Screen ------
                 D) Add Coffee Sale
                 P) Record Bean Purchase
                 L) Ledger
                 X) Exit
                """;

        String command = " ";
        while (!command.equalsIgnoreCase("X")) {
            System.out.print(mainMenu);

            command = ConsoleHelper.promptForString("Choose an option: ");

            switch (command.toUpperCase()) {
                //Allows users to add a deposit - sale
                case "D":
                case "DEPOSIT":
                case "DEPOSITS":
                    System.out.println("Adding new coffee sale.");
                    addDeposit();
                    break;

                //Allows users to make a payment
                case "P":
                case "PAYMENT":
                case "PAYMENTS":
                    System.out.println("Recording bean purchase.");
                    addPayment();
                    break;

                //Displays the Ledger Home Screen
                case "L":
                case "LEDGER":
                    Ledger.showLedgerMenu();
                    break;

                //Exits - closes the program
                case "X":
                case "EXIT":
                    //CHALLENGE YOURSELF FEATURE
                    System.out.println("""
                        **************************************
                        *   Thank you for using JavaBrew! ☕  *
                        *   Keep brewing and tracking smart!  *
                        **************************************
                        """);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        //closes the scanner automatically
        scanner.close();
    }


    public static void addPayment(){
        LocalDate date = ConsoleHelper.promptForLocalDate("Enter the date of Purchase");
        LocalTime time = ConsoleHelper.promptForLocalTime("Enter the time of Purchase");
        String item_description = ConsoleHelper.promptForString("Item Purchased - Milk, Beans, Chocolate, etc: ");
        String vendor_supplier = ConsoleHelper.promptForString("Vendor/Supplier name: ");
        double amount = ConsoleHelper.promptForDouble("Total Purchase amount: ");


        //make sure to store any payment as a negative
        amount = -Math.abs(amount);

        Transaction payment_transaction = new Transaction(date, time, item_description, vendor_supplier, amount);

        Ledger.real_arrayList.add(payment_transaction);

        //save new transaction to file
        try {
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //writing to the csv file
            bufferedWriter.write(date + "|" + time + "|" + item_description + "|" + vendor_supplier + "|" + amount);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

            //make sure to add confirmation of payment being added
            System.out.println("Your Payment has been added successfully! Payment amount: " + amount + " to " + vendor_supplier +
                    " on " + date + " at " + time);

        } catch (Exception e) {
            System.out.println("Error - your payment has not been added. Please try again!");
        }

    }


    public static void addDeposit(){
        LocalDate date = ConsoleHelper.promptForLocalDate("Enter the date of sale");
        LocalTime time = ConsoleHelper.promptForLocalTime("Enter the time of sale");
        String item_description = ConsoleHelper.promptForString("Drink or item sold: ");
        String vendor_customer = ConsoleHelper.promptForString("Enter the vendor/Customer name: ");
        double amount = ConsoleHelper.promptForDouble("Total sale amount: ");

        Transaction deposit_transaction = new Transaction(date, time, item_description, vendor_customer, amount);

        Ledger.real_arrayList.add(deposit_transaction);

        //save new transaction to file
        try {
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //writing to the csv file
            bufferedWriter.write(date + "|" + time + "|" + item_description + "|" + vendor_customer + "|" + amount);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

            //added this for confirmation of deposit
            System.out.println("Your Deposit has been added successfully! Deposit amount: " + amount + " to " + vendor_customer +
                    " on " + date + " at " + time);

        } catch (Exception e) {
            System.out.println("Error - your deposit has not been added. Please try again!");
        }
    }
}
