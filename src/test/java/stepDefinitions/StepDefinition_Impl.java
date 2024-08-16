package stepDefinitions;

import Velusmarttacademy_Main.pageobjects.Cart_page;
import Velusmarttacademy_Main.pageobjects.Checkout;
import Velusmarttacademy_Main.pageobjects.Landingpage;
import Velusmarttacademy_Main.pageobjects.Product_catalogue;
import Velusmarttacademy_Test.Test_components.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class StepDefinition_Impl extends BaseTest {
    public Landingpage landingpage;
    public Product_catalogue productCatalogue;
    public Cart_page cartPage;
    public Checkout checkout;
    @Given("I landed on Ecommerce Page")
    public void landed_on_Ecompage(){
        landingpage=launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void login(String username, String password){
        productCatalogue = landingpage.login(username, password);
    }
    @When("^I add product (.+) from Cart$")
    public void add_product_to_cart(String productName){
        List<WebElement> products = productCatalogue.getProduct_List();
        cartPage=productCatalogue.addproductToCart(productName);
    }
    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_orders(String productName){
        cartPage.navigate_to_cartpage();
        checkout = cartPage.proceed_Checkout();
        checkout.select_opt_dropdwon("ind");
    }
    @Then("{string} message is displayed on confirmation page")
    public void Verify_order_text(String string){
        String exp_text = checkout.gettext();
        Assert.assertTrue(exp_text.equalsIgnoreCase(string));
        driver.quit();
    }

    @Then("{string} message is displayed on login page")
    public void verify_login(String string) {
        Assert.assertEquals(string, landingpage.getErrorMessage());
        driver.quit();
    }
}
