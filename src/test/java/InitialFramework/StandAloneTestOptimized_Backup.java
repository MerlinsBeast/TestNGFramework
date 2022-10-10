package InitialFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StandAloneTestOptimized_Backup {

    WebDriver driver;
    @Test
    public void CompleteFlowtoAddItemsAndValidation() throws IOException, InterruptedException {

        //LaunchApplication();
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("document.body.style.zoom = '0.7'");
        LandingPage pageObject_LandingPage = new LandingPage(driver);
        ProductCardsPage productsPage= new ProductCardsPage(driver);
        CheckOutPage checkOutPage= new CheckOutPage(driver);
        ShippingDetailsPage shippingPage= new ShippingDetailsPage(driver);
        FinalPage finalPage= new FinalPage(driver);


        // Logging using the username and Passwd

        pageObject_LandingPage.LoginIntoApplicationUsing("yd276vijay@gmail.com","Merlins@01Sword");
        //Below are the Items to be added.....
        List<String> itemsToBeAdded= new ArrayList<>(List.of("ZARA COAT 3","ADIDAS ORIGINAL"));
        //Adding items in above list to Cart using the custom built method
        productsPage.clickOnAddToCartForSpecficItems(itemsToBeAdded);
        //Clikcing on teh CART button to land on Checkout page
        productsPage.CLickOnCart();
        // Validating that the added items are available on the CheckoutPage using the below custom built method
        checkOutPage.validateAddedItemsonCheckOutPage(itemsToBeAdded);
        // CLicking on the CHECKOUT button to land on Shipping Page
        checkOutPage.clickOnCheckoutButton();
        //Adding Country as INDIA on shipping page using the below custom built methods
        shippingPage.sendDetailstoCountryDropDown("IND");
        String CountryToBeSelected="India";
        shippingPage.selectDesiredOptionsFromDropDown(CountryToBeSelected);
        shippingPage.clickOnPlaceOrderButton();
        // Order success validation using below custom built method
        finalPage.validateOrderSuccessMessage("Thankyou for the Order.");
//        driver.close();
    }
}
