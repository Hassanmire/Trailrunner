package se.iths;

import java.time.LocalDate;
import java.util.Scanner;

public class MainMenu {
    RunLibrary runLibrary = new RunLibrary();
    private Scanner scanner;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public int chooseRunToDelete() {
        System.out.print("Ange ID-numret på den löprundan du vill radera från historiken: ");
        int runID = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        return runID;
    }
    
    public int chooseRunToViewDetailsFor() {
        System.out.print("Ange ID-numret på den löprundan du vill se detaljer för: ");
        int runID = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        return runID;
    }

    public int saveEnteredHeight() {
        System.out.print("Ange din längd (cm): ");
        int height = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        return height;
    }

    public double saveEnteredWeight() {
        System.out.print("Ange din vikt (kg): ");
        double weight = scanner.nextDouble();
        scanner.nextLine();
        System.out.println();

        return weight;
    }

    public double saveEnteredDistance() {
        System.out.print("Ange löprundans distans: ");
        double distance = scanner.nextDouble();
        scanner.nextLine();
        System.out.println();

        return distance;
    }

    public String saveEnteredTime() {
        System.out.print("Ange löprundans tid (H:M:S): ");
        String time = scanner.nextLine();
        System.out.println();        

        return time;
    }

    public String saveEnteredDate() {
        System.out.print("Ange datumet för löprundan (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.println();

        return date;
    }
    
    public int[] splitTimeUnits(String timeString) {
        String[] timeStringArray = timeString.split(":");
        int[] timeIntArray = new int[3];

        int i = 0;
        for (String timeUnit : timeStringArray) {
            int timeInt = Integer.parseInt(timeUnit);
            timeIntArray[i] = timeInt;
            i++;
        }

        return timeIntArray;
    }

    public LocalDate createLocalDate(String dateString) {
        LocalDate date;

        if (dateString.isEmpty()) {
            date = LocalDate.now();
        } else {
            String[] dateStringArray = dateString.split("-");
            int[] dateIntArray = new int[3];

            int i = 0;
            for (String timeUnit : dateStringArray) {
                int dateInt = Integer.parseInt(timeUnit);
                dateIntArray[i] = dateInt;
                i++;
            }
            
            date = LocalDate.of(dateIntArray[0], dateIntArray[1], dateIntArray[2]);
        }

        return date;
    }
}
