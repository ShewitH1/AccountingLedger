package com.pluralsight;
import com.pluralsight.Ledger;


import java.util.Scanner;

public class Ledger {

    //ledger method
    public void showLedgerMenu(Scanner scanner){
        String ledgerMenu = """
                ------ Ledger ------
                A) All - display all entries
                D) Deposits
                P) Payments
                R) Reports
                0) Back
                H) Home
            """;

        char command = ' ';
        while (command != 'H'){
            System.out.println(ledgerMenu);
            System.out.println("Choose an option: ");

            String input = scanner.nextLine().trim();

            //stores in command variable - gets user input - converts to U.C and gets first letter typed
            command = Character.toUpperCase(input.charAt(0));

            switch (command) {
                case 'A':
                    System.out.println("display all");
                    break;
                case 'D':
                    System.out.println("display deposits");
                    break;
                case 'P':
                    System.out.println("display payments");
                    break;
                case 'R':
                    System.out.println("display reports");
                    break;
                case '0':
                    //ask teacher about this

                case 'H':
                    System.out.println("Returning to Home...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }
    }
}
