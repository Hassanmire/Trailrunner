package se.iths;

import java.time.LocalDate;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RunTest {
    @Test
    public void distanceDividedByTimeEqualsSpeed() {
        LocalDate runDate = LocalDate.of(2024, 1, 2);

        Run run = new Run(4.5, 0, 14, 30, runDate);

        double runSpeed = run.calculateRunSpeed();

        assertEquals(18.6, runSpeed);
    }

    @Test
    public void timeDividetByDistanceEqualsKilometerTime() {
        LocalDate runDate = LocalDate.of(2024, 1, 2);

        Run run = new Run(4.5, 0, 14, 30, runDate);

        String kilometerTime = run.calculateKilometerTime();

        assertEquals("3:22", kilometerTime);
    }
}
