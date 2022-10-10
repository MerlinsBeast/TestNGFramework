package pageObjects;

import JavaUTILITY.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ForgotPasswordPage extends ReusableMethods {

    WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(),'Save New Password')]")
    WebElement SaveNewPasswordbtn;
    public WebElement getSaveNewPasswordbtn() {
        waitForElementsToAcceptClick(SaveNewPasswordbtn);
        return SaveNewPasswordbtn;
    }
    public void clickOnSaveNewBUtton(){
        clickUsingJavaScriptExecutor(SaveNewPasswordbtn);
    }
    @FindBy(xpath = "(//div[@class='ng-star-inserted'])")
    List<WebElement> allErrorMessages;
    String commonPath= "(//div//div[@class='ng-star-inserted'])";
       public String returnFinalXpathForErrorMessages(String commonPath, String finalpart){
        System.out.println("Attempting to gettext of item  ->   "+commonPath+finalpart);
        System.out.println(driver.findElement(By.xpath(commonPath+finalpart)).getText());
        return driver.findElement(By.xpath(commonPath+finalpart)).getText();
    }

    public void getallErrorMessages(List<String> expectedErrorMessage){
        System.out.println("inside");
        for(int i=1;i<=expectedErrorMessage.size();i++){
            System.out.println("sindie for");
            String firstPath= commonPath;
            System.out.println("common path is  "+firstPath);
            String finalpath="["+i+"]";
            System.out.println("Final part is "+finalpath);
            String valueCaptured=returnFinalXpathForErrorMessages(commonPath,finalpath);
            System.out.println("ACtual Error fetched is "+valueCaptured);
            System.out.println("Expecetd Error fetched is "+expectedErrorMessage.get(i));
            Assert.assertEquals(valueCaptured,expectedErrorMessage.get(i));
        }
    }


}
