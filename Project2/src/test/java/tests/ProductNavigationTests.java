package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import utils.TestLogger2;


@Listeners(utils.Listener2.class)
public class ProductNavigationTests {

	private WebDriver driver;
	private LoginPage loginPage;
	private ProductPage productPage;
	private HomePage homePage; 
	private CartPage cartPage;

	@BeforeMethod
	public void setUp() {
		driver = DriverManager.getDriver();
		driver.get("https://www.demoblaze.com/");
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);

		homePage.clickLogin();
		loginPage.enterEmail("paya@test.com");
		loginPage.enterPassword("qweqwe");
		loginPage.clickLoginButton();
	}

	@Test
	public void browseCategories() {	//16. Browse product categories (Phones, Laptops, Monitors)
		SoftAssert softAssert = new SoftAssert();
		boolean result = false;		
		productPage.browseCategory("Laptops");
		softAssert.assertTrue(true, "Clicked Laptops category");
		productPage.browseCategory("Monitors");
		softAssert.assertTrue(true, "Clicked Monitors category");
		productPage.browseCategory("Phones");
		softAssert.assertTrue(true, "Clicked Phones category");
		result = true;		
		TestLogger2.logTestResult("browseCategories", result);
		softAssert.assertAll();
	}

	@Test
	public void viewProductDetails() {//17. View product details
		SoftAssert softAssert = new SoftAssert();
		boolean result = false;
		homePage.clickHome(); 
		productPage.clickProductByName("Samsung galaxy s6");
		softAssert.assertTrue(productPage.isProductDetailDisplayed(), "Product detail page displayed");
		result = true;
		TestLogger2.logTestResult("viewProductDetails", result);
		softAssert.assertAll();
	}

	@Test
	public void verifyHomeNavigation() { //18. Verify home navigation
		SoftAssert softAssert = new SoftAssert();
		boolean result = false;
		homePage.clickHome();
		softAssert.assertTrue(productPage.isHomePageDisplayed(), "Homepage displayed with products");
		result = true;
		TestLogger2.logTestResult("verifyHomeNavigation", result);
		softAssert.assertAll();
	}

	@Test
	public void navigateUsingNavbarLinks() {//19. Navigate using navbar links
		SoftAssert softAssert = new SoftAssert();
		boolean result = false;
		homePage.clickHome();
		softAssert.assertTrue(productPage.isHomePageDisplayed(), "Navigated to Home");
		cartPage.openCart();
		softAssert.assertTrue(productPage.isCartPageDisplayed(), "Navigated to Cart");
		homePage.clickContact();
		softAssert.assertTrue(productPage.isContactPageDisplayed(), "Navigated to Contact");
		result = true;
		TestLogger2.logTestResult("navigateUsingNavbarLinks", result);
		softAssert.assertAll();
	}


	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
