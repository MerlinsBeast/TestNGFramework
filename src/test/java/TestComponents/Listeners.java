package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReportingNG;

import javax.xml.transform.Result;
import java.io.IOException;


public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports reports= ExtentReportingNG.ReportConfig();
    ThreadLocal<ExtentTest> extenetTest= new ThreadLocal();
    @Override
    public void onTestStart(ITestResult result) {
    test=reports.createTest(result.getMethod().getMethodName());
    extenetTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,"Test execution has paased ");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("inside the onTEstFailure methods");
       extenetTest.get().fail(result.getThrowable());
        System.out.println("before frist try");
        try {
            driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filePath= null;
        try {
            filePath = getScreenShot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        reports.flush();
    }
}
