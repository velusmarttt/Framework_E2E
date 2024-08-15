package Velusmarttacademy_Test.Test_components;

import Velusmarttacademy_Main.resources.Extent_Report_TestNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
        ExtentTest test;
      ExtentReports extent=Extent_Report_TestNG.config();
      ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();//Thread safe

        @Override
        public void onTestStart(ITestResult result) {
           test=extent.createTest(result.getMethod().getMethodName());
           extentTest.set(test);//unique thread
        }
        @Override
        public void onTestSuccess(ITestResult result) {
            extentTest.get().log(Status.PASS, "Test Passed");
        }
        @Override
        public void onTestFailure(ITestResult result) {
            extentTest.get().fail(result.getThrowable());
            try {
                driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
                        .get(result.getInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //1.Take a screenshot
                //2.Attach screenshot to report
            String filepath= null;
            try {
                filepath = getScreenshot(result.getMethod().getMethodName(), driver);
            } catch (IOException e) {
                e.printStackTrace();
            }
            extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
        }
        @Override
        public void onTestSkipped(ITestResult result) {
            // not implemented
        }
        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
            // not implemented
        }
        @Override
        public void onTestFailedWithTimeout(ITestResult result) {
            onTestFailure(result);
        }

        @Override
        public void onStart(ITestContext context) {
        }
        @Override
        public void onFinish(ITestContext context){
            extent.flush();
        }
    }
