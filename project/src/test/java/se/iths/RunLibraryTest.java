package se.iths;

import java.time.LocalDate;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RunLibraryTest {
    RunLibrary runLibrary;

    @BeforeEach
    public void setup() {
        runLibrary = new RunLibrary();
    }

    @Test
    public void savedRunShouldHaveUniqueID() {
        LocalDate runDate = LocalDate.of(2024, 1, 2);
        Run runOne = new Run(4.0, 0, 14, 30, runDate);
        Run runTwo = new Run(6.0, 0, 55, 10, runDate);

        runLibrary.saveRun(runOne);
        runLibrary.saveRun(runTwo);

        assertEquals(2, runLibrary.runMap.size());
    }

    @Test
    public void fourKmPlusSixKmEqualsTenKm() {
        LocalDate runDate = LocalDate.of(2024, 1, 2);
        Run runOne = new Run(4.0, 0, 14, 30, runDate);
        Run runTwo = new Run(6.0, 0, 55, 10, runDate);
        
        runLibrary.saveRun(runOne);
        runLibrary.saveRun(runTwo);

        assertEquals(10.0, runLibrary.calculateTotalRunDistance());
    }

    @Test
    public void tenKmDividedByTwoEqualsFiveKmAverageDistance() {
        LocalDate runDate = LocalDate.of(2024, 1, 2);
        Run runOne = new Run(4.0, 0, 14, 30, runDate);
        Run runTwo = new Run(6.0, 0, 55, 10, runDate);
        runLibrary.saveRun(runOne);
        runLibrary.saveRun(runTwo);

        assertEquals(5.0, runLibrary.calculateAverageRunDistance());
    }

    @Test
    public void runDetailsAreFetchedCorrectly() {
        LocalDate runDate = LocalDate.of(2024, 1, 2);
        Run runOne = new Run(4.0, 0, 14, 30, runDate);
        runLibrary.saveRun(runOne);

        String expected = "Löprunda 1" + System.lineSeparator();
        expected += "-----------" + System.lineSeparator();
        expected += "Datum: 2024-01-02" + System.lineSeparator();
        expected += "Distans: 4.0 km" + System.lineSeparator();
        expected += "Tid: 0:14:30" + System.lineSeparator();
        expected += "Medelhastighet: 16.6 km/h" + System.lineSeparator();
        expected += "Kilometer-tid: 3:63/km" + System.lineSeparator();

        assertEquals(expected, runLibrary.fetchRunDetails(1));
    }

    @Test
    public void ifRunExistsDeleteAndReturnSuccessStatement() {
        LocalDate runDate = LocalDate.of(2024, 1, 2);
        Run runOne = new Run(4.0, 0, 14, 30, runDate);
        runLibrary.saveRun(runOne);
        
        assertEquals("Löprunda 1 har raderats.", runLibrary.deleteRun(1));
    }

    @Test
    public void ifRunDoesntExistReturnFailureStatement() {
        LocalDate runDate = LocalDate.of(2024, 1, 2);
        Run runOne = new Run(4.0, 0, 14, 30, runDate);
        runLibrary.saveRun(runOne);
        
        assertEquals("Löprunda 2 kan inte hittas.", runLibrary.deleteRun(2));
    }

}
