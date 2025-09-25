package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage extends Basepage {

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "orderModal")
    private WebElement placeOrderModal;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement creditCardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    @FindBy(xpath = "//div[@id='orderModal']//button[text()='Close']")
    private WebElement closeButton;

    @FindBy(xpath = "//p[@class='lead text-muted ']")
    private WebElement confirmationModel;

    public void openPlaceOrderModal() {
        placeOrderModal.click();
    }

    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        creditCardField.sendKeys(card);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
    }

    public void clickPurchase() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement purchaseBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Purchase']")));
        purchaseBtn.click();
    }

    public void closeModal() {
        closeButton.click();
    }

    public String getConfirmationText() {
        return confirmationModel.getText();
    }

    public boolean isPlaceOrderModalDisplayed() {
        return placeOrderModal.isDisplayed();
    }

    public boolean isConfirmationDisplayed() {
        return confirmationModel.isDisplayed();
    }
}
