package StepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Hooks.Hooks;
import PageObjectModel.ProductInventoryPage;
import PageObjectModel.loginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductInventoryStepsDefinition {

	private WebDriver driver;
	private loginPage login;
	private ProductInventoryPage productPage;

	public ProductInventoryStepsDefinition() {
		
		this.driver = Hooks.getDriver();
		this.login = Hooks.getLoginPOM();
		this.productPage = new ProductInventoryPage(driver);

	}
	
	
	@Given("User is logged in with username {string} and password {string}")
	public void user_is_logged_in_with_username_and_password(String username, String password) {

		try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            driver.get("https://www.saucedemo.com/");

            login.userLogin(username, password);
            Hooks.addStep("User is logged in with username: " + username + " and password: " + password);
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	@Given("User navigates to the Home Page")
	public void user_navigates_to_the_home_page() {
		try {
            System.out.println("Inventory Page");

            boolean isProductPageDisplay = login.isProductLogoDisplay();
            Assert.assertTrue(isProductPageDisplay, "Products");

            Hooks.addStep("User should be navigated to the Home Page");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@When("sorting products by {string}")
	public void i_sort_the_products_by(String order) throws InterruptedException {
		try {
            Thread.sleep(2000);
            productPage.productsSort(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Then("Products sort in {string}")
	public void products_are_sorted_according_to_the(String order) throws InterruptedException {
		try {
            Assert.assertEquals(productPage.checkSorting(), order);
            Thread.sleep(2000);

            Hooks.addStep("Products sort in " + order);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	

	@When("add {string} to the cart")
	public void add_to_the_cart(String item) throws InterruptedException {
		try {
            Thread.sleep(2000);
            productPage.addItems(item);

            Hooks.addStep("add " + item + " to the cart");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Then("Product added to the cart")
	public void product_added_to_the_cart() throws InterruptedException {
		try {
            boolean areOrdersPresent = productPage.checkOrders();
            Assert.assertTrue(areOrdersPresent, "Item successfully added");
            Thread.sleep(2000);

            Hooks.addStep("Product added to the cart");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	// for logout
	
	@When("click menu bar")
	public void click_menu_bar() {
	    
		 try {
	            productPage.clickMenuBar();

	            Hooks.addStep("click menu bar");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

	@And("click logout button")
	public void click_logout_button() throws InterruptedException {
	    
		Thread.sleep(2000);
		productPage.clickLogoutBtn();
		
		Hooks.addStep("click logout button");
	}

	@Then("user should be logged out successfully")
	public void user_should_be_logged_out_successfully() {
		 try {
	            Thread.sleep(2000);
	            productPage.clickLogoutBtn();

	            Hooks.addStep("click logout button");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
}
