package pageObjects;

import JavaUTILITY.ReusableMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LandingPage extends ReusableMethods{
    WebDriver driver;


    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }
    // Will use PageFactory as the Design pattern
    @FindBy(xpath = "//input[@type='email']")
    WebElement UserName;
    @FindBy(xpath = "//input[@type='password']")
    WebElement Password;
    @FindBy(xpath = "//input[@id='login']")
    WebElement LoginButton;


    @FindBy(css = "[class*='flyInOut']")
    WebElement ErrorMessageToast;
    public WebElement getErrorMessageToast() {
        return ErrorMessageToast;
    }
    @FindBy(xpath = "//a[@class='forgot-password-link']")
    WebElement ForgotPasswordLink;
    public WebElement getForgotPasswordLink() throws InterruptedException {
        Thread.sleep(4000);
        return ForgotPasswordLink;
    }
    public void landonForgotPasswordPage(){
clickUsingJavaScriptExecutor(ForgotPasswordLink);
    }


    //    ReusableMethods JSmethods= new ReusableMethods(driver);
    public void LoginIntoApplicationUsing(String Username, String Passwd){
        UserName.sendKeys(Username);
        Password.sendKeys(Passwd);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",LoginButton);
    }

    public void Validate_IncorrectErrorLoginErrorMessage(String expErrorMessage) throws InterruptedException {
        waitTillVisisbilityOfElement(ErrorMessageToast);
//        Thread.sleep(1000);
        String ToastMessage= ErrorMessageToast.getText();
        Assert.assertEquals(ToastMessage,expErrorMessage);
    }

}
