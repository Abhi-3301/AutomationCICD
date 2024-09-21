package abhishek;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Automation {

	@Test
	public void Run() {
		// TODO Auto-generated method stub

		// abhishek@gmail.com
		// Abhishek@123

		String userName = "abhishek@gmail.com";
		String password = "Abhishek@123";
		String productName = "ADIDAS ORIGINAL";

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");

		driver.findElement(By.id("userEmail")).sendKeys(userName);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card button:last-child")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> prds = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = prds.stream().anyMatch(prt -> prt.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));

		driver.findElement(By.cssSelector(".ta-item:last-child")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".hero-primary")));

		String confirm = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();

	}

}
