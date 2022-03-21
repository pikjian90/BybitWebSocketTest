package testcase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;

    @BeforeSuite
    public void beforeSuite(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/BybitWebSocketTestReport.html");
        htmlReporter.config().setDocumentTitle("API Testing Report");
        htmlReporter.config().setReportName("Bybit WebSocket API Test");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Host Name","Local Host");
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("Tester","QA");

    }

    @AfterSuite
    public void tearDownSuite(){
        extentReports.flush();
    }
}
