package abhishek.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ("tr td:nth-child(3)"))
	List<WebElement> prdNames;

	public Boolean verifyOrderDisplay(String productName) {

		return prdNames.stream().anyMatch(prt -> prt.getText().equalsIgnoreCase(productName));

	}
}
