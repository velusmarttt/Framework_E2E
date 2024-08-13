package Velusmarttacademy_Main.pageobjects;

import Velusmarttacademy_Main.Abstract_Components.Abstract_component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Landingpage extends Abstract_component {
    WebDriver driver;
    public Landingpage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

//    WebElement userEmails=driver.findElement(By.id("userEmail"));
    //PageFactory

    @FindBy(id="userEmail")
    WebElement userEmail;
    @FindBy(id="userPassword")
    WebElement userPassword;
    @FindBy(id="login")
    WebElement submit;
    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public Product_catalogue login(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        return new Product_catalogue(driver);
    }
    public String getErrorMessage(){
        waitfor_errormessage_Appear(errorMessage);
        return errorMessage.getText();
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

}
