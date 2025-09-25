package tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ExtentManager;

public class BaseTest {

    @BeforeSuite
    public void setupExtentReports() {
        ExtentManager.initReports();
    }

    @AfterSuite
    public void tearDownExtentReports() {
        ExtentManager.flushReports();
    }
}
