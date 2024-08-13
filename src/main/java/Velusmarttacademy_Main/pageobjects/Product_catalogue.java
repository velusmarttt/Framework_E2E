package Velusmarttacademy_Main.pageobjects;

import Velusmarttacademy_Main.Abstract_Components.Abstract_component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Product_catalogue extends Abstract_component {
    WebDriver driver;
    public Product_catalogue(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory

    @FindBy(css=".mb-3")
    List<WebElement> products;
    @FindBy(css=".ng-animating")
    WebElement spinner;
    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.xpath("//*[@id='toast-container']");

    public List<WebElement> getProduct_List(){
        waitforElement_to_be_appear(productsBy);
        return products;
    }
    public WebElement getproductByName(String productName){
        WebElement prod=getProduct_List().stream().filter(product->product.findElement(By.cssSelector("b"))
                .getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }
    public Cart_page addproductToCart(String productName){
        WebElement prod=getproductByName(productName);
        prod.findElement(addToCart).click();
        waitforElement_to_be_appear(toastMessage);
        waitforElement_to_be_Disappear(spinner);
        return new Cart_page(driver);
    }

}
