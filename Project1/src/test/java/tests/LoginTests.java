package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import utils.TestLogger;

@Listeners(utils.Listener.class)
public class LoginTests extends BaseTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void verifyValidLogin() { //1. Verify login with valid credentials
        SoftAssert softAssert = new SoftAssert();

        loginPage.enterEmail("testeruser@test.com");
        loginPage.enterPassword("Password123");
        loginPage.clicksubmitButton();

        boolean result = loginPage.ContactListHeader();
        softAssert.assertTrue(result, "User successfully signed in");
        TestLogger.logTestResult("verifyValidLogin", result);

        softAssert.assertAll();
    }

    @Test
    public void verifyInvalidPassword() { //2. Verify login with incorrect password
        SoftAssert softAssert = new SoftAssert();

        loginPage.enterEmail("userwrewone@test.com");
        loginPage.enterPassword("WrongPassword");
        loginPage.clicksubmitButton();

        boolean result = loginPage.InvalidPasswordError().equals("Incorrect username or password");
        softAssert.assertTrue(result, "Error displayed for incorrect password");
        TestLogger.logTestResult("verifyInvalidPassword", result);

        softAssert.assertAll();
    }

    @Test
    public void verifyEmptyFields() { //3. Verify login with empty fields
        SoftAssert softAssert = new SoftAssert();

        loginPage.clicksubmitButton();

        boolean result = loginPage.EmptyInputError().equals("Incorrect username or password");
        softAssert.assertTrue(result, "Error displayed for empty input fields");
        TestLogger.logTestResult("verifyEmptyFields", result);

        softAssert.assertAll();
    }

    @Test
    public void verifyInvalidEmailFormat() { //4. Verify login with invalid email format
        SoftAssert softAssert = new SoftAssert();

        loginPage.enterEmail("user.com");
        loginPage.enterPassword("WrongPassword");
        loginPage.clicksubmitButton();

        boolean result = loginPage.EmptyInputError().equals("Incorrect username or password");
        softAssert.assertTrue(result, "Error displayed for invalid email format");
        TestLogger.logTestResult("verifyInvalidEmailFormat", result);

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
