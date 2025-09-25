package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends Basepage {

	public SignUpPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "signin2")
	private WebElement signUpLink;

	@FindBy(id = "sign-username")
	private WebElement usernameField;

	@FindBy(id = "sign-password")
	private WebElement passwordField;

	@FindBy(xpath = "//button[text()='Sign up']")
	private WebElement signUpButton;

	public void clickSignUpLink() {
		signUpLink.click();
	}

	public void enterUsername(String username) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.clear();
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(passwordField));
		passwordField.clear();
		passwordField.sendKeys(password);
	}


	public void clickSignUpButton() {
		signUpButton.click();
	}

	public String getAlertTextAndAccept() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String text = alert.getText(); 
		alert.accept(); 
		return text; 
	}
	public boolean isSignupPopupDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		return usernameField.isDisplayed();
	}
}
