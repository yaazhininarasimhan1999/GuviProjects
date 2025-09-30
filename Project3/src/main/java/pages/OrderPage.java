package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage extends BasePage {

	private WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
	}

	@FindBy(id = "Email")
	private WebElement emailField;

	@FindBy(id = "Password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Log in']")
	private WebElement loginButton;

	@FindBy(xpath = "//a[text()='Log out']")
	private WebElement logoutLink;

	@FindBy(id = "BillingNewAddress_FirstName")
	private WebElement billingFirstName;

	@FindBy(id = "BillingNewAddress_Address1")
	private WebElement billingAddress;

	@FindBy(id = "BillingNewAddress_City")
	private WebElement billingCity;

	@FindBy(id = "BillingNewAddress_StateProvinceId")
	private WebElement billingState;

	@FindBy(id = "BillingNewAddress_ZipPostalCode")
	private WebElement billingZip;

	@FindBy(id = "BillingNewAddress_PhoneNumber")
	private WebElement billingPhone;

	@FindBy(id = "BillingNewAddress_CountryId")
	private WebElement billingCountry;

	@FindBy(xpath = "//input[@title='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//strong[contains(text(),'successfully processed!')]")
	private WebElement orderConfirmationMsg;

	@FindBy(xpath = "//input[@onclick='ConfirmOrder.save()']")
	private WebElement orderConfirmationBtn;

	@FindBy(xpath = "//span[@class='field-validation-error']")
	private WebElement orderErrorMsg;
	
	@FindBy(xpath = "//ul[@class='details']/li[contains(text(),'Order number')]")
	private WebElement orderNum;

	@FindBy(xpath = "//a[text()='Orders']")
	private WebElement ordersLink;

	@FindBy(css = "div.order-list div.title strong")
	private List<WebElement> orderNumbers;

	@FindBy(id = "name")
	private WebElement nameField;

	@FindBy(id = "country")
	private WebElement countryField;

	@FindBy(id = "city")
	private WebElement cityField;

	public void fillBillingAndShipping(String firstName, String address, String city, String state, String zip, String country, String phone) {
		if(billingFirstName.isDisplayed()) {
			billingFirstName.clear();
			billingFirstName.sendKeys(firstName);
			billingAddress.clear();
			billingAddress.sendKeys(address);
			billingCity.clear();
			billingCity.sendKeys(city);
			billingState.sendKeys(state);
			billingZip.clear();
			billingZip.sendKeys(zip);
			Select selectCountry = new Select(billingCountry);
			selectCountry.selectByVisibleText(country);
			billingPhone.clear();
			billingPhone.sendKeys(phone);
		}
		continueButton.click();
	}

	public void fillNewBillingAndShipping(String firstName, String address, String city, String state, String zip, String country, String phone) {
		Select shippingDropdown = new Select(driver.findElement(By.id("billing-address-select")));
		shippingDropdown.selectByVisibleText("New Address");
		billingFirstName.clear();
		billingFirstName.sendKeys(firstName);
		billingAddress.clear();
		billingAddress.sendKeys(address);
		billingCity.clear();
		billingCity.sendKeys(city);
		billingState.sendKeys(state);
		billingZip.clear();
		billingZip.sendKeys(zip);
		Select selectCountry = new Select(billingCountry);
		selectCountry.selectByVisibleText(country);
		billingPhone.clear();
		billingPhone.sendKeys(phone);
		continueButton.click();
	}

	public void BillingAddressContinue() {
		wait.until(ExpectedConditions.visibilityOf(continueButton));
		continueButton.click(); 
	}

	public void ShippingAddressContinue() {
		WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='Shipping.save()']")));
		continueBtn.click(); 
	}

	public void ShippingMethodContinue() {
		WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='ShippingMethod.save()']")));
		continueBtn.click(); 
	}

	public void PaymentMethodContinue() {
		WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='PaymentMethod.save()']")));
		continueBtn.click(); 
	}

	public void PaymentInfoContinue() {
		WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='PaymentInfo.save()']")));
		continueBtn.click(); 
	}

	public void clickConfirm() {
		wait.until(ExpectedConditions.visibilityOf(orderConfirmationBtn));
		orderConfirmationBtn.click();
	}

	public boolean isOrderConfirmed() {
		wait.until(ExpectedConditions.visibilityOf(orderConfirmationMsg));
		return orderConfirmationMsg.isDisplayed();
	}

	public String FetchOrderNum() {
	    wait.until(ExpectedConditions.visibilityOf(orderNum));
	    String onum1 = orderNum.getText(); 
	    String ONum = onum1.replaceAll("[^0-9]", ""); 
	    return ONum;
	}


	public boolean isOrderErrorDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(orderErrorMsg));
		try {
			return orderErrorMsg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void navigateToOrders() {
		ordersLink.click();
	}

	public boolean isOrderPresent(String onum) {
	    wait.until(ExpectedConditions.visibilityOfAllElements(orderNumbers));
	    for (WebElement order : orderNumbers) {
	        String text = order.getText().trim();
	        if (text.contains(onum)) {
	            return true;
	        }
	    }
	    return false;
	}


	public void enterName(String name) {
		nameField.clear();
		nameField.sendKeys(name);
	}

	public void enterCountry(String country) {
		countryField.clear();
		countryField.sendKeys(country);
	}

	public void enterCity(String city) {
		cityField.clear();
		cityField.sendKeys(city);
	}
}
