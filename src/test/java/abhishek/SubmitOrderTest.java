package abhishek;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abhishek.TestComponents.BaseTest;
import abhishek.pageobjects.CartPage;
import abhishek.pageobjects.CheckOutPage;
import abhishek.pageobjects.ConfirmationPage;
import abhishek.pageobjects.OrderPage;
import abhishek.pageobjects.ProductCatelogPage;

public class SubmitOrderTest extends BaseTest {

	String userName = "abhishek@gmail.com";
	String password = "Abhishek@123";
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input)
			throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		ProductCatelogPage pc = lp.loginApplication(input.get("userName"), input.get("password"));
		List<WebElement> products = pc.getProductList();
		WebElement prod = pc.getProductByName(products, input.get("productName"));
		pc.addProdcutToCart(prod);

		CartPage cp = pc.goToCartPage();
		Boolean match = cp.verifyProductDisplay(input.get("productName")); 	
		Assert.assertTrue(match);

		CheckOutPage cop = cp.goToCheckout();
		cop.selectCountry("India");

		ConfirmationPage cnp = cop.submitOrder();
		String confirmMessage = cnp.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {

		ProductCatelogPage pc = lp.loginApplication(userName, password);
		OrderPage op = pc.goToOrdersPage();
		Assert.assertTrue(op.verifyOrderDisplay(productName));

	}
	
		
	//*******************************************
	//	@DataProvider
	//	public Object[][] getData() {
	//		return new Object[][] {{ "abhishek@gmail.com", "Abhishek@123","ADIDAS ORIGINAL"}, {"anshika@gmail.com","Iamking@000","ZARA COAT 3"}};
	//		
	//	}
	//*******************************************
	//	@DataProvider
	//	public Object[][] getData() {
	//
	//		HashMap<String, String> hm1 = new HashMap<String, String>();
	//		hm1.put("userName", "abhishek@gmail.com");
	//		hm1.put("password", "Abhishek@123");
	//		hm1.put("productName", "ADIDAS ORIGINAL");
	//
	//		HashMap<String, String> hm2 = new HashMap<String, String>();
	//		hm2.put("userName", "anshika@gmail.com");
	//		hm2.put("password", "Iamking@000");
	//		hm2.put("productName", "ZARA COAT 3");
	//
	//		return new Object[][] { { hm1 }, { hm2 } };
	//
	//	}
	//*******************************************
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> lst = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\resources\\PurchaseOrder.json");

		return new Object[][] { { lst.get(0) }, {lst.get(1)} };

	}

}
