/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

 import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    public LoginTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        // Initialization code (if any) before running the tests
    }

    @AfterAll
    public static void tearDownClass() {
        // Cleanup code (if any) after all tests are run
    }

    @Test
    public void testCheckUsername_Valid() {
        Login login = new Login("Junior", "Malunzaisa", "JM_69", "Password@123", "o751173227");
        assertTrue(login.checkUsernameFormat());
    }

    @Test
    public void testCheckUsername_Invalid() {
        Login login = new Login("Junior", "Malunzaisa", "Junior Malunzaisa!!!", "Password@123", "0821234567");
        assertFalse(login.checkUsernameFormat());
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(Login.isPasswordComplex("Junior@1283"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(Login.isPasswordComplex("password")); // Too simple
    }

    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        com.mycompany.chatapp.Login instance = null;
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        com.mycompany.chatapp.Login instance = null;
        instance.setUsername(username);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCheckUsernameFormat() {
        System.out.println("checkUsernameFormat");
        com.mycompany.chatapp.Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkUsernameFormat();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsPasswordComplex() {
        System.out.println("isPasswordComplex");
        String password = "";
        boolean expResult = false;
        boolean result = com.mycompany.chatapp.Login.isPasswordComplex(password);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCheckCellphoneNumber() {
        System.out.println("checkCellphoneNumber");
        String regcell = "";
        com.mycompany.chatapp.Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkCellphoneNumber(regcell);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        com.mycompany.chatapp.Login instance = null;
        String expResult = "";
        String result = instance.registerUser();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}
