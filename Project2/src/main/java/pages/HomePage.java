package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Basepage {

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "login2")
	private WebElement loginButton;

	@FindBy(id = "signin2")
	private WebElement signUpButton;

	@FindBy(id = "cartur")
	private WebElement cartLink;

	@FindBy(id = "nameofuser")
	private WebElement loggedInUser;

	@FindBy(xpath = "//a[text()='Log out']")
	private WebElement logoutButton;

	@FindBy(xpath = "//a[text()='Home ']")
	private WebElement homeLink;

	@FindBy(xpath = "//a[text()='Contact']")
	private WebElement contactLink;

	@FindBy(xpath = "(//button[@class='btn btn-secondary'])[3]")
	private WebElement LoginCloseBtn;

	@FindBy(xpath = "(//button[@class='btn btn-secondary'])[2]")
	private WebElement SignupCloseBtn;

	public void clickLogin() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginBtn.click();
	}

	public void clickSignUp() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
		signUpBtn.click();
	}

	public void clickCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(cartLink));
		cartLink.click();
	}

	public void clickLogout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
		logoutButton.click();
	}

	public void clickHome() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		int attempts = 0;
		while (attempts < 3) {
			try {
				WebElement homeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Home ']")));
				homeButton.click();
				break;
			} catch (StaleElementReferenceException e) {
				attempts++;
			}
		}
	}



	public void clickContact() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(contactLink));
		contactLink.click();
	}

	public String getLoggedInUsername() {
		return loggedInUser.getText();
	}

	public boolean isUserLoggedIn() {
		return loggedInUser.isDisplayed();
	}


	public boolean isLoginDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		return loginBtn.isDisplayed();
	}

	public boolean isSignUpDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
		return signUpBtn.isDisplayed();
	}

	public void closeLoginPopup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(LoginCloseBtn));
		closeBtn.click();
	}

	public void closeSignupPopup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(SignupCloseBtn));
		closeBtn.click();
	}
}
