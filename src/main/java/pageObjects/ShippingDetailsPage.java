package pageObjects;

import JavaUTILITY.ReusableMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShippingDetailsPage extends ReusableMethods {
    WebDriver driver;
    public ShippingDetailsPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryDropDown;
    @FindBy(xpath = "//div[@class='form-group']//section//button//span")
    List<WebElement> dropDownOptions;
    @FindBy(xpath = "//a[contains(text(),'Place Order')]")
    WebElement placeOrderBUtton;
    public void sendDetailstoCountryDropDown(String datatoEnter) throws InterruptedException {
        Thread.sleep(2000);
        waitForElementsToAcceptClick(countryDropDown);
        clickUsingJavaScriptExecutor(countryDropDown);
        countryDropDown.sendKeys(datatoEnter);
    }

    public void selectDesiredOptionsFromDropDown(String valueToSelect){
        System.out.println("inside teh country selection methods");
        for(int i=0;i<dropDownOptions.size();i++){
            String countryName=dropDownOptions.get(i).getText();
            System.out.println(countryName);
            if(countryName.equalsIgnoreCase(valueToSelect)){
                System.out.println("inside");
                clickUsingJavaScriptExecutor(dropDownOptions.get(i));
                break;
            }
            else{
                System.out.println("out");
            }
        }
    }

    public void clickOnPlaceOrderButton(){
        clickUsingJavaScriptExecutor(placeOrderBUtton);
    }


}
