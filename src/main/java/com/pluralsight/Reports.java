package com.pluralsight;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {

    public static void showReportsMenu(Scanner scanner){
        String ledgerMenu = """
                ------ Reports ------
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
            command = ConsoleHelper.promptForInt("Choose an option: ");

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
                    searchByVendor();
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

    //search by vendor
    public static void searchByVendor(){
        String vendor_name = ConsoleHelper.promptForString("Enter the vendor name for search: ");

        for(int i = 0; i<Ledger.real_arrayList.size(); i++){
            Transaction transaction = Ledger.real_arrayList.get(i);
            if(transaction.getVendor().equalsIgnoreCase(vendor_name)){
                System.out.println(transaction.toEncodedString());
            }
        }

    }


    //got from lecture - pretty useful!
    /* these methods will help get specific dates for the rest of case 1-5 methods */
    public static boolean isWithinRange(LocalDate date, LocalDate start, LocalDate end) {
        return (date.isEqual(start) || date.isAfter(start)) &&
                (date.isEqual(end)   || date.isBefore(end));
        }
    }










