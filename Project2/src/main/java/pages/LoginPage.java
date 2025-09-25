package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Basepage {

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "login2")
	private WebElement loginButton;

	@FindBy(id = "loginusername")
	private WebElement emailInput;

	@FindBy(id = "loginpassword")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement loginSubmitButton;

	@FindBy(id = "nameofuser")
	private WebElement WelcomeText;

	public void enterEmail(String email) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(emailInput));
		emailInput.clear();
		emailInput.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(passwordInput));
		passwordInput.clear();
		passwordInput.sendKeys(password);
	}

	public void clickLoginButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(loginSubmitButton)).click();
	}

	public boolean isLoginSuccessful() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(WelcomeText));
		return WelcomeText.isDisplayed();
	}

	public boolean isLogoutSuccessful() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		return loginButton.isDisplayed();
	}

	public String getLoginAlertText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String text = alert.getText();
		try {
			return text;
		} catch (Exception e) {
			return "";
		}
	}

	public WebElement getPasswordField() {
		return passwordInput;
	}

	public boolean isLoginPopupDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(emailInput));
		return emailInput.isDisplayed();
	}

}

