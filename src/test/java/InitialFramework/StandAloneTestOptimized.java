package InitialFramework;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StandAloneTestOptimized extends BaseTest{

//    WebDriver driver;
    @Test(retryAnalyzer = Retry.class)
    public void CompleteFlowtoAddItemsAndValidation() throws IOException, InterruptedException {

        ProductCardsPage productsPage= new ProductCardsPage(driver);
        CheckOutPage checkOutPage= new CheckOutPage(driver);
        ShippingDetailsPage shippingPage= new ShippingDetailsPage(driver);
        FinalPage finalPage= new FinalPage(driver);
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

    }

}
