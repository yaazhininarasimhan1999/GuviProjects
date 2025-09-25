package utils;

public class TestLogger2 {

    public static void logTestResult(String testName, boolean result) {
        if (result) {
            ExtentManager2.getTest().pass(testName + ": PASSED");
        } else {
            ExtentManager2.getTest().fail(testName + ": FAILED");
        }
    }
}
