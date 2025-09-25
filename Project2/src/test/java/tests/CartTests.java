package tests;

import driver.DriverManager;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import pages.LoginPage;
import pages.HomePage;
import pages.CartPage;
import utils.TestLogger2;

@Listeners(utils.Listener2.class)
public class CartTests extends BaseTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private CartPage cartPage;

	@BeforeMethod
	public void setUp() {
		driver = DriverManager.getDriver();
		driver.get("https://www.demoblaze.com/");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		cartPage = new CartPage(driver);
		homePage.clickLogin();
		loginPage.enterEmail("paya@test.com");
		loginPage.enterPassword("qweqwe");
		loginPage.clickLoginButton();
	}

	@Test
	public void verifyAddProductToCart() { // 9. Add product to cart
		String productName = "";
		SoftAssert softAssert = new SoftAssert();
		try {
			productName = cartPage.SelectProductByIndex(1);
			String alertText = cartPage.getAlertTextAndAccept();
			boolean result = alertText.toLowerCase().contains("product added");
			softAssert.assertTrue(result, "User successfully added a product to cart and confirmed the alert");
			TestLogger2.logTestResult("verifyAddProductToCart", result);
		} catch (InterruptedException e) {
			e.printStackTrace();
			softAssert.fail("Product selection interrupted.");
		}
		softAssert.assertAll();
	}


	@Test
	public void viewCartwithAddedproducts() { // 10. View cart with added products
	    SoftAssert softAssert = new SoftAssert();
	    String productNameFromItemPage = "";
	    boolean result = false;
	    try {
			productNameFromItemPage = cartPage.SelectProduct();
		} catch (InterruptedException e) {
		}
		cartPage.getAlertTextAndAccept();
	    cartPage.openCart();
	    String productNameFromCart = cartPage.getFirstProductNameInCart();
	    softAssert.assertEquals(productNameFromCart, productNameFromItemPage,"Cart is displaying the added products with title and price.");
	    result = productNameFromCart.equals(productNameFromItemPage);
	    TestLogger2.logTestResult("viewCartwithAddedproducts", result);
	    softAssert.assertAll();
	}

	@Test
	public void RemoveProductFromCart() { // 11. Remove product from cart
	    SoftAssert softAssert = new SoftAssert();
	    boolean result = false;
	 try {
		 cartPage.SelectProduct(); 
		 cartPage.getAlertTextAndAccept(); 
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		  softAssert.fail("Product selection interrupted."); 
		  }		 
	    cartPage.openCart();
	    cartPage.deleteFirstProductInCart();
	    result = true;
	    Assert.assertTrue(result, "User successfully deleted the product.");
	    TestLogger2.logTestResult("RemoveProductFromCart", result);
	    softAssert.assertAll();
	}

	@Test
	public void AddMultipleProductsToCart() { // 12. Add multiple products to cart
	    SoftAssert softAssert = new SoftAssert();
	    boolean result = false;
	    try {
	        cartPage.SelectProductByIndex(1);
	        cartPage.getAlertTextAndAccept();
	        homePage.clickHome();
	        cartPage.SelectProductByIndex(2);
	        cartPage.getAlertTextAndAccept();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	        softAssert.fail("Product selection failed.");
	    }
	    cartPage.openCart();
	    List<String> productsInCart = cartPage.getProductNamesInCart();
	    int totalPrice = cartPage.getTotalPrice();
	    result = productsInCart.size() >= 2;
	    softAssert.assertTrue(result,"User successfully added multiple products in the cart. Total Price: $" + totalPrice);
	    TestLogger2.logTestResult("AddMultipleProductsToCart", result);
	    softAssert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
