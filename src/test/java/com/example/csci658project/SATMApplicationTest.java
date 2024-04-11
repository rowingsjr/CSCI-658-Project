package com.example.csci658project;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SATMApplicationTest {

    private SATMApplication satmApplication;

    @BeforeEach
    public void setUp() {
        satmApplication = new SATMApplication();
        satmApplication.text = new Text(); // Initialize the text field
        satmApplication.square = new Rectangle();
    }

    @Test
    public void testHandleNumberPress_IncorrectPIN() {
        satmApplication.handleNumberPress("1");
        satmApplication.handleNumberPress("2");
        satmApplication.handleNumberPress("3");
        satmApplication.handleNumberPress("4");
        assertFalse(satmApplication.correctPin.equals(satmApplication.pin));
        // You may want to check if there's a specific action or message displayed for incorrect PIN
    }

    @Test
    public void testDepositFunctionality_UpdatesBalanceCorrectly() {
        double initialBalance = satmApplication.balance;
        satmApplication.enterButtonPressed = true;
        satmApplication.depositAmt("200");
        assertEquals(initialBalance + 200, satmApplication.balance);
    }

    @Test
    public void testWithdrawalExceedsBalance() {
        satmApplication.balance = 100;
        satmApplication.enterButtonPressed = true;
        satmApplication.withdrawalAmt("150"); // Attempting to withdraw more than the balance
        assertEquals(100, satmApplication.balance); // Balance remains unchanged
    }





    @Test
    public void testUpdateScreenContent() {
        // Call the method under test
        satmApplication.updateScreenContent("Test", Color.RED);

        // Assert the expected results
        assertEquals("Test", satmApplication.text.getText());
        assertEquals(Color.RED, satmApplication.square.getFill());
    }


    @Test
    public void testUpdateTransactionScreen() {
        satmApplication.updateTransactionScreen("Test", Color.BLUE);
        assertEquals("Test", satmApplication.text.getText());
        assertEquals(Color.BLUE, satmApplication.square.getFill());
    }

    @Test
    public void testPromptForPin() {
        satmApplication.promptForPin();
        assertEquals("Please enter your PIN\n_ _ _ _", satmApplication.text.getText());
        assertEquals(Color.rgb(32, 115, 69), satmApplication.square.getFill());
    }

    @Test
    public void testHandleNumberPress() {
        satmApplication.handleNumberPress("1");
        assertEquals("1", satmApplication.pin);
    }

    @Test
    public void testResetHoldTimer() {
        satmApplication.resetHoldTimer();
        assertNull(satmApplication.holdTimer);
    }

    @Test
    public void testResetHoldTimer2()
    {
        satmApplication.resetHoldTimer2();
        assertNull(satmApplication.holdTimer);
    }

}
