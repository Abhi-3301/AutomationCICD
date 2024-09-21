package abhishek.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	@FindBy(css = ("[placeholder='Select Country']"))
	WebElement country;

	@FindBy(css = (".ta-item:last-child"))
	WebElement selectCountry;

	@FindBy(css = (".action__submit"))
	WebElement submit;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countyName) {

		Actions a = new Actions(driver);
		//waitForWebElementToAppear(country);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		a.sendKeys(country, countyName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();

	}

	public ConfirmationPage submitOrder() {

		submit.click();
		return new ConfirmationPage(driver);

	}

}
