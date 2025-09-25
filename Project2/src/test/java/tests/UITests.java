package tests;

import driver.DriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.SignUpPage;
import pages.UIPage;
import pages.CartPage;
import utils.TestLogger2;

@Listeners(utils.Listener2.class)

public class UITests extends BaseTest {

	private WebDriver driver;
	private HomePage homePage;
	private ProductPage productPage;
	private SignUpPage signupPage;
	private LoginPage loginPage;
	private CartPage cartPage;
	private UIPage uiPage;

	@BeforeMethod
	public void setUp() {
		driver = DriverManager.getDriver();
		driver.get("https://www.demoblaze.com/");
		homePage = new HomePage(driver);
		productPage = new ProductPage(driver);
		loginPage = new LoginPage(driver);
		signupPage = new SignUpPage(driver);
		cartPage = new CartPage(driver);
		uiPage = new UIPage(driver);
	}

	@Test
	public void verifyProductTilesAlignment() { //21. Verify alignment of product tiles
		SoftAssert softAssert = new SoftAssert();
		boolean result = false;
		softAssert.assertTrue(productPage.isAnyProductDisplayedInCategory("Phones"), "Phones category products displayed");
		softAssert.assertTrue(productPage.isAnyProductDisplayedInCategory("Laptops"), "Laptops category products displayed");
		softAssert.assertTrue(productPage.isAnyProductDisplayedInCategory("Monitors"), "Monitors category products displayed");
		result = true;
		TestLogger2.logTestResult("verifyProductTilesAlignment", result);
		softAssert.assertAll();
	}

	@Test
	public void verifyActionButtonsVisibility() throws InterruptedException { //22. Check visibility of action buttons
		SoftAssert softAssert = new SoftAssert();
		boolean result = false;
		softAssert.assertTrue(homePage.isLoginDisplayed(), "Login button is displayed");
		homePage.clickLogin();
		softAssert.assertTrue(loginPage.isLoginPopupDisplayed(), "Login popup is displayed");
		homePage.closeLoginPopup();
		softAssert.assertTrue(homePage.isSignUpDisplayed(), "Sign Up button is displayed");
		homePage.clickSignUp();
		softAssert.assertTrue(signupPage.isSignupPopupDisplayed(), "Sign Up popup is displayed");
		homePage.closeSignupPopup();
		homePage.clickLogin();
		loginPage.enterEmail("paya@test.com");
		loginPage.enterPassword("qweqwe");
		loginPage.clickLoginButton();
		softAssert.assertTrue(cartPage.isAddToCartDisplayed(), "Add to Cart button is displayed and clickable");
		result = true;
		TestLogger2.logTestResult("verifyActionButtonsVisibility", result);
		softAssert.assertAll();
	}

	@Test
	public void verifyFontConsistency() {//23. Check font consistency across pages
		SoftAssert softAssert = new SoftAssert();
		boolean result = false;
		String navFont = uiPage.getNavLinkFontFamily();
		productPage.browseCategory("Laptops");
		String productFont = uiPage.getProductTitleFontFamily();
		homePage.clickCart();   
		String cartFont = uiPage.getCartPageFontFamily();
		softAssert.assertEquals(productFont, navFont, "Font-family matches between Navbar and Product page");
		softAssert.assertEquals(cartFont, navFont, "Font-family matches between Navbar and Cart page");
		result = true;
		TestLogger2.logTestResult("verifyFontConsistency", result);
		softAssert.assertAll();
	}

	@Test
	public void verifyAlertAndPopupStyling() throws InterruptedException {
	    SoftAssert softAssert = new SoftAssert();
	    boolean result = false;
	    productPage.browseCategory("Laptops"); 
	    cartPage.SelectProduct();
	    String alertText = cartPage.getAlertTextAndAccept();
	    softAssert.assertTrue(alertText.length() > 0, "Alert text is not empty");
	    homePage.clickCart();  
	    cartPage.clickPlaceOrder();  
	    String modalFont = uiPage.getPurchaseModalFont();
	    String modalSize = uiPage.getPurchaseModalFontSize();
	    softAssert.assertNotNull(modalFont, "Purchase modal font-family is present");
	    softAssert.assertNotNull(modalSize, "Purchase modal font-size is present");
	    result = true;
	    TestLogger2.logTestResult("verifyAlertAndPopupStyling", result);
	    softAssert.assertAll();
	}


	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
