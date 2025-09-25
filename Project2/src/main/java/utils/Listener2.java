package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener2 implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager2.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager2.getTest().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager2.getTest().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager2.getTest().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    @Override
    public void onStart(ITestContext context) { }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager2.flushReports();
    }
}
