package tests;

import org.testng.annotations.*;
import utils.ExtentManager2;

public class BaseTest {

    @BeforeSuite
    public void setupExtentReports() {
        ExtentManager2.initReports();
    }

    @AfterSuite
    public void tearDownExtentReports() {
        ExtentManager2.flushReports();
    }
}
