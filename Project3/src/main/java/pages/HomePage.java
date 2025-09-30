package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	private WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@FindBy(className = "ico-login") 
	private WebElement loginLink;
	
	@FindBy(className = "ico-logout")
	private WebElement logoutLink;

	@FindBy(className = "ico-register") 
	private WebElement registerBtn;

	@FindBy(id = "small-searchterms")
	private WebElement searchBox;

	@FindBy(className = "account") 
	private WebElement accountEmailElement;

	@FindBy(className = "search-box-button")
	private WebElement searchButton;

	@FindBy(xpath = "//strong[@class='result']")
	private WebElement noResultMessage;

	@FindBy(xpath = "//ul[@class='list']//a")
	private List<WebElement> categoryLinks;

	@FindBy(xpath = "//h2[@class='product-title']")
	private List<WebElement> searchResults;

	public void clickLogin() {
		wait.until(ExpectedConditions.visibilityOf(loginLink));
		loginLink.click();
	}

	public void clickRegister() {
		wait.until(ExpectedConditions.elementToBeClickable(registerBtn));
		registerBtn.click();
	}

	public boolean isLoginLinkVisible() {
		try {
			wait.until(ExpectedConditions.visibilityOf(loginLink));
			return loginLink.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void selectCategory(String categoryName) {
		for (WebElement category : categoryLinks) {
			String text = category.getText().trim();
			if (text.equalsIgnoreCase(categoryName)) {
				wait.until(ExpectedConditions.elementToBeClickable(category));
				category.click();
				return;
			}
		}
	}

	public boolean isCategoryDisplayed(String categoryName) {
		for (WebElement category : categoryLinks) {
			if (category.getText().equalsIgnoreCase(categoryName)) {
				return category.isDisplayed();
			}
		}
		return false;
	}

	public void enterSearchKeyword(String keyword) {
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.clear();
		searchBox.sendKeys(keyword);
	}

	public void clickSearchButton() {
		wait.until(ExpectedConditions.visibilityOf(searchButton));
		searchButton.click();
	}

	public boolean isSearchResultDisplayed(String keyword) {
		for (WebElement result : searchResults) {
			if (result.getText().toLowerCase().contains(keyword.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean isNoResultMessageDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(noResultMessage));
			return noResultMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isUserLoggedIn(String expectedEmail) {
		String actualEmail = accountEmailElement.getText().trim();
		return actualEmail.equalsIgnoreCase(expectedEmail);   
	}
	
	public void clickLogout() {
		wait.until(ExpectedConditions.visibilityOf(logoutLink));
		logoutLink.click();
	}
}
