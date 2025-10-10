package com.pluralsight;
import com.pluralsight.Ledger;


import java.util.Scanner;

public class Ledger {

    //ledger method
    public static void showLedgerMenu(Scanner scanner){
        String ledgerMenu = """
                ------ Ledger ------
                A) All - display all entries
                D) Deposits
                P) Payments
                R) Reports
                0) Back
                H) Home
            """;

        String command = " ";
        while (!command.equalsIgnoreCase("H")){
            System.out.println(ledgerMenu);
            System.out.println("Choose an option: ");

            command = scanner.nextLine().trim();

            switch (command.toUpperCase()) {
                case "A":
                case "ALL":
                    System.out.println("display all");
                    break;

                case "D":
                case "DEPOSIT":
                case "DISPLAY":
                    System.out.println("display deposits");
                    break;

                case "P":
                case "PAYMENT":
                case "PAYMENTS":
                    System.out.println("display payments");
                    break;

                case "R":
                case "REPORT":
                    System.out.println("display reports");
                    break;

                case "0":
                    //ask teacher about this
                    break;

                case "H":
                case "HOME":
                    System.out.println("Returning to Home...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }
    }
}
