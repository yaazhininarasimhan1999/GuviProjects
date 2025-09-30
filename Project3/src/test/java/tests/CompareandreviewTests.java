package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.CompareandReviewPage;
import utils.TestLogger3;

@Listeners(utils.Listener3.class)
public class CompareandreviewTests extends BaseTest {

	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private ProductPage productPage;
	private CompareandReviewPage CRPage;

	@BeforeMethod
	public void setUp() {
		driver = DriverManager.getDriver();
		driver.get("https://demowebshop.tricentis.com/");
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		CRPage = new CompareandReviewPage(driver);
		homePage.clickLogin();
		loginPage.enterEmail("demo@web.shop");
		loginPage.enterPassword("demowebshop");
		loginPage.clickLoginButton(); 
	}

	@Test
	public void addProductToComparelist() {//20. Add product to compare list
		SoftAssert softAssert = new SoftAssert();
		productPage.clickSecondProduct();
		CRPage.clickAddToComparelist();
		boolean added = CRPage.isProductInComparelist();
		softAssert.assertTrue(added, "Product should be added in Compare List");
		TestLogger3.logTestResult("addProductToWishlist", added);
		softAssert.assertAll();
	}

	@Test
	public void removeProductToComparelist() {//21. Clear product from compare list
		SoftAssert softAssert = new SoftAssert();
		productPage.clickSecondProduct();
		CRPage.clickAddToComparelist();
		CRPage.clearCompareList();
		boolean removed = !(CRPage.isProductInComparelist());
		softAssert.assertTrue(removed, "Product should be removed in Compare List");
		TestLogger3.logTestResult("removeProductToComparelist", removed);
		softAssert.assertAll();
	}
	
	@Test
	public void addReviewtoProduct() {//22. Verify adding review to the product
		SoftAssert softAssert = new SoftAssert();
		productPage.clickSecondProduct();
		CRPage.clickAddReview();
		CRPage.addReview();
		boolean result = CRPage.isReviewAdded();
		softAssert.assertTrue(result, "User successfully added review to the product");
		TestLogger3.logTestResult("addReviewtoProduct", result);
		softAssert.assertAll();
	}

	@Test
	public void verifyLogOut() { //23. Verify Logout scenario
		SoftAssert softAssert = new SoftAssert();
		homePage.enterSearchKeyword("Laptop");
		homePage.clickSearchButton();
		homePage.clickLogout();
		boolean result = homePage.isLoginLinkVisible(); ;
		softAssert.assertTrue(result, "User successfully logged out");
		TestLogger3.logTestResult("verifyLogOut", result);
		softAssert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
