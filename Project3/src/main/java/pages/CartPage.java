package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

	private WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	@FindBy(xpath = "//span[text()='Shopping cart']")
	private WebElement shoppingCartLink;

	@FindBy(id = "(//input[@value='Add to cart'])[1]")
	private WebElement addToCartButton;

	@FindBy(xpath = "(//input[@value='Add to cart'])[4]")
	private WebElement addToCartButtonfromHome;

	@FindBy(className="cart-qty")
	private WebElement cartQuantity;

	@FindBy(xpath = "//input[@name='removefromcart']")
	private WebElement removefromCartCheckBox;
	
	@FindBy(xpath = "//input[@name='updatecart']")
	private WebElement updateCartBtn;
	
	@FindBy(className = "order-summary-content")
	private WebElement emptyCartText;
	
	@FindBy(id = "checkout")
	private WebElement checkoutBtn;

	@FindBy(id = "termsofservice")
	private WebElement termsAndServices;

	@FindBy(xpath = "//input[@class='qty-input']")
	private WebElement cartQuantityInput;
	
	public boolean isShoppingCartLinkDisplayed() {
		return shoppingCartLink.isDisplayed();
	}
	
	public void clickShoppingCartLink() {
		wait.until(ExpectedConditions.visibilityOf(shoppingCartLink));
		shoppingCartLink.click();
	}

	public void clickAddToCartfromHome() {
		wait.until(ExpectedConditions.visibilityOf(addToCartButtonfromHome));
		addToCartButtonfromHome.click();
	}

	public void clickAddToCart() {	
		WebElement addToCartButtoN = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@value='Add to cart'])[1]")));
		addToCartButtoN.click();
	}

	public int getCartItemCount() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-qty")));
		String quantityText = cartQuantity.getText();
		return Integer.parseInt(quantityText.replace("(", "").replace(")", ""));
	}


	public void updateQuantity(String quantity) {
		cartQuantityInput.clear();
		cartQuantityInput.sendKeys(quantity);
		cartQuantityInput.sendKeys(Keys.ENTER);
	}
	
	public void removeProduct() {
		wait.until(ExpectedConditions.visibilityOf(removefromCartCheckBox));
		removefromCartCheckBox.click();
	}

	public void clickUpdateCart() {
		wait.until(ExpectedConditions.visibilityOf(updateCartBtn));
		updateCartBtn.click();
	}
	
	public boolean isCartEmpty() {
		wait.until(ExpectedConditions.visibilityOf(emptyCartText));
		return emptyCartText.isDisplayed();
	}

	public void clickCheckOut() {
		wait.until(ExpectedConditions.visibilityOf(termsAndServices));
		termsAndServices.click();
		checkoutBtn.click();		
	}
}
