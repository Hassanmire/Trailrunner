package se.iths;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user;

    @BeforeEach
    public void setup() {
        user = new User();
    }

    @Test
    public void testSettingUserHeight() {
        user.setUserHeight(180);

        assertEquals(180, user.getUserHeight());
    }

    @Test
    public void testUserweight() {
        user.setWeight(70);
        assertEquals(70, user.getWeight());
    }

    @Test
    public void weightDividedBySquaredHeightEqualsBMI() {
        user.setUserHeight(180);
        user.setWeight(70);
        
        double userBMI = user.calculateUserBMI();

        assertEquals(21.6, userBMI);
    }
}  
