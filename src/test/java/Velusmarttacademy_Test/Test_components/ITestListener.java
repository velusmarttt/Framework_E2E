package Velusmarttacademy_Test.Test_components;

import org.testng.ITestContext;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

public interface ITestListener extends ITestNGListener {
    void onTestStart(ITestResult result);

    void onTestSuccess(ITestResult result);

    void onTestFailure(ITestResult result);

    void onTestSkipped(ITestResult result);

    void onTestFailedButWithinSuccessPercentage(ITestResult result);

    void onTestFailedWithTimeout(ITestResult result);

    void onStart(ITestContext context);

    void onFinish(ITestContext context);
}
