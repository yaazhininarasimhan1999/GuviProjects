package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.SignUpPage;
import utils.TestLogger;

@Listeners(utils.Listener.class)
public class SignUpTests extends BaseTest {

    private WebDriver driver;
    private SignUpPage signUpPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void verifyValidSignUp() { //6. Verify sign-up with valid inputs
        SoftAssert softAssert = new SoftAssert();

        signUpPage.clickSignUp();
        signUpPage.enterfirstName("Tester");
        signUpPage.enterlastName("User");
        signUpPage.enterEmail("testeruser@test.com");
        signUpPage.enterPassword("Password123");
        signUpPage.clickSubmit();

        boolean result = signUpPage.ContactListHeader();
        softAssert.assertTrue(result, "User successfully signed up");
        TestLogger.logTestResult("verifyValidSignUp", result);

        softAssert.assertAll();
    }

    @Test
    public void verifyExistingEmail() { //7. Verify registration with already registered email
        SoftAssert softAssert = new SoftAssert();

        signUpPage.clickSignUp();
        signUpPage.enterfirstName("Uerwrser");
        signUpPage.enterlastName("One");
        signUpPage.enterEmail("testeruser@test.com");
        signUpPage.enterPassword("Password123");
        signUpPage.clickSubmit();

        boolean result = signUpPage.getExistingEmailError().equals("Email address is already in use");
        softAssert.assertTrue(result, "Error displayed for already registered email");
        TestLogger.logTestResult("verifyExistingEmail", result);

        softAssert.assertAll();
    }

    @Test
    public void verifyEmptyFields() { //8. Verify registration with blank fields
        SoftAssert softAssert = new SoftAssert();

        signUpPage.clickSignUp();
        signUpPage.clickSubmit();

        boolean result = signUpPage.EmptyInputError().equals(
            "User validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required., email: Email is invalid, password: Path `password` is required.");
        softAssert.assertTrue(result, "Error displayed for blank input fields");
        TestLogger.logTestResult("verifyEmptyFields", result);

        softAssert.assertAll();
    }

    @Test
    public void verifyInvalidEmailFormat() { //10. Verify email format validation during sign-up
        SoftAssert softAssert = new SoftAssert();

        signUpPage.clickSignUp();
        signUpPage.enterfirstName("Uerwrser");
        signUpPage.enterlastName("One");
        signUpPage.enterEmail("user@name");
        signUpPage.enterPassword("Password123");
        signUpPage.clickSubmit();

        boolean result = signUpPage.InvalidEmailFormatError().equals("User validation failed: email: Email is invalid");
        softAssert.assertTrue(result, "Error displayed for invalid email format");
        TestLogger.logTestResult("verifyInvalidEmailFormat", result);

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
