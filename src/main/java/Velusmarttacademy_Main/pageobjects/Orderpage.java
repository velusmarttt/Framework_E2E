package Velusmarttacademy_Main.pageobjects;

import Velusmarttacademy_Main.Abstract_Components.Abstract_component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Orderpage extends Abstract_component {
    WebDriver driver;
    public Orderpage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(xpath = "//tbody//tr//td[2]")
    List<WebElement> productNames;

    public Boolean verify_order(String productName){
        Boolean product_name=productNames.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
        return product_name;
    }

}
