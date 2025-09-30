package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(className = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'message-error')]")
    private WebElement errorMessage;

    @FindBy(id = "RememberMe")
    private WebElement rememberMeCheckbox;

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public String getLoginErrorText() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public void clickRememberMe() {
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
    }
    
    public void checkRememberMe(boolean check) {
        if (rememberMeCheckbox.isSelected() != check) {
            rememberMeCheckbox.click();
        }
    }
}
