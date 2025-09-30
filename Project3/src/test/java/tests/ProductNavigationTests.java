package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.ProductPage;
import utils.TestLogger3;

@Listeners(utils.Listener3.class)
public class ProductNavigationTests extends BaseTest {

	private WebDriver driver;
	private HomePage homePage;
	private ProductPage productPage;

	@BeforeMethod
	public void setUp() {
		driver = DriverManager.getDriver();
		driver.get("https://demowebshop.tricentis.com/");
		homePage = new HomePage(driver);
		productPage = new ProductPage(driver);
	}


	@Test
	public void verifyCategoryNavigation() { // 9. Verify category navigation
		SoftAssert softAssert = new SoftAssert();
		homePage.selectCategory("Books");
		boolean isCategoryDisplayed = homePage.isCategoryDisplayed("Books");
		softAssert.assertTrue(isCategoryDisplayed, "Books category products should be displayed");
		TestLogger3.logTestResult("verifyCategoryNavigation", isCategoryDisplayed);
		softAssert.assertAll();
	}

	@Test
	public void verifySearchWithValidKeyword() {//10. Verify product search with valid keyword
		SoftAssert softAssert = new SoftAssert();
		homePage.enterSearchKeyword("Laptop");
		homePage.clickSearchButton();
		boolean isResultDisplayed = homePage.isSearchResultDisplayed("Laptop");
		softAssert.assertTrue(isResultDisplayed, "Products matching 'Laptop' is displayed");
		TestLogger3.logTestResult("verifySearchWithValidKeyword", isResultDisplayed);
		softAssert.assertAll();
	}

	@Test
	public void verifySearchWithNoResults() { // 11. Verify search with no results
		SoftAssert softAssert = new SoftAssert();
		homePage.enterSearchKeyword("XYZ123");
		homePage.clickSearchButton();
		boolean isNoResultMessageDisplayed = homePage.isNoResultMessageDisplayed();
		softAssert.assertTrue(isNoResultMessageDisplayed, "No result message is displayed");
		TestLogger3.logTestResult("verifySearchWithNoResults", isNoResultMessageDisplayed);
		softAssert.assertAll();
	}

	@Test
	public void verifyProductDetailsPage() { //12. Verify product details page
		SoftAssert softAssert = new SoftAssert();
		productPage.clickSecondProduct();
		boolean isAddToCartVisible = productPage.isAddToCartVisible();
		softAssert.assertTrue(isAddToCartVisible, "Product details page and Add to Cart button are displayed");
		TestLogger3.logTestResult("verifyProductDetailsPage", isAddToCartVisible);
		softAssert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
