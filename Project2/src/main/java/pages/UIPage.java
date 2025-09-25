package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UIPage {
	private WebDriver driver;

	public UIPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(tagName = "h2") 
	private WebElement homeHeader;

	@FindBy(css = ".navbar-nav a")
	private WebElement navLink;

	@FindBy(css = ".card-title a") 
	private WebElement productTitle;

	@FindBy(css = "#page-wrapper h2") 
	private WebElement cartPageHeader;
	
	@FindBy(id = "orderModalLabel") 
	private WebElement purchaseModalTitle;

	@FindBy(id = "orderModal") 
	private WebElement purchaseModal;

	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	public String getHomeHeaderFontFamily() {
		wait.until(ExpectedConditions.visibilityOf(homeHeader));
		return homeHeader.getCssValue("font-family");
	}

	public String getHomeHeaderFontSize() {
		wait.until(ExpectedConditions.visibilityOf(homeHeader));
		return homeHeader.getCssValue("font-size");
	}

	public String getNavLinkFontFamily() {
		wait.until(ExpectedConditions.visibilityOf(navLink));
		return navLink.getCssValue("font-family");
	}

	public String getNavLinkFontSize() {
		wait.until(ExpectedConditions.visibilityOf(navLink));
		return navLink.getCssValue("font-size");
	}

	public String getProductTitleFontFamily() {
		wait.until(ExpectedConditions.visibilityOf(productTitle));
		return productTitle.getCssValue("font-family");
	}

	public String getProductTitleFontSize() {
		wait.until(ExpectedConditions.visibilityOf(productTitle));
		return productTitle.getCssValue("font-size");
	}

	public String getCartPageFontFamily() {
		return cartPageHeader.getCssValue("font-family");
	}
	
	public String getPurchaseModalFont() {
	    return purchaseModalTitle.getCssValue("font-family");
	}

	public String getPurchaseModalFontSize() {
	    return purchaseModalTitle.getCssValue("font-size");
	}

	public String getPurchaseModalBgColor() {
	    return purchaseModal.getCssValue("background-color");
	}
}
