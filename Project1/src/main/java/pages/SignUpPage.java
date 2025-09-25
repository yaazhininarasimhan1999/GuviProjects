package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends BasePage {

	@FindBy(id = "signup")
	private WebElement signupBtn;

	@FindBy(id = "firstName")
	private WebElement firstName;

	@FindBy(id = "lastName")
	private WebElement lastName;

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(tagName = "h1")
	private WebElement ContactListheader;

	@FindBy(id = "submit")
	private WebElement submitBtn;

	@FindBy(id = "error")
	private WebElement errorMessage;

	@FindBy(id = "confirmPassword")
	private WebElement confirmPasswordField;

	@FindBy(id = "phone")
	private WebElement phoneField;

	@FindBy(id = "termsCheckbox")
	private WebElement termsCheckbox;

	@FindBy(id = "successMessage")
	private WebElement successMessage;

	public SignUpPage(WebDriver driver) {
		super(driver);
	}

	public void clickSignUp() { 
		signupBtn.click(); 
	}

	public void enterfirstName(String FirstName) { 
		firstName.sendKeys(FirstName); 
	}

	public void enterlastName(String LastName) { 
		lastName.sendKeys(LastName);
	}

	public void enterEmail(String email) { 
		emailField.sendKeys(email); 
	}

	public void enterPassword(String password) { 
		passwordField.sendKeys(password);
	}

	public void clickSubmit() { 
		submitBtn.click(); 
	}

	public boolean ContactListHeader() { 
		return ContactListheader.isDisplayed();
	}

	public String getExistingEmailError() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement existingEmailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='error']")));
		String errorMsg = existingEmailError.getText();
		System.out.println("Error message: " + errorMsg);
		return errorMsg;
	}

	public String EmptyInputError() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement emptyInputError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='error']")));
		String errorMsg = emptyInputError.getText();
		System.out.println("Error message: " + errorMsg);
		return errorMsg;
	}

	public String InvalidEmailFormatError() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement invalidEmailFormat = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='error']")));
		String errorMsg = invalidEmailFormat.getText();
		System.out.println("Error message: " + errorMsg);
		return errorMsg;
	}

}
