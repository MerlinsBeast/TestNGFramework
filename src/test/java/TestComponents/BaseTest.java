package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import pageObjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest{

public WebDriver driver;
public LandingPage pageObject_LandingPage;
    public WebDriver initializeDriver() throws IOException {

        Properties prop= new Properties();
        FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/GlobalProperty.properties");
        prop.load(fis);
        String browserName=System.getProperty("BrowserName")!=null ? System.getProperty("BrowserName"): prop.getProperty("BrowserName");
//        String browserName= prop.getProperty("BrowserName");

        if(browserName.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().setup();
            driver= new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("EDGE")) {
            System.setProperty("webdriver.edge.driver","edge.exe");
            driver= new EdgeDriver();
        }
        else{
            System.out.println("Please check the Browser value in GLobal propertied file!! Something seems wrong with value");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
return driver;
    }

    @BeforeMethod()
    public WebDriver  LaunchApplication() throws IOException {
       driver= initializeDriver();
        System.out.println("inside the new implementation");
        driver.get("https://rahulshettyacademy.com/client");
        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("document.body.style.zoom = '0.7'");

        pageObject_LandingPage = new LandingPage(driver);
        pageObject_LandingPage.LoginIntoApplicationUsing("yd276vijay@gmail.com","Merlins@01Sword");
        System.out.println("completed methods execution");
        return driver;
    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }
    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts= (TakesScreenshot) driver;
        File source= ts.getScreenshotAs(OutputType.FILE);
        File file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }


}
