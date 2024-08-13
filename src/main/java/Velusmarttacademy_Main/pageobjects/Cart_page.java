package Velusmarttacademy_Main.pageobjects;

import Velusmarttacademy_Main.Abstract_Components.Abstract_component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart_page extends Abstract_component {
    WebDriver driver;
    public Cart_page(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(xpath = "//*[@routerlink='/dashboard/cart']")
    WebElement cart;
    @FindBy(xpath = "//*[@class='totalRow']/*[@class='btn btn-primary']")
            WebElement checkout;
    By elementcartpage = By.xpath("//*[@routerlink='/dashboard/cart']");

    public void navigate_to_cartpage() {
        try {
            waitforElement_to_be_appear(elementcartpage);
            Thread.sleep(4000);
            cart.click();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public Checkout proceed_Checkout(){
        checkout.click();
        return new Checkout(driver);
    }

}
