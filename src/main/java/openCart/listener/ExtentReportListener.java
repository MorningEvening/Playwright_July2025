package openCart.listener;/*
 * @Project PlayWright_Jul_2025
 * @author  pritipradhan
 */

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import static openCart.factory.Playwrightfactory.takeScreenshot;

public class ExtentReportListener implements ITestListener {

    private static final String OUTPUT_FOLDER = "./build";
    private static final String FILE_NAME = "TestExecutionReport.html";

    private static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static ExtentReports extentReports;

    private static ExtentReports init() {
        Path path = Paths.get(OUTPUT_FOLDER);
        if (!Files.exists(path)) {

            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName("Test Results");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System", "MAC");
        extentReports.setSystemInfo("Author", "PRITI");
        extentReports.setSystemInfo("BUILD#", "1.1");
        extentReports.setSystemInfo("TEAM", "OMS");

        return extentReports;
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite started!!");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite is ending!!");
        extent.flush();
        test.remove();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0,last).lastIndexOf(".");
        String className = qualifiedName.substring(mid+1, last);

        System.out.println(methodName + " started");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " passed");
        test.get().pass("Test passed");
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " Failed!");
        test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " Skipped!!");
    }

    private Date getTime(long milis){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milis);
        return calendar.getTime();
    }
}
