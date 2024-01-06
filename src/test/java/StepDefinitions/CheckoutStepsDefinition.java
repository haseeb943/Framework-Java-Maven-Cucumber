package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Hooks.Hooks;
import PageObjectModel.CheckOutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CheckoutStepsDefinition {

	private WebDriver driver;
	private CheckOutPage checkOutPage;

	public CheckoutStepsDefinition() {
		this.driver = Hooks.getDriver();
		this.checkOutPage = new CheckOutPage(driver);
	}

	@Then("Go to checkout page")
	public void go_to_checkout_page() {

		try {
			checkOutPage.goToCheckoutPage();
			Hooks.addStep("Go to checkout page");
		} catch (Exception e) {
			// Handle the exception (e.g., log it or print a message)
			e.printStackTrace();
		}
	}

	@Given("user navigate to checkout page")
	public void user_navigate_to_checkout_page() {

		try {
			boolean isCheckOutDisplayed = checkOutPage.isCheckOutDisplayed();
			Assert.assertTrue(isCheckOutDisplayed, "User should be navigated to the Home Page");
			Hooks.addStep("user navigate to checkout page");
		} catch (Exception e) {
			// Handle the exception (e.g., log it or print a message)
			e.printStackTrace();
		}
	}

	@And("click checkout button")
	public void click_checkout_button() throws InterruptedException {

		try {
			Thread.sleep(2000);
			checkOutPage.clickCheckoutButton();
			Hooks.addStep("click checkout button");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@And("enter info {string}, {string} and {string}")
	public void enter_info(String fname, String lname, String postal) throws InterruptedException {
		try {
			Thread.sleep(2000);
			checkOutPage.enterCheckoutInfo(fname, lname, postal);
			Hooks.addStep("enter info " + fname + " " + lname + " " + postal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("click continue button")
	public void click_continue_button() throws InterruptedException {
		try {
			Thread.sleep(2000);
			checkOutPage.continueButton();
			Hooks.addStep("click continue button");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("click finish button")
	public void click_finish_button() throws InterruptedException {
		try {
			Thread.sleep(2000);
			checkOutPage.FinishButton();
			Hooks.addStep("click finish button");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("Order is successfully placed")
	public void order_is_successfully_placed() throws InterruptedException {
		try {
			Assert.assertEquals(checkOutPage.orderCompleteMessage(), "Thank you for your order!");
			Thread.sleep(4000);

			if (checkOutPage.orderCompleteMessage().equals("Thank you for your order!")) {
				checkOutPage.goBack();

				Hooks.addStep("Order is successfully placed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
