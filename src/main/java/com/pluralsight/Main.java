package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //user input
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

    public void addDeposit(Scanner scanner){

        try {
            //create FileWriter
            FileWriter fileWriter = new FileWriter("transactions.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);







        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
