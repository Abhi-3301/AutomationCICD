package abhishek.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = (".totalRow button"))
	WebElement checkOut;

	@FindBy(css = (".cartSection h3"))
	List<WebElement> prdTitles;

	public Boolean verifyProductDisplay(String productName) {

		return prdTitles.stream().anyMatch(prt -> prt.getText().equalsIgnoreCase(productName));

	}

	public CheckOutPage goToCheckout() {

		checkOut.click();
		return new CheckOutPage(driver);
	}

}
