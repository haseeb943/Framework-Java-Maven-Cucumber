package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {

	WebDriver driver;

	By txt_username = By.id("user-name");
	By txt_password = By.id("password");
	By btn_login = By.id("login-button");
	By loginLogo = By.className("login_logo");
	By productLogo = By.className("title");


	public loginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enter_Username(String uname) {

		driver.findElement(txt_username).sendKeys(uname);
	}

	public void enter_Password(String pass) {

		driver.findElement(txt_password).sendKeys(pass);
	}

	public void clickLogin() {

		driver.findElement(btn_login).click();
	}

	public boolean isProductLogoDisplay() {

		try {
	        return driver.findElement(productLogo).isDisplayed();
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}

	public boolean isLoginDisplay() {

		try {
	        return driver.findElement(loginLogo).isDisplayed();
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
	
	public void userLogin(String uname, String pass) {

		driver.findElement(txt_username).sendKeys(uname);
		driver.findElement(txt_password).sendKeys(pass);
		driver.findElement(btn_login).click();
	}
}
