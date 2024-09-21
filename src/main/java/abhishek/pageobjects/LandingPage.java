package abhishek.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = ("[class*='flyInOut']"))
	WebElement errorMessage;

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client/");

	}
	
	public String getErrorMessage() {
		
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public ProductCatelogPage loginApplication(String email, String pass) {

		userEmail.sendKeys(email);
		userPassword.sendKeys(pass);
		submit.click();
		return new ProductCatelogPage(driver);

	}

}
