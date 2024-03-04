package se.iths;

import java.util.HashMap;
import java.util.Map;

public class RunLibrary {
    Map<Integer, Run> runMap = new HashMap<>();
    private int runID = 1;

    public void saveRun(Run run) {
        runMap.put(runID, run);

        runID = runMap.size() + 1;
    }

    public double calculateTotalRunDistance() {
        double totalDistance = 0.0;

        for (Run run : runMap.values()) {
            totalDistance += run.getDistanceInKm();
        }

        double totalDistanceRounded = Math.round(totalDistance * 100.0) / 100.0;

        return totalDistanceRounded; 
    }  

    public double calculateAverageRunDistance() {
        double totalDistance = 0.0;
    
        for (Run run : runMap.values()) {
            totalDistance += run.getDistanceInKm(); 
        }

        double averageDistance = totalDistance / runMap.size();
        double averageDistanceRounded = Math.round(averageDistance * 100.0) / 100.0;

        return averageDistanceRounded;
    }

    public String fetchRunDetails(int runID) {
        Run run = runMap.get(runID);

        String runDetails = "Löprunda " + runID + System.lineSeparator();
        runDetails += "-----------" + System.lineSeparator();
        runDetails += "Datum: " + run.getRunDate() + System.lineSeparator();
        runDetails += "Distans: " + run.getDistanceInKm() + " km" + System.lineSeparator();
        runDetails += "Tid: " + run.getHours() + ":" + run.getMinutes() + ":" + run.getSeconds() + System.lineSeparator();
        runDetails += "Medelhastighet: " + run.calculateRunSpeed() + " km/h" + System.lineSeparator();
        runDetails += "Kilometer-tid: " + run.calculateKilometerTime() + "/km" + System.lineSeparator();

        return runDetails;
    }

    public String deleteRun(int runID) {
        if (runMap.containsKey(runID)) {
            runMap.remove(runID);

            return "Löprunda " + runID + " har raderats.";
        } else {
            return "Löprunda " + runID + " kan inte hittas.";
        }
    }
}
