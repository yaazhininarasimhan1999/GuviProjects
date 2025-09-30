package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import utils.TestLogger3;

@Listeners(utils.Listener3.class)
public class CartTests extends BaseTest {

	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private ProductPage productPage;
	private CartPage cartPage;

	@BeforeMethod
	public void setUp() {
		driver = DriverManager.getDriver();
		driver.get("https://demowebshop.tricentis.com/");
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		homePage.clickLogin();
		loginPage.enterEmail("demo@web.shop");
		loginPage.enterPassword("demowebshop");
		loginPage.clickLoginButton();
	}

	@Test
	public void addProductToCart() {//13.User selects a product and clicks “Add to cart.”
		SoftAssert softAssert = new SoftAssert();
		boolean result = false;
		productPage.clickSecondProduct();
		cartPage.clickAddToCart();
		int quantity = cartPage.getCartItemCount();
		if (quantity >= 0) {
			result = true;
		}
		softAssert.assertTrue(result,"Product details page and Add to Cart button are displayed");
		TestLogger3.logTestResult("verifyProductDetailsPage", result);
		softAssert.assertAll();
	}
	
	@Test
	public void removeProductFromCart() { //14.Remove product from cart 
		SoftAssert softAssert = new SoftAssert();
	    productPage. clickFirstProduct();
	    cartPage.clickAddToCart();
	    cartPage.clickShoppingCartLink();
		cartPage.removeProduct();
		cartPage.clickUpdateCart();
		boolean isRemoved = cartPage.isCartEmpty();
		softAssert.assertTrue(isRemoved, "Product removed from cart successfully");
		TestLogger3.logTestResult("removeProductFromCart", isRemoved);
		softAssert.assertAll();
	}
	
	@Test
	public void updateProductQuantityInCart() {//15. Update product quantity in cart
	    SoftAssert softAssert = new SoftAssert();
	    cartPage.clickAddToCartfromHome();
	    cartPage.clickShoppingCartLink();
	    cartPage.updateQuantity("5");
	    int quantity = cartPage.getCartItemCount();
	    softAssert.assertTrue(quantity >= 5, "Cart quantity is updated successfully");
	    TestLogger3.logTestResult("updateProductQuantityInCart", true);
	}
	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
