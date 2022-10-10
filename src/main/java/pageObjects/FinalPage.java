package pageObjects;

import JavaUTILITY.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FinalPage extends ReusableMethods{
    WebDriver driver;
    public FinalPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h1[@class='hero-primary']")
    WebElement OrderSuccessMessage;

    public void validateOrderSuccessMessage(String ExpeectedMessage){
        String ActualMessage=OrderSuccessMessage.getText();
    Assert.assertEquals(ActualMessage,ExpeectedMessage.toUpperCase());
    }
}
