package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.OrderPage;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import utils.TestLogger3;

@Listeners(utils.Listener3.class)
public class OrderTests extends BaseTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private OrderPage orderPage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://demowebshop.tricentis.com/");
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
        loginPage = new LoginPage(driver);
        homePage.clickLogin();
		loginPage.enterEmail("demo@web.shop");
		loginPage.enterPassword("demowebshop");
		loginPage.clickLoginButton(); 
		}

    @Test
    public void placeOrderWithValidDetails() {//17. Place order with valid details
        SoftAssert softAssert = new SoftAssert();
        productPage.clickSecondProduct();
		cartPage.clickAddToCart();
	    cartPage.clickShoppingCartLink();
        cartPage.clickCheckOut();
        orderPage.fillBillingAndShipping("Test User", "123 Street", "City", "State", "12345", "India", "9876543211");
        orderPage.BillingAddressContinue();
        orderPage.ShippingAddressContinue();
        orderPage.ShippingMethodContinue();
        orderPage.PaymentMethodContinue();
        orderPage.PaymentInfoContinue();
        orderPage.clickConfirm();
        boolean isOrderConfirmed = orderPage.isOrderConfirmed();
        softAssert.assertTrue(isOrderConfirmed, "Order should be placed successfully");
        TestLogger3.logTestResult("placeOrderWithValidDetails", isOrderConfirmed);
        softAssert.assertAll();
    }

    @Test
    public void placeOrderWithMissingDetails() {//18. Place order with missing details
        SoftAssert softAssert = new SoftAssert();
        productPage.clickSecondProduct();
		cartPage.clickAddToCart();
	    cartPage.clickShoppingCartLink();
        cartPage.clickCheckOut();
        orderPage.fillNewBillingAndShipping("Test User", "123 Street", "City", "State", "12345", "India", "");
        //orderPage.clickConfirm();
        boolean errorDisplayed = orderPage.isOrderErrorDisplayed();
        softAssert.assertTrue(errorDisplayed, "Error displayed for missing mandatory fields");
        TestLogger3.logTestResult("placeOrderWithMissingDetails", errorDisplayed);
        softAssert.assertAll();
    }

    @Test
    public void verifyOrderInHistory() {// 19. Verify order appears in order history
        SoftAssert softAssert = new SoftAssert();
        productPage.clickSecondProduct();
		cartPage.clickAddToCart();
	    cartPage.clickShoppingCartLink();
        cartPage.clickCheckOut();
        orderPage.fillBillingAndShipping("Test User", "123 Street", "City", "State", "12345", "India", "9876543211");
        orderPage.BillingAddressContinue();
        orderPage.ShippingAddressContinue();
        orderPage.ShippingMethodContinue();
        orderPage.PaymentMethodContinue();
        orderPage.PaymentInfoContinue();
        orderPage.clickConfirm();
        String orNum = orderPage.FetchOrderNum();
        System.out.println(orNum);
        orderPage.navigateToOrders();
        boolean orderPresent = orderPage.isOrderPresent(orNum);
        softAssert.assertTrue(orderPresent, "Latest order should appear in order history");
        TestLogger3.logTestResult("verifyOrderInHistory", orderPresent);
        softAssert.assertAll(); 
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
