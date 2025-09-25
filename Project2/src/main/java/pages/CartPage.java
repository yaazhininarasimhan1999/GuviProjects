package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CartPage extends Basepage {

	public CartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

	@FindBy(id = "cartur")
	private WebElement cartBtn;

	@FindBy(css = "#tbodyid > tr")
	private List<WebElement> productRows;

	@FindBy(xpath = "//button[text()='Place Order']")
	private WebElement placeOrderButton;

	@FindBy(xpath = "//div[@id='tbodyid']/div[1]//img")
	private WebElement FirstListItem;

	@FindBy(xpath = "//a[@onclick='addToCart(1)']")
	private WebElement AddtoCartBtn;

	@FindBy(xpath = "//h2[@class='name']")
	private WebElement ProductNameHeader;

	public String getAlertTextAndAccept() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String text = alert.getText();
		alert.accept();
		return text;
	}

	public String SelectProduct() throws InterruptedException { 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
		int attempts = 0; String productName = ""; 
		// Getting StaleElementException continuously, so I used while and try catch 
		while (attempts < 25){ 
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tbodyid"))); 
				WebElement firstProductLink = driver.findElement(By.xpath("//div[@id='tbodyid']/div[1]//h4/a")); 
				Actions actions = new Actions(driver); actions.moveToElement(firstProductLink).perform(); 
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProductLink); 
				WebElement productNameHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='name']"))); 
				productName = productNameHeader.getText(); 
				WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@onclick='addToCart(1)']"))); 
				addToCartBtn.click();
				break; 
			} catch (NoSuchElementException | StaleElementReferenceException e) { 
				attempts++; 
				Thread.sleep(200); } 
		} if (attempts == 5) { 
			throw new RuntimeException("Failed to select the first product after multiple attempts"); 
		} 
		return productName;
	}



	public String getFirstProductNameInCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement cartProductNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='tbodyid']/tr/td[2]")));
		return cartProductNameElement.getText();
	}

	public String SelectProductByIndex(int index) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		int attempts = 0;
		String productName = "";
		while (attempts < 5) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
				WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='tbodyid']/div[" + index + "]//h4/a")));
				productName = productLink.getText();
				Actions actions = new Actions(driver);
				actions.moveToElement(productLink).perform();
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", productLink);
				WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@onclick,'addToCart')]")));
				addToCartBtn.click();
				break;
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				attempts++;
				Thread.sleep(200);
			}
		}
		if (attempts == 5) {
			throw new RuntimeException("Failed to select product at index " + index);
		}
		return productName;
	}

	public void openCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		int attempts = 0;
		while (attempts < 3) {
			try {
				WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
				cartButton.click();
				break;
			} catch (StaleElementReferenceException e) {
				attempts++;
			}
		}
	}


	public int getProductCount() {
		return productRows.size();
	}

	public void deleteFirstProductInCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[@id='tbodyid']/tr/td[4]/a")));
		deleteBtn.click();
	}

	public List<String> getProductNamesInCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody[@id='tbodyid']/tr")));
		List<WebElement> productElements = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr/td[2]"));
		List<String> productNames = new ArrayList<>();
		for (WebElement element : productElements) {
			productNames.add(element.getText());
		}
		return productNames;
	}

	public void clickPlaceOrder() {
		placeOrderButton.click();
	}

	public int getTotalPrice() {
		int total = 0;
		for (WebElement row : productRows) {
			String priceText = row.findElement(By.xpath("./td[3]")).getText();
			total += Integer.parseInt(priceText);
		}
		return total;
	}

	public boolean isAddToCartDisplayed() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		int attempts = 0;
		while (attempts < 5) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tbodyid")));
				WebElement firstProductLink = driver.findElement(By.xpath("//div[@id='tbodyid']/div[1]//h4/a"));
				Actions actions = new Actions(driver);
				actions.moveToElement(firstProductLink).perform();
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProductLink);
				WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@onclick,'addToCart')]")));
				return addToCartBtn.isDisplayed();
			} catch (StaleElementReferenceException e) {
				attempts++;
				Thread.sleep(200);
			}
		}
		return false;
	}

}
