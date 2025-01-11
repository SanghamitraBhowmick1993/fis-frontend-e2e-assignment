package parallel;

import org.testng.Assert;

import com.factory.DriverFactory;
import com.pages.EbayPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EbayPageSteps {
	private EbayPage ebayHomePage = new EbayPage(DriverFactory.getDriver());

	@Given("I am on ebay homepage")
	public void i_am_on_ebay_homepage() {
		ebayHomePage.login_ebayApp();
	}

	@When("I search for {string}")
	public void i_search_for(String searchString) {
		ebayHomePage.searchFor(searchString);
	}

	@When("I click on the first book in the list")
	public void i_click_on_the_first_book_in_the_list() {
		ebayHomePage.clickOnFirstBook();
	}

	@When("I add the book to cart")
	public void i_add_the_book_to_cart() {
		ebayHomePage.addToCart();
	}

	@Then("the cart should display the number of items")
	public void the_cart_should_display_the_number_of_items() {
		Assert.assertTrue(ebayHomePage.getCartCount() == 1, "Cart does not display the correct number of items");
	}

}
