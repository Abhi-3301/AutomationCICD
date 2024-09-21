package abhishek.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abhishek.AbstractComponents.AbstractComponent;

public class ProductCatelogPage extends AbstractComponent {
	
	
WebDriver driver;
	
	
	public ProductCatelogPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	
	@FindBy(css=(".mb-3"))
	List<WebElement> products;
	
	@FindBy(css=(".ng-animating"))
	WebElement spinner;
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card button:last-child");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(List<WebElement> products, String prdName) {
		
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(prdName)).findFirst()
				.orElse(null);
		
		return prod;
	}
	
	public void addProdcutToCart(WebElement prod) throws InterruptedException {
		
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}

}
	