package pageObjects;

import JavaUTILITY.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CheckOutPage extends ReusableMethods {
    WebDriver driver;
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='cartSection']//h3")
    List<WebElement> commonProductNam;
    @FindBy(xpath = "//ul//li[3]//button[contains(text(),'Checkout')]")
    WebElement CheckOutBUtton;
    public List<WebElement> getCommonProductName() {
        return commonProductNam;
    }
    public void clickOnCheckoutButton(){
        waitForElementsToAcceptClick(CheckOutBUtton);
        clickUsingJavaScriptExecutor(CheckOutBUtton);
    }
public void validateAddedItemsonCheckOutPage(List<String> itemsAdded){
    List<WebElement> productOnCart=getCommonProductName();
    for(int i=0;i<productOnCart.size();i++){
        String prodNameOnCart= productOnCart.get(i).getText();
        System.out.println("Element on Cart is  "+ prodNameOnCart);
        Assert.assertEquals(itemsAdded.get(i),prodNameOnCart);
    }
}

}
