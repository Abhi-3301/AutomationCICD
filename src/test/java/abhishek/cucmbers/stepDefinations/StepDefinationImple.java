package abhishek.cucmbers.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import abhishek.TestComponents.BaseTest;
import abhishek.pageobjects.CartPage;
import abhishek.pageobjects.CheckOutPage;
import abhishek.pageobjects.ConfirmationPage;
import abhishek.pageobjects.LandingPage;
import abhishek.pageobjects.ProductCatelogPage;
import freemarker.ext.beans.MemberSelectorListMemberAccessPolicy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImple extends BaseTest {

	public LandingPage landingPage;
	public ProductCatelogPage pcp;
	public ConfirmationPage cnp;

	@Given("I Landed on Ecommerce Page")
	public void I_landed_onEcommerce_Page() throws IOException {
		landingPage = launchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String name, String password) {

		pcp = lp.loginApplication(name, password);

	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String pname) throws InterruptedException {

		List<WebElement> products = pcp.getProductList();
		WebElement prod = pcp.getProductByName(products, pname);
		pcp.addProdcutToCart(prod);

	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String pname) {

		CartPage cp = pcp.goToCartPage();
		Boolean match = cp.verifyProductDisplay(pname);
		Assert.assertTrue(match);

		CheckOutPage cop = cp.goToCheckout();
		cop.selectCountry("India");

		cnp = cop.submitOrder();

	}

	@Then("{string} message is displayed on Confirmation Page")
	public void message_is_displayed_on_confrimation_page(String message) {

		String confirmMessage = cnp.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
		driver.close();
	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String message) {
		Assert.assertEquals(message, lp.getErrorMessage());
		driver.close();
	}
}

// Another way to crate above methods 
// Go to google and search tidy gherkin -> start app 
// copy the feature file and paste it it will automatically create methods for ur definations
