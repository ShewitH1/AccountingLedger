package com.pluralsight;

import java.util.Scanner;
import com.pluralsight.Ledger;


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

        char command = ' ';
        while (command != 'X') {
            System.out.print(mainMenu);
            System.out.println("Choose an option: ");

            String input = scanner.nextLine().trim(); //adding trim to remove whitespaces

            //getting it do convert a single 'char' to its uppercase version
            command = Character.toUpperCase(input.charAt(0));

            //switch - case state: user picks options and display's desired output
            switch (command) {
                case 'D':
                    System.out.println("Add Deposit selected.");
                    break;
                case 'P':
                    System.out.println("Make Payment selected.");
                    break;
                case 'L':
                    //ledger object - use variable to call ledger menu method
                    Ledger ledger = new Ledger();
                    ledger.showLedgerMenu(scanner);
                    break;
                case 'X':
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
}
