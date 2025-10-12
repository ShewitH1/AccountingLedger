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
            System.out.println("Choose an option: ");

            command = scanner.nextLine().trim(); //adding trim to remove whitespaces

            //switch - case state: user picks options and display's desired output
            switch (command.toUpperCase()) {
                case "D":
                case "DEPOSIT":
                case "DEPOSITS":
                    System.out.println("Add Deposit selected.");
                    //All();

                    addDeposit(scanner);

                    break;

                case "P":
                case "PAYMENT":
                case "PAYMENTS":
                    System.out.println("Make Payment selected.");
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



    public static void All(){
        for(Transaction list : real_arrayList2){
            System.out.println(list.toEncodedString());
        }
    }

    public static void addDeposit(Scanner scanner){

        //add some validation to user if user adds invalid date structure
        //user input
        System.out.println("Enter the date: ");
        LocalDate date = LocalDate.parse(scanner.nextLine()); //converting date into local date

        System.out.println("Enter the time: ");
        LocalTime time = LocalTime.parse(scanner.nextLine()); //converting time into local time

        System.out.println("Enter the description: ");
        String item_description = scanner.nextLine().trim();

        System.out.println("Enter the vendor: ");
        String vendor = scanner.nextLine().trim();

        System.out.println("Enter the amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();


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
