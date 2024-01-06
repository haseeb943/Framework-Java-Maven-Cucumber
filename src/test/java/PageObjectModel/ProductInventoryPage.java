package PageObjectModel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductInventoryPage {

	private WebDriver driver;

	// Product Page Locators
	private By filterDropDown = By.className("product_sort_container");
	private By activeFilterStatus = By.xpath("//span[@class='active_option']");
	
	private By addToCartLocator; 
	private By cardItemsCount = By.className("shopping_cart_badge");

	// logout Locators
	private By menuBar = By.id("react-burger-menu-btn");
	private By logoutBn = By.id("logout_sidebar_link");

	
	public ProductInventoryPage(WebDriver driver) {
		this.driver = driver;
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public String checkSorting() {
		return driver.findElement(activeFilterStatus).getText();
	}

	public void productsSort(String order) {
		Select dropdown = new Select(driver.findElement(filterDropDown));
		dropdown.selectByVisibleText(order);
	}

	public void addItems(String product) {

		String formattedString = product.toLowerCase().replace(" ", "-");
		
        String modifiedAddtoCardLocator = "add-to-cart-" + formattedString;
        addToCartLocator = By.id(modifiedAddtoCardLocator);
        
        System.out.print("card locator : "+addToCartLocator);
        
        driver.findElement(addToCartLocator).click();
        
	}

	public boolean checkOrders() {
		String countText = driver.findElement(cardItemsCount).getText();

		try {
			int orderCounter = Integer.parseInt(countText);
			return orderCounter > 0;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false; 
		}
	}
	
	// for logout
	public void clickMenuBar() {
		driver.findElement(menuBar).click();
	}
	
	public void clickLogoutBtn() {
		driver.findElement(logoutBn).click();
	}

}
