package Velusmarttacademy_Test.Test_suites;

import Velusmarttacademy_Main.pageobjects.Cart_page;
import Velusmarttacademy_Main.pageobjects.Checkout;
import Velusmarttacademy_Main.pageobjects.Product_catalogue;
import Velusmarttacademy_Test.Test_components.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Error_validations extends BaseTest {

    @Test(groups = "ErrorMessage")
    public void Login_error_validation() {
        try {
            Product_catalogue productCatalogue = landingpage.login("anshika@gmail.com", "Iamkingss@77000");
            Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void Verify_Add_to_cart_product_text() {
        try {
            String productName = "ADIDAS ORIGINAL";
            Product_catalogue productCatalogue = landingpage.login("anshika@gmail.com", "Iamking@000");
            List<WebElement> products = productCatalogue.getProduct_List();
            Cart_page cartPage = productCatalogue.addproductToCart(productName);
            cartPage.navigate_to_cartpage();
        String text=driver.findElement(By.xpath("//*[@class='cartSection']/h3")).getText();
        System.out.println(text);
        Assert.assertEquals(text, "ADIDAS ORIGINAL");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}