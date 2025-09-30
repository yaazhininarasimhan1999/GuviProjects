package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "ico-register")
    private WebElement registerLink;

    @FindBy(id = "FirstName")
    private WebElement firstNameField;

    @FindBy(id = "LastName")
    private WebElement lastNameField;

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "register-button")
    private WebElement registerButton;
    
    @FindBy(xpath = "//div[@class='result']")
    private WebElement registerSuccessMsg;

    @FindBy(xpath = "//li[contains(text(), 'The specified email already exists')]")
    private WebElement registeredEmailText;

    @FindBy(className = "ico-logout")
    private WebElement logoutLink;

    public void goToRegisterPage() {
        registerLink.click();
    }
    
    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
        passwordField.click();
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
    }

    public void clickRegister() {
        registerButton.click();
    }
    
    public String getSuccessfulRegistrationMsg() {
    	return registerSuccessMsg.getText();
    }
    
    public String getRegisteredEmail() {
		wait.until(ExpectedConditions.visibilityOf(registeredEmailText));
        return registeredEmailText.getText();
    }
    
    public String getFieldErrorMessage() {
        return driver.findElement(By.className("field-validation-error")).getText();
    }

    public boolean isregisterLinkdisplayed() {
    	return registerLink.isDisplayed();
    }
}
