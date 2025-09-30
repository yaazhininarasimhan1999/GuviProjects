package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import utils.TestLogger3;

@Listeners(utils.Listener3.class)
public class LoginTests extends BaseTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://demowebshop.tricentis.com/");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginWithValidCredentials() {//5. Verify login with valid credentials
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
        loginPage.enterEmail("demo@web.shop");
        loginPage.enterPassword("demowebshop");
        loginPage.clickLoginButton();
        boolean isLoggedIn = homePage.isUserLoggedIn("demo@web.shop");
        softAssert.assertTrue(isLoggedIn, "User is able to login with valid credentials");
        TestLogger3.logTestResult("loginWithValidCredentials", isLoggedIn);
        softAssert.assertAll();
    }

    @Test
    public void loginWithInvalidPassword() { // 6. Login with invalid password
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
        loginPage.enterEmail("demo@web.shop");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLoginButton();
        String errorText = loginPage.getLoginErrorText();
        boolean result = errorText.contains("Login was unsuccessful");
        softAssert.assertTrue(result, "User is not able to login with invalid password");
        TestLogger3.logTestResult("loginWithInvalidPassword", result);
        softAssert.assertAll();
    }

    @Test
    public void loginWithEmptyFields() { // 7. Login with empty fields
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
        loginPage.enterEmail("");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        String errorText = loginPage.getLoginErrorText();
        boolean result = errorText.contains("Login was unsuccessful");
        softAssert.assertTrue(result, "User is not able to login with empty fields");
        TestLogger3.logTestResult("loginWithEmptyFields", result);
        softAssert.assertAll();
    }

    @Test
    public void rememberMeFunctionality() { // 8. Verify "Remember Me" functionality
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
        loginPage.enterEmail("demo@web.shop");
        loginPage.enterPassword("demowebshop");
        loginPage.checkRememberMe(true);
        loginPage.clickLoginButton();
        driver.navigate().refresh(); 
        boolean isLoggedIn = homePage.isUserLoggedIn("demo@web.shop");
        softAssert.assertTrue(isLoggedIn, "User should remain logged in with Remember Me");
        TestLogger3.logTestResult("rememberMeFunctionality", isLoggedIn);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
