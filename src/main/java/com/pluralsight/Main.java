package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //gets arraylist from ledger class
    public static ArrayList<Transaction> real_arrayList2 = Ledger.real_arrayList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //for easier and cleaner display instead of multiple 'print' statements
        String mainMenu = """
                ------ Home Screen ------
                 D) Add Deposit
                 P) Make Payment (Debit)
                 L) Ledger
                 X) Exit
                """;

        String command = " ";
        while (!command.equalsIgnoreCase("X")) {
            System.out.print(mainMenu);

            command = ConsoleHelper.promptForString("Choose an option: ");

            //switch - case state: user picks options and display's desired output
            switch (command.toUpperCase()) {
                case "D":
                case "DEPOSIT":
                case "DEPOSITS":
                    System.out.println("Add Deposit selected.");

                    addDeposit();
                    break;

                case "P":
                case "PAYMENT":
                case "PAYMENTS":
                    System.out.println("Make Payment selected.");

                    addPayment();
                    break;

                case "L":
                case "LEDGER":
                    //static method being called
                    Ledger.showLedgerMenu(scanner);
                    break;

                case "X":
                case "EXIT":
                    System.out.println("Exiting program...");
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
        //add some validation to user if user adds invalid date structure
        LocalDate date = ConsoleHelper.promptForLocalDate("Enter the date");
        LocalTime time = ConsoleHelper.promptForLocalTime("Enter the time");
        String item_description = ConsoleHelper.promptForString("Enter the description: ");
        String vendor = ConsoleHelper.promptForString("Enter the vendor: ");
        double amount = ConsoleHelper.promptForDouble("Enter the amount: ");

        try {
            //makes amount into a negative
            amount = -Math.abs(amount);

            //create FileWriter
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //creating transaction object and store variables in object
            Transaction payment_transaction = new Transaction(date, time, item_description, vendor, amount);

            //add to arraylist
            Ledger.real_arrayList.add(payment_transaction);

            //writing to the csv file
            bufferedWriter.write(date + "|" + time + "|" + item_description + "|" + vendor + "|" + amount);
            bufferedWriter.newLine();

            //closing buffer and file
            bufferedWriter.close();
            fileWriter.close();

            //added this for confirmation of deposit
            System.out.println("Your Payment has been added successfully! Payment amount: " + amount + " to " + vendor +
                    " on " + date + " at " + time);


        } catch (Exception e) {
            System.out.println("Error - your payment has not been added. Please try again!");
        }

    }

    public static void addDeposit(){

        //user input - add some validation to user if user adds invalid date structure
        LocalDate date = ConsoleHelper.promptForLocalDate("Enter the date");
        LocalTime time = ConsoleHelper.promptForLocalTime("Enter the time");
        String item_description = ConsoleHelper.promptForString("Enter the description: ");
        String vendor = ConsoleHelper.promptForString("Enter the vendor: ");
        double amount = ConsoleHelper.promptForDouble("Enter the amount: ");

        try {
            //create FileWriter
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //creating transaction object and store variables in object
            Transaction deposit_transaction = new Transaction(date, time, item_description, vendor, amount);

            //add to arraylist
            //real_arrayList2.add(deposit_transaction);
            Ledger.real_arrayList.add(deposit_transaction);

            //writing to the csv file
            bufferedWriter.write(date + "|" + time + "|" + item_description + "|" + vendor + "|" + amount);
            bufferedWriter.newLine();

            //closing buffer and file
            bufferedWriter.close();
            fileWriter.close();

            //added this for confirmation of deposit
            System.out.println("Your Deposit has been added successfully! Deposit amount: " + amount + " to " + vendor +
                    " on " + date + " at " + time);

        } catch (Exception e) {
            System.out.println("Error - your deposit has not been added. Please try again!");
        }
    }
}
