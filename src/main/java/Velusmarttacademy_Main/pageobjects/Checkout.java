package Velusmarttacademy_Main.pageobjects;

import Velusmarttacademy_Main.Abstract_Components.Abstract_component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout extends Abstract_component {
    WebDriver driver;
    public Checkout(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(xpath = "//div[@class='form-group']//input[@class='input txt text-validated']")
            WebElement textbox;
    @FindBy(xpath = "(//*[@class='ta-item list-group-item ng-star-inserted'])[2]")
            WebElement dropdowntext;
    @FindBy(xpath = "//*[contains(@class,'btnn action__submit')]")
            WebElement submit;
    @FindBy(xpath = "//*[@class='hero-primary']")
            WebElement get_text;
    By dropdown_option= By.xpath("//*[@class='ta-results list-group ng-star-inserted']");

    public void select_opt_dropdwon(String country){
        textbox.sendKeys(country);
        waitforElement_to_be_appear(dropdown_option);
        dropdowntext.click();
        submit.click();
    }
    public String gettext(){
        String exp_text=get_text.getText();
        return exp_text;
    }

}
