package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import pages.LoginPage;
import pages.ContactListPage;
import utils.TestLogger;

@Listeners(utils.Listener.class)
public class ContactUIValidationTests extends BaseTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ContactListPage contactListPage;
    
    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        loginPage = new LoginPage(driver);
        contactListPage = new ContactListPage(driver);
        loginPage.enterEmail("testeruser@test.com");
        loginPage.enterPassword("Password123");
        loginPage.clicksubmitButton();
        boolean loginSuccess = loginPage.ContactListHeader();
        Assert.assertTrue(loginSuccess, "User successfully signed in");
        TestLogger.logTestResult("Login", loginSuccess);
    }

    @Test
    public void verifyAlignmentOfFields() {        //26. Verify alignment of fields on contact form
        SoftAssert softAssert = new SoftAssert();
        contactListPage.clickAddContact();
        boolean result = contactListPage.areContactFormFieldsDisplayed();
        softAssert.assertTrue(result, "All fields are aligned properly");
        TestLogger.logTestResult("verifyAlignmentOfFields", result);
        softAssert.assertAll();
    }

    @Test
    public void testMaxCharacterLimit() {        //28. Verify max character limit for contact fields
        SoftAssert softAssert = new SoftAssert();
        contactListPage.clickAddContact();
        boolean result = contactListPage.enterMaxFirstNameField("abcdefghijklmnopqrstuvwxyz");
        softAssert.assertTrue(result, "Input fields are not allowing more than 20 characters");
        TestLogger.logTestResult("testMaxCharacterLimit", result);
        softAssert.assertAll();
    }

    @Test
    public void verifyUnicodeAndEmojisInAddressField() {        //29. Verify Unicode and emojis in address field
        SoftAssert softAssert = new SoftAssert();
        contactListPage.clickAddContact();
        contactListPage.enterFirstName("Test");
        contactListPage.enterLastName("User");
        contactListPage.enterAddress(" 日本");
        contactListPage.clickSave();
        boolean result = loginPage.ContactListHeader();
        softAssert.assertTrue(result, "User is able to add emojis and non-English characters in address field");
        TestLogger.logTestResult("verifyUnicodeAndEmojisInAddressField", result);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
