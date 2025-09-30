package utils;

public class TestLogger3 {

    public static void logTestResult(String testName, boolean result) {
        if (result) {
            ExtentManager3.getTest().pass(testName + ": PASSED");
        } else {
            ExtentManager3.getTest().fail(testName + ": FAILED");
        }
    }
}
