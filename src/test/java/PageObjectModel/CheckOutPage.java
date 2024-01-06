package PageObjectModel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {

	private WebDriver driver;

	private By CheckoutPageLocator = By.className("shopping_cart_badge");
	private By checkOutLocator = By.id("checkout");
	private By txt_firstname = By.id("first-name");
	private By txt_lastname = By.id("last-name");
	private By txt_postal = By.id("postal-code");
	private By continueButton = By.id("continue");
	private By finishButton = By.id("finish");
	private By orderCompletetxt = By.className("complete-header");
	private By backButton = By.id("back-to-products");

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	
	public void goToCheckoutPage() {
		driver.findElement(CheckoutPageLocator).click();
	}

	public boolean isCheckOutDisplayed() {

		try {
	        return driver.findElement(checkOutLocator).isDisplayed();
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
	
	public void clickCheckoutButton() {
		driver.findElement(checkOutLocator).click();
	}

	public void enterCheckoutInfo(String firstname, String lastname, String postal) {
		driver.findElement(txt_firstname).sendKeys(firstname);
		driver.findElement(txt_lastname).sendKeys(lastname);
		driver.findElement(txt_postal).sendKeys(postal);

	}

	public void continueButton() {
		driver.findElement(continueButton).click();
	}

	public void FinishButton() {
		driver.findElement(finishButton).click();
	}

	public String orderCompleteMessage() {
		return driver.findElement(orderCompletetxt).getText();
	}
	
	public void goBack() {
		driver.findElement(backButton).click();
	}
}
