package se.iths;

public class User {
    private double height;
    private double weight;

    public void setUserHeight(double height) {
        this.height = height;
    }

    public double getUserHeight() {
        return height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public double calculateUserBMI() {
        double heightInMeters = height / 100;

        double userBMI = weight / (heightInMeters * heightInMeters);
        double userBMIWithOneDecimal = Math.round(userBMI * 10.0) / 10.0;

        return userBMIWithOneDecimal;
    }
}
