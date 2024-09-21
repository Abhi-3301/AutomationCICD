package abhishek.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abhishek.pageobjects.CartPage;
import abhishek.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);

	}

	public void waitForElementToAppear(By findBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}

	// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {

		Thread.sleep(1000);
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}


	@FindBy(css = ("[routerlink*='cart']"))
	WebElement cart;
	
	
	public CartPage goToCartPage() {

		cart.click();
		return new CartPage(driver);

	}
	
	@FindBy(css = ("[routerlink*='myorders']"))
	WebElement ordere;
	
	public OrderPage goToOrdersPage() {
		
		ordere.click();
		return new OrderPage(driver);
	}

}
