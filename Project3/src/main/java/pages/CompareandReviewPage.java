package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompareandReviewPage extends BasePage {

    private WebDriver driver;

    public CompareandReviewPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
}

    @FindBy(xpath = "//input[@value='Add to compare list']")
    private WebElement addToCompareButton;

    @FindBy(xpath = "//a[@class='clear-list']")
    private WebElement clearCompareListBtn;
    
    @FindBy(xpath = "//tr[@class='product-name']")
    private WebElement productinCompareList;
    
    @FindBy(xpath = "//a[text()='Add your review']")
    private WebElement addReviewBtn;
    
    @FindBy(id="AddProductReview_Title")
    private WebElement reviewTitle;
    
    @FindBy(id="AddProductReview_ReviewText")
    private WebElement reviewBox;
    
    @FindBy(xpath = "//input[@name='add-review']")
    private WebElement submitReviewBtn;
    
    @FindBy(xpath = "//div[@class='result']")
    private WebElement isReviewadded;
    
    public void clickAddToComparelist() {
		wait.until(ExpectedConditions.visibilityOf(addToCompareButton));
    	addToCompareButton.click(); 
    }

    public boolean isProductInComparelist() {
    	try {
		wait.until(ExpectedConditions.visibilityOf(productinCompareList));
    	return productinCompareList.isDisplayed();
    	}catch(Exception e) {
    		return false;
    	}
    }
    
    public void clearCompareList() {
		wait.until(ExpectedConditions.visibilityOf(clearCompareListBtn));
		clearCompareListBtn.click();
    }

    public void clickAddReview() {
    	wait.until(ExpectedConditions.visibilityOf(addReviewBtn));
    	addReviewBtn.click();
    }
    
    public void addReview() {
    	wait.until(ExpectedConditions.visibilityOf(reviewTitle));
    	reviewTitle.sendKeys("Product Review");
    	reviewBox.sendKeys("Good Product. Worth the Cost!!!");
    	submitReviewBtn.click();
    }
    
    public boolean isReviewAdded() {
    	wait.until(ExpectedConditions.visibilityOf(isReviewadded));
    	return isReviewadded.isDisplayed();
    }
    
    public boolean isFontConsistent() {
        List<WebElement> elements = driver.findElements(By.xpath("//*"));
        String baseFont = elements.get(0).getCssValue("font-family");
        for (WebElement el : elements) {
            if (!el.getCssValue("font-family").equals(baseFont)) {
                return false;
            }
        }
        return true;
    }    
}
