package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderPage;
import utils.TestLogger2;

@Listeners(utils.Listener2.class)
public class OrderTests extends BaseTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private OrderPage orderPage;

    @BeforeMethod
    public void setUp() {
    	driver = DriverManager.getDriver();
		driver.get("https://www.demoblaze.com/");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		cartPage = new CartPage(driver);
		orderPage = new OrderPage(driver);  
		homePage.clickLogin();
		loginPage.enterEmail("paya@test.com");
		loginPage.enterPassword("qweqwe");
		loginPage.clickLoginButton();
    }

    @Test
    public void placeOrderWithValidDetails() { //13. Place order with valid details
        SoftAssert softAssert = new SoftAssert();
        boolean result = false;
        try {
        	cartPage.SelectProductByIndex(1);
        	cartPage.getAlertTextAndAccept();
            cartPage.openCart();
            cartPage.clickPlaceOrder();
            orderPage.fillOrderForm("Customer", "India", "TN", "1234567890123456", "09", "2025");
            orderPage.clickPurchase();
            result = orderPage.isConfirmationDisplayed() && orderPage.getConfirmationText().contains("Id");
            softAssert.assertTrue(result, "Order placed successfully with valid details.");
        } catch (Exception e) {
            softAssert.fail("Order placement failed.");
        }
        TestLogger2.logTestResult("placeOrderWithValidDetails", result);
        softAssert.assertAll();
    }

    @Test
    public void placeOrderWithEmptyForm() { //14. Place order with empty form
        SoftAssert softAssert = new SoftAssert();
        boolean result = false;
        try {
            cartPage.SelectProduct();
            cartPage.getAlertTextAndAccept();
            cartPage.openCart();
            cartPage.clickPlaceOrder();
            orderPage.clickPurchase();
            String alertText = cartPage.getAlertTextAndAccept();
            softAssert.assertEquals(alertText, "Please fill out Name and Creditcard.");
            result = alertText.equals("Please fill out Name and Creditcard.");
        } catch (Exception e) {
            softAssert.fail("Order placement with empty form failed due to: " + e.getMessage());
        }
        TestLogger2.logTestResult("placeOrderWithEmptyForm", result);
        //softAssert.assertAll();
    }
    
    @Test 
    public void verifyOrderConfirmationPopup() { // 15. Verify order confirmation popup
        SoftAssert softAssert = new SoftAssert();
        boolean result = false;
        try {
            cartPage.SelectProductByIndex(1);
            cartPage.getAlertTextAndAccept();
            cartPage.openCart();
            cartPage.clickPlaceOrder();
            orderPage.fillOrderForm("Customer", "India", "TN", "1234567890123456", "09", "2025");
            orderPage.clickPurchase();
            String confirmationText = orderPage.getConfirmationText();
            softAssert.assertTrue(orderPage.isConfirmationDisplayed(), "Confirmation popup is displayed.");
            softAssert.assertTrue(confirmationText.contains("Amount"), "Confirmation contains amount.");
            softAssert.assertTrue(confirmationText.contains("Id"), "Confirmation contains unique order ID.");
            result = true; 
        } catch (Exception e) {
            softAssert.fail("Order confirmation popup verification failed due to: " + e.getMessage());
        }
        TestLogger2.logTestResult("verifyOrderConfirmationPopup", result);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
