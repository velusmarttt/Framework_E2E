package Velusmarttacademy_Test.Test_components;

import Velusmarttacademy_Main.pageobjects.Landingpage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver = null;
    public Landingpage landingpage = null;

    public WebDriver initializeDriver() {
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Velusmarttacademy_Main\\resources\\GlobalData.properties");
            prop.load(fis);
          String BrowserName=System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
//            String BrowserName = prop.getProperty("browser");
            if (BrowserName.contains("chrome")) {
                ChromeOptions options=new ChromeOptions();
//            System.setProperty("webdriver.chrome.driver", "C:\\Users\\velus\\IdeaProjects\\Framework_E2E\\src\\main\\Data_source\\Driver\\chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                if (BrowserName.contains("headless")){
                    options.addArguments("headless");
                }
                driver=new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440, 900));//help you to run full screen
            } else if (BrowserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (BrowserName.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println(e);
        }
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataMap(String filepath) throws IOException {
        //read json to string
        String jsoncontent = FileUtils.readFileToString(new File(filepath)
                , StandardCharsets.UTF_8);
        //Convert string to Hashmap using jackson databind
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    @BeforeMethod(alwaysRun = true)
    public Landingpage launchApplication() {
        driver = initializeDriver();
        landingpage = new Landingpage(driver);
        landingpage.goTo();
        return landingpage;
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        driver.quit();
    }

    public String getScreenshot(String Test_case_name, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "\\Reports\\" + Test_case_name + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "\\Reports\\" + Test_case_name + ".png";
    }
}
