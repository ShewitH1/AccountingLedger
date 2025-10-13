package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ConsoleHelper {

    private static Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt){
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public static LocalDate promptForLocalDate(String prompt){
        LocalDate date = null;
        boolean isValid = false;

        do{
            try{
                System.out.print(prompt + " (YYYY-MM-DD): ");
                String input = scanner.nextLine().trim();
                date = LocalDate.parse(input);
                isValid = true;
            } catch (Exception e){
                System.out.println("Invalid date format. Please enter in YYYY-MM-DD format");
            }
        } while(!isValid);
        return date;
    }


    public static LocalTime promptForLocalTime(String prompt){
        LocalTime time = null;
        boolean isValid = false;

        do {
            try {
                System.out.println(prompt + " (HH:MM:SS): ");
                String input = scanner.nextLine().trim();
                time = LocalTime.parse(input);
                isValid = true;
            }catch (Exception e){
                System.out.println("Entered Invalid time format. Please enter in HH:MM:SS format - 24hr .");
            }
        } while (!isValid);
        return time;
    }


    public static int promptForInt(String prompt){

        boolean isValid = false;
        int result = 0;
        do{
            try{
                System.out.print(prompt + ": ");
                result = scanner.nextInt();
                scanner.nextLine();
                isValid = true;
            }
            catch(Exception ex){
                scanner.nextLine();
                System.out.println("Invalid Entry, please enter a whole number");
                //ex.printStackTrace();
            }
        } while (!isValid);


        //after the catch
        return result;

    }


    public static int promptForIntAlt(String prompt){

        int result = 0 ;

        try{
            System.out.print(prompt + ": ");
            result = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex){
            scanner.nextLine();
            System.out.println("Invalid Entry, please enter a whole number");
        }

        //after the catch
        return result;

    }

    public static float promptForFloat(String prompt){
        System.out.print(prompt + ": ");
        float result = scanner.nextFloat();
        scanner.nextLine();
        return result;
    }

    public static double promptForDouble(String prompt){
        System.out.print(prompt + ": ");
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }

    public static long promptForLong(String prompt){
        System.out.print(prompt + ": ");
        long result = scanner.nextLong();
        scanner.nextLine();
        return result;
    }
}
