package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.RegisterPage;
import pages.CartPage;
import pages.CompareandReviewPage;
import utils.TestLogger3;

@Listeners(utils.Listener3.class)
public class UITests extends BaseTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CompareandReviewPage CRPage;
    private RegisterPage regPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://demowebshop.tricentis.com/");
        homePage = new HomePage(driver);
        regPage = new RegisterPage(driver);
        productPage = new ProductPage(driver);
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver);
		CRPage = new CompareandReviewPage(driver);
    }
    @Test
    public void verifyActionLinksVisibility() { //24. verify action links visibility and functionality
        SoftAssert softAssert = new SoftAssert();
        boolean loginLinkVisible = homePage.isLoginLinkVisible(); 
        TestLogger3.logTestResult("Login Link Visible", loginLinkVisible);  
        softAssert.assertTrue(loginLinkVisible, "Login link should be visible");    
        boolean registerLinkVisible = regPage.isregisterLinkdisplayed();
        TestLogger3.logTestResult("Register Link Visible", registerLinkVisible);
        softAssert.assertTrue(registerLinkVisible, "Register link should be visible");
        boolean addToCartVisible = cartPage.isShoppingCartLinkDisplayed();  
        TestLogger3.logTestResult("Shopping Cart Link Visible", addToCartVisible);
        softAssert.assertTrue(addToCartVisible, "Shopping cart link should be visible");
        softAssert.assertAll();
    }
    
    @Test
    public void testBackButtonAfterLogout() { //25. Verify user cannot go back after logout
        SoftAssert softAssert = new SoftAssert();
        homePage.clickLogin();
		loginPage.enterEmail("demo@web.shop");
		loginPage.enterPassword("demowebshop");
		loginPage.clickLoginButton(); 
		homePage.clickLogout();                         
        driver.navigate().back();
        boolean loginLinkVisible = homePage.isLoginLinkVisible(); 
        softAssert.assertTrue(loginLinkVisible,"User is not able to access previous pages after logout");
    }

    @Test
    public void testFontConsistencyAcrossPages() { //26. Verify font consistency across pages
        SoftAssert softAssert = new SoftAssert();
        boolean consistent = CRPage.isFontConsistent();
        softAssert.assertTrue(consistent,"Font styles are inconsistent across pages");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
