package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

	private WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}
	public void browseCategory(String categoryName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		WebElement categoryLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + categoryName + "']")));
		categoryLink.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	public void clickProductByName(String productName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='" + productName + "']")));
		productLink.click();
	}

	public boolean isProductDetailDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".name")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".price-container")));
		return true;
	}

	public boolean isHomePageDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-title a")));
		return true;
	}
	public boolean isCartPageDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".table-responsive")));
		return true;
	}

	public boolean isContactPageDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal")));
		return true;
	}

	public boolean isAnyProductDisplayedInCategory(String categoryName) {
		browseCategory(categoryName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-title a")));
		return true;
	}

}
