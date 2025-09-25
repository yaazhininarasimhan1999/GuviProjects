package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactListPage extends BasePage {

	@FindBy(id = "logout")
	private WebElement logoutButton;

	@FindBy(id = "add-contact")
	private WebElement addContactBtn;

	@FindBy(id = "firstName")
	private WebElement firstName;

	@FindBy(id = "lastName")
	private WebElement lastName;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "phone")
	private WebElement phone;

	@FindBy(id = "street1")
	private WebElement street1;

	@FindBy(id = "city")
	private WebElement city;

	@FindBy(id = "stateProvince")
	private WebElement state;

	@FindBy(id = "postalCode")
	private WebElement postalCode;

	@FindBy(id = "country")
	private WebElement country;

	@FindBy(id = "submit")
	private WebElement saveBtn;

	@FindBy(tagName = "h1")
	private WebElement contactListHeader;

	@FindBy(xpath = "//table[@id='myTable']//tr[1]/td[2]")
	private WebElement FirstEntry;

	@FindBy(id = "edit-contact")
	private WebElement editBtn;

	@FindBy(id = "cancel")
	private WebElement canceleditBtn;

	@FindBy(id = "delete")
	private WebElement deleteContactBtn;

	public ContactListPage(WebDriver driver) {
		super(driver);
	}

	public void clickLogout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout"))).isDisplayed();
		logoutButton.click();
	}

	public boolean isLoginHeaderPresent() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Log In:']"))).isDisplayed();
	}

	public void clickAddContact() { 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-contact"))).isDisplayed();
		addContactBtn.click();
	}

	public void enterFirstName(String fname) {
		firstName.clear();
		firstName.sendKeys(fname);
	}

	public void enterLastName(String lname) {
		lastName.clear();
		lastName.sendKeys(lname);
	}

	public void enterEmail(String emailText) {
		email.clear();
		email.sendKeys(emailText);
	}

	public void enterPhone(String phoneText) {
		phone.clear();
		phone.sendKeys(phoneText);
	}

	public void enterAddress(String addr) {
		street1.clear();
		street1.sendKeys(addr);
	}

	public void enterCity(String cityName) {
		city.clear();
		city.sendKeys(cityName);
	}

	public void enterState(String stateName) {
		state.clear();
		state.sendKeys(stateName);
	}

	public void enterPostalCode(String code) {
		postalCode.clear();
		postalCode.sendKeys(code);
	}

	public void enterCountry(String countryName) {
		country.clear();
		country.sendKeys(countryName);
	}

	public void clickSave() {
		saveBtn.click();
	}

	public boolean isContactListHeaderDisplayed() {
		return contactListHeader.isDisplayed();
	}

	public boolean isContactPresent(String email) {
		try {
			return driver.findElement(By.xpath("//td[text()='" + email + "']")).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void clickFirstContact() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='myTable']//tr[1]/td[2]"))).isDisplayed();
		FirstEntry.click();
	}

	public void clickEditContact() {
		editBtn.click();
	}

	public void clickCancelEdit() {
		canceleditBtn.click();
	}

	public void clearLastName() {
		lastName.clear();
	}

	public boolean clickDeleteContact(String email) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='myTable']//td[text()='" + email + "']"))).isDisplayed();
		WebElement entry = driver.findElement(By.xpath("//table[@id='myTable']//td[text()='" + email + "']"));
		entry.click();
		deleteContactBtn.click();
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;  
		} catch (NoAlertPresentException e) {
			return false; 
		}
	}

	public boolean areContactFormFieldsDisplayed() {
		return firstName.isDisplayed() &&
				lastName.isDisplayed() &&
				email.isDisplayed() &&
				phone.isDisplayed() &&
				street1.isDisplayed() &&
				city.isDisplayed() &&
				state.isDisplayed() &&
				postalCode.isDisplayed() &&
				country.isDisplayed() &&
				saveBtn.isDisplayed();
	}

	public boolean enterMaxFirstNameField(String fname) {
		firstName.clear();
		firstName.sendKeys(fname);
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'longer than the maximum allowed length')]"))).isDisplayed();
		WebElement error = driver.findElement(By.xpath("//span[contains(text(), 'longer than the maximum allowed length')]"));
		return error.isDisplayed();
	}

	public String getFirstNameFieldValue() {
		return firstName.getAttribute("value");
	}

}
