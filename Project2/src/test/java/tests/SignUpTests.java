package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SignUpPage;
import utils.TestLogger2;

@Listeners(utils.Listener2.class)
public class SignUpTests extends BaseTest {

    private WebDriver driver;
    private HomePage homePage;
    private SignUpPage signUpPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://www.demoblaze.com/");
        homePage = new HomePage(driver);
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void signUpWithUniqueCredentials() { //6. Verify user sign-up with unique credentials
        SoftAssert softAssert = new SoftAssert();
        homePage.clickSignUp();
        signUpPage.enterUsername("mrkouser");
        signUpPage.enterPassword("Password123");
        signUpPage.clickSignUpButton();
        String alertText = signUpPage.getAlertTextAndAccept();
        boolean result = alertText.equals("Sign up successful.");
        softAssert.assertTrue(result, "User successfully signed-up with unique credentials");
        TestLogger2.logTestResult("signUpWithUniqueCredentials", result);
        softAssert.assertAll();
    }

    @Test
    public void signUpWithExistingUsername() { //7. Verify sign-up with existing username
        SoftAssert softAssert = new SoftAssert();
        homePage.clickSignUp();
        signUpPage.enterUsername("uniqueUser123");
        signUpPage.enterPassword("Password123");
        signUpPage.clickSignUpButton();
        String alertText = signUpPage.getAlertTextAndAccept();
        boolean result = alertText.equals("This user already exist.");
        softAssert.assertTrue(result, "User is not able to sign-up with existing username");
        TestLogger2.logTestResult("signUpWithExistingUsername", result);
        softAssert.assertAll();
    }

    @Test
    public void signUpWithEmptyFields() {  // 8. Verify sign-up with empty fields
        SoftAssert softAssert = new SoftAssert();
        homePage.clickSignUp();
        signUpPage.enterUsername("");
        signUpPage.enterPassword("");
        signUpPage.clickSignUpButton();
        String alertText = signUpPage.getAlertTextAndAccept();
        boolean result = alertText.contains("Please fill out Username and Password.");
        softAssert.assertTrue(result, "Error displayed to fill the empty fields");
        TestLogger2.logTestResult("signUpWithEmptyFields", result);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
