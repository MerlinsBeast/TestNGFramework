package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportingNG {
 static ExtentReports reports;
    public static ExtentReports ReportConfig(){
        String reportPath=System.getProperty("user.dir")+"\\reports\\index.html";
        System.out.println(reportPath);
        ExtentSparkReporter sparkReporter= new ExtentSparkReporter(reportPath);
        sparkReporter.config().setReportName("TESTER NAME- VIJAY YADAV");
        sparkReporter.config().setDocumentTitle("TEST RESULTS");
        sparkReporter.config().setTheme(Theme.DARK);

        reports= new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("TESTER","VIJAY YADAV");
        return reports;
    }

}
