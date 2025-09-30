package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;
import pages.HomePage;
import utils.TestLogger3;

@Listeners(utils.Listener3.class)
public class RegisterTests extends BaseTest {

    private WebDriver driver;
    private HomePage homePage;
    private RegisterPage signUpPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://demowebshop.tricentis.com/");
        homePage = new HomePage(driver);
        signUpPage = new RegisterPage(driver);
    }

    @Test
    public void registrationWithValidDetails() { //1. Verify registration with valid details
        SoftAssert softAssert = new SoftAssert();       
        homePage.clickRegister();
        signUpPage.enterFirstName("Test");
        signUpPage.enterLastName("User");
        signUpPage.enterEmail("theegreattesting@test.com");
        signUpPage.enterPassword("StrongPass123!");
        signUpPage.clickRegister();
        String registeredEmail = signUpPage.getSuccessfulRegistrationMsg();
        boolean result = registeredEmail.contains("completed");
        softAssert.assertTrue(result, "User successfully registered.");
        TestLogger3.logTestResult("registrationWithValidDetails", result);
        softAssert.assertAll();
    }

    @Test
    public void registrationWithExistingEmail() {//2. Verify registration with already registered email
        SoftAssert softAssert = new SoftAssert();
        homePage.clickRegister();
        signUpPage.enterFirstName("Test");
        signUpPage.enterLastName("User");
        signUpPage.enterEmail("Demouseqwr@test.com");
        signUpPage.enterPassword("StrongPass123!");
        signUpPage.clickRegister();
        boolean result = signUpPage.getRegisteredEmail().contains("The specified email already exists");
        softAssert.assertTrue(result, "User is unable to register with existing email.");
        TestLogger3.logTestResult("registrationWithExistingEmail", result);
        softAssert.assertAll();
    }

    @Test
    public void registrationWithMissingMandatoryFields() { //3. Verify registration with missing mandatory fields
        SoftAssert softAssert = new SoftAssert();
        homePage.clickRegister();
        signUpPage.enterFirstName("Test");
        signUpPage.enterLastName("User");
        signUpPage.clickRegister();
        String emailError = signUpPage.getFieldErrorMessage();
        boolean result = emailError.contains("required");
        softAssert.assertTrue(result, "User is unable to register without giving mandatory fields.");
        TestLogger3.logTestResult("registrationWithMissingMandatoryFields", result);
        softAssert.assertAll();
    }

    @Test
    public void registrationWithInvalidEmailFormat() {//4. Verify email format validation
        SoftAssert softAssert = new SoftAssert();
        homePage.clickRegister();
        signUpPage.enterFirstName("Test");
        signUpPage.enterLastName("User");
        signUpPage.enterEmail("abc@xyz");      
        String emailError = signUpPage.getFieldErrorMessage();
        boolean result = emailError.contains("Wrong ");
        softAssert.assertTrue(result, "Email format validation message displayed");
        TestLogger3.logTestResult("registrationWithInvalidEmailFormat", result);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
