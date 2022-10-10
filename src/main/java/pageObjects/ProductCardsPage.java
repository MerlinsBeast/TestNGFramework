package pageObjects;

import JavaUTILITY.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCardsPage extends ReusableMethods{

    private WebDriver driver;
    public ProductCardsPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy (xpath = "(//div[@class='card']//div[@class='card-body'])")
    WebElement commonPath;
    public WebElement getCommonPath() {
        return commonPath;
    }

    @FindBy (xpath = "(//div[@class='card']//div[@class='card-body'])")
    List<WebElement> commonCards;
    public List<WebElement> getcommonCards() {
        return commonCards;
    }

   public String XpathFirstPart="(//div[@class='card']//div[@class='card-body'])";
   @FindBy(xpath = "(//div[@class='card']//div[@class='card-body'])")
   WebElement firstpart;
    public void addSpecificItemtoCart(WebElement button){
        clickUsingJavaScriptExecutor(button);
    }
    public void waitForLoaderstoVanish(){
        waitTillVisisbilityOfElement(CartIcon);
        waitforElementToVanish(AnimationLoader);
    }


    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement CartIcon;
    @FindBy (xpath = ".ng-animating")
    WebElement AnimationLoader;
   @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
   WebElement CLickableCartIcon;

   public WebElement returnFinalXpath(String element, String finalpart){
       System.out.println("Attempting click on item "+element+finalpart);
       return driver.findElement(By.xpath(element+finalpart));
   }

    public void getCommonElementCards(){
        clickUsingJavaScriptExecutor(commonPath);
    }
    public List<WebElement> returnListOfElements(){
        return commonCards;
    }
    public String return_commonPath(){
    return XpathFirstPart;
    }
    @FindBy(xpath = "(//div[@class='card']//div[@class='card-body'])")
    List<WebElement> commonProductCardPath;
    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement productCart;
    @FindBy(xpath = ".ng-animating")
    WebElement LoadingAnimator;

    public void CLickOnCart(){
        waitForElementsToAcceptClick(CLickableCartIcon);
        clickUsingJavaScriptExecutor(CLickableCartIcon);
    }


    String commonXpath="(//div[@class='card']//div[@class='card-body'])";

    public void clickOnAddToCartForSpecficItems(List<String> itemstoAdd){

        System.out.println("1");
        waitForElementsToAcceptClick(getCommonPath());
        List<WebElement> listofCards= getcommonCards();
        System.out.println("2");
        System.out.println("NUmber of Cards on Page  "+ listofCards.size() );

        for(int i=1;i<listofCards.size();i++){
            String finalxpath=commonXpath+"["+i+"]";
            System.out.println("Xpath constructed for "+i+"th element is "+ finalxpath);
            WebElement eachElement=returnFinalXpath(finalxpath,"//b");
            System.out.println("fetched the XPATH of product for "+i+"th element  "+eachElement);
            String ProdName=eachElement.getText();
            System.out.println("fetched the name of the product for "+i+"th element  "+ProdName);
            if(itemstoAdd.contains(ProdName)){
                WebElement button= returnFinalXpath(finalxpath,"//button[2]");
                addSpecificItemtoCart(button);
            }
            else{
                System.out.println("SOmethings srong ");
            }

        }



        /*            waitForElementsToAcceptClick(commonPath);
                String commonxpath=XpathFirstPart;
            List<WebElement> listofCards=commonCards;
            System.out.println(listofCards.size());
            for(int i=1;i<=listofCards.size();i++){
                String finalexpath= commonxpath+"["+i+"]";
                System.out.println(finalexpath);
                WebElement eachelement=returnFinalXpath(commonxpath,"//b");
                System.out.println(eachelement);
                String ProdName=eachelement.getText();
                System.out.println(ProdName);

                if(itemstoAdd.contains(ProdName)){
                    WebElement button= returnFinalXpath(finalexpath,"//button[2]");
                    addSpecificItemtoCart(button);
                }
                else {
                    System.out.println("Items not in list");
                }
            }*/
    }

}
