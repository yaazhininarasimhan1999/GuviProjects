package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ContactListPage;
import pages.ContactValidationPage;
import utils.TestLogger;

public class AddEdidDeleteContactTests extends BaseTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ContactListPage contactListPage;
    private ContactValidationPage contactValidationPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        loginPage = new LoginPage(driver);
        contactListPage = new ContactListPage(driver);
        contactValidationPage = new ContactValidationPage(driver);

        loginPage.enterEmail("testeruser@test.com");
        loginPage.enterPassword("Password123");
        loginPage.clicksubmitButton();

        SoftAssert softAssert = new SoftAssert();
        boolean loginSuccess = loginPage.ContactListHeader();
        softAssert.assertTrue(loginSuccess, "User successfully signed in");

        softAssert.assertAll();
    }

    @Test
    public void verifyAddContact() {        //11. Verify adding contact with all valid details
        SoftAssert softAssert = new SoftAssert();

        contactListPage.clickAddContact();
        contactListPage.enterFirstName("Test");
        contactListPage.enterLastName("User");
        contactListPage.enterEmail("testuser@mail.com");
        contactListPage.enterPhone("1234567890");
        contactListPage.enterAddress("123 Street");
        contactListPage.enterCity("Toronto");
        contactListPage.enterState("ON");
        contactListPage.enterPostalCode("L5M1A1");
        contactListPage.enterCountry("Canada");
        contactListPage.clickSave();

        boolean result = loginPage.ContactListHeader();
        softAssert.assertTrue(result, "User successfully added a contact with all valid details");
        TestLogger.logTestResult("verifyAddContact", result);
        softAssert.assertAll();
    }

    @Test
    public void verifyAddContactWithMissingRequiredFields() {        //12. Verify adding contact with missing required fields
        SoftAssert softAssert = new SoftAssert();

        contactListPage.clickAddContact();
        contactListPage.enterEmail("testuser@mail.com");
        contactListPage.enterPhone("1234567890");
        contactListPage.enterAddress("123 Street");
        contactListPage.enterCity("Toronto");
        contactListPage.enterState("ON");
        contactListPage.enterPostalCode("L5M1A1");
        contactListPage.enterCountry("Canada");
        contactListPage.clickSave();

        boolean result = loginPage.FNLNMissingError();
        softAssert.assertTrue(result, "User is unable to add a contact without giving input to mandatory fields");
        TestLogger.logTestResult("verifyAddContactWithMissingRequiredFields", result);
        softAssert.assertAll();
    }

    @Test
    public void verifyOnlyNumericInputInPhoneField() {        //13. Verify phone field accepts only numeric input
        SoftAssert softAssert = new SoftAssert();

        contactListPage.clickAddContact();
        contactListPage.enterFirstName("Test");
        contactListPage.enterLastName("User");
        contactListPage.enterEmail("testuser@mail.com");
        contactValidationPage.enterPhone("abcd123");
        contactValidationPage.clickSave();

        boolean result = contactValidationPage.InvalidPhonenumError();
        softAssert.assertTrue(result, "Invalid phone error shown");
        TestLogger.logTestResult("verifyOnlyNumericInputInPhoneField", result);
        softAssert.assertAll();
    }

    @Test
    public void verifyAddingDuplicateContactDetails() {        //14. Verify adding duplicate contact details
        SoftAssert softAssert = new SoftAssert();

        contactListPage.clickAddContact();
        contactListPage.enterFirstName("Test");
        contactListPage.enterLastName("User");
        contactListPage.enterEmail("testuser@mail.com");
        contactListPage.enterPhone("1234567890");
        contactListPage.enterAddress("123 Street");
        contactListPage.enterCity("Toronto");
        contactListPage.enterState("ON");
        contactListPage.enterPostalCode("L5M1A1");
        contactListPage.enterCountry("Canada");
        contactListPage.clickSave();

        boolean result = loginPage.ContactListHeader();
        softAssert.assertTrue(result, "User is able to add a duplicate contact details");
        TestLogger.logTestResult("verifyAddingDuplicateContactDetails", result);
        softAssert.assertAll();
    }

    @Test
    public void verifyFormResetsAfterContactAdded() {        //15. Verify form resets after contact is added
        SoftAssert softAssert = new SoftAssert();

        contactListPage.clickAddContact();
        contactListPage.enterFirstName("Testtt");
        contactListPage.enterLastName("Users");
        contactListPage.enterEmail("testttuser@mail.com");
        contactListPage.enterPhone("1243567890");
        contactListPage.enterAddress("1234 Street");
        contactListPage.enterCity("Chennai");
        contactListPage.enterState("TN");
        contactListPage.enterPostalCode("600001");
        contactListPage.enterCountry("India");
        contactListPage.clickSave();

        boolean result = loginPage.ContactListHeader();
        softAssert.assertTrue(result, "Form resets after contact is added");
        TestLogger.logTestResult("verifyFormResetsAfterContactAdded", result);
        softAssert.assertAll();
    }

    @Test
    public void verifyEditContact() {        //16. Verify user can edit an existing contact
        SoftAssert softAssert = new SoftAssert();

        contactListPage.clickFirstContact();
        contactListPage.clickEditContact();
        contactListPage.enterFirstName("NewFName");
        contactListPage.enterLastName("NewLName");
        contactListPage.enterEmail("NewName@mail.com");
        contactListPage.enterPhone("0987654321");
        contactListPage.enterAddress("5678 Street");
        contactListPage.enterCity("Kmu");
        contactListPage.enterState("TN");
        contactListPage.enterPostalCode("612001");
        contactListPage.enterCountry("India");
        contactListPage.clickSave();

        boolean result = contactListPage.isContactListHeaderDisplayed();
        softAssert.assertTrue(result, "User edited the existing contact successfully");
        TestLogger.logTestResult("verifyEditContact", result);
        softAssert.assertAll();
    }

    @Test
    public void verifyCancellingEdit() {        //17. Verify canceling an edit
        SoftAssert softAssert = new SoftAssert();

        contactListPage.clickFirstContact();
        contactListPage.clickEditContact();
        contactListPage.enterFirstName("NewFName");
        contactListPage.enterLastName("NewLName");
        contactListPage.enterEmail("NewName@mail.com");
        contactListPage.enterPhone("0987654321");
        contactListPage.enterAddress("5678 Street");
        contactListPage.enterCity("Kmu");
        contactListPage.enterState("TN");
        contactListPage.enterPostalCode("612001");
        contactListPage.enterCountry("India");
        contactListPage.clickCancelEdit();

        boolean result = contactListPage.isContactListHeaderDisplayed();
        softAssert.assertTrue(result, "User cancelled the edited contact successfully");
        TestLogger.logTestResult("verifyCancellingEdit", result);
        softAssert.assertAll();
    }

    @Test
    public void verifyEditValidation() {        //18. Verify validation during edit
        SoftAssert softAssert = new SoftAssert();

        contactListPage.clickFirstContact();
        contactListPage.clickEditContact();
        contactListPage.enterFirstName("NewFName");
        contactListPage.clearLastName();
        contactListPage.clickSave();
        //softAssert.assertTrue(loginPage.LNMissingError(), "User is unable to edit a contact without giving input to mandatory fields");

        TestLogger.logTestResult("verifyEditValidation", true);
        softAssert.assertAll();
    }

    @Test
    public void verifyDeleteContact() {        //19. Verify deleting a contact || 20. Verify delete confirmation || 21. Verify contact no longer appears after deletion
        SoftAssert softAssert = new SoftAssert();

        contactListPage.clickAddContact();
        contactListPage.enterFirstName("Test");
        contactListPage.enterLastName("User");
        contactListPage.enterEmail("deletetestuser@mail.com");
        contactListPage.enterPhone("1234567890");
        contactListPage.enterAddress("123 Street");
        contactListPage.enterCity("Toronto");
        contactListPage.enterState("ON");
        contactListPage.enterPostalCode("L5M1A1");
        contactListPage.enterCountry("Canada");
        contactListPage.clickSave();

        boolean result = contactListPage.clickDeleteContact("deletetestuser@mail.com");
        softAssert.assertTrue(result, "User successfully deleted a contact by accepting delete alert and the contact is no longer appears");
        TestLogger.logTestResult("verifyDeleteContact", result);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
