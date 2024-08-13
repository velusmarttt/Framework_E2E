package Velusmarttacademy_Test.Test_components;

import Velusmarttacademy_Main.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver=null;
    public Landingpage landingpage=null;
    public WebDriver initializeDriver(){
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Velusmarttacademy_Main\\resources\\GlobalData.properties");
            prop.load(fis);
            String BrowseName = prop.getProperty("browser");
            if (BrowseName.equalsIgnoreCase("chrome")) {
//            System.setProperty("webdriver.chrome.driver", "C:\\Users\\velus\\IdeaProjects\\Framework_E2E\\src\\main\\Data_source\\Driver\\chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (BrowseName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (BrowseName.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return driver;
    }
    @BeforeMethod(alwaysRun = true)
    public Landingpage launchApplication() {
            driver = initializeDriver();
            landingpage = new Landingpage(driver);
            landingpage.goTo();
            return landingpage;
    }
    @AfterMethod(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }
}
