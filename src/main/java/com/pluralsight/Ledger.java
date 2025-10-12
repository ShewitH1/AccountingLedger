package com.pluralsight;
import com.pluralsight.Ledger;


import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {
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
                    System.out.println();

                    displayPaymentsEntries();
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
            bufferedReader.readLine();

            //read through file - line by line
            String line;
            while((line = bufferedReader.readLine()) != null){

                //split and store into variables
                String[] line_part = line.trim().split("\\|");

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


    //display all entries
    public static void displayEntries(){
        //loop thru arraylist and print
        for(Transaction list : real_arrayList){
            System.out.println(list.toEncodedString());
        }
    }

    //display deposits
    public static void displayDepositEntries(){

        real_arrayList = getFromCSVFile(); //reloads arraylist everytime user adds

        double totalDeposits = 0;
        int count = 0;

        //loop thru arraylist - see if amount is greater than 0 - print
        for(int i = 0; i<real_arrayList.size(); i++){
            Transaction transaction = real_arrayList.get(i);
            if(transaction.getAmount() > 0){
                System.out.println(transaction.toEncodedString());
                totalDeposits += transaction.getAmount(); //then add deposit amount to variable
                count++; //increment count of added deposit
            }
        }
        //if - else: sees if there is deposits made - if so it will print the amount and number of deposit
        if (count == 0){
            System.out.println("No deposits were found");
        }
        else{
            //possible make it have a '+' beside it
            System.out.printf("Number of deposits: %d | Total Deposits: $%.2f%n", count, totalDeposits);
        }

    }


    //display deposits
    public static void displayPaymentsEntries(){
        double totalPayments = 0;
        int count = 0;

        //loop thru array - amount < 0 - print and add amount/counter
        for(Transaction payment : real_arrayList){
            if(payment.getAmount() < 0){
                System.out.println(payment);
                totalPayments+=payment.getAmount(); //then add payment amount to variable
                count++;    //increment count of added payments
            }
        }

        if (count == 0){
            System.out.println("No payments were found");
        }
        else{
            //possible make it have a '-' beside it
            System.out.printf("Number of payments: %d | Total Payments: $%.2f%n", count, totalPayments);
        }
    }
}
