package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "submit")
	private WebElement submitBtn;

	@FindBy(id = "error")
	private WebElement errorMessage;

	@FindBy(tagName = "h1")
	private WebElement ContactListheader;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterEmail(String email) {
		emailField.clear();
		emailField.sendKeys(email);
	}

	public void enterPassword(String password) {
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public void clicksubmitButton() {
		submitBtn.click();
	}

	public String EmptyInputError() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement emptyInputError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='error']")));
		String errorMsg = emptyInputError.getText();
		System.out.println("Error message: " + errorMsg);
		return errorMsg;
	}

	public String InvalidPasswordError() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement existingEmailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='error']")));
		String errorMsg = existingEmailError.getText();
		System.out.println("Error message: " + errorMsg);
		return errorMsg;
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

	public boolean ContactListHeader() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement ContactListheader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Contact List']")));
		return ContactListheader.isDisplayed();
	}
	
	public boolean ContactListheader() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement ContactListheader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Contact List']")));
		return ContactListheader.isDisplayed();
	}

	public boolean PageNotFoundHeader() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement pageNotFoundHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
		return pageNotFoundHeader.isDisplayed();
	}
	
	public boolean FNLNMissingError() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement FnLnMissingError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Contact validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required.']")));
		return FnLnMissingError.isDisplayed();
	}
	public boolean LNMissingError() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement LnMissingError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Validation failed: lastName: Path `lastName` is required.']")));
		return LnMissingError.isDisplayed();
	}
}
