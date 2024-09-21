package abhishek;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import abhishek.TestComponents.BaseTest;
import abhishek.TestComponents.Retry;
import abhishek.pageobjects.CartPage;
import abhishek.pageobjects.ProductCatelogPage;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String userName = "abhishek@gmail.com";
		String password = "Abhi@123";

		lp.loginApplication(userName, password);
		Assert.assertEquals("Incorrect email or passwrd.", lp.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String userName = "anshika@gmail.com";
		String password = "Iamking@000";
		String productName = "ADIDAS ORIGINAL";

		ProductCatelogPage pc = lp.loginApplication(userName, password);
		List<WebElement> products = pc.getProductList();
		WebElement prod = pc.getProductByName(products, productName);
		pc.addProdcutToCart(prod);

		CartPage cp = pc.goToCartPage();
		Boolean match = cp.verifyProductDisplay("ADIDAS ORIGINAL");
		Assert.assertFalse(match);

	}

}
