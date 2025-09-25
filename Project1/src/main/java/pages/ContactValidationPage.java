package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactValidationPage extends BasePage {

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "submit")
    private WebElement saveBtn;

    @FindBy(id = "error")
    private WebElement errorMsg;

    public ContactValidationPage(WebDriver driver) {
        super(driver);
    }

    public void clearFirstName() {
        firstName.clear();
    }

    public void clearLastName() {
        lastName.clear();
    }

    public void enterPhone(String phoneText) {
        phone.clear();
        phone.sendKeys(phoneText);
    }

    public void clickSave() {
        saveBtn.click();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOf(errorMsg)).getText();
    }
    
    public boolean InvalidPhonenumError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement invalidPhonenumError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        return invalidPhonenumError.isDisplayed();
    }

}
