package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

	private WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	@FindBy(xpath = "(//div[@class='product-item']//div[@class='picture']/a/img)[1]") 
	private WebElement FirstProduct;
	
	@FindBy(xpath = "(//div[@class='product-item']//div[@class='picture']/a/img)[2]") 
	private WebElement secondProduct;

	@FindBy(id = "add-to-cart-button-31")
	private WebElement addToCartButton;

	public void clickSecondProduct() {
		wait.until(ExpectedConditions.visibilityOf(secondProduct));
		secondProduct.click();
	}
	
	public void clickFirstProduct() {
		wait.until(ExpectedConditions.visibilityOf(FirstProduct));
		FirstProduct.click();
		WebElement RName = driver.findElement(By.xpath("//input[@id='giftcard_2_RecipientName']"));
		WebElement REmail = driver.findElement(By.xpath("//input[@id='giftcard_2_RecipientEmail']"));
		if (RName.isDisplayed() && REmail.isDisplayed()) {
			RName.sendKeys("Receiver");
			REmail.sendKeys("Receiver@test.com");
		}
	}

	public boolean isAddToCartVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(addToCartButton));
		return addToCartButton.isDisplayed();
	}

	public boolean isCartPageDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(addToCartButton));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
