package tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ExtentManager3;

public class BaseTest {

    @BeforeSuite
    public void beforeSuite() {
        ExtentManager3.initReports();
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager3.flushReports();
    }
}

