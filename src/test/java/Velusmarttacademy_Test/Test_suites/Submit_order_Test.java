package Velusmarttacademy_Test.Test_suites;

import Velusmarttacademy_Main.Abstract_Components.Abstract_component;
import Velusmarttacademy_Main.pageobjects.Cart_page;
import Velusmarttacademy_Main.pageobjects.Checkout;
import Velusmarttacademy_Main.pageobjects.Orderpage;
import Velusmarttacademy_Main.pageobjects.Product_catalogue;
import Velusmarttacademy_Test.Test_components.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class Submit_order_Test extends BaseTest {
    String productName = "ADIDAS ORIGINAL";

    @Test(dataProvider = "getData", groups = {"purchase"})
    public void submitOrder(HashMap<String, String> feeddata) {
        try {
            Product_catalogue productCatalogue = landingpage.login(feeddata.get("email"), feeddata.get("password"));
            List<WebElement> products = productCatalogue.getProduct_List();
            Cart_page cartPage = productCatalogue.addproductToCart(feeddata.get("product"));
            cartPage.navigate_to_cartpage();
//        String text=driver.findElement(By.xpath("//*[@class='cartSection']/h3")).getText();
//        System.out.println(text);
//        List<WebElement> cartproducts=driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
//        Boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase("addidas OriGinal"));
//        Assert.assertTrue(match);
            Checkout checkout = cartPage.proceed_Checkout();
            checkout.select_opt_dropdwon("ind");
            String exp_text = checkout.gettext();
            Assert.assertTrue(exp_text.equalsIgnoreCase("Thankyou for the order."));
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    @Test(dependsOnMethods = "submitOrder")
    public void OrderHistory (){
        Product_catalogue productCatalogue = landingpage.login("anshika@gmail.com", "Iamking@000");
        Orderpage orderpage=productCatalogue.goTo_orderpage();
        Assert.assertTrue(orderpage.verify_order(productName));
    }
    @DataProvider
    public Object[][] getData(){
        HashMap<String, String> map=new HashMap<String, String>();
        map.put("email", "anshika@gmail.com");
        map.put("password", "Iamking@000");
        map.put("product", "ADIDAS ORIGINAL");

        HashMap<String, String> map1=new HashMap<String, String>();
        map1.put("email", "shetty@gmail.com");
        map1.put("password", "Iamking@000");
        map1.put("product", "ZARA COAT 3");
        return new Object[][] {{map}, {map1}};
    }
//    @DataProvider
//    public Object[][] getData(){
//        return new Object[][] {{"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}, {"shetty@gmail.com","Iamking@000", "ZARA COAT 3"}};
//    }

}