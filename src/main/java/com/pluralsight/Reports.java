package com.pluralsight;

import java.util.Scanner;

public class Reports {

    public static void showReportsMenu(Scanner scanner){
        String ledgerMenu = """
                ------ Ledger ------
                1) Month to Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                0) Back
            """;

        int command = -1;
        while (command != 0){
            System.out.println(ledgerMenu);
            System.out.println("Choose an option: ");

            //get the user input and convert to int
            String input = scanner.nextLine().trim();

            try{
                //trying to convert 'string' input into an int
                command = Integer.parseInt(input);
            } catch (Exception e){
                System.out.println("Invalid input, please enter a number!");
                continue;
            }

            switch (command) {
                case 1:
                    System.out.println("Month to date");
                    break;

                case 2:
                    System.out.println("previous month");
                    break;

                case 3:
                    System.out.println("year to date");
                    break;

                case 4:
                    System.out.println("previous year");
                    break;

                case 5:
                    System.out.println("search by vendor");
                    break;

                case 0:
                    System.out.println("Back to ledge page...");
                    Ledger.showLedgerMenu(scanner);

                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }
    }
}
