package Velusmarttacademy_Main.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extent_Report_TestNG {
    public static ExtentReports config() {
        //Extentreports  Extentsparkreporter
        String path = System.getProperty("user.dir") + "\\Reports\\index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("Automation Results");
        extentSparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester", "velusmarttt");
        return extentReports;

    }
}
//    @Test
//    public void extentreports_demo(){
//        ExtentTest test=extentReports.createTest("extentreports_demo");
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("https://rahulshettyacademy.com");
//        System.out.println(driver.getTitle());
//        driver.quit();
//        test.fail("Results not matching");
//        extentReports.flush();
//    }
