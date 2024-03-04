package se.iths;

import java.time.LocalDate;

public class Run {
    private double distanceInKm;
    private int hours;
    private int minutes;
    private int seconds;
    private LocalDate date;

    public Run(double distanceInKm, int hours, int minutes, int seconds, LocalDate date) {
        this.distanceInKm = distanceInKm;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.date = date;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public LocalDate getRunDate() {
        return date;
    }
    
    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public double calculateRunSpeed() {
        double runTimeInHours = hours + (minutes / 60.0) + (seconds / 3600.0);

        double speed = distanceInKm / runTimeInHours;
        double speedRoundedToOneDecimal = Math.round(speed * 10.0) / 10.0;

        return speedRoundedToOneDecimal;
    }

    public String calculateKilometerTime() {
        double runTimeInMinutes = (hours * 60) + minutes + (seconds / 60.0);

        double kilometerTime = runTimeInMinutes / distanceInKm;
        double kilometerTimeRounded = Math.round(kilometerTime * 100.0) / 100.0;

        String kilometerTimeString = Double.toString(kilometerTimeRounded);
        kilometerTimeString = kilometerTimeString.replace(".", ":");

        return kilometerTimeString;
    }
}
