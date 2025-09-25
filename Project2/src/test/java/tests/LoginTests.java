package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.HomePage;
import utils.TestLogger2;

@Listeners(utils.Listener2.class)

public class LoginTests extends BaseTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://www.demoblaze.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void loginWithValidCredentials() { // 1. Verify login with valid credentials
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
        loginPage.enterEmail("paya@test.com");
        loginPage.enterPassword("qweqwe");
        loginPage.clickLoginButton();
        boolean result = loginPage.isLoginSuccessful();
        softAssert.assertTrue(result, "User successfully logged in with valid credentials");
        TestLogger2.logTestResult("loginWithValidCredentials", result);
        softAssert.assertAll();
    }

    @Test
    public void loginWithIncorrectPassword() { // 2. Verify login with incorrect password
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
        loginPage.enterEmail("paya@test.com");
        loginPage.enterPassword("WrongPassword");
        loginPage.clickLoginButton();
        boolean result = loginPage.getLoginAlertText().equals("Wrong password.");
        softAssert.assertTrue(result, "User is unable to login with wrong password");
        TestLogger2.logTestResult("loginWithIncorrectPassword", result);
        softAssert.assertAll();
    }

    @Test
    public void loginWithEmptyFields() { // 3. Verify login with empty fields
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
        loginPage.clickLoginButton();
        boolean result = loginPage.getLoginAlertText().equals("Please fill out Username and Password.");
        softAssert.assertTrue(result, "User is unable to login with empty fields");
        TestLogger2.logTestResult("loginWithEmptyFields", result);
        softAssert.assertAll();
    }

    @Test
    public void loginWithInvalidEmailFormat() { // 4. Verify login with invalid email format
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
        loginPage.enterEmail("paya.com");
        loginPage.enterPassword("qweqwe");
        loginPage.clickLoginButton();
        boolean result = loginPage.getLoginAlertText().equals("User does not exist.");
        softAssert.assertTrue(result, "User is unable to login with invalid email");
        TestLogger2.logTestResult("loginWithInvalidEmailFormat", result);
        softAssert.assertAll();
    }

    @Test
    public void PasswordFieldMasking() { // 5. Verify password field masks input
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
        loginPage.enterPassword("Password123");
        boolean result = loginPage.getPasswordField().getAttribute("type").equals("password");
        softAssert.assertTrue(result, "Password field masks input properly");
        TestLogger2.logTestResult("PasswordFieldMasking", result);
        softAssert.assertAll();
    }
    
    @Test
    public void verifyLogout() { // 20. Verify logout after login
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
        loginPage.enterEmail("uniueUser123");
        loginPage.enterPassword("Password123");
        loginPage.clickLoginButton();
        homePage.clickLogout();
        boolean result = loginPage.isLogoutSuccessful();
        softAssert.assertTrue(result, "User successfully logged out");
        TestLogger2.logTestResult("Logout", result);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
