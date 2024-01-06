package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Hooks.Hooks;
import PageObjectModel.loginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPOM_StepsDefinition {

	private WebDriver driver;
	private loginPage login;

	public LoginPOM_StepsDefinition() {

		try {
			this.driver = Hooks.getDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Given("User is on login page")
	public void user_is_on_login_page() {
		try {
			login = Hooks.getLoginPOM();
			driver.manage().window().maximize();
			driver.get("https://www.saucedemo.com/");

			// for extentReport
			Hooks.addStep("User is on login page: URL : https://www.saucedemo.com/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("User enters valid {string} and {string}")

	public void user_enters_valid_username_and_password(String username, String password) throws InterruptedException {
		try {
			login.enter_Username(username);
			login.enter_Password(password);

			Hooks.addStep("User enters valid " + username + " " + password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("Clicks on login Button")
	public void click_on_login_button() {

		try {
			login.clickLogin();

			Hooks.addStep("Clicks on login Button");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("User should be {string}")
	public void user_should_be(String loginStatus) {

		try {
			boolean userStatus = false;

			if (loginStatus.equals("navigated_to_home")) {
				userStatus = login.isProductLogoDisplay();
				Assert.assertTrue(userStatus, "User should be navigated to the Home Page");

				Hooks.addStep("User should be navigated to the Home Page");
			} else if (loginStatus.equals("not_navigated")) {
				userStatus = login.isProductLogoDisplay();
				Assert.assertTrue(userStatus, "User should not be navigated to the Home Page");

				Hooks.addStep("User should not be navigated to the Home Page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Then("close the browser")
	public void close_Browser() {

		try {
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
