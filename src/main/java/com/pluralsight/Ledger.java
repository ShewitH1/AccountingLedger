package com.pluralsight;
import com.pluralsight.Ledger;


import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {
    public static ArrayList<Transaction> dummy_arrayList = getInventory();
    public static ArrayList<Transaction> real_arrayList = getFromCSVFile();

    //ledger method
    public static void showLedgerMenu(Scanner scanner) {
        String ledgerMenu = """
                    ------ Ledger ------
                    A) All - display all entries
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home
                """;

        String command = " ";
        while (!command.equalsIgnoreCase("H")) {
            System.out.println(ledgerMenu);
            System.out.println("Choose an option: ");

            command = scanner.nextLine().trim();

            switch (command.toUpperCase()) {
                case "A":
                case "ALL":
                    System.out.println("display all");
                    System.out.println();
                    displayEntries();
                    break;

                case "D":
                case "DEPOSIT":
                case "DISPLAY":
                    System.out.println("display deposits");
                    System.out.println();
                    displayDepositEntries();
                    break;

                case "P":
                case "PAYMENT":
                case "PAYMENTS":
                    System.out.println("display payments");
                    break;

                case "R":
                case "REPORT":
                    System.out.println("display reports");
                    Reports.showReportsMenu(scanner);
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


    public static ArrayList<Transaction> getFromCSVFile(){
        //create an array list
        ArrayList<Transaction> arrayList = new ArrayList<>();


        try {
            //create file reader
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //read through file - line by line
            String line;
            while((line = bufferedReader.readLine()) != null){

                //split and store into variables
                String[] line_part = line.split("\\|");

                LocalDate date = LocalDate.parse(line_part[0]);
                LocalTime time = LocalTime.parse(line_part[1]);
                String description = line_part[2];
                String vendor = line_part[3];
                double amount = Double.parseDouble(line_part[4]);

                //create object and store variables
                Transaction transaction_object = new Transaction(date, time, description, vendor, amount);

                //add to arraylist
                arrayList.add(transaction_object);
            }

            //close both readers
            bufferedReader.close();
            fileReader.close();


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return arrayList;
    }

    //entries - dummy data
    public static ArrayList<Transaction> getInventory() {
        ArrayList<Transaction> entries = new ArrayList<>();

        Transaction t1 = new Transaction(
                LocalDate.of(2025, 10, 1),
                LocalTime.of(9, 30),
                "Office Supplies Purchase",
                "Staples",
                -45.67
        );

        entries.add(t1);

        Transaction t2 = new Transaction(
                LocalDate.of(2025, 10, 3),
                LocalTime.of(14, 15),
                "Client Payment",
                "Acme Corp",
                1500.00
        );

        entries.add(t2);

        Transaction t3 = new Transaction(
                LocalDate.of(2025, 10, 5),
                LocalTime.of(10, 45),
                "Coffee for Team Meeting",
                "Starbucks",
                18.25
        );

        entries.add(t3);

        Transaction t4 = new Transaction(
                LocalDate.of(2025, 10, 8),
                LocalTime.of(16, 20),
                "Website Maintenance",
                "TechFix Ltd",
                -250.00
        );

        entries.add(t4);

        Transaction t5 = new Transaction(
                LocalDate.of(2025, 10, 9),
                LocalTime.of(11, 5),
                "Monthly Subscription",
                "Adobe",
                -29.99
        );
        entries.add(t5);

        return entries;

    }

    //display all entries
    public static void displayEntries(){
        //loop thru arraylist and print
        for(Transaction list : real_arrayList){
            System.out.println(list.toEncodedString());
        }
    }

    //display deposits
    public static void displayDepositEntries(){
        double totalDeposits = 0;
        int count = 0;

        //loop thru arraylist - see if amount is greater than 0 - print
        for(int i = 0; i<dummy_arrayList.size(); i++){
            Transaction transaction = dummy_arrayList.get(i);
            if(transaction.getAmount() > 0){
                System.out.println(transaction);
                totalDeposits += transaction.getAmount(); //then add deposit amount to variable
                count++; //increment count of added deposit
            }
        }
        //if - else: sees if there is deposits made - if so it will print the amount and number of deposit
        if (count == 0){
            System.out.println("No deposits were found");
        }
        else{
            System.out.printf("Number of deposits: %d | Total Deposits: $%.2f%n", count, totalDeposits);
        }

    }
}
