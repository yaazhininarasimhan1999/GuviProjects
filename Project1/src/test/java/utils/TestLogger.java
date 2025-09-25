package utils;

import utils.ExtentManager;

public class TestLogger {

	public static void logTestResult(String testName, boolean result) {
        if (result) {
            ExtentManager.getTest().pass(testName + ": PASSED");
        } else {
            ExtentManager.getTest().fail(testName + ": FAILED");
        }
    }
}
