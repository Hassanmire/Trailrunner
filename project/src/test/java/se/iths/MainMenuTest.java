package se.iths;

import java.time.LocalDate;
import java.util.Scanner;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.*;
import static org.mockito.Mockito.*;

public class MainMenuTest {
    MainMenu mainMenu;

    @Mock
    private Scanner mockScanner;

    @BeforeEach
    public void setuo() {
        mockScanner = mock(Scanner.class);
        mainMenu = new MainMenu(mockScanner);       
    }
    
    @Test
    public void whenUserTypesInOneRunOneGetsSelectedForDeletion() {  
        when(mockScanner.nextInt()).thenReturn(1);

        assertEquals(1, mainMenu.chooseRunToDelete());

        verify(mockScanner).nextInt();
    }
    
    @Test
    public void whenUserTypesInOneRunOneGetsSelectedForDetailViewing() {
        when(mockScanner.nextInt()).thenReturn(1);

        assertEquals(1, mainMenu.chooseRunToViewDetailsFor());

        verify(mockScanner).nextInt();
    }

    @Test
    public void enteredHeightGetsReturned() {
        when(mockScanner.nextInt()).thenReturn(180);

        assertEquals(180, mainMenu.saveEnteredHeight());

        verify(mockScanner).nextInt();
    }

    @Test
    public void enteredWeightGetsReturned() {
        when(mockScanner.nextDouble()).thenReturn(50.5);

        assertEquals(50.5, mainMenu.saveEnteredWeight());

        verify(mockScanner).nextDouble();
    }
    
    @Test
    public void enteredHeight180AndWeight60ReturnsBMIEighteenPointFive() {
        when(mockScanner.nextInt()).thenReturn(180);
        when(mockScanner.nextDouble()).thenReturn(60.0);

        User user = new User();
        user.setUserHeight(mainMenu.saveEnteredHeight());
        user.setWeight(mainMenu.saveEnteredWeight());

        assertEquals(18.5, user.calculateUserBMI());

        verify(mockScanner).nextInt();
        verify(mockScanner).nextDouble();
    }
    
    @Test
    public void enteredDistanceIsReturnedByMethod() {
        when(mockScanner.nextDouble()).thenReturn(5.5);

        assertEquals(5.5, mainMenu.saveEnteredDistance());

        verify(mockScanner).nextDouble();
    }
    
    @Test
    public void enteredTimeIsReturnedByMethod() {
        when(mockScanner.nextLine()).thenReturn("0:12:30");

        assertEquals("0:12:30", mainMenu.saveEnteredTime());

        verify(mockScanner, times(1)).nextLine();
    }     

    @Test
    public void enteredDateIsReturnedByMethod() {
        when(mockScanner.nextLine()).thenReturn("2024-01-02");

        assertEquals("2024-01-02", mainMenu.saveEnteredDate());

        verify(mockScanner, times(1)).nextLine();
    }
    
    @Test
    public void splitRunTimeToArrayWithElements0and12and30() {
        int[] expected = {0, 12, 30};
        int[] actual = mainMenu.splitTimeUnits("0:12:30");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ifEnteredDateEmptyReturnTodaysDate() {
        when(mockScanner.nextLine()).thenReturn("");

        LocalDate expected = LocalDate.of(2024, 1, 15);
        LocalDate actual = mainMenu.createLocalDate(mainMenu.saveEnteredDate());
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void ifEntered20220101ReturnSameDate() {
        when(mockScanner.nextLine()).thenReturn("2022-01-01");

        LocalDate expected = LocalDate.of(2022, 1, 1);
        LocalDate actual = mainMenu.createLocalDate(mainMenu.saveEnteredDate());
        
        assertEquals(expected, actual);
    }
}
