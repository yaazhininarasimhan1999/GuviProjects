package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ContactListPage;
import utils.TestLogger;

@Listeners(utils.Listener.class)
public class SessionTests extends BaseTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ContactListPage contactListPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        loginPage = new LoginPage(driver);
        contactListPage = new ContactListPage(driver);
    }

    @Test
    public void verifyLogout() { //22. Verify logout redirects to login page
        SoftAssert softAssert = new SoftAssert();

        loginPage.enterEmail("testeruser@test.com");
        loginPage.enterPassword("Password123");
        loginPage.clicksubmitButton();

        boolean loginSuccess = loginPage.ContactListHeader();
        softAssert.assertTrue(loginSuccess, "User successfully signed in");

        contactListPage.clickLogout();
        boolean logoutSuccess = contactListPage.isLoginHeaderPresent();
        softAssert.assertTrue(logoutSuccess, "Logout redirects to login page successfully");

        TestLogger.logTestResult("verifyLogout", loginSuccess && logoutSuccess);

        softAssert.assertAll();
    }

    @Test
    public void verifyLoginStateOnRefresh() { //23. Verify login state on refresh
        SoftAssert softAssert = new SoftAssert();

        loginPage.enterEmail("testeruser@test.com");
        loginPage.enterPassword("Password123");
        loginPage.clicksubmitButton();

        boolean loginSuccess = loginPage.ContactListHeader();
        softAssert.assertTrue(loginSuccess, "User successfully signed in");

        driver.navigate().refresh();
        boolean stillLoggedIn = loginPage.ContactListHeader();
        softAssert.assertTrue(stillLoggedIn, "User stays signed in after refresh");

        TestLogger.logTestResult("verifyLoginStateOnRefresh", loginSuccess && stillLoggedIn);

        softAssert.assertAll();
    }

    @Test
    public void verifyLoginRequiredForContactList() { //24. Verify login is required to access contact list
        SoftAssert softAssert = new SoftAssert();

        driver.get("https://thinking-tester-contact-list.herokuapp.com/contact-list");

        boolean accessDenied = loginPage.PageNotFoundHeader();
        softAssert.assertTrue(accessDenied, "User is not able to access contact list without logging in");

        TestLogger.logTestResult("verifyLoginRequiredForContactList", accessDenied);

        softAssert.assertAll();
    }

    @Test
    public void verifyCannotAccessContactListAfterLogout() { //25. Verify user cannot go back to contact list after logout
        SoftAssert softAssert = new SoftAssert();

        loginPage.enterEmail("testeruser@test.com");
        loginPage.enterPassword("Password123");
        loginPage.clicksubmitButton();

        boolean loginSuccess = loginPage.ContactListHeader();
        softAssert.assertTrue(loginSuccess, "User successfully signed in");

        contactListPage.clickLogout();
        driver.navigate().back();

        boolean redirected = contactListPage.isLoginHeaderPresent();
        softAssert.assertTrue(redirected, "Logout redirects to login page successfully");

        TestLogger.logTestResult("verifyCannotAccessContactListAfterLogout", loginSuccess && redirected);

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
