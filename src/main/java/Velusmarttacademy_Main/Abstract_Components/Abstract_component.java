package Velusmarttacademy_Main.Abstract_Components;

import Velusmarttacademy_Main.pageobjects.Orderpage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Abstract_component {
    WebDriver driver;
    WebDriverWait wait;

    public Abstract_component(WebDriver driver) {
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        this.wait=wait;
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[@routerlink='/dashboard/myorders']")
    WebElement orderHeader;
    public void waitforElement_to_be_appear(By findBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitfor_errormessage_Appear(WebElement findBy) {
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }
    public void waitforElement_to_be_Disappear(WebElement ele) {
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }
    public Orderpage goTo_orderpage(){
        orderHeader.click();
        Orderpage orderpage=new Orderpage(driver);
        return orderpage;
    }
}
